package com.bio.biosmart.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class SeqEditShow {
    VBox seqAllLayout = new VBox(10);

    /**
     * Generate a layout to manage and edit given sequence. This layout
     * est ready to be injected into the workspace in the main window.
     *
     * @return Layout with all its elements (including those from other
     * created classes).
     */
    public VBox getSeqEditLayout(){
        // get text sequence layout
        SeqTextUI seqTextUI = new SeqTextUI();
        HBox seqTextLayout = seqTextUI.getSeqLayout();
        seqTextLayout.setFillHeight(true);
        seqTextLayout.setAlignment(Pos.CENTER);

        // get sequence band layout
        SeqBandUI seqBandUI = new SeqBandUI();
        ScrollPane seqBandLayout = seqBandUI.getSeqLayout();

        // Put element into the parent layout
        seqAllLayout.getChildren().addAll(
                seqTextLayout,
                seqBandLayout
        );
        // Layout policies
        seqAllLayout.setPadding(new Insets(10));
        seqAllLayout.setFillWidth(true);
        //seqAllLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(seqTextLayout, Priority.ALWAYS);

        return seqAllLayout;
    }

}
