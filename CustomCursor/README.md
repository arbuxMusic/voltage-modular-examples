# Setting Normal & Custom Cursors

This module shows you how to configure a custom cursor to change on entry / exit of an image. The same technique can be used for any control type that has a "Wants Mouse Notifications" property.

## Setting a standard cursor

Cursors are setup on a VoltageModule level.

To set a standard cursor, call the SetMouseCursor method with one of the  standard MouseCursorTypes. You can find these types in the Designer's Library under Enumerations -> VoltageModule.MouseCursorTypes.

```
    MouseCursorTypes newCursor = VoltageModule.MouseCursorTypes.Busy;
        
    // Set the mouse cursor for the module back to normal
    SetMouseCursor(newCursor);
``` 

## Setting the custom cursor

This section shows how to set a custom cursor. It is also possible to configure the module to specific controls by using the Object_MouseMove and Object_MouseLeave notifications.

Setting a custom cursor requires the SetCustomCursor function, followed by the SetMouseCursor method.

The SetCustomCursor function takes an image resource name along with the offset from the top left of the resource of the cursor's center point.

You will need to create an image for the cursor and add it as an Extra Resource on the module. I tested a transparent background PNG image with the SetCustomCursor method successfully. I also recommend a contrasting border when you draw the customer so that the cursor can be seen on all backgrounds.

Once the SetCustomCursor function returns, the SetMouseCursor method can be called with the  MouseCursorTypes.CustomCursor parameter.

Example java code to set the custom cursor:

```java
case Object_MouseMove:   // called when mouse is over an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
    {
    // The mouseChangeImage image control is setup as "Wants Mouse Notifications"
    // When the mouse moves over the image, we want the custom cursor to show
    if (component == mouseChangeImage)
    {
           // Add a png resource (with transparency) for your cursor to the Extra Resources for the module.
           // Your added resource is then referenced in the cursorResourceName parameter
           // For the cursor design, I recommend a contrasting outline so that the cursor is visible against all backgrounds.
           String cursorResourceName = "CustomCursor.png";
           
           // The cursor's hotspot is the center point for the cursor.
           // In this example, if you load the image, you can see that the center point
           // is at (15px, 15px) x/y.
           // If your cursor is a custom arrow pointing towards the upper right, your hotspot would be 0, 0
           int hotSpotX = 15, hotSpotY = 15;
        
           // Setting a custom cursor is a two step operation:
           // - tell Voltage Modular which custom cursor to use
           // - tell Voltage Modular to show a custom cursor
           // NOTE: Watch for the incorrect enum name when dragging from the Library - it drags in as VoltageModule.MouseCursorTypes.CustomCustor. Note the mispelling in the final word - Custor -> Cursor
           MouseCursorTypes newCursor = VoltageModule.MouseCursorTypes.CustomCursor;
        
           // This method sets custom cursor image to the specified resource name and hotspot
           if (SetCustomCursor(cursorResourceName, hotSpotX, hotSpotY)) {
              // If the custom cursor was set OK, change to show a custom cursor
              // Note that this sets the cursor at the module level, so when the mouse leaves, we'll need to reset the cursor to the standard cursor type. See the Object_MouseLeave for the code to do this.
              SetMouseCursor(newCursor);
           }
        }
    }
    break;
```

## Reverting back to the normal cursor

Once the cursor leaves the image, the Object_MouseLeave event is called.
The cursor should be returned back to normal with the SetMouseCursor method can be called again with the VoltageModule.MouseCursorTypes.Normal parameter.

```java
    case Object_MouseLeave:  // called when mouse leaves an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
    {
        // When the cursor moves away from the control with the custom cursor
        if (component == mouseChangeImage)
        {
           MouseCursorTypes newCursor = VoltageModule.MouseCursorTypes.Normal;
        
           // Set the mouse cursor for the module back to normal
           SetMouseCursor(newCursor);
    }
    break;
```