package administracion.interfaces.payback;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Card1Controller extends SceneController implements Initializable {
    private int period;
    private double inflow, outflow, cashFlow;
    private PaybackService paybackService;

    @FXML
    private Text periodText, cashFlowText;

    @FXML
    private TextField inflowTextField, outflowTextField;

    public Card1Controller(int period) {
        paybackService = PaybackService.getPaybackService();

        this.inflow = paybackService.getInflow()[period];
        this.outflow = paybackService.getOutflow()[period];
        this.cashFlow = inflow - outflow;

        this.period = period + 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodText.setText(period + "");

        setValues();

        addListenerInflow();
        addListenerOutflow();
    }

    public void setValues() {
        if (inflow != 0){
            String value = String.format("%.2f", inflow);
            inflowTextField.setText("$" + value);
        }

        if (outflow != 0){
            String value = String.format("%.2f", outflow);
            outflowTextField.setText("$" + value);
        }

        if (cashFlow != 0) {
            String value = String.format("%.2f", cashFlow);
            cashFlowText.setText("$" + value);
        }
    }

    public void addListenerInflow() {
        inflowTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = inflowTextField.getText().split(" ");

            try {
                if (!newValue) {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal <= 0) {
                        inflowTextField.setText("");
                        inflow = 0;
                    } else {
                        inflow = temporal;

                        PaybackService.getPaybackService().getInflow()[period-1] = temporal;

                        String value2 = String.format("%.2f", inflow);
                        inflowTextField.setText("$" + value2);

                        cashFlow = inflow - outflow;
                        if (cashFlow < 0) {
                            value2 = String.format("%.2f", Math.abs(cashFlow));
                            cashFlowText.setText("-$" + value2);
                        } else {
                            value2 = String.format("%.2f", cashFlow);
                            cashFlowText.setText("$" + value2);
                        }
                    }
                } else {
                    if (inflow == 0){
                        inflowTextField.setText("");
                    } else {
                        String value2 = String.format("%.2f", inflow);
                        inflowTextField.setText(value2);
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                inflowTextField.setText("");
                inflow = 0;

                PaybackService.getPaybackService().getInflow()[period-1] = 0;

                cashFlow = inflow - outflow;
                if (cashFlow < 0) {
                    String value2 = String.format("%.2f", Math.abs(cashFlow));
                    cashFlowText.setText("-$" + value2);
                } else {
                    String value2 = String.format("%.2f", cashFlow);
                    cashFlowText.setText("$" + value2);
                }
            }
        });
    }

    public void addListenerOutflow() {
        outflowTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String[] value = outflowTextField.getText().split(" ");

            try {
                if (!newValue) {
                    double temporal = Double.valueOf(value[0]);

                    if (temporal <= 0) {
                        outflowTextField.setText("");
                        outflow = 0;
                    } else {
                        outflow = temporal;

                        PaybackService.getPaybackService().getOutflow()[period-1] = temporal;

                        String value2 = String.format("%.2f", outflow);
                        outflowTextField.setText("$" + value2);

                        cashFlow = inflow - outflow;
                        if (cashFlow < 0) {
                            value2 = String.format("%.2f", Math.abs(cashFlow));
                            cashFlowText.setText("-$" + value2);
                        } else {
                            value2 = String.format("%.2f", cashFlow);
                            cashFlowText.setText("$" + value2);
                        }
                    }
                } else {
                    if (outflow == 0){
                        outflowTextField.setText("");
                    } else {
                        String value2 = String.format("%.2f", outflow);
                        outflowTextField.setText(value2);
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                outflowTextField.setText("");
                outflow = 0;

                PaybackService.getPaybackService().getOutflow()[period-1] = 0;

                cashFlow = inflow - outflow;
                if (cashFlow < 0) {
                    String value2 = String.format("%.2f", Math.abs(cashFlow));
                    cashFlowText.setText("-$" + value2);
                } else {
                    String value2 = String.format("%.2f", cashFlow);
                    cashFlowText.setText("$" + value2);
                }
            }
        });
    }
}
