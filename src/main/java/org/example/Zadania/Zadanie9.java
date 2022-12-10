package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie9 {

    public void MonteCarlo() {

        final int cenaDnes = 500;
        final double letPlny = 75;
        final double dnesNaplneny = 27;
        final int pocetReplikacii = 1000000;
        final int pocetDni = 7;

        var genX = new TriangularRNG(1.0, 4.0, 11.0);
        var genY = new UniformContinuousRNG(5.0, 14.0);
        double[] celkovaCena = new double[pocetDni];

        for (int i = 0; i < pocetReplikacii; i++) {
            double cenaPreKonkretnyDen = 0.0;
            double naplnenostPreKonkretnyDen = 0.0;
            boolean cenaSaMeni = true;

            for (int j = 0; j < pocetDni; j++) {
                if (j == 0) {
                    cenaPreKonkretnyDen = cenaDnes;
                    naplnenostPreKonkretnyDen = dnesNaplneny;
                    celkovaCena[j] += cenaPreKonkretnyDen;
                    continue;
                }

                naplnenostPreKonkretnyDen += genY.sample();

                if (cenaSaMeni) {
                    if (naplnenostPreKonkretnyDen < letPlny) {
                        cenaPreKonkretnyDen -= (cenaPreKonkretnyDen / 100) * genX.sample();
                    } else {
                        cenaSaMeni = false;
                        cenaPreKonkretnyDen += (cenaPreKonkretnyDen / 100) * 30.0;
                    }
                }

                celkovaCena[j] += cenaPreKonkretnyDen;
            }
        }

        double najnizsiaCena = Double.MAX_VALUE;
        int najlepsiDen = -1;

        for (int i = 0; i < pocetDni; i++) {
            double priemernaCena = celkovaCena[i] / pocetReplikacii;
            if (priemernaCena < najnizsiaCena) {
                najnizsiaCena = priemernaCena;
                najlepsiDen = i;
            }
        }

        System.out.println("Cestovatel by mal zakupit letenku " + najlepsiDen + " den za " + najnizsiaCena + " eur");

    }
}
