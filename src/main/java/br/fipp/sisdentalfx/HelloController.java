package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Dentista;
import br.fipp.sisdentalfx.db.entidades.Pessoa;
import br.fipp.sisdentalfx.db.entidades.Usuario;
import br.fipp.sisdentalfx.singleton.Singleton;
import br.fipp.sisdentalfx.util.UIControl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public static BorderPane staticpainel;
    public BorderPane painel;
    public Button btHome;
    public Button btClose;
    public Label lbAcesso;
    public Button btLogout;

    private boolean telaInicial = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticpainel=painel;
        btHome.setTooltip(new Tooltip("Ir para o início"));
        btClose.setTooltip(new Tooltip("Fechar o sistema"));
        btHome.setDisable(true);
        btLogout.setDisable(true);

        lbAcesso.setOnMouseClicked(e->{
            try {
                btHome.setDisable(false);
                onHome(null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public boolean logar(String usuariologin, String senha) {
        Singleton singleton = Singleton.getInstance();
        List<Pessoa> usuarios= new PessoaDAL().get("uso_nome like '%"+usuariologin+"%'", new Usuario());
        Usuario usuario;
        boolean logado = false;
        if(usuarios.size() > 0)
        {
            usuario=(Usuario) usuarios.get(0);
            if (usuario.getSenha().equals(senha)) {
                singleton.setDentista(new Dentista(usuario.getId(),usuario.getNome(), 0, "", ""));
                System.out.println("senha bateu");
                UIControl.usuario=usuario.getNome();
                UIControl.nivel=usuario.getNivel();
                // 1 ADM
                // 2 Secretária
                // 3 dentista
                logado = true;
                btLogout.setDisable(false);
                return logado;
            }
        }

        return false;

    }

    private void carregarTelaInicial() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        staticpainel.setCenter(root);
        telaInicial = true; // Define o indicador de tela inicial como verdadeiro ao retornar para a tela inicial
    }



    public void onHome(ActionEvent actionEvent) throws IOException {
        if (!telaInicial) {
            // Se não estiver na tela inicial, retorne para a tela inicial
            carregarTelaInicial();
            return;
        }
        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setTitle("Login");
        usernameDialog.setHeaderText(null);
        usernameDialog.setContentText("Digite o nome de usuário:");

        Optional<String> usernameResult = usernameDialog.showAndWait();
        if (usernameResult.isPresent()) {
            String username = usernameResult.get();
            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Login");
            passwordDialog.setHeaderText(null);
            passwordDialog.setContentText("Digite a senha:");

            Optional<String> passwordResult = passwordDialog.showAndWait();
            if (passwordResult.isPresent()) {
                boolean logado = logar(usernameResult.get(), passwordResult.get());
                if (logado) {
                    System.out.println("Usuário logado");
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
                    fxmlLoader.load();
                    staticpainel.setCenter(fxmlLoader.getRoot());
                    telaInicial = false;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Usuário ou senha inválidos");
                    alert.showAndWait();
                }
            }
        }

    }
    public void onClose(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Finalizar o sistema?");
        if(alert.showAndWait().get() == ButtonType.OK)
            Platform.exit();
    }

    public void onHelp(ActionEvent actionEvent) {
        File file = new File("help/exemplo.html");
        UIControl.abreHelp(file.toURI().toString());
    }

    public void onLogout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja realmente sair?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            UIControl.usuario = null;
            UIControl.nivel = 0;
            btLogout.setDisable(true);
            try {
                telaInicial = true;
                onHome(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}