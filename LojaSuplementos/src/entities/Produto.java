package entities;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int quantidade = 0;

	public Produto(int id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void adicionarEstoque(int qtd) {
		quantidade += qtd;
	}
	public boolean venderProduto(int qtd) {
		if(quantidade >= qtd) {
			quantidade -= qtd;
			return true;
		}else{
			return false;
		}
	}
	
	public String toString() {
		return "ID: " + id +
			   "| Produto: " + nome + 
			   " | Preço: "+ preco + 
			   "| Estoque: " + quantidade;
	}
	
}
