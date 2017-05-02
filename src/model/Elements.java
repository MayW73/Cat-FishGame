package model;


import java.awt.*;

/**
 * represent all the elements in the game
 */
public abstract class Elements {
    protected int x;
    protected int y;
    protected int width;
    protected int height;


    //Constructs an element
    //Effects: element is at the specified location with given width and height.
    public Elements(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Moves the element
    // modifies: this
    // effects:  element has been moved
    public void move() {
        handleBoundary();
    }

    // Draws the element
    // modifies: g
    // effects: draws the element on the Graphics object g
    public abstract void draw(Graphics g);

    // Constrains element so that it doesn't travel off sides of screen
    // modifies: this
    // effects: sprite is constrained to remain within horizontal boundaries of game
    protected void handleBoundary() {
        if (x < 0)
            x = 0;
        else if (x > CFGame.WIDTH)
            x = CFGame.WIDTH;
    }
}
