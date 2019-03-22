package sistema;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objetos.Aluno;
import objetos.Ensino;
import objetos.Prova;
import objetos.Turma;


public class Agenda {
    private final Persistencia db;
    
    public Agenda(){
        db = new Persistencia();
    }
    
    public void criarCSV(String local) throws SQLException, IOException{
            List<Aluno> lista = db.ListarAluno();
            List<String> linha = new ArrayList<>();
            Iterator<Aluno> it = lista.iterator();
            while(it.hasNext()){
                Aluno a = it.next();
                String nome = a.getNome();
                String nomeD[] = nome.split(" ");
                String turma = a.getTurma().substring(0, 1) + "-" + a.getTurma().substring(1, 4);
                nome = nome.replace(nomeD[0]+" ", "");
                String aux = a.getMatricula() + "," + nomeD[0] + "," + nome + "," + turma;
                linha.add(aux);
            }
            db.gerarCSVAluno(linha,local);
    }
    
    public void criarTurma(Turma nova,String texto) throws SQLException{
                db.adicionarTurma(nova);
                String codigo = nova.getSerie() + nova.getTurma() + nova.getAno();
                String linha[] = texto.split("\\n");
                for(int i = 0; i < linha.length; i++){
                    String dados[] = linha[i].split("	");
                    db.adicionarAluno(dados[0], dados[1], codigo);
                }
                
    }
    
    public void correcao(File[] arquivo,Ensino tipo) throws IOException{
        int q1 = 10;
        int q2 = q1 + tipo.getLinguagem();
        int q3 = q2 + tipo.getMatematica();
        int q4 = q3 + tipo.getNatureza();
        int q5 = q4 + tipo.getHumana();
        int total = tipo.getTotal();
        String ano = tipo.getAno();
        List<String> texto = db.lerCSV(arquivo);
        for(Iterator<String> it = texto.iterator();it.hasNext();){
            String[] linha = it.next().split(",");
            Prova novo = new Prova();
            for(int i = q1;i < q1+total;i++){
                
            }
        }
    }
}
