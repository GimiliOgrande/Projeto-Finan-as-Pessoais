package ramon.ufpb.dcx.financasPessoais;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import org.junit.Before;

public class GerenciadorFinanceiroTest {
    private Usuario usuario;
    @Before
    public void setUp() {
        usuario = new Usuario("test@example.com", "password");
        new GerenciadorFinanceiro();
        GerenciadorFinanceiro.carregarUsuarios();
        GerenciadorFinanceiro.carregarTransacoes(usuario);
    }

    @Test
    public void testAdicionarTransacao() {
        int tamanhoInicial = usuario.getTransacoes().size();

        // Adicione uma transação
        Transacao transacao = new Transacao("Compra de café", 10.0);
        usuario.adicionarTransacao(transacao);

        int tamanhoFinal = usuario.getTransacoes().size();

        // Verifique se o tamanho da lista de transações aumentou após adicionar uma transação
        assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }
    
    public void testRemoverTransacao() {
        int tamanhoInicial = usuario.getTransacoes().size();

        // Adicione uma transação para o usuário
        Transacao transacao = new Transacao("Compra de café", 10.0);
        usuario.adicionarTransacao(transacao);

        // Verifique se o tamanho da lista de transações aumentou após adicionar uma transação
        assertEquals(tamanhoInicial + 1, usuario.getTransacoes().size());

        // Remova a transação
        GerenciadorFinanceiro.removerTransacao("Compra de café");

        // Verifique se a transação foi removida com sucesso
        assertEquals(tamanhoInicial, usuario.getTransacoes().size());
    }
    
    public void testCadastrarUsuario() {
        // Verifique se o usuário não existe inicialmente
        assertFalse(GerenciadorFinanceiro.getUsuarios().contains(usuario));

        // Cadastre um novo usuário
        Usuario novoUsuario = new Usuario("novoteste@example.com", "novasenha");
        GerenciadorFinanceiro.cadastrarUsuario(novoUsuario);

        // Verifique se o novo usuário está na lista de usuários
        assertTrue(GerenciadorFinanceiro.getUsuarios().contains(novoUsuario));
    }
    
    public void testPesquisarTransacoes() {
        // Adicione algumas transações para o usuário
        usuario.adicionarTransacao(new Transacao("Compra de café", 10.0));
        usuario.adicionarTransacao(new Transacao("Compra de pão", 5.0));
        usuario.adicionarTransacao(new Transacao("Almoço", 15.0));

        // Execute a pesquisa com a palavra-chave "Compra"
        ArrayList<Transacao> resultados = GerenciadorFinanceiro.pesquisarTransacoes("Compra");

        // Verifique se a pesquisa retorna as transações corretas
        assertEquals(2, resultados.size()); // Deve haver 2 transações correspondentes
        assertTrue(resultados.get(0).getDescricao().contains("Compra"));
        assertTrue(resultados.get(1).getDescricao().contains("Compra"));
    }

}
