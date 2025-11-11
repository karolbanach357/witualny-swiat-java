package proj.Rosliny;

import proj.Organizm;
import proj.Stale;
import proj.Swiat;

import java.util.Random;

public class BarszczSosnowskiego extends Roslina{

    public BarszczSosnowskiego(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.inicjatywa = 0;
        this.sila = 10;
        this.wiek = 0;
        this.nazwa = "BarszczSosnowskiego";
        this.znak_gatunku = Stale.BARSZCZ_SOSNOWSKIEGO;

    }

    public BarszczSosnowskiego(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
    }

    @Override
    public void akcja()
    {
        boolean rozmnozy = true;
        if (CzyOtoczona())
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie ma gdzie sie rozsiac");
            rozmnozy = false;
        }
        if(rozmnozy)
            Rozmnazanie(null);
        boolean zabil = false;
        if (polozeniex > 0 && swiat.GetOrganizm(polozeniex - 1, polozeniey) != null)
        {
            if (swiat.GetOrganizm(polozeniex - 1, polozeniey).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex - 1, polozeniey);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.UsunOrganizm(polozeniex - 1, polozeniey);
                zabil = true;
            }
        }
        if (polozeniex < swiat.GetRozmiarX() - 1 && swiat.GetOrganizm(polozeniex + 1, polozeniey) != null)
        {
            if (swiat.GetOrganizm(polozeniex + 1, polozeniey).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex + 1, polozeniey);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.UsunOrganizm(polozeniex + 1, polozeniey);
                zabil = true;
            }
        }
        if (polozeniey > 0 && swiat.GetOrganizm(polozeniex, polozeniey - 1) != null)
        {
            if (swiat.GetOrganizm(polozeniex, polozeniey - 1).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex, polozeniey - 1);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.UsunOrganizm(polozeniex, polozeniey - 1);
                zabil = true;
            }
        }
        if (polozeniey < swiat.GetRozmiarY() - 1 && swiat.GetOrganizm(polozeniex, polozeniey + 1) != null)
        {
            if (swiat.GetOrganizm(polozeniex, polozeniey + 1).GetInicjatywa() > 0)
            {
                Organizm o = swiat.GetOrganizm(polozeniex, polozeniey + 1);
                String px = String.valueOf(polozeniex);
                String py = String.valueOf(polozeniey);
                String p2x = String.valueOf(o.GetPolozenieX());
                String p2y = String.valueOf(o.GetPolozenieY());
                swiat.WypiszZdarzenie(nazwa + "(" + px + "," + py + ") zabil " + o.GetNazwa() + "(" + p2x + "," + p2y + ")");
                swiat.UsunOrganizm(polozeniex, polozeniey + 1);
                zabil = true;
            }
        }
        if(!zabil)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie zabil zadnego organizmu");
        }
    }

    @Override
    public void kolizja(Organizm o)
    {
        String px = String.valueOf(polozeniex);
        String py = String.valueOf(polozeniey);
        String p2x = String.valueOf(o.GetPolozenieX());
        String p2y = String.valueOf(o.GetPolozenieY());
        swiat.WypiszZdarzenie(o.GetNazwa() + "(" + p2x + "," + p2y + ") zjadl " + nazwa + "(" + px + "," + py + ") i razem z nim ginie");
        swiat.ZabijOrganizm(o.GetPolozenieX(), o.GetPolozenieY());
        swiat.UsunOrganizm(polozeniex, polozeniey);
    }
    @Override
    public void PostawDziecko(int x, int y) {
        Random rand = new Random();
        int szansa = rand.nextInt(10);
        if (szansa == 0)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") rozsiewa sie na (" + x + "," + y + ")");
            swiat.DodajOrganizm(new BarszczSosnowskiego(x, y, swiat));
            return;
        }
        swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") nie rozsiewa sie");
    }
}
