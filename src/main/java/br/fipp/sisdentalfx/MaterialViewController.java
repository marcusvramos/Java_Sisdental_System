package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.entidades.Material;
import br.fipp.sisdentalfx.db.util.DB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialViewController implements Initializable {
    public TextField tfId;
    public TextField tfValor;
    public TextField tfDescricao;
    public Button btnConfirmar;
    public Button btnCancelar;

    private void gravarMaterial(ActionEvent e) {

        Material material = new Material(
                tfDescricao.getText(),
                Double.parseDouble(tfValor.getText())
        );

        if(!new MaterialDAL().gravar(material)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao cadastrar material "+ DB.getCon().getMensagemErro());
            alert.showAndWait();
        }

        ((Control)e.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> tfDescricao.requestFocus());
        btnCancelar.setOnAction(e -> ((Control)e.getSource()).getScene().getWindow().hide());
        btnConfirmar.setOnAction(this::gravarMaterial);
    }
}
