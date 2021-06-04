package administracion.interfaces.home;

public class HomeService {
    private static HomeService homeService;
    private String proyect, evaluator;

    public HomeService() {
        this.proyect = "";
        this.evaluator = "";
    }

    public static HomeService getInstance() {
        if(homeService == null)
            homeService = new HomeService();

        return homeService;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public String getProyect() {
        return proyect;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public void setProyect(String proyect) {
        this.proyect = proyect;
    }
}
