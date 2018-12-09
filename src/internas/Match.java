package internas;

public class Match {
	private int pontos;
	private Item itemMatch;
	
	public Match(int pontos, Item itemMatch) {
		super();
		this.pontos = pontos;
		this.itemMatch = itemMatch;
	}

	public int getPontos() {
		return pontos;
	}
	
	public int getItemId() {
		return this.itemMatch.getId();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.itemMatch.getId());
		builder.append(" - ");
		builder.append(this.itemMatch.getDescricao());
		builder.append(", tags: [");
		builder.append(this.itemMatch.getTags());
		builder.append("], quantidade: ");
		builder.append(this.itemMatch.getQuantidade());
		builder.append(", doador: ");
		builder.append(this.itemMatch.getUsuario().getNome());
		builder.append("/");
		builder.append(this.itemMatch.getUsuario().getId());
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemMatch == null) ? 0 : itemMatch.hashCode());
		result = prime * result + pontos;
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
		Match other = (Match) obj;
		if (itemMatch == null) {
			if (other.itemMatch != null)
				return false;
		} else if (!itemMatch.equals(other.itemMatch))
			return false;
		if (pontos != other.pontos)
			return false;
		return true;
	}
	
}
