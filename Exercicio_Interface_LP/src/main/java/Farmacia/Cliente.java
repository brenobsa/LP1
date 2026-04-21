package Farmacia;

public class Cliente {
    private String cpf;
    private String nome;
    private String emailContato;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.emailContato = email;
    }

    public String cadastrarDependente(String nomeDep) {
        return "Dependente " + nomeDep + " vinculado a " + this.nome;
    }

    public String consultarHistoricoCompras() {
        return "Histórico de compras carregado para o CPF: " + cpf;
    }

    public boolean validarDadosCadastrais() {
        return cpf.length() == 11 && emailContato.contains("@");
    }
}