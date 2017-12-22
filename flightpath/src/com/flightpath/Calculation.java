package com.flightpath;

/**
 * Created by Arjun Bhalla on 16-Jul-17.
 */
public class Calculation {

    private static final double GRAVITY = -9.81;


    public static double[] findXVals(double initY,double initV,double angle){
        double[] xVals = new double[Graph.PRECISION];

        double vy = -initV*Math.sin(angle);
        double tTotal = 2*(vy/GRAVITY);
        double vx = initV*Math.cos(angle);
        double incTot = Graph.BOX_WIDTH/Graph.PRECISION;
        double tFragment = tTotal*incTot/50;
        double tLocal = tFragment;

        xVals[0] = 0.0;

        for (int i = 1; i < Graph.PRECISION; i++){
            xVals[i] = tLocal*vx;
            tLocal+=tFragment;
        }
        return xVals;
    }

    public static double[] findYVals(double initY,double initV,double angle){
        double[] yVals = new double[Graph.PRECISION];

        double vy = initV*Math.sin(angle);
        double tTotal = 2*(vy/GRAVITY);
        double tFragment = tTotal/Graph.PRECISION;
        double tTotLocal = tFragment;
        double yv = vy;
        yVals[0] = initY;

        for (int i = 1; i<(Graph.PRECISION/2)+1; i++){
            yVals[i] = yVals[i-1] - ((Math.pow(yv-(GRAVITY*tFragment),2.0)+Math.pow(yv,2.0)))/(2*GRAVITY);
            yv -= GRAVITY*tFragment;
            tTotLocal += tFragment;
            if(yVals[i] > Graph.BOX_HEIGHT){yVals[i] = Graph.BOX_HEIGHT;}
        }
        for (int i = (Graph.PRECISION/2); i<Graph.PRECISION;i++){
            yVals[i] = yVals[(Graph.PRECISION)-i];
        }
        return yVals;
    }
}
