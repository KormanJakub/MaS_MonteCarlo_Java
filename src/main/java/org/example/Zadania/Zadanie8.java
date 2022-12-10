package org.example.Zadania;

import OSPRNG.TriangularRNG;

public class Zadanie8 {

    public void MonteCarlo() {
        final int pocetReplikacii = 100000;
        final int rozloha = 300;

        var rozlohaGen = new TriangularRNG(1.0, 3.0, 3.5);

        int pocetKombajnov = 0;
        double pravdepodobnostPrekrocenia;

        do {
            pocetKombajnov++;

            int pocetPrekroceni = 0;
            for (int i = 0; i < pocetReplikacii; i++) {
                double zozataRozloha = 0;

                for (int j = 0; j < 20; j++) {
                    for (int k = 0; k < pocetKombajnov; k++) {
                        zozataRozloha += rozlohaGen.sample();
                    }
                }

                if (zozataRozloha < rozloha)
                    pocetPrekroceni++;
            }

            pravdepodobnostPrekrocenia = (double) pocetPrekroceni / pocetReplikacii;

        } while (pravdepodobnostPrekrocenia >= 0.1);

        System.out.println("Počet kombajnov: " + pocetKombajnov + ", pravdepodobnosť prekročenia: " + pravdepodobnostPrekrocenia * 100.0);
    }
}
