package sistema;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objetos.Aluno;
import objetos.Tipo;
import objetos.Prova;
import objetos.Turma;

public class Agenda {

    private final Persistencia db;

    public Agenda() {
        db = new Persistencia();
    }

    public void criarCSV(String local) throws SQLException, IOException {
        List<Aluno> lista = db.ListarAluno();
        List<String> linha = new ArrayList<>();
        Iterator<Aluno> it = lista.iterator();
        while (it.hasNext()) {
            Aluno a = it.next();
            String nome = a.getNome();
            String nomeD[] = nome.split(" ");
            String turma = a.getTurma().substring(0, 1) + "-" + a.getTurma().substring(1, 4);
            nome = nome.replace(nomeD[0] + " ", "");
            String aux = a.getMatricula() + "," + nomeD[0] + "," + nome + "," + turma;
            linha.add(aux);
        }
        db.gerarCSVAluno(linha, local);
    }

    public void criarTurma(Turma nova, String texto) throws SQLException {
        db.adicionarTurma(nova);
        String codigo = nova.getSerie() + nova.getTurma() + nova.getAno();
        String linha[] = texto.split("\\n");
        for (int i = 0; i < linha.length; i++) {
            String dados[] = linha[i].split("	");
            db.adicionarAluno(dados[0], dados[1], codigo);
        }

    }
    
    public String[] getTurmas(String ano) throws SQLException{
        List<Turma> lista = db.getTurmas(ano);
        String retorno[] = new String[lista.size()];
        for(int i = 0;i < lista.size(); i++){
            String aux = lista.get(i).getSerie() + " " + lista.get(i).getTurma();
            retorno[i] = aux;
        }
        return retorno;
    }
    
    public void criarTipo(Tipo novo,Object[] turmas) throws SQLException{
        String codigo = db.adicionarTipo(novo);
        System.out.println(codigo);
        for(int i = 0;i < turmas.length;i++){
            String codigoTurma = ((String) turmas[i]).substring(0,1) + ((String) turmas[i]).substring(2, 5) + novo.getAno();
            db.adicionarRelacaoTipoTurma(codigo, codigoTurma);
        }
    }
    
    public List<Tipo> getTipo(String ano, String bimestre) throws SQLException{
        return db.getTipos(ano, bimestre);
    }
    
    public Tipo getTipo(String codigo) throws SQLException{
        return db.getTipos(codigo);
    }
    
    public void criarPDF() throws FileNotFoundException, DocumentException, BadElementException, IOException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("documento.pdf"));
        document.open();
        Paragraph nomeEscola = new Paragraph();
        nomeEscola.setFont(new Font(FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLACK));
        nomeEscola.setAlignment(Element.ALIGN_CENTER);
        nomeEscola.add("\n");
        nomeEscola.add("\n");
        nomeEscola.add("\n");
        nomeEscola.add("Escola SESI Campina");
        document.add(nomeEscola);
        
        Image img = Image.getInstance("logoSESI.png");
        img.scaleAbsolute(150,80);
        img.setAbsolutePosition(220, 760);
        
        document.add(img);
        
        document.close();
    }

    public void correcao(File[] arquivo, Tipo tipo) throws IOException, SQLException {
        int q1 = 10;
        int q2 = q1 + tipo.getLinguagem();
        int q3 = q2 + tipo.getMatematica();
        int q4 = q3 + tipo.getNatureza();
        int q5 = q4 + tipo.getHumana();
        System.out.println(q1+" "+q2+" "+q3+" "+q4+" "+q5);
        int total = tipo.getTotal();
        String protocolo = tipo.getAno() + tipo.getBimestre();
        List<String> texto = db.lerCSV(arquivo);
        for (Iterator<String> it = texto.iterator(); it.hasNext();) {
            String[] linha = it.next().split(",");
            if (!"Quiz Name".equals(linha[0])) {
                Prova novo = new Prova();
                novo.setTipo(tipo.getCodigo());
                novo.setCodigo(protocolo + linha[2]);
                novo.setCodigoAluno(linha[2]);
                novo.setAno(tipo.getAno());
                int cont = 0;
                for (int i = q1; i < q2; i++) {
                    if ("1".equals(linha[i])) {
                        cont++;
                    }
                }
                novo.setLinguagem(cont);
                cont = 0;
                for (int i = q2; i < q3; i++) {
                    if ("1".equals(linha[i])) {
                        cont++;
                    }
                }
                novo.setMatematica(cont);
                cont = 0;
                for (int i = q3; i < q4; i++) {
                    if ("1".equals(linha[i])) {
                        cont++;
                    }
                }
                novo.setNatureza(cont);
                cont = 0;
                for (int i = q4; i < q5; i++) {
                    if ("1".equals(linha[i])) {
                        cont++;
                    }
                }
                novo.setHumana(cont);
                db.adicionarProva(novo);
            }
        }
    }
}
