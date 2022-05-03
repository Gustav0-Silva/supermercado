package supermercado.com.letscode;

public enum Tipo {
        ALIMENTOS (1.2, 1),
        BEBIDAS (2.3, 2),
        HIGIENE(1.5, 3);

        Tipo(double markup, int index){
                this.markup = markup;
                this.index = index;
        }

        private double markup;
        private int index;

        public int getIndex() {
                return index;
        }

        public double getMarkup() {
                return markup;
        }
}
