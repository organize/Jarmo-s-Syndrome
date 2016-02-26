<h4>Käyttöohje</h4>
* Olet viirus, eli toistaiseksi musta neliö keskellä ruutua.
* Peli on arcade-tyylinen, eli jatkumuoa ei ole. Peli vaikeutuu progressiivisesti.
<h4>Ohjaus</h4>
* `W` = Liiku ylös.
* `A` = Liiku vasemmalle.
* `S` = Liiku alas.
* `D` = Liiku oikealle.
* `MOUSE1` = Lähetä ammus. Ammuksissa ei ole "jäähyä", eli rämppää mielin määrin.
* `ESCAPE` = Pysäytä tai poista pysäytys.
* Paniikkinappula: `P`. Jos jotenkin velhomaisesti saat pelaajan katoamaan, palauttaa tämä nappi rakkaan neliömme origoon.
<h4>Pelimekaniikat</h4>
* Vasemmalla ylhäällä näät pisteesi. K tarkoittaa tuhansia, M miljoonia.
* Pelaajan päällä näkyvä numero on pelaajan taso. Taso nousee soluja tuhotessa, eri soluista saa eri määrän pisteita. Eniten K-soluista.
* Alhaalla on elämäpisteesi. Formaatti on `[nykyiset elämäpisteet/maksimielämäpisteet]`. Jokaisen kierroksen loputtua saat
1/8 maksimielämäpisteistäsi takaisin. Aloitat sadalla, maksimi on viisisataa.

* Soluja ja niiden tehtäviä:
 * Vihreä A-solu: Näiden solujen tehtävä on satuttaa pelaajaa. Niiden koko kertoo, kuinka paljon niillä on elämäpisteitä jäljellä.
 Jos A-solu onnistuu osumaan pelaajaan, menettää pelaaja A-solun elämäpisteiden määrän elämäpisteitään. A-solut kehittyvät nopeammaksi
 pelaajan tason noustessa.
 * Bakteeri: Vihreitä, neliön muotoisia pyöriviä härnääjiä. Ne auttavat pelaajaa, eli et voi vahingoittaa niitä. Bakteerit kehittyvät
 erittäin vahvoiksi pelaajan tason kohotessa. Bakteerit syövät A-ja E-soluja. Niitä syntyy pelaajan tasoon ja K-solujen määrään nähden.
 * Purppura K-solu: K-solut ovat kestäviä. Niiden tarkoitus on syödä bakteereja. Ne liikkuvat erittäin hitaasti, mutta eivät vahingoita pelaajaa.
 * Beigen värinen E-solu: E-solut voivat joko hidastaa pelaajaa tai auttaa A-soluja. E-solut lähettävät vihreitä elämäpisteitä A-soluille,
 joten niiden tuhoaminen alustavasti on suositeltavaa. Niiden tarjoamat hidastus-ja-pelastuspalvelut nopeutuvat mitä korkeampi pelaajan taso on.
 Jos pelaaja saavuttaa tason 10, kehittyvät E-solut tekemään molempia: eli hidastamaan ja pelastamaan.
 
 * Kun kaikki solut (bakteereja lukuunottamatta) ovat tuhottu, alkaa uusi kierros. Kierroksen aikana syntyy pelaajan tasoon nähden määrä
 erityyppisiä soluja.
 
 * Peliä ei voi voittaa, se on arcade-tyylinen. Yritä saada korkeat pisteet ja pidä hauskaa!
