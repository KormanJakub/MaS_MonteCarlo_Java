package org.example.Zadania;

import OSPRNG.TriangularRNG;

public class Zadanie7 {

    public void MonteCarlo() {
        int naSklade = 0;
        final int nakladyNaVyrobu = 150;
        final int zaPokazenyKus = 50;
        final int pocetReplikacii = 1000000;

        int dopyt;
        var genDopyt = new TriangularRNG(1000.0, 4000.0, 8500.0);

        double celkoveNaklady = Double.MAX_VALUE;
        int celkoveKusy = 0;

        for (naSklade = 4000; naSklade <= 6000; naSklade++) {
            double naklady = 0.0;

            for (int i = 0; i < pocetReplikacii; i++) {

                dopyt = (int)Math.round(genDopyt.sample());
                int kusy;

                if (naSklade >= dopyt) {
                    kusy = naSklade - dopyt;
                    naklady += (kusy * zaPokazenyKus);
                } else {
                    kusy = dopyt - naSklade;
                    naklady += (kusy * nakladyNaVyrobu);
                }
            }

            double priemerneNaklady = (double) naklady / pocetReplikacii;

            if (celkoveNaklady > priemerneNaklady) {
                celkoveNaklady = priemerneNaklady;
                celkoveKusy = naSklade;
            }
        }

        System.out.println(celkoveKusy);

    }
}
