package ferramentas;

import internas.Item;

/**
 * Interface criada para implementar os metodos para comparar Itens por quantidade e descrição
 * @author Nathan Fernandes
 */
public interface Comparator {
    int compare(Item atual, Item outro);
}
