package com.bio.biosmart.UI;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class SeqEditShow {
    BorderPane seqAllLayout = new BorderPane();

    /**
     * Generate a layout to manage and edit given sequence. This layout
     * est ready to be injected into the workspace in the main window.
     *
     * @return Layout with all its elements (including those from other
     * created classes.
     */
    public BorderPane getSeqEditLayout(){
        // get text sequence layout
        SeqTextUI seqTextUI = new SeqTextUI();
        HBox seqTextLayout = seqTextUI.getSeqLayout();

        // get sequence band layout
        SeqBandUI seqBandUI = new SeqBandUI();
        ScrollPane seqBAndLayout = seqBandUI.getSeqLayout();

        // Put element into the parent layout
        seqAllLayout.setTop(seqTextLayout);
        seqAllLayout.setCenter(seqBAndLayout);

        return seqAllLayout;
    }

}
