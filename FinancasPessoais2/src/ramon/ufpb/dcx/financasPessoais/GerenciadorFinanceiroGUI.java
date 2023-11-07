package ramon.ufpb.dcx.financasPessoais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GerenciadorFinanceiroGUI extends JFrame implements ActionListener {
	
	
	public static Usuario usuarioAtual;
	private ArrayList<Usuario> usuarios;
	private static final long serialVersionUID = 1L;
	JTextField caixaTextEmail;
	JTextField caixaTextSenha;
	

	Font font = new Font("Arial", Font.BOLD, 15);
	Font font2 = new Font("Arial", Font.BOLD, 12);
	Color colorTexto = new Color(40, 20, 15);
	Color colorFundo = new Color(150, 150, 150);
	Color colorFundo2 = new Color(200, 220, 220);

	public GerenciadorFinanceiroGUI(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
		
		setVisible(true);
		setSize(700, 400);
		setTitle("Gerenciador Financeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		
		//Botão LOGIN
		setLayout(null);
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(110, 220, 200, 70);
		loginButton.setFont(font);
		loginButton.setForeground(colorTexto);
		loginButton.setBackground(colorFundo);
		add(loginButton);
		loginButton.addActionListener(this);
		
		caixaTextEmail = new JTextField();
		caixaTextEmail.setBounds(70, 70, 560, 35);
		caixaTextEmail.setBackground(new Color(230, 230, 230));
		
		caixaTextSenha = new JTextField();
		caixaTextSenha.setBounds(70, 150, 560, 35);
		caixaTextSenha.setBackground(new Color(230, 230, 230));
		
		JLabel textEmail = new JLabel("Email:");
		add(textEmail);
		textEmail.setBounds(70, 40, 50, 35);
		textEmail.setFont(font);
		textEmail.setForeground(colorTexto);
		
		JLabel textSenha = new JLabel("Senha:");
		add(textSenha);
		textSenha.setBounds(70, 120, 50, 35);
		textSenha.setFont(font);
		textSenha.setForeground(colorTexto);
		
		JLabel tittle = new JLabel("GERENCIADOR FINANCEIRO");
		add(tittle);
		tittle.setBounds(this.getX() / 2, 0, 500, 60);
		tittle.setFont(new Font("Arial", Font.ITALIC, 25));
		tittle.setForeground(colorTexto);
		
		add(caixaTextSenha);
		add(caixaTextEmail);
		
		//Botão CADASTRAR
		
		JButton CadastrarButton = new JButton("Cadastrar");
		CadastrarButton.setBounds(390, 220, 200, 70);
		CadastrarButton.setFont(font);
		CadastrarButton.setForeground(colorTexto);
		CadastrarButton.setBackground(colorFundo);
		add(CadastrarButton);
		CadastrarButton.addActionListener(this::Cadastro);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Botão de login
		// Método para logar e em seguida abrir o menu de opções
		
		fazerLogin(); 
		
	}
	
	public void Cadastro(ActionEvent actionEvent) {
		// Método para cadastrar
		
		cadastrarUsuario(caixaTextEmail.getText(), caixaTextSenha.getText());
	}
	
	public void exibirMenuPrincipal() {

		JFrame menuprincipal = new JFrame();
		menuprincipal.setVisible(true);
		menuprincipal.setSize(600, 350);
		menuprincipal.setTitle("Gerenciador Financeiro");
		menuprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuprincipal.setResizable(false);
		menuprincipal.setLocationRelativeTo(null);
		menuprincipal.setLayout(null);

		//BOTÕES
		
	    JButton adicionarTransacaoButton = new JButton("Adicionar Transação");
	    adicionarTransacaoButton.setBounds(20, 50, 160, 70);
	    adicionarTransacaoButton.setFont(font2);
	    adicionarTransacaoButton.setForeground(colorTexto);
	    adicionarTransacaoButton.setBackground(colorFundo2);
	    menuprincipal.add(adicionarTransacaoButton);
	    adicionarTransacaoButton.addActionListener(this::AdicionarTransacao);
	    
	    JButton pesquisarTransacaoButton = new JButton("Pesquisar Transação");
	    pesquisarTransacaoButton.setBounds(210, 50, 160, 70);
	    pesquisarTransacaoButton.setFont(font2);
	    pesquisarTransacaoButton.setForeground(colorTexto);
	    pesquisarTransacaoButton.setBackground(colorFundo2);
	    menuprincipal.add(pesquisarTransacaoButton);
	    pesquisarTransacaoButton.addActionListener(this::pesquisarTransacao);


	    JButton removerTransacaoButton = new JButton("Remover Transação");
	    removerTransacaoButton.setBounds(400, 50, 160, 70);
	    removerTransacaoButton.setFont(font2);
	    removerTransacaoButton.setForeground(colorTexto);
	    removerTransacaoButton.setBackground(colorFundo2);
	    menuprincipal.add(removerTransacaoButton);
	    removerTransacaoButton.addActionListener(this::removerTransacao);

	    
	    JButton mostrarValorTotalButton = new JButton("Mostrar Valor Total");
	    mostrarValorTotalButton.setBounds(20, 170, 160, 70);
	    mostrarValorTotalButton.setFont(font2);
	    mostrarValorTotalButton.setForeground(colorTexto);
	    mostrarValorTotalButton.setBackground(colorFundo2);
	    menuprincipal.add(mostrarValorTotalButton);
	    mostrarValorTotalButton.addActionListener(this::mostrarValor);

	    JButton logoutButton = new JButton("Logout");
	    logoutButton.setBounds(210, 170, 160, 70);
	    logoutButton.setFont(font2);
	    logoutButton.setForeground(colorTexto);
	    logoutButton.setBackground(colorFundo2);
	    menuprincipal.add(logoutButton);
	    logoutButton.addActionListener(this::logout);

	    
	    JButton SairButton = new JButton("Sair");
	    SairButton.setBounds(400, 170, 160, 70);
	    SairButton.setFont(font2);
	    SairButton.setForeground(colorTexto);
	    SairButton.setBackground(colorFundo2);
	    menuprincipal.add(SairButton);
	    SairButton.addActionListener(this::Sair);

	    
	   
	}

	//AÇÕES BOTÕES DO MENU =======================================================================================================================================================
	
	 public void AdicionarTransacao(ActionEvent actionEvent) {
		 exibirFormularioTransacao();
			
		}
	 

	 public void pesquisarTransacao(ActionEvent actionEvent) {
		 exibirFormularioPesquisa();
			
		}
	 
	 public void removerTransacao(ActionEvent actionEvent) {
		 exibirFormularioRemoverTransacao();
		 
		}
	 
	 public void mostrarValor(ActionEvent actionEvent) {
		 exibirValorTotalTransacoes();
			
		}
	 
	 public void logout(ActionEvent actionEvent) {
		 fazerLogout();			
			
		}
	 
	 public void Sair(ActionEvent actionEvent) {
		 encerrarAplicativo();			
			
		}
	 
	//============================================================================================================================================
	
	public void fazerLogin() {
        String email = caixaTextEmail.getText();
        String senha = new String(caixaTextSenha.getText());

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                usuarioAtual = u;
                exibirMenuPrincipal();
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Credenciais inválidas. Tente novamente.");
    }
	
	public void cadastrarUsuario(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                JOptionPane.showMessageDialog(null, "Este email já está em uso. Tente outro.");
                return;
            }
        }

        Usuario novoUsuario = new Usuario(email, senha);
        usuarios.add(novoUsuario);

        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }
	
	public void exibirFormularioTransacao() {
        // Crie um diálogo para inserir informações da transação
        JFrame dialogFrame = new JFrame("Adicionar Transação");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(350, 200);

        JPanel dialogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel descricaoLabel = new JLabel("Descrição:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialogPanel.add(descricaoLabel, constraints);

        JTextField descricaoField = new JTextField(20);
        descricaoField.setBounds(20, 20, 15, 20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        dialogPanel.add(descricaoField, constraints);

        JLabel valorLabel = new JLabel("Valor:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        dialogPanel.add(valorLabel, constraints);

        JTextField valorField = new JTextField(10);
        valorField.setBounds(20, 20, 15, 20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        dialogPanel.add(valorField, constraints);

        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String descricao = descricaoField.getText();
                String valorStr = valorField.getText();

                // Valide os campos (pode adicionar mais validações conforme necessário)
                if (descricao.isEmpty() || valorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogFrame, "Preencha todos os campos.");
                } else {
                    try {
                        double valor = Double.parseDouble(valorStr);

                        // Crie a transação
                        Transacao transacao = new Transacao(descricao, valor);

                        // Adicione a transação ao usuário atual
                        usuarioAtual.adicionarTransacao(transacao);

                        // Feche o diálogo
                        dialogFrame.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialogFrame, "Valor inválido. Use um número.");
                    }
                }
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        dialogPanel.add(adicionarButton, constraints);

        dialogFrame.add(dialogPanel);
        dialogFrame.setVisible(true);
    }
    
    // ... (outros métodos e campos)


	public void exibirFormularioPesquisa() {
	    // Crie um diálogo para inserir critérios de pesquisa
	    JFrame dialogFrame = new JFrame("Pesquisar Transação");
	    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    dialogFrame.setSize(450, 300);
	
	    JPanel dialogPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.insets = new Insets(5, 5, 5, 5);
	    //////////////////
	    JLabel descricaoLabel = new JLabel("Descrição:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialogPanel.add(descricaoLabel, constraints);

        JTextField descricaoField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        dialogPanel.add(descricaoField, constraints);

        JLabel valorLabel = new JLabel("Valor Mínimo:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        dialogPanel.add(valorLabel, constraints);

        JTextField valorMinimoField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        dialogPanel.add(valorMinimoField, constraints);
       
        dialogPanel.add(valorMinimoField);
        
	    JButton pesquisarButton = new JButton("Pesquisar");
	    pesquisarButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtenha os critérios de pesquisa
	            String descricao = descricaoField.getText();
	            String valorMinimoStr = valorMinimoField.getText();
	
	            // Valide os campos (pode adicionar mais validações conforme necessário)
	            if (valorMinimoStr.isEmpty()) {
	                valorMinimoStr = "0";
	            }
	
	            try {
	                double valorMinimo = Double.parseDouble(valorMinimoStr);
	
	                // Realize a pesquisa usando os critérios
	                ArrayList<Transacao> resultadoPesquisa = usuarioAtual.pesquisarTransacoes(descricao, valorMinimo);
	
	                // Exiba o resultado da pesquisa (você pode implementar isso conforme necessário)
	                exibirResultadoPesquisa(resultadoPesquisa);
	
	                // Feche o diálogo
	                dialogFrame.dispose();
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(dialogFrame, "Valor mínimo inválido. Use um número.");
	            }
	        }
	    });
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    dialogPanel.add(pesquisarButton, constraints);
	
	    dialogFrame.add(dialogPanel);
	    dialogFrame.setVisible(true);
	}
	
	public void exibirResultadoPesquisa(ArrayList<Transacao> resultadoPesquisa) {
        if (resultadoPesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma transação encontrada.");
        } else {
            JFrame resultadoFrame = new JFrame("Resultado da Pesquisa");
            resultadoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resultadoFrame.setSize(400, 300);

            JPanel resultadoPanel = new JPanel(new BorderLayout());

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> listaTransacoes = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(listaTransacoes);

            for (Transacao transacao : resultadoPesquisa) {
                listModel.addElement(transacao.getDescricao() + " - Valor: " + transacao.getValor());
            }

            resultadoPanel.add(scrollPane, BorderLayout.CENTER);

            resultadoFrame.add(resultadoPanel);
            resultadoFrame.setVisible(true);
        }
    }
	
	public void exibirFormularioRemoverTransacao() {
        // Crie um diálogo para selecionar a transação a ser removida
        JFrame dialogFrame = new JFrame("Remover Transação");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(500, 350);

        JPanel dialogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel selecioneLabel = new JLabel("Selecione a transação:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialogPanel.add(selecioneLabel, constraints);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> listaTransacoes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaTransacoes);

        // Preencha a lista de transações com as transações do usuário atual
        for (Transacao transacao : usuarioAtual.getTransacoes()) {
            listModel.addElement(transacao.getDescricao() + " - Valor: " + transacao.getValor());
        }

        constraints.gridx = 0;
        constraints.gridy = 1;
        dialogPanel.add(scrollPane, constraints);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha o índice da transação selecionada
                int selectedIndex = listaTransacoes.getSelectedIndex();

                if (selectedIndex != -1) {
                    // Remova a transação do usuário atual
                    usuarioAtual.removerTransacao(selectedIndex);

                    // Atualize a lista de transações exibida na janela principal
                    exibirMenuPrincipal();

                    // Feche o diálogo
                    dialogFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialogFrame, "Selecione uma transação para remover.");
                }
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        dialogPanel.add(removerButton, constraints);

        dialogFrame.add(dialogPanel);
        dialogFrame.setVisible(true);
    }
	
	public void exibirValorTotalTransacoes() {
        // Calcule o valor total de todas as transações do usuário
        double valorTotal = usuarioAtual.calcularValorTotalTransacoes();

        // Exiba o valor total em uma janela de diálogo
        JOptionPane.showMessageDialog(null, "Valor Total de Transações: " + valorTotal);
    }
	
	 public void fazerLogout() {
	        int escolha = JOptionPane.showConfirmDialog(null, "Deseja fazer logout?", "Logout", JOptionPane.YES_NO_OPTION);

	        if (escolha == JOptionPane.YES_OPTION) {
	            // Limpe os dados de usuário e volte à tela de login
	            usuarioAtual = null; // Limpa o usuário atual
	            exibirTelaLogin();
	        }
	 }
	 
	 public void exibirTelaLogin() {
		 setVisible(true);
			setSize(700, 400);
			setTitle("Gerenciador Financeiro");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);

			
			//Botão LOGIN
			setLayout(null);
			JButton loginButton = new JButton("Login");
			loginButton.setBounds(110, 220, 200, 70);
			loginButton.setFont(font);
			loginButton.setForeground(colorTexto);
			loginButton.setBackground(colorFundo);
			add(loginButton);
			loginButton.addActionListener(this);
			
			caixaTextEmail = new JTextField();
			caixaTextEmail.setBounds(70, 70, 560, 35);
			caixaTextEmail.setBackground(new Color(230, 230, 230));
			
			caixaTextSenha = new JTextField();
			caixaTextSenha.setBounds(70, 150, 560, 35);
			caixaTextSenha.setBackground(new Color(230, 230, 230));
			
			JLabel textEmail = new JLabel("Email:");
			add(textEmail);
			textEmail.setBounds(70, 40, 50, 35);
			textEmail.setFont(font);
			textEmail.setForeground(colorTexto);
			
			JLabel textSenha = new JLabel("Senha:");
			add(textSenha);
			textSenha.setBounds(70, 120, 50, 35);
			textSenha.setFont(font);
			textSenha.setForeground(colorTexto);
			
			JLabel tittle = new JLabel("GERENCIADOR FINANCEIRO");
			add(tittle);
			tittle.setBounds(this.getX() / 2, 0, 500, 60);
			tittle.setFont(new Font("Arial", Font.ITALIC, 25));
			tittle.setForeground(colorTexto);
			
			add(caixaTextSenha);
			add(caixaTextEmail);
			
			//Botão CADASTRAR
			
			JButton CadastrarButton = new JButton("Cadastrar");
			CadastrarButton.setBounds(390, 220, 200, 70);
			CadastrarButton.setFont(font);
			CadastrarButton.setForeground(colorTexto);
			CadastrarButton.setBackground(colorFundo);
			add(CadastrarButton);
			CadastrarButton.addActionListener(this::Cadastro);
	    }
	 
	 public void encerrarAplicativo() {
	        int escolha = JOptionPane.showConfirmDialog(null, "Deseja sair do aplicativo?", "Sair", JOptionPane.YES_NO_OPTION);

	        if (escolha == JOptionPane.YES_OPTION) {
	            // Encerre o aplicativo
	            System.exit(0);
	        }
	    }
}
