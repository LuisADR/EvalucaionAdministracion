package administracion.interfaces.payback;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PaybackFinishController extends SceneController implements Initializable {

    @FXML
    VBox vBoxCards;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            borderPane.setLeft(getNavbar(PAYBACK));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int periods = PaybackService.getPaybackService().getPeriods();

        vBoxCards.getChildren().clear();
        for (int i = 0; i <= periods; i++) {
            vBoxCards.getChildren().add(getTableCard2(i));
        }
    }

    public void newPayback() throws Exception {
        changeScene((Stage) vBoxCards.getScene().getWindow(), PAYBACK);
    }
}
