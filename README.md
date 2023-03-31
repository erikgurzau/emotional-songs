# Emotional Songs

Emotional Songs è un progetto universitario che consente di creare playlist musicali in base al proprio stato d'animo.

## Requisiti

Per far funzionare l'applicazione sono necessari i seguenti requisiti:

- Java JDK 1.8 o successivo

## Installazione ed Esecuzione

### Generazione del file .jar

- Per generare il file .jar dell'applicazione "Emotional Songs", seguire i seguenti passaggi:

1. Clonare il repository:
  '''sh
  git clone https://github.com/erikgurzau/emotional-songs.git
  '''
  
2. Accedere alla cartella del progetto:
'''sh
 cd emotional-songs
'''

3. Compilare l'applicazione:
'''sh
javac *.java
'''
Questo comando compila tutti i file Java presenti nella directory corrente.

**Nota:** per eseguire il comando `javac`, è necessario avere il JDK (Java Development Kit) installato sul proprio computer.

4. Generare il file .jar:
'''sh
jar cvfe emotional-songs.jar Main *.class
'''
Questo comando crea il file .jar dell'applicazione "Emotional Songs" nella directory corrente.

**Nota:** il parametro `cvfe` indica la creazione di un file .jar con il nome specificato (`emotional-songs.jar`), l'entry point specificato (`Main`), e i file `.class` presenti nella directory corrente.

### Esecuzione del file .jar

Una volta generato il file .jar dell'applicazione "Emotional Songs", seguire i seguenti passaggi per eseguirlo:

1. Aprire il terminale o la finestra del prompt dei comandi e accedere alla directory in cui è stato generato il file .jar.

2. Eseguire il file .jar con il comando:
'''sh
java -jar emotional-songs.jar
'''
**Nota:** Assicurarsi di avere installato la versione corretta di Java sul proprio computer. Per verificare la versione di Java installata, eseguire il comando `java -version` nel terminale o nella finestra del prompt dei comandi.

## Contributi

Ogni contributo al progetto è benvenuto! Se avete idee per migliorare l'applicazione, potete aprire una issue o una pull request.

## Come scaricare ed eseguire

Per scaricare ed eseguire Emotional Songs, segui i seguenti passaggi:

1. Clona il repository su GitHub utilizzando il seguente comando:
'''sh
git clone https://github.com/erikgurzau/emotional-songs.git
'''

2. Importa il progetto in un ambiente di sviluppo integrato (IDE) come Eclipse o NetBeans.

3. Esegui il progetto dall'IDE o esegui il file jar generato.

## Feature Future

Si potrebbe sviluppare un algoritmo di suggerimento di canzoni basato sulle preferenze dell'utente, che aiuterebbe a creare playlist ancora più personalizzate e in linea con il loro stato d'animo. Questa nuova feature potrebbe fornire una maggiore interattività e coinvolgimento per gli utenti, migliorando l'esperienza d'uso dell'applicazione. 

Algoritmo di analisi delle emozioni per generare una playlist di canzoni che corrispondono al tuo stato d'animo. L'algoritmo prende in input il tuo stato d'animo e le preferenze musicali e restituisce una playlist di canzoni selezionate in base all'emozione e al genere musicale
