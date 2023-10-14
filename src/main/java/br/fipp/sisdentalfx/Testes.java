package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.entidades.Material;
import br.fipp.sisdentalfx.db.util.DB;

import java.util.List;

public class Testes {
    public static void main(String[] args) {
        if(DB.conectar()) {
            MaterialDAL dal = new MaterialDAL();
            dal.gravar(new Material("mascara",5.50));
            List<Material> lista = dal.get("");
            for (Material material : lista)
                System.out.println(material);
        }
        else
            System.out.println(DB.getCon().getMensagemErro());
    }
}
