package Farmacia;

public class Cliente {
    String cpf;
    String nome;
    String telefone;

    public Cliente(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String consultarHistoricoCompras() {
        return "Histórico de compras carregado para o CPF: " + cpf;
    }

    public boolean validarDadosCadastrais() {
        return cpf.length() == 11 && telefone.length() == 9;
    }
}