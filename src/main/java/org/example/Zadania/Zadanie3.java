package org.example.Zadania;

import OSPRNG.UniformContinuousRNG;

public class Zadanie3 {

    public void MonteCarlo() {
        final int obsahStvorca = 1;
        final double kruhX = 0.5;
        final double kruhY = 0.5;
        final double polomer = 0.5;
        final double chyba = 10e-6;

        double x;
        double y;

        var genX = new UniformContinuousRNG();
        var genY = new UniformContinuousRNG();

        int pocetPokusov = 0;
        int pocetBodovVkruhu = 0;

        double novePi = 0.0;

        while (Math.abs(novePi - Math.PI) >= chyba) {
            pocetPokusov++;

            x = genX.sample();
            y = genY.sample();

            if (Math.pow((x - kruhX), 2) + Math.pow((y - kruhY), 2) <= Math.pow(polomer, 2)) {
                pocetBodovVkruhu++;
            }

            novePi = (obsahStvorca * ((double) pocetBodovVkruhu / pocetPokusov)) / Math.pow(polomer, 2);
        }

        System.out.println("Pocet pokusov, ktore potrebujeme na dosiahnutie " + pocetPokusov);
        System.out.println("Aproximovane PI " + novePi);
    }
}
