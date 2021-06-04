package administracion.interfaces.checklist;

import administracion.interfaces.SceneController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.C;

import java.net.URL;
import java.util.ResourceBundle;

public class ChecklistController extends SceneController implements Initializable {

    private ChecklistService checklistService;

    @FXML
    TextField question1, question2, question3, question4, question5, question9, question10,
            question11, question12, question13, question14, question15, question17;

    @FXML
    ChoiceBox question6, question7, question8, question16, question18, question19, question20;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.checklistService = ChecklistService.getInstance();

        initQuestion1();
        initQuestion2();
        initQuestion3();
        initQuestion4();
        initQuestion5();
        initQuestion6();
        initQuestion7();
        initQuestion8();
        initQuestion9();
        initQuestion10();
        initQuestion11();
        initQuestion12();
        initQuestion13();
        initQuestion14();
        initQuestion15();
        initQuestion16();
        initQuestion17();
        initQuestion18();
        initQuestion19();
        initQuestion20();

        try {
            borderPane.setLeft(getNavbar(CHECKLIST));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initQuestion1() {
        if (checklistService.getAnswers()[0] != null)
            question1.setText(checklistService.getAnswers()[0]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[0] = value;
            }
        });
    }

    public void initQuestion2() {
        if (checklistService.getAnswers()[1] != null)
            question2.setText(checklistService.getAnswers()[1]);

        question2.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question2.getText();

            if (!newValue) {
                checklistService.getAnswers()[1] = value;
            }
        });
    }

    public void initQuestion3() {
        if (checklistService.getAnswers()[2] != null)
            question3.setText(checklistService.getAnswers()[2]);

        question3.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question3.getText();

            if (!newValue) {
                checklistService.getAnswers()[2] = value;
            }
        });
    }

    public void initQuestion4() {
        if (checklistService.getAnswers()[3] != null)
            question4.setText(checklistService.getAnswers()[3]);

        question4.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question4.getText();

            if (!newValue) {
                checklistService.getAnswers()[3] = value;
            }
        });
    }

    public void initQuestion5() {
        if (checklistService.getAnswers()[4] != null)
            question5.setText(checklistService.getAnswers()[4]);

        question5.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question5.getText();

            if (!newValue) {
                checklistService.getAnswers()[4] = value;
            }
        });
    }

    public void initQuestion6() {
        String[] answers = {"Yes", "No"};
        question6.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[5] != null)
            question6.setValue(checklistService.getAnswers()[5]);

        question6.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[5] = answers[temporal];
        });
    }

    public void initQuestion7() {
        String[] answers = {"Build", "Buy"};
        question7.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[6] != null)
            question7.setValue(checklistService.getAnswers()[6]);

        question7.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[6] = answers[temporal];
        });
    }

    public void initQuestion8() {
        String[] answers = {"Yes", "No"};
        question8.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[7] != null)
            question8.setValue(checklistService.getAnswers()[7]);

        question8.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[7] = answers[temporal];
        });
    }

    public void initQuestion9() {
        if (checklistService.getAnswers()[8] != null)
            question9.setText(checklistService.getAnswers()[8]);

        question9.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question9.getText();

            if (!newValue) {
                checklistService.getAnswers()[8] = value;
            }
        });
    }

    public void initQuestion10() {
        if (checklistService.getAnswers()[9] != null)
            question10.setText(checklistService.getAnswers()[9]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[9] = value;
            }
        });
    }

    public void initQuestion11() {
        if (checklistService.getAnswers()[10] != null)
            question11.setText(checklistService.getAnswers()[10]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[10] = value;
            }
        });
    }

    public void initQuestion12() {
        if (checklistService.getAnswers()[11] != null)
            question12.setText(checklistService.getAnswers()[11]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[11] = value;
            }
        });
    }

    public void initQuestion13() {
        if (checklistService.getAnswers()[12] != null)
            question13.setText(checklistService.getAnswers()[12]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[12] = value;
            }
        });
    }

    public void initQuestion14() {
        if (checklistService.getAnswers()[13] != null)
            question14.setText(checklistService.getAnswers()[13]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[13] = value;
            }
        });
    }

    public void initQuestion15() {
        if (checklistService.getAnswers()[14] != null)
            question15.setText(checklistService.getAnswers()[13]);

        question1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question1.getText();

            if (!newValue) {
                checklistService.getAnswers()[14] = value;
            }
        });
    }

    public void initQuestion16() {
        String[] answers = {"Yes", "No"};
        question16.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[15] != null)
            question16.setValue(checklistService.getAnswers()[15]);

        question6.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[15] = answers[temporal];
        });
    }

    public void initQuestion17() {
        if (checklistService.getAnswers()[16] != null)
            question17.setText(checklistService.getAnswers()[16]);

        question17.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String value = question17.getText();

            if (!newValue) {
                checklistService.getAnswers()[16] = value;
            }
        });
    }

    public void initQuestion18() {
        String[] answers = {"Yes", "No"};
        question18.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[17] != null)
            question18.setValue(checklistService.getAnswers()[17]);

        question18.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[17] = answers[temporal];
        });
    }

    public void initQuestion19() {
        String[] answers = {"New initiative or path ", "Existing initiative"};
        question19.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[18] != null)
            question19.setValue(checklistService.getAnswers()[18]);

        question19.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
                checklistService.getAnswers()[18] = answers[temporal];
        });
    }

    public void initQuestion20() {
        String[] answers = {"Available", "New"};
        question20.setItems(FXCollections.observableArrayList(answers));

        if (checklistService.getAnswers()[19] != null)
            question20.setValue(checklistService.getAnswers()[19]);

        question20.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int temporal = (Integer) newValue;

            if (temporal >= 0)
            checklistService.getAnswers()[19] = answers[temporal];
        });
    }

    public void showChecklist() throws Exception{
        changeScene((Stage) question1.getScene().getWindow(), CHECKLIST_FINISH);
    }

    public void cleanAll(){
        ChecklistService.setChecklistService(new ChecklistService());

        question1.setText("");
        question2.setText("");
        question3.setText("");
        question4.setText("");
        question5.setText("");
        question9.setText("");
        question10.setText("");
        question11.setText("");
        question12.setText("");
        question13.setText("");
        question14.setText("");
        question15.setText("");
        question17.setText("");

        question6.setValue(null);
        question7.setValue(null);
        question8.setValue(null);
        question16.setValue(null);
        question18.setValue(null);
        question19.setValue(null);
        question20.setValue(null);
    }
}
