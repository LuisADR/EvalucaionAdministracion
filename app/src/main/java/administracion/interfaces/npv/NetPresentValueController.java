package administracion.interfaces.npv;

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
import java.util.Arrays;
import java.util.ResourceBundle;

public class NetPresentValueController extends SceneController implements Initializable {

    private int period, periodSalvageValue;
    private double principal, interest, tax, inflation, salvageValue;

    private NetPresentValueService npvService;

    private static final String[] PERIODS = {
            "1 Periodo", "2 Periodos", "3 Periodos", "4 Periodos", "5 Periodos",
            "6 Periodos", "7 Periodos", "8 Periodos", "9 Periodos", "10 Periodos"
    };

    @FXML
    ChoiceBox periodChoiceBox, salvageValueChoiceBox;

    @FXML
    BorderPane borderPane;

    @FXML
    TextField principalTextField, interestTextField, taxTextField,
            inflationTextField, salvageValueTextField;

    @FXML
    Text errorPeriod, errorPrincipal, errorInterest, errorTax,
            errorInflation, errorSalvageValue;

    @FXML
    VBox vBoxCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.npvService = NetPresentValueService.getNetPresentValueService();

        addPeriodListener();
        addPrincipalListener();
        addInterestListener();
        addTaxListener();
        addInflationListener();
        addSalvageValue();
        addPeriodSalvageValue();

