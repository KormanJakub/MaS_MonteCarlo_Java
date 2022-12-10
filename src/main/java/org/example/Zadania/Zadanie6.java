package org.example.Zadania;

import OSPRNG.TriangularRNG;

import java.util.Arrays;

public class Zadanie6 {

    public void MonteCarlo() {
        final int pocetReplikacii = 1000000;
        final int pocetAutJednehoTypu = 5;

        double casFast;
        double casFurious;

        var genCasFast = new TriangularRNG(40.0, 50.0, 75.0);
        var genCasFurious = new TriangularRNG(35.0, 52.0, 80.0);

        int fastLepsieUmiestnenie = 0;

        for (int i = 0; i < pocetReplikacii; i++) {

            double[] casyFast = new double[pocetAutJednehoTypu];
            double[] casyFurious = new double[pocetAutJednehoTypu];

            for (int j = 0; j < pocetAutJednehoTypu; j++) {
                casFast = genCasFast.sample();
                casFurious = genCasFurious.sample();

                casyFast[j] += casFast;
                casyFurious[j] += casFurious;
            }

            Arrays.sort(casyFast);
            Arrays.sort(casyFurious);

            if (casyFast[0] < casyFurious[0] && casyFast[0] < casyFurious[1] && casyFast[1] < casyFurious[0] && casyFast[1] < casyFurious[1]) {
                fastLepsieUmiestnenie++;
            }
        }

        double priemerneUmiestnenieFast = ((double) fastLepsieUmiestnenie / pocetReplikacii) * 100;
        System.out.println("Pravdepodobnost umiestnenia fast na prvych dvoch miestach je " + priemerneUmiestnenieFast);
    }
}
