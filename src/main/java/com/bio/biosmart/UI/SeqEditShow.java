package com.bio.biosmart.UI;

import com.bio.biosmart.utils.OpenFile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class SeqEditShow extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage seqStage) throws Exception {
        BorderPane mainLayout = new BorderPane();
        // seqLayout is the part that shows the sequence
        //GridPane seqLayout = getSeqEditShowLayout();
        ScrollPane seqLayout = getSeqEditShowLayout();

        // Locate elements on the main layout
        mainLayout.setCenter(seqLayout);
        BorderPane.setAlignment(seqLayout, Pos.CENTER);
        mainLayout.setBottom(statusBar());

        // Set Scene properties
        Scene seqEditShowScene = new Scene(mainLayout);
        seqStage.setScene(seqEditShowScene);
        seqStage.show();
    }

    // Events Handlers ----------------------------------
    EventHandler<MouseEvent> mousEvent = event -> {
        System.out.println(event.getEventType());
    };

    EventHandler<MouseEvent> changeNtColor = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            StackPane wrappedElement = (StackPane) event.getSource();
            wrappedElement.setStyle(
                    "-fx-border-width: 5px; -fx-border-color: rgb(0,0,0);"
            );
            //wrappedElement.setBorder();
        }
    };

    /**
     * Layout defines UI element for sequence edit and sequence show.
     */
    private ScrollPane getSeqEditShowLayout(){
        // Central layout
        ScrollPane layout = new ScrollPane();
        layout.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY,Insets.EMPTY)));
        layout.setMinHeight(200);
        layout.setMinWidth(500);
        layout.setPadding(new Insets(10));
        //layout.setHgap(10);
        //layout.setVgap(10);

        // Sequence show layout
        ArrayList<Character> test = new ArrayList<>();
        test.add('A'); test.add('G'); test.add('G'); test.add('G'); test.add('T'); test.add('A');
        test.add('A'); test.add('C'); test.add('A'); test.add('A'); test.add('C'); test.add('A');
        test.add('C'); test.add('A'); test.add('A'); test.add('A'); test.add('C'); test.add('T');
        test.add('A'); test.add('C'); test.add('G'); test.add('A'); test.add('A'); test.add('T');
        test.add('A'); test.add('C'); test.add('A'); test.add('G'); test.add('C'); test.add('A');
        test.add('A'); test.add('G'); test.add('A'); test.add('A'); test.add('C'); test.add('A');
        test.add('A'); test.add('C'); test.add('T'); test.add('G'); test.add('T'); test.add('A');
        test.add('A'); test.add('T'); test.add('A'); test.add('T'); test.add('C'); test.add('A');
        test.add('A'); test.add('C'); test.add('T'); test.add('A'); test.add('C'); test.add('A');
        test.add('A'); test.add('G'); test.add('A'); test.add('T');
        HBox seqBandLayout = getSeqLayout(
                OpenFile.getEntireContent("data/rawSeq.txt")
        );
        // sequence tools

        // Add elements to the central layout
        layout.setContent(seqBandLayout);
        layout.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //GridPane.setHgrow(seqBandLayout, Priority.ALWAYS);
        //GridPane.setHalignment(seqBandLayout, HPos.CENTER);
        //GridPane.setValignment(seqBandLayout, VPos.CENTER);

        return layout;
    }

    private HBox getSeqLayout(String seq){
        //convert string to ArrayList
        ArrayList<Character> sequence = new ArrayList<>();
        char[] seqChar= seq.toCharArray();
        for (char x:
             seqChar) {
            sequence.add(Character.toUpperCase(x));
        }

        HBox seqLayout = new HBox(0);
        seqLayout.setBackground(
             new Background(
                     new BackgroundFill(
                        Color.HONEYDEW,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                     )
             )
        );
         // Fill the layout with sequence elements
        for (int i = 0; i < sequence.size(); i++) {
            // wrap N
            Character element = sequence.get(i);
            StackPane wrappedElement = wrapElement(
                    String.valueOf(element),
                    15,
                    20,
                    getNucleotideColor(element)
            );

            // Events
            //wrappedElement.addEventFilter(MouseEvent.MOUSE_ENTERED, mousEvent);
            wrappedElement.addEventHandler(MouseEvent.MOUSE_CLICKED, changeNtColor);

            // Add to the sequence near layout
            seqLayout.getChildren().add(
                    i, wrappedElement
            );
            seqLayout.setAlignment(Pos.CENTER);
        }

         return seqLayout;
    }

    private StackPane wrapElement(String element, double width, double height, Paint color){
        StackPane CLayout= new StackPane();
        Rectangle background = new Rectangle(width, height, color);
        Label lbl = new Label(element);

        // Add to the layout
        CLayout.getChildren().add(0, background);
        CLayout.getChildren().add(1, lbl);

        return CLayout;
    }

    private Paint getNucleotideColor(Character nt){
        return switch (nt) {
            case 'A' -> Color.rgb(255, 55, 0, 0.8);
            case 'T' -> Color.rgb(255, 255, 0, 0.9);
            case 'G' -> Color.rgb(0, 188, 0, 0.8);
            case 'C' -> Color.rgb(123, 85, 255, 0.8);
            default -> Color.gray(0.66);
        };
    }

    /**
     * Get status bar.
     * @return
     * layout and its contents.
     */
    private HBox statusBar(){
        HBox statusBarLayout = new HBox(5);
        Label leftText = new Label("Status");

        // order on the status layout
        statusBarLayout.getChildren().add(0, leftText);
        return statusBarLayout;
    }



    private Double[] getNodeCoordinates(Node node){
        double nodeX = node.getLayoutX();
        double nodeY = node.getLayoutY();
        Double[] coordinate = {nodeY, nodeY};

        return coordinate;
    }


}
