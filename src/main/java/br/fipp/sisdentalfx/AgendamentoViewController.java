package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.util.UIControl;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.effect.BoxBlur;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AgendamentoViewController implements Initializable {
    public DatePicker dpDiaConsulta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dpDiaConsulta.setValue(LocalDate.now());
    }

    public void onPaciente(ActionEvent actionEvent) throws IOException {
        BoxBlur bb = new BoxBlur(15,15,10);
        dpDiaConsulta.getScene().getRoot().setEffect(bb); // aplicando efeito borrado no painel
        UIControl.abreModal("paciente-table-view.fxml");
        dpDiaConsulta.getScene().getRoot().setEffect(null);
    }

    public void onMedico(ActionEvent actionEvent) {
    }

    public void onMaterial(ActionEvent actionEvent) {
        BoxBlur bb = new BoxBlur(15,15,10);
        dpDiaConsulta.getScene().getRoot().setEffect(bb);
        UIControl.abreModal("material-table-view.fxml");
        dpDiaConsulta.getScene().getRoot().setEffect(null);
    }

    public void onProcedimento(ActionEvent actionEvent) {
        BoxBlur bb = new BoxBlur(15,15,10);
        dpDiaConsulta.getScene().getRoot().setEffect(bb);
        UIControl.abreModal("procedimento-table-view.fxml");
        dpDiaConsulta.getScene().getRoot().setEffect(null);
    }


}
