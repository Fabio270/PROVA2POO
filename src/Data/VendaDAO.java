package Data;

import Model.ItemVenda;
import Model.Venda;

import java.util.List;

public interface VendaDAO extends DAO<Venda>{
    void salvar(Venda venda);
    void atualizar(Venda venda);
    void apagar(Venda venda);
    Venda buscar(int id);
    List<Venda> buscarTodos();
}
