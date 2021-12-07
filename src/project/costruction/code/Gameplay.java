package project.costruction.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class Gameplay {

    final String TITLE_OF_PROGRAM = "Tetris"; //название окна
    static final int BLOCK_SIZE = 25; //размер одного блока
    static final int ARC_RADIUS = 6; //закругление краев
    final int FIELD_WIDTH = 10; //количество блоков в ширину
    final int FIELD_HEIGHT = 18; // в высоту
    final int START_LOCATION = 180; // начальное положение
    final int FIELD_DX = 7; // смещение для нормального размера окна в ширину
    final int FIELD_DY = 26; // смещение для нормального размера окна в высоту
    final int LEFT = 37; //кодировки кнопки влево
    final int UP = 38; //вверх
    final int RIGHT = 39; // вправо
    final int DOWN = 40; // вниз
    final int SHOW_DELAY = 350; // задержка хода
    final int[][][] SHAPES = { //массив всех фигур
            {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {4, 0x00f0f0}}, // I
            {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {4, 0xf0f000}}, // O
            {{1, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x0000f0}}, // J
            {{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf0a000}}, // L
            {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x00f000}}, // S
            {{1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xa000f0}}, // T
            {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf00000}}  // Z
    };
    final int[] SCORES = {100, 300, 700, 1500}; // счета за закрытия строки
    int gameScore = 0;
    int[][] mine = new int[FIELD_HEIGHT + 1][FIELD_WIDTH]; // поле длля игры
    JFrame frame; //наши окна
    Canvas canvas = new Canvas();
    Random random = new Random();
    Figure figure = new Figure();
    boolean gameOver = false;
    final int[][] GAME_OVER_MSG = { //код для конча игры
            {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0}};

    void run() {
        frame = new JFrame(TITLE_OF_PROGRAM); //создаем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // добавляем обработчик закрытия окна
        frame.setSize(FIELD_WIDTH * BLOCK_SIZE + FIELD_DX, FIELD_HEIGHT * BLOCK_SIZE + FIELD_DY); // задаем размеры окна
        frame.setLocation(START_LOCATION, START_LOCATION); // задаем первоначальное положение окна
        frame.setResizable(false); // делаем окно константного размера
        canvas.setBackground(Color.black); // задаем задний цвет
        frame.addKeyListener(new KeyAdapter() { // задаем обработчик нажатий клавиш
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
                    switch (e.getKeyCode()) {
                        case DOWN -> figure.drop();//опускаем сразу вниз
                        case UP -> figure.rotate();//делаем поворот по часовой
                        case LEFT -> figure.move(LEFT); //смещаем влево
                        case RIGHT -> figure.move(RIGHT); //или вправо
                        default -> {
                        }
                    }
                    canvas.repaint();
                }
            }
        });
        frame.setVisible(true); //показываем наше окно

        Arrays.fill(mine[FIELD_HEIGHT], 1); //инициализурем поле

        while (!gameOver) { //идем пока игра не закончилась
            try {
                Thread.sleep(SHOW_DELAY); //делаем задрежку
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            canvas.repaint();
            if (figure.isTouchGround()) { // проверяем дошли ли до конца
                figure.leaveOnGround(); // вставляем фигуру вниз
                checkFilling(); // проверяем на заполнение строк
                figure = new Figure(); // создаем новую фигуру
                gameOver = figure.isCrossGround(); // проверяем на конец игры
            } else {
                figure.stepDown(); //опускаем фигуру на один вниз, если еще не дошла до конца
            }
        }
    }

    void checkFilling() {

    }
}
