package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie5 {

    public void MonteCarlo() {
        final int pocetReplikacii = 10000000;
        final double sancaNaPravdu = 0.8;

        boolean vestica1;
        boolean vestica2;
        boolean vestica3;

        int vestica1a2sazhoduju = 0;
        int vestica1a2majupravdu = 0;

        int vestica1a2a3sazhoduju = 0;
        int vestica1a2a3majupravdu = 0;

        var genVestica1 = new UniformContinuousRNG();
        var genVestica2 = new UniformContinuousRNG();
        var genVestica3 = new UniformContinuousRNG();

        boolean vestica1T;
        boolean vestica2T;
        boolean vestica3T;

        int vestica1a2sazhodujuT = 0;
        int vestica1a2majupravduT = 0;

        int vestica1a2a3sazhodujuT = 0;
        int vestica1a2a3majupravduT = 0;

        var genUspesnost = new TriangularRNG(0.3, 0.8, 1.0);

        for (int i = 0; i < pocetReplikacii; i++) {
            vestica1 = genVestica1.sample() < sancaNaPravdu;
            vestica2 = genVestica2.sample() < sancaNaPravdu;

            if (vestica1 == vestica2) {
                vestica1a2sazhoduju++;
                if (vestica1) {
                    vestica1a2majupravdu++;
                }
            }

            vestica1 = genVestica1.sample() < sancaNaPravdu;
            vestica2 = genVestica2.sample() < sancaNaPravdu;
            vestica3 = genVestica3.sample() < sancaNaPravdu;

            if (vestica1 == vestica2 && vestica1 == vestica3) {
                vestica1a2a3sazhoduju++;
                if (vestica1) {
                    vestica1a2a3majupravdu++;
                }
            }

            vestica1T = genVestica1.sample() < genUspesnost.sample();
            vestica2T = genVestica2.sample() < genUspesnost.sample();

            if (vestica1T == vestica2T) {
                vestica1a2sazhodujuT++;
                if (vestica1T) {
                    vestica1a2majupravduT++;
                }
            }

            vestica1T = genVestica1.sample() < genUspesnost.sample();
            vestica2T = genVestica2.sample() < genUspesnost.sample();
            vestica3T = genVestica3.sample() < genUspesnost.sample();

            if (vestica1T == vestica2T && vestica1T == vestica3T) {
                vestica1a2a3sazhodujuT++;
                if (vestica1T) {
                    vestica1a2a3majupravduT++;
                }
            }
        }

        double pravedpodobnost1a2 = ((double) vestica1a2majupravdu / vestica1a2sazhoduju) * 100;
        double pravedpodobnost1a2a3 = ((double) vestica1a2a3majupravdu / vestica1a2a3sazhoduju) * 100;
        System.out.println("Vestice 1 a 2 maju pravdepodobnost, ze maju oboje pravdu " + pravedpodobnost1a2);
        System.out.println("Vestice 1 a 2 a 3 maju pravdepodobnost, ze maju oboje pravdu " + pravedpodobnost1a2a3);

        double pravedpodobnost1a2T = ((double) vestica1a2majupravduT / vestica1a2sazhodujuT) * 100;
        double pravedpodobnost1a2a3T = ((double) vestica1a2a3majupravduT / vestica1a2a3sazhodujuT) * 100;
        System.out.println("Vestice 1 a 2 maju pravdepodobnost, ze maju oboje pravdu " + pravedpodobnost1a2T + " trian");
        System.out.println("Vestice 1 a 2 a 3 maju pravdepodobnost, ze maju oboje pravdu " + pravedpodobnost1a2a3T + " trian");
    }
}
