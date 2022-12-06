import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.io.*;
public class FractalExplorer {
    private int displaySize; //сторона квадрата в пикселях
    private JImageDisplay display; //рисовать пиксели, стирать, работа с изображением
    private FractalGenerator fractal; //
    private Rectangle2D.Double initRange; //класс для работы с прямоугольникамиб переменная хранит координаты х и у, высоту и ширину

    private JComboBox<FractalGenerator> comboBox;
    //Конструктор класса
    public FractalExplorer (int displaySize) {
        this.displaySize = displaySize;
        fractal = new Mandelbrot(); //т.к. мондельброт наследуется от фрактал.генератора, мы можем поместить объект класса мондельброт в переменную с типом данных фракт-ген
        initRange = new Rectangle2D.Double();
        fractal.getInitialRange(initRange);
    }
    //Имплементируем интерфейс ActionListener для кнопок
    class The implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("RESET".equals(command)) {
                fractal.getInitialRange(initRange);
                drawFractal();
            }
            else if ("comboBoxChanged".equals(command)) {
                fractal = (FractalGenerator) comboBox.getSelectedItem();
                fractal.getInitialRange(initRange);
                drawFractal();
            }
            else if ("SAVE".equals(command)) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String path = file.toString();
                    if (path.length()==0) {
                        return;
                    }
                    if (!path.contains(".png")) {
                        file = new File(path + ".png");
                    }
                    try {
                        javax.imageio.ImageIO.write(display.getImage(), "png", file);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }
    }
    //Наследуем MouseAdapter для обработки событий мыши
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
    //Метод для инициализации графического интерфейса Swing
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JImageDisplay(displaySize, displaySize); //создается объект для работы с изображением
        frame.add(display, BorderLayout.CENTER); //прикрепляем картинку к окну приложения по центру
    //обработчик
        The handler = new The();
    //panels top and bottom
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
    //Label: Fractal
        JLabel label = new JLabel("Fractal: ");
        top.add(label, BorderLayout.WEST);
    //combo-box
        comboBox = new JComboBox<FractalGenerator>();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(handler);
        top.add(comboBox, BorderLayout.EAST);
    //save
        JButton save = new JButton("SAVE");
        save.addActionListener(handler);
        bottom.add(save, BorderLayout.WEST);
    //reset
        JButton reset = new JButton("RESET");
        reset.addActionListener(handler);
        bottom.add(reset, BorderLayout.EAST);
    //top and bottom frames
        frame.add(bottom,BorderLayout.SOUTH);
        frame.add(top, BorderLayout.NORTH);
    //mouse
        ClickMouse click = new ClickMouse();
        display.addMouseListener(click);

        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);
        drawFractal();
    }
    //Точка входа в программу
    public static void main(String[] args) {
        FractalExplorer goPaint = new FractalExplorer(600);
        goPaint.createAndShowGUI();
    }
    //Метод для отрисовки фрактала
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