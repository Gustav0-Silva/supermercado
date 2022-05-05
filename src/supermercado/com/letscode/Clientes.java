package supermercado.com.letscode;

public enum Clientes {
    PF(0.0),
    PJ(0.95),
    VIP(0.85);

    Clientes (double desconto){
        this.desconto = desconto;
    }

    private double desconto;

    public double getDesconto() {
        return desconto;
    }

    public static double calcularDesconto(double valorCompra, Clientes desconto){
        return valorCompra * desconto.getDesconto();
    }
}
