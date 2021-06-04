package administracion.interfaces.npv;

public class NetPresentValueService {

    private int periods, periodSalvageValue;
    private double principal, interest, impuestos, inflacion,
            salvageValue, inflow[], outflow[], npv;

    private static NetPresentValueService netPresentValueService;

    public NetPresentValueService() {
        this.periods = -1;
        this.principal = 0;
        this.interest = 0;
        this.impuestos = 0;
        this.inflacion = 0;
        this.salvageValue = 0;
        this.npv = 0;
        this.periodSalvageValue = -1;
        this.inflow = new double[10];
        this.outflow = new double[10];
    }

    public static NetPresentValueService getNetPresentValueService() {
        if (netPresentValueService == null)
            netPresentValueService = new NetPresentValueService();

        return netPresentValueService;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public void setInflacion(double inflacion) {
        this.inflacion = inflacion;
    }

    public void setOutflow(double[] outflow) {
        this.outflow = outflow;
    }

    public void setNpv(double npv) {
        this.npv = npv;
    }

    public void setInflow(double[] inflow) {
        this.inflow = inflow;
    }

    public void setPeriodSalvageValue(int periodSalvageValue) {
        this.periodSalvageValue = periodSalvageValue;
    }

    public int getPeriodSalvageValue() {
        return periodSalvageValue;
    }

    public double[] getOutflow() {
        return outflow;
    }

    public double[] getInflow() {
        return inflow;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public double getNpv() {
        return npv;
    }

    public double getInflacion() {
        return inflacion;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public int getPeriods() {
        return periods;
    }

    public static void setNetPresentValueService(NetPresentValueService netPresentValueService) {
        NetPresentValueService.netPresentValueService = netPresentValueService;
    }
}
