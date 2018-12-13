package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import internas.Item;
import internas.Usuario;

class ItemTest {
	Usuario novoUsuario = new Usuario("1111111", "Raquel Lopes", "raquel_lopes@ccc.ufcg.edu.br", "3333 - 3333",
			"pessoa_fisica", false);
	Item i1 = new Item(1, "macarrao bom", 5, "macarrao,almondegas", novoUsuario);
	Item i2 = new Item(2, "sapatos novos", 2, "adidas,novo", novoUsuario);
	Item i3 = new Item(3, "macarrao bom", 3, "macarrao,almondegas", novoUsuario);

	@Test
	void testHashCode() {
		assertEquals(i1.hashCode(), i3.hashCode());
	}

	@Test
	void testItem() {
		assertEquals("macarrao bom", i1.getDescricao());
		assertEquals(5, i1.getQuantidade());
		assertEquals("macarrao,almondegas", i1.getTags());
	}

	@Test
	void testEqualsObject() {
		assertEquals(i1, i3);
		assertNotEquals(i2, i3);
	}

	@Test
	void testToString() {
		i1.setId(2);
		i2.setId(3);

		assertEquals("2 - macarrao bom, tags: [macarrao, almondegas], quantidade: 5", i1.toString());
		assertEquals("3 - sapatos novos, tags: [adidas, novo], quantidade: 2", i2.toString());
	}

	@Test
	void testTagsToString() {
		assertEquals("macarrao, almondegas", i1.tagsToString());
	}

}