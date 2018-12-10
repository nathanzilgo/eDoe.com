package internas;

public class Doacao {
	private String data;
	private Usuario doador;
	private Item itemDoado;
	private Usuario receptor;
	
	
	public Doacao(String data, Usuario doador, Item itemDoado, Usuario receptor) {
		super();
		this.data = data;
		this.doador = doador;
		this.itemDoado = itemDoado;
		this.receptor = receptor;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Doacao [data=");
		builder.append(data);
		builder.append(", doador=");
		builder.append(doador);
		builder.append(", itemDoado=");
		builder.append(itemDoado);
		builder.append(", receptor=");
		builder.append(receptor);
		builder.append("]");
		return builder.toString();
	}
	
	
}
