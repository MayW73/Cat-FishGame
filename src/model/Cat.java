package model;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 * represent the cat in the game
 */
public class Cat extends Elements {
    public static final int DX = 4;
    private static final int SIZE_X = 40;
    private static final int SIZE_Y = 60;
    private static final int CAT_Y = CFGame.HEIGHT - 60;
    private static final Color COLOR = new Color(250, 128, 20);
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private int direction;

    // Constructs a cat
    // Effects: cat is located at position (x, TANK_Y)
    public Cat(int x) {
        super(x, CAT_Y, SIZE_X, SIZE_Y);
        direction = RIGHT;
    }

    // EFFECTS: returns true if cat is facing right, false otherwise
    public boolean isFacingRight() {
        return direction == RIGHT;
    }

    // Faces cat to the right
    // modifies: this
    // effects: cat is facing right
    public void faceRight() {
        direction = RIGHT;
    }

    // Faces cat to the left
    // modifies: this
    // effects: cat is facing left
    public void faceLeft() {
        direction = LEFT;
    }

    @Override
    public void move() {
        x = x + direction * DX;

        super.move();
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        Double radius = (double) (SIZE_X/2);
        g.fillOval(getX() -25, getY()-SIZE_Y-10,50,50);
        int px1 = getX()-25;
        int px2 = getX()-15;
        int px3 = getX()- 5;
        int py1 = getY()-SIZE_Y-20;
        int py2 = getY()-50;
        int px4 = getX()+25;
        int px5 = getX()+15;
        int px6 = getX()+5;
        Polygon ear1 = new Polygon(new int[]{px1,px2,px3}, new int[]{py2, py1, py2}, 3);
        Polygon ear2 = new Polygon(new int[]{px4,px5,px6},new int[]{py2,py1,py2},3);
        g.fillPolygon(ear1);
        g.fillPolygon(ear2);
        int tx1 = getX()+SIZE_X/2;
        int tx2 = getX()+SIZE_X;
        int tx3 = getX()+50;
        int tx4 = getX()+60;
        int ty1 = getY()+SIZE_Y/2;
        int ty2 = getY()+25;
        int ty3 = getY()+20;
        int ty4 = getY();
        Polygon tail = new Polygon(new int[]{tx1,tx2,tx3,tx4},new int[]{ty1,ty2,ty3,ty4}, 4);
        g.fillPolygon(tail);
        g.setColor(savedCol);

        Graphics2D g2 = (Graphics2D)g;
        Ellipse2D.Double eye1 = new Ellipse2D.Double((double)px2, (double)getY()-55, 5, 5);
        g2.draw(eye1);
        g2.setColor(Color.BLACK);
        g2.fill(eye1);

        Ellipse2D.Double eye2 = new Ellipse2D.Double((double)getX()+10, (double)getY()-55,5,5);
        g2.draw(eye2);
        g2.setColor(Color.BLACK);
        g2.fill(eye2);

        Arc2D.Double mouth = new Arc2D.Double((double)getX()-5, (double)getY()-50, 10, 10, 0, -180, Arc2D.OPEN);
        g2.draw(mouth);


    }


}
