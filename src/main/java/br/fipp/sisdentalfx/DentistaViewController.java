package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
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

public class DentistaViewController implements Initializable {

    @FXML
    public Button tfConf;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfnome;
    @FXML
    private TextField tfcro;
    @FXML
    private TextField tfFone;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button tfCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> tfId.requestFocus());
        MaskFieldUtil.foneField(tfFone);
        tfCancelar.setOnAction(e -> ((Control)e.getSource()).getScene().getWindow().hide());
        tfConf.setOnAction(e -> gravarDentista(e));
    }

    private void gravarDentista(javafx.event.ActionEvent e) {
        Dentista dentista = new Dentista(
                tfnome.getText(),
                Integer.parseInt(tfcro.getText()),
                tfFone.getText(),
                tfEmail.getText()
        );

        if (!new PessoaDAL().gravar(dentista)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao cadastrar dentista " + DB.getCon().getMensagemErro());
            alert.showAndWait();
        }
        ((Control) e.getSource()).getScene().getWindow().hide();
    }
}
