import java.awt.image.BufferedImage;
import java.awt.*;
public class JImageDisplay extends javax.swing.JComponent{
    private BufferedImage bufIm;
    //Конструктор класса
    public JImageDisplay(int width, int height) {
        bufIm = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//создаем объект класса баф
        Dimension dim = new Dimension(width, height); //разрешение
        super.setPreferredSize(dim);//загружаем в род. класс разрешение
    }
    //Метод для отрисовки изображения
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufIm, 0, 0, bufIm.getWidth(), bufIm.getHeight(), null);
    }
    //Метод для очистки изображения
    public void clearImage() {
        int[] colourBlack = new int[bufIm.getWidth()*bufIm.getHeight()];
        bufIm.setRGB(0, 0, bufIm.getWidth(), bufIm.getWidth(),colourBlack, 0, 1); //полность закрашивает экран в черный, т.к массив пустой
    }
    //Метод для установки пикселя в определенный цвет
    public void drawPixel(int x, int y, int colour) {
        bufIm.setRGB(x, y, colour);
    }
    public BufferedImage getImage() {
        return bufIm;
    }
}
