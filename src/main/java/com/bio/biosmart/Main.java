package com.bio.biosmart;

import com.bio.biosmart.UI.SeqEditShow;
import com.bio.biosmart.UI.SeqTextUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/** The main window in the software provide simple interface and
 * access to all other tools. */
public class Main extends Application {
    public static void main(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        *  A BorderPane divides the workspace to host other elements.
        */
        // Root ===================================================
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);

        // Tool Bar =======================================================
        MenuBar mainMenu = new MenuBar();

        // create menus
        Menu fileMenu = new Menu("File");
        Menu seqMenu = new Menu("Seq Tools");
        Menu ngsMenu = new Menu("NGS Tools");

        // create Menu's items
        MenuItem importItem = new MenuItem("Import sequence");
        MenuItem exitItem = new MenuItem("Exit");

        // Assign Items to menus
        fileMenu.getItems().addAll(importItem, exitItem);

        // Add submenus to the main menu
        mainMenu.getMenus().addAll(fileMenu, seqMenu, ngsMenu);

        // workspace ======================================================
        /*
        * Workspace is a place for work. This place is changeable and defined
        * using layouts. Each layout is provided with its elements which form
        * a workspace. A workspace may be empty by using an empty Label for
        * example.
        */

        SeqEditShow seqEditShow = new SeqEditShow();
        BorderPane seqLayout = seqEditShow.getSeqEditLayout();


        // Status bar =====================================================
        HBox statusBar = new HBox(10);
        statusBar.setPadding(new Insets(5,15,5,15));
        Label statusLabel = new Label("Status: ");

        statusBar.getChildren().addAll(statusLabel);


        // Locate elements on the root  ===================================
        root.setTop(mainMenu);
        root.setBottom(statusBar);
        root.setCenter(seqLayout);

        // Application =====================================================
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("BioSmart");
        primaryStage.show();
    }
}





