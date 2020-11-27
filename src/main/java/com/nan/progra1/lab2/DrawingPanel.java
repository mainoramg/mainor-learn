package com.nan.progra1.lab2;

import com.nan.progra1.lab2.models.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static com.nan.progra1.lab2.Menu.PANEL_MAX_HEIGHT;
import static com.nan.progra1.lab2.Menu.PANEL_MAX_WIDTH;

/**
 * Allows to draw a rectangle in a JFrame
 *
 * @author Wendy Ram&iacute;rez
 */
public class DrawingPanel extends JPanel {

    private Point[] points;
    private final Random random = new Random();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        for (int i = 0; i < points.length; i++) {
            if (i == points.length - 1) {
                g.drawLine(points[i].getX(), points[i].getY(), points[0].getX(), points[0].getY());
            } else {
                g.drawLine(points[i].getX(), points[i].getY(), points[i + 1].getX(), points[i + 1].getY());
            }
        }
    }

    private void drawPoints(Point[] pointArray) {
        points = pointArray;
        repaint();
    }

    public static void draw(Point... points) {
        Point[] pointArray = points;
        JFrame marco = new JFrame("Dibujo de la figura");
        marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DrawingPanel drawing = new DrawingPanel();
        drawing.drawPoints(pointArray);
        marco.add(drawing);
        marco.setSize(PANEL_MAX_WIDTH, PANEL_MAX_HEIGHT);
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
        marco.dispose();
    }
}
