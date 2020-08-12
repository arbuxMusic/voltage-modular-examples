# ShowDropDownMethod

Provides example code on how to handle drop down menus.

## Setting up the control to accept mouse events

Voltage Module controls can be set to accept mouse notifications through the "Wants Mouse Notifications" on the control.
To setup a right click menu, you'll need to designate a control to have "Wants Mouse Notifications" to checked. This will enable the Object_RightButtonDown notification.
Be careful: Too many controls with the Wants Mouse Notifications option switched on will mean that the standard VoltageModular right click menu will not appear - not a great user experience.  

## Receiving the right click notfication and showing the drop down menu

Assuming the control has the "Wants Mouse Notifications" property checked, Object_Right/Left Button Up/Down notifications are sent through.

I will create a separate example on how to use mouse events at a future date. For the purposes of the sample, I want the Object_RightButtonDown notification (right mouse button down) to show the drop down menu.

The ShowDropDownMenu function takes in arguments to define the menu behaviour and returns the selected index.
* options: A String array containing the options to be shown in the dropdown menu.
* currentlySelectedIndex: The index of the menu item to show as checked. Pass in -1 if none of the options should be checked. If you need to track the 
* showMenuUnderThisObject: Pass in null for the menu to appear at the point the user right clicked. Pass in a component if the menu should appear under a specific component. Standard UI design principles would suggest that this should always be null.

The function will return -1 if the user clicks away from the menu (effectively cancelling the right click) or the index of the menu option that the user selects.

The sample code below shows the right click action (including the last selected option checked) and has a dummy area that the module would use to perform actions based on the right click option: 
```java
    case Object_RightButtonDown:

    // If the component is clicked
    if (component == rightClickLabel) {
    
       // Provide a string array with the options to display.
       // If these entries are static, I'd suggest making this is stored in a variable on startup rather than created every time in this method.
       String[] options = new String[] {"Option 1", "Option 2", "Option 3"};
       
       // lastSelectedIndex is a Module-level variable that stores the last selected state.
       // If a zero+ index is passed in, the will user see a checkmark for the selected menu index. (e.g. if the option list is a selection list).
       // If the right click is for a menu of actions that aren't selected, pass in -1 for this parameter.
       int currentlySelectedIndex = lastSelectedIndex;
       
       // The showMenuUnderThisObject parameter takes in an optional componet for where the menu should be shown
       // Recommend leaving this null to show it next to the current location of the cursor
       VoltageComponent showMenuUnderThisObject = null; // e.g. can also be component;
    
       // Show the dropdown menu based on the options above.
       // Returns -1 if the user does not select an option
       // Returns the index of the option that the user selected
       int newSelectedIndex = ShowDropDownMenu(options, currentlySelectedIndex, showMenuUnderThisObject);
       Log("User selected:" + newSelectedIndex);
       
       // This code allows the last selection to be tracked assuming you want the last selected item to have a checkmark next to it
       if (newSelectedIndex > -1) {
          lastSelectedIndex = newSelectedIndex;
       }
    
       // This code will allow individual actions be undertaken depending on the result of the ShowDropDownMenu call
       switch (newSelectedIndex) {   
          case 0: // Option 1 pressed
             // do something
             break;
          case 1: // Option 2 pressed
             // do something
             break;
          case 2: // Option 3 pressed
             // do something
             break;
          default: // Cancel pressed (newSelectedIndex == -1)
             break;
       }  
    }
    break;

    ...
    // In the user area, declare a variable to store the last selected drop down menu index:
    int lastSelectedIndex = 0;
```