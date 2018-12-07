package internas;

public class Match {
	private int pontos;
	private String descricao;
	private String tags;
	private Item itemMatch;
	
	public Match(int pontos, Item itemMatch) {
		super();
		this.pontos = pontos;
		this.itemMatch = itemMatch;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
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
	
	public int getItemId() {
		return this.itemMatch.getId();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(pontos);
		builder.append(", ");
		builder.append(descricao);
		builder.append(", tags:[");
		builder.append(tags);
		builder.append("], quantidade:");
		builder.append(this.itemMatch.getQuantidade());
		builder.append(", doador:");
		builder.append(this.itemMatch.getUsuario().getNome());
		builder.append("/");
		builder.append(this.itemMatch.getUsuario().getId());
		return builder.toString();
	}
	
	
	
}
