package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Consulta;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Pessoa;
import br.fipp.sisdentalfx.db.util.DB;
import br.fipp.sisdentalfx.util.MaskFieldUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AcompanhamentoFormController implements Initializable {

    @FXML
    public Button tfConf;
    public TextField tfRelato;
    @FXML
    private Button tfCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
