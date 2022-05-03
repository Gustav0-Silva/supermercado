package supermercado.com.letscode;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    private static int linhas = 2;
    private static int colunas = 9;
    private static Object[][] dadosProdutos = new Object[linhas][colunas];
    private static String tipoLista;

    private static Tipo tipo =  null;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean ciclo = true;
        int menu = 0;

        while (ciclo){
            menu = lerMenu(sc);

            if (menu == 1){
                solicitarDados(sc);
                System.out.println("Operação realizada com sucesso!");

                //temporário para visualização de resultados
                System.out.println();
                for (int i = 0; i < dadosProdutos.length; i++) {
                    for (int j = 0; j < dadosProdutos[i].length; j++) {
                        System.out.print(dadosProdutos[i][j]+ " ");
                    }
                    System.out.println();
                    //temporário para visualização de resultados

                }
            } else if (menu == 2) {
                imprimirEstoque();
                System.out.println();
            } else if (menu == 3) {
                listarTipo(sc);
            } else if (menu == 4) {
                ciclo = false;
            }else if (menu == 0) {
                System.out.println("Opção inválida, tente novamente");
                System.out.println();
            }

        }
    }


    public static int lerMenu (Scanner sc){
        int menu = 0;

        System.out.println("Por favor, digite o que deseja fazer:");
        System.out.println("1 - Cadastro/Compra de produtos");
        System.out.println("2 - Imprimir estoque");
        System.out.println("3 - Listar produtos pelo tipo");
        System.out.println("4 - Sair do Sistema");

        menu = sc.nextInt();

        if (menu == 1){
            return 1;
        } else if (menu == 2) {
            return 2;
        } else if (menu == 3) {
            return 3;
        } else if (menu == 4) {
            return 4 ;
        }else {
            return 0;
        }

    }

    public static void listarTipo(Scanner sc){
        boolean valid = false;
        int index;
        int flag=0;

        do{
            System.out.println("Por favor, digite por qual tipo deseja filtrar os produtos: ");
            System.out.println("1 - Alimentos");
            System.out.println("2 - Bebidas");
            System.out.println("3 - Higiene");
            System.out.print("Tipo de produto: ");
            index = sc.nextInt();


            if (index == 1 || index == 2 || index == 3){
                valid = true;
            }else {
                System.out.println("Por favor, digite um valor correto");
            }

        }while(valid != true);

        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos [i][0] == null){
                return;
            }
            if (((int) dadosProdutos[i][0]) == index){
                System.out.println("Código de tipo: " + dadosProdutos[i][0]);
                System.out.println("Marca do produto: " + dadosProdutos[i][1]);
                System.out.println("Código identificador: " + dadosProdutos[i][2]);
                System.out.println("Nome do produto: " + dadosProdutos[i][3]);
                System.out.println("Preço de custo: " + dadosProdutos[i][4]);
                System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
                System.out.println("Data da última compra: " + dadosProdutos[i][6]);
                System.out.println("Preço de venda: " + dadosProdutos[i][7]);
                System.out.println("Quantidade em estoque: " + dadosProdutos[i][8]);
                System.out.println();
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("Não existem itens deste tipo no estoque");
        }

    }
    public static void imprimirEstoque(){
        if(dadosProdutos[0][0] == null){
            System.out.println("O estoque ainda não ser impresso,pois nada foi adicionado");
            return;
        }

        System.out.println("Lista de itens cadastrados");
        System.out.println();

        for (int i = 0; i < dadosProdutos.length; i++) {

            if (dadosProdutos[i][0] == null){
                return;
            }

            System.out.println("Código de tipo: " + dadosProdutos[i][0]);
            System.out.println("Marca do produto: " + dadosProdutos[i][1]);
            System.out.println("Código identificador: " + dadosProdutos[i][2]);
            System.out.println("Nome do produto: " + dadosProdutos[i][3]);
            System.out.println("Preço de custo: " + dadosProdutos[i][4]);
            System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
            System.out.println("Data da última compra: " + dadosProdutos[i][6]);
            System.out.println("Preço de venda: " + dadosProdutos[i][7]);
            System.out.println("Quantidade em estoque: " + dadosProdutos[i][8]);
            System.out.println();
        }

        return;

    }
    public static void solicitarDados(Scanner sc){
        int index;
        String marca;
        String identificador;
        String nome;
        double precoCusto;
        int quant;
        LocalDateTime dataCompra;
        double precoFinal;
        int estoque = 0;
        boolean valid=false;

        //valida se foi digitado corretamente itens pertencentes ao enum
        do{
            System.out.println("Por favor, digite o número correspondente: ");
            System.out.println("1 - Alimentos");
            System.out.println("2 - Bebidas");
            System.out.println("3 - Higiene");
            System.out.print("Tipo de produto: ");
            index = sc.nextInt();


            if (index == 1 || index == 2 || index == 3){
                valid = true;
            }else {
                System.out.println("Por favor, digite um valor correto");
            }

        }while(valid != true);

        valid = false;

        //valida se foi digitada uma marca diferente de null ou String vazia
        do{
            System.out.print("Marca: ");
            marca = sc.next();

            if (marca.equals(null) || marca.equals(" ")){
                System.out.println("Por favor, digite uma marca válida");
            }else {
                valid = true;
            }
        }while (valid != true);

        valid = false;

        //valida se foi digitado um identificador diferente de null ou String vazia
        do{
            System.out.print("Identificador: ");
            identificador = sc.next();

            if (identificador.equals(null) || identificador.equals(" ")){
                System.out.println("Por favor, digite um identificador válido");
            }else {
                valid = true;
            }
        }while (valid != true);

        valid = false;

        //valida se foi digitado um nome diferente de null ou String vazia
        do{
            System.out.print("Nome do Produto: ");
            nome = sc.next();

            if (nome.equals(null) || nome.equals(" ")){
                System.out.println("Por favor, digite uma marca válida");
            }else {
                valid = true;
            }
        }while (valid != true);

        valid = false;

        //valida se foi digitado um preco de custo maior do que zero
        do{
            System.out.print("Preço de custo: R$ ");
            precoCusto = sc.nextDouble();


            if (precoCusto <= 0){
                System.out.println("Por favor, digite um valor válido");
            }else {
                valid = true;
            }
        }while (valid != true);

        valid =false;

        //valida se foi digitada uma quantidade positiva
        do{
            System.out.print("Quantidade comprada/em estoque: ");
            quant = sc.nextInt();

            if (quant < 0){
                System.out.println("Por favor, digite um valor positivo ou 0 (zero)");
            }else {
                valid = true;
            }
        }while (valid != true);

        valid = false;

        dataCompra = LocalDateTime.now();
        precoFinal = calculaPrecoFinal (index, precoCusto);

        valid = matrizCheia();

        if (valid == true){
            aumentarMatriz();
        }
        
        valid = false;
        valid = produtoExistente(identificador);

        //passa para dentro da matriz
        if (valid == true){
            //método de aumentar estoque
            for (int i = 0; i < dadosProdutos.length; i++) {
                if (dadosProdutos[i][2].equals(identificador)){
                    dadosProdutos[i][0] = index;
                    dadosProdutos[i][1] = marca;
                    dadosProdutos[i][2] = identificador;
                    dadosProdutos[i][3] = nome;
                    dadosProdutos[i][4] = precoCusto;
                    dadosProdutos[i][5] = quant;
                    dadosProdutos[i][6] = dataCompra;
                    dadosProdutos[i][7] = precoFinal;
                    dadosProdutos[i][8] = ((Integer) dadosProdutos[i][8]) + quant;
                    return;
                }
            }

        }else{
            //método de criar novo item
            for (int i = 0; i < dadosProdutos.length; i++) {
                if (dadosProdutos[i][0] == null){
                    dadosProdutos[i][0] = index;
                    dadosProdutos[i][1] = marca;
                    dadosProdutos[i][2] = identificador;
                    dadosProdutos[i][3] = nome;
                    dadosProdutos[i][4] = precoCusto;
                    dadosProdutos[i][5] = quant;
                    dadosProdutos[i][6] = dataCompra;
                    dadosProdutos[i][7] = precoFinal;
                    dadosProdutos[i][8] = quant;
                    return;
                }
            }
        }
              


    }


    public static boolean matrizCheia(){

        for (int i = 0; i < dadosProdutos.length; i++) {
                if (dadosProdutos[i][0] == null){
                    return false;
                }
        }

        return true;

    }

    public static void aumentarMatriz(){
        Object [][] newMatriz = new Object[dadosProdutos.length*2][colunas];

        for (int i = 0; i < dadosProdutos.length; i++) {
            for (int j = 0; j < linhas; j++) {
                newMatriz[i][j] = dadosProdutos [i][j];
            }
        }
        dadosProdutos = newMatriz;
    }
    
    public static boolean produtoExistente(String identificador){
        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos[i][2] == null){
                return false;
            }
            else if ( dadosProdutos[i][2].equals(identificador)){
                return true;
            }
        }
        return false;
    }

    public static double calculaPrecoFinal(int index, double preco){

        if (index == 1){
            return preco * Tipo.ALIMENTOS.getMarkup();

        } else if (index == 2) {
            return preco * Tipo.BEBIDAS.getMarkup();

        } else if (index == 3) {
            return preco * Tipo.HIGIENE.getMarkup();
        }else {
            return 0;
        }

    }


}