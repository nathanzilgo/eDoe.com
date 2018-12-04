package internas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import ferramentas.Validar;

public abstract class Usuario {

	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	protected HashSet<Integer> idsItens;
	/**
	 * Estrutura que armazena os itens dos usuarios doadores
	 */
	protected Map<Integer, Item> itens;

	public Usuario(String id, String nome, String email, String celular, String classe) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.itens = new LinkedHashMap<>();
		this.idsItens = new HashSet<>();

	}

	/**
	 * Formata o id do usuario e o retorna
	 * 
	 * @return String
	 */
	private String getId() {
		return this.id;
	}

	/**
	 * Metodo que muda o nome do usuario.
	 * 
	 * @param nome nome novo.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que muda o email do usuario.
	 * 
	 * @param email email novo.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que muda o telefone do usuario.
	 * 
	 * @param telefone telefone novo.
	 */
	public void setTelefone(String telefone) {
		this.celular = telefone;
	}

	/**
	 * Metodo que muda a classe do usuario.
	 * 
	 * @param classe classe nova.
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * Metodo que retorna o nome do usuario.
	 * 
	 * @return retorna o nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo que retorna o mapa de itens.
	 * 
	 * @return retorna o mapa.
	 */
	public Map<Integer, Item> getItens() {
		return itens;
	}

	/**
	 * Metodo que pesquisa um item pelo id e o retorna.
	 * 
	 * @param id id do item a ser retornado.
	 * @return retorna o item pesquisado.
	 */
	public Item getItem(int id) {
		return this.getItens().get(id);
	}

	/**
	 * Metodo que atualiza os dados de um usuario.
	 * 
	 * @param nome    novo nome.
	 * @param email   novo email.
	 * @param celular novo celular.
	 */
	public void atualizaUsuario(String nome, String email, String celular) {
		if (Validar.checaArgumento(nome)) {
			this.nome = nome;
		}
		if (Validar.checaArgumento(email)) {
			this.email = email;
		}
		if (Validar.checaArgumento(celular)) {
			this.celular = celular;
		}

	}

	/**
	 * Representacao em String de um usuario.
	 */
	@Override
	public String toString() {
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.celular + ", status: ";
	}

	/**
	 * Metodo que adiciona um novo item ao mapa de itens.
	 * 
	 * @param idItem        id do item a ser adicionado.
	 * @param descricaoItem descricao do item a ser adicionado.
	 * @param quantidade    quantidade de itens a ser adicionada.
	 * @param tags          tags do item a ser adicionado.
	 * @return retorna o id do item.
	 */
	public int adicionaItem(int idItem, String descricaoItem, int quantidade, String tags) {

		Item item = new Item(idItem, descricaoItem, quantidade, tags);

		if (checaItem(item)) {
			itens.get(getIdItem(item)).setQuantidade(quantidade);

		}

		if (!checaItem(item)) {
			this.itens.put(idItem, item);
			return getIdItem(item);
		}

		return getIdItem(item);

	}

	/**
	 * Implementado em Doador.java Possui comportamento polimorfico com o outro
	 * metodo do mesmo nome
	 * 
	 * @param idItem
	 * @return
	 */
	public abstract boolean existeItem(int idItem);

	/**
	 * Checa se um determinado item existe. Retorna um booleano indicando se existe
	 * ou nao.
	 * 
	 * @param item item a ser checado se existe ou nao.
	 * @return retorna se existe ou nao.
	 */
	public boolean checaItem(Item item) {
		return getItem(item) == null ? false : true;
	}

	/**
	 * Procura por um item no mapa e o retorna.
	 * 
	 * @param item item a ser procurado
	 * @return retorna o item.
	 */
	public Item getItem(Item item) {
		for (Item i : this.itens.values()) {
			if (i.equals(item)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Retorna uma colecao dos itens do usuario.
	 * 
	 * @return retorna uma colecao de itens.
	 */
	public Collection<Item> retornaItensUsuario() {
		return itens.values();
	}

	/**
	 * Retorna o id do usuario.
	 * 
	 * @return retorna o id.
	 */
	public String retornaId() {
		return this.id;
	}

	/**
	 * Atualiza um item do usuario
	 * 
	 * @param idItem         id do item a ser atualizado.
	 * @param novaQuantidade nova quantidade de itens.
	 * @param novasTags      novas tags do item.
	 * @return retorna uma representacao em String do item alterado.
	 */
	public String atualizaItem(int idItem, int novaQuantidade, String novasTags) {
		if (idItem < 0) {

			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}

		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).atualizaItem(novaQuantidade, novasTags);

	}

	/**
	 * Remove um item do usuario.
	 * 
	 * @param idItem id do item a ser removido.
	 */

	public void removeItemNecessario(int idItem) {

		if (itens.size() == 0) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		itens.remove(idItem);
	}

	/**
	 * Metodo privado para pegar o id de um item.
	 * 
	 * @param item item a ser pego o id.
	 * @return retorna o id.
	 */
	private int getIdItem(Item item) {
		return getItem(item).getId();
	}

	/**
	 * Metodo que pesquisa um item pela sua descricao.
	 * 
	 * @param entrada descricao a ser procurada.
	 * @return retorna um ArrayList de itens com a descricao procurada.
	 */
	public ArrayList<Item> pesquisaDescricao(String entrada) {
		ArrayList<Item> itensPesquisados = new ArrayList<>();
		for (Item item : itens.values()) {
			if (item.pesquisaNaDescricao(entrada)) {
				itensPesquisados.add(item);
			}
		}
		return itensPesquisados;
	}
	
	/**
	 * US 5
	 * Chama a funcao dentro do receptor informado e tenta encontrar possiveis matches para o item desejado
	 * Caso nao existam, uma String vazia eh retornada
	 */
	public void match(int idItemNec) {
			
	}
}
