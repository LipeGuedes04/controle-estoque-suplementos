package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Produto> produtos = new ArrayList<>();

		
		System.out.println("________ Estoque de Suplementos ___________");
		
		int escolha = 0;
		while (escolha != 5) {
			System.out.println("\n===== Menu Principal =======");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Verificar estoque");
			System.out.println("3 - Dar entrada no estoque");
			System.out.println("4 - Vender produto");
			System.out.println("5 - Sair");
			
			System.out.println("Escolha uma opção: ");
			escolha = sc.nextInt();
			sc.nextLine();

		

		switch (escolha) {
		case 1:
			System.out.println("Insira o id do produto: ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("Insira o nome do produto: ");
			String nome = sc.nextLine();
			System.out.println("Insira o preco do produto: ");
			double preco = sc.nextDouble();

			produtos.add(new Produto(id, nome, preco));
			System.out.println("Produto cadastrado com sucesso!!");

			break;
		case 2:
				if(produtos.isEmpty()) {
					System.out.println("Não possui nenhum produto!!");
				}else {
					for(Produto c : produtos) {
						System.out.println(c);
					}
				}
			break;
		case 3:
			if(produtos.isEmpty()) {
				System.out.println("Não possui nenhum produto no estoque!!");
			} else {
				System.out.println("Digite o Id do produto que chegou: ");
				int buscarID = sc.nextInt();
				sc.nextLine();
				
				Produto IdEcontrado = null;
				
				for(Produto c : produtos) {
					if(c.getId() == buscarID) {
						IdEcontrado = c;
						break;
					}
				}
				
				if(IdEcontrado == null) {
					System.out.println("Produto não encontrado no estoque");
				}else {
					System.out.println("Digite a quantidade que será inserida no estoque: ");
					int quantidade = sc.nextInt();
					sc.nextLine();
					
					IdEcontrado.adicionarEstoque(quantidade);
				}
				
				
			}

			break;
		case 4:
			if(produtos.isEmpty()) {
				System.out.println("Não possui nenhum produto no estoque!!");
			} else {
				System.out.println("Digite o Id do produto que será vendido: ");
				int buscarID = sc.nextInt();
				sc.nextLine();
				
				Produto IdEcontrado = null;
				
				for(Produto c : produtos) {
					if(c.getId() == buscarID) {
						IdEcontrado = c;
						break;
					}
				}
				
				if(IdEcontrado == null) {
					System.out.println("Produto não encontrado no estoque");
				}else {
					System.out.println("Digite a quantidade que será vendida: ");
					int quantidade = sc.nextInt();
					sc.nextLine();
					
					if(IdEcontrado.venderProduto(quantidade) == true) {
						System.out.println("Venda realizada ....");
					}else {
						System.out.println("Estoque insuficiente para essa quantidade!!");
					}
				}
				
				
			}

			break;
		case 5:
			System.out.println("Programa Finalizado!! ");
			break;

		}
	}

		sc.close();
	}

}
