package org.example.Zadania;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Marton_2 {

    public void MonteCarlo() {
        final int pocetReplikacii = 1000000;

        double casVlaku;
        double casPeso;

        var genCasVlaku = new TriangularRNG(20.0, 40.0, 60.0);
        var genCasPeso = new UniformContinuousRNG(5.0, 15.0);

        double casTaxika;
        double casZapchy;

        var genCasTaxika = new TriangularRNG(30.0, 45.0, 75.0);
        var genCasZapchy = new TriangularRNG(30.0, 40.0, 45.0);

        double celkovyCasHromadouDopravou = 0;
        double celkovyCasIndividualnouDopravou = 0;

        double pravde;
        var genPravde = new UniformContinuousRNG();

        for (int i = 0; i < pocetReplikacii; i++) {
            double myCasH = 0;
            double myCasI = 0;

            //Hromadna
            casVlaku = genCasVlaku.sample();
            casPeso = genCasPeso.sample();

            myCasH += (casVlaku + casPeso);
            celkovyCasHromadouDopravou += myCasH;

            //Individualna

            casTaxika = genCasTaxika.sample();
            myCasI += casTaxika;

            pravde = genPravde.sample();
            if (pravde < 0.08) {
                casZapchy = genCasZapchy.sample();
                myCasI += casZapchy;
            }

            celkovyCasIndividualnouDopravou += myCasI;
        }

        double priemerneHromadnou = celkovyCasHromadouDopravou / pocetReplikacii;
        double priemerneIndividualno = celkovyCasIndividualnouDopravou / pocetReplikacii;

        if (priemerneHromadnou > priemerneIndividualno) {
            System.out.println("Je lepsie cestovat individualnou dopravou");
        } else {
            System.out.println("Je lepsie cestovat hromadnou dopravou");
        }

        System.out.println("Zakaznik by pri vyuzity hromadnej dopravy cestoval " + priemerneHromadnou);
        System.out.println("Zakaznik by pri vyuzity individualnej dopravy cestoval " + priemerneIndividualno);
    }

    /*
    Je lepsie cestovat hromadnou dopravou
    Zakaznik by pri vyuzity hromadnej dopravy cestoval 49.998779321124005
    Zakaznik by pri vyuzity individualnej dopravy cestoval 53.078131694523364
     */
}
