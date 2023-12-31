package Data;

import Model.ItemVenda;
import Model.Produto;
import Model.Venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaSQliteDAO implements VendaDAO{
    @Override
    public void salvar(Venda venda) {
        String sql = "INSERT INTO venda (idVenda, dataVenda, valorTotalVenda) VALUES (?,?,?)";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, venda.getIdVenda());
            stmt.setString(2, venda.getDataVenda());
            stmt.setDouble(3, venda.getValorTotalVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Venda venda) {
        String sql = "UPDATE venda SET dataVenda=?, valorTotalVenda=? WHERE idVenda=?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setString(1, venda.getDataVenda());
            stmt.setDouble(2, venda.getValorTotalVenda());
            stmt.setInt(3, venda.getIdVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Venda venda) {
        String sql = "DELETE FROM venda WHERE idVenda=?";
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, venda.getIdVenda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venda buscar(int id) {
        String sql = "SELECT * FROM venda WHERE idVenda=?";
        Venda venda = null;

        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                List<ItemVenda> listaItensVenda = new ArrayList<>();
                venda = new Venda(rs.getInt("idVenda"),
                        rs.getString("dataVenda"),
                        listaItensVenda);
                listaItensVenda = new ItemVendaSQliteDAO().buscarItensVenda(venda);
                venda.setListaItemVenda(listaItensVenda);
                venda.calcularValorTotalVenda();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  venda;
    }

    @Override
    public List<Venda> buscarTodos() {
        String sql = "SELECT * FROM venda";
        List<Venda> listaVendas = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                List<ItemVenda> listaItensVenda = new ArrayList<>();
                Venda venda = new Venda(rs.getInt("idVenda"),
                        rs.getString("dataVenda"),
                        listaItensVenda);
                listaItensVenda = new ItemVendaSQliteDAO().buscarItensVenda(venda);
                venda.setListaItemVenda(listaItensVenda);
                venda.calcularValorTotalVenda();
                listaVendas.add(venda);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listaVendas;
    }
}
