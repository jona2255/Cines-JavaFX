### Com crear un executable independent del IDE

> És necessari que tingueu el plugin maven `maven-assembly-plugin` tal com es veu al `pom.xml` del projecte.

Després cal executar les següents accions:

`mvn compile assembly:single`

Això crea a la carpeta `target` el fitxer `jar` amb el nom i la versió indicades al `pom.xml`

> A continuació només cal executar:

`java --module-path: java --module-path pathSDK-Java/lib --add-modules javafx.controls,javafx.fxml -jar nomFitxer.jar
`

> Per simplificar la operació us podeu crear un script com el següent

[runfx.sh](runfx.sh)
