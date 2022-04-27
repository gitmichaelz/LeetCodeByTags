import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color:#ffffff");
        final Boolean[] selectedItem = new Boolean[2];

        VBox box = new VBox(50);
        CheckBox c1 = new CheckBox("c1");
        CheckBox c2 = new CheckBox("c2");


        box.getChildren().addAll(c1, c2);
        AnchorPane.setTopAnchor(box, 100.0);
        AnchorPane.setLeftAnchor(box, 100.0);

        an.getChildren().add(box);

        c1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem[0] = newValue;
//                System.out.println(b1[0]);
            }
        });
        c2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                selectedItem[1] = newValue;
                System.out.println(c2.isSelected());

            }
        });

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Automation Demon");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();


        System.out.println(c1.isSelected() + "  c1");
        System.out.println(c2.isSelected() + "  c2");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
