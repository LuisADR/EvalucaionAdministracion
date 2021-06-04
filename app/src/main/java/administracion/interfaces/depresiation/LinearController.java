package administracion.interfaces.depresiation;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LinearController extends SceneController implements Initializable {

    @FXML
    BorderPane borderPane;

    @FXML
    VBox vBoxCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.borderPane.setLeft(getNavbar(DEPRECIATION));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DepreciationService.getInstance().setAccDep(0);
        int periods = DepreciationService.getInstance().getPeriod();
        vBoxCards.getChildren().clear();

        for (int i = 0; i <= periods; i++){
            vBoxCards.getChildren().add(getTableCard5(i));
        }
    }

    public void back() throws Exception {
        changeScene((Stage) vBoxCards.getScene().getWindow(), DEPRECIATION);
    }
}
