package org.example.Zadania;

import OSPRNG.NormalRNG;
import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

public class Jancigova {
    public void MonteCarlo() {
        final int pocetReplikacii = 1000000;
        final int pocetStran = 3407;

        int dniCitaniaAnicka;
        double anickaPrecitala;

        var genAnickaPrecitala = new NormalRNG(150.0, 33.0);
        var genDniCitaniaAnicka = new UniformDiscreteRNG(1, 5);

        double zuzkaPrecitala;
        var genZuzkaPrecitala = new TriangularRNG(29.0, 34.0, 52.0);

        int zuzkaRychlejsie = 0;
        int anickaRychlejsie = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            double myAnickaStran = 0;
            int myAnickaDni = 0;

            double myZuzkaStran = 0;
            int myZuzkaDni = 0;

            while (myAnickaStran <= pocetStran) {
                dniCitaniaAnicka = genDniCitaniaAnicka.sample();
                anickaPrecitala = genAnickaPrecitala.sample();

                myAnickaDni += dniCitaniaAnicka;
                myAnickaStran += anickaPrecitala;
            }

            while (myZuzkaStran <= pocetStran) {
                zuzkaPrecitala = genZuzkaPrecitala.sample();

                myZuzkaDni++;
                myZuzkaStran += zuzkaPrecitala;
            }

            if (myAnickaDni < myZuzkaDni) {
                anickaRychlejsie++;
            } else {
                zuzkaRychlejsie++;
            }
        }

        double priemerneAnicka = ((double) anickaRychlejsie / pocetReplikacii) * 100;
        double priemerneZuzka = ((double) zuzkaRychlejsie / pocetReplikacii) * 100;

        if (priemerneZuzka > priemerneAnicka) {
            System.out.println("Zuzka precita rychlejsie s pravdepodobnostou " + priemerneZuzka);
        } else {
            System.out.println("Anicka precita rychlejsie s pravdepodobnostou " + priemerneAnicka);
        }
    }
}
