module com.example.advjavamidterm200373088 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens advjavamidterm200373088 to javafx.fxml;
    exports advjavamidterm200373088;
}