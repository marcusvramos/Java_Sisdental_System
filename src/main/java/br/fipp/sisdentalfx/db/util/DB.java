package br.fipp.sisdentalfx.db.util;

public class DB
{
    static private Conexao con=new Conexao();
    static public boolean conectar()
    {
        return con.conectar("jdbc:postgresql://localhost:5432/", 
                "sisdentaldb", "postgres", "postgres");
        
    }
    static public Conexao getCon()
    {
        return con;
    }

    private DB() {
    }
    
}
