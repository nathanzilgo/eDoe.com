package internas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
		this.itens = new HashMap<>();

	}

	private String getId() {
		StringBuilder stringBuilder = new StringBuilder(this.id);
		if (this.id.length() == 11) {
			stringBuilder.insert(this.id.length() - 8, '.');
			stringBuilder.insert(this.id.length() - 4, '.');
			stringBuilder.insert(this.id.length(), '-');
			return stringBuilder.toString();
		}
		stringBuilder.insert(this.id.length() - 12, '.');
		stringBuilder.insert(this.id.length() - 8, '.');
		stringBuilder.insert(this.id.length() - 4, '/');
		stringBuilder.insert(this.id.length() + 1, '-');
		return stringBuilder.toString();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.celular = telefone;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNome() {
		return this.nome;
	}

	public Map<Integer, Item> getItens() {
		return itens;
	}

	public void setItens(Map<Integer, Item> itens) {
		this.itens = itens;
	}

	public Item getItem(int id) {
		return this.getItens().get(id);
	}

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

	@Override
	public String toString() {
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.celular + ", status: ";
	}

	/**
	 * Implementada em Doador.java
	 * 
	 * @param item
	 * @return Integer
	 */
	public int adicionaItem(int idItem, String descricaoItem, int quantidade, String tags) {

		Item item = new Item(idItem, descricaoItem, quantidade, tags);

		if (existeItem(item)) {
			itens.get(getIdItem(item)).setQuantidade(quantidade);

		}

		if (!existeItem(item)) {
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
	 * Implementado em Doador.java Possui comportamento polimorfico com o outro
	 * metodo do mesmo nome.
	 * 
	 * @param item
	 * @return
	 */
	public boolean existeItem(Item item) {
		return getItem(item) == null ? false : true;
	}

	/**
	 * Procura por um item no mapa e o retorna.
	 * 
	 * @param item
	 * @return
	 */
	public Item getItem(Item item) {
		for (Item i : this.itens.values()) {
			if (i.equals(item)) {
				return i;
			}
		}
		return null;
	}

	public Collection<Item> retornaItensUsuario() {
		return itens.values();
	}

	public String retornaId() {
		return this.id;
	}

	public String atualizaItem(int idItem, int novaQuantidade, String novasTags) {
		if (idItem < 0) {

			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}

		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return itens.get(idItem).atualizaItem(novaQuantidade, novasTags);

	}

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

	private int getIdItem(Item item) {
		return getItem(item).getId();
	}
	public ArrayList<Item> pesquisaDescricao(String entrada) {
		ArrayList<Item> itensPesquisados = new ArrayList<>();
		for(Item item : itens.values()) {
			if(item.pesquisaNaDescricao(entrada)) {
				itensPesquisados.add(item);
			}
		}
		return itensPesquisados;
}
}
