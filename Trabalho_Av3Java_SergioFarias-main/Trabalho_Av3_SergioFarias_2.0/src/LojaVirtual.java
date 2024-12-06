import java.io.*;
import java.util.*;

public class LojaVirtual {

    private static List<Produtos> produtos = new ArrayList<>();
    private static List<Pedidos> pedidos = new ArrayList<>();
    private static Pessoa usuarioLogado;


    public static void main(String[] args) throws FileNotFoundException {

        Scanner prompt = new Scanner(System.in);
        boolean executandoCadastro = true;


        while (executandoCadastro) {
            System.out.println("\n==== Menu da Loja Virtual ====");
            System.out.println("1. Cadastre o usuário");
            System.out.println("2 Continuar");
            System.out.println("3. Sair");


            int opcao = prompt.nextInt();
            prompt.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Qual o tipo de usuário? ");
                    System.out.println("1. Administrador");
                    System.out.println("2. Cliente");

                    int opcao2 = prompt.nextInt();
                    prompt.nextLine();

                    switch (opcao2) {
                        case 1:
                            System.out.println("1. Administrador");
                            usuarioLogado = cadastroAdministrador();
                            break;
                        case 2:
                            System.out.println("2. Cliente");
                            usuarioLogado = cadastroCliente();
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Encaminhando para menu de loja virtual...");
                    inicializarProduto();

                    boolean continuarLoja = true;
                    while (continuarLoja) {
                        System.out.println("\n==== Menu da Loja Virtual ====");
                        System.out.println("1. Listar Produtos");
                        System.out.println("2. Mudar produtos do catálogo");
                        System.out.println("3. Criar pedido");
                        System.out.println("4. Exibir pedidos");
                        System.out.println("5. Aplicar desconto");
                        System.out.println("6. Salvar pedidos");
                        System.out.println("7. Sair");

                        int opcaoLoja = prompt.nextInt();
                        prompt.nextLine();

                        switch (opcaoLoja) {
                            case 1:
                                listarProdutos();
                                break;
                            case 2:
                                executarAutorizacao();
                                break;
                            case 3:
                                criarPedido();
                                break;
                            case 4:
                                exibirPedidos();
                                break;
                            case 5:
                                System.out.println("Escreva 'SIM' + o número referente à porcentagem de desconto");
                                aplicarDesconto();
                                break;
                            case 6:
                                try {
                                    salvarPedidos();
                                } catch (FileNotFoundException e) {
                                    System.out.println("Erro ao salvar pedidos: " + e.getMessage());
                                }
                                break;
                            case 7:
                                continuarLoja = false; // Sai do menu da loja
                                System.out.println("Saindo do menu da loja. Até logo!");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 3:
                    executandoCadastro = false;
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        prompt.close();

    }

    private static Pessoa cadastroAdministrador() {
        Scanner prompt = new Scanner(System.in);
        System.out.println("Digite o nome do administrador: ");
        String nomeAdministrador = prompt.nextLine();
        nomeAdministrador = Administrador.nomePessoa;
        return new Administrador(true, nomeAdministrador);
    }

    private static Pessoa cadastroCliente() {
        Scanner prompt = new Scanner(System.in);
        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = prompt.nextLine();
        nomeCliente = Cliente.nomePessoa;
        return new Cliente(false, nomeCliente);
    }

    private static void inicializarProduto() {
        produtos.add(new Eletronicos("Notebook", 3000, 11, "TURF", "ASUS", "Placa de video: NVIDIA RTX 4090", 2023));
        produtos.add(new Eletronicos("Smartphone", 2299, 8, "RAZR 40", "MOTOROLA", "Processador: SNAPDRAGON GEN 1", 2022));
        produtos.add(new Roupas("Cachecol", 120, 5, "M", "Algodão", "ZARA"));
        produtos.add(new Roupas("Calça Jeans", 100, 20, "P", "JEANS", "RIACHOELO"));
    }

    private static void listarProdutos() {
        System.out.println("\n==== Lista de Produtos ====");
        for (int indice = 0; indice < produtos.size(); indice++) {
            System.out.println((indice + 1) + ". " + produtos.get(indice));
        }
    }

    private static void executarAutorizacao() {
        if (usuarioLogado instanceof Administrador) {
            try {
                usuarioLogado.isAutorizacaoDeMudanca(true);
                System.out.println("Você tem autorização para mudar os produtos do catálogo.");
                Scanner prompt = new Scanner(System.in);
                listarProdutos();

                System.out.println("Digite o índice do produto que deseja mudar:");
                int indice = prompt.nextInt() - 1;
                prompt.nextLine();

                System.out.println("Digite o novo nome do produto:");
                String novoNome = prompt.nextLine();

                System.out.println("Digite o novo preço do produto:");
                double novoPreco = prompt.nextDouble();

                System.out.println("Digite o novo estoque do produto:");
                int novoEstoque = prompt.nextInt();

                mudarProduto(indice, novoNome, novoPreco, novoEstoque);

            } catch (PermissaoNaoAutorizadaException e) {
                System.out.println(e.getMessage());
            }
        } else if (usuarioLogado instanceof Cliente) {
            try {
                usuarioLogado.isAutorizacaoDeMudanca(true);
            } catch (PermissaoNaoAutorizadaException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void mudarProduto(int indice, String novoNome, double novoPreco, int novoEstoque) {
        if (indice >= 0 && indice < produtos.size()) {
            Produtos produto = produtos.get(indice);
            produto.nomeProduto = novoNome;
            produto.preco = novoPreco;
            produto.estoque = novoEstoque;
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Índice do produto inválido.");
        }
    }

    private static void criarPedido() {
        Scanner prompt = new Scanner(System.in);

        System.out.println("Escolha o tipo de pedido:");
        System.out.println("1. Pedido Online");
        System.out.println("2. Pedido para Retirada");

        int tipoPedido = prompt.nextInt();
        prompt.nextLine();

        listarProdutos();

        System.out.println("Digite o índice do produto que deseja adicionar ao pedido:");
        int indiceProduto = prompt.nextInt() - 1;
        prompt.nextLine();

        System.out.println("Digite a quantidade do produto:");
        int quantidade = prompt.nextInt();
        prompt.nextLine();

        try {
            // Verifica se o índice do produto é válido
            if (indiceProduto < 0 || indiceProduto >= produtos.size()) {
                System.out.println("Índice do produto inválido.");
                return;
            }

            Produtos produtoSelecionado = produtos.get(indiceProduto);

            Pedidos novoPedido;
            if (tipoPedido == 1) {
                System.out.println("Digite o endereço de entrega:");
                String enderecoEntrega = prompt.nextLine();
                novoPedido = new PedidoOnline(usuarioLogado, enderecoEntrega);
            } else if (tipoPedido == 2) {
                novoPedido = new PedidoRetirada(usuarioLogado);
                ((PedidoRetirada) novoPedido).setEnderecoLoja();
            } else {
                System.out.println("Tipo de pedido inválido.");
                return;
            }

            novoPedido.adicionarProduto(produtoSelecionado, quantidade);
            pedidos.add(novoPedido);
            System.out.println("Pedido criado com sucesso!");

        } catch (EstoqueInsuficienteException e) {
            System.out.println("Estoque insuficiente para o produto selecionado.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar o pedido: " + e.getMessage());
        }
    }

    public static void exibirPedidos(){
        System.out.println("\n==== Lista de Pedidos ====");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi criado.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            Pedidos pedido = pedidos.get(i);
            System.out.println("Pedido " + (i + 1) + ":");

            if (pedido instanceof PedidoOnline) {
                System.out.println("Tipo: Pedido Online");
                System.out.println("Endereço de Entrega: " + ((PedidoOnline) pedido).getEnderecoCliente());
            } else if (pedido instanceof PedidoRetirada) {
                System.out.println("Tipo: Pedido para Retirada");
                System.out.println("Endereço da Loja: " + ((PedidoRetirada) pedido).getEnderecoLoja());
            }

            System.out.println("Produtos:");
            for (Map.Entry<Produtos, Integer> entry : pedido.getProdutos().entrySet()) {
                Produtos produto = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println("- " + produto.getNomeProduto() + " (Quantidade: " + quantidade + ")");
            }
            double total = pedido.calcularTotal();
            System.out.printf("Valor Total: R$%.2f%n", total);
            System.out.println();
        }
    }

    public static void salvarPedidos() throws FileNotFoundException {
        File file = new File("pedido.txt");

        try (OutputStream os = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(os);
             BufferedWriter bfw = new BufferedWriter(osw)) {

            for (Pedidos pedido : pedidos) {
                if (pedido instanceof PedidoOnline) {
                    bfw.write("Tipo: Pedido Online");
                    bfw.newLine();
                    bfw.write("Endereço de Entrega: " + ((PedidoOnline) pedido).getEnderecoCliente());
                    bfw.newLine();
                } else if (pedido instanceof PedidoRetirada) {
                    bfw.write("Tipo: Pedido para Retirada");
                    bfw.newLine();
                    bfw.write("Endereço da Loja: " + ((PedidoRetirada) pedido).getEnderecoLoja());
                    bfw.newLine();
                }

                bfw.write("Produtos:");
                bfw.newLine();
                for (Map.Entry<Produtos, Integer> entry : pedido.getProdutos().entrySet()) {
                    Produtos produto = entry.getKey();
                    int quantidade = entry.getValue();
                    bfw.write("- " + produto.getNomeProduto() + " (Quantidade: " + quantidade + ")");
                    bfw.newLine();
                }
                bfw.newLine();
            }
            System.out.println("Pedidos salvos com sucesso em " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os pedidos: " + e.getMessage());
        }
    }

    public static void aplicarDesconto(){
        Scanner prompt = new Scanner(System.in);

        // Listar produtos disponíveis
        listarProdutos();

        System.out.println("Digite o índice do produto que deseja aplicar desconto:");
        int indiceProduto = prompt.nextInt() - 1;
        prompt.nextLine(); // Consumir a nova linha

        if (indiceProduto < 0 || indiceProduto >= produtos.size()) {
            System.out.println("Índice do produto inválido.");
            return;
        }

        Produtos produtoSelecionado = produtos.get(indiceProduto);


        System.out.println("Digite o desconto em letras (ex: 'SIM' ou 'NAO'):");
        String descontoLetras = prompt.nextLine();

        System.out.println("Digite o desconto em números (ex: '10' para 10%):");
        String descontoNumeros = prompt.nextLine();

        // Aplicar desconto
        boolean descontoNumeroB = descontoNumeros != null && descontoNumeros.matches("\\d{1,3}");
        boolean descontoLetrasB = descontoLetras != null && descontoLetras.equalsIgnoreCase("SIM");

        try {
            produtoSelecionado.descontoCalculo(descontoLetras, descontoNumeros, descontoNumeroB, descontoLetrasB);
            System.out.println("Desconto aplicado com sucesso ao produto: " + produtoSelecionado.getNomeProduto());
            System.out.println("Novo preço: R$" + produtoSelecionado.getPreco());
        } catch (DescontoInvalidoException e) {
            System.out.println("Erro ao aplicar desconto: " + e.getMessage());
        }
    }
}