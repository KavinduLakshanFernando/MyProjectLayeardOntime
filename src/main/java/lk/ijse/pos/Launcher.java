package lk.ijse.pos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LauncherWrapper.class.getResource("/view/LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("IJSE");
        stage.setScene(scene);
        stage.show();
    }
}
