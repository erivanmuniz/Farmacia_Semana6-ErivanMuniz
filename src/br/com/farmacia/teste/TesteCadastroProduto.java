package br.com.farmacia.teste;

import br.com.farmacia.modelo.Produto;
import br.com.farmacia.service.ProdutoService;

public class TesteCadastroProduto {

    public static void main(String[] args) {
        // Dados do produto
        int idProduto = 7;
        double preco = 25.30;
        String nome = "Polaramine";
        String fabricante = "Farmácia XYZ";

        // Criação do objeto Produto
        Produto produto = new Produto(idProduto, nome, preco, fabricante);

        // Instanciação do ProdutoService e chamada do método cadastrarNovoProduto
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrarNovoProduto(idProduto, produto);

        // Mensagem de sucesso
        System.out.println("Produto cadastrado com sucesso!");
    }
}
