package supermercado.com.letscode;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static int linhas = 2;
    private static int colunas = 9;
    private static Object[][] dadosProdutos = new Object[linhas][colunas];

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
                pesquisaIdentificador(sc);
            }else if (menu == 5) {
                pesquisaNome(sc);
            } else if (menu == 6) {
                //modo de vendas
            } else if (menu == 7) {
                //relatório de todas as vendas
            } else if (menu == 8) {
                //relatório de vendas consolidado pelo cpf
            } else if (menu == 9 ) {
                ciclo = false;
            }


        }
    }


    public static int lerMenu (Scanner sc){
        String menu = "0";

        System.out.println("-----------------------------------------------");
        System.out.println("Por favor, digite o que deseja fazer:");
        System.out.println("1 - Cadastro/Compra de produtos");
        System.out.println("2 - Imprimir estoque");
        System.out.println("3 - Listar produtos pelo tipo");
        System.out.println("4 - Pesquisar um produto pelo código");
        System.out.println("5 - Pesquisar um produto pelo nome");
        System.out.println("6 - Venda de produtos");
        System.out.println("7 - Relatório de todas as vendas");
        System.out.println("8 - Relatório de vendas consolidado pelo CPF");
        System.out.println("9 - Sair do Sistema");
        System.out.println("-----------------------------------------------");

        menu = sc.nextLine();

        if (menu.equals("1")){
            return 1;
        } else if (menu.equals("2")) {
            return 2;
        } else if (menu.equals("3")) {
            return 3;
        } else if (menu.equals("4")) {
            return 4 ;
        }else if (menu.equals("5")){
            return 5;
        } else if (menu.equals("6")) {
            return 6;
        } else if (menu.equals("7")) {
            return 7;
        } else if (menu.equals("8")) {
            return 8;
        } else if (menu.equals("9")) {
            return 9;
        }else {
            return 0;
        }

    }

    public static void listarTipo(Scanner sc){
        String index;
        Tipo markup = null;
        int flag=0;

        do{
            System.out.println("Por favor, digite por qual tipo deseja filtrar os produtos: ");
            System.out.println("1 - Alimentos");
            System.out.println("2 - Bebidas");
            System.out.println("3 - Higiene");
            System.out.print("Tipo de produto: ");
            index = sc.nextLine();

            if (index.equals("1")){
                markup = Tipo.ALIMENTOS;
            } else if (index.equals("2")) {
                markup = Tipo.BEBIDAS;
            } else if (index.equals("3")) {
                markup = Tipo.HIGIENE;
            }else {

                System.out.println("Por favor, digite um valor correto");
                System.out.println();
            }

        }while(markup == null);

        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos [i][0] == null){
                return;
            }
            if (((Tipo) dadosProdutos[i][0]).equals(markup)){
                LocalDateTime dataHoraEntrada = (LocalDateTime) dadosProdutos[i][6];
                String dataFormatada = dataHoraEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

                System.out.println("Código de tipo: " + dadosProdutos[i][0]);
                System.out.println("Marca do produto: " + dadosProdutos[i][1]);
                System.out.println("Código identificador: " + dadosProdutos[i][2]);
                System.out.println("Nome do produto: " + dadosProdutos[i][3]);
                System.out.println("Preço de custo: " + dadosProdutos[i][4]);
                System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
                System.out.printf("Data de compra: %s \n", dataFormatada);
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

            LocalDateTime dataHoraEntrada = (LocalDateTime) dadosProdutos[i][6];
            String dataFormatada = dataHoraEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            System.out.println("Código de tipo: " + dadosProdutos[i][0]);
            System.out.println("Marca do produto: " + dadosProdutos[i][1]);
            System.out.println("Código identificador: " + dadosProdutos[i][2]);
            System.out.println("Nome do produto: " + dadosProdutos[i][3]);
            System.out.println("Preço de custo: " + dadosProdutos[i][4]);
            System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
            System.out.printf("Data de compra: %s \n", dataFormatada);
            System.out.println("Preço de venda: " + dadosProdutos[i][7]);
            System.out.println("Quantidade em estoque: " + dadosProdutos[i][8]);
            System.out.println();
        }

        return;

    }
    public static void solicitarDados(Scanner sc){
        String index;
        Tipo markup = null;
        String marca;
        String identificador;
        String nome;
        double precoCusto;
        int quant;
        LocalDateTime dataCompra;
        double precoFinal;
        boolean valid=false;

        //valida se foi digitado corretamente itens pertencentes ao enum
        do{
            System.out.println("Por favor, digite o número correspondente: ");
            System.out.println("1 - Alimentos");
            System.out.println("2 - Bebidas");
            System.out.println("3 - Higiene");
            System.out.print("Tipo de produto: ");
            index = sc.nextLine();


            if (index.equals("1")){
                markup = Tipo.ALIMENTOS;
            } else if (index.equals("2")) {
                markup = Tipo.BEBIDAS;
            } else if (index.equals("3")) {
                markup = Tipo.HIGIENE;
            }else {

                System.out.println("Por favor, digite um valor correto");
            }
        }while(markup == null);

        valid = false;

        //valida se foi digitada uma marca diferente de null ou String vazia
        do{
            System.out.print("Marca: ");
            marca = sc.nextLine();

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
            identificador = sc.nextLine();

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
            nome = sc.nextLine();

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
        precoFinal = Tipo.calcularVenda (markup, precoCusto);

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
                    dadosProdutos[i][0] = markup;
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
                    dadosProdutos[i][0] = markup;
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

    public static void pesquisaIdentificador(Scanner sc){
        String prod;
        String exit = "0";

            System.out.println("Por favor, digite o identificador do produto que deseja procurar: ");
            prod = sc.nextLine();

            for (int i = 0; i < dadosProdutos.length; i++) {
                if (dadosProdutos[i][2].equals(prod)){

                    LocalDateTime dataHoraEntrada = (LocalDateTime) dadosProdutos[i][6];
                    String dataFormatada = dataHoraEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

                    System.out.println("Código de tipo: " + dadosProdutos[i][0]);
                    System.out.println("Marca do produto: " + dadosProdutos[i][1]);
                    System.out.println("Código identificador: " + dadosProdutos[i][2]);
                    System.out.println("Nome do produto: " + dadosProdutos[i][3]);
                    System.out.println("Preço de custo: " + dadosProdutos[i][4]);
                    System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
                    System.out.printf("Data de compra: %s \n", dataFormatada);
                    System.out.println("Preço de venda: " + dadosProdutos[i][7]);
                    System.out.println("Quantidade em estoque: " + dadosProdutos[i][8]);
                    System.out.println();
                    do {
                        System.out.println("Digite 1 para voltar ao menu principal: ");
                        exit = sc.nextLine();
                    }while (!exit.equals("1"));

                    return;
                }
            }
    }

    public static void pesquisaNome(Scanner sc){


        System.out.println("Por favor, digite a palavra que deseja procurar");
        String pesquisa = sc.nextLine();
        String pesquisaFormatada = pesquisa;
        pesquisaFormatada.toLowerCase();
        String exit = "0";

        for (int i = 0; i < dadosProdutos.length; i++) {
            String nomeFormatado = ((String) dadosProdutos[i][3]);
            nomeFormatado.toLowerCase();

            if (nomeFormatado.contains(pesquisaFormatada)){

                LocalDateTime dataHoraEntrada = (LocalDateTime) dadosProdutos[i][6];
                String dataFormatada = dataHoraEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

                System.out.println("Código de tipo: " + dadosProdutos[i][0]);
                System.out.println("Marca do produto: " + dadosProdutos[i][1]);
                System.out.println("Código identificador: " + dadosProdutos[i][2]);
                System.out.println("Nome do produto: " + dadosProdutos[i][3]);
                System.out.println("Preço de custo: " + dadosProdutos[i][4]);
                System.out.println("Quantidade da última compra: " + dadosProdutos[i][5]);
                System.out.printf("Data de compra: %s \n", dataFormatada);
                System.out.println("Preço de venda: " + dadosProdutos[i][7]);
                System.out.println("Quantidade em estoque: " + dadosProdutos[i][8]);
                System.out.println();
                do {
                    System.out.println("Digite 1 para voltar ao menu principal: ");
                    exit = sc.nextLine();
                }while (!exit.equals("1"));
                return;
            }

        }

    }

    public static void metodoCompra(Scanner sc){
        String inserir;
        boolean validar = false;

        System.out.println("Bem vindo a interface de compra, deseja inserir o CPF? ");
        System.out.println("Digite 1 para sim e 0 para não: ");

        do {
            inserir = sc.nextLine();
            if (inserir.equals("1") || inserir.equals("0")){
               validar = true;
            }else {
                System.out.println("Por favor, digite um número válido");
            }
        }while (!validar);

        String cpf =validarCPF(sc,inserir);


    }

    public static String validarCPF(Scanner sc, String inserir){

        boolean isNumber = false;
        boolean isValid = false;
        String cpf = null;
        do {

            if(inserir.equals("1")) {

                System.out.println("Por favor, digite o seu CPF: ");
                cpf = sc.nextLine();
                char[] cpfArray = cpf.toCharArray();
                if (cpfArray.length <= 11 && cpfArray.length > 9) {
                    isNumber = validaIsString(cpfArray);
                    if (isNumber == true) {
                        isValid = calculoCPF(cpfArray);
                    }
                }
                if (!isValid) {
                    System.out.println("CPF inválido, por favor digite novamente");
                }
            }

            if (inserir.equals("0")) {
                cpf = "00000000191";
                isValid = true;
            }
        }while(!isValid);

        return cpf;

    }

    public static boolean validaIsString (char [] cpfArray) {
        for (int i = 0; i < cpfArray.length; i++) {
            if (cpfArray[i] == '0' || cpfArray[i] == '1' || cpfArray[i] == '2' || cpfArray[i] == '3' ||
                    cpfArray[i] == '4' || cpfArray[i] == '5' || cpfArray[i] == '6' || cpfArray[i] == '7' ||
                    cpfArray[i] == '8' || cpfArray[i] == '9') {
            }else {
                return false;
            }
        }
        return true;
    }

    public static boolean calculoCPF (char [] cpfArray){

        int [] cpfNum = new int[cpfArray.length];

        for (int i = 0; i < cpfArray.length; i++) {
            cpfNum [i] = Integer. parseInt(String.valueOf(cpfArray[i]));
        }

        int soma = 0;
        int primeiroDigito;
        int segundoDigito;

        soma = soma + (cpfNum[0] * 10);
        soma = soma + (cpfNum[1] * 9);
        soma = soma + (cpfNum[2] * 8);
        soma = soma + (cpfNum[3] * 7);
        soma = soma + (cpfNum[4] * 6);
        soma = soma + (cpfNum[5] * 5);
        soma = soma + (cpfNum[6] * 4);
        soma = soma + (cpfNum[7] * 3);
        soma = soma + (cpfNum[8] * 2);

        primeiroDigito = (soma*10)%11;

        if (primeiroDigito == 10){
            primeiroDigito = 0;
        }

        if (primeiroDigito != cpfNum[9]){
            return false;
        }

        soma = 0;

        soma = soma + (cpfNum[0] * 11);
        soma = soma + (cpfNum[1] * 10);
        soma = soma + (cpfNum[2] * 9);
        soma = soma + (cpfNum[3] * 8);
        soma = soma + (cpfNum[4] * 7);
        soma = soma + (cpfNum[5] * 6);
        soma = soma + (cpfNum[6] * 5);
        soma = soma + (cpfNum[7] * 4);
        soma = soma + (cpfNum[8] * 3);
        soma = soma + (cpfNum[9] * 2);

        segundoDigito = (soma*10)%11;

        if (segundoDigito == 10){
            segundoDigito =0;
        }

        if (segundoDigito != cpfNum[10]){
            return false;
        }

        return true;
    }
    }
