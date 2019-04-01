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
import objetos.ProvaAluno;
import objetos.Tipo;
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
        ResultSet rs = getRS("SELECT * FROM Aluno WHERE turma = '30032019' ORDER BY nome ASC");
        while(rs.next()){
            Aluno aux = new Aluno();
            aux.setMatricula(rs.getString("matricula"));
            aux.setNome(rs.getString("nome"));
            aux.setTurma(rs.getString("turma"));
            retorno.add(aux);
        }
        return retorno;
    }
    
    public List<ProvaAluno> getListadeProva(String turma) throws SQLException{
        List<ProvaAluno> retorno = new ArrayList<>();
        ResultSet rs = getRS("SELECT Prova.*,Aluno.nome,Tipo.linguagem AS ql,Tipo.matematica AS qm,Tipo.humana AS qh, Tipo.natureza AS qn, Tipo.total FROM Prova "
                + "INNER JOIN Aluno ON Prova.aluno = Aluno.matricula "
                + "INNER JOIN Turma ON Aluno.turma = Turma.codigo "
                + "INNER JOIN Tipo ON Prova.tipo = Tipo.codigo WHERE Turma.codigo = '"+turma+"'");
        while(rs.next()){
            ProvaAluno pa = new ProvaAluno();
            pa.setCodigoAluno(rs.getString("aluno"));
            pa.setNomeAluno(rs.getString("nome"));
            pa.setQlin(rs.getInt("ql"));
            pa.setLinguagem(rs.getInt("linguagem"));
            pa.setQmat(rs.getInt("qm"));
            pa.setMatematica(rs.getInt("matematica"));
            pa.setQhum(rs.getInt("qh"));
            pa.setHumana(rs.getInt("humanas"));
            pa.setQnat(rs.getInt("qn"));
            pa.setNatureza(rs.getInt("naturais"));
            pa.setQtotal(rs.getInt("total"));
            retorno.add(pa);
        }
        return retorno;
    }
    
    public List<Aluno> getAluno(String codigo) throws SQLException{
        List<Aluno> retorno = new ArrayList<>();
        ResultSet rs = getRS("SELECT * FROM Aluno WHERE turma='"+codigo+"' ORDER BY nome ASC");
        while(rs.next()){
            Aluno pa = new Aluno();
            pa.setMatricula(rs.getString("matricula"));
            pa.setNome(rs.getString("nome"));
            retorno.add(pa);
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
        String dados = "'" + novo.getCodigo() + "','" + novo.getCodigoAluno() + "','" +  novo.getTipo() + "','" + 
                novo.getLinguagem() + "','" +  novo.getMatematica() + "','" + novo.getNatureza() + "','" +  novo.getHumana() + "'";
        String update = "INSERT INTO Prova (codigo,aluno,tipo,linguagem,matematica,naturais,humanas) VALUES ("+dados+")";
        System.out.println(update);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
    }
    
    public String adicionarTipo(Tipo novo) throws SQLException{
        String dados = "'" + novo.getAno() + "','" + novo.getBimestre() + "','" +  novo.getTotal() + "','" + 
                novo.getLinguagem() + "','" +  novo.getMatematica() + "','" + novo.getNatureza() + "','" +  novo.getHumana() + "'";
        String update = "INSERT INTO Tipo (ano,bimestre,total,linguagem,matematica,humana,natureza) VALUES ("+dados+")";
        System.out.println(update);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
        String r = getRS("SELECT codigo FROM Tipo WHERE codigo = (SELECT MAX(codigo)  FROM Tipo)").getString("codigo");
        return r;
    }
    
    public void adicionarRelacaoTipoTurma(String codigoTipo, String codigoTurma) throws SQLException{
        String dados = "'" + codigoTurma + "','" +  codigoTipo + "','" + codigoTipo+codigoTurma + "'";
        String update = "INSERT INTO RTipoTurma (turma,tipo,nome) VALUES ("+dados+")";
        System.out.println(update);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(update);
    }
    
    public List<String> lerCSV(File[] arquivo) throws FileNotFoundException, IOException{
        List<String> retorno = new ArrayList<>();
        for(int i = 0; i < arquivo.length; i++){
            FileReader arq = new FileReader(arquivo[i]);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while(linha != null){
                retorno.add(linha);
                System.out.println(linha);
                linha = lerArq.readLine();
            }
            arq.close();
        }
        return retorno;
    }
    
    public List<Tipo> getTipos(String ano, String bimestre) throws SQLException{
        List<Tipo> retorno = new ArrayList<>();
        ResultSet rs = getRS("SELECT * FROM Tipo WHERE ano='"+ano+"' AND bimestre='"+bimestre+"'");
        while(rs.next()){
            Tipo novo = new Tipo();
            novo.setCodigo(rs.getString("codigo"));
            novo.setAno(rs.getString("ano"));
            novo.setBimestre(rs.getString("bimestre"));
            novo.setTotal(rs.getInt("total"));
            novo.setLinguagem(rs.getInt("linguagem"));
            novo.setMatematica(rs.getInt("matematica"));
            novo.setHumana(rs.getInt("humana"));
            novo.setNatureza(rs.getInt("natureza"));
            retorno.add(novo);
        }
        return retorno;
    }
    
    public Tipo getTipos(String codigo) throws SQLException{
        Tipo novo = new Tipo();
        ResultSet rs = getRS("SELECT * FROM Tipo WHERE codigo='"+codigo+"'");
        while(rs.next()){
            novo.setCodigo(rs.getString("codigo"));
            novo.setAno(rs.getString("ano"));
            novo.setBimestre(rs.getString("bimestre"));
            novo.setTotal(rs.getInt("total"));
            novo.setLinguagem(rs.getInt("linguagem"));
            novo.setMatematica(rs.getInt("matematica"));
            novo.setHumana(rs.getInt("humana"));
            novo.setNatureza(rs.getInt("natureza"));
        }
        return novo;
    }
    
    public List<Turma> getTurmas(String ano) throws SQLException{
        ResultSet rs = getRS("SELECT * FROM Turma WHERE ano='"+ano+"' ORDER BY codigo ASC");
        List<Turma> retorno = new ArrayList<>();
        while(rs.next()){
            Turma novo = new Turma();
            novo.setCodigo(rs.getString("codigo"));
            novo.setSerie(rs.getString("serie"));
            novo.setTurma(rs.getString("turma"));
            novo.setEnsino(rs.getString("ensino"));
            novo.setAno(ano);
            retorno.add(novo);
        }
        return retorno;
    }
    
}