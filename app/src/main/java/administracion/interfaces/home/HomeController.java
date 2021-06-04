package administracion.interfaces.home;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends SceneController implements Initializable {
    private String proyect, evaluator;
    private HomeService homeService;

    @FXML
    TextField proyectTextField, evaluatorTextField;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeService = HomeService.getInstance();

        initEvaluator();
        initProyect();

        try {
            borderPane.setLeft(getNavbar(HOME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initEvaluator() {
        evaluatorTextField.setText("");

        if (homeService.getEvaluator() != null) {
            evaluatorTextField.setText(homeService.getEvaluator());
        }

        evaluatorTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                homeService.setEvaluator(evaluatorTextField.getText());
            }
        });
    }

    public void initProyect() {
        proyectTextField.setText("");

        if (homeService.getProyect() != null) {
            proyectTextField.setText(homeService.getProyect());
        }

        proyectTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                homeService.setProyect(proyectTextField.getText());
            }
        });
    }

}
