package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import internas.Doador;
import internas.Receptor;
import internas.Usuario;

class UsuarioTest {
	private Usuario novoUsuario;

	@Test
	void testSetNome() {
		// Cadastrando doador
		this.novoUsuario = new Doador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		// Alterando nome do doador
		this.novoUsuario.setNome("Raquel");
		// Retorna representacao textual do doador com nome atualizado
		assertEquals("Raquel/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoUsuario.toString());

		// Cadastrando novo receptor
		this.novoUsuario = new Receptor("91724529000103", "Pedro Henrique", "pedrohenriquedavimoura_@keffin.com.br",
				"(27) 98460-3045", "ORGAO_PUBLICO_MUNICIPAL");
		// Alterando nome do receptor
		this.novoUsuario.setNome("Pedro");
		// Retorna representacao textual do receptor com nome atualizado
		assertEquals(
				"Pedro/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoUsuario.toString());
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetTelefone() {
		fail("Not yet implemented");
	}

	@Test
	void testSetClasse() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNome() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItens() {
		fail("Not yet implemented");
	}

	@Test
	void testSetItens() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemInt() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		// Cadastrando doador com cpf no id
		this.novoUsuario = new Doador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		// Retorna representacao textual do doador cadastrado com cpf no id
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				this.novoUsuario.toString());

		// Cadastrando doador com cnpj no id
		this.novoUsuario = new Doador("01234567000189", "Lar da garota", "lardagarota@gmail.com", "(83) 98762-4554",
				"ong");
		// Retorna representacao textual do doador cadastrado com cnpj no id
		assertEquals("Lar da garota/01.234.567/0001-89, lardagarota@gmail.com, (83) 98762-4554, status: doador",
				this.novoUsuario.toString());

		// Cadastrando novo receptor com cpf no id
		this.novoUsuario = new Receptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br",
				"(31) 99776-7434", "PESSOA_FISICA");
		// Retorna representacao textual do receptor cadastrado com cpf no id
		assertEquals(
				"Murilo Luiz Brito/844.737.120-44, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor",
				this.novoUsuario.toString());

		// Cadastrando novo receptor com cnpj no id
		this.novoUsuario = new Receptor("91724529000103", "Pedro Henrique", "pedrohenriquedavimoura_@keffin.com.br",
				"(27) 98460-3045", "ORGAO_PUBLICO_MUNICIPAL");
		// Retorna representacao textual do receptor cadastrado com cnpj no id
		assertEquals(
				"Pedro Henrique/91.724.529/0001-03, pedrohenriquedavimoura_@keffin.com.br, (27) 98460-3045, status: receptor",
				this.novoUsuario.toString());

	}

	@Test
	void testAdicionaItem() {
		fail("Not yet implemented");
	}

	@Test
	void testExisteItemInt() {
		fail("Not yet implemented");
	}

	@Test
	void testExisteItemItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemItem() {
		fail("Not yet implemented");
	}

}
