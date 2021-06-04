package administracion.interfaces;

import administracion.interfaces.depresiation.Card5Controller;
import administracion.interfaces.depresiation.Card6Controller;
import administracion.interfaces.npv.Card3Controller;
import administracion.interfaces.npv.Card4Controller;
import administracion.interfaces.payback.Card1Controller;
import administracion.interfaces.payback.Card2Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    public static final String HOME = "/fxml/home.fxml";
    public static final String NAVBAR = "/fxml/navbar.fxml";
    public static final String PAYBACK = "/fxml/payback.fxml";
    public static final String PAYBACK_FINISH = "/fxml/paybackFinish.fxml";
    public static final String NET_PRESENT_VALUE = "/fxml/npv.fxml";
    public static final String NET_PRESENT_VALUE_FINISH = "/fxml/npvFinish.fxml";
    public static final String CHECKLIST = "/fxml/checklist.fxml";
    public static final String CHECKLIST_FINISH = "/fxml/checklistFinish.fxml";
    public static final String DEPRECIATION ="/fxml/depreciation.fxml";
    public static final String DEPRECIATION_LINEAR = "/fxml/linearDepreciation.fxml";
    public static final String DEPRECIATION_MACRS = "/fxml/macrsDepreciation.fxml";
    public static final String TABLE_CARD_1 = "/fxml/cards/payback1.fxml";
    public static final String TABLE_CARD_2 = "/fxml/cards/payback2.fxml";
    public static final String TABLE_CARD_3 = "/fxml/cards/npm1.fxml";
    public static final String TABLE_CARD_4 = "/fxml/cards/npm2.fxml";
    public static final String TABLE_CARD_5 = "/fxml/cards/depreciation.fxml";

    public void changeScene(Stage window, String fxml) throws Exception {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(fxml));

        Scene tableViewScene = new Scene(tableViewParent);

        tableViewScene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());

        window.setScene(tableViewScene);
        window.show();
    }

    public Parent getNavbar(String url) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource(NAVBAR));
        ImageView icon;

        switch (url) {
            case HOME:
                icon = (ImageView) menu.lookup("#homeLogo");
                icon.setImage(new Image("img/4x/homeSelect@4x.png"));

                icon.getParent().lookup("#selector").setVisible(true);
                break;
            case PAYBACK:
                icon = (ImageView) menu.lookup("#paybackIcon");
                icon.setImage(new Image("img/4x/paybackSelect@4x.png"));

                icon.getParent().lookup("#selector").setVisible(true);
                break;

            case NET_PRESENT_VALUE:
                icon = (ImageView) menu.lookup("#npvIcon");
                icon.setImage(new Image("img/4x/npvSelect@4x.png"));

                icon.getParent().lookup("#selector").setVisible(true);
                break;

            case CHECKLIST:
                icon = (ImageView) menu.lookup("#checklistIcon");
                icon.setImage(new Image("img/4x/checklistSelect@4x.png"));

                icon.getParent().lookup("#selector").setVisible(true);
                break;

            case DEPRECIATION:
                icon = (ImageView) menu.lookup("#depreciationIcon");
                icon.setImage(new Image("img/4x/depresiationSelect@4x.png"));

                icon.getParent().lookup("#selector").setVisible(true);
                break;
        }

        return menu;
    }

    public Parent getTableCard1(int period) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_1));

            loader.setController(new Card1Controller(period));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return card;
    }

    public Parent getTableCard2(int period) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_2));

            loader.setController(new Card2Controller(period));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return card;
    }

    public Parent getTableCard3(int period) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_3));

            loader.setController(new Card3Controller(period));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException.getCause());
        }

        return card;
    }

    public Parent getTableCard4(int period) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_4));

            loader.setController(new Card4Controller(period));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException.getCause());
        }

        return card;
    }

    public Parent getTableCard5(int period) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_5));

            loader.setController(new Card5Controller(period));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException.getCause());
        }

        return card;
    }

    public Parent getTableCard6(int period, double depRate) {
        FXMLLoader loader;
        Parent card = null;

        try {
            loader = new FXMLLoader (getClass().getResource(TABLE_CARD_5));

            loader.setController(new Card6Controller(period, depRate));

            card = loader.load();

        } catch (IOException ioException) {
            System.out.println(ioException.getCause());
        }

        return card;
    }
}
