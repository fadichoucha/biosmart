package com.bio.biosmart.UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeqEditShow extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage seqStage) throws Exception {
        GridPane seqLayout = getSeqEditShowLayout();
        Scene seqEditShowScene = new Scene(seqLayout);
        seqStage.setScene(seqEditShowScene);
        seqStage.show();
    }
    /**
     * Layout defines UI element for sequence edit and sequence show.
     */
    private GridPane getSeqEditShowLayout(){
        // Central layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(10);

        // Sequence show layout
        VBox seqLayout = new VBox(10);
        seqLayout.setMinHeight(100);
        // sequence tools

        // Add elements to the central layout
        layout.add(seqLayout,0,1,20,5);
        return layout;
    }


}
