package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fornecedor.Fornecedor;
import validator.Validator;

/**
 * 
 * Controller responsável por administrar métodos e lista que estão relacionados
 * aos produtos.
 * 
 * @author Juan Silva - 119210821
 *
 */

public class ControllerProdutosDosFornecedores {
	private Map<String, Fornecedor> fornecedores;

	/**
	 * Construtor responsável por iniciar atributos referente ao controlador de
	 * Produtos.
	 * 
	 * @param fornecedores Map de fornecedores
	 */
	public ControllerProdutosDosFornecedores(Map<String, Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;

	}

	/**
	 * Método responsável por verificar se um fornecedor existe.
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @return retorna um booleano
	 */
	public boolean existeFornecedor(String fornecedor) {
		return this.fornecedores.containsKey(fornecedor);
	}

	/**
	 * Método responsável por cadastrar o produto a partir do fornecedor, nome,
	 * descricao e preco.
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @param nome       Nome do Produto
	 * @param descricao  Descricao do Produto
	 * @param preco      Preco do produto
	 */
	public void cadastraProduto(String fornecedor, String nome, String descricao, double preco) {
		Validator.verificaStringNull(fornecedor, "Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(fornecedor,
				"Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		if (existeFornecedor(fornecedor)) {
			if (existeProduto(fornecedor, nome, descricao) == false) {
				Validator.verificaStringNull(nome, "Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
				Validator.verificaStringVazia(nome, "Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
				Validator.verificaStringNull(descricao,
						"Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
				Validator.verificaStringVazia(descricao,
						"Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
				Validator.verificaDoubleValido(preco, "Erro no cadastro de produto: preco invalido.");
				fornecedores.get(fornecedor).cadastraProduto(nome, descricao, preco);
			} else {
				throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
			}
		} else {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao existe.");
		}
	}

	/**
	 * Método responsável por verificar se um produto existe.
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @param nome       Nome do Produto
	 * @param descricao  Descricao do produto
	 * @return retorna um booleano
	 */
	public boolean existeProduto(String fornecedor, String nome, String descricao) {
		if (existeFornecedor(fornecedor)) {
			return this.fornecedores.get(fornecedor).existeProduto(nome, descricao);
		}
		return false;
	}

	/**
	 * Método responsável por exibir um produto atraves do nome do fornecedor.
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @return retorna um String com um produto
	 */
	public String exibeProduto(String fornecedor, String nome, String descricao) {
		Validator.verificaStringNull(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(fornecedor,
				"Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(nome, "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(nome, "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(descricao, "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		Validator.verificaStringVazia(descricao, "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		if (existeFornecedor(fornecedor)) {
			if (existeProduto(fornecedor, nome, descricao)) {
				return this.fornecedores.get(fornecedor).exibeProduto(nome, descricao);
			} else {
				throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");
			}
		} else {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		}

	}

	/**
	 * Método responsável por exibir um produto atraves do nome do fornecedor.
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @return retorna um String com um produto
	 */
	public String exibeProdutosFornecedor(String fornecedor) {
		Validator.verificaStringNull(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(fornecedor,
				"Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		if (existeFornecedor(fornecedor)) {
			return this.fornecedores.get(fornecedor).exibeProdutos();
		}
		throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
	}

	/**
	 * Método responsável por exibir todos os produtos
	 * 
	 * @return retorna uma string com todos os produtos
	 */
	public String exibeTodosProdutos() {
		List<Fornecedor> todosFornecedores = new ArrayList<Fornecedor>();
		todosFornecedores.addAll(this.fornecedores.values());
		Collections.sort(todosFornecedores);
		String[] produtosToString = new String[this.fornecedores.size()];
		for (int i = 0; i < this.fornecedores.size(); i++) {
			produtosToString[i] = todosFornecedores.get(i).exibeProdutos();
		}
		return String.join(" | ", produtosToString);

	}

	/**
	 * Método responsável por editar o produto a partir do nome,descricao,fornecedor
	 * e novoPreco.
	 * 
	 * @param nome       Nome do produto
	 * @param descricao  Descricao do produto
	 * @param fornecedor Nome do Fornecedor
	 * @param novoPreco  Novo preco do produto
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		Validator.verificaStringNull(nome, "Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(nome, "Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(descricao, "Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		Validator.verificaStringVazia(descricao, "Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		Validator.verificaStringNull(fornecedor, "Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(fornecedor, "Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaDoubleValido(novoPreco, "Erro na edicao de produto: preco invalido.");
		if (existeFornecedor(fornecedor)) {
			if (existeProduto(fornecedor, nome, descricao)) {
				fornecedores.get(fornecedor).editaProduto(nome, descricao, novoPreco);
			} else {
				throw new IllegalArgumentException("Erro na edicao de produto: produto nao existe.");
			}
		} else {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao existe.");
		}
	}

	/**
	 * Método responsável por remover um produto a partir do
	 * nome,descricao,fornecedor.
	 * 
	 * @param nome       Nome do produto
	 * @param descricao  Descricao do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public void removeProduto(String nome, String descricao, String fornecedor) {
		Validator.verificaStringNull(nome, "Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(nome, "Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		Validator.verificaStringNull(descricao, "Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		Validator.verificaStringVazia(descricao, "Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		Validator.verificaStringNull(fornecedor, "Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		Validator.verificaStringVazia(fornecedor, "Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		if (existeFornecedor(fornecedor)) {
			if (existeProduto(fornecedor, nome, descricao)) {
				fornecedores.get(fornecedor).removeProduto(nome, descricao);
			} else {
				throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");
			}
		} else {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao existe.");
		}
	}

}
