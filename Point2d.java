public class Point2d {
    /**  двумерный класс точки. **/
    /** координата X **/
    private double xCoord;
    /**  координата Y **/
    private double yCoord;
    /** Конструктор **/
    public Point2d(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    /** Конструктор по умолчанию. **/
    public Point2d () {
        //Вызовите конструктор с двумя параметрами и определите источник.
        this(0, 0);
    }
    /** Возврат координаты X **/
    public double getxCoord() {
        return xCoord;
    }
    /** Возврат координаты У **/
    public double getyCoord() {
        return yCoord;
    }
    /** Присвоение значения координаты X. **/
    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }
    /**  Присвоение значения координаты Y. **/
    public void setyCoord(double yCoord)
    {
        this.yCoord = yCoord;
    }
}
