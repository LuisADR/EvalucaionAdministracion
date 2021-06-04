package administracion.interfaces.depresiation;

import administracion.interfaces.SceneController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepreciationController extends SceneController implements Initializable {

    private int period, year, cat;
    private double principal, tax, salvageValue;

    private static final String[] PERIODS = {
            "1 Periodo", "2 Periodos", "3 Periodos", "4 Periodos", "5 Periodos",
            "6 Periodos", "7 Periodos", "8 Periodos", "9 Periodos", "10 Periodos"
    };

    private static final String[] CAT = {
            "Cat 3", "Cat 5", "Cat 7", "Cat 10"
    };

    private DepreciationService depService;

    @FXML
    ChoiceBox periodsChoiceBox, catChoiceBox;

    @FXML
    Text errorPeriod, errorPrincipal, errorTax, errorSalvageValue, errorYear, errorCat;

    @FXML
    BorderPane borderPane;

    @FXML
    TextField principalTextField, taxTextField, salvageValueTextField, yearTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.depService = DepreciationService.getInstance();

        addPeriodListener();
        addPrincipalListener();
        addTaxListener();
        addSavageValue();
        addYearListener();
        addCatListener();

        try {
            borderPane.setLeft(getNavbar(DEPRECIATION));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPeriodListener() {
        periodsChoiceBox.setItems(FXCollections.observableArrayList(PERIODS));

        if (depService.getPeriod() == -1) {
            period = -1;
        } else {
            period = depService.getPeriod();
            periodsChoiceBox.setValue((period+1) + " Periodos");
        }

        periodsChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            errorPeriod.setVisible(false);
            period = temporal;

            depService.setPeriod(period);
        });
    }

    public void addPrincipalListener() {
        if (depService.getPrincipal() == -1) {
            principal = -1;
        } else {
            principal = depService.getPrincipal();
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

                        depService.setPrincipal(temporal);
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

    public void addTaxListener() {
        if (depService.getTax() == 0) {
            tax = -1;
        } else {
            tax = depService.getTax();
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

                        depService.setTax(tax);
                        String value2 = String.format("%.2f", tax);
                        taxTextField.setText(value2 + "%");
                    } else {
                        errorTax.setVisible(true);
                        taxTextField.setText("");
                        tax = -1.0;
                        depService.setTax(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    errorTax.setVisible(true);
                    taxTextField.setText("");
                    tax = -1.0;
                    depService.setTax(0);
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

    public void addSavageValue() {
        if (depService.getSalvageValue() == 0) {
            salvageValue = 0;
        } else {
            salvageValue = depService.getSalvageValue();
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

                        depService.setSalvageValue(salvageValue);
                        String value2 = String.format("%.2f", salvageValue);
                        salvageValueTextField.setText("$" + value2);
                    } else if (temporal == 0) {
                        salvageValue = 0;

                        salvageValueTextField.setText("");
                    } else {
                        errorSalvageValue.setVisible(true);
                        salvageValueTextField.setText("");
                        salvageValue = 0;
                        depService.setSalvageValue(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    salvageValueTextField.setText("");
                    salvageValue = 0;
                    depService.setSalvageValue(0);
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

    public void addYearListener() {
        if (depService.getYear() == 0){
            this.year = -1;
        } else {
            this.year = depService.getYear();
            yearTextField.setText(year + "");
        }

        this.yearTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = yearTextField.getText().split(" ");

            if (!newValue) {
                errorYear.setVisible(false);
                try {
                    int temporal = Integer.valueOf(value[0]);

                    if (temporal > 0) {
                        year = temporal;

                        depService.setYear(year);
                    } else {
                        errorYear.setVisible(true);
                        yearTextField.setText("");
                        year = -1;
                        depService.setYear(0);
                    }

                } catch (NumberFormatException numberFormatException) {
                    yearTextField.setText("");
                    year = -1;
                    depService.setYear(0);
                    errorYear.setVisible(true);
                }

            } else {
                if (year == -1) {
                    yearTextField.setText("");
                } else {
                    yearTextField.setText(year + "");
                }
            }
        });
    }

    public void addCatListener() {
        catChoiceBox.setItems(FXCollections.observableArrayList(CAT));

        if (depService.getCat() == -1){
            this.cat = -1;
        } else {
            this.cat = depService.getCat();
            switch (this.cat) {
                case 0:
                    catChoiceBox.setValue("CAT 3");
                    break;
                case 1:
                    catChoiceBox.setValue("CAT 5");
                    break;
                case 2:
                    catChoiceBox.setValue("CAT 7");
                    break;
                default:
                    catChoiceBox.setValue("CAT 10");
                    break;
            }
        }

        catChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            errorCat.setVisible(false);
            cat = temporal;

            depService.setCat(cat);
        });
    }

    public void linearDepreciation() throws Exception {
        boolean isCorrect = true;

        if (period == -1){
            errorPeriod.setVisible(true);
            isCorrect = false;
        }

        if (principal == -1){
            errorPrincipal.setVisible(true);
            isCorrect = false;
        }

        if (tax == -1){
            errorTax.setVisible(true);
            isCorrect = false;
        }

        if (year == -1){
            errorYear.setVisible(true);
            isCorrect = false;
        }

        if (isCorrect){
            changeScene((Stage) errorYear.getScene().getWindow(), DEPRECIATION_LINEAR);
        }
    }

    public void catDepreciation() throws Exception {
        boolean isCorrect = true;

        if (principal == -1){
            errorPrincipal.setVisible(true);
            isCorrect = false;
        }

        if (cat == -1){
            errorCat.setVisible(true);
            isCorrect = false;
        }

        if (tax == -1){
            errorTax.setVisible(true);
            isCorrect = false;
        }

        if (year == -1){
            errorYear.setVisible(true);
            isCorrect = false;
        }

        if (isCorrect){
            changeScene((Stage) errorYear.getScene().getWindow(), DEPRECIATION_MACRS);
        }
    }

    public void clean() {
        DepreciationService.setDepreciationService(new DepreciationService());

        period = -1;
        year = -1;
        cat = -1;
        principal = -1;
        tax = -1;
        salvageValue = 0;


        periodsChoiceBox.setValue(null);
        catChoiceBox.setValue(null);

        errorPeriod.setVisible(false);
        errorPrincipal.setVisible(false);
        errorTax.setVisible(false);
        errorSalvageValue.setVisible(false);
        errorYear.setVisible(false);
        errorCat.setVisible(false);

        principalTextField.setText("");
        taxTextField.setText("");
        salvageValueTextField.setText("");
        yearTextField.setText("");
    }
}
