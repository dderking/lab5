package fornecedor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Fornecedor implements Comparable<Fornecedor> {

	private String nome;
	private String email;
	private String telefone;
	private Map<String, Produto> produtos;
	private Map<String, Conta> contas;

	public Fornecedor(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap<>();
		this.contas = new HashMap<>();

	}

	public void cadastraProduto(String nome, String descricao, double preco) {
		if (!existeProduto(nome, descricao)) {
			this.produtos.put(nome + descricao, new Produto(nome, descricao, preco));
		}
	}

	public void cadastraCompra(String cpf, String nomeCliente, String nomeProduto, String descricao, String data) {
		if (existeProduto(nomeProduto, descricao)) {
			if (contas.containsKey(cpf)) {
				contas.get(cpf).adicionaCompras(nomeProduto, data, produtos.get(nomeProduto + descricao).getPreco());
			} else {
				contas.put(cpf, new Conta(this.nome, nomeCliente));
				contas.get(cpf).adicionaCompras(nomeProduto, data, produtos.get(nomeProduto + descricao).getPreco());
			}
		}
	}

	public boolean existeProduto(String nome, String descricao) {
		return this.produtos.containsKey(nome + descricao);
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String exibeProduto(String nome, String descricao) {
		if (existeProduto(nome, descricao)) {
			return this.produtos.get(nome + descricao).toString();
		}
		throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");

	}

	public String exibeProdutos() {
		if (this.produtos.isEmpty()) {
			return this.nome + " -";
		}
		List<Produto> todosProdutos = new ArrayList<Produto>();
		todosProdutos.addAll(this.produtos.values());
		Collections.sort(todosProdutos);
		String[] produtosToString = new String[this.produtos.size()];
		for (int i = 0; i < this.produtos.size(); i++) {
			produtosToString[i] = this.nome + " - " + todosProdutos.get(i).toString();
		}
		return String.join(" | ", produtosToString);
	}

	public String getDebitoConta(String cpf) {
		if (contas.containsKey(cpf)) {
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			nf.setMinimumFractionDigits(2);			
			return nf.format(contas.get(cpf).getDebito());
		}
		throw new NullPointerException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");

	}

	public String exibeContaCliente(String cpf) {
		if(contas.containsKey(cpf)) {
			return contas.get(cpf).toString();
			
		}
		else {
			throw new NullPointerException("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
	}

	public String exibeConta(String cpf) {
		if(contas.containsKey(cpf)) {
			return contas.get(cpf).contaFormatada();
			
		}
		else {
			throw new NullPointerException("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
	}

	public void editaProduto(String nome, String descricao, double novoPreco) {
		if (existeProduto(nome, descricao)) {
			this.produtos.get(nome + descricao).setPreco(novoPreco);
		} else {
			throw new IllegalArgumentException("Erro na edicao de produto: produto nao existe.");

		}
	}

	public void removeProduto(String nome, String descricao) {
		if (existeProduto(nome, descricao)) {
			this.produtos.remove(nome + descricao);
		} else {
			throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");

		}
	}

	public int compareTo(Fornecedor fornecedor) {
		return this.nome.compareTo(fornecedor.getNome());
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}



}
