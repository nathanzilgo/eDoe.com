package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import controllers.Controller;
import internas.Usuario;
import junit.framework.Assert;

class ControllerTest {

	private Controller controller = new Controller();
	private LinkedHashMap<String, Usuario> mapaUsuarios;

	@Test
	void testAdicionaDoadorValido() throws Exception {
		// Adiciona pessoa física como doador.
		assertEquals("01234567899", controller.adicionaDoador("01234567899", "Raquel Lopes",
				"raquel@computacao.ufcg.edu.br", "(83) 9990-9999", "pessoa_fisica"));

		// Adiciona igreja como doador.
		assertEquals("12345678900", controller.adicionaDoador("12345678900", "Igreja do Rosario",
				"igrejarosario@gmail.com", "(21) 9888-0021", "igreja"));

		// Adiciona ong como doador.
		assertEquals("01234567000189", controller.adicionaDoador("01234567000189", "Lar da garota",
				"lardagarota@gmail.com", "(83) 98762-4554", "ong"));

		// Adiciona orgao publico municipal como doador.
		assertEquals("01234567888110", controller.adicionaDoador("01234567888110", "Estadual da Prata",
				"estadual_da_prata@gmail.com", "4322 - 5520", "orgao_publico_municipal"));

		// Adiciona orgao publico estadual como doador.
		assertEquals("01234567000110", controller.adicionaDoador("01234567000110", "Uepb", "uepb@edu.com.br",
				"3333 - 3333", "orgao_publico_estadual"));

		// Adiciona orgao publico federal como doador.
		assertEquals("01234567000000", controller.adicionaDoador("01234567000000", "Ufcg", "ufcg@edu.com.br",
				"1234 - 5678", "orgao_publico_federal"));

		// Adiciona associacao como doador.
		assertEquals("12345678000190", controller.adicionaDoador("12345678000190", "Convento Ipuarana",
				"ipuarana@hotmail.com", "(83) 98777-9856", "associacao"));

		// Adiciona sociedade como doador.
		assertEquals("12345678901", controller.adicionaDoador("12345678901", "Sociedade", "sociedade@gmail.com",
				"3321 - 4166", "sociedade"));
	}

