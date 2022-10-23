package com.bio.biosmart.UI;

import com.bio.biosmart.seqtools.ImportSeq;
import com.bio.biosmart.utils.SearchString;
import com.bio.biosmart.utils.ShowSelectionRange;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class SeqEditShow extends Application {
    double clickedStartX;
    double clickedStartY;
    double clickedEndX;
    double clickedEndY;
    BorderPane mainLayout; // main layout for the sequence edit window
    HBox seqInnerLayout;

    StackPane seqBandPane = new StackPane();

    HBox selectedSeqBox;


    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage seqStage) throws Exception {
        mainLayout = new BorderPane();
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

    // Events Handlers =================================================

    /**
     * Action when click a sequence element in the sequence layout
     */
    EventHandler<MouseEvent> elementClickedStart = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            StackPane target = (StackPane) event.getSource();
            double xx = target.getLayoutX();
            double yy = target.getLayoutY();

            if (clickedStartX == 0){
                clickedStartX = xx;
                clickedStartY = yy;
                System.out.println("--> X::: " + xx);
                System.out.println("--> Y::: " + yy);
            } else {
                clickedEndX = xx;
                clickedEndY = yy;
                System.out.println("<-- X::: " + xx);
                System.out.println("<-- Y::: " + yy);
                showSelectedSeqRange();
            }
        }
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

    EventHandler<MouseEvent> rangeSelection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("------");
        }
    };

    // END ===========================================================


    /**
     * Layout defines UI element for sequence edit and sequence show.
     */
    private ScrollPane getSeqEditShowLayout(){
        // Central layout
        ScrollPane layout = new ScrollPane();
        layout.setBackground(new Background(new BackgroundFill(Color.ORCHID,CornerRadii.EMPTY,Insets.EMPTY)));
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

        // get file content as String
        String fileContentStr = ImportSeq.getEntireContent("data/rawSeq.txt");
        HBox seqBandLayout = getSeqLayout(
                ImportSeq.getEntireContent("data/rawSeq.txt")
        );
        // Put the sequence inner layout in a Stack pane
        seqBandPane.setBackground(new Background(new BackgroundFill(Color.ORANGERED,CornerRadii.EMPTY,Insets.EMPTY)));
        seqBandPane.getChildren().add(0,seqBandLayout);

        // TESTS
        SearchString searcher = new SearchString(fileContentStr, "cagtcgtgt");
        ArrayList<Integer[]> matchedIndex = searcher.getMatchedIndex();
        System.out.println("matchedIndex:: ");
        for (Integer[] index: matchedIndex) {
            System.out.println(index[0] + " to "+  index[1]);
        }

        // Add elements to the central layout
        layout.setContent(seqBandPane);
        layout.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //GridPane.setHgrow(seqBandLayout, Priority.ALWAYS);
        //GridPane.setHalignment(seqBandLayout, HPos.CENTER);
        //GridPane.setValignment(seqBandLayout, VPos.CENTER);

        return layout;
    }

    private HBox getSeqLayout(String seq){
        seqInnerLayout = new HBox(0);
        //convert string to ArrayList
        ArrayList<Character> sequence = new ArrayList<>();
        char[] seqChar= seq.toCharArray();
        for (char x:
             seqChar) {
            sequence.add(Character.toUpperCase(x));
        }
        // create the sequence inner-layout
        seqInnerLayout.setBackground(
             new Background(
                     new BackgroundFill(
                        Color.LIGHTBLUE,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                     )
             )
        );
         // Fill the layout with sequence elements
        for (int i = 0; i < sequence.size(); i++) {
            // wrap N
            Character element = sequence.get(i);
            // Each character is contained in individual 'Box'
            StackPane wrappedElement = wrapElement(
                    String.valueOf(element),
                    15,
                    20,
                    getNucleotideColor(element)
            );

            // Events -------------------------
            //wrappedElement.addEventFilter(MouseEvent.MOUSE_ENTERED, mousEvent);
            wrappedElement.addEventHandler(MouseEvent.MOUSE_CLICKED, elementClickedStart);

            // Add to the sequence near layout
            seqInnerLayout.getChildren().add(
                    i, // Index of the element in the sequence inner-layout
                    wrappedElement
            );
            seqInnerLayout.setAlignment(Pos.CENTER);
        }

         return seqInnerLayout;
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

    private void showSelectedSeqRange(){
        selectedSeqBox = new ShowSelectionRange(
                clickedStartX, clickedStartX,
                clickedStartY, clickedStartY + 50.0,
                clickedEndX+ 15.0, clickedEndX + 15.0,
                clickedEndY, clickedEndY + 50.0,
                15.0, clickedEndX - clickedStartX + 15
        ).getSelectTab();
        selectedSeqBox.setTranslateX(clickedStartX);
        seqBandPane.getChildren().add(1, selectedSeqBox);

        // Set zero values for X and Y
        clickedStartX = 0;
        clickedStartY = 0;
        clickedEndX = 0;
        clickedEndY = 0;
    }

    private Double[] getNodeCoordinates(Node node){
        double nodeX = node.getLayoutX();
        double nodeY = node.getLayoutY();
        Double[] coordinate = {nodeY, nodeY};

        return coordinate;
    }


}
