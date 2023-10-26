import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDeVendas {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Venda> vendas = new ArrayList();

    public void cadastrarCliente(String cpf, String nome, String email) {
        if (validarEmail(email) && !cpfRepetido(cpf) && !emailRepetido(email)) {
            clientes.add(new Cliente(cpf, nome, email));
            System.out.println("Cliente cadastrado com sucesso.");
        } else {
            System.out.println("Cadastro de cliente inválido.");
        }
    }

    public void cadastrarVendedor(String email, String nome) {
        if (validarEmail(email) && !emailRepetido(email)) {
            vendedores.add(new Vendedor(email, nome));
            System.out.println("Vendedor cadastrado com sucesso.");
        } else {
            System.out.println("Cadastro de vendedor inválido.");
        }
    }

    public void cadastrarVenda(int codigo, Vendedor vendedor, Cliente cliente, String produto, double preco, int quantidade, String dataRegistro) {
        if (clientes.contains(cliente) && vendedores.contains(vendedor)) {
            vendas.add(new Venda(codigo, vendedor, cliente, produto, preco, quantidade, dataRegistro));
            System.out.println("Venda cadastrada com sucesso.");
        } else {
            System.out.println("Não é possível cadastrar a venda. Cliente ou vendedor não encontrados.");
        }
    }

    public void listarVendas() {
        for (Venda venda : vendas) {
            System.out.println("Código: " + venda.getCodigo());
            System.out.println("Vendedor: " + venda.getVendedor().getNome());
            System.out.println("Cliente: " + venda.getCliente().getNome());
            System.out.println("Produto: " + venda.getProduto());
            System.out.println("Preço: " + venda.getPreço());
            System.out.println("Quantidade: " + venda.getQuantidade());
            System.out.println("Valor Total: " + venda.getValorTotal());
            System.out.println("Data de Registro: " + venda.getDataRegistro());
            System.out.println();
        }
    }

    public void listarVendedores() {
        for (Vendedor vendedor : vendedores) {
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("E-mail: " + vendedor.getEmail());
            System.out.println();
        }
    }

    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("E-mail: " + cliente.getEmail());
            System.out.println();
        }
    }

    public void pesquisarComprasCliente(String cpf) {
        for (Venda venda : vendas) {
            if (venda.getCliente().getCpf().equals(cpf)) {
                System.out.println("Código: " + venda.getCodigo());
                System.out.println("Produto: " + venda.getProduto());
                System.out.println("Preço: " + venda.getPreço());
                System.out.println("Quantidade: " + venda.getQuantidade());
                System.out.println("Valor Total: " + venda.getValorTotal());
                System.out.println("Data de Registro: " + venda.getDataRegistro());
                System.out.println();
            }
        }
    }

    public void pesquisarVendasVendedor(String email) {
        for (Venda venda : vendas) {
            if (venda.getVendedor().getEmail().equals(email)) {
                System.out.println("Código: " + venda.getCodigo());
                System.out.println("Produto: " + venda.getProduto());
                System.out.println("Preço: " + venda.getPreço());
                System.out.println("Quantidade: " + venda.getQuantidade());
                System.out.println("Valor Total: " + venda.getValorTotal());
                System.out.println("Data de Registro: " + venda.getDataRegistro());
                System.out.println();
            }
        }
    }

    private boolean validarEmail(String email) {
        return email.contains("@");
    }

    private boolean cpfRepetido(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean emailRepetido(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return true;
            }
        }
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SistemaDeVendas sistema = new SistemaDeVendas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Vendedor");
            System.out.println("3. Cadastrar Venda");
            System.out.println("4. Listar Vendas");
            System.out.println("5. Listar Vendedores");
            System.out.println("6. Listar Clientes");
            System.out.println("7. Pesquisar Compras por CPF");
            System.out.println("8. Pesquisar Vendas por Email");
            System.out.println("9. Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanner.next();
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.next();
                    System.out.println("Digite o email do cliente:");
                    String email = scanner.next();
                    sistema.cadastrarCliente(cpf, nome, email);
                    break;

                case 2:
                    System.out.println("Digite o email do vendedor:");
                    String emailVendedor = scanner.next();
                    System.out.println("Digite o nome do vendedor:");
                    String nomeVendedor = scanner.next();
                    sistema.cadastrarVendedor(emailVendedor, nomeVendedor);
                    break;

                case 3:
                    System.out.println("Digite o código da venda:");
                    int codigo = scanner.nextInt();
                    System.out.println("Digite o email do vendedor:");
                    String emailV = scanner.next();
                    System.out.println("Digite o CPF do cliente:");
                    String cpfC = scanner.next();
                    System.out.println("Digite o nome do produto:");
                    String produto = scanner.next();
                    System.out.println("Digite o preço:");
                    double preco = scanner.nextDouble();
                    System.out.println("Digite a quantidade:");
                    int quantidade = scanner.nextInt();
                    System.out.println("Digite a data de registro:");
                    String dataRegistro = scanner.next();

                    Vendedor vendedor = null;
                    Cliente cliente = null;

                    for (Vendedor v : sistema.vendedores) {
                        if (v.getEmail().equals(emailV)) {
                            vendedor = v;
                            break;
                        }
                    }

                    for (Cliente c : sistema.clientes) {
                        if (c.getCpf().equals(cpfC)) {
                            cliente = c;
                            break;
                        }
                    }

                    if (vendedor != null && cliente != null) {
                        sistema.cadastrarVenda(codigo, vendedor, cliente, produto, preco, quantidade, dataRegistro);
                    } else {
                        System.out.println("Não foi possível cadastrar a venda. Vendedor ou cliente não encontrados.");
                    }
                    break;

                case 4:
                    sistema.listarVendas();
                    break;

                case 5:
                    sistema.listarVendedores();
                    break;

                case 6:
                    sistema.listarClientes();
                    break;

                case 7:
                    System.out.println("Digite o CPF do cliente:");
                    String cpfPesquisa = scanner.next();
                    sistema.pesquisarComprasCliente(cpfPesquisa);
                    break;

                case 8:
                    System.out.println("Digite o email do vendedor:");
                    String emailPesquisa = scanner.next();
                    sistema.pesquisarVendasVendedor(emailPesquisa);
                    break;

                case 9:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}