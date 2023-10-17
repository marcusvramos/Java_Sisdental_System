package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Material;
import br.fipp.sisdentalfx.db.entidades.Paciente;
import br.fipp.sisdentalfx.db.entidades.Pessoa;
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

            PessoaDAL dal=new PessoaDAL();
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
            List<Pessoa> lista = dal.get("", new Paciente());
            //System.out.println(dal.get(1, new Paciente()).getNome());
            for(Pessoa p : lista){
                System.out.println(((Paciente)p).getCpf());
            }

        }
        else
            System.out.println(DB.getCon().getMensagemErro());
    }
}
