<img src="https://svgshare.com/i/t6f.svg" alt="Emotional Songs Logo" width="600"/>

# Emotional Songs [![HitCount](https://hits.dwyl.com/erikgurzau/emotional-songs.svg?style=flat-square)](http://hits.dwyl.com/erikgurzau/emotional-songs)

`Emotional Songs` is a university project that aims to analyze the emotions perceived by users while listening to certain songs. 
It is an application developed in Java.

Version `v1.0.0` uses a CLI (Command-Line Interface) based user interface, meaning users can interact with the application through text commands entered directly from the keyboard.
For data storage, an organized structure based on text files, similar in format to .csv files, is used. In this way, data can be easily managed and manipulated without the need for a complex database.

In "Emotional Songs," customized playlists can be created with any song, and users have the ability to record their emotional reactions associated with each song in the playlist. To rate the emotion aroused by a song, users can select the desired emotion and assign an intensity value from 1 to 5. This feature allows users to keep track of the emotions evoked by songs and customize their playlists according to personal preferences.

Finally, the application offers the ability to generate reports and summary statements on the emotions felt while listening to the playlists, displaying the intensity assigned to different songs. Using this tool, users can analyze their emotional reaction to the songs, detecting any correlations between emotions and musical preferences.

The emotion scale for being able to emotionally evaluate a piece of music is as follows: wonder, solemnity, tenderness, nostalgia, calm, power, joy, tension, and sadness. With this range of emotions, users can create customized playlists that evoke the desired feelings, making listening to music an even more engaging and exciting experience.

The entire Emotional Songs project is inspired by the [GEMS](https://musemap.org/resources/gems) (Geneva Emotional Music Scale), a research project that aims to create a standardized scale to measure emotions elicited by music.

## Requirements

The following requirements are needed to run the application:
- Java JDK 1.8 or later


## Installation and Execution

### Generating the .jar file

- To generate the .jar file of the "Emotional Songs" application, follow the following steps:

1. Clone the repository:
  ```sh
  git clone https://github.com/erikgurzau/emotional-songs.git
  ```
  
2. Access the project folder:
```sh
 cd emotional-songs
```

3. Compile the application:
```sh
javac *.java
```
This command compiles all Java files in the current directory.

**Note:** to run the `javac` command, you must have the JDK (Java Development Kit) installed on your computer.

4. Generate the .jar file:
```sh
jar cvfe emotional-songs.jar Main *.class
```
This command creates the .jar file of the "Emotional Songs" application in the current directory.

**Note:** the `cvfe` parameter indicates the creation of a .jar file with the specified name (`emotional-songs.jar`), the specified entry point (`Main`), and the `.class` files in the current directory.

### Running the .jar file

Once the `emotional-songs` application .jar file has been generated, follow the following steps to run it:

1. Open the terminal or command prompt window and access the directory where the .jar file was generated.

2. Run the .jar file with the command:
```sh
java -jar emotional-songs.jar
```
**Note:** Make sure you have the correct version of Java installed on your computer. To check the version of Java installed, run the command `java -version` in the terminal or command prompt window.


## Contributions

Any contribution to the project is welcome! If you have ideas for improving the application, you can open an issue or pull request. Follow the Github guide for what to do.


## Feature Future.

- Develop a song suggestion algorithm based on user preferences, which would help create even more personalized playlists in line with their mood. This new feature could provide greater interactivity and engagement for users, improving the user experience of the app. 

- Develop an emotion analysis algorithm to generate a playlist of songs that match your mood. The algorithm takes your mood and music preferences as input and returns a playlist of songs selected based on emotion and music genre


## License

The `Emotional Songs` project is distributed under the MIT license. For more information, please see the [LICENSE](LICENSE) file.
