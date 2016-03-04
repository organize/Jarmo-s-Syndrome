<h4> Rakennekuvaus</h4>

* Kaiken "isäntänä" toimii `SyndromeFactory`, joka sisältää ja hallinnoi instansseja tärkeimmistä luokista.
Luokka sisältää maksimissaan yhden instanssin näistä luokista, eikä niihin pääse käsiksi muualta.
 * Toinen "isäntäluokka" on `GUIManager`, jonka instanssi sisältyy `SyndromeFactory`. Luokka pitää yllä instansseja GUI-luokista,
esimerkiksi päävalikosta ja peliruudusta.
 * `World`-luokka sisältää kaiken itse pelin sisäisen datan. Se pitää listaa ammuksista, pelaajasta ja ei-pelattavista hahmoista. 
Kun `SyndromeFactory` luo instanssin tästä luokasta, luo `World` myös `Player`in sisälleen.
  * `SyndromeTimer` sisältyy `World`iin, ja se hallinnoi pelikierroksen aikana tehtävät asiat. Sen sisältämä `tick`-metodi kutsutaan
  n kertaa sekunnissa, jossa n = ruudun päivitysnopeus. `tick` kutsuu myös pelaajien, ei-pelaajien ja `Spawner`in omia `tick`-metodeita.
  Eli jos peli on pysäytetty, ei tämä metodi toteuta kutsuja muuallekkaan. 
    * `Spawner`-luokka hoitaa ei-pelaajien luomisen satunnaisin intervallein, kun `World`-luokan lista ei-pelaajista (miinus `Bacteria`-instanssit)
    on nolla. Se luo satunnaisittain ei-pelaajia ja asettaa ne satunnaisiin koordinaatteihin.
    
    
* `Entity` on pelaajan ja ei-pelaajan rajapinta. Rajapinta tarjoaa joitain yhteneviä attribuutteja, kuten sijainti ja koko.
  * `NPC` on abstrakti luokka, joka esittää ei-pelaajaa. Se tarjoaa paljon omia metodeitaan, mutta osa sen alaluokista `entity.impl`-kansiossa
  ylikirjoittaa metodeita omaan käyttöön. Kaikki `entity.impl`-luokat toteuttavat `NPC`, joten siis myös `Entity`n.
  * `Player` kuvaa pelaajaa. Se toteuttaa `Entity`n, mutta sisältää joitain uniikkeja attribuutteja `NPC` verrattuna, mm. `Direction`-määrittelyn,
  joka kertoo pelaajan nykyisen liikkeen suunnan.
  
* Viimeisenä keskeisenä rakenteena, muttei vähäisimpänä, on `Projectile`-rajapinta. Sen toteuttaa kaikki `projectile.impl`-kansion
 luokat. Rajapinta esittää ammusta, eli siis teoriassa jotain, mikä 
  1. Lähtee jostain.
  2. Lähenee jotain.
  3. Osuu tai ei osu johonkin.
  
  * "Joku" voi siis olla jokin koordinaatti, tai vaikka `Entity`-instanssi, jolloin ammus on hakeutuva.
  * Ammuksia käyttää pelaaja ja ei-pelaajat. Ei-pelaajat voivat ammuksilla esim. hidastaa pelaajaa tai parantaa vaurioituneita ei-pelaajia.
  
* Muuta huomioitavaa:
 1. `Toolbox` tarjoaa apumetodeita yleiskäyttöön.
 2. `AudioManager` tarjoaa ääniin liittyviä apumetodeita, ja ylläpitää listaa jo muistiin ladatuista äänitiedostoista.
 2. Koodissa esiintyvä metodi `updateTranslation` (muodossa tai toisessa) tarkoittaa ammuksen tai ei-pelaajan relatiivista
 liikuttamista pelaajaan nähden. Pelialusta on 640x480 pikseliä, ja koordinaatit vasemmassa yläkulmassa ovat [-320, -240]. 
 Origo on siis keskellä pelialustaa. JavaFX:n dokumentaatioista löytyy lisää `translateX/translateY`-kentistä ja niiden funktiosta.
