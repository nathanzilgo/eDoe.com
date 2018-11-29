package ferramentas;

import internas.Item;

/**
 * Compara 2 itens de acordo com apenas a descricao.
 * @author Nathan Fernandes
 */
public class DescricaoComparator implements Comparator {
    public int compare(Item atual, Item outro){
        return atual.getDescricao().compareTo(outro.getDescricao());
    }
}
