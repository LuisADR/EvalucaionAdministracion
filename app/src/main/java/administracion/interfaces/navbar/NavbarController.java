package administracion.interfaces.navbar;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController extends SceneController implements Initializable {

    @FXML
    Pane paneHome, panePayback, paneNPV, paneCheck, paneDepreciation, paneMatrix;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void navigateHome() throws Exception {
        changeScene((Stage) paneHome.getScene().getWindow(), HOME);
    }

    public void navigatePayback() throws Exception {
        changeScene((Stage) panePayback.getScene().getWindow(), PAYBACK);
    }

    public void navigateNpv() throws Exception {
        changeScene((Stage) panePayback.getScene().getWindow(), NET_PRESENT_VALUE);
    }

    public void navigateDepreciation() throws Exception {
        changeScene((Stage) panePayback.getScene().getWindow(), DEPRECIATION);
    }

    public void navigateChecklist() throws Exception {
        changeScene((Stage) panePayback.getScene().getWindow(), CHECKLIST);
    }

    public void openMatrix() throws Exception{
        Desktop.getDesktop().open(new File("Project Screening Matrix.xlsx"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Abriendo Archivo");
        alert.setHeaderText("Por favor espere");
        alert.setContentText("En un momento se abrira su archivo");

        alert.showAndWait();
    }
}
