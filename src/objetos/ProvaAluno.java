package objetos;

public class ProvaAluno extends Prova{

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getQlin() {
        return qlin;
    }

    public void setQlin(int qlin) {
        this.qlin = qlin;
    }

    public int getQmat() {
        return qmat;
    }

    public void setQmat(int qmat) {
        this.qmat = qmat;
    }

    public int getQnat() {
        return qnat;
    }

    public void setQnat(int qnat) {
        this.qnat = qnat;
    }

    public int getQhum() {
        return qhum;
    }

    public void setQhum(int qhum) {
        this.qhum = qhum;
    }

    public int getQtotal() {
        return qtotal;
    }

    public void setQtotal(int qtotal) {
        this.qtotal = qtotal;
    }
    private String nomeAluno;
    private int qlin;
    private int qmat;
    private int qnat;
    private int qhum;
    private int qtotal;
}
