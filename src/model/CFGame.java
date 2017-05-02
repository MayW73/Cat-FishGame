package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by yuwang on 2017-05-01.
 */
public class CFGame extends Observable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Random RND = new Random();

    public static final String EVENT_GAME_STARTED = "GAME STARTED";
    public static final String EVENT_GAME_OVER = "GAME OVER";
    public static final String EVENT_FISH_SPOTTED = "FISH SPOTTED";
    public static final String EVENT_FISH_CAUGHT = "FISH HIT";


    private List<Elements> elements;
    private Cat cat;
    private boolean isGameOver;
    private int numFishCaught;

    // Constructor
    // Effects: sets up the game
    public CFGame() {
        elements = new ArrayList<Elements>();
        initializeElements();
        reset();
    }

    // Updates the game on clock tick
    // modifies: this
    // effects:  updates tank, missiles and invaders
    public void update() {
        moveElements();
        invade();
        checkCollisions();
        checkGameOver();
    }

    // Responds to key press codes
    // modifies: this
    // effects:  turns tank, fires missiles and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)
            cat.faceLeft();
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)
            cat.faceRight();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void draw(Graphics g) {
        for (Elements aSprite: elements)
            aSprite.draw(g);
    }

    // Is game over?
    // Effects: returns true if game is over, false otherwise
    public boolean isOver() {
        return isGameOver;
    }



    public int getNumFishCaught() {
        return numFishCaught;
    }

    public List<Elements> getElements() {
        return elements;
    }

    public Cat getCat() {
        return cat;
    }

    // moves the element
    // modifies: this
    // effects: moves elements to location at next time
    private void moveElements() {
        for (Elements next : elements) {
            next.move();
        }
    }

    // Sets / resets the game
    // modifies: this
    // effects:  resets number of fish caught;
    //           game is not over
    private void reset() {
        isGameOver = false;
        numFishCaught = 0;
        setChanged();
        notifyObservers(EVENT_GAME_STARTED);
    }

    // Initializes elements
    // modifies: this
    // effects:  sets up list of elements with no fish
    //           and cat halfway across screen
    private void initializeElements() {
        elements.clear();
        cat = new Cat(WIDTH / 2);
        elements.add(cat);
    }




    // RunFish!
    // modifies: this
    // effects: randomly generates new fish at top of screen with random x coordinate.
    private void invade() {
        if (RND.nextInt(250) < 1) {
            Fish i = new Fish(RND.nextInt(WIDTH), 20);
            elements.add(i);
            setChanged();
            notifyObservers(EVENT_FISH_SPOTTED);
        }
    }

    // Checks for collisions between an cat and a fish
    // modifies: this
    // effects:  removes any fish that has been caught by the cat
    private void checkCollisions() {
        List<Elements> toBeRemoved = new ArrayList<Elements>();
        for (Elements next : elements) {
            if (next instanceof Fish) {
                if(((Fish) next).collidedWith(cat)){
                    toBeRemoved.add(next);
                    numFishCaught++;
                    setChanged();
                    notifyObservers(EVENT_FISH_CAUGHT);
                }
            }
        }

        elements.removeAll(toBeRemoved);
    }


    // Is game over? (Has an invader managed to land?)
    // modifies: this
    // effects:  if an invader has landed, game is marked as
    //           over and lists of invaders & missiles cleared
    private void checkGameOver() {
        for (Elements next : elements) {
            if (next.getY() > HEIGHT)
                isGameOver = true;
        }

        if (isGameOver) {
            initializeElements();
            setChanged();
            notifyObservers(EVENT_GAME_OVER);
        }
    }
}
