# Emotional Songs

"Emotional Songs" is a university project that aims to analyze the emotions perceived by users while listening to certain songs. 
It is an application developed in Java, which uses a CLI (Command-Line Interface) based user interface. 
Thanks to this interface, users can interact with the application through text commands entered directly from the keyboard in a simple and intuitive way. 

It also allows users to create customized playlists based on the emotions that songs evoke in users. While listening, the application records feedback and emotions associated with individual songs, allowing the user to create a customized playlist that evokes the desired emotions.

Finally, the application offers the ability to generate reports and summary statements of one's playlists, displaying the scores associated with different emotions. Using this tool, users can analyze their emotional reaction to songs, detecting any correlations between emotions and music preferences.

Emotions monitored by the app include joy, sadness, anger, fear, elation, and nostalgia. With this range of emotions, users can create customized playlists that evoke the desired feelings, making music listening an even more engaging and exciting experience.


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

The "Emotional Songs" project is distributed under the MIT license. For more information, please see the [LICENSE](LICENSE) file.
