package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Postagem;

public class PostagemDAO {
	private static final String COLUNAS = "SELECT p.id, p.titulo, p.imagem, p.autor_id"
			+ "p.data_publicacao, p.status, u.nome AS autor_nome"
			+ "FROM postagem p INNER JOIN usuario u ON.id = p.autor_id";
	
	public void cadastrar(Postagem postagem) throws Exception {
		String sql = "INSERT INTO postagem" + "(titulo, conteudo, imagem, autor_id, status) VALUES(?, ?, ?, ?, ?)";
	
		try (Connection connection = ConexaoDB.getConexao();
			PreparedStatement statement = connection.prepareCall(sql)) {
			statement.setString(1, postagem.getTitulo());
			statement.setString(2, postagem.getConteudo());
			statement.setString(3, postagem.getImagem());
			statement.setInt(4, postagem.getAutorId());
			statement.setString(5, "PUBLICADA");
			
		}
				
	}

}
