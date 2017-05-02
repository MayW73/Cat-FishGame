package model;

import java.awt.*;

/**
 * Created by yuwang on 2017-05-01.
 */
public class Fish extends Elements {
    public static final int DY = 3;
    public static final int SIZE_X = 10;
    public static final int SIZE_Y = 20;
    private static final Color COLOR = new Color(10, 50, 188);
    private static final int JIGGLE_X = 1;

    // Constructor
    // Effects: invader is at position (x, y)
    public Fish(int x, int y) {
        super(x, y, SIZE_X, SIZE_Y);
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        int px1 = getX()-SIZE_X;
        int px2 = getX();
        int px3 = getX()+SIZE_X;
        int py1 = getY();
        int py2 = getY()-SIZE_Y;
        Polygon tail = new Polygon(new int[]{px1,px2,px3}, new int[]{py2, py1,py2}, 3);
        g.fillPolygon(tail);
        g.setColor(savedCol);
    }

    @Override
    public void move() {
        x = x + CFGame.RND.nextInt(2 * JIGGLE_X + 1) - JIGGLE_X;
        y = y + DY;

        super.move();
    }

    // Has fish collided with cat?
    // Effects: returns true if this Fish has collided with other Sprite; false otherwise
    public boolean collidedWith(Elements other) {
        Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2,
                getY() - getHeight() / 2,
                getWidth(),
                getHeight());
        Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2,
                other.getY() - other.getHeight() / 2,
                other.getWidth(),
                other.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }
}
