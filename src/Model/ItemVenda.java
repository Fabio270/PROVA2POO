package Model;

public class ItemVenda implements MostrarDados{
    private int idItemVenda;
    private int quantidadeVendida;
    private double valorTotal;
    private Produto produto;
    private Venda venda;
    public ItemVenda(int idItemVenda, int quantidadeVendida, Produto produto, Venda venda) {
        this.idItemVenda = idItemVenda;
        this.quantidadeVendida = quantidadeVendida;
        this.produto = produto;
        this.venda = venda;
        this.calcularValorTotal();
        this.produto.atualizarEstoque(this.produto.getQuantidadeEstoque()-this.quantidadeVendida);
    }

    public void calcularValorTotal(){
        this.valorTotal = this.getProduto().getPrecoUnitario() * this.getQuantidadeVendida();
    }
    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public void mostrarDados() {
        System.out.println("\nID ITEM-VENDA: " + this.getIdItemVenda()
        +"\nQuantidade Vendida: " + this.getQuantidadeVendida()
        +"\nValor Total: " + this.getValorTotal());
    }
}
