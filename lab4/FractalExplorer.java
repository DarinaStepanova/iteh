import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
public class FractalExplorer {
    private int displaySize; //сторона квадрата в пикселях
    private JImageDisplay display; //рисовать пиксели, стирать, работа с изображением
    private FractalGenerator fractal; //
    private Rectangle2D.Double initRange; //класс для работы с прямоугольникамиб переменная хранит координаты х и у, высоту и ширину
    public FractalExplorer (int displaySize) {
        this.displaySize = displaySize;
        fractal = new Mandelbrot(); //т.к. мондельброт наследуется от фрактал.генератора, мы можем поместить объект класса мондельброт в переменную с типом данных фракт-ген
        initRange = new Rectangle2D.Double();
        fractal.getInitialRange(initRange);
    }
    class ResetButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(initRange);
            drawFractal();
        }
    }
    class ClickMouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            display.clearImage();
            int x = event.getX();
            int y = event.getY();
            double xF = fractal.getCoord(initRange.x, initRange.x + initRange.width, displaySize, x);
            double yF = fractal.getCoord(initRange.y, initRange.y + initRange.height, displaySize, y);
            fractal.recenterAndZoomRange(initRange, xF, yF, 0.5);
            drawFractal();
        }
    }
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JImageDisplay(displaySize, displaySize); //создается объект для работы с изображением
        frame.add(display, BorderLayout.CENTER); //прикрепляем картинку к окну приложения по центру
        JButton reset = new JButton("RESET");
        ResetButton resetBut = new ResetButton();
        reset.addActionListener(resetBut);
        ClickMouse click = new ClickMouse();
        display.addMouseListener(click);
        frame.add(reset, BorderLayout.SOUTH);
        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);
        drawFractal();
    }
    public static void main(String[] args) {
        FractalExplorer goPaint = new FractalExplorer(800);
        goPaint.createAndShowGUI();
    }
    private void drawFractal() {
        for (int i = 0; i < displaySize; i++) {
            for (int j = 0; j < displaySize; j++) {
                double x = fractal.getCoord(initRange.x, initRange.x + initRange.width, displaySize, i);
                double y = fractal.getCoord(initRange.y, initRange.y + initRange.height, displaySize, j);
                int iteration = fractal.numIterations(x, y); //сколько итераций у фрактала в данной точке
                if (iteration == -1) {
                    display.drawPixel(i, j, 0);
                }
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f); //чем больше итераций, тем светлее цвет
                    display.drawPixel(i, j, rgbColor);
                }
            }
        }
        display.repaint();
    }
}
