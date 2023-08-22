package Data;

import Model.ItemVenda;
import Model.Produto;

import java.util.List;

public interface ProdutoDAO extends DAO<Produto>{
    void salvar(Produto produto);
    void atualizar(Produto produto);
    void apagar(Produto produto);
    Produto buscar(int id);
    List<Produto> buscarTodos();
}
