package spellingBee.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

public class Honeycomb extends JComponent {

    private static final Color OUTER_HEXAGON_COLOR = new Color(224, 224, 224);
    private static final Color CENTER_HEXAGON_COLOR = new Color(255, 222, 0);

    private char[] outerLetters;
    private char centerLetter;

    // Positions of the vertices of the hexagons
    private int[][] xPoints = { { 140, 170, 230, 260, 230, 170 }, // hexagon 1
            { 235, 265, 325, 355, 325, 265 }, // hexagon 2
            { 235, 265, 325, 355, 325, 265 }, // hexagon 3
            { 140, 170, 230, 260, 230, 170 }, // hexagon 4
            { 45, 75, 135, 165, 135, 75 }, // hexagon 5
            { 45, 75, 135, 165, 135, 75 }, // hexagon 6
            { 140, 168, 232, 261, 232, 168 } // hexagon 7
    };

    private int[][] yPoints = { { 120, 69, 69, 120, 171, 171 }, // hexagon 1
            { 171, 120, 120, 171, 222, 222 }, // hexagon 2
            { 278, 227, 227, 278, 329, 329 }, // hexagon 3
            { 329, 278, 278, 329, 380, 380 }, // hexagon 4
            { 278, 227, 227, 278, 329, 329 }, // hexagon 5
            { 171, 120, 120, 171, 222, 222 }, // hexagon 6
            { 224, 175, 175, 224, 274, 274 } // hexagon 7
    };

    /**
     * @param letters letters for the outer cells
     * @param center  the center letter for the center cell
     */
    public Honeycomb(char[] letters, char center) {
        super();

        this.outerLetters = letters;
        this.centerLetter = center;

    }

    public void paintComponent(Graphics hex) {
        super.paintComponent(hex);

        for (int i = 0; i < xPoints.length; i++) {
            if (i == 6) {
                hex.setColor(CENTER_HEXAGON_COLOR);
                hex.fillPolygon(xPoints[6], yPoints[6], 6);
            } else {
                hex.setColor(OUTER_HEXAGON_COLOR);
                hex.fillPolygon(xPoints[i], yPoints[i], 6);
            }
        }

        drawLetters(hex);

    }

    /**
     * draw letters in each cell in honeycomb
     * 
     * @param g
     */
    public void drawLetters(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 35);
        g.setFont(font);

        int centerX;
        int centerY;
        String letter = null;

        for (int i = 0; i < xPoints.length; i++) {

            // display letter in caps
            if (i == 6) {
                // center letter
                letter = String.valueOf(centerLetter).toUpperCase();

            } else {
                letter = String.valueOf(outerLetters[i]).toUpperCase();
            }

            centerX = getCenterX(xPoints[i]);
            centerY = getCenterY(yPoints[i]);

            FontMetrics metrics = g.getFontMetrics();
            int letterWidth = metrics.stringWidth(letter);
            int letterHeight = metrics.getHeight();

            // calculate center coordinates to position letters at the center of
            // the hexagon
            g.drawString(letter, centerX - (letterWidth / 2),
                    centerY + (letterHeight / 2));

        }
    }

    /**
     * Calculate center x coordinate of the hexagon
     * 
     * @param xPoints array of x-coordinates of verticies of hexagon
     */
    static int getCenterX(int[] xPoints) {
        int sum = 0;
        for (int x : xPoints) {
            sum += x;
        }
        return sum / xPoints.length;
    }

    /**
     * Calculate center y-coordinate of the hexagon
     * 
     * @param yPoints array of y-coordinates of verticies of hexagon
     */
    static int getCenterY(int[] yPoints) {
        int sum = 0;
        for (int y : yPoints) {
            sum += y;
        }

        return (sum / yPoints.length) - 10;
    }

    /**
     * shuffle letters in the outer cells when the shuffle button is clicked
     * 
     * @param letters letters to shuffle
     * @return new order of letters in the letters array
     */
    public char[] shuffleLetters(char[] letters) {
        // randomly choose letters to set up in each cell
        // display letters
        // center remains same

        Random rand = new Random();

        // new character array to store the shuffled letters
        char[] result = new char[6];

        for (int i = 0; i < 6; i++) {
            int newRandomIndex = rand.nextInt(6);

            char temp = letters[newRandomIndex];

            result[newRandomIndex] = letters[i];

            result[i] = temp;

            // update letters as well
            letters[newRandomIndex] = letters[i];
            letters[i] = temp;

        }

        return result;
    }

    public void setOuterLetters(char[] letters) {
        this.outerLetters = letters;
    }

}
