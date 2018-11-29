package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import internas.Doador;
import internas.Item;
import internas.Receptor;
import internas.Usuario;

class UsuarioTest {

	private Usuario novoDoador = new Doador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br",
			"(83) 9990-9999", "pessoa_fisica");
	private Usuario novoReceptor = new Receptor("91724529000103", "Pedro Henrique",
			"pedrohenriquedavimoura_@keffin.com.br", "(27) 98460-3045", "ORGAO_PUBLICO_MUNICIPAL");
	private Item itemUm = new Item("cadeira de rodas", 1, "manual, adulto");
	private Item itemDois = new Item("curso sobre cuidados com o bebê", 3, "maternidade, duracao 12h");

	/**@Test
	void testSetNome() {
		this.novoDoador.setNome("Raquel");
		assertEquals("Raquel/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setNome("Pedro");
		assertEquals(
				"Pedro/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testSetEmail() {
		this.novoDoador.setEmail("raquel@ccc.ufcg.edu.br");
		assertEquals("Raquel Lopes/012.345.678-99, raquel@ccc.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setEmail("pedro@gmail.com");
		assertEquals("Pedro Henrique/91.724.529/0001-03, pedro@gmail.com, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testSetTelefone() {
		this.novoDoador.setTelefone("0000 - 0000");
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, 0000 - 0000, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.setTelefone("(27) 0000 - 0000");
		assertEquals(
				"Pedro Henrique/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 0000 - 0000, status: receptor",
				this.novoReceptor.toString());
	}

	@Test
	void testGetNome() {
		assertEquals("Raquel Lopes", this.novoDoador.getNome());

		assertEquals("Pedro Henrique", this.novoReceptor.getNome());
	}

	@Test
	void testGetItens() {
		this.novoDoador.adicionaItem(itemUm);
		this.novoDoador.adicionaItem(itemDois);
		assertEquals(
				"{1=1 - cadeira de rodas, tags: [manual,  adulto], quantidade: 1, 2=2 - curso sobre cuidados com o bebê, tags: [maternidade,  duracao 12h], quantidade: 3}",
				this.novoDoador.getItens().toString());
	}

	@Test
	void testGetItemInt() {
		int id = this.novoDoador.adicionaItem(itemUm);
		assertEquals(id + " - cadeira de rodas, tags: [manual,  adulto], quantidade: 1",
				this.novoDoador.getItem(1).toString());
	}

	@Test
	void testAtualizaUsuario() {
		this.novoDoador.atualizaUsuario("Raquel", null, "0000 - 0000");
		assertEquals("Raquel/012.345.678-99, raquel@computacao.ufcg.edu.br, 0000 - 0000, status: doador",
				this.novoDoador.toString());

		this.novoReceptor.atualizaUsuario("Pedro", null, "");
		assertEquals(
				"Pedro/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());

	}

	@Test
	void testToString() {
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoDoador.toString());

		assertEquals(
				"Pedro Henrique/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoReceptor.toString());

	}

	@Test
	void testAdicionaItem() {
		this.novoDoador.adicionaItem(itemUm);
		this.novoDoador.adicionaItem(itemDois);
		assertEquals(
				"{1=1 - cadeira de rodas, tags: [manual,  adulto], quantidade: 1, 2=2 - curso sobre cuidados com o bebê, tags: [maternidade,  duracao 12h], quantidade: 3}",
				this.novoDoador.getItens().toString());
	}

	@Test
	void testExisteItemInt() {
		assertEquals(false, this.novoDoador.existeItem(1));
		this.novoDoador.adicionaItem(itemUm);
		assertEquals(true, this.novoDoador.existeItem(1));

	}

	@Test
	void testExisteItemItem() {
		assertEquals(false, this.novoDoador.existeItem(itemUm));
		this.novoDoador.adicionaItem(itemUm);
		assertEquals(true, this.novoDoador.existeItem(itemUm));
	}
**/
}
