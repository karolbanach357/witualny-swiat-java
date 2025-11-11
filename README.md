# 🌍 Wirtualny Świat / Virtual World Simulator

## 🇵🇱 Opis projektu

Projekt przedstawia **symulator wirtualnego świata** — turową grę, w której na dwuwymiarowej planszy (domyślnie 20×20) żyją i oddziałują na siebie różne organizmy.  
Każdy organizm zajmuje jedno pole i może wykonywać charakterystyczne dla swojego gatunku akcje, takie jak ruch, walka, rozmnażanie czy rozprzestrzenianie.

Program został zrealizowany w ramach kursu **Programowanie Obiektowe** i stanowi praktyczną implementację zasad **dziedziczenia**, **polimorfizmu**, **hermetyzacji** oraz **abstrakcji**.

---

## 🧩 Struktura projektu

- **Świat (World)** — zarządza całą symulacją, organizmami, turami i kolizjami.  
- **Organizm (Organism)** — klasa abstrakcyjna definiująca wspólne cechy (siła, inicjatywa, pozycja).  
- **Zwierzę (Animal)** — klasa pochodna od Organizm, implementuje ruch, kolizje i rozmnażanie.  
- **Roślina (Plant)** — klasa pochodna od Organizm, odpowiedzialna za rozprzestrzenianie się.  
- **Człowiek (Human)** — specjalny organizm sterowany przez gracza, posiadający unikalną **umiejętność specjalną** aktywowaną na określony czas.  

---

## 🐾 Organizmy w świecie

### Zwierzęta:
| Gatunek        | Siła | Inicjatywa | Cechy specjalne |
|----------------|------|-------------|------------------|
| Wilk           | 9    | 5           | standardowe zachowanie |
| Owca           | 4    | 4           | brak |
| Lis            | 3    | 7           | unika silniejszych organizmów |
| Żółw           | 2    | 1           | 75% szansy na brak ruchu, odpiera słabszych |
| Antylopa       | 4    | 4           | porusza się o 2 pola, może uciec z walki |

### Rośliny:
| Gatunek              | Siła | Cechy |
|----------------------|------|-------|
| Trawa                | 0    | standardowa |
| Mlecz                | 0    | próba rozprzestrzenienia 3× na turę |
| Guarana              | 0    | zwiększa siłę po zjedzeniu |
| Wilcze Jagody        | 99   | zabijają zwierzę, które je zje |
| Barszcz Sosnowskiego | 10   | zabija sąsiadujące zwierzęta (z wyjątkiem cyber-owcy) |

---

## 🧠 Umiejętności człowieka

Umiejętność specjalna zależy od numeru indeksu:
| ID | Nazwa              | Opis |
|----|--------------------|------|
| 0  | Nieśmiertelność    | Człowiek nie może zginąć, unika ataku |
| 1  | Magiczny Eliksir   | Tymczasowo zwiększa siłę do 10 |
| 2  | Szybkość antylopy  | Ruch o 2 pola, stopniowo słabnący efekt |
| 3  | Tarcza Alzura      | Odbija atakujące zwierzęta |
| 4  | Całopalenie        | Niszczy wszystkie organizmy wokół |

---

## 🧭 Sterowanie

- **Strzałki** – ruch człowieka  
- **Spacja / Enter** – kolejna tura  
- **S** – zapis stanu świata  
- **L** – wczytanie zapisanego stanu  
- **A** – aktywacja umiejętności specjalnej  

---

## 🖼️ Wizualizacja

W **interfejsie graficznym Swing** (wariant Java).  
Każdy organizm jest reprezentowany przez unikalny znak ASCII lub ikonę graficzną.

---

## ⚙️ Uruchomienie

### Kompilacja (Java)
```bash
javac Main.java
java Main
```

## 🧑‍💻 Autor
**Karol Banach**  
Projekt zrealizowany w ramach przedmiotu *Programowanie Obiektowe*.

---
