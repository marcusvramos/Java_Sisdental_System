package br.fipp.sisdentalfx;

import br.fipp.sisdentalfx.db.util.DB;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdmViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onRelatorios(MouseEvent mouseEvent) {
        gerarRelatorio("select * from paciente", "src/Relatorios/rel_pacientes.jasper", "Relatório de Pacientes");
    }

    public void onCadastros(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agendamento-view.fxml"));
        fxmlLoader.load();
        HelloController.staticpainel.setCenter(fxmlLoader.getRoot());
    }

    private void gerarRelatorio(String sql, String relat, String titulotela)
    {
        try {  //sql para obter os dados para o relatorio
            ResultSet rs = DB.getCon().consultar(sql);
            //implementação da interface JRDataSource para DataSource ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            //preenchendo e chamando o relatório
            String jasperPrint = JasperFillManager.fillReportToFile(relat, null, jrRS);
            JasperViewer viewer = new JasperViewer(jasperPrint, false, false);

            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
            viewer.setTitle(titulotela);
            viewer.setVisible(true);
        } catch (JRException erro) {
            System.out.println(erro);
        }
    }
}
