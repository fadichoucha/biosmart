package com.bio.biosmart.utils;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ShowSelectionRange {
   HBox selectionLayout = new HBox();
    double lx1; // Left X start point
    double lx2; // Left X end point
    double ly1; // Left Y start point
    double ly2; // Right Y end point
    double rx1; // Right X start point
    double rx2; // Right X end point
    double ry1; // Right Y start point
    double ry2; // Right Y end point
    double BHeight; // Height of selection box
    double BWidth; // Width of selection box


   public ShowSelectionRange(
           double lx1, double lx2,
           double ly1, double ly2,
           double rx1, double rx2,
           double ry1, double ry2,
           double BHeight, double BWidth
   ){
       this.lx1 = lx1;  this.lx2 = lx2;
       this.ly1 = ly1; this.ly2 = ly2;
       this.rx1 = rx1; this.rx2 = rx2;
       this.ry1 = ry1; this.ry2 = ry2;
       this.BHeight = BHeight; this.BWidth = BWidth;
   }

   public HBox getSelectTab(

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
