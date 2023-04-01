# Emotional Songs

"Emotional Songs" è un progetto universitario che si propone di analizzare le emozioni percepite dagli utenti ascoltando determinate canzoni. 
É un'applicazione sviluppata in Java, che utilizza un'interfaccia utente basata sulla CLI (Command-Line Interface). 
Grazie a questa interfaccia, gli utenti possono interagire con l'applicazione tramite comandi testuali inseriti direttamente da tastiera, in modo semplice e intuitivo. 

Inoltre, permette di creare playlist personalizzate in base alle emozioni che i brani evocano negli utenti. Durante l'ascolto, l'applicazione registra i feedback e le emozioni associate ai singoli brani, consentendo di creare una playlist personalizzata in grado di evocare le emozioni desiderate.

Infine, l'applicazione offre la possibilità di generare report e prospetti riassuntivi delle proprie playlist, visualizzando i punteggi associati alle diverse emozioni. Grazie a questo strumento, gli utenti possono analizzare la loro reazione emotiva ai brani, rilevando eventuali correlazioni tra le emozioni e le preferenze musicali.

Le emozioni monitorate dall'applicazione includono la gioia, la tristezza, la rabbia, la paura, l'euforia e la nostalgia. Grazie a questa gamma di emozioni, gli utenti possono creare playlist personalizzate in grado di evocare le sensazioni desiderate, rendendo l'ascolto della musica un'esperienza ancora più coinvolgente ed emozionante.


## Requisiti

Per far funzionare l'applicazione sono necessari i seguenti requisiti:

- Java JDK 1.8 o successivo


## Installazione ed Esecuzione

### Generazione del file .jar

- Per generare il file .jar dell'applicazione "Emotional Songs", seguire i seguenti passaggi:

1. Clonare il repository:
  ```sh
  git clone https://github.com/erikgurzau/emotional-songs.git
  ```
  
2. Accedere alla cartella del progetto:
```sh
 cd emotional-songs
```

3. Compilare l'applicazione:
```sh
javac *.java
```
Questo comando compila tutti i file Java presenti nella directory corrente.

**Nota:** per eseguire il comando `javac`, è necessario avere il JDK (Java Development Kit) installato sul proprio computer.

4. Generare il file .jar:
```sh
jar cvfe emotional-songs.jar Main *.class
```
Questo comando crea il file .jar dell'applicazione "Emotional Songs" nella directory corrente.

**Nota:** il parametro `cvfe` indica la creazione di un file .jar con il nome specificato (`emotional-songs.jar`), l'entry point specificato (`Main`), e i file `.class` presenti nella directory corrente.

### Esecuzione del file .jar

Una volta generato il file .jar dell'applicazione "Emotional Songs", seguire i seguenti passaggi per eseguirlo:

1. Aprire il terminale o la finestra del prompt dei comandi e accedere alla directory in cui è stato generato il file .jar.

2. Eseguire il file .jar con il comando:
```sh
java -jar emotional-songs.jar
```
**Nota:** Assicurarsi di avere installato la versione corretta di Java sul proprio computer. Per verificare la versione di Java installata, eseguire il comando `java -version` nel terminale o nella finestra del prompt dei comandi.


## Contributi

Ogni contributo al progetto è ben accetto! Se avete idee per migliorare l'applicazione, potete aprire una issue o una pull request. Segui la guida di Github per sapere cosa fare.


## Feature Future

- Sviluppare un algoritmo di suggerimento di canzoni basato sulle preferenze dell'utente, che aiuterebbe a creare playlist ancora più personalizzate e in linea con il loro stato d'animo. Questa nuova feature potrebbe fornire una maggiore interattività e coinvolgimento per gli utenti, migliorando l'esperienza d'uso dell'applicazione. 

- Sviluppare un algoritmo di analisi delle emozioni per generare una playlist di canzoni che corrispondono al tuo stato d'animo. L'algoritmo prende in input il tuo stato d'animo e le preferenze musicali e restituisce una playlist di canzoni selezionate in base all'emozione e al genere musicale


## Lingue

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg" height="16px" alt="English Flag">
  [English](https://github.com/erikgurzau/emotional-songs/blob/main/lan/README-EN.md)
</p>


## Licenza

Il progetto "Emotional Songs" è distribuito con la licenza MIT. Per ulteriori informazioni, consultare il file [LICENSE](LICENSE).

