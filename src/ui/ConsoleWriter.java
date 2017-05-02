package ui;

import model.CFGame;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yuwang on 2017-05-01.
 */
public class ConsoleWriter implements Observer {
    @Override
    public void update(Observable subject, Object event) {
        if (CFGame.EVENT_FISH_CAUGHT.equals(event))
            System.out.println("Wow: Fish Caught!");
    }

}
