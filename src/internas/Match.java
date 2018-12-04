	package internas;

import java.util.HashMap;

public class Match {
	private int pontos;
	private HashMap<Integer, Item> itens;
	private String descricao;
	private String tags;
	
	public Match(int pontos, HashMap<Integer, Item> itens, String descricao, String tags) {
		super();
		this.pontos = pontos;
		this.itens = itens;
		this.descricao = descricao;
		this.tags = tags;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public HashMap<Integer, Item> getItens() {
		return itens;
	}

	public void setItens(HashMap<Integer, Item> itens) {
		this.itens = itens;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
