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

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

import au.com.arbuxmusic.corelib.util.arrays.*;

//[/user-imports]


public class MyModule extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public MyModule( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "My Module", ModuleType.ModuleType_Utility, 12.8 );


      textLabel5 = new VoltageLabel( "textLabel5", "textLabel5", this, "Component Event Explorer" );
      AddComponent( textLabel5 );
      textLabel5.SetWantsMouseNotifications( false );
      textLabel5.SetPosition( 0, 0 );
      textLabel5.SetSize( 375, 30 );
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
      DONTLOG_logFrame.SetPosition( 378, 0 );
      DONTLOG_logFrame.SetSize( 538, 359 );
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

      DONTLOG_logCanvas = new VoltageCanvas( "DONTLOG_logCanvas", "DONTLOG_logCanvas", this, 433, 306 );
      AddComponent( DONTLOG_logCanvas );
      DONTLOG_logCanvas.SetWantsMouseNotifications( false );
      DONTLOG_logCanvas.SetPosition( 387, 18 );
      DONTLOG_logCanvas.SetSize( 433, 306 );

      DONTLOG_logScrollbar = new VoltageScrollbar( "DONTLOG_logScrollbar", "DONTLOG_logScrollbar", this, false, false );
      AddComponent( DONTLOG_logScrollbar );
      DONTLOG_logScrollbar.SetWantsMouseNotifications( false );
      DONTLOG_logScrollbar.SetPosition( 388, 330 );
      DONTLOG_logScrollbar.SetSize( 432, 20 );
      DONTLOG_logScrollbar.SetRange( 0, 800 );
      DONTLOG_logScrollbar.SetThumbSize( 20 );
      DONTLOG_logScrollbar.ScrollTo( 0 );
      DONTLOG_logScrollbar.SetThumbColor( 122, 122, 122, 255 );
      DONTLOG_logScrollbar.SetBackgroundColor( 20, 20, 20, 255 );
      DONTLOG_logScrollbar.SetThumbCornerSize( 4.00 );

      DONTLOG_logPauseButton = new VoltageToggle( "DONTLOG_logPauseButton", "DONTLOG_logPauseButton", this, false, 0 );
      AddComponent( DONTLOG_logPauseButton );
      DONTLOG_logPauseButton.SetWantsMouseNotifications( false );
      DONTLOG_logPauseButton.SetPosition( 824, 326 );
      DONTLOG_logPauseButton.SetSize( 82, 31 );
      DONTLOG_logPauseButton.SetSkin( "Blue Square" );
      DONTLOG_logPauseButton.ShowOverlay( true );
      DONTLOG_logPauseButton.SetOverlayText( "Pause Log" );
      DONTLOG_logPauseButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      DONTLOG_logPauseButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      DONTLOG_logPauseButton.SetOverlayArea( 0, 0, 0, 0 );
      DONTLOG_logPauseButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );

      analogMeter1 = new VoltageAnalogVUMeter( "analogMeter1", "analogMeter1", this );
      AddComponent( analogMeter1 );
      analogMeter1.SetWantsMouseNotifications( false );
      analogMeter1.SetPosition( 10, 47 );
      analogMeter1.SetSize( 103, 57 );
      analogMeter1.SetSkin( "Analog Black" );

      button2 = new VoltageButton( "button2", "button2", this );
      AddComponent( button2 );
      button2.SetWantsMouseNotifications( false );
      button2.SetPosition( 99, 134 );
      button2.SetSize( 39, 39 );
      button2.SetSkin( "Large Rounded Square" );
      button2.ShowOverlay( false );
      button2.SetOverlayText( "" );
      button2.SetAutoRepeat( false );

      canvas1 = new VoltageCanvas( "canvas1", "canvas1", this, 141, 82 );
      AddComponent( canvas1 );
      canvas1.SetWantsMouseNotifications( true );
      canvas1.SetPosition( 6, 272 );
      canvas1.SetSize( 141, 82 );

      counter1 = new VoltageDigitalCounter( "counter1", "counter1", this, 2 );
      AddComponent( counter1 );
      counter1.SetWantsMouseNotifications( false );
      counter1.SetPosition( 11, 140 );
      counter1.SetSize( 42, 33 );
      counter1.SetSkin( "Gray" );
      counter1.SetJustificationFlags( VoltageDigitalCounter.Justification.Centered );

      counterUpButton = new VoltageButton( "counterUpButton", "counterUpButton", this );
      AddComponent( counterUpButton );
      counterUpButton.SetWantsMouseNotifications( false );
      counterUpButton.SetPosition( 55, 141 );
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
      counterDownButton.SetPosition( 55, 157 );
      counterDownButton.SetSize( 14, 14 );
      counterDownButton.SetSkin( "Large Rounded Square" );
      counterDownButton.ShowOverlay( true );
      counterDownButton.SetOverlayText( "-" );
      counterDownButton.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      counterDownButton.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      counterDownButton.SetOverlayArea( 0, 0, 0, 0 );
      counterDownButton.SetOverlayTextJustification( VoltageButton.Justification.Centered );
      counterDownButton.SetAutoRepeat( true );

      editableText1 = new VoltageLabel( "editableText1", "editableText1", this, "Editable Text" );
      AddComponent( editableText1 );
      editableText1.SetWantsMouseNotifications( false );
      editableText1.SetPosition( 13, 180 );
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
      knob1.SetPosition( 13, 204 );
      knob1.SetSize( 27, 27 );
      knob1.SetSkin( "Plastic White" );
      knob1.SetRange( 0.0, 1.0, 0.5, false, 0 );
      knob1.SetKnobParams( 215, 145 );
      knob1.DisplayValueInPercent( false );
      knob1.SetKnobAdjustsRing( true );

      image1 = new VoltageImage( "image1", "image1", this, false );
      AddComponent( image1 );
      image1.SetWantsMouseNotifications( false );
      image1.SetPosition( 241, 154 );
      image1.SetSize( 128, 64 );
      image1.SetCurrentImage( "image image.png" );

      knob2 = new VoltageKnob( "knob2", "knob2", this, 0.0, 1.0, 0.5 );
      AddComponent( knob2 );
      knob2.SetWantsMouseNotifications( false );
      knob2.SetPosition( 53, 205 );
      knob2.SetSize( 27, 27 );
      knob2.SetSkin( "Plastic White" );
      knob2.SetRange( 0.0, 1.0, 0.5, false, 0 );
      knob2.SetKnobParams( 215, 145 );
      knob2.DisplayValueInPercent( false );
      knob2.SetKnobAdjustsRing( true );

      LED1 = new VoltageLED( "LED1", "LED1", this );
      AddComponent( LED1 );
      LED1.SetWantsMouseNotifications( false );
      LED1.SetPosition( 171, 185 );
      LED1.SetSize( 17, 17 );
      LED1.SetSkin( "Red" );

      LED2 = new VoltageLED( "LED2", "LED2", this );
      AddComponent( LED2 );
      LED2.SetWantsMouseNotifications( false );
      LED2.SetPosition( 171, 204 );
      LED2.SetSize( 17, 17 );
      LED2.SetSkin( "Red" );

      toggleLEDButton = new VoltageButton( "toggleLEDButton", "toggleLEDButton", this );
      AddComponent( toggleLEDButton );
      toggleLEDButton.SetWantsMouseNotifications( false );
      toggleLEDButton.SetPosition( 194, 186 );
      toggleLEDButton.SetSize( 39, 39 );
      toggleLEDButton.SetSkin( "Large Rounded Square" );
      toggleLEDButton.ShowOverlay( false );
      toggleLEDButton.SetOverlayText( "" );
      toggleLEDButton.SetAutoRepeat( false );

      midiInputJack1 = new VoltageMidiJack( "midiInputJack1", "midiInputJack1", this, JackType.JackType_MidiInput );
      AddComponent( midiInputJack1 );
      midiInputJack1.SetWantsMouseNotifications( false );
      midiInputJack1.SetPosition( 264, 220 );
      midiInputJack1.SetSize( 57, 57 );
      midiInputJack1.SetSkin( "MIDI Jack" );

      midiOutputJack1 = new VoltageMidiJack( "midiOutputJack1", "midiOutputJack1", this, JackType.JackType_MidiOutput );
      AddComponent( midiOutputJack1 );
      midiOutputJack1.SetWantsMouseNotifications( false );
      midiOutputJack1.SetPosition( 323, 234 );
      midiOutputJack1.SetSize( 57, 57 );
      midiOutputJack1.SetSkin( "MIDI Jack" );

      inputJack1 = new VoltageAudioJack( "inputJack1", "inputJack1", this, JackType.JackType_AudioInput );
      AddComponent( inputJack1 );
      inputJack1.SetWantsMouseNotifications( false );
      inputJack1.SetPosition( 290, 294 );
      inputJack1.SetSize( 37, 37 );
      inputJack1.SetSkin( "Jack Straight" );

      outputJack1 = new VoltageAudioJack( "outputJack1", "outputJack1", this, JackType.JackType_AudioOutput );
      AddComponent( outputJack1 );
      outputJack1.SetWantsMouseNotifications( false );
      outputJack1.SetPosition( 334, 295 );
      outputJack1.SetSize( 37, 37 );
      outputJack1.SetSkin( "Jack Straight" );

      polyInputJack1 = new VoltagePolyJack( "polyInputJack1", "polyInputJack1", this, JackType.JackType_PolyInput );
      AddComponent( polyInputJack1 );
      polyInputJack1.SetWantsMouseNotifications( false );
      polyInputJack1.SetPosition( 296, 330 );
      polyInputJack1.SetSize( 25, 25 );
      polyInputJack1.SetSkin( "Poly Jack Straight" );

      polyOutputJack1 = new VoltagePolyJack( "polyOutputJack1", "polyOutputJack1", this, JackType.JackType_PolyOutput );
      AddComponent( polyOutputJack1 );
      polyOutputJack1.SetWantsMouseNotifications( false );
      polyOutputJack1.SetPosition( 338, 331 );
      polyOutputJack1.SetSize( 25, 25 );
      polyOutputJack1.SetSkin( "Poly Jack Straight" );

      scrollbar2 = new VoltageScrollbar( "scrollbar2", "scrollbar2", this, true, false );
      AddComponent( scrollbar2 );
      scrollbar2.SetWantsMouseNotifications( false );
      scrollbar2.SetPosition( 274, 42 );
      scrollbar2.SetSize( 12, 100 );
      scrollbar2.SetRange( 0, 100 );
      scrollbar2.SetThumbSize( 20 );
      scrollbar2.ScrollTo( 0 );
      scrollbar2.SetThumbColor( 122, 122, 122, 255 );
      scrollbar2.SetBackgroundColor( 20, 20, 20, 255 );
      scrollbar2.SetThumbCornerSize( 4.00 );

      slider1 = new VoltageSlider( "slider1", "slider1", this, true, 0.0, 1.0, 0.5, 0 );
      AddComponent( slider1 );
      slider1.SetWantsMouseNotifications( false );
      slider1.SetPosition( 245, 42 );
      slider1.SetSize( 12, 103 );
      slider1.SetSkin( "Straight Black Plain" );
      slider1.DisplayValueInPercent( false );

      switch1 = new VoltageSwitch( "switch1", "switch1", this, 0 );
      AddComponent( switch1 );
      switch1.SetWantsMouseNotifications( false );
      switch1.SetPosition( 158, 141 );
      switch1.SetSize( 35, 35 );
      switch1.SetSkin( "3-State Silver" );

      textLabel2 = new VoltageLabel( "textLabel2", "textLabel2", this, "Text" );
      AddComponent( textLabel2 );
      textLabel2.SetWantsMouseNotifications( false );
      textLabel2.SetPosition( 97, 185 );
      textLabel2.SetSize( 52, 30 );
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

      toggleButton2 = new VoltageToggle( "toggleButton2", "toggleButton2", this, false, 1 );
      AddComponent( toggleButton2 );
      toggleButton2.SetWantsMouseNotifications( false );
      toggleButton2.SetPosition( 201, 109 );
      toggleButton2.SetSize( 31, 31 );
      toggleButton2.SetSkin( "Blue Square" );
      toggleButton2.ShowOverlay( false );
      toggleButton2.SetOverlayText( "" );

      toggleButton3 = new VoltageToggle( "toggleButton3", "toggleButton3", this, false, 1 );
      AddComponent( toggleButton3 );
      toggleButton3.SetWantsMouseNotifications( false );
      toggleButton3.SetPosition( 201, 145 );
      toggleButton3.SetSize( 31, 31 );
      toggleButton3.SetSkin( "Blue Square" );
      toggleButton3.ShowOverlay( false );
      toggleButton3.SetOverlayText( "" );

      toggleButton4 = new VoltageToggle( "toggleButton4", "toggleButton4", this, false, 0 );
      AddComponent( toggleButton4 );
      toggleButton4.SetWantsMouseNotifications( false );
      toggleButton4.SetPosition( 201, 40 );
      toggleButton4.SetSize( 31, 31 );
      toggleButton4.SetSkin( "Blue Square" );
      toggleButton4.ShowOverlay( false );
      toggleButton4.SetOverlayText( "" );

      meter1 = new VoltageVUMeter( "meter1", "meter1", this );
      AddComponent( meter1 );
      meter1.SetWantsMouseNotifications( false );
      meter1.SetPosition( 129, 50 );
      meter1.SetSize( 21, 59 );
      meter1.SetSkin( "Small Meter" );
      meter1.SetLinearMode( false );

      vuMeterInput = new VoltageAudioJack( "vuMeterInput", "vuMeterInput", this, JackType.JackType_AudioInput );
      AddComponent( vuMeterInput );
      vuMeterInput.SetWantsMouseNotifications( false );
      vuMeterInput.SetPosition( 159, 59 );
      vuMeterInput.SetSize( 37, 37 );
      vuMeterInput.SetSkin( "Jack Straight" );

      DONTLOG_showGetToolTipTextEvents = new VoltageLabel( "DONTLOG_showGetToolTipTextEvents", "DONTLOG_showGetToolTipTextEvents", this, "Show ToolTip events" );
      AddComponent( DONTLOG_showGetToolTipTextEvents );
      DONTLOG_showGetToolTipTextEvents.SetWantsMouseNotifications( false );
      DONTLOG_showGetToolTipTextEvents.SetPosition( 856, 16 );
      DONTLOG_showGetToolTipTextEvents.SetSize( 61, 44 );
      DONTLOG_showGetToolTipTextEvents.SetEditable( false, false );
      DONTLOG_showGetToolTipTextEvents.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      DONTLOG_showGetToolTipTextEvents.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      DONTLOG_showGetToolTipTextEvents.SetColor( new Color( 255, 255, 255, 255 ) );
      DONTLOG_showGetToolTipTextEvents.SetBkColor( new Color( 65, 65, 65, 0 ) );
      DONTLOG_showGetToolTipTextEvents.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      DONTLOG_showGetToolTipTextEvents.SetBorderSize( 1 );
      DONTLOG_showGetToolTipTextEvents.SetMultiLineEdit( true );
      DONTLOG_showGetToolTipTextEvents.SetIsNumberEditor( false );
      DONTLOG_showGetToolTipTextEvents.SetNumberEditorRange( 0, 100 );
      DONTLOG_showGetToolTipTextEvents.SetNumberEditorInterval( 1 );
      DONTLOG_showGetToolTipTextEvents.SetNumberEditorUsesMouseWheel( false );
      DONTLOG_showGetToolTipTextEvents.SetHasCustomTextHoverColor( false );
      DONTLOG_showGetToolTipTextEvents.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      DONTLOG_showGetToolTipTextEvents.SetFont( "<Sans-Serif>", 10, false, false );

      DONTLOG_showToolTipTextEventsToggle = new VoltageToggle( "DONTLOG_showToolTipTextEventsToggle", "DONTLOG_showToolTipTextEventsToggle", this, true, 0 );
      AddComponent( DONTLOG_showToolTipTextEventsToggle );
      DONTLOG_showToolTipTextEventsToggle.SetWantsMouseNotifications( false );
      DONTLOG_showToolTipTextEventsToggle.SetPosition( 824, 23 );
      DONTLOG_showToolTipTextEventsToggle.SetSize( 31, 31 );
      DONTLOG_showToolTipTextEventsToggle.SetSkin( "Blue Square" );
      DONTLOG_showToolTipTextEventsToggle.ShowOverlay( false );
      DONTLOG_showToolTipTextEventsToggle.SetOverlayText( "" );

      textLabel3 = new VoltageLabel( "textLabel3", "textLabel3", this, "The Canvas below will draw a blue rectangle around the update area" );
      AddComponent( textLabel3 );
      textLabel3.SetWantsMouseNotifications( false );
      textLabel3.SetPosition( 11, 236 );
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
      DONTLOG_clearLogButton.SetPosition( 826, 259 );
      DONTLOG_clearLogButton.SetSize( 79, 39 );
      DONTLOG_clearLogButton.SetSkin( "Large Rounded Square" );
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
      animation1.SetPosition( 164, 281 );
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



      canBeBypassed = false;
      SetSkin( "166f4f30fe3b4489b1d0d33621cc2a27" );
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
         LogToBuffer("Notify: component:" + componentName +   
            ", ModuleNotifications:" + notification +
            ", doubleValue:" + doubleValue +
            ", longValue:" + longValue +
            ", x:" + x + 
            ", y:" + y +
            ", object:" + object);
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


      // Set inputs to outputs
      outputJack1.SetValue(inputJack1.GetValue());
      polyOutputJack1.SetValue(polyInputJack1.GetValue());
      var messages = midiInputJack1.GetMessages();
      if (messages != null) {
         for(var message : midiInputJack1.GetMessages()) {
            midiOutputJack1.AddMessage(message);
         }
      }
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
   private VoltageImage animation1;
   private VoltageButton DONTLOG_clearLogButton;
   private VoltageLabel textLabel3;
   private VoltageToggle DONTLOG_showToolTipTextEventsToggle;
   private VoltageLabel DONTLOG_showGetToolTipTextEvents;
   private VoltageAudioJack vuMeterInput;
   private VoltageVUMeter meter1;
   private VoltageToggle toggleButton4;
   private VoltageToggle toggleButton3;
   private VoltageToggle toggleButton2;
   private VoltageLabel textLabel2;
   private VoltageSwitch switch1;
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
   private VoltageKnob knob2;
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
   String[] logEntries = new String[27];
   
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
      for (int i = 0; i < logEntries.length; i++) {
         String logEntry = logEntries[i];
         
         // int stringWidth = fontMetrics.stringWidth(logEntry);
         
         g.drawString(logEntry, -horizontalOffset, (i + 1) * stringHeight);
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

 