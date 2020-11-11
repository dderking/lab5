package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cliente.Cliente;
import validator.Validator;

/**
 * 
 * Controller responsável por administrar métodos e lista que estão relacionados
 * a cliente.
 * 
 * @author Juan Silva - 119210821
 *
 */

public class ControllerCliente {
	private Map<String, Cliente> clientes;

	/**
	 * Construtor responsável por iniciar atributos referente ao controlador de
	 * clientes.
	 */
	public ControllerCliente() {
		this.clientes = new HashMap<>();
	}

	/**
	 * Método responsável por pegar um cliente a partir de seu CPF.
	 * 
	 * @param cpf CPF do cliente na forma de String.
	 * @return Retorna o cliente do tipo Cliente.
	 */
	public Cliente getCliente(String cpf) {
		return clientes.get(cpf);
	}

	/**
	 * Método responsável por adicionar um cliente no sistema.
	 * 
	 * @param cpf         CPF do cliente na forma de String.
	 * @param nome        Nome do cliente na forma de String.
	 * @param email       Email do cliente na forma de String.
	 * @param localizacao Localizacao do cliente na forma de String.
	 * @return Retorna o cpf do cliente na forma de String, se o cadastro for
	 *         concluido corretamente.
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) {
		if (existeCliente(cpf) == false) {
			Cliente cliente = new Cliente(cpf, nome, email, localizacao);
			Validator.verificaStringNull(cpf, "Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(cpf, "Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
			Validator.verificaCpfInvalido(cpf, "Erro no cadastro do cliente: cpf invalido.");
			Validator.verificaStringNull(nome, "Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(nome, "Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
			Validator.verificaStringNull(email, "Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
			Validator.verificaStringVazia(email, "Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
			Validator.verificaStringNull(localizacao,
					"Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
			Validator.verificaStringVazia(localizacao,
					"Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
			this.clientes.put(cpf, cliente);
			return cpf;
		} else {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
		}
	}

	/**
	 * Método responsável por verificar no sistema que existe o cliente a partir de
	 * seu CPF.
	 * 
	 * @param cpf CPF do cliente na forma de String.
	 * @return Retorna um booleano para indicar se existe o cliente no HashMap de
	 *         clientes.
	 */
	public boolean existeCliente(String cpf) {
		return this.clientes.containsKey(cpf);
	}

	/**
	 * Método responsável por exibir um fornecedor na forma de String.
	 * 
	 * @param cpf CPF do cliente na forma de String
	 * @return Retorna um cliente na forma de String.
	 */
	public String exibeCliente(String cpf) {
		Validator.verificaStringNull(cpf, "Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(cpf, "Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.");
		if (existeCliente(cpf) == false) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cliente nao existe.");
		}
		return this.clientes.get(cpf).toString();

	}

	/**
	 * Método responsável por exibir todos os cliente na forma de String.
	 * 
	 * @return Retorna todos os clientes na forma de String.
	 */
	public String exibeTodosClientes() {
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		todosClientes.addAll(this.clientes.values());
		Collections.sort(todosClientes);
		String[] clientesToString = new String[this.clientes.size()];
		for (int i = 0; i < this.clientes.size(); i++) {
			clientesToString[i] = todosClientes.get(i).toString();
		}
		return String.join(" | ", clientesToString);

	}

	/**
	 * Método responsável por editar informações de um fornecedor.
	 * 
	 * @param cpf       CPF do cliente na forma de String.
	 * @param atributo  Atributo no qual sera alterado no cliente na forma de
	 *                  String.
	 * @param novoValor NovoValor informado para alteração do cliente na forma de
	 *                  String.
	 * @return Retorna o novoValor inserido do cliente na forma de String, se a
	 *         edição for concluída com sucesso.
	 */
	public String editaCliente(String cpf, String atributo, String novoValor) {
		Validator.verificaStringNull(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(cpf, "Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(atributo, "Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(atributo, "Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(novoValor, "Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(novoValor, "Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		if (existeCliente(cpf) == false) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cliente nao existe.");
		}

		switch (atributo) {

		case "cpf":
			throw new IllegalArgumentException("Erro na edicao do cliente: cpf nao pode ser editado.");
		case "nome":
			getCliente(cpf).setNome(novoValor);
			break;
		case "email":
			getCliente(cpf).setEmail(novoValor);
			break;
		case "localizacao":
			getCliente(cpf).setLocalizacao(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
		}

		return novoValor;
	}

	/**
	 * Método responsável por remover um cliente do sistema a partir de seu CPF.
	 * 
	 * @param cpf CPF do cliente na forma de String.
	 * @return Retorna o CPF do cliente removido, se a remoção for concluída com
	 *         sucesso.
	 */
	public String removeCliente(String cpf) {
		Validator.verificaStringNull(cpf, "Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		Validator.verificaStringVazia(cpf, "Erro na remocao do cliente: cpf nao pode ser vazio ou nulo");
		if (existeCliente(cpf) == false) {
			throw new IllegalArgumentException("Erro na remocao do cliente: cliente nao existe.");
		}
		clientes.remove(cpf);
		return cpf;
	}
}
