package Feira;

public class Banca {
    private String nomeBanca;
    private String setor;
    private boolean aberta = false;

    public Banca(String nome, String setor) {
        this.nomeBanca = nome;
        this.setor = setor;
    }

    public void alternarStatusBanca() {
        this.aberta = !this.aberta;
    }

    public String gerarAnuncioChamada() {
        String status = aberta ? "ABERTA" : "FECHADA";
        return "Banca " + nomeBanca + " (" + setor + ") está " + status + "!";
    }

    public String getNomeBanca() { return nomeBanca; }
}