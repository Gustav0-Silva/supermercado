package supermercado.com.letscode;

public enum Tipo {
        ALIMENTOS (1.2),
        BEBIDAS (2.3),
        HIGIENE(1.5);

        Tipo(double markup){
                this.markup = markup;
        }

        private double markup;

        public static double calcularVenda(Tipo tipo, double custo) {
                return tipo.getMarkup() * custo;
        }
        public double getMarkup() {
                return markup;
        }
}
