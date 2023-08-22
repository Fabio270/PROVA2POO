import Data.ItemVendaSQliteDAO;
import Data.ProdutoSQliteDAO;
import Data.VendaSQliteDAO;
import Model.ItemVenda;
import Model.Produto;
import Model.Venda;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Produto produto1 = new Produto(1,"bola",30,200);
        ProdutoSQliteDAO produtodao = new ProdutoSQliteDAO();


        //produtodao.salvar(produto1);
        List<ItemVenda> listavenda = new ArrayList<>();
        Venda venda1 = new Venda(1,"12/12/2012", listavenda);
        VendaSQliteDAO vendadao = new VendaSQliteDAO();

        ItemVenda itemVenda1 = new ItemVenda(1,10, produto1, venda1);
        ItemVendaSQliteDAO itemvendadao = new ItemVendaSQliteDAO();

        produto1.mostrarDados();
        venda1.mostrarDados();
        itemVenda1.mostrarDados();

        //produtodao.salvar(produto1);
        //vendadao.salvar(venda1);
        //itemvendadao.salvar(itemVenda1);

        Produto testebuscarproduto = produtodao.buscar(1);
        Venda testebuscarvenda = vendadao.buscar(1);
        ItemVenda testebuscaritemvenda = itemvendadao.buscar(1);

        testebuscarproduto.mostrarDados();
        testebuscarvenda.mostrarDados();
        testebuscaritemvenda.mostrarDados();

        List<Produto> testelistaprodutos = produtodao.buscarTodos();

        for (Produto p: testelistaprodutos){
            p.mostrarDados();
        }

        List<Venda> testelistavenda = vendadao.buscarTodos();

        for (Venda v: testelistavenda){
            v.mostrarDados();
        }

        List<ItemVenda> testelistaitemvenda = itemvendadao.buscarTodos();

        for (ItemVenda v: testelistaitemvenda){
            v.mostrarDados();
        }


    }
}