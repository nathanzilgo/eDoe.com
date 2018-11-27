package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import controllers.Controller;
import internas.Usuario;

class ControllerTest {

	private Controller controller = new Controller();
	private LinkedHashMap<String, Usuario> mapaUsuarios;

	@Test
	void testController() {
		fail("Not yet implemented");
	}

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
	void testPesquisaUsuarioPorId() {
		fail("Not yet implemented");
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveUsuario() {
		fail("Not yet implemented");
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
