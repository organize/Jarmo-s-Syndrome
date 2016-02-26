Pitistä hieman:

* impl-alakansioissa sijaitsevat luokat ovat funktionaalisesti identtisiä, 
	joten yhden tälläisen luokan testaus kattaa myös muiden impl-luokkien toiminnallisuuden.
* Joissain luokissa satunnaisuus estää testauksen. En saanut selvää, miten Random-luokkaa
	käyttävät metodit tulisi testata. Näitä luokkia esim. Spawner ja Location.