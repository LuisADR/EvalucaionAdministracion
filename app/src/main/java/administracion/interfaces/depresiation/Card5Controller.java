package administracion.interfaces.depresiation;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Card5Controller extends SceneController implements Initializable {

    private int period, year;
    private double depRate, anualDep, accDep, valueLedges, tax;
    private DepreciationService depService;

    @FXML
    Text periodText, yearText, depRateText, anualDepText, accDepText, valueLedgeText, taxPerYearText;

    public Card5Controller(int period) {
        this.depService = DepreciationService.getInstance();

        this.period = period;
        this.depRate = 100 / (depService.getPeriod() + 1);
        this.year = depService.getYear() + period + 1;
        this.anualDep = (depService.getPrincipal() - depService.getSalvageValue()) / (depService.getPeriod() +1);
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

        depRateText.setText(depRate + "%");

        String value = String.format("%.2f", anualDep);
        anualDepText.setText("$" + value);

        value = String.format("%.2f", accDep);
        accDepText.setText("$" + value);

        value = String.format("%.2f", valueLedges);
        valueLedgeText.setText("$" + value);

        value = String.format("%.2f", tax);
        taxPerYearText.setText("$" + value);
    }
}
