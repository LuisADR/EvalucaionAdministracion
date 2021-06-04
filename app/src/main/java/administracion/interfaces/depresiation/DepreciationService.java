package administracion.interfaces.depresiation;

public class DepreciationService {

    private int period, year, cat;
    private double principal, tax, salvageValue, accDep;

    private static DepreciationService depreciationService;

    public DepreciationService() {
        this.period = -1;
        this.principal = -1;
        this.tax = 0;
        this.accDep = 0;
        this.salvageValue = 0;
        this.year = 0;
        this.cat = -1;
    }

    public static DepreciationService getInstance() {
        if (depreciationService == null)
            depreciationService = new DepreciationService();

        return depreciationService;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setAccDep(double accDep) {
        this.accDep = accDep;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getPrincipal() {
        return principal;
    }

    public int getCat() {
        return cat;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public double getTax() {
        return tax;
    }

    public double getAccDep() {
        return accDep;
    }

    public int getYear() {
        return year;
    }

    public int getPeriod() {
        return period;
    }

    public static void setDepreciationService(DepreciationService depreciationService) {
        DepreciationService.depreciationService = depreciationService;
    }
}
