package internas;

public class Doacao implements Comparable<Doacao>{
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

	@Override
	public int compareTo(Doacao o) {
		if(this.data.equals(o.data)) {
			return this.itemDoado.getDescricao().compareTo(o.itemDoado.getDescricao());
		}
		return this.dataEmDias() - o.dataEmDias();
	}
	
	private int dataEmDias() {
		String[] s = data.split("/");
		int dia = Integer.parseInt(s[0]);
		int mes = Integer.parseInt(s[1]);
		int ano = Integer.parseInt(s[2]);
		return dia + mes*30 + ano*365;
	}

}
