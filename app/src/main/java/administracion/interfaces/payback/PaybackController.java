package administracion.interfaces.payback;

import administracion.interfaces.SceneController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PaybackController extends SceneController implements Initializable {
    private int period;
    private double principal, interest;
    private PaybackService paybackService;

    private static final String[] PERIODS = {
            "1 Periodo", "2 Periodos", "3 Periodos", "4 Periodos", "5 Periodos",
            "6 Periodos", "7 Periodos", "8 Periodos", "9 Periodos", "10 Periodos"
    };

    @FXML
    BorderPane borderPane;

    @FXML
    VBox vBoxCards;

    @FXML
    TextField principalTextField, interestTextField;

    @FXML
    ChoiceBox periodChoiceBox;

    @FXML
    Text errorPeriod, errorPrincipal, errorInterest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paybackService = PaybackService.getPaybackService();

        try {
            borderPane.setLeft(getNavbar(PAYBACK));
        } catch (Exception e) {
            e.printStackTrace();
        }

        addPeriodListener();
        addPrincipalListener();
        addInterest();
    }

    public void addPeriodListener() {
        periodChoiceBox.setItems(FXCollections.observableArrayList(PERIODS));

        if (paybackService.getPeriods() == -1) {
            period = -1;
        } else {
            period = paybackService.getPeriods();
            periodChoiceBox.setValue((period+1) + " Periodos");

            for (int i = 0; i <= period; i++){
                vBoxCards.getChildren().add(getTableCard1(i));
            }
        }

        periodChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (period == -1) {
                errorPeriod.setVisible(false);
                period = temporal;

                vBoxCards.getChildren().clear();
                for (int i = 0; i <= period; i++){
                    vBoxCards.getChildren().add(getTableCard1(i));
                }

            } else {
                if (period < temporal) {
                    for (int i = period + 1; i <= temporal; i++){
                        vBoxCards.getChildren().add(getTableCard1(i));
                    }
                } else {
                    vBoxCards.getChildren().remove(temporal + 1, period + 1);
                }
                period = temporal;
            }

            paybackService.setPeriods(period);
        });
    }

    public void addPrincipalListener() {
        if (paybackService.getPrincipal() == 0) {
            principal = -1;
        } else {
            principal = paybackService.getPrincipal();
            String value = String.format("%.2f", principal);
            principalTextField.setText("$" + value);
        }

        principalTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = principalTextField.getText().split(" ");

            if (!newValue) {
                errorPrincipal.setVisible(false);
                try {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal > 0) {
                        principal = temporal;

                        PaybackService.getPaybackService().setPrincipal(-temporal);
                        String value2 = String.format("%.2f", principal);
                        principalTextField.setText("$" + value2);
                    } else {
                        errorPrincipal.setVisible(true);
                        principalTextField.setText("");
                        principal = -1.0;
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorPrincipal.setVisible(true);
                    principalTextField.setText("");
                    principal = -1.0;
                }

            } else {
                if (principal == -1) {
                    principalTextField.setText("");
                } else {
                    String value2 = String.format("%.2f", principal);
                    principalTextField.setText(value2);
                }
            }
        });
    }

    public void addInterest() {
        if (paybackService.getInterest() == 0) {
            interest = -1;
        } else {
            interest = paybackService.getInterest();
            String value = String.format("%.2f", interest);
            interestTextField.setText(value + "%");
        }

        interestTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = interestTextField.getText().split(" ");

            if (!newValue) {
                errorInterest.setVisible(false);
                try {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal < 100) {
                        interest = temporal;
                        String value2 = String.format("%.2f", interest);
                        interestTextField.setText(value2 + "%");
                        PaybackService.getPaybackService().setInterest(temporal);
                    } else {
                        errorInterest.setVisible(true);
                        interestTextField.setText("");
                        interest = -1.0;
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorInterest.setVisible(true);
                    interestTextField.setText("");
                    interest = -1.0;
                }

            } else {
                if (interest == -1) {
                    interestTextField.setText("");
                } else {
                    String value2 = String.format("%.2f", interest);
                    interestTextField.setText(value2);
                }
            }
        });
    }

    public void calculate() throws Exception {
        if (period == -1) {
            errorPeriod.setVisible(true);
        } else {
            if (interest == -1) {
                errorInterest.setVisible(true);
            } else {
                if (principal == -1) {
                    errorPrincipal.setVisible(true);
                } else {
                    changeScene((Stage) interestTextField.getScene().getWindow(), PAYBACK_FINISH);
                }
            }
        }
    }

    public void cleanData() {
        PaybackService.setPaybackService(new PaybackService());
        principal = -1;
        interest = -1;
        period = -1;

        principalTextField.setText("");
        interestTextField.setText("");
        periodChoiceBox.setValue(null);
    }
}
