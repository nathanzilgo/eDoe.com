package internas;

import java.util.ArrayList;
import java.util.List;

/**
 * Dois itens para doação são iguais se eles tiverem o mesmo descritor de item e as mesmas tags (na mesma ordem).
 * @author Nathan Fernandes
 *
 */
public class Item {
	private String id;
	private int quantidade;
	private List<String> tags;
	private String descricao;
	private String data;
	
	public Item(String id, int quantidade, List<String> tags, String data) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.tags = tags;
		this.data = data;
	}
	
	public void setQuantidade(int novaQuantidade) {
		this.quantidade = novaQuantidade;
	}
	
	public void setTags(List<String> newTags) {
		this.tags = newTags;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
	
	
}
