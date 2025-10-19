package dao;
import java.sql.*;

public class historico {
    int id;
    String nome;
    String descricao;
    double preco;
    int quantidadeEstoque;

    public historico(int id, String nome, String descricao, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Preco: " + preco + ", Estoque: " + quantidadeEstoque;
    }
}