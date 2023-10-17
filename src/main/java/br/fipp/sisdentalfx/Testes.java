package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.dals.ProcedimentoDAL;
import br.fipp.sisdentalfx.db.entidades.*;
import br.fipp.sisdentalfx.db.util.DB;

import java.util.List;

public class Testes {
    public static void main(String[] args) {
        if(DB.conectar()) {
//            MaterialDAL dal = new MaterialDAL();
//            dal.gravar(new Material("agulhas",2.20));
//            List<Material> lista = dal.get("");
//            for (Material material : lista)
//                System.out.println(material);

            //PessoaDAL dal=new PessoaDAL();
//            if(!dal.gravar(new Dentista("Teodoro",123,"18996807124","joelton@email.com"))) {
//                DB.getCon().getMensagemErro();
//            }
//            if(!dal.gravar(new Paciente("Gustavo Lima",
//                    "40380352800",
//                    "19023290",
//                    "Rua Paulistas",
//                    "1234",
//                    "Vila Geni",
//                    "Presidente Prudente",
//                    "SP",
//                    "18996807124",
//                    "marcusramos651@gmail.com",
//                    "")
//            )) {
//                DB.getCon().getMensagemErro();
//            }
//            List<Pessoa> lista = dal.get("", new Paciente());
//            //System.out.println(dal.get(1, new Paciente()).getNome());
//            for(Pessoa p : lista){
//                System.out.println(((Paciente)p).getCpf());
//            }
            ProcedimentoDAL dal = new ProcedimentoDAL();
            if(!dal.gravar(new Procedimento("Clareamento Dental", 50, 250))){
                DB.getCon().getMensagemErro();
            }
            List<Procedimento> lista = dal.get("");
            for(Procedimento p : lista)
                System.out.println(p.getDescricao());

        }
        else
            System.out.println(DB.getCon().getMensagemErro());
    }
}
