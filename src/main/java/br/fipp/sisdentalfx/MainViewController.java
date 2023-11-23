package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.util.UIControl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public ImageView opADM;
    public ImageView opSecretaria;
    public ImageView OpDentista;
    public ImageView opConfig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(UIControl.nivel==3) {
            OpDentista.setDisable(false);
            OpDentista.setOpacity(1);
        }
        if(UIControl.nivel==2) {
            opSecretaria.setDisable(false);
            opSecretaria.setOpacity(1);
        }
        if(UIControl.nivel==1) { // adm
            opSecretaria.setDisable(false);
            opSecretaria.setOpacity(1);
            opADM.setDisable(false);
            opADM.setOpacity(1);
            opConfig.setDisable(false);
            opConfig.setOpacity(1);
        }
    }
    public void onAdm(MouseEvent mouseEvent) throws IOException {
        UIControl.trocaPainel("adm-view.fxml");
        //Platform.runLater(()->{
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adm-view.fxml"));
//        fxmlLoader.load();
//        HelloController.staticpainel.setCenter(fxmlLoader.getRoot());
        //});
    }

    public void onSecretaria(MouseEvent mouseEvent) throws IOException {
        UIControl.trocaPainel("agendamento-view.fxml");
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agendamento-view.fxml"));
//        fxmlLoader.load();
//        HelloController.staticpainel.setCenter(fxmlLoader.getRoot());
    }

    public void onDentista(MouseEvent mouseEvent) {
        UIControl.trocaPainel("acompanhamento-view.fxml");
    }

    public void onConfig(MouseEvent mouseEvent) {
    }


    public void onMouseEntered(MouseEvent mouseEvent) {
        ((ImageView)mouseEvent.getSource()).setEffect(new InnerShadow());

    }

    public void onMouseExited(MouseEvent mouseEvent) {
        ((ImageView)mouseEvent.getSource()).setEffect(null);
    }


}
