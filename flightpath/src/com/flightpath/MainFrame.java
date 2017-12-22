package com.flightpath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Details the MainFrame
 * Arjun Bhalla, 14.7.17
 */
public class MainFrame extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel();
    JPanel entryPane = new JPanel();
    JPanel withoutAirResistance = new JPanel();
    JPanel diffEq = new JPanel();

    JLabel initialVelocityLabel = new JLabel("Initial Velocity: ");
    static JTextField initialVelocity = new JTextField(4);
    JPanel ivPanel = new JPanel();

    JLabel initialYPosLabel = new JLabel("Y start: ");
    static JTextField initialYPos = new JTextField(4);
    JPanel yPanel = new JPanel();

    JLabel angleLabel = new JLabel("Angle: ");
    static JTextField angle = new JTextField(4);
    JPanel anglePanel = new JPanel();

    JButton graphButton = new JButton("Graph");
    JPanel emptyPanel = new JPanel();
    JPanel buttonPanelFirst = new JPanel();

    Graph graph = new Graph();

    private double iv;
    private double pos;
    private double ang;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static void main(String[] args){
                MainFrame frame = new MainFrame();
            }

    public void setComps(){
        iv = Double.parseDouble(this.initialVelocity.getText());
        pos = Double.parseDouble(this.initialYPos.getText());
        ang = getAngle();
    }

    public double getIv() {
        return iv;
    }

    public double getPos() {
        return pos;
    }

    public double getAng() {
        return ang;
    }

    public MainFrame() {
        super("Flight Path Calculator");
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //Layout Managers
        GridLayout mainLayout = new GridLayout(1,2);
        mainPanel.setLayout(mainLayout);

        GridLayout entry = new GridLayout(2,1);
        entryPane.setLayout(entry);

        GridLayout noAirResist = new GridLayout(4,2);
        withoutAirResistance.setLayout(noAirResist);

        FlowLayout entryComps = new FlowLayout(FlowLayout.RIGHT);

        //adding components to panels

        ivPanel.add(initialVelocityLabel);
        ivPanel.add(initialVelocity);
        ivPanel.setLayout(entryComps);

        yPanel.add(initialYPosLabel);
        yPanel.add(initialYPos);
        yPanel.setLayout(entryComps);

        anglePanel.add(angleLabel);
        anglePanel.add(angle);
        anglePanel.setLayout(entryComps);

        buttonPanelFirst.add(emptyPanel);
        buttonPanelFirst.add(graphButton);
        graphButton.addActionListener(this);
        buttonPanelFirst.setLayout(entryComps);

        withoutAirResistance.add(ivPanel);
        withoutAirResistance.add(yPanel);
        withoutAirResistance.add(anglePanel);
        withoutAirResistance.add(buttonPanelFirst);
        withoutAirResistance.setMaximumSize(new Dimension(WIDTH/2,HEIGHT/2));

        // TODO : INSETS!!!!

        entryPane.add(withoutAirResistance);
        entryPane.add(diffEq);
        entryPane.setMaximumSize(new Dimension(WIDTH/2,HEIGHT));

        mainPanel.add(graph);
        mainPanel.add(entryPane);

        add(mainPanel);

    }

    public static double getAngle(){
        double ang = Math.toRadians(Double.parseDouble(angle.getText()));
        return ang;
    }

    public void sendVals(){
        double[] x = Calculation.findXVals(getPos(),getIv(),getAng());
        double[] y = Calculation.findYVals(getPos(),getIv(),getAng());
        graph.receiveXVals(x);
        graph.receiveYVals(y);
        graph.receiveYInit(getPos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setComps();
        sendVals();
        graph.setGraphPressed(true);
        graph.repaint();

    }
}



