package br.com.farmacia.teste;

import br.com.farmacia.service.ProdutoService;

public class TesteEditarPrecoProduto {

    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();

        // Suponha que você queira editar o preço do produto com ID 1 para R$ 15.99
        produtoService.editarPrecoProduto(1, 15.99);

        System.out.println("Preço do produto editado com sucesso!");
    }
}
