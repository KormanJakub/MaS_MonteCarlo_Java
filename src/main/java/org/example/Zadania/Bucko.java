package org.example.Zadania;

import OSPRNG.NormalRNG;
import OSPRNG.TriangularRNG;

public class Bucko {
    public void MonteCarlo() {
        final int mesacnaInvesticia = 100;
        final double mesacnyPoplatok = 5.25;
        final double finalnaInvesticia = mesacnaInvesticia - mesacnyPoplatok;
        final int pocetDni = 10800;
        final int pocetReplikacii = 50000;

        double fondA;
        double fondB;

        var genFondA = new TriangularRNG(-0.0925,0.037,0.0598);
        var genFondB = new NormalRNG(0.01, 0.03);

        double celkovyFondAZisk = 0;
        double celkovyFondBZisk = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            double myZiskA = 0;
            double myZiskB = 0;

            for (int j = 1; j <= pocetDni; j++) {
                if (j == 1) {
                    myZiskA = finalnaInvesticia;
                    myZiskB = finalnaInvesticia;
                    continue;
                }

                fondA = genFondA.sample();
                myZiskA *= (1+fondA);

                if ((j % 30) == 0) {
                    fondB = genFondB.sample();
                    myZiskB *= (1+fondB);

                    myZiskA += finalnaInvesticia;
                    myZiskB += finalnaInvesticia;
                }
            }

            celkovyFondAZisk += myZiskA;
            celkovyFondBZisk += myZiskB;
        }

        double priemernyZiskA = celkovyFondAZisk / pocetReplikacii;
        double priemernyZiskB = celkovyFondBZisk / pocetReplikacii;

        System.out.println("Celkova hodnota majetku po 30 rokoch investovani do fondu A bude: " + priemernyZiskA);
        System.out.println("Celkova hodnota majetku po 30 rokoch investovani do fondu B bude: " + priemernyZiskB);

        /*
        Celkova hodnota majetku po 30 rokoch investovani do fondu A bude: 1.2886769269669392E10
        Celkova hodnota majetku po 30 rokoch investovani do fondu B bude: 334437.0232669268
         */
    }
}
