package ramon.ufpb.dcx.financasPessoais;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinanceiroIMPL implements GerenciadorFinanceiroInterface {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Transacao> transacoes;
    private Usuario usuarioAtual;

    public GerenciadorFinanceiroIMPL() {
        this.usuarios = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.usuarioAtual = null;
    }

    @Override
    public void cadastrarUsuario(String email, String senha) {
        usuarios.add(new Usuario(email, senha));
    }

    @Override
    public boolean fazerLogin(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioAtual = usuario;
                return true;
            }
        }
        return false;
    }

    @Override
    public void adicionarTransacao(String descricao, double valor) {
        if (usuarioAtual != null) {
            transacoes.add(new Transacao(descricao, valor));
        }
    }

    @Override
    public List<String> pesquisarTransacoes(String palavraChave) {
        List<String> resultados = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getDescricao().toLowerCase().contains(palavraChave.toLowerCase())) {
                resultados.add("Descrição: " + transacao.getDescricao() + ", Valor: " + transacao.getValor());
            }
        }
        return resultados;
    }

    @Override
    public void removerTransacao(String descricao) {
        if (usuarioAtual != null) {
            transacoes.removeIf(transacao -> transacao.getDescricao().equalsIgnoreCase(descricao));
        }
    }

    @Override
    public void fazerLogout() {
        usuarioAtual = null;
    }
}
