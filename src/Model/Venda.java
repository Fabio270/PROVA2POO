package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda implements MostrarDados{
    private int idVenda;
    private String dataVenda;
    private double valorTotalVenda;

    private List<ItemVenda> listaItemVenda = new ArrayList<>();

    public Venda(int idVenda, String dataVenda, List<ItemVenda> listaItemVenda) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.listaItemVenda = listaItemVenda;
        this.calcularValorTotalVenda();
    }

    public void incluirItemVenda(ItemVenda iv){
        this.listaItemVenda.add(iv);
        this.calcularValorTotalVenda();
    }
    public void removerItemVenda(ItemVenda iv){
        this.listaItemVenda.remove(iv);
        this.calcularValorTotalVenda();
    }

    public void calcularValorTotalVenda(){
        this.valorTotalVenda = 0;
        for (ItemVenda iv: this.listaItemVenda){
            this.valorTotalVenda += iv.getValorTotal();
        }
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(double valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public List<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }

    @Override
    public void mostrarDados() {
        System.out.println("\nID da venda: " + this.getIdVenda()
        +"\nData da venda: " + this.getDataVenda()
        +"\nValor total venda: " + this.getValorTotalVenda());
        System.out.println("\nlISTA DE ITENS:\n");
        for (ItemVenda iv: this.listaItemVenda){
            iv.mostrarDados();
        }
    }
}
