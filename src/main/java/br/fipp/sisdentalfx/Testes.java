package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.ConsultaDAL;
import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.*;
import br.fipp.sisdentalfx.db.util.DB;

import java.time.LocalDate;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        /* conexão com o banco*/
        if(DB.conectar()) {
            Dentista dentista = (Dentista) new PessoaDAL().get(1, new Dentista());
            List <Consulta> consulta = new ConsultaDAL().get(dentista, LocalDate.now());
            System.out.println(consulta.get(0).getPaciente().getNome());
            List<Consulta.ItemProc> procedimentos = consulta.get(0).getProcedimentos();

            for(Consulta.ItemProc item : procedimentos){
                System.out.println(item.quant() + " " + item.procedimento().getDescricao());
            }
        }
        else
            System.out.println(DB.getCon().getMensagemErro());
    }
}
