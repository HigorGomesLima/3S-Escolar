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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
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
import objetos.ProvaAluno;
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
    
    public void criarPDF(String codigo) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("documento"+codigo+".pdf"));
        document.open();
        Paragraph nomeEscola = new Paragraph();
        nomeEscola.setFont(new Font(FontFamily.TIMES_ROMAN,12,Font.BOLD,BaseColor.BLACK));
        nomeEscola.setAlignment(Element.ALIGN_CENTER);
        nomeEscola.add("\n");
        nomeEscola.add("\n");
        nomeEscola.add("Escola SESI Campina");
        document.add(nomeEscola);
        
        
        Paragraph titulo = new Paragraph();
        titulo.setFont(new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLACK));
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.add("\n");
        titulo.add("Planilha de Notas do Simulado "+codigo.substring(0, 1)+"º Ano "+codigo.substring(2, 4)+ " - I Bimestre");
        document.add(titulo);
        
        Image img = Image.getInstance("logoSESI.png");
        img.scaleAbsolute(110,58);
        img.setAbsolutePosition(250, 780);
        document.add(img);
        
        PdfPTable tabela = new PdfPTable(12);
        Font fontnormal = new Font(FontFamily.COURIER,8,Font.NORMAL,BaseColor.BLACK);
        Font fontnegrito = new Font(FontFamily.COURIER,8,Font.BOLD,BaseColor.BLACK);
        tabela.setWidthPercentage(110);
        PdfPCell c1 = new PdfPCell(new Paragraph("Nº",fontnegrito));
        PdfPCell c2 = new PdfPCell(new Paragraph("Matrícula",fontnegrito));
        PdfPCell c3 = new PdfPCell(new Paragraph("Aluno",fontnegrito));
        PdfPCell c4 = new PdfPCell(new Paragraph("Ling",fontnegrito));
        PdfPCell c5 = new PdfPCell(new Paragraph("Nota",fontnegrito));
        PdfPCell c6 = new PdfPCell(new Paragraph("Mat",fontnegrito));
        PdfPCell c7 = new PdfPCell(new Paragraph("Nota",fontnegrito));
        PdfPCell c8 = new PdfPCell(new Paragraph("C.Nat",fontnegrito));
        PdfPCell c9 = new PdfPCell(new Paragraph("Nota",fontnegrito));
        PdfPCell c10 = new PdfPCell(new Paragraph("C.Hum",fontnegrito));
        PdfPCell c11 = new PdfPCell(new Paragraph("Nota",fontnegrito));
        PdfPCell c12 = new PdfPCell(new Paragraph("Nota Extra",fontnegrito));
        
        tabela.addCell(c1);
        tabela.addCell(c2);
        tabela.addCell(c3);
        tabela.addCell(c4);
        tabela.addCell(c5);
        tabela.addCell(c6);
        tabela.addCell(c7);
        tabela.addCell(c8);
        tabela.addCell(c9);
        tabela.addCell(c10);
        tabela.addCell(c11);
        tabela.addCell(c12);
        tabela.setWidths(new float[]{(float)0.8,(float)1.8,7,1,1,1,1,(float)1.2,1,(float)1.2,1,2});
        
        List<Aluno> listaAluno = db.getAluno(codigo);
        List<ProvaAluno> listaProva = db.getListadeProva(codigo);
        String dados[] = new String[12];
        Iterator<Aluno> it = listaAluno.iterator();
        for(int i = 0;it.hasNext();i++){
            Aluno atual = it.next();
            dados[0] = (i+1)+"";
            dados[1] = atual.getMatricula();
            dados[2] = atual.getNome();
            dados[3] = "0";
            dados[4] = "0.0";
            dados[5] = "0";
            dados[6] = "0.0";
            dados[7] = "0";
            dados[8] = "0.0";
            dados[9] = "0";
            dados[10] = "0.0";
            dados[11] = "0.0";
            Iterator<ProvaAluno> itp = listaProva.iterator();
            while(itp.hasNext()){
                ProvaAluno pAtual = itp.next();
                if(pAtual.getCodigoAluno().equals(atual.getMatricula())){
                    dados[3] = pAtual.getLinguagem()+"";
                    dados[4] = ((float)(pAtual.getLinguagem() / pAtual.getQlin() * 10))+"";
                    dados[5] = pAtual.getMatematica()+"";
                    dados[6] = ((float)(pAtual.getMatematica() / pAtual.getQmat()* 10))+"";
                    dados[7] = pAtual.getNatureza()+"";
                    dados[8] = ((float)(pAtual.getNatureza() / pAtual.getQnat()* 10))+"";
                    dados[9] = pAtual.getHumana()+"";
                    dados[10] = ((float)(pAtual.getHumana() / pAtual.getQhum()* 10))+"";
                    int pontos = pAtual.getLinguagem() + pAtual.getMatematica() + pAtual.getNatureza() + pAtual.getHumana();
                    float media = pontos/pAtual.getQtotal();
                    if(media >= 0.5)
                        dados[11] = "1.0"+pAtual.getQtotal();
                    else
                        dados[11] = "0.5"+pAtual.getQtotal();
                }
            }
            PdfPCell d1 = new PdfPCell(new Paragraph(dados[0],fontnormal));
            PdfPCell d2 = new PdfPCell(new Paragraph(dados[1],fontnormal));
            PdfPCell d3 = new PdfPCell(new Paragraph(dados[2],fontnormal));
            PdfPCell d4 = new PdfPCell(new Paragraph(dados[3],fontnormal));
            PdfPCell d5 = new PdfPCell(new Paragraph(dados[4],fontnormal));
            PdfPCell d6 = new PdfPCell(new Paragraph(dados[5],fontnormal));
            PdfPCell d7 = new PdfPCell(new Paragraph(dados[6],fontnormal));
            PdfPCell d8 = new PdfPCell(new Paragraph(dados[7],fontnormal));
            PdfPCell d9 = new PdfPCell(new Paragraph(dados[8],fontnormal));
            PdfPCell d10 = new PdfPCell(new Paragraph(dados[9],fontnormal));
            PdfPCell d11 = new PdfPCell(new Paragraph(dados[10],fontnormal));
            PdfPCell d12 = new PdfPCell(new Paragraph(dados[11],fontnegrito));
            
            tabela.addCell(d1);
            tabela.addCell(d2);
            tabela.addCell(d3);
            tabela.addCell(d4);
            tabela.addCell(d5);
            tabela.addCell(d6);
            tabela.addCell(d7);
            tabela.addCell(d8);
            tabela.addCell(d9);
            tabela.addCell(d10);
            tabela.addCell(d11);
            tabela.addCell(d12);
        }
        
        document.add(tabela);
        
        
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
