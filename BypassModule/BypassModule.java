package au.com.arbuxmusic.bypassmodule;


import voltage.controllers.*;
import voltage.core.*;
import voltage.core.Jack.JackType;
import voltage.sources.*;
import voltage.utility.*;
import voltage.processors.*;
import voltage.effects.*;
import java.awt.*;

//[user-imports]   Add your own imports here

import au.com.arbuxmusic.corelib.io.*;


//[/user-imports]


public class BypassModule extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public BypassModule( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "Bypass Module", ModuleType.ModuleType_Utility, 2.0 );


      line0 = new VoltageLine( "line0", "line0", this );
      AddComponent( line0 );
      line0.SetWantsMouseNotifications( false );
      line0.SetLineColor( new Color( 255, 255, 20, 123 ) );
      line0.SetLineWidth( (float)2 );
      line0.SetStartPosition( 45, 187 );
      line0.SetEndPosition( 99, 187 );
      line0.SetHasArrow( true );
      line0.SetArrowWidth( (float)8 );
      line0.SetArrowLength( (float)12 );

      moduleTitleLabel = new VoltageLabel( "moduleTitleLabel", "moduleTitleLabel", this, "BypassModule" );
      AddComponent( moduleTitleLabel );
      moduleTitleLabel.SetWantsMouseNotifications( false );
      moduleTitleLabel.SetPosition( 0, 0 );
      moduleTitleLabel.SetSize( 144, 30 );
      moduleTitleLabel.SetEditable( false, false );
      moduleTitleLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      moduleTitleLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      moduleTitleLabel.SetColor( new Color( 0, 0, 0, 255 ) );
      moduleTitleLabel.SetBkColor( new Color( 255, 255, 255, 255 ) );
      moduleTitleLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      moduleTitleLabel.SetBorderSize( 1 );
      moduleTitleLabel.SetMultiLineEdit( false );
      moduleTitleLabel.SetIsNumberEditor( false );
      moduleTitleLabel.SetNumberEditorRange( 0, 100 );
      moduleTitleLabel.SetNumberEditorInterval( 1 );
      moduleTitleLabel.SetNumberEditorUsesMouseWheel( false );
      moduleTitleLabel.SetHasCustomTextHoverColor( false );
      moduleTitleLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      moduleTitleLabel.SetFont( "<Sans-Serif>", 14, true, false );

      volumeKnob = new VoltageKnob( "volumeKnob", "Volume Knob", this, 0.0, 1.0, 0.5 );
      AddComponent( volumeKnob );
      volumeKnob.SetWantsMouseNotifications( false );
      volumeKnob.SetPosition( 59, 97 );
      volumeKnob.SetSize( 27, 27 );
      volumeKnob.SetSkin( "Plastic White" );
      volumeKnob.SetRange( 0.0, 1.0, 0.5, false, 0 );
      volumeKnob.SetKnobParams( 215, 145 );
      volumeKnob.DisplayValueInPercent( false );
      volumeKnob.SetKnobAdjustsRing( true );

      inputJack1 = new VoltageAudioJack( "inputJack1", "inputJack1", this, JackType.JackType_AudioInput );
      AddComponent( inputJack1 );
      inputJack1.SetWantsMouseNotifications( false );
      inputJack1.SetPosition( 9, 169 );
      inputJack1.SetSize( 37, 37 );
      inputJack1.SetSkin( "Jack Straight" );

      outputJack1 = new VoltageAudioJack( "outputJack1", "outputJack1", this, JackType.JackType_AudioOutput );
      AddComponent( outputJack1 );
      outputJack1.SetWantsMouseNotifications( false );
      outputJack1.SetPosition( 98, 169 );
      outputJack1.SetSize( 37, 37 );
      outputJack1.SetSkin( "Jack Straight" );

      processingEnabledLED = new VoltageLED( "processingEnabledLED", "Processing Enabled?", this );
      AddComponent( processingEnabledLED );
      processingEnabledLED.SetWantsMouseNotifications( false );
      processingEnabledLED.SetPosition( 67, 190 );
      processingEnabledLED.SetSize( 11, 11 );
      processingEnabledLED.SetSkin( "Silver Backed Blue" );

      textLabel2 = new VoltageLabel( "textLabel2", "textLabel2", this, "Volume" );
      AddComponent( textLabel2 );
      textLabel2.SetWantsMouseNotifications( false );
      textLabel2.SetPosition( 30, 115 );
      textLabel2.SetSize( 80, 30 );
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



      canBeBypassed = true;
      SetSkin( "f39ff68d39ed477c85e3e0b08a19d799" );
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

      volumeValue.SetValue(volumeKnob.GetValue());

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
            if (component == volumeKnob) {
               volumeValue.SetValue(volumeKnob.GetValue());
            }
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
         }
         break;
      
         case Object_MouseLeave:  // called when mouse leaves an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
         {
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

      // The LED is for demonstration purposes only. 
      // I wouldn't recommend changing UI controls inside the sample processing methods in production code.
      // Set LED (if needed) if samples are being processed.
      if (processingEnabledLED.GetValue() < 1) {
         // Light the LED up
         processingEnabledLED.SetValue(1);
      }
      
      // Process the input to the output via the volume setting
      // From the documentation, the ProcessSample-type methods should be as performant as possible.
      // Thus checking if an output jack is connected should always be checked before any more work is completed.      
      if (outputJack1.IsConnected()) {
         // Get the input value from the input jack. Will be 0 if there is no input jack connected
         double inputValue = inputJack1.GetValue();
         
         // Adjust the volume of the input in preparation for passing it to the output
         double processedValue = inputValue * volumeValue.GetValue();
         
         // Set the output jack to the processed value
         outputJack1.SetValue(processedValue);
      }
      //[/user-ProcessSample]
   }


   //-------------------------------------------------------------------------------
   //  public void ProcessBypassedSample()

      //  ProcessBypassedSample gets called instead of ProcessSample when you've checked the "Can Be Bypassed" box
      //  and a user has bypassed your module. If your module processes data from input jacks and then sends it to
      //  output jack(s), make ProcessBypassedSample just send the data from the input jacks to the output jacks without
      //  processing it.
   //-------------------------------------------------------------------------------
   @Override
   public void ProcessBypassedSample()
   {
      //[user-ProcessBypassedSample]   Add your own code here

      // The LED is for demonstration purposes only. 
      // I wouldn't recommend changing UI controls inside the sample processing methods in production code.
      // Reset LED (if needed) if samples are being bypassed.
      if (processingEnabledLED.GetValue() > 0) {
         // Switch off the LED
         processingEnabledLED.SetValue(0);
      }
      
      // Copy input to output without processing
      // From the documentation, the ProcessSample-type methods should be as performant as possible.
      // Thus checking if an output jack is connected should always be checked before any more work is completed.
      if (outputJack1.IsConnected()) {
         // Get the value from the input jack
         double inputValue = inputJack1.GetValue();
         
         // Set the output to the input value without any processing
         outputJack1.SetValue(inputValue);
      }
      //[/user-ProcessBypassedSample]
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
   private VoltageLED processingEnabledLED;
   private VoltageAudioJack outputJack1;
   private VoltageAudioJack inputJack1;
   private VoltageKnob volumeKnob;
   private VoltageLabel moduleTitleLabel;
   private VoltageLine line0;


   //[user-code-and-variables]    Add your own variables and functions here


   // The setting store for the volume value, as set by the user.
   // Per Cherry Audio recommendation, SmoothValue is used to avoid sudden changes in audio that may cause distortion
   private SmoothValue volumeValue = new SmoothValue();


   //[/user-code-and-variables]
}

 