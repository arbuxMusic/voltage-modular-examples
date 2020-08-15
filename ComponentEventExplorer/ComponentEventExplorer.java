package au.com.arbuxmusic.voltagemodule;


import voltage.controllers.*;
import voltage.core.*;
import voltage.core.Jack.JackType;
import voltage.sources.*;
import voltage.utility.*;
import voltage.processors.*;
import voltage.effects.*;
import java.awt.*;

//[user-imports]   Add your own imports here

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

import au.com.arbuxmusic.corelib.util.arrays.*;
import au.com.arbuxmusic.corelib.midi.*;

//[/user-imports]


public class ComponentEventExplorer extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public ComponentEventExplorer( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "ComponentEventExplorer", ModuleType.ModuleType_Visualizers, 14.4 );


      textLabel5 = new VoltageLabel( "textLabel5", "textLabel5", this, "Component Event Explorer" );
      AddComponent( textLabel5 );
      textLabel5.SetWantsMouseNotifications( false );
      textLabel5.SetPosition( 3, 0 );
      textLabel5.SetSize( 442, 30 );
      textLabel5.SetEditable( false, false );
      textLabel5.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel5.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel5.SetColor( new Color( 0, 0, 0, 255 ) );
      textLabel5.SetBkColor( new Color( 255, 255, 255, 255 ) );
      textLabel5.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel5.SetBorderSize( 1 );
      textLabel5.SetMultiLineEdit( false );
      textLabel5.SetIsNumberEditor( false );
      textLabel5.SetNumberEditorRange( 0, 100 );
      textLabel5.SetNumberEditorInterval( 1 );
      textLabel5.SetNumberEditorUsesMouseWheel( false );
      textLabel5.SetHasCustomTextHoverColor( false );
      textLabel5.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel5.SetFont( "<Sans-Serif>", 14, false, false );

      DONTLOG_logFrame = new VoltageLabel( "DONTLOG_logFrame", "DONTLOG_logFrame", this, "Event Log" );
      AddComponent( DONTLOG_logFrame );
      DONTLOG_logFrame.SetWantsMouseNotifications( false );
      DONTLOG_logFrame.SetPosition( 447, 0 );
      DONTLOG_logFrame.SetSize( 589, 359 );
      DONTLOG_logFrame.SetEditable( false, false );
      DONTLOG_logFrame.SetJustificationFlags( VoltageLabel.Justification.Left );
      DONTLOG_logFrame.SetJustificationFlags( VoltageLabel.Justification.Top );
      DONTLOG_logFrame.SetColor( new Color( 0, 0, 0, 255 ) );
      DONTLOG_logFrame.SetBkColor( new Color( 176, 176, 176, 255 ) );
      DONTLOG_logFrame.SetBorderColor( new Color( 57, 24, 149, 255 ) );
      DONTLOG_logFrame.SetBorderSize( 1 );
      DONTLOG_logFrame.SetMultiLineEdit( false );
      DONTLOG_logFrame.SetIsNumberEditor( false );
      DONTLOG_logFrame.SetNumberEditorRange( 0, 100 );
      DONTLOG_logFrame.SetNumberEditorInterval( 1 );
      DONTLOG_logFrame.SetNumberEditorUsesMouseWheel( false );
      DONTLOG_logFrame.SetHasCustomTextHoverColor( false );
      DONTLOG_logFrame.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      DONTLOG_logFrame.SetFont( "<Sans-Serif>", 14, false, false );

      DONTLOG_logCanvas = new VoltageCanvas( "DONTLOG_logCanvas", "DONTLOG_logCanvas", this, 504, 306 );
      AddComponent( DONTLOG_logCanvas );
      DONTLOG_logCanvas.SetWantsMouseNotifications( false );
      DONTLOG_logCanvas.SetPosition( 455, 18 );
      DONTLOG_logCanvas.SetSize( 504, 306 );

      DONTLOG_logScrollbar = new VoltageScrollbar( "DONTLOG_logScrollbar", "DONTLOG_logScrollbar", this, false, false );
      AddComponent( DONTLOG_logScrollbar );
      DONTLOG_logScrollbar.SetWantsMouseNotifications( false );
      DONTLOG_logScrollbar.SetPosition( 455, 330 );
      DONTLOG_logScrollbar.SetSize( 505, 20 );
      DONTLOG_logScrollbar.SetRange( 0, 800 );
      DONTLOG_logScrollbar.SetThumbSize( 20 );
      DONTLOG_logScrollbar.ScrollTo( 0 );
      DONTLOG_logScrollbar.SetThumbColor( 122, 122, 122, 255 );
      DONTLOG_logScrollbar.SetBackgroundColor( 20, 20, 20, 255 );
      DONTLOG_logScrollbar.SetThumbCornerSize( 4.00 );

      DONTLOG_logPauseButton = new VoltageToggle( "DONTLOG_logPauseButton", "DONTLOG_logPauseButton", this, false, 0 );
      AddComponent( DONTLOG_logPauseButton );
      DONTLOG_logPauseButton.SetWantsMouseNotifications( false );
      DONTLOG_logPauseButton.SetPosition( 962, 325 );
      DONTLOG_logPauseButton.SetSize( 71, 30 );
      DONTLOG_logPauseButton.SetSkin( "Mini Rectangle Green" );
      DONTLOG_logPauseButton.ShowOverlay( true );
      DONTLOG_logPauseButton.SetOverlayText( "Pause Log" );
      DONTLOG_logPauseButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      DONTLOG_logPauseButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_logPauseButton.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_logPauseButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );

      analogMeter1 = new VoltageAnalogVUMeter( "analogMeter1", "analogMeter1", this );
      AddComponent( analogMeter1 );
      analogMeter1.SetWantsMouseNotifications( false );
      analogMeter1.SetPosition( 59, 46 );
      analogMeter1.SetSize( 73, 40 );
      analogMeter1.SetSkin( "Analog Black" );

      button2 = new VoltageButton( "button2", "button2", this );
      AddComponent( button2 );
      button2.SetWantsMouseNotifications( false );
      button2.SetPosition( 129, 230 );
      button2.SetSize( 32, 32 );
      button2.SetSkin( "Large Rounded Square" );
      button2.ShowOverlay( false );
      button2.SetOverlayText( "" );
      button2.SetAutoRepeat( false );

      canvas1 = new VoltageCanvas( "canvas1", "canvas1", this, 125, 82 );
      AddComponent( canvas1 );
      canvas1.SetWantsMouseNotifications( true );
      canvas1.SetPosition( 310, 235 );
      canvas1.SetSize( 125, 82 );

      counter1 = new VoltageDigitalCounter( "counter1", "counter1", this, 2 );
      AddComponent( counter1 );
      counter1.SetWantsMouseNotifications( false );
      counter1.SetPosition( 64, 135 );
      counter1.SetSize( 42, 33 );
      counter1.SetSkin( "Gray" );
      counter1.SetJustificationFlags( VoltageDigitalCounter.Justification.Centered );

      counterUpButton = new VoltageButton( "counterUpButton", "counterUpButton", this );
      AddComponent( counterUpButton );
      counterUpButton.SetWantsMouseNotifications( false );
      counterUpButton.SetPosition( 108, 136 );
      counterUpButton.SetSize( 14, 14 );
      counterUpButton.SetSkin( "Large Rounded Square" );
      counterUpButton.ShowOverlay( true );
      counterUpButton.SetOverlayText( "+" );
      counterUpButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      counterUpButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      counterUpButton.SetOverlayArea( 0, 0, 0, 0 );
      counterUpButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );
      counterUpButton.SetAutoRepeat( true );

      counterDownButton = new VoltageButton( "counterDownButton", "counterDownButton", this );
      AddComponent( counterDownButton );
      counterDownButton.SetWantsMouseNotifications( false );
      counterDownButton.SetPosition( 108, 152 );
      counterDownButton.SetSize( 14, 14 );
      counterDownButton.SetSkin( "Large Rounded Square" );
      counterDownButton.ShowOverlay( true );
      counterDownButton.SetOverlayText( "-" );
      counterDownButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      counterDownButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      counterDownButton.SetOverlayArea( 0, 0, 0, 0 );
      counterDownButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );
      counterDownButton.SetAutoRepeat( true );

      editableText1 = new VoltageLabel( "editableText1", "editableText1", this, "Some Text" );
      AddComponent( editableText1 );
      editableText1.SetWantsMouseNotifications( false );
      editableText1.SetPosition( 70, 171 );
      editableText1.SetSize( 80, 18 );
      editableText1.SetEditable( false, true );
      editableText1.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      editableText1.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      editableText1.SetColor( new Color( 255, 255, 255, 255 ) );
      editableText1.SetBkColor( new Color( 65, 65, 65, 0 ) );
      editableText1.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      editableText1.SetBorderSize( 1 );
      editableText1.SetEditTextColor( new Color( 0, 0, 0 ) );
      editableText1.SetEditBackColor( new Color( 65, 65, 65, 0 ) );
      editableText1.SetEditOutlineColor( new Color( 0, 0, 0, 0 ) );
      editableText1.SetMultiLineEdit( false );
      editableText1.SetIsNumberEditor( false );
      editableText1.SetNumberEditorRange( 0, 100 );
      editableText1.SetNumberEditorInterval( 1 );
      editableText1.SetNumberEditorUsesMouseWheel( false );
      editableText1.SetHasCustomTextHoverColor( false );
      editableText1.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      editableText1.SetFont( "<Sans-Serif>", 14, false, false );

      knob1 = new VoltageKnob( "knob1", "knob1", this, 0.0, 1.0, 0.5 );
      AddComponent( knob1 );
      knob1.SetWantsMouseNotifications( false );
      knob1.SetPosition( 178, 231 );
      knob1.SetSize( 27, 27 );
      knob1.SetSkin( "Plastic White" );
      knob1.SetRange( 0.0, 1.0, 0.5, false, 0 );
      knob1.SetKnobParams( 215, 145 );
      knob1.DisplayValueInPercent( false );
      knob1.SetKnobAdjustsRing( true );

      image1 = new VoltageImage( "image1", "image1", this, false );
      AddComponent( image1 );
      image1.SetWantsMouseNotifications( false );
      image1.SetPosition( 340, 100 );
      image1.SetSize( 93, 46 );
      image1.SetCurrentImage( "image image.png" );

      LED1 = new VoltageLED( "LED1", "LED1", this );
      AddComponent( LED1 );
      LED1.SetWantsMouseNotifications( false );
      LED1.SetPosition( 177, 135 );
      LED1.SetSize( 17, 17 );
      LED1.SetSkin( "Red" );

      LED2 = new VoltageLED( "LED2", "LED2", this );
      AddComponent( LED2 );
      LED2.SetWantsMouseNotifications( false );
      LED2.SetPosition( 177, 154 );
      LED2.SetSize( 17, 17 );
      LED2.SetSkin( "Red" );

      toggleLEDButton = new VoltageButton( "toggleLEDButton", "toggleLEDButton", this );
      AddComponent( toggleLEDButton );
      toggleLEDButton.SetWantsMouseNotifications( false );
      toggleLEDButton.SetPosition( 197, 133 );
      toggleLEDButton.SetSize( 39, 39 );
      toggleLEDButton.SetSkin( "Large Rounded Square" );
      toggleLEDButton.ShowOverlay( false );
      toggleLEDButton.SetOverlayText( "" );
      toggleLEDButton.SetAutoRepeat( false );

      midiInputJack1 = new VoltageMidiJack( "midiInputJack1", "midiInputJack1", this, JackType.JackType_MidiInput );
      AddComponent( midiInputJack1 );
      midiInputJack1.SetWantsMouseNotifications( false );
      midiInputJack1.SetPosition( 10, 293 );
      midiInputJack1.SetSize( 57, 57 );
      midiInputJack1.SetSkin( "MIDI Jack" );

      midiOutputJack1 = new VoltageMidiJack( "midiOutputJack1", "midiOutputJack1", this, JackType.JackType_MidiOutput );
      AddComponent( midiOutputJack1 );
      midiOutputJack1.SetWantsMouseNotifications( false );
      midiOutputJack1.SetPosition( 134, 293 );
      midiOutputJack1.SetSize( 57, 57 );
      midiOutputJack1.SetSkin( "MIDI Jack" );

      inputJack1 = new VoltageAudioJack( "inputJack1", "inputJack1", this, JackType.JackType_AudioInput );
      AddComponent( inputJack1 );
      inputJack1.SetWantsMouseNotifications( false );
      inputJack1.SetPosition( 73, 284 );
      inputJack1.SetSize( 37, 37 );
      inputJack1.SetSkin( "Jack Straight" );

      outputJack1 = new VoltageAudioJack( "outputJack1", "outputJack1", this, JackType.JackType_AudioOutput );
      AddComponent( outputJack1 );
      outputJack1.SetWantsMouseNotifications( false );
      outputJack1.SetPosition( 196, 287 );
      outputJack1.SetSize( 37, 37 );
      outputJack1.SetSkin( "Jack Straight" );

      polyInputJack1 = new VoltagePolyJack( "polyInputJack1", "polyInputJack1", this, JackType.JackType_PolyInput );
      AddComponent( polyInputJack1 );
      polyInputJack1.SetWantsMouseNotifications( false );
      polyInputJack1.SetPosition( 80, 325 );
      polyInputJack1.SetSize( 25, 25 );
      polyInputJack1.SetSkin( "Poly Jack Straight" );

      polyOutputJack1 = new VoltagePolyJack( "polyOutputJack1", "polyOutputJack1", this, JackType.JackType_PolyOutput );
      AddComponent( polyOutputJack1 );
      polyOutputJack1.SetWantsMouseNotifications( false );
      polyOutputJack1.SetPosition( 205, 325 );
      polyOutputJack1.SetSize( 25, 25 );
      polyOutputJack1.SetSkin( "Poly Jack Straight" );

      scrollbar2 = new VoltageScrollbar( "scrollbar2", "scrollbar2", this, false, false );
      AddComponent( scrollbar2 );
      scrollbar2.SetWantsMouseNotifications( false );
      scrollbar2.SetPosition( 64, 114 );
      scrollbar2.SetSize( 129, 12 );
      scrollbar2.SetRange( 0, 100 );
      scrollbar2.SetThumbSize( 20 );
      scrollbar2.ScrollTo( 0 );
      scrollbar2.SetThumbColor( 122, 122, 122, 255 );
      scrollbar2.SetBackgroundColor( 20, 20, 20, 255 );
      scrollbar2.SetThumbCornerSize( 4.00 );

      slider1 = new VoltageSlider( "slider1", "slider1", this, false, 0.0, 1.0, 0.5, 0 );
      AddComponent( slider1 );
      slider1.SetWantsMouseNotifications( false );
      slider1.SetPosition( 65, 95 );
      slider1.SetSize( 129, 15 );
      slider1.SetSkin( "Straight Black Plain Horizontal" );
      slider1.DisplayValueInPercent( false );

      textLabel25 = new VoltageLabel( "textLabel25", "textLabel25", this, "Capture:" );
      AddComponent( textLabel25 );
      textLabel25.SetWantsMouseNotifications( false );
      textLabel25.SetPosition( 963, 24 );
      textLabel25.SetSize( 69, 202 );
      textLabel25.SetEditable( false, false );
      textLabel25.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel25.SetJustificationFlags( VoltageLabel.Justification.Top );
      textLabel25.SetColor( new Color( 0, 0, 0, 255 ) );
      textLabel25.SetBkColor( new Color( 48, 48, 48, 62 ) );
      textLabel25.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel25.SetBorderSize( 1 );
      textLabel25.SetMultiLineEdit( false );
      textLabel25.SetIsNumberEditor( false );
      textLabel25.SetNumberEditorRange( 0, 100 );
      textLabel25.SetNumberEditorInterval( 1 );
      textLabel25.SetNumberEditorUsesMouseWheel( false );
      textLabel25.SetHasCustomTextHoverColor( false );
      textLabel25.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel25.SetFont( "<Sans-Serif>", 14, false, false );

      switch1 = new VoltageSwitch( "switch1", "switch1", this, 0 );
      AddComponent( switch1 );
      switch1.SetWantsMouseNotifications( false );
      switch1.SetPosition( 203, 226 );
      switch1.SetSize( 35, 35 );
      switch1.SetSkin( "3-State Silver" );

      toggleButton2 = new VoltageToggle( "toggleButton2", "toggleButton2", this, false, 1 );
      AddComponent( toggleButton2 );
      toggleButton2.SetWantsMouseNotifications( false );
      toggleButton2.SetPosition( 177, 191 );
      toggleButton2.SetSize( 31, 31 );
      toggleButton2.SetSkin( "Blue Square" );
      toggleButton2.ShowOverlay( false );
      toggleButton2.SetOverlayText( "" );

      toggleButton3 = new VoltageToggle( "toggleButton3", "toggleButton3", this, false, 1 );
      AddComponent( toggleButton3 );
      toggleButton3.SetWantsMouseNotifications( false );
      toggleButton3.SetPosition( 205, 191 );
      toggleButton3.SetSize( 31, 31 );
      toggleButton3.SetSkin( "Blue Square" );
      toggleButton3.ShowOverlay( false );
      toggleButton3.SetOverlayText( "" );

      toggleButton4 = new VoltageToggle( "toggleButton4", "toggleButton4", this, false, 0 );
      AddComponent( toggleButton4 );
      toggleButton4.SetWantsMouseNotifications( false );
      toggleButton4.SetPosition( 130, 191 );
      toggleButton4.SetSize( 31, 31 );
      toggleButton4.SetSkin( "Blue Square" );
      toggleButton4.ShowOverlay( false );
      toggleButton4.SetOverlayText( "" );

      meter1 = new VoltageVUMeter( "meter1", "meter1", this );
      AddComponent( meter1 );
      meter1.SetWantsMouseNotifications( false );
      meter1.SetPosition( 152, 49 );
      meter1.SetSize( 15, 41 );
      meter1.SetSkin( "Small Meter" );
      meter1.SetLinearMode( false );

      vuMeterInput = new VoltageAudioJack( "vuMeterInput", "vuMeterInput", this, JackType.JackType_AudioInput );
      AddComponent( vuMeterInput );
      vuMeterInput.SetWantsMouseNotifications( false );
      vuMeterInput.SetPosition( 198, 49 );
      vuMeterInput.SetSize( 37, 37 );
      vuMeterInput.SetSkin( "Jack Straight" );

      DONTLOG_showToolTipTextEventsToggle = new VoltageToggle( "DONTLOG_showToolTipTextEventsToggle", "DONTLOG_showToolTipTextEventsToggle", this, true, 0 );
      AddComponent( DONTLOG_showToolTipTextEventsToggle );
      DONTLOG_showToolTipTextEventsToggle.SetWantsMouseNotifications( false );
      DONTLOG_showToolTipTextEventsToggle.SetPosition( 965, 49 );
      DONTLOG_showToolTipTextEventsToggle.SetSize( 65, 31 );
      DONTLOG_showToolTipTextEventsToggle.SetSkin( "Blue Square" );
      DONTLOG_showToolTipTextEventsToggle.ShowOverlay( true );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayText( "Tooltip Events" );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayTextFont( "<Sans-Serif>", 11, false, false );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayTextJustification( VoltageButton.Justification.Centered );

      textLabel3 = new VoltageLabel( "textLabel3", "textLabel3", this, "The Canvas above will draw a blue rectangle around the update area" );
      AddComponent( textLabel3 );
      textLabel3.SetWantsMouseNotifications( false );
      textLabel3.SetPosition( 305, 316 );
      textLabel3.SetSize( 135, 34 );
      textLabel3.SetEditable( false, false );
      textLabel3.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel3.SetJustificationFlags( VoltageLabel.Justification.Bottom );
      textLabel3.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel3.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel3.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel3.SetBorderSize( 1 );
      textLabel3.SetMultiLineEdit( true );
      textLabel3.SetIsNumberEditor( false );
      textLabel3.SetNumberEditorRange( 0, 100 );
      textLabel3.SetNumberEditorInterval( 1 );
      textLabel3.SetNumberEditorUsesMouseWheel( false );
      textLabel3.SetHasCustomTextHoverColor( false );
      textLabel3.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel3.SetFont( "<Sans-Serif>", 11, false, false );

      DONTLOG_clearLogButton = new VoltageButton( "DONTLOG_clearLogButton", "DONTLOG_clearLogButton", this );
      AddComponent( DONTLOG_clearLogButton );
      DONTLOG_clearLogButton.SetWantsMouseNotifications( false );
      DONTLOG_clearLogButton.SetPosition( 964, 259 );
      DONTLOG_clearLogButton.SetSize( 69, 31 );
      DONTLOG_clearLogButton.SetSkin( "Red Square" );
      DONTLOG_clearLogButton.ShowOverlay( true );
      DONTLOG_clearLogButton.SetOverlayText( "Clear Log" );
      DONTLOG_clearLogButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      DONTLOG_clearLogButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_clearLogButton.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_clearLogButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );
      DONTLOG_clearLogButton.SetAutoRepeat( false );

      animation1 = new VoltageImage( "animation1", "animation1", this, true );
      AddComponent( animation1 );
      animation1.SetWantsMouseNotifications( false );
      animation1.SetPosition( 314, 158 );
      animation1.SetSize( 120, 67 );
      animation1.SetCurrentImage( "BouncingBall.0001.png" );
      animation1.SetAnimationSpeed( 300, true );
      animation1.AddNewImage( "BouncingBall.0002.png" );
      animation1.AddNewImage( "BouncingBall.0003.png" );
      animation1.AddNewImage( "BouncingBall.0004.png" );
      animation1.AddNewImage( "BouncingBall.0005.png" );
      animation1.AddNewImage( "BouncingBall.0006.png" );
      animation1.AddNewImage( "BouncingBall.0007.png" );
      animation1.AddNewImage( "BouncingBall.0008.png" );
      animation1.AddNewImage( "BouncingBall.0009.png" );
      animation1.AddNewImage( "BouncingBall.0010.png" );
      animation1.AddNewImage( "BouncingBall.0011.png" );
      animation1.AddNewImage( "BouncingBall.0012.png" );
      animation1.AddNewImage( "BouncingBall.0013.png" );
      animation1.AddNewImage( "BouncingBall.0014.png" );
      animation1.AddNewImage( "BouncingBall.0015.png" );
      animation1.AddNewImage( "BouncingBall.0016.png" );
      animation1.AddNewImage( "BouncingBall.0017.png" );
      animation1.AddNewImage( "BouncingBall.0018.png" );
      animation1.AddNewImage( "BouncingBall.0019.png" );
      animation1.AddNewImage( "BouncingBall.0020.png" );
      animation1.AddNewImage( "BouncingBall.0021.png" );
      animation1.AddNewImage( "BouncingBall.0022.png" );
      animation1.AddNewImage( "BouncingBall.0023.png" );
      animation1.AddNewImage( "BouncingBall.0024.png" );
      animation1.AddNewImage( "BouncingBall.0025.png" );
      animation1.AddNewImage( "BouncingBall.0026.png" );
      animation1.AddNewImage( "BouncingBall.0027.png" );
      animation1.AddNewImage( "BouncingBall.0028.png" );
      animation1.AddNewImage( "BouncingBall.0029.png" );
      animation1.AddNewImage( "BouncingBall.0030.png" );
      animation1.AddNewImage( "BouncingBall.0031.png" );
      animation1.AddNewImage( "BouncingBall.0032.png" );
      animation1.AddNewImage( "BouncingBall.0033.png" );
      animation1.AddNewImage( "BouncingBall.0034.png" );
      animation1.AddNewImage( "BouncingBall.0035.png" );
      animation1.AddNewImage( "BouncingBall.0036.png" );
      animation1.StartAnimation( VoltageImage.AnimationMode.Forward, true, 300, 0 );

      textLabel4 = new VoltageLabel( "textLabel4", "textLabel4", this, "Line:" );
      AddComponent( textLabel4 );
      textLabel4.SetWantsMouseNotifications( false );
      textLabel4.SetPosition( 252, 29 );
      textLabel4.SetSize( 52, 30 );
      textLabel4.SetEditable( false, false );
      textLabel4.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel4.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel4.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel4.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel4.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel4.SetBorderSize( 1 );
      textLabel4.SetMultiLineEdit( false );
      textLabel4.SetIsNumberEditor( false );
      textLabel4.SetNumberEditorRange( 0, 100 );
      textLabel4.SetNumberEditorInterval( 1 );
      textLabel4.SetNumberEditorUsesMouseWheel( false );
      textLabel4.SetHasCustomTextHoverColor( false );
      textLabel4.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel4.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel6 = new VoltageLabel( "textLabel6", "textLabel6", this, "Rectangle / Ellipse:" );
      AddComponent( textLabel6 );
      textLabel6.SetWantsMouseNotifications( false );
      textLabel6.SetPosition( 252, 61 );
      textLabel6.SetSize( 61, 30 );
      textLabel6.SetEditable( false, false );
      textLabel6.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel6.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel6.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel6.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel6.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel6.SetBorderSize( 1 );
      textLabel6.SetMultiLineEdit( false );
      textLabel6.SetIsNumberEditor( false );
      textLabel6.SetNumberEditorRange( 0, 100 );
      textLabel6.SetNumberEditorInterval( 1 );
      textLabel6.SetNumberEditorUsesMouseWheel( false );
      textLabel6.SetHasCustomTextHoverColor( false );
      textLabel6.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel6.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel7 = new VoltageLabel( "textLabel7", "textLabel7", this, "Image:" );
      AddComponent( textLabel7 );
      textLabel7.SetWantsMouseNotifications( false );
      textLabel7.SetPosition( 252, 104 );
      textLabel7.SetSize( 52, 30 );
      textLabel7.SetEditable( false, false );
      textLabel7.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel7.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel7.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel7.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel7.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel7.SetBorderSize( 1 );
      textLabel7.SetMultiLineEdit( false );
      textLabel7.SetIsNumberEditor( false );
      textLabel7.SetNumberEditorRange( 0, 100 );
      textLabel7.SetNumberEditorInterval( 1 );
      textLabel7.SetNumberEditorUsesMouseWheel( false );
      textLabel7.SetHasCustomTextHoverColor( false );
      textLabel7.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel7.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel8 = new VoltageLabel( "textLabel8", "textLabel8", this, "Animation" );
      AddComponent( textLabel8 );
      textLabel8.SetWantsMouseNotifications( false );
      textLabel8.SetPosition( 252, 175 );
      textLabel8.SetSize( 52, 30 );
      textLabel8.SetEditable( false, false );
      textLabel8.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel8.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel8.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel8.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel8.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel8.SetBorderSize( 1 );
      textLabel8.SetMultiLineEdit( false );
      textLabel8.SetIsNumberEditor( false );
      textLabel8.SetNumberEditorRange( 0, 100 );
      textLabel8.SetNumberEditorInterval( 1 );
      textLabel8.SetNumberEditorUsesMouseWheel( false );
      textLabel8.SetHasCustomTextHoverColor( false );
      textLabel8.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel8.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel9 = new VoltageLabel( "textLabel9", "textLabel9", this, "Canvas:" );
      AddComponent( textLabel9 );
      textLabel9.SetWantsMouseNotifications( false );
      textLabel9.SetPosition( 252, 259 );
      textLabel9.SetSize( 52, 30 );
      textLabel9.SetEditable( false, false );
      textLabel9.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel9.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel9.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel9.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel9.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel9.SetBorderSize( 1 );
      textLabel9.SetMultiLineEdit( false );
      textLabel9.SetIsNumberEditor( false );
      textLabel9.SetNumberEditorRange( 0, 100 );
      textLabel9.SetNumberEditorInterval( 1 );
      textLabel9.SetNumberEditorUsesMouseWheel( false );
      textLabel9.SetHasCustomTextHoverColor( false );
      textLabel9.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel9.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel10 = new VoltageLabel( "textLabel10", "textLabel10", this, "<==" );
      AddComponent( textLabel10 );
      textLabel10.SetWantsMouseNotifications( false );
      textLabel10.SetPosition( 172, 60 );
      textLabel10.SetSize( 26, 30 );
      textLabel10.SetEditable( false, false );
      textLabel10.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel10.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel10.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel10.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel10.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel10.SetBorderSize( 1 );
      textLabel10.SetMultiLineEdit( false );
      textLabel10.SetIsNumberEditor( false );
      textLabel10.SetNumberEditorRange( 0, 100 );
      textLabel10.SetNumberEditorInterval( 1 );
      textLabel10.SetNumberEditorUsesMouseWheel( false );
      textLabel10.SetHasCustomTextHoverColor( false );
      textLabel10.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel10.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel21 = new VoltageLabel( "textLabel21", "textLabel21", this, "==>" );
      AddComponent( textLabel21 );
      textLabel21.SetWantsMouseNotifications( false );
      textLabel21.SetPosition( 109, 305 );
      textLabel21.SetSize( 26, 30 );
      textLabel21.SetEditable( false, false );
      textLabel21.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel21.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel21.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel21.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel21.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel21.SetBorderSize( 1 );
      textLabel21.SetMultiLineEdit( false );
      textLabel21.SetIsNumberEditor( false );
      textLabel21.SetNumberEditorRange( 0, 100 );
      textLabel21.SetNumberEditorInterval( 1 );
      textLabel21.SetNumberEditorUsesMouseWheel( false );
      textLabel21.SetHasCustomTextHoverColor( false );
      textLabel21.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel21.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel11 = new VoltageLabel( "textLabel11", "textLabel11", this, "VUs:" );
      AddComponent( textLabel11 );
      textLabel11.SetWantsMouseNotifications( false );
      textLabel11.SetPosition( 8, 51 );
      textLabel11.SetSize( 52, 30 );
      textLabel11.SetEditable( false, false );
      textLabel11.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel11.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel11.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel11.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel11.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel11.SetBorderSize( 1 );
      textLabel11.SetMultiLineEdit( false );
      textLabel11.SetIsNumberEditor( false );
      textLabel11.SetNumberEditorRange( 0, 100 );
      textLabel11.SetNumberEditorInterval( 1 );
      textLabel11.SetNumberEditorUsesMouseWheel( false );
      textLabel11.SetHasCustomTextHoverColor( false );
      textLabel11.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel11.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel12 = new VoltageLabel( "textLabel12", "textLabel12", this, "Slider:" );
      AddComponent( textLabel12 );
      textLabel12.SetWantsMouseNotifications( false );
      textLabel12.SetPosition( 8, 88 );
      textLabel12.SetSize( 52, 30 );
      textLabel12.SetEditable( false, false );
      textLabel12.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel12.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel12.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel12.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel12.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel12.SetBorderSize( 1 );
      textLabel12.SetMultiLineEdit( false );
      textLabel12.SetIsNumberEditor( false );
      textLabel12.SetNumberEditorRange( 0, 100 );
      textLabel12.SetNumberEditorInterval( 1 );
      textLabel12.SetNumberEditorUsesMouseWheel( false );
      textLabel12.SetHasCustomTextHoverColor( false );
      textLabel12.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel12.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel13 = new VoltageLabel( "textLabel13", "textLabel13", this, "Scroll Bar:" );
      AddComponent( textLabel13 );
      textLabel13.SetWantsMouseNotifications( false );
      textLabel13.SetPosition( 8, 104 );
      textLabel13.SetSize( 52, 30 );
      textLabel13.SetEditable( false, false );
      textLabel13.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel13.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel13.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel13.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel13.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel13.SetBorderSize( 1 );
      textLabel13.SetMultiLineEdit( false );
      textLabel13.SetIsNumberEditor( false );
      textLabel13.SetNumberEditorRange( 0, 100 );
      textLabel13.SetNumberEditorInterval( 1 );
      textLabel13.SetNumberEditorUsesMouseWheel( false );
      textLabel13.SetHasCustomTextHoverColor( false );
      textLabel13.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel13.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel14 = new VoltageLabel( "textLabel14", "textLabel14", this, "Digital Counter:" );
      AddComponent( textLabel14 );
      textLabel14.SetWantsMouseNotifications( false );
      textLabel14.SetPosition( 8, 137 );
      textLabel14.SetSize( 52, 30 );
      textLabel14.SetEditable( false, false );
      textLabel14.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel14.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel14.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel14.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel14.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel14.SetBorderSize( 1 );
      textLabel14.SetMultiLineEdit( false );
      textLabel14.SetIsNumberEditor( false );
      textLabel14.SetNumberEditorRange( 0, 100 );
      textLabel14.SetNumberEditorInterval( 1 );
      textLabel14.SetNumberEditorUsesMouseWheel( false );
      textLabel14.SetHasCustomTextHoverColor( false );
      textLabel14.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel14.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel15 = new VoltageLabel( "textLabel15", "textLabel15", this, "EditableText:" );
      AddComponent( textLabel15 );
      textLabel15.SetWantsMouseNotifications( false );
      textLabel15.SetPosition( 8, 166 );
      textLabel15.SetSize( 63, 30 );
      textLabel15.SetEditable( false, false );
      textLabel15.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel15.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel15.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel15.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel15.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel15.SetBorderSize( 1 );
      textLabel15.SetMultiLineEdit( false );
      textLabel15.SetIsNumberEditor( false );
      textLabel15.SetNumberEditorRange( 0, 100 );
      textLabel15.SetNumberEditorInterval( 1 );
      textLabel15.SetNumberEditorUsesMouseWheel( false );
      textLabel15.SetHasCustomTextHoverColor( false );
      textLabel15.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel15.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel16 = new VoltageLabel( "textLabel16", "textLabel16", this, "Ungrp/Grp Toggle:" );
      AddComponent( textLabel16 );
      textLabel16.SetWantsMouseNotifications( false );
      textLabel16.SetPosition( 8, 194 );
      textLabel16.SetSize( 83, 30 );
      textLabel16.SetEditable( false, false );
      textLabel16.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel16.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel16.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel16.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel16.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel16.SetBorderSize( 1 );
      textLabel16.SetMultiLineEdit( false );
      textLabel16.SetIsNumberEditor( false );
      textLabel16.SetNumberEditorRange( 0, 100 );
      textLabel16.SetNumberEditorInterval( 1 );
      textLabel16.SetNumberEditorUsesMouseWheel( false );
      textLabel16.SetHasCustomTextHoverColor( false );
      textLabel16.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel16.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel17 = new VoltageLabel( "textLabel17", "textLabel17", this, "LED:" );
      AddComponent( textLabel17 );
      textLabel17.SetWantsMouseNotifications( false );
      textLabel17.SetPosition( 143, 138 );
      textLabel17.SetSize( 38, 30 );
      textLabel17.SetEditable( false, false );
      textLabel17.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel17.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel17.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel17.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel17.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel17.SetBorderSize( 1 );
      textLabel17.SetMultiLineEdit( false );
      textLabel17.SetIsNumberEditor( false );
      textLabel17.SetNumberEditorRange( 0, 100 );
      textLabel17.SetNumberEditorInterval( 1 );
      textLabel17.SetNumberEditorUsesMouseWheel( false );
      textLabel17.SetHasCustomTextHoverColor( false );
      textLabel17.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel17.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel18 = new VoltageLabel( "textLabel18", "textLabel18", this, "Button/Knob/Switch:" );
      AddComponent( textLabel18 );
      textLabel18.SetWantsMouseNotifications( false );
      textLabel18.SetPosition( 8, 230 );
      textLabel18.SetSize( 98, 30 );
      textLabel18.SetEditable( false, false );
      textLabel18.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel18.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel18.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel18.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel18.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel18.SetBorderSize( 1 );
      textLabel18.SetMultiLineEdit( false );
      textLabel18.SetIsNumberEditor( false );
      textLabel18.SetNumberEditorRange( 0, 100 );
      textLabel18.SetNumberEditorInterval( 1 );
      textLabel18.SetNumberEditorUsesMouseWheel( false );
      textLabel18.SetHasCustomTextHoverColor( false );
      textLabel18.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel18.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel19 = new VoltageLabel( "textLabel19", "textLabel19", this, "Inputs:" );
      AddComponent( textLabel19 );
      textLabel19.SetWantsMouseNotifications( false );
      textLabel19.SetPosition( 8, 268 );
      textLabel19.SetSize( 43, 30 );
      textLabel19.SetEditable( false, false );
      textLabel19.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel19.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel19.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel19.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel19.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel19.SetBorderSize( 1 );
      textLabel19.SetMultiLineEdit( false );
      textLabel19.SetIsNumberEditor( false );
      textLabel19.SetNumberEditorRange( 0, 100 );
      textLabel19.SetNumberEditorInterval( 1 );
      textLabel19.SetNumberEditorUsesMouseWheel( false );
      textLabel19.SetHasCustomTextHoverColor( false );
      textLabel19.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel19.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel20 = new VoltageLabel( "textLabel20", "textLabel20", this, "Outputs:" );
      AddComponent( textLabel20 );
      textLabel20.SetWantsMouseNotifications( false );
      textLabel20.SetPosition( 140, 268 );
      textLabel20.SetSize( 57, 30 );
      textLabel20.SetEditable( false, false );
      textLabel20.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel20.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel20.SetColor( new Color( 255, 255, 255, 255 ) );
      textLabel20.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel20.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel20.SetBorderSize( 1 );
      textLabel20.SetMultiLineEdit( false );
      textLabel20.SetIsNumberEditor( false );
      textLabel20.SetNumberEditorRange( 0, 100 );
      textLabel20.SetNumberEditorInterval( 1 );
      textLabel20.SetNumberEditorUsesMouseWheel( false );
      textLabel20.SetHasCustomTextHoverColor( false );
      textLabel20.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel20.SetFont( "<Sans-Serif>", 14, false, false );

      DONTLOG_INTENSIVE = new VoltageLabel( "DONTLOG_INTENSIVE", "DONTLOG_INTENSIVE", this, "!! INTENSIVE !!" );
      AddComponent( DONTLOG_INTENSIVE );
      DONTLOG_INTENSIVE.SetWantsMouseNotifications( false );
      DONTLOG_INTENSIVE.SetPosition( 964, 130 );
      DONTLOG_INTENSIVE.SetSize( 69, 94 );
      DONTLOG_INTENSIVE.SetEditable( false, false );
      DONTLOG_INTENSIVE.SetJustificationFlags( VoltageLabel.Justification.Left );
      DONTLOG_INTENSIVE.SetJustificationFlags( VoltageLabel.Justification.Top );
      DONTLOG_INTENSIVE.SetColor( new Color( 0, 0, 0, 255 ) );
      DONTLOG_INTENSIVE.SetBkColor( new Color( 255, 0, 0, 62 ) );
      DONTLOG_INTENSIVE.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      DONTLOG_INTENSIVE.SetBorderSize( 1 );
      DONTLOG_INTENSIVE.SetMultiLineEdit( false );
      DONTLOG_INTENSIVE.SetIsNumberEditor( false );
      DONTLOG_INTENSIVE.SetNumberEditorRange( 0, 100 );
      DONTLOG_INTENSIVE.SetNumberEditorInterval( 1 );
      DONTLOG_INTENSIVE.SetNumberEditorUsesMouseWheel( false );
      DONTLOG_INTENSIVE.SetHasCustomTextHoverColor( false );
      DONTLOG_INTENSIVE.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      DONTLOG_INTENSIVE.SetFont( "<Sans-Serif>", 14, false, false );

      DONTLOG_showMonoSamples = new VoltageToggle( "DONTLOG_showMonoSamples", "DONTLOG_showMonoSamples", this, false, 0 );
      AddComponent( DONTLOG_showMonoSamples );
      DONTLOG_showMonoSamples.SetWantsMouseNotifications( false );
      DONTLOG_showMonoSamples.SetPosition( 966, 155 );
      DONTLOG_showMonoSamples.SetSize( 65, 31 );
      DONTLOG_showMonoSamples.SetSkin( "Blue Square" );
      DONTLOG_showMonoSamples.ShowOverlay( true );
      DONTLOG_showMonoSamples.SetOverlayText( "Mono In" );
      DONTLOG_showMonoSamples.SetOverlayTextFont( "<Sans-Serif>", 11, false, false );
      DONTLOG_showMonoSamples.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_showMonoSamples.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_showMonoSamples.SetOverlayTextJustification( VoltageButton.Justification.Centered );

      DONTLOG_showPolySamples_1 = new VoltageToggle( "DONTLOG_showPolySamples_1", "DONTLOG_showPolySamples_1", this, false, 0 );
      AddComponent( DONTLOG_showPolySamples_1 );
      DONTLOG_showPolySamples_1.SetWantsMouseNotifications( false );
      DONTLOG_showPolySamples_1.SetPosition( 966, 190 );
      DONTLOG_showPolySamples_1.SetSize( 65, 31 );
      DONTLOG_showPolySamples_1.SetSkin( "Blue Square" );
      DONTLOG_showPolySamples_1.ShowOverlay( true );
      DONTLOG_showPolySamples_1.SetOverlayText( "Poly In" );
      DONTLOG_showPolySamples_1.SetOverlayTextFont( "<Sans-Serif>", 11, false, false );
      DONTLOG_showPolySamples_1.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_showPolySamples_1.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_showPolySamples_1.SetOverlayTextJustification( VoltageButton.Justification.Centered );

      DONTLOG_showMidiInMessages = new VoltageToggle( "DONTLOG_showMidiInMessages", "DONTLOG_showMidiInMessages", this, true, 0 );
      AddComponent( DONTLOG_showMidiInMessages );
      DONTLOG_showMidiInMessages.SetWantsMouseNotifications( false );
      DONTLOG_showMidiInMessages.SetPosition( 965, 87 );
      DONTLOG_showMidiInMessages.SetSize( 65, 31 );
      DONTLOG_showMidiInMessages.SetSkin( "Blue Square" );
      DONTLOG_showMidiInMessages.ShowOverlay( true );
      DONTLOG_showMidiInMessages.SetOverlayText( "Midi In" );
      DONTLOG_showMidiInMessages.SetOverlayTextFont( "<Sans-Serif>", 11, false, false );
      DONTLOG_showMidiInMessages.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_showMidiInMessages.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_showMidiInMessages.SetOverlayTextJustification( VoltageButton.Justification.Centered );



      canBeBypassed = false;
      SetSkin( "d9196ecc606942d18dc80bc2be9ce4aa" );
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

      LogToBuffer("Inititalize");
      
      StartNamedTimer(displayTimerName, 50);

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

      LogToBuffer("Destroy");


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
      
      // This code logs out to the buffer with the relevant Notify event information
      // This part processes the information to be logged
      String componentName;
      if (component != null)
         componentName = component.componentName;
      else
         componentName = "(null)";
         
      String objectName;
      if (object != null)
         objectName = object.toString();
      else
         objectName = "(null)";
      
      // Any compnents whose name starts with "DONTLOG_" should be ignored. These are generally controls that are used to control the event log/display, but aren't included so that the debug log doesn't fill up.
      // if the component is one that should be logged out, then send it to the log
      if (!(objectName.startsWith("DONTLOG_") ||
            componentName.startsWith("DONTLOG_")))
      {
         String objectText = "" + object;
         if (object instanceof VoltageMouseKeyFlags) {
            VoltageMouseKeyFlags flags = (VoltageMouseKeyFlags)object;
            objectText = "(VoltageMouseKeyFlags) : { " +
               (flags.IsShiftKeyDown() ? "IsShiftKeyDown " : "") +
               (flags.IsCtrlKeyDown() ? "IsCtrlKeyDown " : "") +
               (flags.IsAltKeyDown() ? "IsAltKeyDown " : "") +
               (flags.IsCommandDown() ? "IsCommandDown " : "") +
               (flags.IsLeftButtonDown() ? "IsLeftButtonDown " : "") +
               (flags.IsRightButtonDown() ? "IsRightButtonDown " : "") +
               (flags.IsMiddleButtonDown() ? "IsMiddleButtonDown " : "") + "}";
         } else if (object instanceof VoltageKeyPressInfo) {
            VoltageKeyPressInfo kpi = (VoltageKeyPressInfo) object;
            objectText = "(VoltageKeyPressInfo) : {" +
               "GetKeycode:" + kpi.GetKeyCode() + " " +
               "GetCharacter:" + kpi.GetCharacter() + " " +
               (kpi.IsNonCharacterKeyCode() ? "IsNonCharacterKeyCode " : "") +
               (kpi.IsBackspaceKey() ? "IsBackspaceKey " : "") +
               (kpi.IsDeleteKey() ? "IsDeleteKey " : "") +
               (kpi.IsDownArrowKey() ? "IsDownArrowKey " : "") +
               (kpi.IsEndKey() ? "IsEndKey " : "") +
               (kpi.IsEnterKey() ? "IsEnterKey " : "") +
               (kpi.IsEscapeKey() ? "IsEscapeKey " : "") +
               (kpi.IsF1Key() ? "IsF1Key " : "") +
               (kpi.IsF2Key() ? "IsF2Key " : "") +
               (kpi.IsF3Key() ? "IsF3Key " : "") +
               (kpi.IsF4Key() ? "IsF4Key " : "") +
               (kpi.IsF5Key() ? "IsF5Key " : "") +
               (kpi.IsF6Key() ? "IsF6Key " : "") +
               (kpi.IsF7Key() ? "IsF7Key " : "") +
               (kpi.IsF8Key() ? "IsF8Key " : "") +
               (kpi.IsF9Key() ? "IsF9Key " : "") +
               (kpi.IsF10Key() ? "IsF10Key " : "") +
               (kpi.IsF11Key() ? "IsF11Key " : "") +
               (kpi.IsF12Key() ? "IsF12Key " : "") +
               (kpi.IsF13Key() ? "IsF13Key " : "") +
               (kpi.IsF14Key() ? "IsF14Key " : "") +
               (kpi.IsF15Key() ? "IsF15Key " : "") +
               (kpi.IsF16Key() ? "IsF16Key " : "") +
               (kpi.IsF17Key() ? "IsF17Key " : "") +
               (kpi.IsF18Key() ? "IsF18Key " : "") +
               (kpi.IsF19Key() ? "IsF19Key " : "") +
               (kpi.IsF20Key() ? "IsF20Key " : "") +
               (kpi.IsF21Key() ? "IsF21Key " : "") +
               (kpi.IsF22Key() ? "IsF22Key " : "") +
               (kpi.IsF23Key() ? "IsF23Key " : "") +
               (kpi.IsF24Key() ? "IsF24Key " : "") +
               (kpi.IsF25Key() ? "IsF25Key " : "") +
               (kpi.IsF26Key() ? "IsF26Key " : "") +
               (kpi.IsF27Key() ? "IsF27Key " : "") +
               (kpi.IsF28Key() ? "IsF28Key " : "") +
               (kpi.IsF29Key() ? "IsF29Key " : "") +
               (kpi.IsF30Key() ? "IsF30Key " : "") +
               (kpi.IsF31Key() ? "IsF31Key " : "") +
               (kpi.IsF32Key() ? "IsF32Key " : "") +
               (kpi.IsF33Key() ? "IsF33Key " : "") +
               (kpi.IsF34Key() ? "IsF34Key " : "") +
               (kpi.IsF35Key() ? "IsF35Key " : "") +
               (kpi.IsFastForwardKey() ? "IsFastForwardKey " : "") +
               (kpi.IsHomeKey() ? "IsHomeKey " : "") +
               (kpi.IsInsertKey() ? "IsInsertKey " : "") +
               (kpi.IsLeftArrowKey() ? "IsLeftArrowKey " : "") +
               (kpi.IsNumberPad0() ? "IsNumberPad0 " : "") +
               (kpi.IsNumberPad1() ? "IsNumberPad1 " : "") +
               (kpi.IsNumberPad2() ? "IsNumberPad2 " : "") +
               (kpi.IsNumberPad3() ? "IsNumberPad3 " : "") +
               (kpi.IsNumberPad4() ? "IsNumberPad4 " : "") +
               (kpi.IsNumberPad5() ? "IsNumberPad5 " : "") +
               (kpi.IsNumberPad6() ? "IsNumberPad6 " : "") +
               (kpi.IsNumberPad7() ? "IsNumberPad7 " : "") +
               (kpi.IsNumberPad8() ? "IsNumberPad8 " : "") +
               (kpi.IsNumberPad9() ? "IsNumberPad9 " : "") +
               (kpi.IsNumberPadAdd() ? "IsNumberPadAdd " : "") +
               (kpi.IsNumberPadComma() ? "IsNumberPadComma " : "") +
               (kpi.IsNumberPadDelete() ? "IsNumberPadDelete " : "") +
               (kpi.IsNumberPadDivide() ? "IsNumberPadDivide " : "") +
               (kpi.IsNumberPadEquals() ? "IsNumberPadEquals " : "") +
               (kpi.IsNumberPadMultiply() ? "IsNumberPadMultiply " : "") +
               (kpi.IsNumberPadPeriod() ? "IsNumberPadPeriod " : "") +
               (kpi.IsNumberPadSubtract() ? "IsNumberPadSubtract " : "") +
               (kpi.IsPageDownKey() ? "IsPageDownKey " : "") +
               (kpi.IsPageUpKey() ? "IsPageUpKey " : "") +
               (kpi.IsPlayKey() ? "IsPlayKey " : "") +
               (kpi.IsReturnKey() ? "IsReturnKey " : "") +
               (kpi.IsRewindKey() ? "IsRewindKey " : "") +
               (kpi.IsRightArrowKey() ? "IsRightArrowKey " : "") +
               (kpi.IsSpaceBarKey() ? "IsSpaceBarKey " : "") +
               (kpi.IsStopKey() ? "IsStopKey " : "") +
               (kpi.IsTabKey() ? "IsTabKey " : "") +
               (kpi.IsUpArrowKey() ? "IsUpArrowKey " : "") +
               (kpi.IsShiftKeyDown() ? "IsShiftKeyDown " : "") +
               (kpi.IsCtrlKeyDown() ? "IsCtrlKeyDown " : "") +
               (kpi.IsAltKeyDown() ? "IsAltKeyDown " : "") +
               (kpi.IsCommandDown() ? "IsCommandDown " : "") + "}";
               
         }
         
         LogToBuffer("Notify: component:" + componentName +   
            ", ModuleNotifications:" + notification +
            ", doubleValue:" + doubleValue +
            ", longValue:" + longValue +
            ", x:" + x + 
            ", y:" + y +
            ", object:" + objectText);
      }
      
      // Process the event
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
            if (component == DONTLOG_clearLogButton) {
               ClearLogBuffer();
            } else if (component == counterDownButton && doubleValue > 0)
            {
               counter1.SetValue(counter1.GetValue()-1);
            } else if (component == counterUpButton && doubleValue > 0)
            {
               counter1.SetValue(counter1.GetValue()+1);
            } else if (component == toggleLEDButton && doubleValue > 0)
            {
               if (LED1.GetValue() > 0) {
                  LED1.SetValue(0);
                  LED2.SetValue(1);
               } else
               {
                  LED1.SetValue(1);
                  LED2.SetValue(0);
               }
            }
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
            // Request an update the log canvas
            if (displayTimerName == displayTimerName) {
               DONTLOG_logCanvas.Invalidate();
            }
         }
         break;
      
         case Canvas_Painting:   // About to paint canvas.  object is a java.awt.Rectangle with painting boundaries
         {
            if (component == DONTLOG_logCanvas) {
               // Paint the log canvas
               PaintLogCanvas();
            } else if (component == canvas1) {
               // Pain the test canvas
               PaintCanvas1((Rectangle)object);
            }
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

      // Due to the number of logged items, the process sample logging is disabled.
      // You can adjust the message to display any events you want to capture.
      // LogToBuffer("Process Sample");

      // Set meters
      analogMeter1.SetValue(vuMeterInput.GetValue());
      meter1.SetValue(vuMeterInput.GetValue());

      // Send Midi in to Midi Out
      var messages = midiInputJack1.GetMessages();
      if (messages != null) {
      
         if (DONTLOG_showMidiInMessages.GetValue() > 0) {
            // Copy the message to the output
            for(var message : messages) {
               midiOutputJack1.AddMessage(message);
            }
         }

         for (var message : messages) {
            LogToBuffer("Process Sample:Midi-In: " + shortMessageFormatter.toString(message));
         }      
      }

      // Send mono inputs to mono outputs
      double monoInput = inputJack1.GetValue();
      if (DONTLOG_showMonoSamples.GetValue() > 0) {
         LogToBuffer("Process Sample:Mono-In: " + monoInput);
      }
      outputJack1.SetValue(monoInput);
      
      // Send poly inputs to poly outputs
      int length = GetNumberOfPolyVoices();
      if (DONTLOG_showPolySamples_1.GetValue() > 0) {
         String polyLog = "#voices:" + length + "{";
         for (int i = 0; i < length; i++) {
            if (i > 0)
               polyLog += ", " + polyInputJack1.GetPolyValue(i);
            else
               polyLog += polyInputJack1.GetPolyValue(i);
         }
         
         LogToBuffer("Process Sample:Poly-In: " + polyLog + "}");
      }
      
      for (int i = 0; i < length; i++)
         polyOutputJack1.SetPolyValue(i, polyInputJack1.GetPolyValue(i));
      
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
      
      // Dump out the Tool Tip Text method call to the log if the show tool tip text is toggled.
      String getToolTipText = super.GetTooltipText( component );
      if (DONTLOG_showToolTipTextEventsToggle.GetValue() > 0) {
         String componentName;
         if (component != null)
            componentName = component.componentName;
         else
            componentName = "(null)";

         if (!componentName.startsWith("DONTLOG_"))
         {
            LogToBuffer("GetToolTipText: component:" + component +
               ", super GetTooltipText" + getToolTipText);
         }
      }  
      return getToolTipText;
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

      // Log out the Edit component value event
      LogToBuffer("GetToolTipText: component:" + component +
         ", newValue:" + newValue +
         ", newText:" + newText);

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

      // Log out the fact that there was an undo/redo event
      LogToBuffer("OnUndoRedo: undoType:" + undoType +
         ", newValue:" + newValue +
         ", optionalObject:" + optionalObject);
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

      LogToBuffer("GetStateInformation");

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

      LogToBuffer("SetStateInformation: stateInfo:" + stateInfo);

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

      // Log out the variations information
      byte[] result = GetStateInformation();
      LogToBuffer("GetStateInformationForVariations: default result:" + result);

      return result;
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
      
      // Log out the variations information
      SetStateInformation(stateInfo);

      LogToBuffer("GetStateInformationForVariations: stateInfo:" + stateInfo);

      //[/user-SetStateInformationForVariations]
   }


   // Auto-generated variables
   private VoltageToggle DONTLOG_showMidiInMessages;
   private VoltageToggle DONTLOG_showPolySamples_1;
   private VoltageToggle DONTLOG_showMonoSamples;
   private VoltageLabel DONTLOG_INTENSIVE;
   private VoltageLabel textLabel20;
   private VoltageLabel textLabel19;
   private VoltageLabel textLabel18;
   private VoltageLabel textLabel17;
   private VoltageLabel textLabel16;
   private VoltageLabel textLabel15;
   private VoltageLabel textLabel14;
   private VoltageLabel textLabel13;
   private VoltageLabel textLabel12;
   private VoltageLabel textLabel11;
   private VoltageLabel textLabel21;
   private VoltageLabel textLabel10;
   private VoltageLabel textLabel9;
   private VoltageLabel textLabel8;
   private VoltageLabel textLabel7;
   private VoltageLabel textLabel6;
   private VoltageLabel textLabel4;
   private VoltageImage animation1;
   private VoltageButton DONTLOG_clearLogButton;
   private VoltageLabel textLabel3;
   private VoltageToggle DONTLOG_showToolTipTextEventsToggle;
   private VoltageAudioJack vuMeterInput;
   private VoltageVUMeter meter1;
   private VoltageToggle toggleButton4;
   private VoltageToggle toggleButton3;
   private VoltageToggle toggleButton2;
   private VoltageSwitch switch1;
   private VoltageLabel textLabel25;
   private VoltageSlider slider1;
   private VoltageScrollbar scrollbar2;
   private VoltagePolyJack polyOutputJack1;
   private VoltagePolyJack polyInputJack1;
   private VoltageAudioJack outputJack1;
   private VoltageAudioJack inputJack1;
   private VoltageMidiJack midiOutputJack1;
   private VoltageMidiJack midiInputJack1;
   private VoltageButton toggleLEDButton;
   private VoltageLED LED2;
   private VoltageLED LED1;
   private VoltageImage image1;
   private VoltageKnob knob1;
   private VoltageLabel editableText1;
   private VoltageButton counterDownButton;
   private VoltageButton counterUpButton;
   private VoltageDigitalCounter counter1;
   private VoltageCanvas canvas1;
   private VoltageButton button2;
   private VoltageAnalogVUMeter analogMeter1;
   private VoltageToggle DONTLOG_logPauseButton;
   private VoltageScrollbar DONTLOG_logScrollbar;
   private VoltageCanvas DONTLOG_logCanvas;
   private VoltageLabel DONTLOG_logFrame;
   private VoltageLabel textLabel5;


   //[user-code-and-variables]    Add your own variables and functions here

   // This is the name of the log canvas update timer
   private String displayTimerName = "DONTLOG_DEBUG_DISPLAYTIMER";

   // The circular buffer that contains the Log Text.
   // This code is in the CoreLib jar file (code supplied in the repsitory)
   private CircularArrayList<String> logBuffer = new CircularArrayList<String>(128, "");
   
   // The default log time format. Removed date so that it didn't take up unnecessary log space.
   private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSSSS"); 
   
   // A fixed buffer to receive log entries for painting 
   String[] logEntries = new String[28];
   
   // Midi formatter for decoding midi messages into text
   ShortMessageFormatter shortMessageFormatter = new ShortMessageFormatter();
   
   // Clear the log buffer back to blank.
   private void ClearLogBuffer() {
      logBuffer.reset();
   }
   
   // Log a message to the log buffer
   private void LogToBuffer(String message) {
      if (DONTLOG_logPauseButton.GetValue() == 0) {
   
         LocalDateTime now = LocalDateTime.now();  
         String threadName = "";//Thread.currentThread().getName();
         long threadId = Thread.currentThread().getId();
      
         String formattedMessage = dateTimeFormatter.format(now) + ": T<" + threadId + ">" + threadName + ":" + message;
      
         logBuffer.put(formattedMessage);
      }
   
   }  
   
   // Paint the log entries out
   private void PaintLogCanvas() {
      
      // Get the log entries
      logBuffer.toArray(logEntries);
      
      int horizontalOffset = DONTLOG_logScrollbar.GetPosition();
      
      Graphics2D g = DONTLOG_logCanvas.GetGraphics();
      int canvasWidth = DONTLOG_logCanvas.GetBitmapWidth();
      int canvasHeight = DONTLOG_logCanvas.GetBitmapHeight();

      g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
      FontMetrics fontMetrics = g.getFontMetrics();
      int stringHeight = fontMetrics.getAscent();
      
      g.setColor(Color.BLACK);
      g.fillRect(0,0,canvasWidth,canvasHeight);
      
      
      g.setColor(Color.WHITE);
      int y = canvasHeight - 1;
      for (int i = logEntries.length - 1; i >= 0; i--) {
         String logEntry = logEntries[i];
         
         g.drawString(logEntry, -horizontalOffset, y);
         y -= stringHeight;
      }
      
      g.dispose();
   }
   
   /**
      Paint the canvas1 canvas
      This is the test canvas
   */
   private void PaintCanvas1(Rectangle rectangle) {
       Graphics2D g = canvas1.GetGraphics();
      int canvasWidth = canvas1.GetBitmapWidth();
      int canvasHeight = canvas1.GetBitmapHeight();

      // Get the text metrics of the output string
      String outputString = "canvas1";
      g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));
      FontMetrics fontMetrics = g.getFontMetrics();
      int stringHeight = fontMetrics.getAscent();
      int stringWidth = fontMetrics.stringWidth(outputString);
      
      // Clear the canvas
      g.setColor(Color.BLACK);
      g.fillRect(0,0,canvasWidth,canvasHeight);
      
      // Draw the border rectangle
      g.setColor(Color.WHITE);
      g.drawRect(0,0,canvasWidth - 1, canvasHeight - 1);
      
      // Draw the text to show this is canvas1
      g.drawString(outputString, canvasWidth / 2 - stringWidth / 2, canvasHeight / 2 - stringHeight / 2);

      // Draw a rectangle to show which area was repainted
      g.setColor(Color.BLUE);
      g.drawRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1);

      g.dispose();
      
   }
   //[/user-code-and-variables]
}

 