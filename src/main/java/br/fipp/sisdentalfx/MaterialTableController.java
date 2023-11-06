package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.MaterialDAL;
import br.fipp.sisdentalfx.db.entidades.Material;
import br.fipp.sisdentalfx.util.UIControl;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialTableController implements Initializable {
    public TableView <Material> tabela;
    public TableColumn <Material, Integer> colID;
    public TableColumn <Material, String> colDescricao;
    public TableColumn <Material, Double> colValor;
    public Button btClose;
    public Button btNovo;
    public TextField tfPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btClose.setOnAction(e->{((Button)e.getSource()).getScene().getWindow().hide();});
        this.preencherTabela("");
    }

    private void preencherTabela(String s) {
        List<Material> materiais = new MaterialDAL().get(s);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        this.tabela.setItems(FXCollections.observableArrayList(materiais));
    }
    public void onNovoMaterial(javafx.event.ActionEvent actionEvent) {
        UIControl.abreModal("material-view.fxml");
        preencherTabela("");
    }

    public void onFiltrar(KeyEvent keyEvent) {
        String filtro = tfPesquisa.getText().toUpperCase();
        preencherTabela("upper(mat_desc) like '%"+filtro+"%'");
    }

    public void onAlterar(javafx.event.ActionEvent actionEvent) {
    }

    public void onApagar(javafx.event.ActionEvent actionEvent) {
        if(tabela.getSelectionModel().getSelectedItem()!=null){
            Material material = tabela.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            System.out.println(material.getId());
            alert.setContentText("Posso apagar definitivamente o material " + material.getDescricao());
            if(alert.showAndWait().get() == ButtonType.OK){
                new MaterialDAL().apagar(material);
                preencherTabela("");
            }
        }
    }
}
