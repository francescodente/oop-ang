### What is this repository for? ###

The purpose of this project is to emulate the classic arcade game Pang by Mitchell Corporation using Java programming language.

The stages start with differing numbers and sizes of balloons you have to shoot them to make them explode. 

The largest ball divides for the first three times it is popped; after the fourth and smallest ball is popped it vanishes.

If a ball touches the player you lose a life.

There are two game modes:

* In Tour Mode the player must finish a round-the-world quest visiting several Earth landmarks and cities.
	
* In Survival mode you have to stay alive as much as you can.
	
Keep playing to gain xp Points and reach rank 10. Every rank is rewarded with coins to upgrade your powers.

### How do I get set up? ###
1. Install a Java 17 or newer JDK.
2. Build the executable jar with Gradle:
   ```sh
   ./gradlew build
   ```
3. Run the generated jar:
   ```sh
   java -jar build/libs/oop-ang-1.0.0-all.jar
   ```

The project uses OpenJFX through Gradle because JavaFX is no longer bundled with the JDK after Java 8.

### Makers ###
* Samuele Burattini
* Nicholas Ciarafoni
* Francesco Dente
* Lorenzo Menghini
* Francesca Tonetti

This project was realized for the Object Oriented Programming course in Computer Science and Engineering department of UniBo, Cesena.
