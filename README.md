# Space_Shooter

## Auteurs
Sandrine EAR <br />
Ingrid THERET <br />


## Version de Java

La version 13 de Java a été utilisé pour réaliser ce projet


## Lancement (Option 1)

Deux fichiers permettent d'installer les fichiers permettant au jeu de fonctionner (il s'agit des jar de javaFx) <br />
La commande pour installer les fichiers sous linux > sh ./install_linux.sh<br />
La commande pour installer les fichiers sous Windows > sh ./install_windows.sh<br />
La commande pour desinstaller ces fichiers > sh ./desinstall_files.sh<br />

Lorsque les fichiers sont installés, lancer le fichier `build.xml` (Cocher compile et run )<br />

S'il y a un problème pour le lancement, regarder l'option 2.


## JavaFx (Option 2)

Si le fonctionnement à partir du fichier xml ne fonctionne pas ou si on souhaite installer JavaFx


### Installation de JavaFx
Téléchager le fichier correspondant au systeme d'exploitation à l'adresse: <https://gluonhq.com/products/javafx/> <br />
Prendre la Version 18 <br />
Dezipper le fichier `openjfx-<nom>.zip` <br />
le dossier qui a été dezippé doit avoir un nom de type `javafx-sdk-18/` contenant un dossier `lib/` où se trouve les jar <br />

Dans Eclipse: <br />
Click Droit sur le projet > Properties > Java Build Path > Librairies > Classpath <br />
Selectionner Add Librairies > User Library > New  <br />
  lui donner un nom (JavaFX18 par exemple) <br />
Selectionner la librairie (JavaFX18) > Add External JARs... <br />
  trouver le dossier où on a dezippé le zip > `lib` > selectionner tout les .jar <br />
Apply And Close <br />

### Lancement du jeu à partir d'Eclipse
Clique droit sur le main > Run As > Run Configurations... > Arguments <br />
  Ajouter dans VM arguments: --module-path **absolute_path**/javafx-sdk-18/lib --add-modules javafx.controls,javafx.media <br />
  où **absolute_path** est le chemin absolu du dossier javafx-sdk-18 <br />
  
  
## Jeu : space shooter

### Commandes
Vous contrôlez votre vaisseau: 
- utilisez les flèches directionnelles pour le faire bouger
- appuyer sur espace pour tirer

### Règles
Ils y a trois types d'ennemies:
- des météores destructibles, elles rapportent 1 point si vous les detruisez avec vos missiles
- des météores indestructibles, elles détruisent vos missiles et ne peuvent être détruites
- des ennemis qui tirent des missiles, elles rapportent 5 points si vous les detruisez, 1 point lors d'une collision avec eux
 <br />
Vous pouvez entrez en collision avec ces objets et cela vous fera perdre une vie. Vous pouvez néanmoins regagnez des vies: le jeu compte le nombre de hits que vous avez fait et vous redonne une vie à chaque palier (par exemple tout les 5 hits) <br />

Game Over lorsque vous perdez toutes vos vies.

