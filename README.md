# Anime List Project
Project Description: GUI-based editor for Anime List creator

Authors: Abhay Chopra, Brandon Greene

2022-04-11

### Project Internals:
GUI application, using JavaFX, to get a view of Anime objects created
through program. Additionally, adds saving and reading from file functionality
using FileChooser. Handles various events via incorporating event-driven design.

## Running the Project-from command line (Windows):

1) Go to directory location with provided .jar file
2) Launch Command Prompt from directory
3) Use the following command: java --module-path "Add Path where JavaFx openSDK is located" --add-modules
   javafx.controls,javafx.fxml -jar CPSC233ProjGui.jar

## Example run (when running command prompt from location with.jar file):
java --module-path "C:\Program Files\Java\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar CPSC233ProjGui.jar


For usage (when program is launched):
1) Add anime with desired episode length or load a library using world file (csv file)
2) Add anime to library (select original or extend off previous) by providing required information
3) Capable of viewing details about anime by pressing anime combo box
4) Able to delete anime in library by selecting anime from combo box and using "delete" button
5) Can save library data to external file (menu item)
6) Can acquire additional information about project using "About" in menu items
