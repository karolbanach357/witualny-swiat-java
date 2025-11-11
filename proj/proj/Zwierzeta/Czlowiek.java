package proj.Zwierzeta;
import java.util.Random;
import proj.Stale;
import proj.Swiat;

public class Czlowiek extends Zwierze{
    private int licznik;
    private int ruch;
    public Czlowiek(int polX, int polY, Swiat swiat) {
        super(polX, polY, swiat);
        this.sila = 5;
        this.inicjatywa = 4;
        this.znak_gatunku = Stale.CZLOWIEK;
        this.wiek = 0;
        this.nazwa = "Czlowiek";
        this.licznik = 0;
        this.ruch = 1;
    }

    public Czlowiek(int polozenieX, int polozenieY, int sila, int inicjatywa, String znak_gatunku, int wiek, String nazwa, Swiat swiat, int ruch, int licznik) {
        super(polozenieX, polozenieY, sila, inicjatywa, znak_gatunku, wiek, nazwa, swiat);
        this.licznik = licznik;
        this.ruch = ruch;

    }
    @Override
    public void akcja()
    {
        int x = polozeniex;
        int y = polozeniey;
        String k = swiat.GetKierunekCzlowieka();
        if (k.equals("zostaje"))
        {
            // nic
        }
        else if (k.equals("lewo") && x > 0)
        {
            x-=ruch;
        }
        else if (k.equals("prawo") && x < swiat.GetRozmiarX() - 1)
        {
            x+=ruch;
        }
        else if (k.equals("gora") && y > 0)
        {
            y-=ruch;
        }
        else if (k.equals("dol") && y < swiat.GetRozmiarY() - 1)
        {
            y+=ruch;
        }
        if (polozeniex == x && polozeniey == y)
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") zostaje na miejscu");
        }
        if (swiat.GetOrganizm(x, y) != null)
        {
            swiat.GetOrganizm(x, y).kolizja(this); //this wjechal w organizm na polu x,y
            if (wiek != -1 && swiat.GetOrganizm(x, y) == null)
            {
                polozeniex = x; // jesli this nie zostal zjedzony ORAZ zjadl to zmien polozenie
                polozeniey = y;
            }
            if (licznik <= 2 && licznik != 0)
            {
                Random rand = new Random();
                ruch = 1 + rand.nextInt(2);
            }
            if (licznik != 0 )
                licznik--;
            else
                ruch = 1;

            if (wiek == -1)
            {
                swiat.UsunOrganizm(polozeniex, polozeniey); // jesli this zostal zjedzony to go usun
            }

        }
        else
        {
            swiat.WypiszZdarzenie(nazwa + "(" + polozeniex + "," + polozeniey + ") przemiescil sie na (" + x + "," + y + ")");
            polozeniex = x;
            polozeniey = y;
            if (licznik <= 2 && licznik != 0)
            {
                Random rand = new Random();
                ruch = 1 + rand.nextInt(2);
            }
            if (licznik != 0 )
                licznik--;
            else
                ruch = 1;
        }
    }

    public void ZwiekszRuch()
    {
        if (licznik == 0)
        {
            licznik = 5;
            ruch = 2;
        }
    }

    public int GetLicznik()
    {
        return licznik;
    }

    public int GetRuch() {return ruch;}

    @Override
    public void PostawDziecko(int x, int y) {

    }
}
