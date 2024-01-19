module simple_db_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires osdt.cert;
    requires ojdbc8;


    opens Controllers to javafx.fxml;
    exports Controllers;
    opens ClientFiles to javafx.fxml;
    exports ClientFiles;
    opens TableContentClass to javafx.base;
    exports TableContentClass;
}