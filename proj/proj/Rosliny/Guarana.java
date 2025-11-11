package proj.Rosliny;
import proj.Stale;
import proj.Swiat;
import proj.Organizm;

import java.util.Random;

public class Guarana extends Roslina{
    public Guarana(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.inicjatywa = 0;
        this.sila = 0;
        this.wiek = 0;
        this.nazwa = "Guarana";
        this.znak_gatunku = Stale.GUARANA;
    }

    public Guarana(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void kolizja(Organizm o)
    {
        o.SetSila(o.GetSila() + 3);

        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + "), dostaje +3 sily");

        swiat.UsunOrganizm(polozeniex, polozeniey);
    }

    @Override
    public void PostawDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(6);
        if (szansa == 0)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new Guarana(x, y, swiat));
            return;
        }
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
    }

}
