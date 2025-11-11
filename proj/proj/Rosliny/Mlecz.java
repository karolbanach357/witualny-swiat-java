package proj.Rosliny;

import proj.Stale;
import proj.Swiat;

import java.util.Random;

public class Mlecz extends Roslina{

    public Mlecz(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.inicjatywa = 0;
        this.sila = 0;
        this.wiek = 0;
        this.nazwa = "Mlecz";
        this.znak_gatunku = Stale.MLECZ;
    }
    public Mlecz(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat)
    {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }
    @Override
    public void akcja()
    {
        for(int i = 0; i<3; i++)
            super.akcja();
    }

    @Override
    public void PostawDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(10);
        if (szansa == 0)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new Mlecz(x, y, swiat));
            return;
        }
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
    }
}
