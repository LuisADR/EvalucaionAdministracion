package administracion.interfaces.npv;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Card4Controller extends SceneController implements Initializable {

    private int period;
    private double inflow, outflow, cashflow, presentValue, interest, inflation;
    private NetPresentValueService npvService;

    @FXML
    Text inflowText, outflowText, cashflowText, presentValueText, periodText;

    public Card4Controller(int period) {
        this.npvService = NetPresentValueService.getNetPresentValueService();

        this.period = period;

        this.inflow = npvService.getInflow()[period];

        if (npvService.getPeriodSalvageValue() == period){
            this.inflow += npvService.getSalvageValue();
        }

        this.outflow = npvService.getOutflow()[period];
        this.cashflow = inflow - outflow;
        this.cashflow *= (1-(npvService.getImpuestos() / 100));

        this.inflation = npvService.getInflacion() / 100;
        this.interest = npvService.getInterest() / 100;

        this.period ++;

        double interesAjustado = interest+inflation+(interest*inflation);

        this.presentValue =  cashflow / Math.pow(1+interesAjustado, period);

        double actualPresentValue = npvService.getNpv();
        actualPresentValue += presentValue;

        npvService.setNpv(actualPresentValue);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodText.setText(period + "");

        String value = String.format("%.2f", inflow);
        inflowText.setText("$" + value);

        value = String.format("%.2f", outflow);
        outflowText.setText("$" + value);

        if (cashflow < 0) {
            value = String.format("%.2f", Math.abs(cashflow));
            cashflowText.setText("-$" + value);
        } else {
            value = String.format("%.2f", cashflow);
            cashflowText.setText("$" + value);
        }

        if (presentValue < 0) {
            value = String.format("%.2f", presentValue);
            presentValueText.setText("-$" + value);
        } else {
            value = String.format("%.2f", presentValue);
            presentValueText.setText("$" + value);
        }
    }
}
