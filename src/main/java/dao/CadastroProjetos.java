package dao;

import java.lang.classfile.TypeAnnotation.TargetType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Projeto;

public class CadastroProjetos {
	public boolean CadastrarProjeto(Projeto projeto) throws Exception {
		String sql = """
					INSERT INTO project (titulo, descricao, link, tecnologias, data_criacao)
					VALUES (?, ?, ?, ?, ?)
					""";
		
		try(Connection connect = ConexaoDB.getConexao(); 
			PreparedStatement pStatement = connect.prepareStatement(sql)) {
			pStatement.setString(1, projeto.getTitulo());
			pStatement.setString(2, projeto.getDescricao());
			pStatement.setString(3, projeto.getLink());
			pStatement.setString(4, projeto.getTecnologias());
			pStatement.setString(5, projeto.getData_criacao());
			
			int registroCadastrado = pStatement.executeUpdate();
			return registroCadastrado > 0;
		} catch (SQLException e) {
			throw new SQLException("Erro ao cadastrar o registro" + e.getMessage(), e);
		}
	}
	 public void selecionarProjeto(Projeto projeto) throws Exception {
		 String sql = "SELECT * FROM project WHERE id = ?";
		 
		 try(Connection connect = ConexaoDB.getConexao();
			PreparedStatement pStatement = connect.prepareStatement(sql)) {
			pStatement.setInt(1, projeto.getId());
			
			try( ResultSet rs = pStatement.executeQuery()) {
				if(rs.next()) {
					projeto.setId(rs.getInt("Id"));
					projeto.setTitulo(rs.getString("Titulo"));
					projeto.setDescricao(rs.getString("descrição"));
					projeto.setLink(rs.getString("link"));
					projeto.setTecnologias(rs.getString("tecnologia"));
					projeto.setData_criacao(rs.getString("Data de Criação"));
				}
			} catch (SQLException e) {
				throw new SQLException("Erro ao selecionar o registro"); 
			} 
		 }
		 }
		 
		 public ArrayList<Projeto> ListarProjeto() throws Exception {
			 String sql = "SELECT * FROM project";
			 
			 ArrayList<Projeto> listarProjeto = new ArrayList<>();
			 
			try(Connection connect = ConexaoDB.getConexao();
					PreparedStatement pStatement = connect.prepareStatement(sql);
					ResultSet rs = pStatement.executeQuery()) {
						
					while(rs.next()) {
						Projeto projeto = new Projeto();
						
						projeto.setId(rs.getInt("Id"));
						projeto.setTitulo(rs.getString("Titulo"));
						projeto.setDescricao(rs.getString("descrição"));
						projeto.setLink(rs.getString("link"));
						projeto.setTecnologias(rs.getString("tecnologia"));
						projeto.setData_criacao(rs.getString("Data de Criação"));
						
						listarProjeto.add(projeto);
				}
			}
					catch (SQLException e) {
						throw new SQLException("Erro ao buscar o projeto");
			}
			return listarProjeto;
			
		 }
		 
		public boolean alterarProjeto(Projeto projeto) throws Exception {
			String sql = """
					UPDATE ptoject
					SET titulo = ?, descricao = ?, tecnologias = ?, data_criacao = ?
					WHERE id = ?
					
						""";
			
			try(Connection connect = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setString(1, projeto.getTitulo());
				pStatement.setString(2, projeto.getDescricao());
				pStatement.setString(3, projeto.getLink());
				pStatement.setString(4, projeto.getTecnologias());
				pStatement.setString(5, projeto.getData_criacao());
				pStatement.setInt(6, projeto.getId());
				
				int registroAtualizado = pStatement.executeUpdate();
				
				return registroAtualizado > 0;
			}
			
			catch (SQLException e) {
				throw new SQLException("Erro ao alterar o registro");
	 }
				
			}
			
		public boolean deletarProjeto(int id) throws Exception {
			String sql = "DELET FRCM projeto WHERE id = ?";
			
			try(Connection connect = ConexaoDB.getConexao();
				PreparedStatement pStatement = connect.prepareStatement(sql)) {
				pStatement.setInt(1, id);
				int rowsAffect = pStatement.executeUpdate();
				
				return rowsAffect > 0;
			}
			catch (SQLException e) {
				throw new SQLException("Erro ao excluir o registro");
			}
		}

}
