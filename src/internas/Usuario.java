package internas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ferramentas.MatchComparator;
import ferramentas.Validar;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private boolean isReceptor;
	private HashSet<Integer> idsItens;
	private Map<Integer, Item> itens;
	private List<Match> matches;

	public Usuario(String id, String nome, String email, String celular, String classe, boolean isReceptor) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.itens = new LinkedHashMap<>();
		this.idsItens = new HashSet<>();
		this.matches = new ArrayList<>();
		this.isReceptor = isReceptor;
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
		return this.nome + "/" + getId() + ", " + this.email + ", " + this.celular + ", status: "
				+ this.getTipoUsuario();
	}

	private String getTipoUsuario() {
		if (this.isReceptor)
			return "receptor";
		return "doador";
	}

	public boolean getIsReceptor() {
		return isReceptor;
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

		Item item = new Item(idItem, descricaoItem, quantidade, tags, this);

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
	public boolean existeItem(int idItem) {
		return this.itens.containsKey(idItem);
	}

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
	 * Metodo que pesquisa itens dentro do Usuario pela sua descricao.
	 * 
	 * @param Descricao a ser procurada.
	 * @return ArrayList de itens com a descricao procurada.
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
	 * US 5 - Faz um match com os itens passados como parametros.
	 * Ja que a o metodo em controller recebe uma lista de todos os itens com o descritor
	 * desejado, o processo inicia com 20 pontos.
	 * Cria duas listas com as tags do itemMatch e do itemNecessario.
	 * 
	 */
	public void match(Item itemMatch, Item itemNec) {
		int pontos = 20;
		ArrayList<String> itemMatchTags = itemMatch.listaTags();
		ArrayList<String> itemNecTags = itemNec.listaTags();
		
		//System.out.println(itemMatchTags.toString());
		//System.out.println(itemNecTags.toString());
		
		for(int x = 0; x < itemMatchTags.size(); x++) {
			
			for(int k = 0; k < itemNecTags.size(); k++) {
				if(itemMatchTags.get(x).equalsIgnoreCase(itemNecTags.get(k)) && x==k) {
					pontos += 10;
				}
				else if(itemMatchTags.get(x).equalsIgnoreCase(itemNecTags.get(k)) && x!=k) {
					pontos += 5;
				}
			}
		}
		this.matches.add(new Match(pontos, itemMatch));
	}
	
	/**
	 * US 5 - 
	 * Retorna os toString de todos os matches do doador receptor.
	 * Ordenados conforme a especificacao: pontuacao, se for igual: identificador de item.
	 * Por fim, reseta a estrutura matches para que nao se repitam matches passados no mesmo receptor.
	 * 
	 * @return toString() de todos os matches.
	 */
	public String getMatches() {
		StringBuilder builder = new StringBuilder();
		MatchComparator matchComparator = new MatchComparator();
		
		Collections.sort(this.matches, matchComparator);
		
		for(Match mt: this.matches) {
			if(!mt.equals(this.matches.get(matches.size()-1))) {
				builder.append(mt.toString());
				builder.append(" | ");
			}else {
				builder.append(mt.toString());
			}
		}
		this.matches = new ArrayList<>();
		return builder.toString();
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public HashSet<Integer> getIdsItens() {
		return idsItens;
	}

	public void setIdsItens(HashSet<Integer> idsItens) {
		this.idsItens = idsItens;
	}

	public String getEmail() {
		return email;
	}

	public String getClasse() {
		return classe;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setItens(Map<Integer, Item> itens) {
		this.itens = itens;
	}

	/**
	 * Retorna o id do usuario
	 * 
	 * @return String
	 */
	public String getId() {
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
}
