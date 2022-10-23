package com.bio.biosmart.UI;

import com.bio.biosmart.seqtools.ImportSeq;
import com.bio.biosmart.utils.SearchString;
import com.bio.biosmart.utils.ShowSelectionRange;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SeqBandUI {
    double clickedStartX;
    double clickedStartY;
    double clickedEndX;
    double clickedEndY;
    HBox seqInnerLayout;
    StackPane seqBandPane = new StackPane();
    HBox selectedSeqBox;

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
     * Represent a sequence in cleaner and notable way.
     * @return Layout contains essential elements to show sequence as interactive
     * unite-box sequence.
     */
    public ScrollPane getSeqLayout(){
        // Central layout
        ScrollPane layout = new ScrollPane();
        layout.setBackground(new Background(new BackgroundFill(Color.ORCHID,CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setMinHeight(150);
        layout.setMinWidth(500);
        layout.setPadding(new Insets(10));


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
