package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Material;
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
            dal.gravar(new Dentista("Clemente",123,"1899999","cle@email.com"));

        }
        else
            System.out.println(DB.getCon().getMensagemErro());
    }
}
