import java.util.Scanner; // импорт библиотеки для получения данных из консоли


public class Lab1 { // объявление класса
    public static void main(String[] args){ // объявление функции
        Scanner in = new Scanner(System.in); // объявление переменной типа Scaner для считывания данных из консоли
        String s1 = in.nextLine(); String s2 = in.nextLine(); String s3 = in.nextLine(); // считывание данных
        Point3d point1 = convertToPoint3d(s1); // конвертация точек в класс Point3d
        Point3d point2 = convertToPoint3d(s2);
        Point3d point3 = convertToPoint3d(s3);
        if(point1.comparePoints(point2)&&point2.comparePoints(point3)&&point3.comparePoints(point1 )){ // если точки не совпадают
            System.out.printf("Area = %.2f", computeArea(point1, point2, point3));
        }
        else { // если есть совпадающие точки
            System.out.println("There are same points. Change input.");
        }
    }

    private static Point3d convertToPoint3d(String s) { // конвертирует String в Point3d (X Y Z)
        String[] p = s.split(" "); // разделение координат точек пробелом
        return new Point3d(Double.parseDouble(p[0]), Double.parseDouble(p[1]), Double.parseDouble(p[2]));
    }

    private static double computeArea(Point3d point1, Point3d point2, Point3d point3){ // считает площадь
        double a = point1.distanceTo(point2); // рассчёт стороны а
        double b = point2.distanceTo(point3); // рассчёт стороны b
        double c = point1.distanceTo(point3); // рассчёт стороны c
        double p = (a+b+c)/2; // рассчёт полупериметра
        return Math.round(Math.sqrt(p*(p-a)*(p-b)*(p-c))*100.0)/100.0; // вывод площади
    }

}
