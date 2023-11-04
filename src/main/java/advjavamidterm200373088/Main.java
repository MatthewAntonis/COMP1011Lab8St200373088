package advjavamidterm200373088;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    static {
        testDatabaseConnection();
    }

    private static void testDatabaseConnection() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Database connected!");
                conn.close(); // Close the connection once done
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MidtermView.fxml"));
        primaryStage.setTitle("Code Crafters");
        root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/CodeLogo.png")));
        primaryStage.setScene(new Scene(root, 850, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
