package administracion.interfaces.depresiation;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Card6Controller extends SceneController implements Initializable {

    private int period, year;
    private double depRate, anualDep, accDep, valueLedges, tax;
    private DepreciationService depService;

    @FXML
    Text periodText, yearText, depRateText, anualDepText, accDepText, valueLedgeText, taxPerYearText;

    public Card6Controller(int period, double depRate) {
        this.depService = DepreciationService.getInstance();

        this.period = period;
        this.depRate = depRate / 100;
        this.year = depService.getYear() + period + 1;
        this.anualDep = (depService.getPrincipal() * (this.depRate));
        this.accDep = depService.getAccDep();

        this.accDep += anualDep;
        depService.setAccDep(accDep);

        double tax = depService.getTax();
        this.valueLedges = (depService.getPrincipal() - accDep);
        this.tax = this.valueLedges * tax / 100;

        this.period ++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodText.setText(period + "");

        yearText.setText(year + "");

        String value = String.format("%.2f", (depRate*100));
        depRateText.setText(value + "%");

        value = String.format("%.2f", anualDep);
        anualDepText.setText("$" + value);

        value = String.format("%.2f", accDep);
        accDepText.setText("$" + value);

        value = String.format("%.2f", valueLedges);
        valueLedgeText.setText("$" + value);

        value = String.format("%.2f", tax);
        taxPerYearText.setText("$" + value);
    }
}
