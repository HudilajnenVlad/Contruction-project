package project.costruction.code;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Music music = new Music();
        Thread thread = new Thread(music);
        thread.start();
        new Gameplay().run();
    }

}
