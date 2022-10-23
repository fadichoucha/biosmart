package com.bio.biosmart.UI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class SeqTextUI {
    public HBox getSeqLayout(){
        HBox seqLayout = new HBox(10);
        TextArea textBox = new TextArea();
        textBox.setStyle("-fx-highlight-fill: lightgray;" +
                " -fx-highlight-text-fill: firebrick;" +
                " -fx-font-size: 14px;");

        // Add to the seqLayout
        seqLayout.getChildren().addAll(
                textBox
        );
        return seqLayout;
    }
}
