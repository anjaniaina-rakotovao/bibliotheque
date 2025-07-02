@echo off
:: Définition des chemins
set PROJECT_DIR=.
set TOMCAT_DIR=C:\Apache Software Foundation\Tomcat 10.1\
set JAVA_DIR=%PROJECT_DIR%\src\main\java
set WEBAPPS_DIR=%TOMCAT_DIR%\webapps
set BUILD_DIR=%PROJECT_DIR%\build
set LIB_DIR=%PROJECT_DIR%\lib
set WEBAPP_DIR=%PROJECT_DIR%\src\main\webapp
set PROJECT_NAME="springBibliotheque"


echo Nettoyage du répertoire build...
if exist "%BUILD_DIR%" (
    rd /s /q "%BUILD_DIR%"
)
rd /s /q "%TOMCAT_DIR%\webapps\springBibliotheque"
del /s /q "%TOMCAT_DIR%\webapps\springBibliotheque.war"

mkdir "%BUILD_DIR%\WEB-INF\classes"

echo Compilation des fichiers Java...
:: Recherche récursive des fichiers Java et compilation
setlocal enabledelayedexpansion
set FILES=
for /R "%JAVA_DIR%" %%f in (*.java) do (
    set FILES=!FILES! "%%f"
)

javac -cp "%LIB_DIR%\*;." -d "%BUILD_DIR%\WEB-INF\classes" !FILES!

if %errorlevel% neq 0 (
    echo Une erreur est survenue pendant la compilation des fichiers Java. Arrêt du déploiement.
    pause
    exit /b %errorlevel%
)

echo Copie des fichiers web...
xcopy /s /y "%WEBAPP_DIR%\*" "%BUILD_DIR%\" /I
echo Copie des fichiers de configuration...
xcopy /s /y "%PROJECT_DIR%\src\main\resources\*" "%BUILD_DIR%\WEB-INF\"

echo Copie des fichiers de configuration Spring...
xcopy /s /i /y "lib\*.jar" "%BUILD_DIR%\WEB-INF\lib"
echo Archivage en .war...
jar -cvf "%PROJECT_NAME%.war" -C "%BUILD_DIR%" .

echo Copie du fichier .war dans Tomcat directory...
xcopy /y "%PROJECT_NAME%.war" "%WEBAPPS_DIR%"

echo Déploiement terminé avec succès !
@REM pause
