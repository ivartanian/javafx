package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainButton extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        Button btn;
        btn = new Button();
        btn.setLayoutX(20);
        btn.setLayoutY(20);
        btn.setText("Тестировать свойства");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Свойства, унаследованные от класса Node:" + "\n" +
                        "Свойство blendMode: " + btn.blendModeProperty().getValue() + "\n" +
                        "Свойство boundsInLocal: " + btn.boundsInLocalProperty().getValue());
            }
        });

        Button btnON;
        btnON = new Button();
        btnON.setLayoutX(20);
        btnON.setLayoutY(150);
        btnON.setText("Установить свойства");
        btnON.setStyle("");
        btnON.setPrefSize(200, 30);
        btnON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                btn.setBlendMode(BlendMode.DARKEN);
                javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(75, 53, 80);
                btn.setCursor(Cursor.CLOSED_HAND);
                DropShadow effect = new DropShadow();
                effect.setOffsetX(10);
                effect.setOffsetY(10);
                btn.setEffect(effect);
                btn.setOpacity(0.5);
                btn.setRotate(10);
                btn.setLayoutX(80);
                btn.setScaleX(1.8);
                btn.setLayoutY(170
                );
                btn.setTranslateZ(
                        -
                                50);
                btn.setPrefSize(150, 100);
                btn.setTooltip(new Tooltip("Это кнопка тестирования свойств класса Button"));
                Image im = new Image(this.getClass().getResource("image.png").toString());
                ImageView imv = new ImageView(im);
                imv.setFitHeight(50);
                imv.setFitWidth(50);
                btn.setGraphic(imv);
                btn.setStyle("-fx-font:  bold italic 10pt Helvetica;");
                btn.setAlignment(Pos.CENTER);
                btn.setContentDisplay(ContentDisplay.RIGHT);
                btn.setUnderline(true);
                btn.setWrapText(true);
            }
        });

        root.getChildren().add(btnON);
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
