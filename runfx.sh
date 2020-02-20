#!/bin/sh
echo Executant $1...
java --module-path pathTo-JAVAFX-SDK/lib --add-modules javafx.controls,javafx.fxml -jar $1

