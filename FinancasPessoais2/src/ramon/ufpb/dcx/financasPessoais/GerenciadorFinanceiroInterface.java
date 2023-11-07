package ramon.ufpb.dcx.financasPessoais;

import java.util.List;

public interface GerenciadorFinanceiroInterface {
	
	void cadastrarUsuario(String email, String senha);
    boolean fazerLogin(String email, String senha);
    void adicionarTransacao(String descricao, double valor);
    List<String> pesquisarTransacoes(String palavraChave);
    void removerTransacao(String descricao);
    void fazerLogout();
}
