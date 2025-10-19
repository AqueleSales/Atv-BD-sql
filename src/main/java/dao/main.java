package dao;

import static dao.produto.*;
import dao.historico;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        conexaoBD banco = new conexaoSQL();
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("iniciando conexão com o banco...");
            conexao = banco.obterConexao();
            System.out.println("conexão deu bom\n");

            while (true) {
                System.out.println("--------- MENU DE OPÇÕES ---------");
                System.out.println("1. listar produtos");
                System.out.println("2. adicionar novo produto");
                System.out.println("3. remover produto por ID");
                System.out.println("4. sair");
                System.out.print("escolha uma opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        try {
                            List<historico> listaDeProdutos = listarprodutos(conexao);

                            if (listaDeProdutos.isEmpty()) {
                                System.out.println("nenhum produto cadastrado.\n");
                            } else {
                                System.out.println("\n--- LISTA DE PRODUTOS ---");
                                System.out.println("ID | Nome | Preco | Estoque");
                                System.out.println("-------------------------------------");
                                for (historico p : listaDeProdutos) {
                                    System.out.println(p.toString()); // Agora usará o toString() de 'historico'
                                }
                                System.out.println("-------------------------------------\n");
                            }
                        } catch (Exception e) {
                            System.out.println("erro ao listar produtos: " + e.getMessage());
                        }
                        break;
                    case "2":
                        adicionarproduto(conexao, scanner);
                        break;
                    case "3":
                        removerproduto(conexao, scanner);
                        break;
                    case "4":
                        System.out.println("encerrando o programa");
                        return;
                    default:
                        System.out.println("opção inválida tente novamente\n");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("\nocorreu uma falha grave na aplicação:");
            e.printStackTrace();
        } finally {
            System.out.println("\nfechando");
            if (conexao != null) {
                banco.fecharConexao(conexao);
            }
            scanner.close();
        }
    }
}