Aplikacija za crtanje dvodimenzionalnih
geometrijskih oblika
- Funkcionalnost dodavanja, brisanja i modifikacije oblika na crtežu
- Organizacija komponenti saglasno MVC
- Undo/Redo funkcionalnost
- Korišćenje klasa sa interfejsom različitim od željenog
- Obezbeđenje perzistencije podataka

Korišćenjem Java Swing implementirati desktop aplikaciju za rad sa 2D grafikom. Aplikacija mora
podržavati funkcionalnosti koje su rađene u projektnom zadatku na predmetu Objektno orijentisane
informacione tehnologije.
Izmene/Dodatne funkcionalnosti:
1. nazivi klasa, metoda i promenljivih moraju biti na engleskom jeziku,
2. aplikacija mora biti realizovan u skladu sa MVC arhitektonskim obrascem,
3. dodavanje, brisanje i modifikacija šestougla (hexagon) koristeći Adapter obrazac za hexagon.jar,
4. poništavanje izvršenih komandi (undo funkcionalnost) – Command i Prototype obrazac,
ponovno izvršenje poništenih komandi (redo funkcionalnost) – Command i Prototype obrazac,
undo i redo dugme treba da budu dostupni samo kada je dostupna i funkcionalnost,
5. generisanje i prikaz loga izvršenih komandi u okviru aplikacije - Command?,
6. zapis u tekstualnu datoteku loga izvršenih komandi na eksterni memorijski medijum,
zapis kompletnog crteža (Serialization) na eksterni memorijski medijum, - Strategy obrazac,
7. učitavanje tekstualne datoteke koja sadrži log izvršenih komandi i na osnovu sadržaja, kreiranje
odgovarajućeg crteža, komandu po komandu u interakciji sa korisnikom, učitavanje kompletnog crteža,
8. promenu pozicije oblika po Z osi, ToFront (pozicija po pozicija), ToBack (pozicija po pozicija),
BringToFront (na najvišu poziciju), BringToBack (na najnižu poziciju),
9. prikazati trenutno aktivne boje za crtanje ivice i popunjavanje oblika, klikom na boju, otvara se
dijalog sa mogućnošću promene trenutno aktivne boje,
10. omogućiti selekciju više oblika,
11. Dugme za brisanje treba da bude dostupno samo u slučaju da postoje selektovani objekti – Observer
obrazac,
12. Dugme za modifikaciju treba da bude dostupan samo u slučaju kada je selektovan samo jedan oblik
– Observer obrazac.

Model - sadrži podatke aplikacije
View – predstavlja prikaz podataka
Controller – definiše kako aplikacija reaguje na interakciju korisnika

Za stanje dugmica - brisanje, selekcija, undo/redo - observer obrazac
Kod CmdUpdatePoint - originalState = oldState.clone();