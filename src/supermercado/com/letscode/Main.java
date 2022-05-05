package supermercado.com.letscode;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static int linhas = 2;
    private static int colunas = 9;

    private static Object[][] dadosProdutos = new Object[linhas][colunas];
    //A matriz acima refere-se a que guarda os dados dos produtos adicionados/comprados
    private static Object[][] produtosComprados = new Object[1][4];
    //A matriz acima refere-se aos produtos que foram comprados, para serem exibidos nos relatórios
    //analíticos e sintétitcos

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean ciclo = true;
        int menu = 0;

        while (ciclo){
            menu = lerMenu(sc);

            if (menu == 1){
                //Chama a parte de compra/cadastro
                solicitarDados(sc);
                System.out.println("Operação realizada com sucesso!");
            }
            else if (menu == 2) {
                //Chama método para imprimir estoque
                imprimirEstoque();
                System.out.println("Operação realizada com sucesso!");

            } else if (menu == 3) {
                //Chama método para listar por tipo
                listarTipo(sc);
                System.out.println("Operação realizada com sucesso!");

            } else if (menu == 4) {
                //Pesquisa nos itens já cadastrados usando o código identificador como parâmetro
                pesquisaIdentificador(sc);
                System.out.println("Operação realizada com sucesso!");

            }else if (menu == 5) {
                //Pesquisanos itens já cadastrados usando o nome, ou parte do nome como parâmetro
                pesquisaNome(sc);
                System.out.println("Operação realizada com sucesso!");

            } else if (menu == 6) {
                //Entra no método de venda de produtos
                metodoVenda(sc);
                System.out.println("Operação realizada com sucesso!");

            } else if (menu == 7) {
                //relatório de todas as vendas

            } else if (menu == 8) {
                //relatório de vendas consolidado pelo cpf

            } else if (menu == 9 ) {
                //Finaliza o programa;
                ciclo = false;
            }


        }
    }


    public static int lerMenu (Scanner sc){
        //Mostra um Menu requisitando ao usuário que
        //digite um número para entrar em uma das funções do sistema
        //retornando ao programa principal

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
        //Lista todos os itens guardados dentro do "Banco de dados" usando um dos tipos
        //Alimentos, Bebidas ou Higiene

        String index;
        Tipo markup = null;
        int flag=0;

        do{
            //Dentro deste ciclo, pergunta e exibe novamente os itens que podem ser escolhidos
            //e mantém dentro desta repetição até que seja escolhido um número que está dentro
            //do que é pedido

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

        //Após receber um tipo válido, parte para a exibição

        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos [i][0] == null){
                //Entrará em null, após percorrer toda a matriz, já que os espaços vazios, ou nulls
                //vão estar sempre na última posição da matriz, ou seja, se for null não haverá mais nada
                //a frente e o método pode ser encerrado.
                return;
            }
            if (((Tipo) dadosProdutos[i][0]).equals(markup)){

                //Caso o produto corresponda ao tipo anteriormente selecionado, irá exibir o que está abaixo
                //As primeiras linhas se referem a formatação de data da última compra

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

            //Se não entrar nenhuma vez no ciclo, então, o flag será igual a 0, como na declaração
            //da variável, então, irá exibir a mensagem de que não existe nenhum item no estoque
            //que bate com o que foi solicitado anteriormente
        }

    }
    public static void imprimirEstoque(){

        //Se não houver nada cadastrado ainda, mostra a mensagem e volta pro programa principal

        if(dadosProdutos[0][0] == null){
            System.out.println("O estoque ainda não ser impresso,pois nada foi adicionado");
            return;
        }



        System.out.println("Lista de itens cadastrados");
        System.out.println();

        for (int i = 0; i < dadosProdutos.length; i++) {

            //Mostra todos os itens do estoqueaté que chegue em alguma posição que seja null
            //Ou seja, após passar por todas as opções possíveis dentro da matriz

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

        //Neste método, antes de passar para a matriz, os dados são salvos em variáveis
        // que vão sendo validadas abaixo

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

        //salvando em variáveis também a data atual e calculando o preço final de cada produto
        //Usando uma função escrita dentro do enum tipo, markup e preçoCusto que são passados nesta
        //função, são recebidos anteriormente no código

        dataCompra = LocalDateTime.now();
        precoFinal = Tipo.calcularVenda (markup, precoCusto);

        //Se a matriz estiver cheia, retornará um boolean verdadeiro, permitindo entrar no método de
        //aumentar a matriz

        valid = matrizCheia();

        if (valid == true){
            aumentarMatriz();
        }

        //garantindo que há espaço para armazenar, é verificado se o produto já foi cadastrado por
        //meio do método produtoExistente, usando o código identificador como parâmetro

        valid = false;
        valid = produtoExistente(identificador);

        //passa para dentro da matriz
        if (valid == true){
            //método de aumentar estoque, caso item já exista
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
            //método de criar novo item, caso não exista ainda
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

        //Método de verificação, matriz cheia ou não

        for (int i = 0; i < dadosProdutos.length; i++) {
                if (dadosProdutos[i][0] == null){
                    return false;
                }
        }
        return true;
    }

    public static void aumentarMatriz(){

        //Método para dobrar o tamanho da matriz, recebendo os valores dela em uma matriz temporária
        //que tem o dobro do tamanho, logo depois, fazendo a matriz que esá sendo usada como principal
        //receber todos os itens da temporária, inclusive seu tamanho

        Object [][] newMatriz = new Object[dadosProdutos.length*2][colunas];

        for (int i = 0; i < dadosProdutos.length; i++) {
            for (int j = 0; j < dadosProdutos[i].length; j++) {
                newMatriz[i][j] = dadosProdutos [i][j];
            }
        }
        dadosProdutos = newMatriz;
    }
    
    public static boolean produtoExistente(String identificador){

        //Procura o item pelo identificador dentro da matriz principal, caso não ache
        //eventualmente irá achar o null, que é onde acabam os dados da matriz,
        // então, retorna false, pois o item não foi encontrado

        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos[i][2] == null){
                return false;
            }
            else if (((String) dadosProdutos[i][2]).equals(identificador)){
                return true;
            }
        }
        return false;
    }

    public static void pesquisaIdentificador(Scanner sc){

        //Método de pesquisa semelhante ao listarTipo, difere pois apenas um produto será retornado
        //com todas as suas especificações

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

        //Pesquisa semelhante a do itenficiador, difrenciando-se por pesquisar pelo nome
        //Ou por parte dele. Ex, se for procurado "Choco" irá encontrar o produto caso
        //ele seja chamado "Chocolate"


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

    public static void metodoVenda(Scanner sc){
        String inserir;
        boolean validar = false;

        System.out.println("Bem vindo a interface de vendas, deseja inserir o CPF? ");
        System.out.println("Digite 1 para sim e 0 para não: ");

        do {
            inserir = sc.nextLine();
            if (inserir.equals("1") || inserir.equals("0")){
               validar = true;
            }else {
                System.out.println("Por favor, digite um número válido");
            }
        }while (!validar);

        String cpf = validarCPF(sc,inserir);

        String receberCliente = null;
        Clientes tipoCliente = null;

        if(!cpf.equals("00000000191")){
            do {
                System.out.println("Digite o tipo de cliente (PJ ou VIP): ");
                receberCliente = sc.nextLine();

                if (receberCliente.equals("PJ")){
                    tipoCliente = Clientes.PJ;
                } else if (receberCliente.equals("VIP")) {
                    tipoCliente = Clientes.VIP;
                }else {
                    System.out.println("Tipo inválido, por favor, digite novamente");
                }

            }while (tipoCliente == null);
        }else {
            tipoCliente = Clientes.PF;
        }

        periodoCompras(sc, tipoCliente, cpf);


    }

    public static void periodoCompras(Scanner sc, Clientes tipoCliente, String cpf){
        String prodComprado;
        Object exibirCompra [][] = new Object[1][5];

        do {

            System.out.println("Por favor, digite o código do produto que deseja comprar, " +
                    "ou digite Fim para finalizar as compras: ");
            prodComprado = sc.nextLine();
            boolean valida = false;

            if (prodComprado.equalsIgnoreCase("fim")) {
                exibirCompra(exibirCompra, tipoCliente, cpf);

                return;
            } else {
                do {
                    valida = produtoExistente(prodComprado);
                    if (valida == false) {
                        System.out.println("Produto não encontrado no sistema, por favor, digite um código válido: ");
                        prodComprado = sc.nextLine();
                    }
                } while (!valida);
            }

            int quantidade = 0;
            boolean temEstoque = false;
            int exibirEstoque = 0;
            do {
                System.out.println("Por favor, digite a quantidade que deseja comprar: ");
                quantidade = sc.nextInt();
                String aux = sc.nextLine();
                if (quantidade > 0){
                    temEstoque = comparaComEstoque(prodComprado,quantidade);
                    if (!temEstoque){
                        for (int i = 0; i < dadosProdutos.length; i++) {
                            if (((String) dadosProdutos[i][2]).equals(prodComprado)){
                                exibirEstoque = ((int) dadosProdutos[i][8]);
                            }
                        }
                        System.out.println("A quantidade requerida é maior do que a do estoque");
                        System.out.println("Quantidade em estoque: " + exibirEstoque);
                    }
                }else {
                    System.out.println("Digite um valor maior do que zero");
                }
            } while (!temEstoque);

            //baixa no estoque
            baixaEstoque(prodComprado,quantidade);

            String nomeProduto = null;
            double precoProduto = 0;

            for (int i = 0; i < dadosProdutos.length; i++) {
                if (((String) dadosProdutos[i][2]).equals(prodComprado)){
                    nomeProduto = ((String) dadosProdutos[i][3]);
                    precoProduto = ((double) dadosProdutos[i][7]);
                    i = dadosProdutos.length +1;
                }
            }

            double valorTotal = precoProduto * quantidade;

            for (int i = 0; i < exibirCompra.length; i++) {
                exibirCompra[i][0] = prodComprado;
                exibirCompra[i][1] = nomeProduto;
                exibirCompra[i][2] = quantidade;
                exibirCompra[i][3] = precoProduto;
                exibirCompra[i][4] = valorTotal;
            }

            exibirCompra = aumentaEmUm(exibirCompra);

        }while(!prodComprado.equalsIgnoreCase("fim"));

    }

    public static void baixaEstoque(String identificador, int quantidade){

        //Recebe um identificador e uma quantidade a remover do estoque
        //Procura o produto pelo identificador
        //guarda a variável de quantidade em estoque em uma variável auxiliar
        //subtrai a quantidade comprada e armazena novamente dentro da matriz

        for (int i = 0; i < dadosProdutos.length; i++) {
            if (((String) dadosProdutos[i][2]).equals(identificador)){
                int aux = 0;
                aux = ((int) dadosProdutos[i][8]);
                aux = aux - quantidade;
                dadosProdutos[i][8] = aux;
                return;
            }
        }

    }

    public static boolean comparaComEstoque(String identificador, int quantidade){
        for (int i = 0; i < dadosProdutos.length; i++) {
            if (dadosProdutos[i][2].equals(identificador)){
                if (((int) dadosProdutos[i][8]) < quantidade){
                    return false;
                }else {
                    return true;
                }
            }
        }
        return true;
    }

    public static void exibirCompra (Object[][] exibirCompra, Clientes tipoCliente, String cpf){

        double totalProdutos = 0;
        int itens =0;

        for (int i = 0; i < exibirCompra.length-1; i++) {
                System.out.println("-----------------------");
                System.out.println("Código: " + exibirCompra[i][0]);
                System.out.println("Nome: " + exibirCompra[i][1]);
                System.out.println("Quantidade: " + exibirCompra[i][2]);
                System.out.println("Preço Unitário: " + exibirCompra[i][3]);
                System.out.println("Valor Total: " + exibirCompra[i][4]);
                System.out.println("-----------------------");
                totalProdutos = totalProdutos + ((double) exibirCompra[i][4]);
                itens++;
        }

        totalProdutos = Clientes.calcularDesconto(totalProdutos,tipoCliente);

        System.out.println("Valor total da compra: " + totalProdutos);

        for (int i = 0; i < produtosComprados.length; i++) {
            if (produtosComprados[i] == null){
                produtosComprados[i][0] = cpf;
                produtosComprados[i][1] = tipoCliente;
                produtosComprados[i][2] = itens;
                produtosComprados[i][3] = totalProdutos;
            }
        }
        produtosComprados = aumentaEmUm(produtosComprados);
    }

    public static Object[][] aumentaEmUm(Object[][] exibirCompra){

        //Método para aumentar em apenas um o tamanho da matriz, pois dentro do método de exibir compra
        //do cliente, o cliente pode comprar de 1 a n produtos, por isso a matriz vai aumentar
        //de 1 em 1, conforme o cliente for comprando

        Object [][] newMatriz = new Object[exibirCompra.length+1][5];

        for (int i = 0; i < exibirCompra.length; i++) {
            for (int j = 0; j < exibirCompra[i].length; j++) {
                newMatriz[i][j] = exibirCompra [i][j];
            }
        }
        return newMatriz;
    }


    public static String validarCPF(Scanner sc, String inserir){

        //validação de cpf precisa receber 1 ou 0, pois caso o cliente não queira informar o cpf
        //será admitido o cpf ficticio, porém válido: 000 000 001 91, quando houver exibição de relatório
        //vai ser necessário exibir um cpf, esse número é adicionado sem que o usuário veja, caso este
        //método receba 0, isso já acontece automaticamente

        boolean isNumber = false;
        boolean isValid = false;
        String cpf = null;
        do {

            if(inserir.equals("1")) {

                System.out.println("Por favor, digite o seu CPF: ");
                cpf = sc.nextLine();
                char[] cpfArray = cpf.toCharArray();

                //Não aceita se o cpf não tiver o tamanho certo

                if (cpfArray.length <= 11 && cpfArray.length > 9) {

                    //função para confirmar se foram digitados apenas números e não letras
                    isNumber = validaIsString(cpfArray);

                    if (isNumber == true) {

                        //função do cálculo do cpf
                        isValid = calculoCPF(cpfArray);
                    }
                }
                if (!isValid) {

                    //todas as funções retornam um boolen para saber se é válido, caso não seja, irá cair
                    //neste bloco e pedir o cpf novamente
                    System.out.println("CPF inválido, por favor digite novamente");
                }
            }

            if (inserir.equals("0")) {

                //caso o cliente não queira informar o cpf, este será atribuido automaticamente
                cpf = "00000000191";
                isValid = true;
            }
        }while(!isValid);

        return cpf;

    }

    public static boolean validaIsString (char [] cpfArray) {
        //validação do cpf, recebido como string e transformado em array de char, para saber se todos
        //os caracteres são números

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

        //cálculo de cpf seguindo os padrões encontrados na internet, para referencia
        //pode-se usar: https://dicasdeprogramacao.com.br/algoritmo-para-validar-cpf/#:~:text=Regra%20para%20validar%20CPF&text=O%20CPF%20%C3%A9%20formado%20por,do%20sinal%20%22%2D%22).

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
