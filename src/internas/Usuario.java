package internas;

import java.util.Map;

import ferramentas.Validar;

public abstract class Usuario {
	

	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	protected int contadorDeItens = 0;

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
		} if (Validar.checaArgumento(email)) {
			this.email = email;
		} if (Validar.checaArgumento(celular)) {
			this.celular = celular;
		}

	}
	
	@Override
	public String toString() {
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.celular + ", status: ";
	}
	
	/**
	 * Implementada em Doador.java
	 * @param item
	 * @return Integer
	 */
	public abstract int adicionaItem(Item item);
	
	/**
	 * Implementado em Doador.java
	 * Possui comportamento polimorfico com o outro metodo do mesmo nome
	 * @param idItem
	 * @return
	 */
	public abstract boolean existeItem(int idItem);
	
	/**
	 * Implementado em Doador.java
	 * Possui comportamento polimorfico com o outro metodo do mesmo nome.
	 * @param item
	 * @return
	 */
	public abstract boolean existeItem(Item item);
	
	/**
	 * Procura por um item no mapa e o retorna.
	 * @param item
	 * @return
	 */
	public Item getItem(Item item) {
		for(Item i: this.itens.values()) {
			if(i.equals(item)) {
				return i;
			}
		}
		return null;
	}
}


