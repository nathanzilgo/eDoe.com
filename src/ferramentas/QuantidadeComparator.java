package ferramentas;

import internas.Item;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparador para ser usado em Item.
 * Compara um item com outro pela quantidade, se forem iguais, compara pela descricao.
 * @author NAthan Fernandes
 */
public class QuantidadeComparator implements Comparator<Item>, Serializable {

    private static final long serialVersionUID = 1L;

    public int compare(Item it, Item outro){
        if(it.getQuantidade() == outro.getQuantidade()){
            return it.getDescricao().compareTo(outro.getDescricao());
        }
        return outro.getQuantidade() - it.getQuantidade();
    }
}
