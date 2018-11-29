package ferramentas;

import internas.Item;

/**
 * Comparador para ser usado em Item.
 * Compara um item com outro pela quantidade, se forem iguais, compara pela descricao.
 * @author NAthan Fernandes
 */
public class QuantidadeComparator implements Comparator{

    public int compare(Item it, Item outro){
        if(it.getQuantidade() == outro.getQuantidade()){
            return it.getDescricao().compareTo(outro.getDescricao());
        }
        if(it.getQuantidade() < outro.getQuantidade()) return -1;
        return 1;
    }
}
