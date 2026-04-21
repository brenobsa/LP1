package Feira;

public class Banca {
    private String nomeBanca;
    private String setor; // Ex: Frutas, Legumes, Pastéis
    private boolean aberta;

    public Banca(String nome, String setor) {
        this.nomeBanca = nome;
        this.setor = setor;
        this.aberta = false;
    }

    public void alternarStatusBanca() {
        this.aberta = !this.aberta;
    }

    public String gerarAnuncioChamada() {
        return "Olha a " + setor.toLowerCase() + " freguês! Banca " + nomeBanca + " com ofertas!";
    }

    public boolean validarSetor() {
        return setor != null && !setor.isEmpty();
    }
}