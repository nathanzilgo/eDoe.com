package projeto_lp2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import internas.Item;

class ItemTest {
	Item i1 = new Item("macarrao bom", 5, "macarrao,almondegas");
	Item i2 = new Item("sapatos novos", 2, "adidas,novo");
	Item i3 = new Item("macarrao bom", 3, "macarrao,almondegas");
	
	@Test
	void testHashCode() {
		assertEquals(i1.hashCode(),i3.hashCode());
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
