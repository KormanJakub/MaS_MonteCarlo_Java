package org.example.Zadania;

import OSPRNG.UniformDiscreteRNG;

public class Zadanie4 {

    public void MonteCarlo() {
        final int pocetReplikacii = 100000;
        final int pocetKrokov = 1000;

        int pohyb;
        var genPohyb = new UniformDiscreteRNG(0, 1);

        int celkovaVzdialenost = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            int terazPohyb = 0;
            for (int j = 0; j < pocetKrokov; j++) {
                pohyb = genPohyb.sample();
                switch (pohyb) {
                    case 0 -> terazPohyb--;
                    case 1 -> terazPohyb++;
                }
            }
            celkovaVzdialenost += Math.abs(terazPohyb);
        }

        double priemernaVzdialenost = (double) celkovaVzdialenost / pocetReplikacii;
        double teoeretickaVzdialenost = Math.sqrt((2.0 * pocetKrokov) / Math.PI);

        System.out.println("Opity namornik prejde priemerne " + priemernaVzdialenost + " vzdialenost");
        System.out.println("Teoreticka vzdialenost je: " + teoeretickaVzdialenost);

        int pohyb2D;
        var genPohyb2D = new UniformDiscreteRNG(0, 3);

        int celkovaVzdialenost2D = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            int terazPohybX = 0;
            int terazPohybY = 0;
            for (int j = 0; j < pocetKrokov; j++) {
                pohyb2D = genPohyb2D.sample();
                switch (pohyb2D) {
                    case 0 -> terazPohybX--;
                    case 1 -> terazPohybX++;
                    case 2 -> terazPohybY--;
                    case 3 -> terazPohybY++;
                }
            }
            celkovaVzdialenost2D += Math.abs(terazPohybX) + Math.abs(terazPohybY);
        }

        double priemernaVzdialenost2D = (double) celkovaVzdialenost2D / pocetReplikacii;
        double teoeretickaVzdialenost2D = Math.sqrt((4.0 * pocetKrokov) / Math.PI);

        System.out.println("\n\n2D\n\n");

        System.out.println("Opity namornik prejde priemerne " + priemernaVzdialenost2D + " vzdialenost");
        System.out.println("Teoreticka vzdialenost je: " + teoeretickaVzdialenost2D);

        int pohyb3D;
        var genPohyb3D = new UniformDiscreteRNG(0, 5);

        int celkovaVzdialenost3D = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            int terazPohybX = 0;
            int terazPohybY = 0;
            int terazPohybZ = 0;
            for (int j = 0; j < pocetKrokov; j++) {
                pohyb3D = genPohyb3D.sample();
                switch (pohyb3D) {
                    case 0 -> terazPohybX--;
                    case 1 -> terazPohybX++;
                    case 2 -> terazPohybY--;
                    case 3 -> terazPohybY++;
                    case 4 -> terazPohybZ--;
                    case 5 -> terazPohybZ++;
                }
            }
            celkovaVzdialenost3D += Math.abs(terazPohybX) + Math.abs(terazPohybY) + Math.abs(terazPohybZ);
        }

        double priemernaVzdialenost3D = (double) celkovaVzdialenost3D / pocetReplikacii;
        double teoeretickaVzdialenost3D = Math.sqrt((6.0 * pocetKrokov) / Math.PI);

        System.out.println("\n\n3D\n\n");

        System.out.println("Opity namornik prejde priemerne " + priemernaVzdialenost3D + " vzdialenost");
        System.out.println("Teoreticka vzdialenost je: " + teoeretickaVzdialenost3D);
    }
}
