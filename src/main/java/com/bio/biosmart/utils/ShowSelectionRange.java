package com.bio.biosmart.utils;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class showSelectionRange {
   HBox selectionLayout = new HBox();
   private HBox getSelectTab(
           double lx1, double lx2,
           double ly1, double ly2,
           double rx1, double rx2,
           double ry1, double ry2,
           double BHeight, double BWidth
   ){
       // selection box
       Rectangle selectionShape = new Rectangle(BWidth, BHeight);
       selectionShape.setFill(Color.GREEN);

       // border lines
       Line leftLine = new Line(lx1, ly1, lx2, ly2);
       Line rightLine = new Line(rx1, ry1, rx2, ry2);

       // place in the layout
       selectionLayout.getChildren().add(0, leftLine);
       selectionLayout.getChildren().add(1, selectionShape);
       selectionLayout.getChildren().add(2, rightLine);

       return selectionLayout;
   }
}
