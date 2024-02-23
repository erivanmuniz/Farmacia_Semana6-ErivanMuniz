package br.com.farmacia.teste;

import br.com.farmacia.service.ProdutoService;

public class TesteDeletarProduto {

    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();

        // Suponha que você queira deletar o produto com ID 1
        produtoService.deletarProduto(1);

        System.out.println("Produto deletado com sucesso!");
    }
}
