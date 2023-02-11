# IB Computer Science HL IA

English/Indonesian Bible application. This application is Windows only and requires the latest version of Java.

## Launching the Application

### Maven
- Clone the repository to your local system or download one of the releases 
- Open command prompt in the working directory and run the following
```
mvn javafx:run
```

### Windows Excecutable
- Still figuring out how to distribute the exe...


### Uninstalling the application
- Go to the install location and delete the directory
- Go into %AppData$, find "BilingualBible" folder and delete it.

## Known Bugs
- Verse searching function does not work for chapters with verses that are split into two (e.g. verse 2a, verse 2b, etc.).
- Encoding error but verses are still legible