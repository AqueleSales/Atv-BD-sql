package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // InputMismatchException não será mais importado explicitamente aqui

public class produto {
    public produto(int id, String nome, String descricao, double preco, int estoque) {
    }

    public static List<historico> listarprodutos(Connection conexao) {
        System.out.println("\nprodutos");
        List<historico> lista = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, preco, quantidade_estoque FROM produtos";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                int estoque = rs.getInt("quantidade_estoque");
                lista.add(new historico(id, nome, descricao, preco, estoque));
            }

        } catch (SQLException e) {
            System.out.println("err no banco ao buscar " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e){}
            try { if (stmt != null) stmt.close(); } catch (SQLException e){}
        }

        System.out.println("finalizado");
        return lista;
    }

    public static void adicionarproduto(Connection conexao, Scanner scanner) {
        String nome = "";
        String descricao = "";
        double preco = 0.0;
        int quantidade = 0;
        boolean inputValido = false;

        System.out.println("\n--- adicionar produto ---");
        try {
            System.out.print("nome: ");
            nome = scanner.nextLine();
            System.out.print("descricao: ");
            descricao = scanner.nextLine();

            System.out.print("preco: ");
            preco = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("estoque: ");
            quantidade = scanner.nextInt();
            scanner.nextLine();
            inputValido = true;

        } catch (Exception e) { 
            System.out.println("erro tente novamente");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        
        if (inputValido) {
            String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = null;
            try {
                pstmt = conexao.prepareStatement(sql);
                pstmt.setString(1, nome);
                pstmt.setString(2, descricao);
                pstmt.setDouble(3, preco);
                pstmt.setInt(4, quantidade);

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("produto adicionado!\n");
                }
            } catch (SQLException e) {
                System.out.println("erro: " + e.getMessage());
            } finally {
                try { if (pstmt != null) pstmt.close(); } catch (SQLException e){}
            }
        }
    }
    
    public static void removerproduto(Connection conexao, Scanner scanner) {
        int id = 0;
        boolean idValido = false;

        System.out.println("\n--- Remover produto ---");
        try {
            System.out.print("ID para remover: ");
            id = scanner.nextInt();
            scanner.nextLine();
            idValido = true;
        } catch (Exception e) {
            System.out.println("erro ID precisa ser um numero inteiro");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }

        if (idValido) {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = conexao.prepareStatement(sql);
                pstmt.setInt(1, id);
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("produto ID " + id + " removido!\n");
                } else {
                    System.out.println("produto ID " + id + " nao encontrado.\n");
                }
            } catch (SQLException e) {
                System.out.println("erro ao remover " + e.getMessage());
            } finally {
                try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
            }
        }
    }
}