package administracion.interfaces.payback;

public class PaybackService {

    private int periods;
    private double principal, interest, inflow[], outflow[];
    private static PaybackService paybackService;
    boolean isPayback;

    public PaybackService() {
        periods = -1;
        principal = 0;
        interest = 0;
        inflow = new double[10];
        outflow = new double[10];
    }

    public static PaybackService getPaybackService() {
        if (paybackService == null)
            paybackService = new PaybackService();
        return paybackService;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public double[] getInflow() {
        return inflow;
    }

    public double[] getOutflow() {
        return outflow;
    }

    public int getPeriods() {
        return periods;
    }

    public boolean isPayback() {
        return isPayback;
    }

    public void setInflow(double[] inflow) {
        this.inflow = inflow;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setOutflow(double[] outflow) {
        this.outflow = outflow;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setPayback(boolean payback) {
        isPayback = payback;
    }

    public static void setPaybackService(PaybackService paybackService) {
        PaybackService.paybackService = paybackService;
    }
}
