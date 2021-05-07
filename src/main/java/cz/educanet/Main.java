package cz.educanet;

import java.util.Scanner;

import static cz.educanet.Window.Okno;

public class Main {
    public static String Maze =
            "-0.19999999;-1.0;0.4\n" +
                    "0.20000005;-1.0;0.4\n" +
                    "0.6;-1.0;0.4\n" +
                    "0.6;-0.6;0.4\n" +
                    "-1.0;-0.19999999;0.4\n" +
                    "-0.6;-0.19999999;0.4\n" +
                    "-0.19999999;-0.19999999;0.4\n" +
                    "0.6;-0.19999999;0.4\n" +
                    "-1.0;0.20000005;0.4\n" +
                    "-1.0;0.6;0.4\n" +
                    "-0.6;0.6;0.4\n" +
                    "-0.19999999;0.6;0.4";



    public static int W;
    public static int H;
    public static boolean collide = false;


    public static void main(String[] args) throws Exception {

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        //Ano je to možná zbytečné, ale mě to jinak furt padalo.

        System.out.println("Jak velké má být pole?");
        System.out.println("Výška:");
        W = sc1.nextInt();
        System.out.println("Šířka:");
        H = sc2.nextInt();

            Okno();

    }
}
