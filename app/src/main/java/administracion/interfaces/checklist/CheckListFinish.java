package administracion.interfaces.checklist;

import administracion.interfaces.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckListFinish extends SceneController implements Initializable {

    private ChecklistService checklistService;

    @FXML
    Text question1, question2, question3, question4, question5,
            question6, question7, question8, question9, question10,
            question11, question12, question13, question14, question15,
            question16, question17, question18, question19, question20;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.checklistService = ChecklistService.getInstance();

        try {
            borderPane.setLeft(getNavbar(CHECKLIST));
        } catch (Exception e) {
            e.printStackTrace();
        }

        question1.setText(checklistService.getAnswers()[0]);
        question2.setText(checklistService.getAnswers()[1]);
        question3.setText(checklistService.getAnswers()[2]);
        question4.setText(checklistService.getAnswers()[3]);
        question5.setText(checklistService.getAnswers()[4]);
        question6.setText(checklistService.getAnswers()[5]);
        question7.setText(checklistService.getAnswers()[6]);
        question8.setText(checklistService.getAnswers()[7]);
        question9.setText(checklistService.getAnswers()[8]);
        question10.setText(checklistService.getAnswers()[9]);
        question11.setText(checklistService.getAnswers()[10]);
        question12.setText(checklistService.getAnswers()[11]);
        question13.setText(checklistService.getAnswers()[12]);
        question14.setText(checklistService.getAnswers()[13]);
        question15.setText(checklistService.getAnswers()[14]);
        question16.setText(checklistService.getAnswers()[15]);
        question17.setText(checklistService.getAnswers()[16]);
        question18.setText(checklistService.getAnswers()[17]);
        question19.setText(checklistService.getAnswers()[18]);
        question20.setText(checklistService.getAnswers()[19]);
    }

    public void back () throws Exception{
        changeScene((Stage) question1.getScene().getWindow(), CHECKLIST);
    }
}
