package br.com.farmacia.teste;

import br.com.farmacia.modelo.Produto;
import br.com.farmacia.modelo.RegraDeNegocioException;
import br.com.farmacia.service.ProdutoService;
import java.util.Scanner;

public class FarmaciaApplication {

    private static ProdutoService produtoService = new ProdutoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var opcao = Menu();

        while (opcao != 5) {
            try {
                switch (opcao) {
                    case 1:
                        listarProdutos();
                        break;
                    case 2:
                        cadastrarProduto();
                        break;
                    case 3:
                        editarProduto();
                        break;
                    case 4:
                        removerProduto();

                }
            } catch (RegraDeNegocioException e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e ENTER para voltar ao menu");
                scanner.next();
            }

            opcao = Menu();

        }

        System.out.println("Finalizando o sistema.");
    }


    private static int Menu() {
        System.out.println("""
                FARMÁCIA - ESCOLHA UMA OPÇÃO:
                1 - Listar Produtos
                2 - Cadastrar Produto
                3 - Editar Produto
                4 - Remover Produto
                5 - Sair
               """);

        return scanner.nextInt();
    }

    private static void listarProdutos() {
        System.out.println("Lista de Produtos:");
        produtoService.listarTodosProdutos().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e ENTER para voltar ao menu");
        scanner.next();
    }

    private static void cadastrarProduto() {
        System.out.println("Digite o ID do produto:");
        int id = scanner.nextInt();
        System.out.println("Digite o nome do produto:");
        String nome = scanner.next();
        System.out.println("Digite o preço do produto:");
        double preco = scanner.nextDouble();
        System.out.println("Digite o fabricante do produto:");
        String fabricante = scanner.next();


        Produto produto = new Produto(id, nome, preco, fabricante);
        produtoService.cadastrarNovoProduto(id, produto);
        System.out.println("Produto cadastrado com sucesso!");

        System.out.println("Pressione qualquer tecla e ENTER para voltar ao menu");
        scanner.next();
    }


    private static void editarProduto() {
        System.out.println("Digite o ID do produto a ser editado:");
        int idProduto = scanner.nextInt();
        System.out.println("Digite o novo preço do produto:");
        double novoPreco = scanner.nextDouble();

        produtoService.editarPrecoProduto(idProduto, novoPreco);
        System.out.println("Preço do produto editado com sucesso!");

        System.out.println("Pressione qualquer tecla e ENTER para voltar ao menu");
        scanner.next();
    }

    private static void removerProduto() {
        System.out.println("Digite o ID do produto a ser removido:");
        int idProduto = scanner.nextInt();
        produtoService.deletarProduto(idProduto);
        System.out.println("Produto removido com sucesso!");

        System.out.println("Pressione qualquer tecla e ENTER para voltar ao menu");
        scanner.next();
    }
}
