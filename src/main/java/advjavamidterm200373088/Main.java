package advjavamidterm200373088;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MidtermView.fxml"));
        primaryStage.setTitle("Code Crafters");
        root.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/CodeLogo.png")));
        primaryStage.setScene(new Scene(root, 450, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
