package proj.Rosliny;
import proj.Organizm;
import proj.Swiat;

import java.util.Random;


abstract public class Roslina extends Organizm{

    // KONSTRUKTORY
    public Roslina(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
    }

    public Roslina(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat)
    {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    // AKCJE
    @Override
    public void akcja()
    {
        if (CzyOtoczona())
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            return;
        }
        Rozmnazanie(null);
    }

    @Override
    public void Rozmnazanie(Organizm o)
    {
        int x = polozeniex;
        int y = polozeniey;
        Random rand = new Random();
        int kierunek;
        while (true)
        {
            kierunek = rand.nextInt(4);
            if (kierunek == 0)
            {
                if (y > 0 && swiat.GetOrganizm(x, y - 1) == null)
                {
                    y--;
                    break;
                }
            }
            else if (kierunek == 1 && swiat.GetOrganizm(x, y + 1) == null)
            {
                if (y < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(x, y + 1) == null)
                {
                    y++;
                    break;
                }
            }
            else if (kierunek == 2 && swiat.GetOrganizm(x - 1, y) == null)
            {
                if (x > 0)
                {
                    x--;
                    break;
                }
            }
            else if (kierunek == 3)
            {
                if (x < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(x + 1, y) == null)
                {
                    x++;
                    break;
                }
            }
        }
        this.PostawDziecko(x, y);
    }


    @Override
    public void kolizja(Organizm o)
    {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zdeptal " + nazwa + "(" + px + "," + py + ")");
        swiat.UsunOrganizm(polozeniex, polozeniey);
    }

    protected boolean CzyOtoczona()
    {
        if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) == null)
        {
            return false;
        }
        if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) == null)
        {
            return false;
        }
        if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) == null)
        {
            return false;
        }
        if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) == null)
        {
            return false;
        }
        return true;
    }
}