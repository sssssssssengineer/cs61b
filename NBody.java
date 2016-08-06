/**
 * Created by Administrator on 2016/7/20.
 */
import java.util.Scanner;

//import examples.*;
public class NBody {
    public static String imageToDraw = "starfield.jpg";

    public static double readRadius(String str) {
        In in = new In(str);
        int firstItemInFile = in.readInt();
        double SecondItemInFile = in.readDouble();
        /*while (!in.isEmpty()){
            double n1 = in.readInt();
            double n2 = in.readInt();
            double n3 = in.readInt();
            double n4 = in.readInt();
            double n5 = in.readInt();
            String n6 = in.readString();
        }
        */
        return SecondItemInFile;
    }

    public static int countlines(String str) {
        In in = new In(str);
        int firstItemInFile = in.readInt();
        double SecondItemInFile = in.readDouble();
        int i = 0;
        while (!(in.isEmpty() && in.hasNextChar())) {
            in.readDouble();
            in.readDouble();
            in.readDouble();
            in.readDouble();
            in.readDouble();
            in.readString();
            i += 1;
        }
        return i;
    }

    public static Planet[] readPlanets(String str) {
        int number = countlines(str);
        In in = new In(str);
        int firstItemInFile = in.readInt();
        double SecondItemInFile = in.readDouble();
        int i = 0;
        Planet[] p = new Planet[number];
        while (!in.isEmpty()) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double ms = in.readDouble();
            String rs = in.readString();
            p[i] = new Planet(xp, yp, xv, yv, ms, rs);
            i += 1;
        }
        return p;
    }

    public static void main(String[] args) {
        //Scanner scanner=new Scanner(System.in);
        //double T= Double.parseDouble(scanner.next());
        //double dt=Double.parseDouble(scanner.next());
        //String filename= scanner.next();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] ps = NBody.readPlanets(filename);
        /*
        System.out.println(T);
        System.out.println(dt);
        System.out.println(radius);
        System.out.println(ps[0].imgFileName);
        */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show(2000);
        int i = 0;
        while (i != ps.length) {
            ps[i].draw();
            i += 1;
        }
        double[] xForce = new double[ps.length];
        double[] yForce = new double[ps.length];

        int Time = 0;
        while (Time != T) {
            int j = 0;
            while (j != ps.length) {
                xForce[j] = ps[j].calcNetForceExertedByX(ps);
                yForce[j] = ps[j].calcNetForceExertedByY(ps);
                j+=1;
            }
            j = 0;
            while (j != ps.length) {
                ps[j].update(dt, xForce[j], yForce[j]);
                j+=1;
            }
            StdDraw.picture(0, 0, imageToDraw);
            int k = 0;
            while (k != ps.length) {
                ps[k].draw();
                k += 1;
            }
            StdDraw.show(5);
            Time += dt;
        }
    }
}

