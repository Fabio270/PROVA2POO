package Data;

import Model.ItemVenda;
import Model.Venda;

import java.util.List;

public interface ItemVendaDAO extends DAO<ItemVenda>{
    void salvar(ItemVenda itemvenda);
    void atualizar(ItemVenda itemvenda);
    void apagar(ItemVenda itemvenda);
    ItemVenda buscar(int id);
    List<ItemVenda> buscarTodos();

    List<ItemVenda> buscarItensVenda(Venda venda);
}
