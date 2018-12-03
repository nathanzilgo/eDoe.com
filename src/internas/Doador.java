package internas;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Random;

import controllers.*;

public class Doador extends Usuario {

	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
	}

	
	
	
	@Override
	public String toString() {
		return super.toString() + "doador";
	}

	@Override
	public boolean existeItem(int idItem) {
		return this.itens.containsKey(idItem);
	}

	@Override
	public boolean existeItem(Item item) {
		for(Item i: this.itens.values()) {
			if(i.equals(item)) {
				return true;
			}
		}
		return false;
	}

	public HashSet<Integer> getItensIds(){
		return this.idsItens;
	}
}