        try {
            borderPane.setLeft(getNavbar(NET_PRESENT_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPeriodListener() {
        periodChoiceBox.setItems(FXCollections.observableArrayList(PERIODS));

        if (npvService.getPeriods() == -1) {
            period = -1;
        } else {
            period = npvService.getPeriods();
            periodChoiceBox.setValue((period+1) + " Periodos");

            for (int i = 0; i <= period; i++){
                vBoxCards.getChildren().add(getTableCard3(i));
            }
        }

        periodChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            errorPeriod.setVisible(false);
            int temporal = (Integer) newValue;

            if (period < temporal) {
                for (int i = period + 1; i <= temporal; i++){
                    vBoxCards.getChildren().add(getTableCard3(i));
                }

                salvageValueChoiceBox.setItems(FXCollections.observableArrayList(
                        Arrays.copyOf(PERIODS, temporal + 1)
                ));

            } else {
                vBoxCards.getChildren().remove(temporal + 1, period + 1);

                salvageValueChoiceBox.setItems(FXCollections.observableArrayList(
                        Arrays.copyOf(PERIODS, temporal +1)
                ));
            }

            npvService.setPeriodSalvageValue(-1);
            periodSalvageValue = -1;
            period = temporal;
            npvService.setPeriods(period);
        });
    }

    public void addPrincipalListener() {
        if (npvService.getPrincipal() == 0) {
            principal = -1;
        } else {
            principal = npvService.getPrincipal();
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

                        npvService.setPrincipal(principal);
                        String value2 = String.format("%.2f", principal);
                        principalTextField.setText("$" + value2);
                    } else {
                        errorPrincipal.setVisible(true);
                        principalTextField.setText("");
                        principal = -1.0;
                        npvService.setPrincipal(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorPrincipal.setVisible(true);
                    principalTextField.setText("");
                    principal = -1.0;
                    npvService.setPrincipal(0);
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

    public void addInterestListener() {

        if (npvService.getInterest() == 0) {
            interest = -1;
        } else {
            interest = npvService.getInterest();
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

                        npvService.setInterest(interest);
                        String value2 = String.format("%.2f", interest);
                        interestTextField.setText(value2 + "%");
                    } else {
                        errorInterest.setVisible(true);
                        interestTextField.setText("");
                        interest = -1.0;
                        npvService.setInterest(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorInterest.setVisible(true);
                    interestTextField.setText("");
                    interest = -1.0;
                    npvService.setInterest(0);
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

    public void addTaxListener() {
        if (npvService.getImpuestos() == 0) {
            tax = -1;
        } else {
            tax = npvService.getImpuestos();
            String value = String.format("%.2f", tax);
            taxTextField.setText(value + "%");
        }

        taxTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = taxTextField.getText().split(" ");

            if (!newValue) {
                errorTax.setVisible(false);
                try {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal < 100) {
                        tax = temporal;

                        npvService.setImpuestos(tax);
                        String value2 = String.format("%.2f", tax);
                        taxTextField.setText(value2 + "%");
                    } else {
                        errorTax.setVisible(true);
                        taxTextField.setText("");
                        tax = -1.0;
                        npvService.setImpuestos(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorTax.setVisible(true);
                    taxTextField.setText("");
                    tax = -1.0;
                    npvService.setImpuestos(0);
                }

            } else {
                if (tax == -1) {
                    taxTextField.setText("");
                } else {
                    String value2 = String.format("%.2f", tax);
                    taxTextField.setText(value2);
                }
            }
        });
    }

    public void addInflationListener() {
        if (npvService.getInflacion() == 0) {
            inflation = -1;
        } else {
            inflation = npvService.getInflacion();
            String value = String.format("%.2f", inflation);
            inflationTextField.setText(value + "%");
        }

        inflationTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = inflationTextField.getText().split(" ");

            if (!newValue) {
                errorInflation.setVisible(false);
                try {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal < 100) {
                        inflation = temporal;

                        npvService.setInflacion(inflation);
                        String value2 = String.format("%.2f", inflation);
                        inflationTextField.setText(value2 + "%");
                    } else {
                        errorInflation.setVisible(true);
                        inflationTextField.setText("");
                        inflation = -1.0;
                        npvService.setInflacion(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorInflation.setVisible(true);
                    inflationTextField.setText("");
                    inflation = -1.0;
                    npvService.setInflacion(0);
                }

            } else {
                if (inflation == -1) {
                    inflationTextField.setText("");
                } else {
                    String value2 = String.format("%.2f", inflation);
                    inflationTextField.setText(value2);
                }
            }
        });
    }

    public void addSalvageValue() {
        if (npvService.getSalvageValue() == 0) {
            salvageValue = 0;
        } else {
            salvageValue = npvService.getSalvageValue();
            String value = String.format("%.2f", salvageValue);
            salvageValueTextField.setText("$" + value);
        }

        salvageValueTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = salvageValueTextField.getText().split(" ");

            if (!newValue) {
                errorSalvageValue.setVisible(false);
                try {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal > 0) {
                        salvageValue = temporal;

                        npvService.setSalvageValue(salvageValue);
                        String value2 = String.format("%.2f", salvageValue);
                        salvageValueTextField.setText("$" + value2);
                    } else if (temporal == 0) {
                        salvageValue = 0;

                        salvageValueTextField.setText("");
                    } else {
                        errorSalvageValue.setVisible(true);
                        salvageValueTextField.setText("");
                        salvageValue = 0;
                        npvService.setSalvageValue(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    salvageValueTextField.setText("");
                    salvageValue = 0;
                    npvService.setSalvageValue(0);
                }

            } else {
                if (salvageValue == 0) {
                    salvageValueTextField.setText("");
                } else {
                    String value2 = String.format("%.2f", salvageValue);
                    salvageValueTextField.setText(value2);
                }
            }
        });
    }

    public void addPeriodSalvageValue() {
        if (npvService.getPeriodSalvageValue() == -1) {
            periodSalvageValue = -1;
        } else {
            periodSalvageValue = npvService.getPeriodSalvageValue();
            salvageValueChoiceBox.setValue((periodSalvageValue+1) + " Periodos");

            salvageValueChoiceBox.setItems(FXCollections.observableArrayList(
                    Arrays.copyOf(PERIODS, period +1)
            ));
        }

        salvageValueChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            periodSalvageValue = temporal;
            npvService.setPeriodSalvageValue(periodSalvageValue);
        });
    }

    public void getNpv() throws Exception {
        boolean isCorrect = true;

        if (period == -1) {
            errorPeriod.setVisible(true);
            isCorrect = false;
        }

        if (principal == -1) {
            errorPrincipal.setVisible(true);
            isCorrect = false;
        }

        if (interest == -1) {
            errorInterest.setVisible(true);
            isCorrect = false;
        }

        if (tax == -1) {
            errorTax.setVisible(true);
            isCorrect = false;
        }

        if (inflation == -1) {
            errorInflation.setVisible(true);
            isCorrect = false;
        }

        if (isCorrect){
            npvService.setNpv((principal * (1 + tax/100)) * - 1);
            changeScene((Stage) salvageValueChoiceBox.getScene().getWindow(), NET_PRESENT_VALUE_FINISH);
        }
    }

    public void clean() {
        NetPresentValueService.setNetPresentValueService(
                new NetPresentValueService()
        );

        period = -1;
        periodSalvageValue = -1;

        principal = -1;
        interest = -1;
        tax = -1;
        inflation = -1;
        salvageValue = 0;

        principalTextField.setText("");
        interestTextField.setText("");
        taxTextField.setText("");
        inflationTextField.setText("");
        salvageValueTextField.setText("");

        salvageValueChoiceBox.getItems().clear();
        vBoxCards.getChildren().clear();

        periodChoiceBox.setValue(null);
        salvageValueChoiceBox.setValue(null);
    }
}
