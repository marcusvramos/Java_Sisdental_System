package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.dals.ProcedimentoDAL;
import br.fipp.sisdentalfx.db.entidades.Paciente;
import br.fipp.sisdentalfx.db.entidades.Procedimento;
import br.fipp.sisdentalfx.db.util.DB;
import br.fipp.sisdentalfx.util.MaskFieldUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class ProcedimentoViewController implements Initializable {
    public TextField tfId;
    public TextField tfValor;
    public TextField tfTempo;
    public TextField tfDescricao;
    public Button btnConfirmar;
    public Button btnCancelar;

    private void gravarProcedimento(ActionEvent e) {

        Procedimento procedimento = new Procedimento(
                tfDescricao.getText(),
                Integer.parseInt(tfTempo.getText()),
                Double.parseDouble(tfValor.getText())
        );

        if(!new ProcedimentoDAL().gravar(procedimento)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao cadastrar paciente "+ DB.getCon().getMensagemErro());
            alert.showAndWait();
        }

        ((Control)e.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> tfDescricao.requestFocus());
        btnCancelar.setOnAction(e -> ((Control)e.getSource()).getScene().getWindow().hide());
        btnConfirmar.setOnAction(this::gravarProcedimento);
    }
}
