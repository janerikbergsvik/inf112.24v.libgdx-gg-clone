## INF112 Project – *Echoing dreams; A sisters revenge*

**Team:** *Digi-teamet*

**Gruppemedlemmer:**
* Endre Håland Jørgensen
* Jan Erik Bergsvik
* Kristoffer Sørensen Vestbø
* Tanja Todorovic

## Om spillet
* Echoing Dreams; A sisters revenge er navnet på spillet. En Great Giana Sisters clone. 
* Kan styres med piltastene/tastaturet, og hopping skjer med piltast opp. Om man innehar en bullet power-up kan man skyte med space.
* Aktiver cheat-code med å holde inne "DIGI" tastene.
* Trykk "n" for å gå til neste brett.

## Kjøring
* Kompileres med `mvn package`.
* Kjøres deretter med `java -jar target/gdx-2dgame-1.0-SNAPSHOT-fat.jar`
* Kan også kjøres med `mvn exec:java`
* Krever Java 19 eller senere

# Maven setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File - Import - Maven - Existing Maven Projects* 
You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

These folders are used:
* `src/main/java` – Java source files are placed here.
* `src/main/resources` – data files go here
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files
# `doc` – Obligatory assignments and minutes of meeting documents.
	
## Running with Maven alternative
You can run the project with Maven using `mvn exec:java`. 

You can run with `mvn compile` – or in a single step, `mvn compile exec:java`.

## Testing with Maven
Run unit tests with `mvn test`.
This will also generate a [JaCoCo](https://www.jacoco.org/jacoco) code coverage report, which you can find in [target/site/jacoco/index.html](target/site/jacoco/index.html).

Use `mvn verify` to run any integration tests. 

## Jar Files advise/description
If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/gdx-2dgame-1.0-SNAPSHOT.jar` – your compiled project, packaged in a JAR file
* `target/gdx-2dgame-1.0-SNAPSHOT-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/gdx-2dgame-1.0-SNAPSHOT-fat.jar`.

If you have test failures, and *really* need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.

## Git Setup
Git repository branch protection has been activated on 'main' branch so everyone have to use merge requets.

## Kjente feil
* På enkelte pcer kan det være en litt spesiell oppførsel når spilleren skal hoppe. Dette skjer bare på utvalgte miljøer, og det  
har derfor ikke vært rett frem å utbedre.

# Credits

### Graphics:
### Glassy skin files
* `src/main/resources/skins/glassy` - https://github.com/czyzby/gdx-skins/tree/master/glassy - Raymond "Raeleus" Buckley, Licensed under; http://creativecommons.org/licenses/by/4.0/ (CC-BY-4.0)
* `src/main/resources/truetypefonts/giana` - https://www.kreativekorp.com/software/fonts/c64/, Licensed under; https://www.kreativekorp.com/software/fonts/PersonalLicense.txt
* `src/main/resources/levels/tilesets_scaled_32x32` - https://www.spriters-resource.com/submitter/GianaSistersFan64/, https://www.spriters-resource.com/nes/supermariobros/sheet/52571/, https://www.spriters-resource.com/commodore_64/greatgianasisters/, License information; https://www.spriters-resource.com/page/faq/
* `src/main/resources/textures/sisters_and_enemies` - https://www.spriters-resource.com/submitter/GianaSistersFan64/, License information; https://www.spriters-resource.com/page/faq/
* `src/main/resources/textures/powerups` - https://www.spriters-resource.com/submitter/GianaSistersFan64/, https://www.spriters-resource.com/custom_edited/gianasisterscustoms/sheet/113748/, License information; https://www.spriters-resource.com/page/faq/
* `src/main/resources/textures/gametitle00.png` - 
cooltext.com, Free online text generator
* https://www.spriters-resource.com/submitter/GianaSistersFan64/
* `src/main/resources/textures/bullet.png` - https://www.spriters-resource.com/submitter/GianaSistersFan64/, License information; https://www.spriters-resource.com/page/faq/


### Sound:
* `src/main/resources/music/kim-lightyear-leave-the-world-tonight-chiptune-edit-loop-132102.mp3`, ref. https://pixabay.com/music/search/c64/, Free for use under the Pixabay Content License, ref. https://pixabay.com/service/license-summary/
* `src/main/resources/sound/scale-f6-106128.mp3`, ref. https://pixabay.com/sound-effects/search/diamond. See license here; https://pixabay.com/service/license-summary/
* `src/main/resources/sound/mixkit-game-level-completed-2059.wav`, ref. https://mixkit.co/free-sound-effects/game-over/. See license here; https://mixkit.co/license/#sfxFree, name of effect; "Game level completed"
* `src/main/resources/sound/mixkit-little-piano-game-over-1944.wav`, ref. https://mixkit.co/free-sound-effects/game-over/. See license here; https://mixkit.co/license/#sfxFree, name of effect; "Little piano game over"

### References/tutorials used to learn about implementations
* https://www.youtube.com/@BrentAureliCodes 
More specifically his Mario tutorials from given list is highly appreciated and has been used as guidance through-out the
project.
* https://happycoding.io/tutorials/libgdx/game-screens
* https://libgdx.com/wiki/start/simple-game-extended
* https://libgdx.com/wiki/graphics/2d/orthographic-camera
* https://gamefromscratch.com/libgdx-tutorial-part-17-viewports/
* https://stackoverflow.com/questions/32451921/how-to-create-libgdx-main-menu-screen
* https://gamedev.stackexchange.com/questions/151188/libgdx-table-layout
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/
* https://gamedev.stackexchange.com/questions/151188/libgdx-table-layout
* https://libgdx.com/wiki/graphics/2d/scene2d/table
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/
* https://stackoverflow.com/questions/36696303/libgdx-how-can-i-add-freetype-extension
* https://gamedev.stackexchange.com/questions/151188/libgdx-table-layout
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/
* https://gamedev.stackexchange.com/questions/151188/libgdx-table-layout
* https://libgdx.com/wiki/graphics/2d/scene2d/table
* https://www.gamedevelopment.blog/full-libgdx-game-tutorial-menu-control/

### Licensing
For licensing related to the code developed by Digi-teamet, reference is made to the LICENSE.MD file for more information.  
The LICENSE.MD file is located in the repository root folder.  
The licenses related to the project "resource" files are specified above, and those licenses have to be adhered to in addition to what is  
specified in the LICENSE.md file.   
