package administracion.interfaces.npv;

import administracion.interfaces.SceneController;
import administracion.interfaces.payback.PaybackService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Card3Controller extends SceneController implements Initializable {

    private int period;
    private double inflow, outflow;
    private NetPresentValueService npvService;

    @FXML
    TextField inflowTextField, outflowTextField;

    @FXML
    Text periodText;

    public Card3Controller(int period) {
        this.npvService = NetPresentValueService.getNetPresentValueService();

        this.period = period;
        this.inflow = npvService.getInflow()[period];
        this.outflow = npvService.getOutflow()[period];

        this.period++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        periodText.setText(period + "");

        if (inflow != 0){
            String value = String.format("%.2f", inflow);
            inflowTextField.setText("$" + value);
        }

        if (outflow != 0){
            String value = String.format("%.2f", outflow);
            outflowTextField.setText("$" + value);
        }

        addListenerInflow();
        addListenerOutflow();
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

                        npvService.getInflow()[period-1] = temporal;

                        String value2 = String.format("%.2f", inflow);
                        inflowTextField.setText("$" + value2);
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

                npvService.getInflow()[period-1] = 0;
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

                        npvService.getOutflow()[period-1] = temporal;

                        String value2 = String.format("%.2f", outflow);
                        outflowTextField.setText("$" + value2);

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

                npvService.getOutflow()[period-1] = 0;
            }
        });
    }
}
