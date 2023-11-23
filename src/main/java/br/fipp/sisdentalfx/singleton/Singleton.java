package br.fipp.sisdentalfx.singleton;

import br.fipp.sisdentalfx.db.entidades.Consulta;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Paciente;

public class Singleton {
    private static Singleton instance;

    private String nomeUsuario;

    private Consulta consulta;

    private Paciente paciente;

    private Dentista dentista;
    private Singleton() {
        consulta = new Consulta();
        paciente = new Paciente();
        dentista = new Dentista();
        nomeUsuario = "";
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
