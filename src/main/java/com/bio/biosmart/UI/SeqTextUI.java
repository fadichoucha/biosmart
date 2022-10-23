package com.bio.biosmart.UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SeqTextUI {
    public HBox getSeqLayout(){
        // Set layout
        HBox seqLayout = new HBox(10);

        // Set elements
        TextArea textBox = new TextArea();
        textBox.setMinHeight(300);
        textBox.setStyle(
                "-fx-highlight-fill: lightgray;" +
                " -fx-highlight-text-fill: firebrick;" +
                " -fx-font-size: 14px;");

        // Add to the seqLayout
        seqLayout.getChildren().addAll(textBox);

        // layout policies & priorities
        HBox.setHgrow(textBox, Priority.ALWAYS);

        return seqLayout;
    }
}
