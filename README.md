# Emotional Songs

"Emotional Songs" è un progetto universitario che si propone di analizzare le emozioni percepite dagli utenti ascoltando determinate canzoni. 
É un'applicazione sviluppata in Java.

La versione `v1.0.0` utilizza un'interfaccia utente basata sulla CLI (Command-Line Interface), ovvero gli utenti possono interagire con l'applicazione tramite comandi testuali inseriti direttamente da tastiera.
Per l'archiviazione dei dati, viene utilizzata una struttura organizzata basata su file di testo, simili per formato ai file .csv. In questo modo, i dati possono essere facilmente gestiti e manipolati senza la necessità di un database complesso.

In "Emotional Songs" è possibile creare playlist personalizzate con qualsiasi brano e gli utenti hanno la possibilità di registrare le proprie reazioni emotive associate a ciascuna canzone della playlist. Per valutare l'emozione suscitata da una canzone, gli utenti possono selezionare l'emozione desiderata e assegnare un valore di intensità da 1 a 5. Questa funzionalità permette agli utenti di tenere traccia delle emozioni evocate dalle canzoni e di personalizzare le proprie playlist in base alle preferenze personali.

Infine, l'applicazione offre la possibilità di generare report e prospetti riassuntivi sulle emozioni provate durante l'ascolto delle playlist, visualizzando l'intensità assegnata alle diverse canzoni. Grazie a questo strumento, gli utenti possono analizzare la loro reazione emotiva ai brani, rilevando eventuali correlazioni tra le emozioni e le preferenze musicali.

La scala di emozioni per poter valutare emotivamente un brano musicale è la seguente: la meraviglia, la solennità, la tenerezza, la nostalgia, la calma, la potenza, la gioia, la tensione e la tristezza. Grazie a questa gamma di emozioni, gli utenti possono creare playlist personalizzate in grado di evocare le sensazioni desiderate, rendendo l'ascolto della musica un'esperienza ancora più coinvolgente ed emozionante.

L'intero progetto di Emotional Songs si ispira a [GEMS](https://musemap.org/resources/gems) (Geneva Emotional Music Scale), un progetto di ricerca che mira a creare una scala standardizzata per misurare le emozioni suscitate dalla musica.


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

<p align="left">
  <img src="https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg" height="12px" alt="English Flag">
  <a href="https://github.com/erikgurzau/emotional-songs/blob/main/lan/README-EN.md">English Version</a>
</p>


## Licenza

Il progetto "Emotional Songs" è distribuito con la licenza MIT. Per ulteriori informazioni, consultare il file [LICENSE](LICENSE).

