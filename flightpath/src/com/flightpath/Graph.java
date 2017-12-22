package com.flightpath;

import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

/**
 * Created by Arjun Bhalla on 16-Jul-17.
 */
public class Graph extends JPanel{
    public static final int PRECISION = 20;

    protected static final int BOX_START_X = MainFrame.WIDTH/40;
    protected static final int BOX_START_Y = MainFrame.HEIGHT/8;
    public static final int BOX_WIDTH = 300;
    public static final int BOX_HEIGHT = 300;

    protected static final double Y_SCALE = BOX_HEIGHT/PRECISION;
    protected static final double X_SCALE = BOX_WIDTH/(PRECISION);

    private static final int LINES = 10;
    private final int INCREMENT_X = BOX_WIDTH/LINES;
    private static final int INCREMENT_Y = BOX_HEIGHT/LINES;

    private double[] yValuesFinal;
    private double[] xValuesFinal;
    private double y0;

    int[] lineStartX = new int[LINES];
    int[] lineStartY = new int[LINES];

    private boolean graphPressed = false;


    Graph(){
        calculateLineStarts();
    }
    public void setGraphPressed(boolean b){
        this.graphPressed = b;
    }

    public boolean getGraphPressed(){return this.graphPressed;}

    public void receiveXVals(double xv[]){
            xValuesFinal = xv;
    }
    public void receiveYVals(double yv[]){
        yValuesFinal = yv;
    }

    public void receiveYInit(double y){
        y0 = y;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(BOX_START_X,BOX_START_Y,BOX_WIDTH,BOX_HEIGHT);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.gray);
        int endYForX = BOX_START_Y+BOX_HEIGHT;
        int endXForY = BOX_START_X + BOX_WIDTH;


        // DRAW X GRIDLINES
        for(int i = 0; i<LINES; i++){
            g2.drawLine(lineStartX[i],BOX_START_Y,lineStartX[i],endYForX);
        }
        //DRAW Y GRIDLINES
        for(int i = 0; i<LINES; i++){
            g2.drawLine(BOX_START_X,lineStartY[i],endXForY,lineStartY[i]);
        }

        if (getGraphPressed()){

            setGraphPressed(false);
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));

            double x1 = BOX_START_X+xValuesFinal[0];
            double y1 = BOX_START_Y+BOX_HEIGHT-yValuesFinal[0];
            double x2 = BOX_START_X+xValuesFinal[1];
            double y2 = BOX_START_Y+BOX_HEIGHT-yValuesFinal[1];

            for (int i = 1; i < (PRECISION-1); i++){
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
                x1 = BOX_START_X+xValuesFinal[i];
                y1 =  BOX_START_Y+BOX_HEIGHT-yValuesFinal[i];
                x2 = BOX_START_X+xValuesFinal[i+1];
                y2 = BOX_START_Y+BOX_HEIGHT-yValuesFinal[i+1];
            }

            }
        }


    private void calculateLineStarts(){
        for (int i = 0; i != LINES; i++){
            lineStartX[i] = BOX_START_X+(i*INCREMENT_X);
            lineStartY[i] = (BOX_START_Y+(i*INCREMENT_Y));
        }
    }


}
