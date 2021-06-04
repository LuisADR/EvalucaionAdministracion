package administracion.interfaces.checklist;

public class ChecklistService {

    private static ChecklistService checklistService;
    private String[] answers;

    public ChecklistService () {
        this.answers = new String[20];

        for (int i = 0; i < 20; i++){
            this.answers[i] = "";
        }
    }

    public static ChecklistService getInstance() {
        if (checklistService == null)
            checklistService = new ChecklistService();

        return checklistService;
    }

    public String[] getAnswers() {
        return answers;
    }

    public static void setChecklistService(ChecklistService checklistService) {
        ChecklistService.checklistService = checklistService;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
