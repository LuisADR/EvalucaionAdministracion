package administracion.interfaces;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        SceneController sc = new SceneController() ;

        Parent root = FXMLLoader.load(getClass().getResource(sc.HOME));

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
