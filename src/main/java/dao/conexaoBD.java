package dao;
import java.sql.*;

public interface conexaoBD {

    Connection obterConexao() throws Exception;

    void fecharConexao(Connection conexao);
}
