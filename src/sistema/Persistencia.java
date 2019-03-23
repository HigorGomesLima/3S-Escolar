package sistema;

import objetos.Aluno;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Prova;
import objetos.Turma;

public class Persistencia {
    private Connection con;
    
    public Persistencia() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:bancodecorrecoes.db");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception  ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private ResultSet getRS(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    public List<Aluno> lerNotas(String nomeArquivo){
        List<Aluno> saida = new ArrayList<>();
        try{
            FileReader arq = new FileReader(nomeArquivo);
            BufferedReader lerArq = new BufferedReader(arq);
 
      String linha = lerArq.readLine();
        }catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
        }
        return saida;
    }
    
    public void adicionarAluno(String matricula,String nome,String turma) throws SQLException{
        String update = "INSERT INTO Aluno (matricula,nome,turma) VALUES ('"+matricula+"','"+nome+"','"+turma+"')";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
    }
    
    public void adicionarTurma(Turma novo) throws SQLException{
        String codigo = novo.getSerie() + novo.getTurma() + novo.getAno();
        String values = "'" + codigo + "','"+ novo.getSerie() + "','" + novo.getTurma() + "','"+ novo.getEnsino() + "','" + novo.getAno() + "'";
        String update = "INSERT INTO Turma(codigo,serie,turma,ensino,ano)VALUES("+values+")";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
    }
    
    public List<Aluno> ListarAluno() throws SQLException{
        List<Aluno> retorno = new ArrayList<>();
        ResultSet rs = getRS("SELECT * FROM Aluno");
        while(rs.next()){
            Aluno aux = new Aluno();
            aux.setMatricula(rs.getString("matricula"));
            aux.setNome(rs.getString("nome"));
            aux.setTurma(rs.getString("turma"));
            retorno.add(aux);
        }
        return retorno;
    }
    
    public void gerarCSVAluno(List<String> comando,String local) throws IOException{
        FileWriter arq = new FileWriter(local);
        PrintWriter gravarArq = new PrintWriter(arq);
        Iterator<String> it = comando.iterator();
        while(it.hasNext()){
            String aux = it.next();
            gravarArq.printf(aux+"%n");
        }
        arq.close();
    }
    
    public void adicionarProva(Prova novo) throws SQLException{
        String dados = "'" + novo.getCodigo() + "','" + novo.getCodigoAluno() + "','" +  novo.getBimestre() + "','" + 
                novo.getLinguagem() + "','" +  novo.getMatematica() + "','" + novo.getNatureza() + "','" +  novo.getHumana() + "'";
        String update = "INSERT INTO Prova (codigo,aluno,bimestre,linguagem,matematica,naturais,humanas) VALUES ("+dados+")";
        System.out.println(update);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
    }
    
    public List<String> lerCSV(File[] arquivo) throws FileNotFoundException, IOException{
        List<String> retorno = new ArrayList<>();
        for(int i = 0; i < arquivo.length; i++){
            System.out.println(arquivo[i].getName());
            FileReader arq = new FileReader(arquivo[i]);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while(linha != null){
                retorno.add(linha);
                linha = lerArq.readLine();
            }
            arq.close();
        }
        return retorno;
    }
    
}