package ramon.ufpb.dcx.financasPessoais;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {

	private String email;
    private String senha;
    private ArrayList<Transacao> transacoes;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.transacoes = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }

	public void adicionarTransacao(Transacao transacao) {
		transacoes.add(transacao);
		
	}
	
	public ArrayList<Transacao> pesquisarTransacoes(String descricao, double valorMinimo) {
        ArrayList<Transacao> resultadoPesquisa = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            // Verifique se a descrição da transação contém o texto de pesquisa (ignore case)
            if (transacao.getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                // Verifique se o valor da transação é maior ou igual ao valor mínimo
                if (transacao.getValor() >= valorMinimo) {
                    resultadoPesquisa.add(transacao);
                }
            }
        }

        return resultadoPesquisa;
    }
	
	public void removerTransacao(int indice) {
        if (indice >= 0 && indice < transacoes.size()) {
            transacoes.remove(indice);
        }
    }
	
	public double calcularValorTotalTransacoes() {
        double valorTotal = 0.0;

        for (Transacao transacao : transacoes) {
            valorTotal += transacao.getValor();
        }

        return valorTotal;
    }
}