package administracion.interfaces.payback;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Card2Controller extends SceneController implements Initializable {

    private int period;
    private double inflow, outflow, cashflow, interest, dpp;
    private PaybackService paybackService;

    @FXML
    Text periodText, inflowText, outflowText, cashflowText, dppText, ccfText;

    @FXML
    HBox card;

    public Card2Controller (int period) {
        paybackService = PaybackService.getPaybackService();
        this.period = period + 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodText.setText(period + "");

        inflow =  paybackService.getInflow()[period-1];
        String value = String.format("%.2f", inflow);
        inflowText.setText("$" + value);

        outflow = paybackService.getOutflow()[period-1];
        value = String.format("%.2f", outflow);
        outflowText.setText("$" + value);

        cashflow = inflow - outflow;
        if (cashflow < 0) {
            value = String.format("%.2f", Math.abs(cashflow));
            cashflowText.setText("-$" + value);
        } else {
            value = String.format("%.2f", cashflow);
            cashflowText.setText("$" + value);
        }

        interest = paybackService.getInterest();
        dpp = cashflow/Math.pow((1+(interest/100)),period);
        if (dpp < 0) {
            value = String.format("%.2f", Math.abs(dpp));
            dppText.setText("-$" + value);
        } else {
            value = String.format("%.2f", dpp);
            dppText.setText("$" + value);
        }

        double principal = paybackService.getPrincipal();
        principal += dpp;
        paybackService.setPrincipal(principal);

        if (principal < 0) {
            value = String.format("%.2f", Math.abs(principal));
            ccfText.setText("-$" + value);
        } else {
            boolean isPayback = paybackService.isPayback();

            if (!isPayback) {
                card.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ddedcd"), CornerRadii.EMPTY, Insets.EMPTY)));
                paybackService.setPayback(true);
            }
            value = String.format("%.2f", principal);
            ccfText.setText("$" + value);

        }

    }

    public void newPayback() throws Exception {
        changeScene((Stage) card.getScene().getWindow(), PAYBACK);
    }
}
