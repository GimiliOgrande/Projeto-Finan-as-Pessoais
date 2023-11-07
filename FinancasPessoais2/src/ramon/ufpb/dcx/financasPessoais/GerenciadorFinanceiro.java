package ramon.ufpb.dcx.financasPessoais;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorFinanceiro {

	private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioAtual = null;

    public static void main(String[] args) {
        carregarUsuarios();

        new GerenciadorFinanceiroGUI(usuarios);
        boolean executando = true;
        Scanner scanner = new Scanner(System.in);

        while (executando) {
            if (usuarioAtual == null) {
                System.out.println("1. Cadastro");
                System.out.println("2. Login");
                System.out.println("0. Sair");
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (escolha) {
                    case 1:
                        cadastrarUsuario(scanner);
                        break;
                    case 2:
                        fazerLogin(scanner);
                        break;
                    case 0:
                        executando = false;
                        break;
                    default:
                        System.out.println("Escolha inválida");
                }
            } else {
                System.out.println("Bem-vindo, " + usuarioAtual.getEmail());
                System.out.println("1. Adicionar transação");
                System.out.println("2. Pesquisar transações");
                System.out.println("3. Remover transações");
                System.out.println("4. Logout");
                System.out.println("5. Mostrar Valor Total de Transações");
                System.out.println("0. Sair");

                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (escolha) {
                    case 1:
                        adicionarTransacao(scanner);
                        break;
                    case 2:
                        pesquisarTransacoes(scanner);
                        break;
                    case 3:
                        removerTransacao(scanner);
                        break;
                    case 4:
                        usuarioAtual = null;
                        break;
                    case 5:
                        mostrarValorTotalTransacoes();
                        break;
                    case 0:
                        executando = false;
                        break;
                    default:
                        System.out.println("Escolha inválida");
                }
            }
        }
        salvarUsuarios();
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        usuarios.add(new Usuario(email, senha));
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void fazerLogin(Scanner scanner) {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioAtual = usuario;
                System.out.println("Login realizado com sucesso!");
                return;
            }
        }
        System.out.println("Email ou senha incorretos.");
    }

    private static void adicionarTransacao(Scanner scanner) {
        System.out.print("Descrição da transação: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor da transação: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir nova linha

        Transacao transacao = new Transacao(descricao, valor);
        usuarioAtual.adicionarTransacao(transacao);
        System.out.println("Transação adicionada com sucesso!");
    }

    public static void pesquisarTransacoes(Scanner scanner) {
        System.out.print("Digite uma palavra-chave para a pesquisa: ");
        String palavraChave = scanner.nextLine();

        for (Transacao transacao : usuarioAtual.getTransacoes()) {
            if (transacao.getDescricao().toLowerCase().contains(palavraChave.toLowerCase())) {
                System.out.println("Descrição: " + transacao.getDescricao() + ", Valor: " + transacao.getValor());
            }
        }
    }

    private static void removerTransacao(Scanner scanner) {
        System.out.print("Digite a descrição da transação a ser removida: ");
        String descricao = scanner.nextLine();

        usuarioAtual.getTransacoes().removeIf(transacao -> transacao.getDescricao().equalsIgnoreCase(descricao));
        System.out.println("Transação removida com sucesso!");
    }

    private static void mostrarValorTotalTransacoes() {
        double valorTotal = 0;
        for (Transacao transacao : usuarioAtual.getTransacoes()) {
            valorTotal += transacao.getValor();
        }
        System.out.println("Valor Total de Transações: " + valorTotal);
    }

    public static void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                Usuario usuario = new Usuario(partes[0], partes[1]);
                usuarios.add(usuario);

                carregarTransacoes(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarTransacoes(Usuario usuario) {
        String arquivoTransacoes = usuario.getEmail() + "_transacoes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoTransacoes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                Transacao transacao = new Transacao(partes[0], Double.parseDouble(partes[1]));
                usuario.adicionarTransacao(transacao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getEmail() + "," + usuario.getSenha());
                bw.newLine();

                salvarTransacoes(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarTransacoes(Usuario usuario) {
        String arquivoTransacoes = usuario.getEmail() + "_transacoes.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTransacoes))) {
            for (Transacao transacao : usuario.getTransacoes()) {
                bw.write(transacao.getDescricao() + "," + transacao.getValor());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}