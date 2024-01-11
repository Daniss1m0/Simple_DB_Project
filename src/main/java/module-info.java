module simple_db_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens Controllers to javafx.fxml;
    exports Controllers;
    opens ClientFiles to javafx.fxml;
    exports ClientFiles;
}