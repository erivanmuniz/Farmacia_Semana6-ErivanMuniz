package br.com.farmacia.service;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.modelo.Produto;
import br.com.farmacia.modelo.RegraDeNegocioException;

import java.util.List;


public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void cadastrarNovoProduto(int id, Produto produto) {
        produto.setId(id);
        produtoDAO.salvar(id, produto);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoDAO.listarTodos();
    }

    public void editarPrecoProduto(int idProduto, double novoPreco) {
        if (idProduto <= 0) {
            throw new RegraDeNegocioException("ID do produto inválido");
        }

        // Verifica se o produto com o ID fornecido existe antes de editar o preço
        if (produtoDAO.existeProduto(idProduto)) {
            produtoDAO.editarPreco(idProduto, novoPreco);
        } else {
            throw new RegraDeNegocioException("Produto com o ID fornecido não encontrado");
        }
    }

    public void deletarProduto(int idProduto) {
        if (idProduto <= 0) {
            throw new RegraDeNegocioException("ID do produto inválido");
        }

        // Verifica se o produto com o ID fornecido existe antes de deletá-lo
        if (produtoDAO.existeProduto(idProduto)) {
            produtoDAO.deletarProduto(idProduto);
        } else {
            throw new RegraDeNegocioException("Produto com o ID fornecido não encontrado");
        }
    }
}
