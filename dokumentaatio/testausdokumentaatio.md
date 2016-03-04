<h4>Testausdokumentaatio</h4>
* Joitain alaluokkia ei ole testattu, koska ne toimivat virtuaalisesti samanlailla kuten vertaisensa.
  * esim. `Projectile`-rajapinnan alaluokat. Vain `Entanglement`-luokka tulee testatuksi, koska muut alaluokat toimivat identtisesti.
  En siis nähnyt tärkeäksi käyttää aikaa copypasteen.
  * Sama pätee mm. `NPC`-alaluokille. 
* `Spawner`, `SyndromeTimer` ja `Location` saavat huonon rivikattauksen `Random`-luokan käytöstä. 
   En tiedä, miten näitä luokkia tulisi testata automaattisesti, joten tein sen manuaalisesti.
  * Breakpointein ja printein selvitin, että esim. `Location`in satunnaisgenerointi ei koskaan tapahdu pelaajan päälle.
* UI-luokkia tai Input-luokkia ei ilmeisesti tarvinnut testata, ja logiikka tuli priorisoida. Logiikka onkin siis perinpohjaisesti testattu, sekä
yksikkötesteillä että manuaalisesti.
<h4>Bugeja</h4>
* Tiedossani ei ole ainakaa mitään merkittävää bugia.
 * Yksi outo bugi on peliruudun komponenttien prioriteetti muiden yli. Tätä ei JavaFX:ssä pystynyt säätämään.
   * Eli siis esim. E-solu voi ilmestyä hiscore-palkin päälle. Ratkaisu olisi piirtää palkit aina uudestaan jokaisella pelipäivityksellä.
     Totesin, ettei bugi vaikuta ohjelman toimivuuteen mitenkään merkittävästi, ja uudelleenpiirtäminen kävisi raskaaksi.
* Se, että pelaaja voi olla "puoliksi" ruudun ulkopuolella, on toiminnallisuus. Koska vihollinen voi syntyä esim. koordinaatteihin [320, 0],
 on tällä ratkaisulla mahdollisuus kiertää vihollinen.
* 5. tason jälkeinen unlock on tyhjänä.
