package dao;

import java.lang.classfile.TypeAnnotation.TargetType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Projeto;

public class CadastroProjetos {
	public boolean CadastrarProjeto(Projeto projeto) throws Exception {
		String sql = """
					INSERT INTO project (titulo, descricao, link, tecnologia, data_criacao)
					VALUES (?, ?, ?, ?, ?)
					""";
		
		try(Connection connect = ConexaoDB.getConexao(); 
			PreparedStatement pStatement = connect.prepareStatement(sql)) {
			pStatement.setString(1, projeto.getTitulo());
			pStatement.setString(2, projeto.getDescricao());
			pStatement.setString(3, projeto.getLink());
			pStatement.setString(4, projeto.getTecnologia());
			pStatement.setString(5, projeto.getData_criacao());
			
			int registroCadastrado = pStatement.executeUpdate();
			return registroCadastrado > 0;
		} catch (SQLException e) {
			throw new SQLException("Erro ao cadastrar o registro" + e.getMessage(), e);
		}
	}
	 public void selecionarProjeto(Projeto projeto) throws Exception {
		 String sql = "SeLECT * FROM project WHERE id = ?";
		 
		 try(Connection connect = ConexaoDB.getConexao();
			PreparedStatement pStatement = connect.prepareStatement(sql)) {
			p
		 }
				 
				 
				 
	 }

}
