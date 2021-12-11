package project.costruction.code;

import org.junit.Assert;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FigureTest {

    @org.junit.jupiter.api.Test
    void isTouchGround() {
        Figure figure1 = new Figure(1);
        while (!figure1.isCrossGround())
            figure1.stepDown();
        assertTrue(figure1.isTouchGround());
    }

    @org.junit.jupiter.api.Test
    void isCrossGround() {
        Figure figure1 = new Figure();
        while (!figure1.isTouchGround())
            figure1.stepDown();
        assertFalse(figure1.isCrossGround());
        figure1.stepDown();
        assertTrue(figure1.isCrossGround());
    }

    @org.junit.jupiter.api.Test
    void leaveOnTheGround() {
    }

    @org.junit.jupiter.api.Test
    void isTouchWallLeft() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        Figure figure1 = new Figure();
        for (int i = 0; i < 100; i++) {
            figure1.move(Gameplay.LEFT);
        }
        assertTrue(figure1.isTouchWall(Gameplay.LEFT));
        Figure figure2 = new Figure();
        assertFalse(figure2.isTouchWall(Gameplay.LEFT));
        Figure figure3 = new Figure(0);
        for (int i = 0; i < 2; i++) {
            figure3.move(Gameplay.LEFT);
        }
        assertFalse(figure3.isTouchWall(Gameplay.LEFT));
        figure3.move(Gameplay.LEFT);
        assertTrue(figure3.isTouchWall(Gameplay.LEFT));
    }

    @org.junit.jupiter.api.Test
    void isTouchWallRight() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        Figure figure1 = new Figure();
        for (int i = 0; i < 100; i++) {
            figure1.move(39);
        }
        assertTrue(figure1.isTouchWall(39));
        Figure figure2 = new Figure();
        assertFalse(figure2.isTouchWall(39));
        Figure figure3 = new Figure(0);
        for (int i = 0; i < 2; i++) {
            figure3.move(39);
        }
        assertFalse(figure3.isTouchWall(39));
        figure3.move(39);
        assertTrue(figure3.isTouchWall(39));
    }

    @org.junit.jupiter.api.Test
    void moveLeft() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        for (int i = 0; i < 7; i++) {
            Figure figure1 = new Figure(i);
            figure1.move(Gameplay.LEFT);
            Figure figure2 = new Figure(i);
            assertEquals(figure1.getX(), figure2.getX() - 1);
        }
    }

    @org.junit.jupiter.api.Test
    void moveRight() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        for (int i = 0; i < 7; i++) {
            Figure figure1 = new Figure(i);
            figure1.move(Gameplay.RIGHT);
            Figure figure2 = new Figure(i);
            assertEquals(figure1.getX(), figure2.getX() + 1);
        }
    }

    @org.junit.jupiter.api.Test
    void stepDown() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        for (int i = 0; i < 7; i++) {
            Figure figure1 = new Figure(i);
            figure1.stepDown();
            Figure figure2 = new Figure(i);
            assertEquals(figure1.getY(), figure2.getY() + 1);
        }
    }

    @org.junit.jupiter.api.Test
    void isWrongPosition() {
        Arrays.fill(Gameplay.mine[Gameplay.FIELD_HEIGHT], 1);
        Figure figure1 = new Figure(0);
        while(figure1.isTouchWall(Gameplay.RIGHT))
            figure1.move(Gameplay.RIGHT);
        assertFalse(figure1.isWrongPosition());
        Figure figure2 = new Figure(0);
        while(figure2.isTouchWall(Gameplay.LEFT))
            figure2.move(Gameplay.LEFT);
        assertFalse(figure2.isWrongPosition());
    }

    @org.junit.jupiter.api.Test
    void rotateShape() {
        Figure figure1 = new Figure(1);
        Figure figure2 = new Figure(1);
        figure2.rotate();
        assertEquals(figure1.getColor(), figure2.getColor());
        assertEquals(figure1.getX(), figure2.getX());
        assertEquals(figure1.getY(), figure2.getY());
    }
}