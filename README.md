# IB Computer Science HL IA

English/Indonesian Bible application. This application is Windows only and requires versions JavaSE9 and above. JavaSE8 will not work. Since JavaFX has been removed from JavaSE, I used Bellsoft's Liberica JDK-17-Full to develop this application which has JavaFX bundled. 

After packaging this application into a .jar, I couldn't double click the file to launch at first. However, [this solution](https://stackoverflow.com/a/17270546/16146059) from Stackoverflow solved the issue.

## Launching the Application

### Maven
- Clone the repository to your local system or download one of the releases 
- Open command prompt in the working directory and run the following
```
mvn javafx:run
```

### Windows Excecutable/JAR file
- Double-click the file


### Uninstalling the application
- Go to the install location and delete the directory
- If you have a JAR or exe simply delete the file.
- Go into %AppData$, find "BilingualBible" folder and delete it.

## Known Bugs
- Verse searching function does not work for chapters with verses that are split into two (e.g. verse 2a, verse 2b, etc.).
- Encoding error but verses are still legible