package internas;

import java.util.LinkedHashMap;
import java.util.Map;

public class Doador extends Usuario {
	
	/**
	 * Variavel que é incrementada a cada item adicionado ao sistema.
	 * gera o id de cada item.
	 */
	int contadorDeItens;
	
	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.itens = new LinkedHashMap<>();
		this.contadorDeItens = 0;
	}
	
	/**
	 * Adiciona um item a um usuário doador e retorna seu ID (contadorDeItens)
	 * Coloca o Id de Item em seus atributos.
	 */
	public int adicionaItem(Item item) {
		contadorDeItens ++;
		this.itens.put(contadorDeItens, item);
		
		this.itens.get(contadorDeItens).setId(contadorDeItens);
		
		return contadorDeItens;
	}
	
	@Override
	public String toString() {
		return super.toString() + "doador";
	}

	@Override
	public boolean existeItem(int idItem) {
		return this.itens.containsKey(idItem);
	}
}
