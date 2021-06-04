package administracion.interfaces.depresiation;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MACRSController extends SceneController implements Initializable {

    private static final double[] CAT_3 = {33.33, 44.45, 14.81, 7.41};
    private static final double[] CAT_5 = {20.0, 32.0, 19.20, 11.52, 11.52, 5.76};
    private static final double[] CAT_7 = {14.29, 24.49, 17.49, 12.49, 8.93, 8.92, 8.93, 4.46};
    private static final double[] CAT_10 = {10.00, 18.00, 14.40, 11.52, 9.22, 7.37, 6.55, 6.55, 6.56, 6.55, 3.28};

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

        double[] interest;

        switch (DepreciationService.getInstance().getCat()) {
            case 0:
                interest = CAT_3;
                break;
            case 1:
                interest = CAT_5;
                break;
            case 2:
                interest = CAT_7;
                break;
            default:
                interest = CAT_10;
                break;
        }

        for (int i = 0; i < interest.length; i++) {
            vBoxCards.getChildren().add(getTableCard6(i, interest[i]));
        }
    }

    public void back() throws Exception {
        changeScene((Stage) vBoxCards.getScene().getWindow(), DEPRECIATION);
    }
}
