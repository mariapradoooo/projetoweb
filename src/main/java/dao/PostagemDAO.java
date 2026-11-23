package dao;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;



import model.Postagem;



public class PostagemDAO {

	private static final String COLUNAS = "SELECT p.id, p,titulo, p.imagem, p.autor_id"

			+ "p.data_publicacap, p.status, u.nome AS autor_nome"

			+ "FROM postagem p INNER JOIN usuario u ON.id = p.autor_id";

	

	public void cadastrar(Postagem postagem) throws Exception {

		String sql = "INSERT INTO postagem" + "(titulo, conteudo, imagem, autor_id, status) VALUES(?,?,?,?,?)";

		

		try(Connection connection = ConexaoDB.getConexao();

			PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, postagem.getTitulo());

			statement.setString(2, postagem.getConteudo());

			statement.setString(3, postagem.getImagem());

			statement.setInt(4, postagem.getAutorId());

			statement.setString (5, "PUBLICADA");

			statement.executeUpdate();

			

		} catch (SQLException e) {

			throw new SQLException("Não foi possivel inserir o registro");

		}

	}

	

	public List<Postagem>listarPublicadas() throws Exception{

		String sql = COLUNAS + "WHERE p.status = 'PLUBLICADA' ORDER BY p.datpublicacao DESC";

		

		return consultar(sql);

	}

	

	public List<Postagem>listarPorAutor(int autorId) throws Exception{

		String sql = COLUNAS + "WHERE p.autor_id = ? ORDER BY p,data_publicacao DESC";

		List<Postagem>postagens = new ArrayList<>();

		

		try(Connection connection = ConexaoDB.getConexao();

			PreparedStatement statement = connection.prepareStatement(sql)){

			statement.setInt(1, autorId);

			preencherLista(statement, postagens);

			

		} catch (SQLException e) {

			throw new SQLException("Não foi possivel Listar as postagens");

		}

	}

	

	public Postagem buscarPorIdEAutor(int id, int autorid) throws Exception{

		String sql = COLUNAS + "WHERE p.id = ? AND p.autor_id = ?";

		

		try(Connection connection = ConexaoDB.getConexao();

			PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);

			statement.setInt(2, autorid);

			

			try(ResultSet result = statement.executeQuery()) {

				if(result.next()) {

					return criarPostagem(result);

				}

				

			} catch (SQLException e) {

				throw new SQLException("Não foram encontrado registros");

			}

			

		} catch (SQLException e) {

			throw new SQLException("Consulta indisponível");

		}

		

		return null;

	}

	

	public boolean atualizar(Postagem postagem) throws Exception{

		String sql = "UPDATE postagem SET titulo = ?, conteudo =?, imagem = ?" + "WHERE id = ?, AND autor_id = ?";

		

		try(Connection connection = ConexaoDB.getConexao();

			PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, postagem.getTitulo());

			statement.setString(2, postagem.getConteudo());

			statement.setString(3, postagem.getImagem());

			statement.setInt(4, postagem.getId());

			statement.setInt(5, postagem.getAutorId());

			return statement.executeUpdate() > 0;

			

		} catch (SQLException e) {

			throw new SQLException("Não foi possível atualizar os dados do usuário selecionado");

		}

	}



}