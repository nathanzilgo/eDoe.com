package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import internas.Item;
import internas.Usuario;

class UsuarioTest {

	private Usuario novoDoador = new Usuario("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br",
			"(83) 9990-9999", "pessoa_fisica", false);
	private Usuario novoReceptor = new Usuario("91724529000103", "Pedro Henrique",
			"pedrohenriquedavimoura_@keffin.com.br", "(27) 98460-3045", "ORGAO_PUBLICO_MUNICIPAL", true);
	private Item itemUm = new Item(1, "cadeira de rodas", 1, "manual, adulto", novoDoador);

	@Test
	void testSetNome() {
		this.novoDoador.setNome("Raquel");
		assertEquals("Raquel/01234567899, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setNome("Pedro");
		assertEquals("Pedro/91724529000103, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testSetEmail() {
		this.novoDoador.setEmail("raquel@ccc.ufcg.edu.br");
		assertEquals("Raquel Lopes/01234567899, raquel@ccc.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setEmail("pedro@gmail.com");
		assertEquals("Pedro Henrique/91724529000103, pedro@gmail.com, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testSetTelefone() {
		this.novoDoador.setTelefone("0000 - 0000");
		assertEquals("Raquel Lopes/01234567899, raquel@computacao.ufcg.edu.br, 0000 - 0000, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setTelefone("(27) 0000 - 0000");
		assertEquals(
				"Pedro Henrique/91724529000103, pedrohenriquedavimoura_@keffin.com.br, (27) 0000 - 0000, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testGetNome() {
		assertEquals("Raquel Lopes", this.novoDoador.getNome());

		assertEquals("Pedro Henrique", this.novoReceptor.getNome());
	}

	@Test
	void testGetItens() {
		this.novoDoador.adicionaItem(1, "cadeira de rodas", 1, "manual, adulto");
		this.novoDoador.adicionaItem(2, "curso sobre cuidados com o bebê", 3, "maternidade, duracao 12h");
		assertEquals(
				"{1=1 - cadeira de rodas, tags: [manual,  adulto], quantidade: 1, 2=2 - curso sobre cuidados com o bebê, tags: [maternidade,  duracao 12h], quantidade: 3}",
				this.novoDoador.getItens().toString());

	}

	@Test
	void testGetItemInt() {
		int id = this.novoDoador.adicionaItem(1, "cadeira de rodas", 1, "manual, adulto");
		assertEquals(id + " - cadeira de rodas, tags: [manual,  adulto], quantidade: 1",
				this.novoDoador.getItem(1).toString());
	}

	@Test
	void testAtualizaUsuario() {
		this.novoDoador.atualizaUsuario("Raquel", null, "0000 - 0000");
		assertEquals("Raquel/01234567899, raquel@computacao.ufcg.edu.br, 0000 - 0000, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.atualizaUsuario("Pedro", null, "");
		assertEquals("Pedro/91724529000103, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());

	}

	@Test
	void testToString() {
		assertEquals("Raquel Lopes/01234567899, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		assertEquals(
				"Pedro Henrique/91724529000103, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());

	}

	@Test
	void testAdicionaItem() {
		this.novoDoador.adicionaItem(1, "cadeira de rodas", 1, "manual, adulto");
		this.novoDoador.adicionaItem(2, "curso sobre cuidados com o bebê", 3, "maternidade, duracao 12h");
		assertEquals(
				"{1=1 - cadeira de rodas, tags: [manual,  adulto], quantidade: 1, 2=2 - curso sobre cuidados com o bebê, tags: [maternidade,  duracao 12h], quantidade: 3}",
				this.novoDoador.getItens().toString());

	}

	@Test
	void testExisteItemInt() {
		assertEquals(false, this.novoDoador.existeItem(1));
		this.novoDoador.adicionaItem(1, "cadeira de rodas", 1, "manual, adulto");
		assertEquals(true, this.novoDoador.existeItem(1));

	}

	@Test
	void testExisteItemItem() {
		assertEquals(false, this.novoDoador.checaItem(itemUm));
		this.novoDoador.adicionaItem(1, "cadeira de rodas", 1, "manual, adulto");
		assertEquals(true, this.novoDoador.checaItem(itemUm));
	}

}
