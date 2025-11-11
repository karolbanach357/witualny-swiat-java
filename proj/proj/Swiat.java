package proj;

import proj.Rosliny.*;
import proj.Zwierzeta.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class Swiat implements ActionListener, KeyListener {

    Czlowiek c;
    Random rand = new Random();
    JFrame frame = new JFrame("Karol Banach 197912 Wirtualny Swiat");
    JPanel title_panel = new JPanel();
    JPanel container = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel Zdarzenia_panel = new JPanel();
    JTextArea text_zdarzenia = new JTextArea();
    JPanel menu_buttons_panel = new JPanel();
    JButton[][] buttons;
    JButton[] menu_buttons;

    private int rozmiarX, rozmiarY;
    private String[][] plansza;
    private Organizm[] tab_wszystkich;
    private boolean running;
    private int linijka;
    private String kierunekczlowieka;
    private int xczlowieka;
    private int yczlowieka;


    public Swiat(int r_x, int r_y) {
        c = null;
        rozmiarX = r_x;
        rozmiarY = r_y;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(0, 100, 0));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.addKeyListener(this);

        text_zdarzenia.setBackground(new Color(0, 100, 0));
        text_zdarzenia.setForeground(new Color(255, 255, 255, 255));
        text_zdarzenia.setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth() / rozmiarY / 15));
        text_zdarzenia.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 2000, 2000);

        container.setLayout(new GridLayout(1, 2));
        container.setBounds(0, 0, 2000, 2000);

        button_panel.setLayout(new GridLayout(rozmiarY, rozmiarX));
        button_panel.setBackground(new Color(117, 173, 115));

        menu_buttons_panel.setLayout(new GridLayout(1, 4));
        menu_buttons_panel.setBackground(new Color(117, 173, 115));


        Zdarzenia_panel.setLayout(new BorderLayout());
        Zdarzenia_panel.setBackground(new Color(117, 173, 115));

        buttons = new JButton[rozmiarY][];
        for (int i = 0; i < rozmiarY; i++) {
            buttons[i] = new JButton[rozmiarX];
            for (int j = 0; j < rozmiarX; j++) {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth() / rozmiarX / 5));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(new Color(41, 225, 73));
                buttons[i][j].addActionListener(this);
                buttons[i][j].setText(Stale.ZIEMIA );
            }
        }

        menu_buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            menu_buttons[i] = new JButton();
            menu_buttons_panel.add(menu_buttons[i]);
            menu_buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth() / rozmiarX / 5));
            menu_buttons[i].setForeground(new Color(255, 86, 0));
            menu_buttons[i].setFocusable(false);
            menu_buttons[i].setBackground(new Color(173, 241, 171));
            menu_buttons[i].addActionListener(this);
        }

        menu_buttons_panel.setBounds(0, 0, 2000, 2000);
        menu_buttons[0].setText("Moc");
        menu_buttons[1].setText("Zapisz");
        menu_buttons[2].setText("Wczytaj");
        menu_buttons[3].setText("Kolejna");

        Zdarzenia_panel.add(text_zdarzenia);
        container.add(button_panel);
        container.add(Zdarzenia_panel);
        frame.add(container, BorderLayout.CENTER);
        frame.add(menu_buttons_panel, BorderLayout.SOUTH);

        running = true;
        linijka = 0;
        kierunekczlowieka = "zostaje";

        tab_wszystkich = new Organizm[rozmiarX * rozmiarY];
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            tab_wszystkich[i] = null;
        }

        plansza = new String[rozmiarY][];
        for (int i = 0; i < rozmiarY; i++) {
            plansza[i] = new String[rozmiarX];
        }
        for (int i = 0; i < rozmiarY; i++) {
            for (int j = 0; j < rozmiarX; j++) {
                plansza[i][j] = Stale.ZIEMIA;
            }
        }

        for (int i=0;i<rozmiarX*rozmiarY;i++)
        {
            int zmienna = rand.nextInt(15);
            int a = rand.nextInt(rozmiarX);
            int b = rand.nextInt(rozmiarY);
            switch(zmienna)
            {
                case 0:
                    tab_wszystkich[i] = new Wilk(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 1:
                    tab_wszystkich[i] = new Owca(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 2:
                    tab_wszystkich[i] = new Lis(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 3:
                    tab_wszystkich[i] = new Zolw(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 4:
                    tab_wszystkich[i] = new Antylopa(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 5:
                    tab_wszystkich[i] = new Trawa(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 6:
                    tab_wszystkich[i] = new Mlecz(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 7:
                    tab_wszystkich[i] = new Guarana(a,b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 8:
                    tab_wszystkich[i] = new WilczeJagody(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;
                case 9:
                    tab_wszystkich[i] = new BarszczSosnowskiego(a, b, this);
                    buttons[b][a].setText(tab_wszystkich[i].GetZnak_Gatunku());
                    break;

            }
        }


        int xc, yc;
        while(true)
        {
            xc = rand.nextInt(rozmiarX);
            yc = rand.nextInt(rozmiarY);
            if (GetOrganizm(xc, yc) == null)
            {
                break;
            }
        }
        xczlowieka = xc;
        yczlowieka = yc;
    }

    public void DrawMenu(int l)
    {
        text_zdarzenia.append("\n----------------------------------------\n");
        text_zdarzenia.append("Kliknij klawisz i zatwierdz enterem.\n");
        text_zdarzenia.append("Kliknij X aby wyjsc z gry.\n");
        text_zdarzenia.append("Wcisnij strzalke aby wybrac kierunek.\n");
        text_zdarzenia.append("Wcisnij Kolejna aby zostac na miejscu.\n");
        if (l < 0 || l > 10)
        {
            text_zdarzenia.append("Czlowiek nie zyje\n");
        }
        else if (l == 0)
            text_zdarzenia.append("Umiejetnosc gotowa do uzycia\n");
        else if (l > 5)
            text_zdarzenia.append("Umiejetnosc aktywna, pozostalo " + (l-5) + " tur\n");
        else if (l == 5)
            text_zdarzenia.append("Koniec umiejetnosci, do kolejnego uzycia pozostalo Ci 5 tur\n");
        else if (l < 5 && l != 0)
            text_zdarzenia.append("Umiejetnosc aktywna, pozostalo " + l + " tur\n");
    }
    public void Run()
    {
        int xc, yc;
        xc = GetXczlowieka();
        yc = GetYczlowieka();
        c = new Czlowiek(xc, yc, this);
        DodajCzlowieka(c);
        DrawMenu(c.GetLicznik());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu_buttons[0])
        {
            if (c.GetLicznik() == 0)
            {
                int s = c.GetRuch();
                c.ZwiekszRuch();
                if(c.GetRuch() != s)
                    text_zdarzenia.append("- moc aktywowana -");
            }
            return;
        }
        if(e.getSource()==menu_buttons[1])
        {
            if(c != null)
                Save(c.GetLicznik(),c.GetRuch());
            else
                Save(0,0);
            return;
        }
        if(e.getSource()==menu_buttons[2])
        {
            Load();
            return;
        }
        if(e.getSource()==menu_buttons[3])
        {
            if(c != null)
            {
                SetKierunekCzlowieka("zostaje");
            }
            Update();
            DrawMenu(c.GetLicznik());
        }
        for(int i=0; i<rozmiarY; i++)
        {
            for(int j=0;j<rozmiarX;j++)
            {
                if(e.getSource()==buttons[i][j])
                {
                    if (GetOrganizm(j, i) == null)
                    {
                        String[] opcje = {"Wilk", "Owca", "Lis", "Zolw", "Antylopa", "Trawa", "Mlecz", "Guarana", "WilczeJagody", "BarszczSosnowskiego"};
                        int opcja = JOptionPane.showOptionDialog(null, "Wybierz organizm", "Dodawanie organizmu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcje, opcje[0]);
                        int h = 0;
                        while (tab_wszystkich[h] != null)
                        {
                            h++;
                        }
                        switch(opcja)
                        {
                            case 0:
                                tab_wszystkich[h] = new Wilk(j, i, this);
                                break;
                            case 1:
                                tab_wszystkich[h] = new Owca(j, i, this);
                                break;
                            case 2:
                                tab_wszystkich[h] = new Lis(j, i, this);
                                break;
                            case 3:
                                tab_wszystkich[h] = new Zolw(j, i, this);
                                break;
                            case 4:
                                tab_wszystkich[h] = new Antylopa(j, i, this);
                                break;
                            case 5:
                                tab_wszystkich[h] = new Trawa(j, i, this);
                                break;
                            case 6:
                                tab_wszystkich[h] = new Mlecz(j, i, this);
                                break;
                            case 7:
                                tab_wszystkich[h] = new Guarana(j, i, this);
                                break;
                            case 8:
                                tab_wszystkich[h] = new WilczeJagody(j, i, this);
                                break;
                            case 9:
                                tab_wszystkich[h] = new BarszczSosnowskiego(j, i, this);
                                break;
                        }
                        //System.out.println("Dodano " + opcje[opcja] + " na pozycji (" + j + ", " + i + ")");
                        if(opcje[opcja] != null)
                            WypiszZdarzenie("Dodano " + opcje[opcja] + " na pozycji (" + j + ", " + i + ")");
                        RysujSwiat();
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e)
    {
        int licz = -1;
        if(c != null)
            licz = c.GetLicznik();
        switch(e.getKeyCode())
        {
            case 37: // left arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("lewo");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 38: // up arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("gora");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 39: // right arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("prawo");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 40: // down arrow key
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("dol");
                    Update();
                    DrawMenu(licz);
                }
                break;
            }
            case 32:
            {
                if(c != null)
                {
                    SetKierunekCzlowieka("zostaje");
                }
                Update();
                DrawMenu(licz);
                break;
            }
        }
    }

    public int GetRozmiarX() {
        return rozmiarX;
    }

    public int GetRozmiarY() {
        return rozmiarY;
    }

    public boolean isRunning() {
        return running;
    }

    public void Dopisz(int x, int y, String zn) {
        plansza[y][x] = zn;
    }

    public void DodajOrganizm(Organizm o) {
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (tab_wszystkich[i] == null) {
                tab_wszystkich[i] = o;
                buttons[o.GetPolozenieY()][o.GetPolozenieX()].setText(o.GetZnak_Gatunku() + "");
                break;
            }
        }
    }

    public Organizm GetOrganizm(int x, int y) {
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y) {
                return tab_wszystkich[i];
            }
        }
        return null;
    }
    public void ZabijOrganizm(int x, int y)
    {
        for (int i = 0; i < rozmiarX * rozmiarY; i++)
        {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y)
            {
                tab_wszystkich[i].SetWiek(-1);
            }
        }
    }
    public void UsunOrganizm(int x, int y)
    {
        for (int i = 0; i < rozmiarX * rozmiarY; i++)
        {
            if (tab_wszystkich[i] != null && tab_wszystkich[i].GetPolozenieX() == x && tab_wszystkich[i].GetPolozenieY() == y)
            {
                if(tab_wszystkich[i].GetNazwa().equals("Czlowiek"))
                  c = null;
                tab_wszystkich[i] = null;
                buttons[y][x].setText(Stale.ZIEMIA);
            }
        }
    }

    public void WypiszZdarzenie(String zdarzenie) {
        linijka++;
        if (linijka > 30)
            return;
        text_zdarzenia.append("\n" + zdarzenie);
        //frame.setSize(rozmiarx * 80, rozmiary * 60 + linijka * 20);
    }

    public boolean CzySaOtoczone(Organizm o1, Organizm o2)
    {
        int x1 = o1.GetPolozenieX();
        int y1 = o1.GetPolozenieY();
        int x2 = o2.GetPolozenieX();
        int y2 = o2.GetPolozenieY();
        if ((x1 - 1 < 0 || ( x1-1 >= 0 && GetOrganizm(x1 - 1, y1) != null)) && (x1 + 1 >= rozmiarX || (x1 + 1 < rozmiarX && GetOrganizm(x1 + 1, y1) != null)) && (y1 - 1 < 0 || (y1 - 1 >= 0 && GetOrganizm(x1, y1 - 1) != null)) && (y1 + 1 >= rozmiarY || (y1 + 1 < rozmiarY && GetOrganizm(x1, y1 + 1) != null)))
        {
            if ((x2 - 1 < 0 || (x2 - 1 >= 0 && GetOrganizm(x2 - 1, y2) != null)) && (x2 + 1 >= rozmiarX || (x2 + 1 < rozmiarX && GetOrganizm(x2 + 1, y2) != null)) && (y2 - 1 < 0 || (y2 - 1 >= 0 && GetOrganizm(x2, y2 - 1) != null)) && (y2 + 1 >= rozmiarY || (y2 + 1 < rozmiarY && GetOrganizm(x2, y2 + 1) != null)))
            {
                return true;
            }
        }
        return false;
    }

    public void RysujSwiat() {
        for (int i = 0; i < rozmiarX * rozmiarY; i++) {
            if (tab_wszystkich[i] != null) {
                tab_wszystkich[i].rysowanie();
            }
        }
        for (int i = 0; i < rozmiarY; i++) {
            for (int j = 0; j < rozmiarX; j++) {
                if (GetOrganizm(j, i) == null) {
                    buttons[i][j].setText(Stale.ZIEMIA);
                } else {
                    buttons[i][j].setText(plansza[i][j]);
                }
            }
        }
    }
    public void Update()
    {
        text_zdarzenia.setText("");
        text_zdarzenia.append("Zdarzenia:");
        linijka = 0;
        Sort();
        for (int i = 0; i < rozmiarX * rozmiarY; i++)
        {
            if (tab_wszystkich[i] != null)
                tab_wszystkich[i].Postarzej();
        }
        for (int i = 0; i < rozmiarX*rozmiarY; i++)
        {
            if (tab_wszystkich[i] != null)
            {
                if (tab_wszystkich[i].GetWiek() > 0)
                {
                    tab_wszystkich[i].akcja();
                }
            }
        }
        RysujSwiat();
    }
    public void Clean()
    {
        for (int i = 0; i < rozmiarX * rozmiarY; i++)
        {
            if (tab_wszystkich[i] != null)
                tab_wszystkich[i] = null;
        }
        tab_wszystkich = null;

        for (int i = 0; i < rozmiarY; i++)
        {
            if (plansza[i] != null)
                plansza[i] = null;
        }
        plansza = null;
    }

    public void SetKierunekCzlowieka(String kierunek)
    {
        kierunekczlowieka = kierunek;
    }

    public String GetKierunekCzlowieka()
    {
        return kierunekczlowieka;
    }

    public int GetXczlowieka()
    {
        return xczlowieka;
    }

    public int GetYczlowieka()
    {
        return yczlowieka;
    }
    public void DodajCzlowieka(Organizm o)
    {
        tab_wszystkich[20] = o;
        buttons[tab_wszystkich[20].GetPolozenieY()][tab_wszystkich[20].GetPolozenieX()].setText(tab_wszystkich[20].GetZnak_Gatunku());
    }

    private void Sort()
    {
        int n = rozmiarX * rozmiarY;
        boolean swapped = true;
        while(swapped)
        {
            swapped = false;
            for (int i = 0; i < n - 1; i++)
            {
                if (tab_wszystkich[i] == null)
                {
                    tab_wszystkich[i] = tab_wszystkich[i + 1];
                    tab_wszystkich[i + 1] = null;
                    swapped = true;
                }
                else if (tab_wszystkich[i + 1] == null)
                {
                    continue;
                }
                else if (tab_wszystkich[i].GetInicjatywa() < tab_wszystkich[i + 1].GetInicjatywa())
                {
                    Organizm temp = tab_wszystkich[i];
                    tab_wszystkich[i] = tab_wszystkich[i + 1];
                    tab_wszystkich[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }

    public void Save(int licz, int ruch)
    {
        Sort();
        String nazwapliku = JOptionPane.showInputDialog("Podaj nazwe pliku do zapisu");

        try {
            File file = new File(nazwapliku);
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write(rozmiarX + " " + rozmiarY + "\n");
            for (int i = 0; i < rozmiarX * rozmiarY; i++) {
                if (tab_wszystkich[i] == null) {
                    break;
                } else {
                    if (tab_wszystkich[i].GetNazwa().equals("Czlowiek")) {
                        writer.write(tab_wszystkich[i].GetNazwa() + " " + tab_wszystkich[i].GetPolozenieX() + " " +
                                tab_wszystkich[i].GetPolozenieY() + " " + tab_wszystkich[i].GetSila() + " " +
                                tab_wszystkich[i].GetInicjatywa() + " " + tab_wszystkich[i].GetZnak_Gatunku() + " " +
                                tab_wszystkich[i].GetWiek() + " " + ruch + " " + licz + "\n");
                    } else {
                        writer.write(tab_wszystkich[i].GetNazwa() + " " + tab_wszystkich[i].GetPolozenieX() + " " +
                                tab_wszystkich[i].GetPolozenieY() + " " + tab_wszystkich[i].GetSila() + " " +
                                tab_wszystkich[i].GetInicjatywa() + " " + tab_wszystkich[i].GetZnak_Gatunku() + " " +
                                tab_wszystkich[i].GetWiek() + "\n");
                    }
                }
            }
            writer.close();

            System.out.println("Zapisano gre do pliku " + nazwapliku);
        } catch (IOException e) {
            System.out.println("Wystapil blad podczas zapisywania do pliku.");
            e.printStackTrace();
        }
    }
    public void Load()
    {
        String nazwapliku = JOptionPane.showInputDialog("Podaj nazwe pliku do odczytu");

        try {
            File file = new File(nazwapliku);
            if(!file.exists())
            {
                System.out.println("Plik nie istnieje");
                return;
            }
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for(int i=0; i<rozmiarX*rozmiarY; i++)
            {
                if(tab_wszystkich[i] != null)
                    tab_wszystkich[i] = null;
            }
            tab_wszystkich = null;

            for(int i=0; i<rozmiarY; i++)
            {
                if(plansza[i] != null)
                    plansza[i] = null;
            }
            plansza = null;

            for(int i=0; i<rozmiarY; i++)
            {
                for(int j=0; j<rozmiarX; j++)
                {
                    if(buttons[i][j] != null)
                        buttons[i][j] = null;
                }
                buttons[i] = null;
            }
            buttons = null;

            button_panel.removeAll();

            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");
            rozmiarX = Integer.parseInt(parts[0]);
            rozmiarY = Integer.parseInt(parts[1]);
            System.out.println("Wczytano rozmiar planszy: " + rozmiarX + "x" + rozmiarY);
            //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            button_panel.setLayout(new GridLayout(rozmiarY, rozmiarX));

            buttons = new JButton[rozmiarY][];
            for(int i=0;i<rozmiarY; i++)
            {
                buttons[i] = new JButton[rozmiarX];
                for(int j=0; j<rozmiarX; j++)
                {
                    buttons[i][j] = new JButton();
                    button_panel.add(buttons[i][j]);
                    buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, frame.getWidth()/rozmiarX/5));
                    buttons[i][j].setFocusable(false);
                    buttons[i][j].setBackground(new Color(41, 225, 73));
                    buttons[i][j].addActionListener(this);
                    buttons[i][j].setText(Stale.ZIEMIA);
                }
            }

            running = true;
            linijka = 0;
            kierunekczlowieka = "zostaje";

            tab_wszystkich = new Organizm [rozmiarX * rozmiarY];
            for (int i = 0; i < rozmiarX * rozmiarY; i++)
            {
                tab_wszystkich[i] = null;
            }

            plansza = new String[rozmiarY][];
            for (int i = 0; i < rozmiarY; i++)
            {
                plansza[i] = new String[rozmiarX];
            }
            for (int i = 0; i < rozmiarY; i++)
            {
                for (int j = 0; j < rozmiarX; j++)
                {
                    plansza[i][j] = Stale.ZIEMIA;
                }
            }

            String linia;
            int u = 0;
            while ((linia = bufferedReader.readLine()) != null) {
                String[] dane = linia.split(" ");
                String nazwa = dane[0];
                int x = Integer.parseInt(dane[1]);
                int y = Integer.parseInt(dane[2]);
                int sila = Integer.parseInt(dane[3]);
                int inicjatywa = Integer.parseInt(dane[4]);
                String znak = dane[5];
                int wiek = Integer.parseInt(dane[6]);
                System.out.println("Wczytano: " + nazwa + " " + x + " " + y + " " + sila + " " + inicjatywa + " " + znak + " " + wiek);
                if(nazwa.equals("Czlowiek"))
                {
                    int ruch = Integer.parseInt(dane[7]);
                    int licz = Integer.parseInt(dane[8]);
                    c = new Czlowiek(x, y, sila, inicjatywa, znak, wiek, nazwa, this, ruch, licz);
                    tab_wszystkich[u] = c;
                }
                else if(nazwa.equals("Wilk"))
                {
                    tab_wszystkich[u] = new Wilk(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Owca"))
                {
                    tab_wszystkich[u] = new Owca(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Lis"))
                {
                    tab_wszystkich[u] = new Lis(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Zolw"))
                {
                    tab_wszystkich[u] = new Zolw(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Antylopa"))
                {
                    tab_wszystkich[u] = new Antylopa(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Trawa"))
                {
                    tab_wszystkich[u] = new Trawa(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Mlecz"))
                {
                    tab_wszystkich[u] = new Mlecz(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("Guarana"))
                {
                    tab_wszystkich[u] = new Guarana(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("WilczeJagody"))
                {
                    tab_wszystkich[u] = new WilczeJagody(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                else if(nazwa.equals("BarszczSosnowskiego"))
                {
                    tab_wszystkich[u] = new BarszczSosnowskiego(x, y, sila, inicjatywa, znak, wiek, nazwa, this);
                    buttons[y][x].setText(tab_wszystkich[u].GetZnak_Gatunku());
                }
                u++;

            }
            RysujSwiat();

            bufferedReader.close();
            reader.close();

            System.out.println("Odczytano gre z pliku " + nazwapliku);
        } catch (IOException e) {
            System.out.println("Wystapil blad podczas odczytywania z pliku.");
            e.printStackTrace();
        }
    }


}