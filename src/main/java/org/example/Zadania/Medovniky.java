package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Medovniky {

    public void MonteCarlo() {

        final int pocetMedovnikov = 100;
        final int pocetReplikacii = 1000000;

        double cenaNaDnes;
        double casPrichod;
        double casPrichodovPoslednaHodina;

        var genCenaNaDnes = new TriangularRNG(0.5, 0.75, 1.0);
        var genCasPrichodov = new TriangularRNG(2.0, 6.0, 10.0);
        var genCasPrichodovPoslednaHodina = new UniformContinuousRNG(1.0, 3.0);

        double celkovyZisk = 0;
        double celkovoZostalo = 0;


        for (int i = 0; i < pocetReplikacii; i++) {
            cenaNaDnes = genCenaNaDnes.sample();

            double dnesPracuje = 0;
            boolean poslednaHodina = false;
            int dnesPredala = 0;

            while (dnesPracuje < 8 * 60) {
                dnesPredala++;
                celkovyZisk += cenaNaDnes;

                if (dnesPredala == pocetMedovnikov) {
                    break;
                }

                if (!poslednaHodina) {
                    casPrichod = genCasPrichodov.sample();
                    dnesPracuje += casPrichod;
                } else {
                    casPrichodovPoslednaHodina = genCasPrichodovPoslednaHodina.sample();
                    dnesPracuje += casPrichodovPoslednaHodina;
                }

                if (8 * 60 - dnesPracuje <= 60 && pocetMedovnikov - dnesPredala > 10 && !poslednaHodina) {
                    cenaNaDnes /= 4.0;
                    poslednaHodina = true;
                }
            }

            celkovoZostalo += (pocetMedovnikov - dnesPredala);
        }

        double priemernyZisk = (double) celkovyZisk / pocetReplikacii;
        double priemerneZostalo = (double) celkovoZostalo / pocetReplikacii;

        System.out.println("Babke ostava " + priemerneZostalo + " medovnikov. Kazdy den utrzi priemerne " + priemernyZisk + " eur");
    }
}
