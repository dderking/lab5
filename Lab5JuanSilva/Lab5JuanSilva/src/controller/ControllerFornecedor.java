package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fornecedor.Fornecedor;
import validator.Validator;

/**
 * Controller responsável por administrar métodos e lista que estão relacionados
 * a fornecedor.
 * 
 * @author Juan Silva - 119210821
 *
 */
public class ControllerFornecedor {
	private Map<String, Fornecedor> fornecedores;

	/**
	 * Construtor responsável por iniciar atributos referente ao controlador de
	 * fornecedores.
	 */
	public ControllerFornecedor(Map<String, Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	/**
	 * Método responsável por pegar um fornecedor a partir de seu nome.
	 * 
	 * @param nome Nome do fornecedor na forma de String.
	 * @return Retorna o fornecedor do tipo Fornecedor.
	 */
	public Fornecedor getFornecedor(String nome) {
		return fornecedores.get(nome);
	}

	/**
	 * Método responsável por adicionar um fornecedor ao sistema.
	 * 
	 * @param nome     Nome do fornecedor na forma de String.
	 * @param email    Email do fornecedor na forma de String.
	 * @param telefone Telefone do fornecedor na forma de String.
	 * @return Retorna o nome do fornecedor na forma de String, se o cadastro for
	 *         concluido corretamente.
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		if (existeFornecedor(nome) == false) {
			Fornecedor fornecedor = new Fornecedor(nome, email, telefone);
			Validator.verificaStringNull(nome, "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(nome, "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
			Validator.verificaStringNull(email, "Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(email, "Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
			Validator.verificaStringNull(telefone,
					"Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(telefone,
					"Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
			this.fornecedores.put(nome, fornecedor);
			return nome;
		} else {
			throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		}
	}

	/**
	 * Método responsável por verificar no sistema que existe o fornecedor a partir
	 * de seu nome.
	 * 
	 * @param nome Nome do fornecedor na forma de String.
	 * @return Retorna um booleano para indicar se existe o fornecedor no HashMap de
	 *         fornecedores.
	 */
	public boolean existeFornecedor(String nome) {
		return this.fornecedores.containsKey(nome);
	}

	/**
	 * Método responsável por pegar o HashMap de fornecedores.
	 * 
	 * @return retorna o HashMap de fornecedores
	 */
	public Map<String, Fornecedor> getFornecedores() {
		return fornecedores;
	}

	/**
	 * Método responsável por exibir um fornecedor na forma de String.
	 * 
	 * @param nome Nome do fornecedor na forma de String
	 * @return Retorna um fornecedor na forma de String.
	 */
	public String exibeFornecedor(String nome) {
		Validator.verificaStringNull(nome, "Erro na exibicao do fornecedor: fornecedor nao existe.");
		Validator.verificaStringVazia(nome, "Erro na exibicao do fornecedor: fornecedor nao existe.");
		if (existeFornecedor(nome) == false) {
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}
		return getFornecedores().get(nome).toString();

	}

	/**
	 * Método responsável por exibir todos os fornecedores na forma de String.
	 * 
	 * @return Retorna todos os fornecedores na forma de String.
	 */
	public String exibeTodosFornecedores() {

		List<Fornecedor> todosFornecedor = new ArrayList<Fornecedor>();
		todosFornecedor.addAll(this.fornecedores.values());
		Collections.sort(todosFornecedor);
		String[] fornecedoresToString = new String[this.fornecedores.size()];
		for (int i = 0; i < this.fornecedores.size(); i++) {
			fornecedoresToString[i] = todosFornecedor.get(i).toString();
		}
		return String.join(" | ", fornecedoresToString);

	}

	/**
	 * Método responsável por editar informações de um fornecedor.
	 * 
	 * @param nome      Nome do fornecedor na forma de String.
	 * @param atributo  Atributo no qual sera alterado no fornecedor na forma de
	 *                  String.
	 * @param novoValor NovoValor informado para alteração do fornecedor na forma de
	 *                  String.
	 * @return Retorna o novoValor inserido do fornecedor na forma de String, se a
	 *         edição for concluída com sucesso.
	 */
	public String editaFornecedor(String nome, String atributo, String novoValor) {
		Validator.verificaStringNull(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(atributo, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(atributo, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(novoValor, "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(novoValor,
				"Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		if (existeFornecedor(nome) == false) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: fornecedor nao existe.");
		}

		switch (atributo) {
		case "nome":
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		case "email":
			getFornecedor(nome).setEmail(novoValor);
			break;
		case "telefone":
			getFornecedor(nome).setTelefone(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		}

		return novoValor;
	}

	/**
	 * Método responsável por remover um fornecedor do sistema a partir de seu nome.
	 * 
	 * @param nome Nome do fornecedor na forma de String.
	 * @return Retorna o nome do fornecedor removido, se a remoção for concluída com
	 *         sucesso.
	 */
	public String removeFornecedor(String nome) {
		Validator.verificaStringNull(nome,
				"Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(nome,
				"Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		if (existeFornecedor(nome) == false) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}
		fornecedores.remove(nome);
		return nome;
	}

}
