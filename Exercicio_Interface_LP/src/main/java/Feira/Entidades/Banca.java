package Feira;

public class Banca {
    int id;
    String nome;
    String setor;

    public Banca(int id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
    }

    public String gerarAnuncio() {
        return "Banca " + nome + " do setor de " + setor + " está pronta!";
    }

    public boolean validarId() {
        return id > 0;
    }

    public String getNome() {
        return nome;
    }
}