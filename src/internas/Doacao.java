package internas;

import java.io.Serializable;

public class Doacao implements Comparable<Doacao>, Serializable {
	private static final long serialVersionUID = 1L;
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

	/**
	 * o metodo getId(), tem como objetivo retornar o id do item doado.
	 * 
	 * @return valor inteiro referente ao id do item.
	 */
	public int getId() {
		return this.itemDoado.getId();
	}

	/**
	 * O metodo toString(), tem como objetivo retornar a representacao textual de
	 * doacao.
	 */
	@Override
	public String toString() {
		return this.data + " - doador: " + this.doador.getNome() + "/" + this.doador.getId() + ", item: "
				+ this.itemDoado.getDescricao() + ", quantidade: " + this.quatidade + ", receptor: "
				+ this.receptor.getNome() + "/" + this.receptor.getId();
	}

	/**
	 * O metodo compareTo(), tem como objetivo comparar duas doacoes pela data.
	 */
	@Override
	public int compareTo(Doacao o) {
		if (this.data.equals(o.data)) {
			return this.itemDoado.getDescricao().compareTo(o.itemDoado.getDescricao());
		}
		return this.dataEmDias() - o.dataEmDias();
	}

	private int dataEmDias() {
		String[] s = data.split("/");
		int dia = Integer.parseInt(s[0]);
		int mes = Integer.parseInt(s[1]);
		int ano = Integer.parseInt(s[2]);
		return dia + mes * 30 + ano * 365;
	}

	/**
	 * O metodo hashCode(), tem como objetivo retornar um codigo hash do objeto.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doador == null) ? 0 : doador.hashCode());
		result = prime * result + ((itemDoado == null) ? 0 : itemDoado.hashCode());
		result = prime * result + quatidade;
		result = prime * result + ((receptor == null) ? 0 : receptor.hashCode());
		return result;
	}

	/**
	 * O metodo equals(), tem como objetivo comparar se um objeto e igual a outro.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doacao other = (Doacao) obj;
		if (doador == null) {
			if (other.doador != null)
				return false;
		} else if (!doador.equals(other.doador))
			return false;
		if (itemDoado == null) {
			if (other.itemDoado != null)
				return false;
		} else if (!itemDoado.equals(other.itemDoado))
			return false;
		if (quatidade != other.quatidade)
			return false;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		return true;
	}

}
