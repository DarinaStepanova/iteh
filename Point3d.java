public class Point3d extends Point2d { // Объявление класса
    private double zCoord; //координата z
    //конструктор инициализации
    public Point3d(double xCoord, double yCoord, double zCoord) {
        super(xCoord, yCoord);
        this.zCoord = zCoord;
    }
    //Конструктор по умолчанию
    public Point3d(){
        this(0.0, 0.0, 0.0);
    }
    public double getzCoord() { // Получить координату Z точки
        return zCoord;
    }
    public void setzCoord(double zCoord) { // Задать координату Z точке
        this.zCoord = zCoord;
    }
    public double distanceTo(Point3d point){ // Посчитать расстояние до другой точки
        return Math.round(Math.sqrt(
                Math.pow(point.getxCoord() - getxCoord(), 2) +
                        Math.pow(point.getyCoord() - getyCoord(), 2) +
                        Math.pow(point.getzCoord() - getzCoord(), 2)
        )*100.0)/100.0;
    }
    public boolean comparePoints(Point3d point) { // Сравнить точки (совпадают или нет)
        if((getxCoord()==point.getxCoord()&&getyCoord()==point.getyCoord()&&getzCoord()==point.getzCoord())){
            return false;
        }
        return true;
    }
}
