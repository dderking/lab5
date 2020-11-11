package cliente;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Comparable<Cliente> {

	private String cpf;
	private String nome;
	private String email;
	private String localizacao;
	private List<String> contasFornecedores;

	public Cliente(String cpf, String nome, String email, String localizacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localizacao;
		this.contasFornecedores = new ArrayList<String>();
	}

	public void adicionaContaFornecedor(String fornecedor) {
		if (!contasFornecedores.contains(fornecedor)) {
			contasFornecedores.add(fornecedor);
		}

	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public List<String> getContasFornecedores() {
		return this.contasFornecedores;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String formataNomeCliente() {
		return "Cliente: "+this.nome;
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email;
	}

	public int compareTo(Cliente cliente) {
		return this.nome.compareTo(cliente.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
