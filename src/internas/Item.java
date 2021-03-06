package internas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que representa um Item para doacao.
 * 
 * Dois itens para doacao sao iguais se eles tiverem o mesmo descritor de item e
 * as mesmas tags (na mesma ordem). O ID de cada item eh gerado a partir da ordem
 * em que cada um foi posto em todos os Doadores.
 * 
 * @author Nathan Fernandes
 *
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int quantidade;
	private String tags;
	private String descricao;
	private Usuario usuarioDoador;

	public Item(int id, String descricao, int quantidade, String tags, Usuario usuarioDoador) {
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.usuarioDoador = usuarioDoador;
	}
	/**
	 * @return retorna o Dono do Item.
	 */
	public Usuario getUsuario() {
		return usuarioDoador;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		if (tags.equals(null) || tags.trim().isEmpty()) {
			this.tags = "";
		} else {
			this.tags = tags;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id);
		builder.append(" - ");
		builder.append(this.descricao.toLowerCase());
		builder.append(", tags: [" + this.tagsToString() + "]");
		builder.append(", quantidade: " + this.quantidade);

		return builder.toString();
	}

	public String tagsToString() {
		String saida = "";

		if (tags == null)
			return saida;
		if (tags.trim().isEmpty())
			return saida;

		for (char str : this.tags.toCharArray()) {
			if (str != ',') {
				saida += Character.toString(str);
			} else {
				saida += Character.toString(str) + " ";
			}
		}

		return saida;
	}
	
	/**
	 * US5
	 * Retorna uma lista de todas as tags separadas presentes no item
	 * @return List<String>
	 */
	public ArrayList<String> listaTags(){
		ArrayList<String> saida = new ArrayList<>();
		String tmp = new String();
		
		if (tags == null)
			return saida;
		if (tags.trim().isEmpty())
			return saida;
		
		for(int k = 0; k < this.tags.length(); k++) {
			if(tags.charAt(k) != ',' && k != tags.length()-1) {
				tmp += tags.charAt(k);
			}
			else if(tags.charAt(k) == ',') {
				saida.add(tmp);
				tmp = "";
			}
			else if(k == this.tags.length()-1) {
				tmp += tags.charAt(k);
				saida.add(tmp);
				break;
			}
		}
		return saida;
	}

	public String atualizaItem(int novaQuantidade, String novasTags) {

		if (novaQuantidade > 0) {

			this.quantidade = novaQuantidade;
		}

		if (novasTags != null) {
			if (!novasTags.trim().equals("")) {
				this.tags = novasTags;

			}
		}
		return toString();
	}
	
	public int retornaId() {
		return this.id;
	}
	/**
	 * pesquisa a partir do parametro de entrada se a des
	 * 
	 * @param entrada
	 * @return
	 */
	public boolean pesquisaNaDescricao(String entrada) {
		
		for(String palavra : getDescricao().split(" ")) {
			if(palavra.toLowerCase().equals(entrada.toLowerCase())) {
				return true;
				}
			}
		
		return false;
	}
}
