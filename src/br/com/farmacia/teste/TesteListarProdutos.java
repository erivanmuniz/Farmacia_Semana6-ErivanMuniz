package br.com.farmacia.teste;

import br.com.farmacia.modelo.Produto;
import br.com.farmacia.service.ProdutoService;

import java.util.List;

public class TesteListarProdutos {

    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.listarTodosProdutos();

        produtos.forEach(System.out::println);
    }
}
