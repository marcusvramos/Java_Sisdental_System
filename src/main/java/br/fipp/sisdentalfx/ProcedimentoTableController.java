package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.dals.PessoaDAL;
import br.fipp.sisdentalfx.db.dals.ProcedimentoDAL;
import br.fipp.sisdentalfx.db.entidades.Procedimento;
import br.fipp.sisdentalfx.util.UIControl;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProcedimentoTableController implements Initializable {
    public TableView tabela;
    public TableColumn colID;
    public TableColumn colDescricao;
    public TableColumn colTempo;
    public TableColumn colValor;
    public Button btClose;
    public Button btNovo;
    public TextField tfPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btClose.setOnAction(e->{((Button)e.getSource()).getScene().getWindow().hide();});
        this.preencherTabela("");
    }

    private void preencherTabela(String s) {
        List<Procedimento> procedimentos = new ProcedimentoDAL().get("");
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colTempo.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.tabela.setItems(FXCollections.observableArrayList(procedimentos));
    }
    public void onNovoProcedimento(javafx.event.ActionEvent actionEvent) {
        UIControl.abreModal("procedimento-view.fxml");
        preencherTabela("");
    }

    public void onFiltrar(KeyEvent keyEvent) {
        String filtro = tfPesquisa.getText().toUpperCase();
        preencherTabela("upper(pro_desc) like '%"+filtro+"%'");
    }

    public void onAlterar(javafx.event.ActionEvent actionEvent) {
    }

    public void onApagar(javafx.event.ActionEvent actionEvent) {
        if(tabela.getSelectionModel().getSelectedItem()!=null){
            Procedimento procedimento = (Procedimento) tabela.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Posso apagar definitivamente o procedimento " + procedimento.getDescricao());
            if(alert.showAndWait().get() == ButtonType.OK){
                new ProcedimentoDAL().apagar(procedimento);
                preencherTabela("");
            }
        }
    }
}
