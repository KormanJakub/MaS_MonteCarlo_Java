package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Jancigova_2 {

    public void MonteCarlo() {
        final int pocetReplikacii = 1000000;
        final int pocetMinut = 4 * 60;

        double casRomeoZdrzanie;
        double casPrichoduRomea;

        double casJuliaZdrzanie;
        double casPrichoduJulii;

        var genCasPrichoduRomeo = new UniformContinuousRNG(0.0, 4.0*60.0);
        var genCasPrichoduJulie = new UniformContinuousRNG(0.0, 4.0*60.0);

        var genCasRomeoZdrzanie = new TriangularRNG(15.0, 24.0, 32.0);
        var genCasJuliaZdrzanie = new TriangularRNG(10.0, 16.0, 22.0);

        var genPravde = new UniformContinuousRNG();

        int stretnuSa = 0;
        int nestretnuSa = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            boolean juliuPrichitila = genPravde.sample() < 0.04;
            double myCas = 0;

            if (!juliuPrichitila) {

                casPrichoduJulii = genCasPrichoduJulie.sample();
                casPrichoduRomea = genCasPrichoduRomeo.sample();

                if (casPrichoduJulii > casPrichoduRomea) {

                    myCas = casPrichoduRomea;
                    casJuliaZdrzanie = genCasJuliaZdrzanie.sample();

                    myCas += casJuliaZdrzanie;
                } else {
                    myCas = casPrichoduJulii;
                    casRomeoZdrzanie = genCasRomeoZdrzanie.sample();

                    myCas += casRomeoZdrzanie;
                }

                if (myCas < pocetMinut) {
                    stretnuSa++;
                } else {
                    nestretnuSa++;
                }
            } else {
                nestretnuSa++;
            }
        }

        double pravedepodobnostStretnutie = ((double) stretnuSa / pocetReplikacii) * 100;

        System.out.println("Pravdepodobnost, ze sa dvaja zalubenci stretnu je " + pravedepodobnostStretnutie);
        //Pravdepodobnost, ze sa dvaja zalubenci stretnu je 95.2949
    }
}
