package Data;

import Model.ItemVenda;
import Model.Produto;
import Model.Venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaSQliteDAO implements ItemVendaDAO{
    @Override
    public void salvar(ItemVenda itemvenda) {
        String sql = "INSERT INTO itemvenda (idItemVenda, idProduto, idVenda, quantidadeVendida, valorTotal) VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, itemvenda.getIdItemVenda());
            stmt.setInt(2, itemvenda.getProduto().getIdProduto());
            stmt.setDouble(3, itemvenda.getVenda().getIdVenda());
            stmt.setInt(4, itemvenda.getQuantidadeVendida());
            stmt.setDouble(5,itemvenda.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(ItemVenda itemvenda) {
        String sql = "UPDATE itemvenda SET quantidadeVendida=?, valorTotal=? WHERE idItemVenda=?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, itemvenda.getQuantidadeVendida());
            stmt.setDouble(2,itemvenda.getValorTotal());
            stmt.setInt(3, itemvenda.getIdItemVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(ItemVenda itemvenda) {
        String sql= "DELETE FROM itemvenda WHERE idItemVenda=?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, itemvenda.getIdItemVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemVenda buscar(int id) {
        String sql = "SELECT * FROM itemvenda WHERE idItemVenda=?";
        ItemVenda itemVenda = null;
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Produto produto = new ProdutoSQliteDAO().buscar(rs.getInt("idProduto"));
                Venda venda = new VendaSQliteDAO().buscar(rs.getInt("idVenda"));
                itemVenda = new ItemVenda(rs.getInt("idItemVenda"),
                        rs.getInt("quantidadeVendida"),
                        produto,
                        venda);
                itemVenda.calcularValorTotal();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  itemVenda;
    }

    @Override
    public List<ItemVenda> buscarTodos() {
        String sql = "SELECT * FROM itemvenda";
        List<ItemVenda> listaItemVenda = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Produto produto = new ProdutoSQliteDAO().buscar(rs.getInt("idProduto"));
                Venda venda = new VendaSQliteDAO().buscar(rs.getInt("idVenda"));
                ItemVenda itemVenda = new ItemVenda(rs.getInt("idItemVenda"),
                        rs.getInt("quantidadeVendida"),
                        produto,
                        venda);
                itemVenda.calcularValorTotal();
                listaItemVenda.add(itemVenda);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listaItemVenda;
    }

    @Override
    public List<ItemVenda> buscarItensVenda(Venda venda) {
        String sql = "SELECT * FROM itemvenda WHERE idVenda=?";
        List<ItemVenda> listaItemVenda = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1,venda.getIdVenda());
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Produto produto = new ProdutoSQliteDAO().buscar(rs.getInt("idProduto"));
                ItemVenda itemVenda = new ItemVenda(rs.getInt("idItemVenda"),
                        rs.getInt("quantidadeVendida"),
                        produto,
                        venda);
                itemVenda.calcularValorTotal();
                listaItemVenda.add(itemVenda);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listaItemVenda;
    }
}
