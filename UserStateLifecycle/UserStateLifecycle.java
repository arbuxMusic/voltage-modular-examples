package au.com.arbuxmusic.userstatelifecycle;


import voltage.controllers.*;
import voltage.core.*;
import voltage.core.Jack.JackType;
import voltage.sources.*;
import voltage.utility.*;
import voltage.processors.*;
import voltage.effects.*;
import java.awt.*;

//[user-imports]   Add your own imports here

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//NOTE: This class is only partially implemented. It currently shows how to serialise/deserialise state,
//       and some basic examples of adding undo/redo functionality. It does not:
// - deal with Reset and Randomize nodes running
//    - These should be dealt with by additional manual states being called when the Reset/Randomize events are called.
//    - I'd like to see GetStateinformation() / SetStateInformation() be called for those nodes
// - deal with the undo of Reset/Randomize
//
// To avoid spamming the undo history, I would also recommend that you only create an undo/redo node on mouse-up for any controls that take mouse input.
// 

//[/user-imports]


public class UserStateLifeycycle extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public UserStateLifeycycle( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "User State Lifecycle", ModuleType.ModuleType_Utility, 12.0 );


      rectangleExample = new VoltageRectangle( "rectangleExample", "rectangleExample", this );
      AddComponent( rectangleExample );
      rectangleExample.SetWantsMouseNotifications( false );
      rectangleExample.SetPosition( 572, 93 );
      rectangleExample.SetSize( 50, 50 );
      rectangleExample.SetRectangleColor( new Color( 165, 165, 165, 255 ) );
      rectangleExample.SetBorderColor( new Color( 0, 0, 0, 255 ) );
      rectangleExample.SetBorderSize( 0 );
      rectangleExample.SetCornerSize( 0 );

      ellipseExample = new VoltageEllipse( "ellipseExample", "ellipseExample", this );
      AddComponent( ellipseExample );
      ellipseExample.SetWantsMouseNotifications( false );
      ellipseExample.SetPosition( 585, 56 );
      ellipseExample.SetSize( 50, 50 );
      ellipseExample.SetEllipseColor( new Color( 165, 165, 165, 255 ) );
      ellipseExample.SetBorderColor( new Color( 0, 0, 0, 255 ) );
      ellipseExample.SetBorderSize( 0 );

      titleLabel = new VoltageLabel( "titleLabel", "titleLabel", this, "User State Lifecycle" );
      AddComponent( titleLabel );
      titleLabel.SetWantsMouseNotifications( false );
      titleLabel.SetPosition( 0, 0 );
      titleLabel.SetSize( 864, 30 );
      titleLabel.SetEditable( false, false );
      titleLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      titleLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      titleLabel.SetColor( new Color( 0, 0, 0, 255 ) );
      titleLabel.SetBkColor( new Color( 255, 255, 255, 255 ) );
      titleLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      titleLabel.SetBorderSize( 1 );
      titleLabel.SetMultiLineEdit( false );
      titleLabel.SetIsNumberEditor( false );
      titleLabel.SetNumberEditorRange( 0, 100 );
      titleLabel.SetNumberEditorInterval( 1 );
      titleLabel.SetNumberEditorUsesMouseWheel( false );
      titleLabel.SetHasCustomTextHoverColor( false );
      titleLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      titleLabel.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel2 = new VoltageLabel( "textLabel2", "textLabel2", this, "These controls auto-save state in a preset:" );
      AddComponent( textLabel2 );
      textLabel2.SetWantsMouseNotifications( false );
      textLabel2.SetPosition( 8, 40 );
      textLabel2.SetSize( 200, 36 );
      textLabel2.SetEditable( false, false );
      textLabel2.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel2.SetJustificationFlags( VoltageLabel.Justification.Top );
      textLabel2.SetColor( new Color( 223, 223, 223, 255 ) );
      textLabel2.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel2.SetBorderColor( new Color( 183, 183, 183, 255 ) );
      textLabel2.SetBorderSize( 1 );
      textLabel2.SetMultiLineEdit( true );
      textLabel2.SetIsNumberEditor( false );
      textLabel2.SetNumberEditorRange( 0, 100 );
      textLabel2.SetNumberEditorInterval( 1 );
      textLabel2.SetNumberEditorUsesMouseWheel( false );
      textLabel2.SetHasCustomTextHoverColor( false );
      textLabel2.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel2.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel3 = new VoltageLabel( "textLabel3", "textLabel3", this, "Editable Text:" );
      AddComponent( textLabel3 );
      textLabel3.SetWantsMouseNotifications( false );
      textLabel3.SetPosition( 5, 84 );
      textLabel3.SetSize( 80, 21 );
      textLabel3.SetEditable( false, false );
      textLabel3.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel3.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel3.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel3.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel3.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel3.SetBorderSize( 1 );
      textLabel3.SetMultiLineEdit( false );
      textLabel3.SetIsNumberEditor( false );
      textLabel3.SetNumberEditorRange( 0, 100 );
      textLabel3.SetNumberEditorInterval( 1 );
      textLabel3.SetNumberEditorUsesMouseWheel( false );
      textLabel3.SetHasCustomTextHoverColor( false );
      textLabel3.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel3.SetFont( "<Sans-Serif>", 14, false, false );

      editableTextExample = new VoltageLabel( "editableTextExample", "editableTextExample", this, "Edit Me" );
      AddComponent( editableTextExample );
      editableTextExample.SetWantsMouseNotifications( false );
      editableTextExample.SetPosition( 86, 82 );
      editableTextExample.SetSize( 117, 24 );
      editableTextExample.SetEditable( false, true );
      editableTextExample.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      editableTextExample.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      editableTextExample.SetColor( new Color( 217, 217, 217, 255 ) );
      editableTextExample.SetBkColor( new Color( 65, 65, 65, 0 ) );
      editableTextExample.SetBorderColor( new Color( 68, 53, 53, 193 ) );
      editableTextExample.SetBorderSize( 1 );
      editableTextExample.SetEditTextColor( new Color( 0, 0, 0 ) );
      editableTextExample.SetEditBackColor( new Color( 65, 65, 65, 0 ) );
      editableTextExample.SetEditOutlineColor( new Color( 0, 0, 0, 0 ) );
      editableTextExample.SetMultiLineEdit( false );
      editableTextExample.SetIsNumberEditor( false );
      editableTextExample.SetNumberEditorRange( 0, 100 );
      editableTextExample.SetNumberEditorInterval( 1 );
      editableTextExample.SetNumberEditorUsesMouseWheel( false );
      editableTextExample.SetHasCustomTextHoverColor( false );
      editableTextExample.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      editableTextExample.SetFont( "<Sans-Serif>", 14, false, false );

      visibleKnobExample = new VoltageKnob( "visibleKnobExample", "visibleKnobExample", this, 0.0, 1.0, 0.5 );
      AddComponent( visibleKnobExample );
      visibleKnobExample.SetWantsMouseNotifications( false );
      visibleKnobExample.SetPosition( 84, 112 );
      visibleKnobExample.SetSize( 27, 27 );
      visibleKnobExample.SetSkin( "Plastic White" );
      visibleKnobExample.SetRange( 0.0, 1.0, 0.5, false, 0 );
      visibleKnobExample.SetKnobParams( 215, 145 );
      visibleKnobExample.DisplayValueInPercent( false );
      visibleKnobExample.SetKnobAdjustsRing( true );

      textLabel4 = new VoltageLabel( "textLabel4", "textLabel4", this, "Knob:" );
      AddComponent( textLabel4 );
      textLabel4.SetWantsMouseNotifications( false );
      textLabel4.SetPosition( 8, 115 );
      textLabel4.SetSize( 80, 21 );
      textLabel4.SetEditable( false, false );
      textLabel4.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel4.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel4.SetColor( new Color( 198, 198, 198, 255 ) );
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

      textLabel5 = new VoltageLabel( "textLabel5", "textLabel5", this, "All Jack connections:" );
      AddComponent( textLabel5 );
      textLabel5.SetWantsMouseNotifications( false );
      textLabel5.SetPosition( 7, 286 );
      textLabel5.SetSize( 80, 42 );
      textLabel5.SetEditable( false, false );
      textLabel5.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel5.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel5.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel5.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel5.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel5.SetBorderSize( 1 );
      textLabel5.SetMultiLineEdit( true );
      textLabel5.SetIsNumberEditor( false );
      textLabel5.SetNumberEditorRange( 0, 100 );
      textLabel5.SetNumberEditorInterval( 1 );
      textLabel5.SetNumberEditorUsesMouseWheel( false );
      textLabel5.SetHasCustomTextHoverColor( false );
      textLabel5.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel5.SetFont( "<Sans-Serif>", 14, false, false );

      midiInputJackExample = new VoltageMidiJack( "midiInputJackExample", "midiInputJackExample", this, JackType.JackType_MidiInput );
      AddComponent( midiInputJackExample );
      midiInputJackExample.SetWantsMouseNotifications( false );
      midiInputJackExample.SetPosition( 90, 260 );
      midiInputJackExample.SetSize( 57, 57 );
      midiInputJackExample.SetSkin( "MIDI Jack" );

      midiOutputJackExample = new VoltageMidiJack( "midiOutputJackExample", "midiOutputJackExample", this, JackType.JackType_MidiOutput );
      AddComponent( midiOutputJackExample );
      midiOutputJackExample.SetWantsMouseNotifications( false );
      midiOutputJackExample.SetPosition( 153, 260 );
      midiOutputJackExample.SetSize( 57, 57 );
      midiOutputJackExample.SetSkin( "MIDI Jack" );

      textLabel6 = new VoltageLabel( "textLabel6", "textLabel6", this, "Input" );
      AddComponent( textLabel6 );
      textLabel6.SetWantsMouseNotifications( false );
      textLabel6.SetPosition( 78, 142 );
      textLabel6.SetSize( 80, 21 );
      textLabel6.SetEditable( false, false );
      textLabel6.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel6.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel6.SetColor( new Color( 198, 198, 198, 255 ) );
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

      textLabel7 = new VoltageLabel( "textLabel7", "textLabel7", this, "Output" );
      AddComponent( textLabel7 );
      textLabel7.SetWantsMouseNotifications( false );
      textLabel7.SetPosition( 140, 142 );
      textLabel7.SetSize( 80, 21 );
      textLabel7.SetEditable( false, false );
      textLabel7.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel7.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel7.SetColor( new Color( 198, 198, 198, 255 ) );
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

      inputJackExample = new VoltageAudioJack( "inputJackExample", "inputJackExample", this, JackType.JackType_AudioInput );
      AddComponent( inputJackExample );
      inputJackExample.SetWantsMouseNotifications( false );
      inputJackExample.SetPosition( 87, 317 );
      inputJackExample.SetSize( 37, 37 );
      inputJackExample.SetSkin( "Jack Straight" );

      polyInputJackExample = new VoltagePolyJack( "polyInputJackExample", "polyInputJackExample", this, JackType.JackType_PolyInput );
      AddComponent( polyInputJackExample );
      polyInputJackExample.SetWantsMouseNotifications( false );
      polyInputJackExample.SetPosition( 121, 322 );
      polyInputJackExample.SetSize( 25, 25 );
      polyInputJackExample.SetSkin( "Poly Jack Straight" );

      outputJackExample = new VoltageAudioJack( "outputJackExample", "outputJackExample", this, JackType.JackType_AudioOutput );
      AddComponent( outputJackExample );
      outputJackExample.SetWantsMouseNotifications( false );
      outputJackExample.SetPosition( 152, 316 );
      outputJackExample.SetSize( 37, 37 );
      outputJackExample.SetSkin( "Jack Straight" );

      polyOutputJackExample = new VoltagePolyJack( "polyOutputJackExample", "polyOutputJackExample", this, JackType.JackType_PolyOutput );
      AddComponent( polyOutputJackExample );
      polyOutputJackExample.SetWantsMouseNotifications( false );
      polyOutputJackExample.SetPosition( 185, 321 );
      polyOutputJackExample.SetSize( 25, 25 );
      polyOutputJackExample.SetSkin( "Poly Jack Straight" );

      scrollbarExample = new VoltageScrollbar( "scrollbarExample", "scrollbarExample", this, false, false );
      AddComponent( scrollbarExample );
      scrollbarExample.SetWantsMouseNotifications( false );
      scrollbarExample.SetPosition( 562, 191 );
      scrollbarExample.SetSize( 75, 12 );
      scrollbarExample.SetRange( 0, 100 );
      scrollbarExample.SetThumbSize( 20 );
      scrollbarExample.ScrollTo( 0 );
      scrollbarExample.SetThumbColor( 122, 122, 122, 255 );
      scrollbarExample.SetBackgroundColor( 20, 20, 20, 255 );
      scrollbarExample.SetThumbCornerSize( 4.00 );

      textLabel8 = new VoltageLabel( "textLabel8", "textLabel8", this, "Scrollbar:" );
      AddComponent( textLabel8 );
      textLabel8.SetWantsMouseNotifications( false );
      textLabel8.SetPosition( 475, 186 );
      textLabel8.SetSize( 80, 21 );
      textLabel8.SetEditable( false, false );
      textLabel8.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel8.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel8.SetColor( new Color( 198, 198, 198, 255 ) );
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

      sliderExample = new VoltageSlider( "sliderExample", "sliderExample", this, false, 0.0, 1, 0, 2 );
      AddComponent( sliderExample );
      sliderExample.SetWantsMouseNotifications( false );
      sliderExample.SetPosition( 104, 178 );
      sliderExample.SetSize( 98, 16 );
      sliderExample.SetSkin( "Straight Black Plain Horizontal" );
      sliderExample.DisplayValueInPercent( false );

      textLabel9 = new VoltageLabel( "textLabel9", "textLabel9", this, "Slider:" );
      AddComponent( textLabel9 );
      textLabel9.SetWantsMouseNotifications( false );
      textLabel9.SetPosition( 7, 176 );
      textLabel9.SetSize( 80, 21 );
      textLabel9.SetEditable( false, false );
      textLabel9.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel9.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel9.SetColor( new Color( 198, 198, 198, 255 ) );
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

      switchExample = new VoltageSwitch( "switchExample", "switchExample", this, 0 );
      AddComponent( switchExample );
      switchExample.SetWantsMouseNotifications( false );
      switchExample.SetPosition( 100, 192 );
      switchExample.SetSize( 35, 35 );
      switchExample.SetSkin( "3-State Silver" );

      textLabel10 = new VoltageLabel( "textLabel10", "textLabel10", this, "Switch:" );
      AddComponent( textLabel10 );
      textLabel10.SetWantsMouseNotifications( false );
      textLabel10.SetPosition( 5, 198 );
      textLabel10.SetSize( 80, 21 );
      textLabel10.SetEditable( false, false );
      textLabel10.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel10.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel10.SetColor( new Color( 198, 198, 198, 255 ) );
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

      toggleButton1Example = new VoltageToggle( "toggleButton1Example", "toggleButton1Example", this, true, 1 );
      AddComponent( toggleButton1Example );
      toggleButton1Example.SetWantsMouseNotifications( false );
      toggleButton1Example.SetPosition( 139, 224 );
      toggleButton1Example.SetSize( 31, 31 );
      toggleButton1Example.SetSkin( "Blue Square" );
      toggleButton1Example.ShowOverlay( false );
      toggleButton1Example.SetOverlayText( "" );

      toggleButton2Example = new VoltageToggle( "toggleButton2Example", "toggleButton2Example", this, false, 1 );
      AddComponent( toggleButton2Example );
      toggleButton2Example.SetWantsMouseNotifications( false );
      toggleButton2Example.SetPosition( 173, 224 );
      toggleButton2Example.SetSize( 31, 31 );
      toggleButton2Example.SetSkin( "Blue Square" );
      toggleButton2Example.ShowOverlay( false );
      toggleButton2Example.SetOverlayText( "" );

      textLabel11 = new VoltageLabel( "textLabel11", "textLabel11", this, "Toggle Button:" );
      AddComponent( textLabel11 );
      textLabel11.SetWantsMouseNotifications( false );
      textLabel11.SetPosition( 6, 233 );
      textLabel11.SetSize( 80, 21 );
      textLabel11.SetEditable( false, false );
      textLabel11.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel11.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel11.SetColor( new Color( 198, 198, 198, 255 ) );
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

      textLabel12 = new VoltageLabel( "textLabel12", "textLabel12", this, "These controls do not save state and must be manually managed:" );
      AddComponent( textLabel12 );
      textLabel12.SetWantsMouseNotifications( false );
      textLabel12.SetPosition( 221, 40 );
      textLabel12.SetSize( 200, 36 );
      textLabel12.SetEditable( false, false );
      textLabel12.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel12.SetJustificationFlags( VoltageLabel.Justification.Top );
      textLabel12.SetColor( new Color( 223, 223, 223, 255 ) );
      textLabel12.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel12.SetBorderColor( new Color( 183, 183, 183, 255 ) );
      textLabel12.SetBorderSize( 1 );
      textLabel12.SetMultiLineEdit( true );
      textLabel12.SetIsNumberEditor( false );
      textLabel12.SetNumberEditorRange( 0, 100 );
      textLabel12.SetNumberEditorInterval( 1 );
      textLabel12.SetNumberEditorUsesMouseWheel( false );
      textLabel12.SetHasCustomTextHoverColor( false );
      textLabel12.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel12.SetFont( "<Sans-Serif>", 14, false, false );

      analogMeterExample = new VoltageAnalogVUMeter( "analogMeterExample", "analogMeterExample", this );
      AddComponent( analogMeterExample );
      analogMeterExample.SetWantsMouseNotifications( false );
      analogMeterExample.SetPosition( 323, 86 );
      analogMeterExample.SetSize( 84, 46 );
      analogMeterExample.SetSkin( "Analog Black" );

      textLabel13 = new VoltageLabel( "textLabel13", "textLabel13", this, "Analog/VU Meter" );
      AddComponent( textLabel13 );
      textLabel13.SetWantsMouseNotifications( false );
      textLabel13.SetPosition( 224, 97 );
      textLabel13.SetSize( 80, 21 );
      textLabel13.SetEditable( false, false );
      textLabel13.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel13.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel13.SetColor( new Color( 198, 198, 198, 255 ) );
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

      animationExample = new VoltageImage( "animationExample", "animationExample", this, true );
      AddComponent( animationExample );
      animationExample.SetWantsMouseNotifications( false );
      animationExample.SetPosition( 322, 134 );
      animationExample.SetSize( 85, 47 );
      animationExample.SetCurrentImage( "BouncingBall.0001.png" );
      animationExample.SetAnimationSpeed( 100, true );
      animationExample.AddNewImage( "BouncingBall.0002.png" );
      animationExample.AddNewImage( "BouncingBall.0003.png" );
      animationExample.AddNewImage( "BouncingBall.0004.png" );
      animationExample.AddNewImage( "BouncingBall.0005.png" );
      animationExample.AddNewImage( "BouncingBall.0006.png" );
      animationExample.AddNewImage( "BouncingBall.0007.png" );
      animationExample.AddNewImage( "BouncingBall.0008.png" );
      animationExample.AddNewImage( "BouncingBall.0009.png" );
      animationExample.AddNewImage( "BouncingBall.0010.png" );
      animationExample.AddNewImage( "BouncingBall.0011.png" );
      animationExample.AddNewImage( "BouncingBall.0012.png" );
      animationExample.AddNewImage( "BouncingBall.0013.png" );
      animationExample.AddNewImage( "BouncingBall.0014.png" );
      animationExample.AddNewImage( "BouncingBall.0015.png" );
      animationExample.AddNewImage( "BouncingBall.0016.png" );
      animationExample.AddNewImage( "BouncingBall.0017.png" );
      animationExample.AddNewImage( "BouncingBall.0018.png" );
      animationExample.AddNewImage( "BouncingBall.0019.png" );
      animationExample.AddNewImage( "BouncingBall.0020.png" );
      animationExample.AddNewImage( "BouncingBall.0021.png" );
      animationExample.AddNewImage( "BouncingBall.0022.png" );
      animationExample.AddNewImage( "BouncingBall.0023.png" );
      animationExample.AddNewImage( "BouncingBall.0024.png" );
      animationExample.AddNewImage( "BouncingBall.0025.png" );
      animationExample.AddNewImage( "BouncingBall.0026.png" );
      animationExample.AddNewImage( "BouncingBall.0027.png" );
      animationExample.AddNewImage( "BouncingBall.0028.png" );
      animationExample.AddNewImage( "BouncingBall.0029.png" );
      animationExample.AddNewImage( "BouncingBall.0030.png" );
      animationExample.AddNewImage( "BouncingBall.0031.png" );
      animationExample.AddNewImage( "BouncingBall.0032.png" );
      animationExample.AddNewImage( "BouncingBall.0033.png" );
      animationExample.AddNewImage( "BouncingBall.0034.png" );
      animationExample.AddNewImage( "BouncingBall.0035.png" );
      animationExample.AddNewImage( "BouncingBall.0036.png" );

      textLabel14 = new VoltageLabel( "textLabel14", "textLabel14", this, "Animation:" );
      AddComponent( textLabel14 );
      textLabel14.SetWantsMouseNotifications( false );
      textLabel14.SetPosition( 224, 155 );
      textLabel14.SetSize( 80, 21 );
      textLabel14.SetEditable( false, false );
      textLabel14.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel14.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel14.SetColor( new Color( 198, 198, 198, 255 ) );
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

      canvasExample = new VoltageCanvas( "canvasExample", "canvasExample", this, 100, 50 );
      AddComponent( canvasExample );
      canvasExample.SetWantsMouseNotifications( false );
      canvasExample.SetPosition( 319, 183 );
      canvasExample.SetSize( 100, 50 );

      textLabel15 = new VoltageLabel( "textLabel15", "textLabel15", this, "Canvas:" );
      AddComponent( textLabel15 );
      textLabel15.SetWantsMouseNotifications( false );
      textLabel15.SetPosition( 225, 199 );
      textLabel15.SetSize( 80, 21 );
      textLabel15.SetEditable( false, false );
      textLabel15.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel15.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel15.SetColor( new Color( 198, 198, 198, 255 ) );
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

      counterExample = new VoltageDigitalCounter( "counterExample", "counterExample", this, 2 );
      AddComponent( counterExample );
      counterExample.SetWantsMouseNotifications( false );
      counterExample.SetPosition( 347, 241 );
      counterExample.SetSize( 42, 33 );
      counterExample.SetSkin( "Gray" );
      counterExample.SetJustificationFlags( VoltageDigitalCounter.Justification.Centered );

      textLabel16 = new VoltageLabel( "textLabel16", "textLabel16", this, "Digital Counter:" );
      AddComponent( textLabel16 );
      textLabel16.SetWantsMouseNotifications( false );
      textLabel16.SetPosition( 225, 244 );
      textLabel16.SetSize( 80, 21 );
      textLabel16.SetEditable( false, false );
      textLabel16.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel16.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel16.SetColor( new Color( 198, 198, 198, 255 ) );
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

      imageExample = new VoltageImage( "imageExample", "imageExample", this, false );
      AddComponent( imageExample );
      imageExample.SetWantsMouseNotifications( false );
      imageExample.SetPosition( 332, 302 );
      imageExample.SetSize( 67, 33 );
      imageExample.SetCurrentImage( "image image(37).png" );

      textLabel17 = new VoltageLabel( "textLabel17", "textLabel17", this, "Image:" );
      AddComponent( textLabel17 );
      textLabel17.SetWantsMouseNotifications( false );
      textLabel17.SetPosition( 225, 310 );
      textLabel17.SetSize( 80, 21 );
      textLabel17.SetEditable( false, false );
      textLabel17.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel17.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel17.SetColor( new Color( 198, 198, 198, 255 ) );
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

      LEDExample = new VoltageLED( "LEDExample", "LEDExample", this );
      AddComponent( LEDExample );
      LEDExample.SetWantsMouseNotifications( false );
      LEDExample.SetPosition( 564, 233 );
      LEDExample.SetSize( 11, 11 );
      LEDExample.SetSkin( "Red" );

      textLabel18 = new VoltageLabel( "textLabel18", "textLabel18", this, "LED:" );
      AddComponent( textLabel18 );
      textLabel18.SetWantsMouseNotifications( false );
      textLabel18.SetPosition( 477, 228 );
      textLabel18.SetSize( 80, 21 );
      textLabel18.SetEditable( false, false );
      textLabel18.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel18.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel18.SetColor( new Color( 198, 198, 198, 255 ) );
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

      meterExample = new VoltageVUMeter( "meterExample", "meterExample", this );
      AddComponent( meterExample );
      meterExample.SetWantsMouseNotifications( false );
      meterExample.SetPosition( 416, 88 );
      meterExample.SetSize( 15, 41 );
      meterExample.SetSkin( "Small Meter" );
      meterExample.SetLinearMode( false );

      textLabel19 = new VoltageLabel( "textLabel19", "textLabel19", this, "Ellipse / Line / Rectangle:" );
      AddComponent( textLabel19 );
      textLabel19.SetWantsMouseNotifications( false );
      textLabel19.SetPosition( 481, 89 );
      textLabel19.SetSize( 80, 39 );
      textLabel19.SetEditable( false, false );
      textLabel19.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel19.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel19.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel19.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel19.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel19.SetBorderSize( 1 );
      textLabel19.SetMultiLineEdit( true );
      textLabel19.SetIsNumberEditor( false );
      textLabel19.SetNumberEditorRange( 0, 100 );
      textLabel19.SetNumberEditorInterval( 1 );
      textLabel19.SetNumberEditorUsesMouseWheel( false );
      textLabel19.SetHasCustomTextHoverColor( false );
      textLabel19.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel19.SetFont( "<Sans-Serif>", 14, false, false );

      lineExample = new VoltageLine( "lineExample", "lineExample", this );
      AddComponent( lineExample );
      lineExample.SetWantsMouseNotifications( false );
      lineExample.SetLineColor( new Color( 255, 255, 20, 255 ) );
      lineExample.SetLineWidth( (float)2 );
      lineExample.SetStartPosition( 612, 80 );
      lineExample.SetEndPosition( 601, 117 );
      lineExample.SetHasArrow( false );
      lineExample.SetArrowWidth( (float)8 );
      lineExample.SetArrowLength( (float)12 );

      textLabel20 = new VoltageLabel( "textLabel20", "textLabel20", this, "Remote cannot access invisible knobs unless they become visible:" );
      AddComponent( textLabel20 );
      textLabel20.SetWantsMouseNotifications( false );
      textLabel20.SetPosition( 652, 40 );
      textLabel20.SetSize( 200, 36 );
      textLabel20.SetEditable( false, false );
      textLabel20.SetJustificationFlags( VoltageLabel.Justification.Left );
      textLabel20.SetJustificationFlags( VoltageLabel.Justification.Top );
      textLabel20.SetColor( new Color( 223, 223, 223, 255 ) );
      textLabel20.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel20.SetBorderColor( new Color( 183, 183, 183, 255 ) );
      textLabel20.SetBorderSize( 1 );
      textLabel20.SetMultiLineEdit( true );
      textLabel20.SetIsNumberEditor( false );
      textLabel20.SetNumberEditorRange( 0, 100 );
      textLabel20.SetNumberEditorInterval( 1 );
      textLabel20.SetNumberEditorUsesMouseWheel( false );
      textLabel20.SetHasCustomTextHoverColor( false );
      textLabel20.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel20.SetFont( "<Sans-Serif>", 14, false, false );

      invisibleKnobExample = new VoltageKnob( "invisibleKnobExample", "invisibleKnobExample", this, 0.0, 100, 50.00 );
      AddComponent( invisibleKnobExample );
      invisibleKnobExample.SetWantsMouseNotifications( false );
      invisibleKnobExample.SetPosition( 742, 113 );
      invisibleKnobExample.SetSize( 27, 27 );
      invisibleKnobExample.SetSkin( "Plastic White" );
      invisibleKnobExample.SetRange( 0.0, 100, 50.00, false, 0 );
      invisibleKnobExample.SetKnobParams( 215, 145 );
      invisibleKnobExample.DisplayValueInPercent( false );
      invisibleKnobExample.SetKnobAdjustsRing( true );

      textLabel22 = new VoltageLabel( "textLabel22", "textLabel22", this, "Invisible Knob:" );
      AddComponent( textLabel22 );
      textLabel22.SetWantsMouseNotifications( false );
      textLabel22.SetPosition( 652, 115 );
      textLabel22.SetSize( 80, 21 );
      textLabel22.SetEditable( false, false );
      textLabel22.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel22.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel22.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel22.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel22.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel22.SetBorderSize( 1 );
      textLabel22.SetMultiLineEdit( false );
      textLabel22.SetIsNumberEditor( false );
      textLabel22.SetNumberEditorRange( 0, 100 );
      textLabel22.SetNumberEditorInterval( 1 );
      textLabel22.SetNumberEditorUsesMouseWheel( false );
      textLabel22.SetHasCustomTextHoverColor( false );
      textLabel22.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel22.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel23 = new VoltageLabel( "textLabel23", "textLabel23", this, "(honest)" );
      AddComponent( textLabel23 );
      textLabel23.SetWantsMouseNotifications( false );
      textLabel23.SetPosition( 649, 128 );
      textLabel23.SetSize( 80, 21 );
      textLabel23.SetEditable( false, false );
      textLabel23.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel23.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel23.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel23.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel23.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel23.SetBorderSize( 1 );
      textLabel23.SetMultiLineEdit( false );
      textLabel23.SetIsNumberEditor( false );
      textLabel23.SetNumberEditorRange( 0, 100 );
      textLabel23.SetNumberEditorInterval( 1 );
      textLabel23.SetNumberEditorUsesMouseWheel( false );
      textLabel23.SetHasCustomTextHoverColor( false );
      textLabel23.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel23.SetFont( "<Sans-Serif>", 14, false, false );

      invisibleKnobValue = new VoltageLabel( "invisibleKnobValue", "invisibleKnobValue", this, "value" );
      AddComponent( invisibleKnobValue );
      invisibleKnobValue.SetWantsMouseNotifications( false );
      invisibleKnobValue.SetPosition( 712, 139 );
      invisibleKnobValue.SetSize( 80, 21 );
      invisibleKnobValue.SetEditable( false, false );
      invisibleKnobValue.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      invisibleKnobValue.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      invisibleKnobValue.SetColor( new Color( 198, 198, 198, 255 ) );
      invisibleKnobValue.SetBkColor( new Color( 65, 65, 65, 0 ) );
      invisibleKnobValue.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      invisibleKnobValue.SetBorderSize( 1 );
      invisibleKnobValue.SetMultiLineEdit( false );
      invisibleKnobValue.SetIsNumberEditor( false );
      invisibleKnobValue.SetNumberEditorRange( 0, 100 );
      invisibleKnobValue.SetNumberEditorInterval( 1 );
      invisibleKnobValue.SetNumberEditorUsesMouseWheel( false );
      invisibleKnobValue.SetHasCustomTextHoverColor( false );
      invisibleKnobValue.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      invisibleKnobValue.SetFont( "<Sans-Serif>", 14, false, false );

      textLabel24 = new VoltageLabel( "textLabel24", "textLabel24", this, "Text Label:" );
      AddComponent( textLabel24 );
      textLabel24.SetWantsMouseNotifications( false );
      textLabel24.SetPosition( 484, 144 );
      textLabel24.SetSize( 80, 39 );
      textLabel24.SetEditable( false, false );
      textLabel24.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabel24.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabel24.SetColor( new Color( 198, 198, 198, 255 ) );
      textLabel24.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabel24.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabel24.SetBorderSize( 1 );
      textLabel24.SetMultiLineEdit( true );
      textLabel24.SetIsNumberEditor( false );
      textLabel24.SetNumberEditorRange( 0, 100 );
      textLabel24.SetNumberEditorInterval( 1 );
      textLabel24.SetNumberEditorUsesMouseWheel( false );
      textLabel24.SetHasCustomTextHoverColor( false );
      textLabel24.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabel24.SetFont( "<Sans-Serif>", 14, false, false );

      textLabelExample = new VoltageLabel( "textLabelExample", "textLabelExample", this, "Text" );
      AddComponent( textLabelExample );
      textLabelExample.SetWantsMouseNotifications( false );
      textLabelExample.SetPosition( 568, 151 );
      textLabelExample.SetSize( 54, 30 );
      textLabelExample.SetEditable( false, false );
      textLabelExample.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      textLabelExample.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      textLabelExample.SetColor( new Color( 0, 0, 0, 255 ) );
      textLabelExample.SetBkColor( new Color( 65, 65, 65, 0 ) );
      textLabelExample.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      textLabelExample.SetBorderSize( 1 );
      textLabelExample.SetMultiLineEdit( false );
      textLabelExample.SetIsNumberEditor( false );
      textLabelExample.SetNumberEditorRange( 0, 100 );
      textLabelExample.SetNumberEditorInterval( 1 );
      textLabelExample.SetNumberEditorUsesMouseWheel( false );
      textLabelExample.SetHasCustomTextHoverColor( false );
      textLabelExample.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      textLabelExample.SetFont( "<Sans-Serif>", 14, false, false );

      adjustCanvas = new VoltageScrollbar( "adjustCanvas", "adjustCanvas", this, true, false );
      AddComponent( adjustCanvas );
      adjustCanvas.SetWantsMouseNotifications( false );
      adjustCanvas.SetPosition( 454, 186 );
      adjustCanvas.SetSize( 12, 45 );
      adjustCanvas.SetRange( 0, 100 );
      adjustCanvas.SetThumbSize( 1 );
      adjustCanvas.ScrollTo( 0 );
      adjustCanvas.SetThumbColor( 122, 122, 122, 255 );
      adjustCanvas.SetBackgroundColor( 20, 20, 20, 255 );
      adjustCanvas.SetThumbCornerSize( 4.00 );

      adjustAnimation = new VoltageScrollbar( "adjustAnimation", "adjustAnimation", this, true, false );
      AddComponent( adjustAnimation );
      adjustAnimation.SetWantsMouseNotifications( false );
      adjustAnimation.SetPosition( 454, 137 );
      adjustAnimation.SetSize( 12, 45 );
      adjustAnimation.SetRange( 0, 35 );
      adjustAnimation.SetThumbSize( 1 );
      adjustAnimation.ScrollTo( 0 );
      adjustAnimation.SetThumbColor( 122, 122, 122, 255 );
      adjustAnimation.SetBackgroundColor( 20, 20, 20, 255 );
      adjustAnimation.SetThumbCornerSize( 4.00 );

      adjustDigitalCounter = new VoltageScrollbar( "adjustDigitalCounter", "adjustDigitalCounter", this, true, false );
      AddComponent( adjustDigitalCounter );
      adjustDigitalCounter.SetWantsMouseNotifications( false );
      adjustDigitalCounter.SetPosition( 454, 237 );
      adjustDigitalCounter.SetSize( 12, 45 );
      adjustDigitalCounter.SetRange( 0, 99 );
      adjustDigitalCounter.SetThumbSize( 1 );
      adjustDigitalCounter.ScrollTo( 0 );
      adjustDigitalCounter.SetThumbColor( 122, 122, 122, 255 );
      adjustDigitalCounter.SetBackgroundColor( 20, 20, 20, 255 );
      adjustDigitalCounter.SetThumbCornerSize( 4.00 );

      adjustImage = new VoltageScrollbar( "adjustImage", "adjustImage", this, true, false );
      AddComponent( adjustImage );
      adjustImage.SetWantsMouseNotifications( false );
      adjustImage.SetPosition( 454, 296 );
      adjustImage.SetSize( 12, 45 );
      adjustImage.SetRange( 0, 3 );
      adjustImage.SetThumbSize( 1 );
      adjustImage.ScrollTo( 0 );
      adjustImage.SetThumbColor( 122, 122, 122, 255 );
      adjustImage.SetBackgroundColor( 20, 20, 20, 255 );
      adjustImage.SetThumbCornerSize( 4.00 );

      adjustInvisibleKnob = new VoltageScrollbar( "adjustInvisibleKnob", "adjustInvisibleKnob", this, true, false );
      AddComponent( adjustInvisibleKnob );
      adjustInvisibleKnob.SetWantsMouseNotifications( false );
      adjustInvisibleKnob.SetPosition( 836, 111 );
      adjustInvisibleKnob.SetSize( 12, 45 );
      adjustInvisibleKnob.SetRange( 0, 10 );
      adjustInvisibleKnob.SetThumbSize( 1 );
      adjustInvisibleKnob.ScrollTo( 0 );
      adjustInvisibleKnob.SetThumbColor( 122, 122, 122, 255 );
      adjustInvisibleKnob.SetBackgroundColor( 20, 20, 20, 255 );
      adjustInvisibleKnob.SetThumbCornerSize( 4.00 );

      adjustVUMeter = new VoltageScrollbar( "adjustVUMeter", "adjustVUMeter", this, true, false );
      AddComponent( adjustVUMeter );
      adjustVUMeter.SetWantsMouseNotifications( false );
      adjustVUMeter.SetPosition( 454, 86 );
      adjustVUMeter.SetSize( 12, 45 );
      adjustVUMeter.SetRange( 0, 5 );
      adjustVUMeter.SetThumbSize( 1 );
      adjustVUMeter.ScrollTo( 0 );
      adjustVUMeter.SetThumbColor( 122, 122, 122, 255 );
      adjustVUMeter.SetBackgroundColor( 20, 20, 20, 255 );
      adjustVUMeter.SetThumbCornerSize( 4.00 );

      selectedImageText = new VoltageLabel( "selectedImageText", "selectedImageText", this, "Image:" );
      AddComponent( selectedImageText );
      selectedImageText.SetWantsMouseNotifications( false );
      selectedImageText.SetPosition( 330, 336 );
      selectedImageText.SetSize( 80, 21 );
      selectedImageText.SetEditable( false, false );
      selectedImageText.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      selectedImageText.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      selectedImageText.SetColor( new Color( 198, 198, 198, 255 ) );
      selectedImageText.SetBkColor( new Color( 65, 65, 65, 0 ) );
      selectedImageText.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      selectedImageText.SetBorderSize( 1 );
      selectedImageText.SetMultiLineEdit( false );
      selectedImageText.SetIsNumberEditor( false );
      selectedImageText.SetNumberEditorRange( 0, 100 );
      selectedImageText.SetNumberEditorInterval( 1 );
      selectedImageText.SetNumberEditorUsesMouseWheel( false );
      selectedImageText.SetHasCustomTextHoverColor( false );
      selectedImageText.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      selectedImageText.SetFont( "<Sans-Serif>", 14, false, false );

      showHideInvisibleKnob = new VoltageButton( "showHideInvisibleKnob", "showHideInvisibleKnob", this );
      AddComponent( showHideInvisibleKnob );
      showHideInvisibleKnob.SetWantsMouseNotifications( false );
      showHideInvisibleKnob.SetPosition( 721, 158 );
      showHideInvisibleKnob.SetSize( 60, 60 );
      showHideInvisibleKnob.SetSkin( "Large Rounded Square" );
      showHideInvisibleKnob.ShowOverlay( true );
      showHideInvisibleKnob.SetOverlayText( "Show" );
      showHideInvisibleKnob.SetOverlayTextFont( "<Sans-Serif>", 14, false, false );
      showHideInvisibleKnob.SetOverlayTextColor( new Color( 0, 0, 0 ) );
      showHideInvisibleKnob.SetOverlayArea( 0, 0, 0, 0 );
      showHideInvisibleKnob.SetOverlayTextJustification( VoltageButton.Justification.Centered );
      showHideInvisibleKnob.SetAutoRepeat( false );



      canBeBypassed = false;
      SetSkin( "3ca5724b5f92420abf18e685b05e12af" );
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

      invisibleKnobExample.SetVisible(false);

      SetVUMeterValue(adjustVUMeter.GetRangeStart());

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
      
      String componentName = "";
      if (component != null)
         componentName = component.componentName;
      // Log("Notify:" + notification + " " + componentName + " " + component + " " + doubleValue + " " + object);
      
      switch( notification )
      {
         case Knob_Changed:   // doubleValue is the new VoltageKnob value
         {
            if (component == invisibleKnobExample) {
               invisibleKnobValue.SetText("" + invisibleKnobExample.GetValue());
            }
         }
         break;
      
         case Slider_Changed:   // doubleValue is the new slider value
         {
         }
         break;
      
         case Button_Changed:   // doubleValue is the new button/toggle button value
         {
            if (component == showHideInvisibleKnob && doubleValue > 0) {
               invisibleKnobExample.SetVisible(!invisibleKnobExample.IsVisible());
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
         }
         break;
      
         case Canvas_Painting:   // About to paint canvas.  object is a java.awt.Rectangle with painting boundaries
         {
            
            if (component == canvasExample) {
               // Paint the cursor position on the example canvas
               Graphics2D g = canvasExample.GetGraphics();
               int canvasWidth = canvasExample.GetBitmapWidth();
               int canvasHeight = canvasExample.GetBitmapHeight();
               // Clear the canvas
               g.setColor(Color.BLACK);
               g.fillRect(0,0,canvasWidth,canvasHeight);
               // Draw the frame
               g.setColor(Color.BLUE);
               g.drawRect(0,0,canvasWidth-1, canvasHeight - 1);
               // Draw the cursor
               g.setColor(Color.WHITE);
               g.drawLine((int)state.exampleCanvasCursorPositionX,0,(int)state.exampleCanvasCursorPositionX, canvasHeight - 1);
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
            if (component == adjustVUMeter) {
               SetVUMeterValue(longValue);
               // analogMeterExample.SetValue(longValue); 
               // meterExample.SetValue(longValue);
            } else if (component == adjustAnimation) {
               animationExample.SetCurrentFrame((int)longValue);
            } else if (component == adjustCanvas) {
               SetExampleCanvasCursorPositionX((int)longValue);
               canvasExample.Invalidate();
            } else if (component == adjustDigitalCounter) {
               counterExample.SetValue(longValue);
            } else if (component == adjustImage) {
               String[] images = new String[] {"SampleImage.png", "BouncingBall.0010.png", "BouncingBall.0011.png", "BouncingBall.0012.png"};
               selectedImageText.SetText(images[(int)longValue]);
               imageExample.SetCurrentImage(images[(int)longValue]);
               // NOTE: seems to be required to force redraw
               imageExample.SetCurrentFrame(0);
            } else if (component == adjustInvisibleKnob) {
               invisibleKnobExample.SetValue((int)longValue);
            }
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

      UndoCommand(undoType, newValue, optionalObject);

      // Log("OnUndoRedo" + undoType + " " + newValue + " " + optionalObject);

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

      // Log("GetStateInformation");
      
      
      final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream objectOutputStream = null;
      try {
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(state);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
      } catch (Exception ex) {
         // do nothing
         Log(ex.toString());
      } finally {
        try {
          byteArrayOutputStream.close();
        } catch (IOException ex) {
          // ignore close exception
        }
      }

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

      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stateInfo);
      ObjectInputStream objectInputStream = null;
      try {
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        state = (ModuleState)objectInputStream.readObject(); 
      } catch(Exception ex) {
         Log(ex.toString());
      } finally {
        try {
          if (objectInputStream != null) {
            objectInputStream.close();
          }
        } catch (IOException ex) {
          Log(ex.toString());
        }
      }
      
      SetVUMeterValue(state.vuMeterValue);
      SetExampleCanvasCursorPositionX(state.exampleCanvasCursorPositionX);

      // Log("SetStateInformation");

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
   private VoltageButton showHideInvisibleKnob;
   private VoltageLabel selectedImageText;
   private VoltageScrollbar adjustVUMeter;
   private VoltageScrollbar adjustInvisibleKnob;
   private VoltageScrollbar adjustImage;
   private VoltageScrollbar adjustDigitalCounter;
   private VoltageScrollbar adjustAnimation;
   private VoltageScrollbar adjustCanvas;
   private VoltageLabel textLabelExample;
   private VoltageLabel textLabel24;
   private VoltageLabel invisibleKnobValue;
   private VoltageLabel textLabel23;
   private VoltageLabel textLabel22;
   private VoltageKnob invisibleKnobExample;
   private VoltageLabel textLabel20;
   private VoltageLine lineExample;
   private VoltageLabel textLabel19;
   private VoltageVUMeter meterExample;
   private VoltageLabel textLabel18;
   private VoltageLED LEDExample;
   private VoltageLabel textLabel17;
   private VoltageImage imageExample;
   private VoltageLabel textLabel16;
   private VoltageDigitalCounter counterExample;
   private VoltageLabel textLabel15;
   private VoltageCanvas canvasExample;
   private VoltageLabel textLabel14;
   private VoltageImage animationExample;
   private VoltageLabel textLabel13;
   private VoltageAnalogVUMeter analogMeterExample;
   private VoltageLabel textLabel12;
   private VoltageLabel textLabel11;
   private VoltageToggle toggleButton2Example;
   private VoltageToggle toggleButton1Example;
   private VoltageLabel textLabel10;
   private VoltageSwitch switchExample;
   private VoltageLabel textLabel9;
   private VoltageSlider sliderExample;
   private VoltageLabel textLabel8;
   private VoltageScrollbar scrollbarExample;
   private VoltagePolyJack polyOutputJackExample;
   private VoltageAudioJack outputJackExample;
   private VoltagePolyJack polyInputJackExample;
   private VoltageAudioJack inputJackExample;
   private VoltageLabel textLabel7;
   private VoltageLabel textLabel6;
   private VoltageMidiJack midiOutputJackExample;
   private VoltageMidiJack midiInputJackExample;
   private VoltageLabel textLabel5;
   private VoltageLabel textLabel4;
   private VoltageKnob visibleKnobExample;
   private VoltageLabel editableTextExample;
   private VoltageLabel textLabel3;
   private VoltageLabel textLabel2;
   private VoltageLabel titleLabel;
   private VoltageEllipse ellipseExample;
   private VoltageRectangle rectangleExample;


   //[user-code-and-variables]    Add your own variables and functions here

   // todo: These should be state setting - e.g. SetAllControls to produce a single Undo statement.

   // Set the VU Meter Value
   private void SetVUMeterValue(double newValue) {
      CreateUndoNode("VUMeterValue", "Change VU Meter Value", state.vuMeterValue, newValue);
      if (adjustVUMeter.GetPosition() != newValue)
         adjustVUMeter.ScrollTo((int)newValue);
      if (state.vuMeterValue != newValue)
         state.vuMeterValue = newValue;
         
      if (analogMeterExample.GetValue() != newValue)
         analogMeterExample.SetValue(newValue); 
         
      if (meterExample.GetValue() != newValue)
         meterExample.SetValue(newValue);

   }

   // Set the canvas cursor position
   private void SetExampleCanvasCursorPositionX(double newValue) {
      CreateUndoNode("ExampleCanvasCursorPositionX", "Change image X cursor position", state.exampleCanvasCursorPositionX, newValue);
      if (adjustCanvas.GetPosition() != newValue) {
         adjustCanvas.ScrollTo((int)newValue);
         canvasExample.Invalidate();
      }
      if (state.exampleCanvasCursorPositionX != newValue)
         state.exampleCanvasCursorPositionX = newValue;
      
   }
   
   // process the undo
   private void UndoCommand(String undoType, double newValue, Object optionalObject) {
      switch (undoType) {
         case "ExampleCanvasCursorPositionX":
         {
            state.exampleCanvasCursorPositionX = newValue;
            adjustCanvas.ScrollTo((int)newValue);
            canvasExample.Invalidate();
            break;
         }
         case "VUMeterValue":
         {
            state.vuMeterValue = newValue;
            adjustVUMeter.ScrollTo((int)newValue);
            break;
         }
         default:
            Log("UndoCommand: MISSING undoType:"+undoType);
            break;
         
      }
   }


   // the module state
   private ModuleState state = new ModuleState();


   //[/user-code-and-variables]
}

 