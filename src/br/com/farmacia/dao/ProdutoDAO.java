package br.com.farmacia.dao;

import br.com.farmacia.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {


    private ConnectionFactory connectionFactory;

    public ProdutoDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    //Todos os métodos da classe ProdutoDao estão utilizando o try-with-resources, para garantir que a
    //conexão com o banco de dadose os recuros sejam fechados automaticamente após o término dos blocos try

    public void salvar(int id, Produto produto) {
        String sql = "INSERT INTO produto (id, nome_produto, preco, fabricante) VALUES (?, ?, ?, ?)";

        try (Connection connection = connectionFactory.recuperarConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setString(4, produto.getFabricante());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o produto", e);
        }
    }

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto";

        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = connectionFactory.recuperarConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomeProduto = resultSet.getString("nome_produto");
                double preco = resultSet.getDouble("preco");
                String fabricante = resultSet.getString("fabricante");

                Produto produto = new Produto(id, nomeProduto, preco, fabricante);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os produtos", e);
        }

        return produtos;
    }

    public void editarPreco(int idProduto, double novoPreco) {
        if (existeProduto(idProduto)) {
            String sql = "UPDATE produto SET preco = ? WHERE id = ?";

            try (Connection connection = connectionFactory.recuperarConexao();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setDouble(1, novoPreco);
                preparedStatement.setInt(2, idProduto);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao editar o preço do produto", e);
            }
        } else {
            throw new IllegalArgumentException("Produto com o ID fornecido não encontrado");
        }
    }

    public void deletarProduto(int idProduto) {
        if (existeProduto(idProduto)) {
            String sql = "DELETE FROM produto WHERE id = ?";

            try (Connection connection = connectionFactory.recuperarConexao();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, idProduto);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao deletar o produto", e);
            }
        } else {
            throw new IllegalArgumentException("Produto com o ID fornecido não encontrado");
        }
    }

    public boolean existeProduto(int idProduto) {
        String sql = "SELECT COUNT(*) AS total FROM produto WHERE id = ?";

        try (Connection connection = connectionFactory.recuperarConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idProduto);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar a existência do produto", e);
        }

        return false;
    }
}
