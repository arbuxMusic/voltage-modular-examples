# File, Drag and Drop

This module shows you how to setup and receive file drag and drop events.
The module also shows how to use the Open and Save file dialogs in a module.

## File Drop

If the VoltageModule is registered for file drops, dragging and dropping a file on the module will fire a File_Dropped dropped notification.

To register a file type for drag and drop (e.g. from your file explorer), call the RegisterFileTypesForFileDrop passing in the allowed extensions separated by semicolons.
Note that the extension should not include "*.".

The example below allows dragging and dropping of png and jpg files.

```java
	RegisterFileTypesForFileDrop("png;jpg"); // allow png or jpg files to be dropped
```

If you would like to also allow folders to be dragged and dropped, you can append a semi colon on the file register option:
```java
	RegisterFileTypesForFileDrop(";"); // allow folder drops only
	// or
	RegisterFileTypesForFileDrop("png;jpg;"); // allow png, jpg file or folder drops
```

Once registered, the module will accept the allowed file/folder drops by firing the File_Dropped notification. The notification's object parameter specified the path to the file or the folder.
The module will receive the event. Component will always be null.

## Open / Save file dialogs

The Open / Save dialogs have three parameters and return the selected filename (or empty string where the user clicks cancel).
* dialogBoxTitle should be left null for the default text or filled in with specific text you'd like to display in the open dialog title area.
* initialPath is the initial browser path for the Dialog. This should be left null to select the last opened path, unless your module requires a specific path to be selected each time. 
* fileTypes list the allowed file types to be displayed
	* Passing null will allow selection of all file types
	* File types can be listed as *.extension with semi-colon separators to limit the selectable files. e.g. *.png;*.jpg

Example java code for the ShowOpenFileDialog function:
```java
    String dialogBoxTitle = null; // could also be "Select a file"
                   
    // Set the initial path.
    // I would suggest leaving this null as that leaves the last folder selected by the user
    // as the one shown.
    String initialPath = null; // could also be VoltageSystem.GetDocumentsPath();
    
    // The file types that the dialog will filter on
    String fileTypes = "*.png;*.jpg";
    
    // Show the open file dialog
    String selectedFile = ShowOpenFileDialog(dialogBoxTitle, initialPath, fileTypes);
    if (selectedFile != "") {
        // A file was selected, so do something with it
        ...
    }
```

Example java code for the ShowSaveFileDialog function has identical parameters:
```java
    // Set the initial path.
    // I would suggest leaving this null as that leaves the last folder selected by the user
    // as the one shown.
    String initialPath = null; // VoltageSystem.GetDocumentsPath();
    
    // The file types that the dialog will filter on
    String fileTypes = "*.png;*.jpg"; // can be null to show all files
    
    // Show the Save file dialog
    String selectedFile = ShowSaveFileDialog("Select a file", initialPath, fileTypes);
    
    // If the cancel button is pressed, the selectedFile will return an empty string
    if (selectedFile != "") {
        // A file was selected, so do something with it
        ...
    }
```

## Other notes

Surprisingly, the built in Drag and Drop notifications in the Notify method seem to not relate to files, but instead to Buttons.
Calling the SetDragMode(true) method on the button enables Control_DragStart, DragOn, DragOff and DragEnd methods.
Storing this info here in case it's useful in future.
