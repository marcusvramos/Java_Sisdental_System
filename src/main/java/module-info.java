module br.fipp.sisdentalfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.fipp.sisdentalfx to javafx.fxml;
    exports br.fipp.sisdentalfx;
}