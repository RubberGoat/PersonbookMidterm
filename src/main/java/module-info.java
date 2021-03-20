module PersonbookMT {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;

    opens PersonbookMT to javafx.fxml;
    exports PersonbookMT;
}