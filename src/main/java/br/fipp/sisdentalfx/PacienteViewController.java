package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.entidades.Paciente;
import br.fipp.sisdentalfx.db.entidades.Pessoa;
import br.fipp.sisdentalfx.db.util.DB;
import br.fipp.sisdentalfx.util.MaskFieldUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PacienteViewController implements Initializable {

    @FXML
    private TextField tfBairro;

    @FXML
    private Button tfCancelar;

    @FXML
    private TextField tfCep;

    @FXML
    private TextField tfCidade;

    @FXML
    private Button tfConfirmar;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextArea tfHistorico;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfRua;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfUf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> tfCpf.requestFocus());
        MaskFieldUtil.cpfField(tfCpf);
        MaskFieldUtil.cepField(tfCep);
        MaskFieldUtil.foneField(tfTelefone);
        tfCancelar.setOnAction(e -> ((Control)e.getSource()).getScene().getWindow().hide());
        tfConfirmar.setOnAction(e -> gravarPaciente(e));

        tfCep.setOnKeyTyped(e -> {
            if(tfCep.getText().length() == 9){
                Task task = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        String dados = consultaCep(tfCep.getText(), "json");
                        JSONObject json = new JSONObject(dados);
                        tfBairro.setText(json.getString("bairro"));
                        tfCidade.setText(json.getString("localidade"));
                        tfUf.setText(json.getString("uf"));
                        tfRua.setText(json.getString("logradouro"));
                        Platform.runLater(() -> tfNumero.requestFocus());
                        return null;
                    }
                };
                new Thread(task).start();
            }
        });
    }

    private String consultaCep(String cep, String formato)
    {
        StringBuffer dados = new StringBuffer();
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ cep + "/"+formato+"/");
            URLConnection con = url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";

            while (null != (s = br.readLine()))
                dados.append(s);

            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dados.toString();
    }

    private void gravarPaciente(ActionEvent e) {
        // validar antes
        Paciente paciente = new Paciente(
                tfNome.getText(),
                tfCpf.getText(),
                tfCep.getText(),
                tfRua.getText(),
                tfNumero.getText(),
                tfBairro.getText(),
                tfCidade.getText(),
                tfUf.getText(),
                tfTelefone.getText(),
                tfEmail.getText(),
                tfHistorico.getText()
        );

        if(!new PessoaDAL().gravar(paciente)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao cadastrar paciente "+ DB.getCon().getMensagemErro());
            alert.showAndWait();
        }

        ((Control)e.getSource()).getScene().getWindow().hide();
    }
}
