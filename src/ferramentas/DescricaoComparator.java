package ferramentas;

import internas.Item;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 itens de acordo com apenas a descricao.
 * @author Nathan Fernandes
 */
public class DescricaoComparator implements Comparator<Item>, Serializable {
    private static final long serialVersionUID = 1L;
    public int compare(Item atual, Item outro){
        return atual.getDescricao().compareTo(outro.getDescricao());
    }
}
