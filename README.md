# SAP System Analyzer Demo

This is a simple Java Swing application that demonstrates how to connect to an SAP system and execute RFC calls. On startup it displays a splash screen, a terms confirmation screen, and finally a login screen that allows selecting an SAP system and entering credentials.

The application uses the SAP Java Connector (JCo) library to call the standard `RFC_PING` function. After a successful ping, several placeholder RFC calls are executed with 5 second waits so the progress bar can be observed.

## Building

The project uses Maven. The SAP JCo library is not included and must be provided locally (see `pom.xml` for the path). Place `sapjco3.jar` in the `lib` directory or adjust the path accordingly.

```bash
mvn package
```

## Running

After building, run the application with:

```bash
java -cp target/sapanalyzer-1.0-SNAPSHOT.jar:lib/sapjco3.jar com.example.sapanalyzer.App
```

Ensure that the SAP JCo native libraries are available in your `java.library.path`.
