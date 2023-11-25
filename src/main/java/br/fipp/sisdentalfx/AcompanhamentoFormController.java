package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.ConsultaDAL;
import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Consulta;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Horario;
import br.fipp.sisdentalfx.db.entidades.Pessoa;
import br.fipp.sisdentalfx.db.util.DB;
import br.fipp.sisdentalfx.singleton.Singleton;
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AcompanhamentoFormController implements Initializable {

    @FXML
    public Button tfConf;
    public javafx.scene.control.TextArea tfRelato;
    @FXML
    private Button tfCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Horario horario = Singleton.getInstance().getHorario();
            String sql = "pac_id = '" + horario.getPaciente().getId() + "' and con_horario = " + horario.getHora();
            Consulta consulta = (Consulta) new ConsultaDAL().get(sql).get(0);
            tfRelato.setText(consulta.getRelato());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao carregar relato");
            alert.showAndWait();
        }

    }

    public void onCancelar(javafx.event.ActionEvent actionEvent) {
        tfCancelar.getScene().getWindow().hide();
    }

    public void onConfirmar(javafx.event.ActionEvent actionEvent){
        Horario horario = Singleton.getInstance().getHorario();
        String sql = "pac_id = '" + horario.getPaciente().getId() + "' and con_horario = " + horario.getHora();
        Consulta consulta = (Consulta) new ConsultaDAL().get(sql).get(0);
        consulta.setRelato(tfRelato.getText());
        if(new ConsultaDAL().alterar(consulta)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Relato salvo com sucesso");
            alert.showAndWait();
            tfCancelar.getScene().getWindow().hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar relato");
            alert.showAndWait();
        }
    }
}
