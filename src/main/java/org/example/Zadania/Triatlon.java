package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Triatlon {
    public void MonteCarlo() {

        final int pocetReplikacii = 1000000;
        final int pocetPretekarov = 66;

        var genCasPlavanie = new TriangularRNG(20.0, 32.0, 40.0);
        var genCasCyklistika = new TriangularRNG(60.0, 70.0, 86.0);
        var genCasBeh = new TriangularRNG(36.0, 46.0, 63.0);

        var genDefekt = new UniformContinuousRNG(0.0, 1.0);
        var genDefektOprava = new UniformContinuousRNG(0.5, 5.0);

        var genSnurky = new UniformContinuousRNG(0.0, 1.0);
        var genRozviazanieOprava = new UniformContinuousRNG(1.0, 5.0);

        double celkovePercentoKvalifikovanych = 0;
        for (int i = 0; i < pocetReplikacii; i++) {
            int pocetKvalifikovanych = 0;

            for (int j = 0; j < pocetPretekarov; j++) {
                double celkovyCasPretekara = genCasPlavanie.sample() + genCasCyklistika.sample() + genCasBeh.sample();

                int pocetDefektov = 0;
                double defektSample = genDefekt.sample();
                if (defektSample < 0.07)
                    pocetDefektov = 1;
                else if (defektSample < 0.07 + 0.04)
                    pocetDefektov = 2;
                else if (defektSample < 0.07 + 0.04 + 0.015)
                    pocetDefektov = 3;

                for (int k = 0; k < pocetDefektov; k++)
                    celkovyCasPretekara += genDefektOprava.sample();

                int pocetRozviazani = 0;
                double rozviazanieSample = genSnurky.sample();
                if (rozviazanieSample < 0.1)
                    pocetRozviazani = 1;
                else if (rozviazanieSample < 0.1 + 0.045)
                    pocetRozviazani = 2;

                for (int k = 0; k < pocetRozviazani; k++)
                    celkovyCasPretekara += genRozviazanieOprava.sample();

                if (celkovyCasPretekara < 140)
                    pocetKvalifikovanych++;
            }

            celkovePercentoKvalifikovanych += (double) pocetKvalifikovanych / pocetPretekarov;
        }

        System.out.println("Z regionálnych pretekov sa na celoslovenské kvalifikovalo: " + (celkovePercentoKvalifikovanych / pocetReplikacii) * 100.0 + "% pretekárov");
    }
}
