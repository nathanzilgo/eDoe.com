package internas;

public class Match {
	private int pontos;
	private String descricao;
	private String tags;
	
	public Match(int pontos, String descricao, String tags) {
		super();
		this.pontos = pontos;
		this.descricao = descricao;
		this.tags = tags;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(pontos);
		builder.append(", ");
		builder.append(descricao);
		builder.append(", tags:[");
		builder.append(tags);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
