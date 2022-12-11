import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;
    @Override
    public void getInitialRange(Rectangle2D.Double initRange) { //указываем начальные координаты от которых будет отрисовываться фрактал, попадет в обзор
        initRange.x = -2;
        initRange.y = -1.5;
        initRange.width = 3;
        initRange.height = 3;
    }
    @Override
    public int numIterations(double x, double y) {//считает кол-во итераций для фрактала, сколько можно
        int countOfIter = 0;
        double zRe = 0;
        double zIm = 0;
        double zRe2 = 0;
        double zIm2 = 0;
        while ((countOfIter < MAX_ITERATIONS) && ((zRe2 + zIm2) < 4)) {
            zIm = Math.abs(2 * zRe * zIm) + y;
            zRe = (zRe2 - zIm2) + x;
            zIm2 = zIm * zIm;
            zRe2 = zRe * zRe;
            countOfIter++;
        }
        if (countOfIter == MAX_ITERATIONS) return -1;
        else return countOfIter;
    }
    public String toString() {
        return "BurningShip";
    }
}
