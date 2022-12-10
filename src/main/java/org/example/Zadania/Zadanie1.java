package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie1 {

    public void MonteCarlo() {

        final double pocetReplikacii = 1000000;
        final double jedenVytlacok = 0.15;

        double dnesnaCena;
        TriangularRNG genDnesnaCena = new TriangularRNG(0.25, 0.6, 0.95);

        double dnesRobi;
        UniformContinuousRNG genDnesRobi = new UniformContinuousRNG(250.0,420.0);

        final double priemernyNakup = 2.7;

        double dennyDopyt;

        double zostatkovaCena = jedenVytlacok * 0.65;

        int pocetBalikov = 0;

        double maxZisk = 0;
        int maxBalikov = 0;

        for (pocetBalikov = 1; pocetBalikov <= 20; pocetBalikov++) {
            double dnesnyZisk = 0.0;

            for (int i = 0; i < pocetReplikacii; i++) {
                double dnesKupilNovin = pocetBalikov * 10;

                dnesRobi = genDnesRobi.sample();
                dennyDopyt = dnesRobi / priemernyNakup;

                dnesnaCena = genDnesnaCena.sample();

                double boloPredanychNovin = Math.min(dnesKupilNovin, dennyDopyt);

                double zostaloNovin = dnesKupilNovin - boloPredanychNovin;

                dnesnyZisk += (boloPredanychNovin * dnesnaCena) + (zostaloNovin * zostatkovaCena) - (dnesKupilNovin * jedenVytlacok);
            }

            double priemernyZisk = dnesnyZisk / pocetReplikacii;
            if (priemernyZisk > maxZisk)
            {
                maxZisk = priemernyZisk;
                maxBalikov = pocetBalikov;
            }
        }

        System.out.println("Korter musi priemern nakupt " + maxBalikov + " aby mal priemerny denny zisk " + maxZisk + " eur");
    }
}
