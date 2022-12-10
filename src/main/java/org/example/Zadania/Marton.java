package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Marton {

    public void MonteCarlo() {
        final int pocetReplikacii = 1000000;
        final int pocetDni = 7;
        final int cenaLetenky = 150;
        final double dnesNaplnenost = 0.5;
        final double naplnenostLetu = 0.8;
        final double narastCeny = 35.0;

        double x;
        var genX = new TriangularRNG(10.0, 20.0, 30.0);
        double y;
        var genY = new UniformContinuousRNG(10.0 / 100, 15.0 / 100);

        double[] cenyLeteniek = new double[pocetDni];

        for (int i = 0; i < pocetReplikacii; i++) {
            double myCena = 0;
            double myNaplnenost = 0;
            boolean cenaSaMeni = true;

            for (int j = 0; j < pocetDni; j++) {

                if (j == 0) {
                    myCena = cenaLetenky;
                    myNaplnenost = dnesNaplnenost;
                    cenyLeteniek[j] += myCena;
                    continue;
                }

                y = genY.sample();;
                myNaplnenost += y;

                if (cenaSaMeni) {
                    if (myNaplnenost < naplnenostLetu) {
                        x = genX.sample();
                        myCena -= (myCena / 100) * x;
                    } else {
                        cenaSaMeni = false;
                        myCena += (myCena / 100) * narastCeny;
                    }
                }

                cenyLeteniek[j] += myCena;
            }
        }

        double minCena = Double.MAX_VALUE;
        double minDen = -1;

        for (int i = 0; i < pocetDni; i++) {
            double priemerneZisk = cenyLeteniek[i] / pocetReplikacii;
            if (minCena > priemerneZisk) {
                minCena = priemerneZisk;
                minDen = i;
            }
        }

        System.out.println("Priemerna najnizsia cena letenky je " + minCena + " v den " + minDen);
        //Priemerna najnizsia cena letenky je 95.99132158325698 v den 2.0
    }
}
