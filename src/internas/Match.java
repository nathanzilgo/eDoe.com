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
	
	
}
