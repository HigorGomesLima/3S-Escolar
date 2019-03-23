package objetos;

public class Tipo {
    
    private String codigo;
    private String ano;
    private String bimestre;
    private int total;
    private int linguagem;
    private int matematica;
    private int humana;
    private int natureza;

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String nome) {
        this.bimestre = nome;
    }

    public int getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(int linguagem) {
        this.linguagem = linguagem;
    }

    public int getMatematica() {
        return matematica;
    }

    public void setMatematica(int matematica) {
        this.matematica = matematica;
    }

    public int getHumana() {
        return humana;
    }

    public void setHumana(int humana) {
        this.humana = humana;
    }

    public int getNatureza() {
        return natureza;
    }

    public void setNatureza(int natureza) {
        this.natureza = natureza;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
