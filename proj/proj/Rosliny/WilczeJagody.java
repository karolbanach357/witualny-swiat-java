package proj.Rosliny;

import proj.Organizm;
import proj.Stale;
import proj.Swiat;

import java.util.Random;

public class WilczeJagody extends Roslina{
    public WilczeJagody(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.inicjatywa = 0;
        this.sila = 99;
        this.wiek = 0;
        this.nazwa = "WilczeJagody";
        this.znak_gatunku = Stale.WILCZE_JAGODY;
    }

    public WilczeJagody(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }
    @Override
    public void kolizja(Organizm o)
    {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + "), I razem z nim ginie");
        swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
        swiat.UsunOrganizm(polozeniex, polozeniey);
    }

    @Override
    public void PostawDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(8);
        if (szansa == 0)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new WilczeJagody(x, y, swiat));
            return;
        }
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
    }
}