	@Test
	void testAdicionaDoadorInvalido() throws Exception {
		// Adiciona doador cpf/cnpj null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador(null, "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"pessoa_fisica");
		});

		// Adiciona doador cpf/cnpj vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("           ", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"pessoa_fisica");
		});

		// Adiciona doador nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", null, "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"pessoa_fisica");
		});

		// Adiciona doador nome vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"pessoa_fisica");
		});

		// Adiciona doador email null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", null, "(83) 9990-9999", "pessoa_fisica");
		});

		// Adiciona doador email vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "       ", "(83) 9990-9999", "pessoa_fisica");
		});

		// Adiciona doador telefone null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", null,
					"pessoa_fisica");
		});

		// Adiciona doador telefone vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "",
					"pessoa_fisica");
		});

		// Adiciona doador classe null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					null);
		});

		// Adiciona doador classe vazia
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"    ");
		});

		// Adiciona doador existente
		controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"pessoa_fisica");
		});

		// Adiciona doador classe inexistente
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
					"nova_pessoa");
		});
	}

	@Test
	void testPesquisaUsuarioPorIdValido() throws Exception {
		// Pesquisa doador ja cadastrado por id
		controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				controller.pesquisaUsuarioPorId("01234567899"));

	}

	@Test
	void testPesquisaUsuarioPorIdInvalido() {
		// Pesquisa usuario por id null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorId(null);
		});

		// Pesquisa usuario por id vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorId("                  ");
		});

		// Pesquisa usuario por id inexistente
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorId("12345678901");
		});
	}

	@Test
	void testPesquisaUsuarioPorNomeValido() throws Exception {
		/*
		 * Pesquisa doador ja cadastrado por nome, unico doador cadastrado com esse nome
		 * no sistema.
		 */
		controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				controller.pesquisaUsuarioPorNome("Raquel Lopes"));

		/*
		 * Pesquisa doador ja cadastrado por nome, varios doadores cadastrados com esse
		 * nome no sistema.
		 */
		controller.adicionaDoador("12345678900", "Igreja", "igrejarosario@gmail.com", "(21) 9888-0021", "igreja");
		controller.adicionaDoador("12345648577", "Igreja", "igrejamatias@gmail.com", "(21) 3353-2221", "igreja");
		controller.adicionaDoador("36271892019", "Igreja", "igrejadejesus@gmail.com", "(21) 9899-0023", "igreja");
		assertEquals(
				"Igreja/123.456.789-00, igrejarosario@gmail.com, (21) 9888-0021, status: doador | Igreja/123.456.485-77, igrejamatias@gmail.com, (21) 3353-2221, status: doador | Igreja/362.718.920-19, igrejadejesus@gmail.com, (21) 9899-0023, status: doador",
				controller.pesquisaUsuarioPorNome("Igreja"));

	}

	@Test
	void testPesquisaUsuarioPorNomeInvalido() {
		// Pesquisa usuario por nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorNome(null);
		});

		// Pesquisa usuario por nome vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorNome("");
		});

		// Pesquisa usuario por nome inexistente
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorNome("Matheus");
		});

	}

	@Test
	void testAtualizaUsuarioValido() throws Exception {
		// Atualiza nome de doador ja cadastrado
		controller.adicionaDoador("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		assertEquals("Raquel A. Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				controller.atualizaUsuario("01234567899", "Raquel A. Lopes", "", ""));

		// Atualiza email de doador ja cadastrado
		assertEquals(
				"Raquel A. Lopes/012.345.678-99, raquel_lopes@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				controller.atualizaUsuario("01234567899", "     ", "raquel_lopes@computacao.ufcg.edu.br", ""));

		// Atualiza telefone de doador ja cadastrado
		assertEquals(
				"Raquel A. Lopes/012.345.678-99, raquel_lopes@computacao.ufcg.edu.br, (83) 3333-3333, status: doador",
				controller.atualizaUsuario("01234567899", "     ", null, "(83) 3333-3333"));

		// Atualiza nome, email e telefone de doador ja cadastrado
		assertEquals("Raquel Lopes/012.345.678-99, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador",
				controller.atualizaUsuario("01234567899", "Raquel Lopes", "raquel@computacao.ufcg.edu.br",
						"(83) 9990-9999"));
	}

	@Test
	void testAtualizaUsuarioInvalido() {
		// Atualiza usuario por id null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.atualizaUsuario(null, "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999");
		});

		// Atualiza usuario por id vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.atualizaUsuario("          ", "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999");
		});

		// Atualiza usuario por id inexistente
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.atualizaUsuario("12345678901", "Raquel Lopes", "raquel@computacao.ufcg.edu.br",
					"(83) 9990-9999");
		});

	}

	@Test
	void testRemoveUsuarioValido() throws Exception {
		// Remove doador ja cadastrado
		String id = "01234567899";
		controller.adicionaDoador(id, "Raquel Lopes", "raquel@computacao.ufcg.edu.br", "(83) 9990-9999",
				"pessoa_fisica");
		controller.removeUsuario(id);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisaUsuarioPorId(id);
		});
	}

	@Test
	void testRemoveUsuarioInvalido() {
		// Remove usuario por id null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.removeUsuario(null);
		});

		// Remove usuario por id vazio
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.removeUsuario("");
		});

		// Remove usuario por id inexistente
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			controller.removeUsuario("01234567899");
		});
	}

	@Test
	void testLerReceptores() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDescritor() {
		fail("Not yet implemented");
	}

	@Test
	void testAdicionaDescritor() {
		fail("Not yet implemented");
	}

	@Test
	void testAdicionaItemParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testExibeItem() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveItemParaDoacao() {
		fail("Not yet implemented");
	}

}
