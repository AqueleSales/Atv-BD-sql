package dao;
import java.sql.*;

public class conexaoSQL implements conexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3307/ecommerce?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "catolica";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    public Connection obterConexao() throws Exception {
        try {
            Class.forName(DRIVER);
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso! ✨");
            return conexao;
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver JDBC do MySQL não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            // A exceção original estava sendo capturada aqui
            throw new Exception("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void fecharConexao(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}