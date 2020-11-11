package fornecedor;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto implements Comparable<Produto> {
	private String nome;
	private String descricao;
	private double preco;

	public Produto(String nome, String descricao, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;

	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int compareTo(Produto produto) {
		return this.nome.compareTo(produto.getNome());
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
		nf.setMinimumFractionDigits(2);
		return this.nome + " - " + this.descricao + " - R$" + nf.format(this.preco);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
}
