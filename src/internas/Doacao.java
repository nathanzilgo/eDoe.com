package internas;

public class Doacao {
	private String data;
	private Usuario doador;
	private Item itemDoado;
	private Usuario receptor;
	private int quatidade;

	public Doacao(String data, Usuario doador, Item itemDoado, Usuario receptor, int quantidade) {
		super();
		this.data = data;
		this.doador = doador;
		this.itemDoado = itemDoado;
		this.receptor = receptor;
		this.quatidade = quantidade;
	}

	@Override
	public String toString() {
		return this.data + " - doador: " + this.doador.getNome() + "/" + this.doador.getId() + ", item: "
				+ this.itemDoado.getDescricao() + ", quantidade: " + this.quatidade + ", receptor: "
				+ this.receptor.getNome() + "/" + this.receptor.getId();
	}

	public int getId() {
		return this.itemDoado.getId();
	}

}
