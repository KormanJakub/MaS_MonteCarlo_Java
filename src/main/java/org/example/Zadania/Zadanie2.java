package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

public class Zadanie2 {

    public void MonteCarlo() {

        final int kusovA = 70;
        final int kusovB = 90;
        final int cenaA = 3;
        final int cenaB = 2;
        final int pocetReplikacii = 1000000;

        double nakladyA;
        double nakladyB;
        int dopytA;
        int dopytB;

        var genNakladyA = new TriangularRNG(1.0, 1.75, 2.5);
        var genNakladyB = new TriangularRNG(0.7, 1.2, 1.7);

        var genDopytA = new UniformDiscreteRNG(40, 80);
        var genDopytB = new UniformDiscreteRNG(66, 155);

        double ziskA = 0;
        double ziskB = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            nakladyA = genNakladyA.sample();
            nakladyB = genNakladyB.sample();

            dopytA = genDopytA.sample();
            dopytB = genDopytB.sample();

            int predanychA = Math.min(kusovA, dopytA);
            int predanychB = Math.min(kusovB, dopytB);

            ziskA += (predanychA * cenaA) - (kusovA * nakladyA);
            ziskB += (predanychB * cenaB) - (kusovB * nakladyB);
        }

        double priemernyZiskA = ziskA / pocetReplikacii;
        double priemernyZiskB = ziskB / pocetReplikacii;

        System.out.println("Pri typu A je priemerny zisk " + priemernyZiskA);
        System.out.println("Pri typu B je priemerny zisk " + priemernyZiskB);
    }
}
