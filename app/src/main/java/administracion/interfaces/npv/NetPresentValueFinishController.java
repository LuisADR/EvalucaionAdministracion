package administracion.interfaces.npv;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NetPresentValueFinishController extends SceneController implements Initializable {

    @FXML
    VBox vBoxCards;

    @FXML
    Text netPresentValue;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String value;

        try {
            borderPane.setLeft(getNavbar(NET_PRESENT_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int periods = NetPresentValueService.getNetPresentValueService().getPeriods();
        NetPresentValueService.getNetPresentValueService().setNpv(0);

        vBoxCards.getChildren().clear();
        for (int i = 0; i <= periods; i++) {
            vBoxCards.getChildren().add(getTableCard4(i));
        }

        double npv = NetPresentValueService.getNetPresentValueService().getNpv();

        if (npv < 0) {
            value = String.format("%.2f", npv);
            netPresentValue.setText("-$" + value);
        } else {
            value = String.format("%.2f", npv);
            netPresentValue.setText("$" + value);
        }
    }

    public void newNpv() throws Exception {
        changeScene((Stage) vBoxCards.getScene().getWindow(), NET_PRESENT_VALUE);
    }
}
