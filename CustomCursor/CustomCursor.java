package com.mycompany.newmodule;


import voltage.controllers.*;
import voltage.core.*;
import voltage.core.Jack.JackType;
import voltage.sources.*;
import voltage.utility.*;
import voltage.processors.*;
import voltage.effects.*;
import java.awt.*;

//[user-imports]   Add your own imports here
//[/user-imports]


public class MyModule extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public MyModule( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "My Module", ModuleType.ModuleType_Utility, 2.0 );


      textLabel1 = new VoltageLabel( "textLabel1", "textLabel1", this, "Custom Cursor" );
      AddComponent( textLabel1 );
      textLabel1.SetWantsMouseNotifications( false );
      textLabel1.SetPosition( 0, 0 );
      textLabel1.SetSize( 144, 30 );
      textLabel1.SetEditable( false, false );
      textLabel1.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel1.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel1.SetColor( new Color( 0, 0, 0, 255 ) );
      textLabel1.SetBkColor( new Color( 255, 255, 255, 255 ) );
      textLabel1.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel1.SetBorderSize( 1 );
      textLabel1.SetMultiLineEdit( false );
      textLabel1.SetIsNumberEditor( false );
      textLabel1.SetNumberEditorRange( 0, 100 );
      textLabel1.SetNumberEditorInterval( 1 );
      textLabel1.SetNumberEditorUsesMouseWheel( false );
      textLabel1.SetHasCustomTextHoverColor( false );
      textLabel1.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel1.SetFont( "<Sans-Serif>", 14, false, false );

      mouseChangeImage = new VoltageImage( "mouseChangeImage", "mouseChangeImage", this, false );
      AddComponent( mouseChangeImage );
      mouseChangeImage.SetWantsMouseNotifications( true );
      mouseChangeImage.SetPosition( 8, 175 );
      mouseChangeImage.SetSize( 128, 64 );
      mouseChangeImage.SetCurrentImage( "image image.png" );

      textLabel2 = new VoltageLabel( "textLabel2", "textLabel2", this, "Hover over the image below to see the custom cursor:" );
      AddComponent( textLabel2 );
      textLabel2.SetWantsMouseNotifications( false );
      textLabel2.SetPosition( 0, 109 );
      textLabel2.SetSize( 144, 55 );
      textLabel2.SetEditable( false, false );
      textLabel2.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel2.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel2.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel2.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel2.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel2.SetBorderSize( 1 );
      textLabel2.SetMultiLineEdit( false );
      textLabel2.SetIsNumberEditor( false );
      textLabel2.SetNumberEditorRange( 0, 100 );
      textLabel2.SetNumberEditorInterval( 1 );
      textLabel2.SetNumberEditorUsesMouseWheel( false );
      textLabel2.SetHasCustomTextHoverColor( false );
      textLabel2.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel2.SetFont( "<Sans-Serif>", 14, false, false );



      canBeBypassed = false;
      SetSkin( "49d41ec79f1b4bac8fcb03ffdcf73b05" );
   }

   //-------------------------------------------------------------------------------
   //  public void Initialize()

      //  Initialize will get called shortly after your module's constructor runs. You can use it to
      //  do any initialization that the auto-generated code doesn't handle.
   //-------------------------------------------------------------------------------
   @Override
   public void Initialize()
   {
      //[user-Initialize]   Add your own initialization code here



      //[/user-Initialize]
   }


   //-------------------------------------------------------------------------------
   //  public void Destroy()

      //  Destroy will get called just before your module gets deleted. You can use it to perform any
      //  cleanup that's not handled automatically by Java.
   //-------------------------------------------------------------------------------
   @Override
   public void Destroy()
   {
      super.Destroy();
      //[user-Destroy]   Add your own module-getting-deleted code here



      //[/user-Destroy]
   }


   //-------------------------------------------------------------------------------
   //  public boolean Notify( VoltageComponent component, ModuleNotifications notification, double doubleValue, long longValue, int x, int y, Object object )

      //  Notify will get called when various events occur - control values changing, timers firing, etc.
   //-------------------------------------------------------------------------------
   @Override
   public boolean Notify( VoltageComponent component, ModuleNotifications notification, double doubleValue, long longValue, int x, int y, Object object )
   {
      //[user-Notify]   Add your own notification handling code between this line and the notify-close comment
      switch( notification )
      {
         case Knob_Changed:   // doubleValue is the new VoltageKnob value
         {
         }
         break;
      
         case Slider_Changed:   // doubleValue is the new slider value
         {
         }
         break;
      
         case Button_Changed:   // doubleValue is the new button/toggle button value
         {
         }
         break;
      
         case Switch_Changed:   // doubleValue is the new switch value
         {
         }
         break;
      
         case Jack_Connected:   // longValue is the new cable ID
         {
         }
         break;
      
         case Jack_Disconnected:   // All cables have been disconnected from this jack
         {
         }
         break;
      
         case GUI_Update_Timer:   // Called every 50ms (by default) if turned on
         {
         }
         break;
      
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
               // If your cursor is a custom arrow pointing towards the upper left of the image, your hotspot would be 0, 0
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
      
         case Object_MouseLeave:  // called when mouse leaves an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
         {
            // When the cursor moves away from the control with the custom cursor
            if (component == mouseChangeImage)
            {
               MouseCursorTypes newCursor = VoltageModule.MouseCursorTypes.Normal;
            
               // Set the mouse cursor for the module back to normal
               SetMouseCursor(newCursor);
            }
         }
         break;
      
         case Object_LeftButtonDown:   // called when user left-clicks on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
         {
         }
         break;
      
         case Object_LeftButtonUp:   // called when user releases left mouse button on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
         {
         }
         break;
      
         case Object_RightButtonDown:   // called when user releases right mouse button on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
         {
         }
         break;
      
         case Object_RightButtonUp:   // called when user right-clicks on an object that receives mouse notifications
         {
         }
         break;
      
         case Object_LeftButtonDoubleClick: // called when user left-button double-clicks on an object that receives mouse notifications
         {
         }
         break;
      
         // Less common notifications:
      
         case Named_Timer:   // object contains a String with the name of the timer that has fired
         {
         }
         break;
      
         case Canvas_Painting:   // About to paint canvas.  object is a java.awt.Rectangle with painting boundaries
         {
         }
         break;
      
         case Canvas_Painted:   // Canvas painting is complete
         {
         }
         break;
      
         case Control_DragStart:    // A user has started dragging on a control that has been marked as draggable
         {
         }
         break;
      
         case Control_DragOn:       // This control has been dragged over during a drag operation. object contains the dragged object
         {
         }
         break;
      
         case Control_DragOff:      // This control has been dragged over during a drag operation. object contains the dragged object
         {
         }
         break;
      
         case Control_DragEnd:      // A user has ended their drag on a control that has been marked as draggable
         {
         }
         break;
      
         case Label_Changed:        // The text of an editable text control has changed
         {
         }
         break;
      
         case SoundPlayback_Start:   // A sound has begun playback
         {
         }
         break;
      
         case SoundPlayback_End:     // A sound has ended playback
         {
         }
         break;
      
         case Scrollbar_Position:    // longValue is the new scrollbar position
         {
         }
         break;
      
         case PolyVoices_Changed:    // longValue is the new number of poly voices
         {
         }
         break;
      
         case File_Dropped:     // 'object' is a String containing the file path
         {
         }
         break;
      
         case Preset_Loading_Start:   // called when preset loading begins
         {
         }
         break;
      
         case Preset_Loading_Finish:  // called when preset loading finishes
         {
         }
         break;
      
         case Variation_Loading_Start:    // sent when a variation is about to load
         {
         }
         break;
      
         case Variation_Loading_Finish:   // sent when a variation has just finished loading
         {
         }
         break;
      
         case Tempo_Changed:     // doubleValue is the new tempo
         {
         }
         break;
      
         case Randomized:     // called when the module's controls get randomized
         {
         }
         break;
      
         case VariationListChanged:   // sent when a variation gets added, deleted, or renamed, or the variations list gets reordered
         {
         }
         break;
      
         case Key_Press:     // sent when module has keyboard focus and a key is pressed; object is a VoltageKeyPressInfo object
         {
         }
         break;
      
         case Reset:    // sent when the module has been reset to default settings
         {
         }
         break;
      
         case Keyboard_NoteOn:   // sent when a note has been pressed on a VoltageKeyboard object. longValue is the note value ( 0-127 )
         {
         }
         break;
      
         case Keyboard_NoteOff:   // sent when a note has been released on a VoltageKeyboard object. longValue is the note value ( 0-127 )
         {
         }
         break;
      }



      return false;
      //[/user-Notify]
   }


   //-------------------------------------------------------------------------------
   //  public void ProcessSample()

      //  ProcessSample is called once per sample. Usually it's where you read
      //  from input jacks, process audio, and write it to your output jacks.
      //  Since ProcesssSample gets called 48,000 times per second, offload CPU-intensive operations
      //  to other threads when possible and avoid calling native functions.
   //-------------------------------------------------------------------------------
   @Override
   public void ProcessSample()
   {
      //[user-ProcessSample]   Add your own process-sampling code here



      //[/user-ProcessSample]
   }


   //-------------------------------------------------------------------------------
   //  public String GetTooltipText( VoltageComponent component )

      //  Gets called when a tooltip is about to display for a control. Override it if
      //  you want to change what the tooltip displays - if you want a knob to work in logarithmic fashion,
      //  for instance, you can translate the knob's current value to a log-based string and display it here.
   //-------------------------------------------------------------------------------
   @Override
   public String GetTooltipText( VoltageComponent component )
   {
      //[user-GetTooltipText]   Add your own code here



      return super.GetTooltipText( component );
      //[/user-GetTooltipText]
   }


   //-------------------------------------------------------------------------------
   //  public void EditComponentValue( VoltageComponent component, double newValue, String newText )

      //  Gets called after a user clicks on a tooltip and types in a new value for a control. Override this if
      //  you've changed the default tooltip display (translating a linear value to logarithmic, for instance)
      //  in GetTooltipText().
   //-------------------------------------------------------------------------------
   @Override
   public void EditComponentValue( VoltageComponent component, double newValue, String newText )
   {
      //[user-EditComponentValue]   Add your own code here



      //[/user-EditComponentValue]
      super.EditComponentValue( component, newValue, newText );
   }


   //-------------------------------------------------------------------------------
   //  public void OnUndoRedo( String undoType, double newValue, Object optionalObject )

      //  If you've created custom undo events via calls to CreateUndoEvent, you'll need to
      //  process them in this function when they get triggered by undo/redo actions.
   //-------------------------------------------------------------------------------
   @Override
   public void OnUndoRedo( String undoType, double newValue, Object optionalObject )
   {
      //[user-OnUndoRedo]   Add your own code here



      //[/user-OnUndoRedo]
   }


   //-------------------------------------------------------------------------------
   //  public byte[] GetStateInformation()

      //  Gets called when the module's state gets saved, typically when the user saves a preset with
      //  this module in it. Voltage Modular will automatically save the states of knobs, sliders, etc.,
      //  but if you have any custom state information you need to save, return it from this function.
   //-------------------------------------------------------------------------------
   @Override
   public byte[] GetStateInformation()
   {
      //[user-GetStateInformation]   Add your own code here



      return null;
      //[/user-GetStateInformation]
   }


   //-------------------------------------------------------------------------------
   //  public void SetStateInformation(byte[] stateInfo)

      //  Gets called when this module's state is getting restored, typically when a user opens a preset with
      //  this module in it. The stateInfo parameter will contain whatever custom data you stored in GetStateInformation().
   //-------------------------------------------------------------------------------
   @Override
   public void SetStateInformation(byte[] stateInfo)
   {
      //[user-SetStateInformation]   Add your own code here



      //[/user-SetStateInformation]
   }


   //-------------------------------------------------------------------------------
   //  public byte[] GetStateInformationForVariations()

      //  Gets called when a user saves a variation with this module in it.
      //  Voltage Modular will automatically save the states of knobs, sliders, etc.,
      //  but if you have any custom state information you need to save, return it from this function.
   //-------------------------------------------------------------------------------
   @Override
   public byte[] GetStateInformationForVariations()
   {
      //[user-GetStateInformationForVariations]   Add your own code here



      return GetStateInformation();
      //[/user-GetStateInformationForVariations]
   }


   //-------------------------------------------------------------------------------
   //  public void SetStateInformationForVariations(byte[] stateInfo)

      //  Gets called when a user loads a variation with this module in it.
      //  The stateInfo parameter will contain whatever custom data you stored in GetStateInformationForVariations().
   //-------------------------------------------------------------------------------
   @Override
   public void SetStateInformationForVariations(byte[] stateInfo)
   {
      //[user-SetStateInformationForVariations]   Add your own code here
      SetStateInformation(stateInfo);



      //[/user-SetStateInformationForVariations]
   }


   // Auto-generated variables
   private VoltageLabel textLabel2;
   private VoltageImage mouseChangeImage;
   private VoltageLabel textLabel1;


   //[user-code-and-variables]    Add your own variables and functions here
   //[/user-code-and-variables]





}

 