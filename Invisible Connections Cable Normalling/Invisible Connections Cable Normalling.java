package au.com.arbuxmusic.invisibleconnections;


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
import java.util.ArrayList;
import javax.sound.midi.ShortMessage;

//[/user-imports]


public class InvisibleConnectionsCableNormalling extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

   public InvisibleConnectionsCableNormalling( long moduleID, VoltageObjects voltageObjects )
   {
      super( moduleID, voltageObjects, "Invisible Connections Cable Normalling", ModuleType.ModuleType_Utility, 4.4 );


      fromHostRectangle = new VoltageRectangle( "fromHostRectangle", "fromHostRectangle", this );
      AddComponent( fromHostRectangle );
      fromHostRectangle.SetWantsMouseNotifications( false );
      fromHostRectangle.SetPosition( 14, 111 );
      fromHostRectangle.SetSize( 61, 31 );
      fromHostRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      fromHostRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      fromHostRectangle.SetBorderSize( 4 );
      fromHostRectangle.SetCornerSize( 8 );

      moduleTitleLabel = new VoltageLabel( "moduleTitleLabel", "moduleTitleLabel", this, "Invisible Connections Cable Normalling" );
      AddComponent( moduleTitleLabel );
      moduleTitleLabel.SetWantsMouseNotifications( false );
      moduleTitleLabel.SetPosition( 0, 0 );
      moduleTitleLabel.SetSize( 317, 30 );
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

      midiInputJack1 = new VoltageMidiJack( "midiInputJack1", "midiInputJack1", this, JackType.JackType_MidiInput );
      AddComponent( midiInputJack1 );
      midiInputJack1.SetWantsMouseNotifications( false );
      midiInputJack1.SetPosition( 16, 35 );
      midiInputJack1.SetSize( 57, 57 );
      midiInputJack1.SetSkin( "MIDI Jack" );

      polyPitchInputJack = new VoltagePolyJack( "polyPitchInputJack", "polyPitchInputJack", this, JackType.JackType_PolyInput );
      AddComponent( polyPitchInputJack );
      polyPitchInputJack.SetWantsMouseNotifications( false );
      polyPitchInputJack.SetPosition( 111, 53 );
      polyPitchInputJack.SetSize( 25, 25 );
      polyPitchInputJack.SetSkin( "Poly Jack Straight" );

      pitchInputJack = new VoltageAudioJack( "pitchInputJack", "pitchInputJack", this, JackType.JackType_AudioInput );
      AddComponent( pitchInputJack );
      pitchInputJack.SetWantsMouseNotifications( false );
      pitchInputJack.SetPosition( 25, 160 );
      pitchInputJack.SetSize( 37, 37 );
      pitchInputJack.SetSkin( "Jack Straight" );

      fromHostLabel = new VoltageLabel( "fromHostLabel", "fromHostLabel", this, "From Host" );
      AddComponent( fromHostLabel );
      fromHostLabel.SetWantsMouseNotifications( false );
      fromHostLabel.SetPosition( 21, 112 );
      fromHostLabel.SetSize( 48, 30 );
      fromHostLabel.SetEditable( false, false );
      fromHostLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      fromHostLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      fromHostLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      fromHostLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      fromHostLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      fromHostLabel.SetBorderSize( 1 );
      fromHostLabel.SetMultiLineEdit( false );
      fromHostLabel.SetIsNumberEditor( false );
      fromHostLabel.SetNumberEditorRange( 0, 100 );
      fromHostLabel.SetNumberEditorInterval( 1 );
      fromHostLabel.SetNumberEditorUsesMouseWheel( false );
      fromHostLabel.SetHasCustomTextHoverColor( false );
      fromHostLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      fromHostLabel.SetFont( "<Sans-Serif>", 12, false, false );

      polyGateInputJack = new VoltagePolyJack( "polyGateInputJack", "polyGateInputJack", this, JackType.JackType_PolyInput );
      AddComponent( polyGateInputJack );
      polyGateInputJack.SetWantsMouseNotifications( false );
      polyGateInputJack.SetPosition( 181, 53 );
      polyGateInputJack.SetSize( 25, 25 );
      polyGateInputJack.SetSkin( "Poly Jack Straight" );

      polyVelInputJack = new VoltagePolyJack( "polyVelInputJack", "polyVelInputJack", this, JackType.JackType_PolyInput );
      AddComponent( polyVelInputJack );
      polyVelInputJack.SetWantsMouseNotifications( false );
      polyVelInputJack.SetPosition( 251, 53 );
      polyVelInputJack.SetSize( 25, 25 );
      polyVelInputJack.SetSkin( "Poly Jack Straight" );

      polyVelRectangle = new VoltageRectangle( "polyVelRectangle", "polyVelRectangle", this );
      AddComponent( polyVelRectangle );
      polyVelRectangle.SetWantsMouseNotifications( false );
      polyVelRectangle.SetPosition( 232, 98 );
      polyVelRectangle.SetSize( 61, 31 );
      polyVelRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      polyVelRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      polyVelRectangle.SetBorderSize( 4 );
      polyVelRectangle.SetCornerSize( 8 );

      polyVelLabel = new VoltageLabel( "polyVelLabel", "polyVelLabel", this, "Poly Vel" );
      AddComponent( polyVelLabel );
      polyVelLabel.SetWantsMouseNotifications( false );
      polyVelLabel.SetPosition( 239, 99 );
      polyVelLabel.SetSize( 48, 30 );
      polyVelLabel.SetEditable( false, false );
      polyVelLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyVelLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyVelLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      polyVelLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyVelLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      polyVelLabel.SetBorderSize( 1 );
      polyVelLabel.SetMultiLineEdit( false );
      polyVelLabel.SetIsNumberEditor( false );
      polyVelLabel.SetNumberEditorRange( 0, 100 );
      polyVelLabel.SetNumberEditorInterval( 1 );
      polyVelLabel.SetNumberEditorUsesMouseWheel( false );
      polyVelLabel.SetHasCustomTextHoverColor( false );
      polyVelLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyVelLabel.SetFont( "<Sans-Serif>", 14, false, false );

      polyPitchRectangle = new VoltageRectangle( "polyPitchRectangle", "polyPitchRectangle", this );
      AddComponent( polyPitchRectangle );
      polyPitchRectangle.SetWantsMouseNotifications( false );
      polyPitchRectangle.SetPosition( 91, 97 );
      polyPitchRectangle.SetSize( 61, 31 );
      polyPitchRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      polyPitchRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      polyPitchRectangle.SetBorderSize( 4 );
      polyPitchRectangle.SetCornerSize( 8 );

      polyPitchLabel = new VoltageLabel( "polyPitchLabel", "polyPitchLabel", this, "Poly Pitch" );
      AddComponent( polyPitchLabel );
      polyPitchLabel.SetWantsMouseNotifications( false );
      polyPitchLabel.SetPosition( 98, 98 );
      polyPitchLabel.SetSize( 48, 30 );
      polyPitchLabel.SetEditable( false, false );
      polyPitchLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyPitchLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyPitchLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      polyPitchLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyPitchLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      polyPitchLabel.SetBorderSize( 1 );
      polyPitchLabel.SetMultiLineEdit( false );
      polyPitchLabel.SetIsNumberEditor( false );
      polyPitchLabel.SetNumberEditorRange( 0, 100 );
      polyPitchLabel.SetNumberEditorInterval( 1 );
      polyPitchLabel.SetNumberEditorUsesMouseWheel( false );
      polyPitchLabel.SetHasCustomTextHoverColor( false );
      polyPitchLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyPitchLabel.SetFont( "<Sans-Serif>", 12, false, false );

      polyGateRectangle = new VoltageRectangle( "polyGateRectangle", "polyGateRectangle", this );
      AddComponent( polyGateRectangle );
      polyGateRectangle.SetWantsMouseNotifications( false );
      polyGateRectangle.SetPosition( 161, 97 );
      polyGateRectangle.SetSize( 61, 31 );
      polyGateRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      polyGateRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      polyGateRectangle.SetBorderSize( 4 );
      polyGateRectangle.SetCornerSize( 8 );

      polyGateLabel = new VoltageLabel( "polyGateLabel", "polyGateLabel", this, "Poly Gate" );
      AddComponent( polyGateLabel );
      polyGateLabel.SetWantsMouseNotifications( false );
      polyGateLabel.SetPosition( 168, 98 );
      polyGateLabel.SetSize( 48, 30 );
      polyGateLabel.SetEditable( false, false );
      polyGateLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyGateLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyGateLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      polyGateLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyGateLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      polyGateLabel.SetBorderSize( 1 );
      polyGateLabel.SetMultiLineEdit( false );
      polyGateLabel.SetIsNumberEditor( false );
      polyGateLabel.SetNumberEditorRange( 0, 100 );
      polyGateLabel.SetNumberEditorInterval( 1 );
      polyGateLabel.SetNumberEditorUsesMouseWheel( false );
      polyGateLabel.SetHasCustomTextHoverColor( false );
      polyGateLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyGateLabel.SetFont( "<Sans-Serif>", 12, false, false );

      pitchRectangle = new VoltageRectangle( "pitchRectangle", "pitchRectangle", this );
      AddComponent( pitchRectangle );
      pitchRectangle.SetWantsMouseNotifications( false );
      pitchRectangle.SetPosition( 14, 213 );
      pitchRectangle.SetSize( 61, 31 );
      pitchRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      pitchRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      pitchRectangle.SetBorderSize( 4 );
      pitchRectangle.SetCornerSize( 8 );

      pitchLabel = new VoltageLabel( "pitchLabel", "pitchLabel", this, "Pitch" );
      AddComponent( pitchLabel );
      pitchLabel.SetWantsMouseNotifications( false );
      pitchLabel.SetPosition( 21, 214 );
      pitchLabel.SetSize( 48, 30 );
      pitchLabel.SetEditable( false, false );
      pitchLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      pitchLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      pitchLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      pitchLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      pitchLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      pitchLabel.SetBorderSize( 1 );
      pitchLabel.SetMultiLineEdit( false );
      pitchLabel.SetIsNumberEditor( false );
      pitchLabel.SetNumberEditorRange( 0, 100 );
      pitchLabel.SetNumberEditorInterval( 1 );
      pitchLabel.SetNumberEditorUsesMouseWheel( false );
      pitchLabel.SetHasCustomTextHoverColor( false );
      pitchLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      pitchLabel.SetFont( "<Sans-Serif>", 14, false, false );

      gateInputJack = new VoltageAudioJack( "gateInputJack", "gateInputJack", this, JackType.JackType_AudioInput );
      AddComponent( gateInputJack );
      gateInputJack.SetWantsMouseNotifications( false );
      gateInputJack.SetPosition( 102, 160 );
      gateInputJack.SetSize( 37, 37 );
      gateInputJack.SetSkin( "Jack Straight" );

      gateRectangle = new VoltageRectangle( "gateRectangle", "gateRectangle", this );
      AddComponent( gateRectangle );
      gateRectangle.SetWantsMouseNotifications( false );
      gateRectangle.SetPosition( 91, 213 );
      gateRectangle.SetSize( 61, 31 );
      gateRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      gateRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      gateRectangle.SetBorderSize( 4 );
      gateRectangle.SetCornerSize( 8 );

      gateLabel = new VoltageLabel( "gateLabel", "gateLabel", this, "Gate" );
      AddComponent( gateLabel );
      gateLabel.SetWantsMouseNotifications( false );
      gateLabel.SetPosition( 98, 214 );
      gateLabel.SetSize( 48, 30 );
      gateLabel.SetEditable( false, false );
      gateLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      gateLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      gateLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      gateLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      gateLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      gateLabel.SetBorderSize( 1 );
      gateLabel.SetMultiLineEdit( false );
      gateLabel.SetIsNumberEditor( false );
      gateLabel.SetNumberEditorRange( 0, 100 );
      gateLabel.SetNumberEditorInterval( 1 );
      gateLabel.SetNumberEditorUsesMouseWheel( false );
      gateLabel.SetHasCustomTextHoverColor( false );
      gateLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      gateLabel.SetFont( "<Sans-Serif>", 14, false, false );

      trigRectangle = new VoltageRectangle( "trigRectangle", "trigRectangle", this );
      AddComponent( trigRectangle );
      trigRectangle.SetWantsMouseNotifications( false );
      trigRectangle.SetPosition( 161, 213 );
      trigRectangle.SetSize( 61, 31 );
      trigRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      trigRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      trigRectangle.SetBorderSize( 4 );
      trigRectangle.SetCornerSize( 8 );

      trigLabel = new VoltageLabel( "trigLabel", "trigLabel", this, "Trig" );
      AddComponent( trigLabel );
      trigLabel.SetWantsMouseNotifications( false );
      trigLabel.SetPosition( 168, 214 );
      trigLabel.SetSize( 48, 30 );
      trigLabel.SetEditable( false, false );
      trigLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      trigLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      trigLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      trigLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      trigLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      trigLabel.SetBorderSize( 1 );
      trigLabel.SetMultiLineEdit( false );
      trigLabel.SetIsNumberEditor( false );
      trigLabel.SetNumberEditorRange( 0, 100 );
      trigLabel.SetNumberEditorInterval( 1 );
      trigLabel.SetNumberEditorUsesMouseWheel( false );
      trigLabel.SetHasCustomTextHoverColor( false );
      trigLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      trigLabel.SetFont( "<Sans-Serif>", 14, false, false );

      trigInputJack = new VoltageAudioJack( "trigInputJack", "trigInputJack", this, JackType.JackType_AudioInput );
      AddComponent( trigInputJack );
      trigInputJack.SetWantsMouseNotifications( false );
      trigInputJack.SetPosition( 172, 160 );
      trigInputJack.SetSize( 37, 37 );
      trigInputJack.SetSkin( "Jack Straight" );

      velInputJack = new VoltageAudioJack( "velInputJack", "velInputJack", this, JackType.JackType_AudioInput );
      AddComponent( velInputJack );
      velInputJack.SetWantsMouseNotifications( false );
      velInputJack.SetPosition( 25, 260 );
      velInputJack.SetSize( 37, 37 );
      velInputJack.SetSkin( "Jack Straight" );

      velRectangle = new VoltageRectangle( "velRectangle", "velRectangle", this );
      AddComponent( velRectangle );
      velRectangle.SetWantsMouseNotifications( false );
      velRectangle.SetPosition( 14, 312 );
      velRectangle.SetSize( 61, 31 );
      velRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      velRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      velRectangle.SetBorderSize( 4 );
      velRectangle.SetCornerSize( 8 );

      velLabel = new VoltageLabel( "velLabel", "velLabel", this, "Vel" );
      AddComponent( velLabel );
      velLabel.SetWantsMouseNotifications( false );
      velLabel.SetPosition( 21, 313 );
      velLabel.SetSize( 48, 30 );
      velLabel.SetEditable( false, false );
      velLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      velLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      velLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      velLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      velLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      velLabel.SetBorderSize( 1 );
      velLabel.SetMultiLineEdit( false );
      velLabel.SetIsNumberEditor( false );
      velLabel.SetNumberEditorRange( 0, 100 );
      velLabel.SetNumberEditorInterval( 1 );
      velLabel.SetNumberEditorUsesMouseWheel( false );
      velLabel.SetHasCustomTextHoverColor( false );
      velLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      velLabel.SetFont( "<Sans-Serif>", 14, false, false );

      aftertouchRectangle = new VoltageRectangle( "aftertouchRectangle", "aftertouchRectangle", this );
      AddComponent( aftertouchRectangle );
      aftertouchRectangle.SetWantsMouseNotifications( false );
      aftertouchRectangle.SetPosition( 90, 311 );
      aftertouchRectangle.SetSize( 61, 31 );
      aftertouchRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      aftertouchRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      aftertouchRectangle.SetBorderSize( 4 );
      aftertouchRectangle.SetCornerSize( 8 );

      aftertouchLabel = new VoltageLabel( "aftertouchLabel", "aftertouchLabel", this, "Aftertouch" );
      AddComponent( aftertouchLabel );
      aftertouchLabel.SetWantsMouseNotifications( false );
      aftertouchLabel.SetPosition( 97, 312 );
      aftertouchLabel.SetSize( 48, 30 );
      aftertouchLabel.SetEditable( false, false );
      aftertouchLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      aftertouchLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      aftertouchLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      aftertouchLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      aftertouchLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      aftertouchLabel.SetBorderSize( 1 );
      aftertouchLabel.SetMultiLineEdit( false );
      aftertouchLabel.SetIsNumberEditor( false );
      aftertouchLabel.SetNumberEditorRange( 0, 100 );
      aftertouchLabel.SetNumberEditorInterval( 1 );
      aftertouchLabel.SetNumberEditorUsesMouseWheel( false );
      aftertouchLabel.SetHasCustomTextHoverColor( false );
      aftertouchLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      aftertouchLabel.SetFont( "<Sans-Serif>", 14, false, false );

      aftertouchInputJack = new VoltageAudioJack( "aftertouchInputJack", "aftertouchInputJack", this, JackType.JackType_AudioInput );
      AddComponent( aftertouchInputJack );
      aftertouchInputJack.SetWantsMouseNotifications( false );
      aftertouchInputJack.SetPosition( 101, 259 );
      aftertouchInputJack.SetSize( 37, 37 );
      aftertouchInputJack.SetSkin( "Jack Straight" );

      susInputJack = new VoltageAudioJack( "susInputJack", "susInputJack", this, JackType.JackType_AudioInput );
      AddComponent( susInputJack );
      susInputJack.SetWantsMouseNotifications( false );
      susInputJack.SetPosition( 171, 259 );
      susInputJack.SetSize( 37, 37 );
      susInputJack.SetSkin( "Jack Straight" );

      susRectangle = new VoltageRectangle( "susRectangle", "susRectangle", this );
      AddComponent( susRectangle );
      susRectangle.SetWantsMouseNotifications( false );
      susRectangle.SetPosition( 160, 311 );
      susRectangle.SetSize( 61, 31 );
      susRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      susRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      susRectangle.SetBorderSize( 4 );
      susRectangle.SetCornerSize( 8 );

      susLabel = new VoltageLabel( "susLabel", "susLabel", this, "Sus" );
      AddComponent( susLabel );
      susLabel.SetWantsMouseNotifications( false );
      susLabel.SetPosition( 167, 312 );
      susLabel.SetSize( 48, 30 );
      susLabel.SetEditable( false, false );
      susLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      susLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      susLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      susLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      susLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      susLabel.SetBorderSize( 1 );
      susLabel.SetMultiLineEdit( false );
      susLabel.SetIsNumberEditor( false );
      susLabel.SetNumberEditorRange( 0, 100 );
      susLabel.SetNumberEditorInterval( 1 );
      susLabel.SetNumberEditorUsesMouseWheel( false );
      susLabel.SetHasCustomTextHoverColor( false );
      susLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      susLabel.SetFont( "<Sans-Serif>", 14, false, false );

      bendRectangle = new VoltageRectangle( "bendRectangle", "bendRectangle", this );
      AddComponent( bendRectangle );
      bendRectangle.SetWantsMouseNotifications( false );
      bendRectangle.SetPosition( 232, 213 );
      bendRectangle.SetSize( 61, 31 );
      bendRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      bendRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      bendRectangle.SetBorderSize( 4 );
      bendRectangle.SetCornerSize( 8 );

      bendLabel = new VoltageLabel( "bendLabel", "bendLabel", this, "Bend" );
      AddComponent( bendLabel );
      bendLabel.SetWantsMouseNotifications( false );
      bendLabel.SetPosition( 239, 214 );
      bendLabel.SetSize( 48, 30 );
      bendLabel.SetEditable( false, false );
      bendLabel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      bendLabel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      bendLabel.SetColor( new Color( 255, 255, 255, 255 ) );
      bendLabel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      bendLabel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      bendLabel.SetBorderSize( 1 );
      bendLabel.SetMultiLineEdit( false );
      bendLabel.SetIsNumberEditor( false );
      bendLabel.SetNumberEditorRange( 0, 100 );
      bendLabel.SetNumberEditorInterval( 1 );
      bendLabel.SetNumberEditorUsesMouseWheel( false );
      bendLabel.SetHasCustomTextHoverColor( false );
      bendLabel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      bendLabel.SetFont( "<Sans-Serif>", 14, false, false );

      bendInputJack = new VoltageAudioJack( "bendInputJack", "bendInputJack", this, JackType.JackType_AudioInput );
      AddComponent( bendInputJack );
      bendInputJack.SetWantsMouseNotifications( false );
      bendInputJack.SetPosition( 243, 160 );
      bendInputJack.SetSize( 37, 37 );
      bendInputJack.SetSkin( "Jack Straight" );

      modWheelInputJack = new VoltageAudioJack( "modWheelInputJack", "modWheelInputJack", this, JackType.JackType_AudioInput );
      AddComponent( modWheelInputJack );
      modWheelInputJack.SetWantsMouseNotifications( false );
      modWheelInputJack.SetPosition( 244, 258 );
      modWheelInputJack.SetSize( 37, 37 );
      modWheelInputJack.SetSkin( "Jack Straight" );

      modWheelRectangle = new VoltageRectangle( "modWheelRectangle", "modWheelRectangle", this );
      AddComponent( modWheelRectangle );
      modWheelRectangle.SetWantsMouseNotifications( false );
      modWheelRectangle.SetPosition( 233, 311 );
      modWheelRectangle.SetSize( 61, 31 );
      modWheelRectangle.SetRectangleColor( new Color( 0, 0, 0, 0 ) );
      modWheelRectangle.SetBorderColor( new Color( 167, 167, 167, 255 ) );
      modWheelRectangle.SetBorderSize( 4 );
      modWheelRectangle.SetCornerSize( 8 );

      modWheel = new VoltageLabel( "modWheel", "modWheel", this, "Mod Wheel" );
      AddComponent( modWheel );
      modWheel.SetWantsMouseNotifications( false );
      modWheel.SetPosition( 240, 312 );
      modWheel.SetSize( 48, 30 );
      modWheel.SetEditable( false, false );
      modWheel.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      modWheel.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      modWheel.SetColor( new Color( 255, 255, 255, 255 ) );
      modWheel.SetBkColor( new Color( 65, 65, 65, 0 ) );
      modWheel.SetBorderColor( new Color( 0, 0, 0, 0 ) );
      modWheel.SetBorderSize( 1 );
      modWheel.SetMultiLineEdit( false );
      modWheel.SetIsNumberEditor( false );
      modWheel.SetNumberEditorRange( 0, 100 );
      modWheel.SetNumberEditorInterval( 1 );
      modWheel.SetNumberEditorUsesMouseWheel( false );
      modWheel.SetHasCustomTextHoverColor( false );
      modWheel.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      modWheel.SetFont( "<Sans-Serif>", 14, false, false );

      midiInputUpArrow = new VoltageImage( "midiInputUpArrow", "midiInputUpArrow", this, false );
      AddComponent( midiInputUpArrow );
      midiInputUpArrow.SetWantsMouseNotifications( false );
      midiInputUpArrow.SetPosition( 39, 95 );
      midiInputUpArrow.SetSize( 11, 11 );
      midiInputUpArrow.SetCurrentImage( "image image(92).svg" );

      image2 = new VoltageImage( "image2", "image2", this, false );
      AddComponent( image2 );
      image2.SetWantsMouseNotifications( false );
      image2.SetPosition( 117, 82 );
      image2.SetSize( 11, 11 );
      image2.SetCurrentImage( "image image(93).svg" );

      image3 = new VoltageImage( "image3", "image3", this, false );
      AddComponent( image3 );
      image3.SetWantsMouseNotifications( false );
      image3.SetPosition( 188, 83 );
      image3.SetSize( 11, 11 );
      image3.SetCurrentImage( "image image(94).svg" );

      image4 = new VoltageImage( "image4", "image4", this, false );
      AddComponent( image4 );
      image4.SetWantsMouseNotifications( false );
      image4.SetPosition( 258, 84 );
      image4.SetSize( 11, 11 );
      image4.SetCurrentImage( "image image(95).svg" );

      image5 = new VoltageImage( "image5", "image5", this, false );
      AddComponent( image5 );
      image5.SetWantsMouseNotifications( false );
      image5.SetPosition( 256, 197 );
      image5.SetSize( 11, 11 );
      image5.SetCurrentImage( "image image(96).svg" );

      image6 = new VoltageImage( "image6", "image6", this, false );
      AddComponent( image6 );
      image6.SetWantsMouseNotifications( false );
      image6.SetPosition( 185, 197 );
      image6.SetSize( 11, 11 );
      image6.SetCurrentImage( "image image(97).svg" );

      image7 = new VoltageImage( "image7", "image7", this, false );
      AddComponent( image7 );
      image7.SetWantsMouseNotifications( false );
      image7.SetPosition( 114, 197 );
      image7.SetSize( 11, 11 );
      image7.SetCurrentImage( "image image(98).svg" );

      image8 = new VoltageImage( "image8", "image8", this, false );
      AddComponent( image8 );
      image8.SetWantsMouseNotifications( false );
      image8.SetPosition( 38, 197 );
      image8.SetSize( 11, 11 );
      image8.SetCurrentImage( "image image(99).svg" );

      image9 = new VoltageImage( "image9", "image9", this, false );
      AddComponent( image9 );
      image9.SetWantsMouseNotifications( false );
      image9.SetPosition( 37, 295 );
      image9.SetSize( 11, 11 );
      image9.SetCurrentImage( "image image(100).svg" );

      image10 = new VoltageImage( "image10", "image10", this, false );
      AddComponent( image10 );
      image10.SetWantsMouseNotifications( false );
      image10.SetPosition( 115, 295 );
      image10.SetSize( 11, 11 );
      image10.SetCurrentImage( "image image(101).svg" );

      image11 = new VoltageImage( "image11", "image11", this, false );
      AddComponent( image11 );
      image11.SetWantsMouseNotifications( false );
      image11.SetPosition( 185, 295 );
      image11.SetSize( 11, 11 );
      image11.SetCurrentImage( "image image(102).svg" );

      image12 = new VoltageImage( "image12", "image12", this, false );
      AddComponent( image12 );
      image12.SetWantsMouseNotifications( false );
      image12.SetPosition( 257, 296 );
      image12.SetSize( 11, 11 );
      image12.SetCurrentImage( "image image(103).svg" );

      fromHostCounter = new VoltageLabel( "fromHostCounter", "fromHostCounter", this, "0" );
      AddComponent( fromHostCounter );
      fromHostCounter.SetWantsMouseNotifications( false );
      fromHostCounter.SetPosition( 19, 137 );
      fromHostCounter.SetSize( 51, 22 );
      fromHostCounter.SetEditable( false, false );
      fromHostCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      fromHostCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      fromHostCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      fromHostCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      fromHostCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      fromHostCounter.SetBorderSize( 1 );
      fromHostCounter.SetMultiLineEdit( false );
      fromHostCounter.SetIsNumberEditor( false );
      fromHostCounter.SetNumberEditorRange( 0, 100 );
      fromHostCounter.SetNumberEditorInterval( 1 );
      fromHostCounter.SetNumberEditorUsesMouseWheel( false );
      fromHostCounter.SetHasCustomTextHoverColor( false );
      fromHostCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      fromHostCounter.SetFont( "<Sans-Serif>", 14, false, false );

      polyPitchCounter = new VoltageLabel( "polyPitchCounter", "polyPitchCounter", this, "0" );
      AddComponent( polyPitchCounter );
      polyPitchCounter.SetWantsMouseNotifications( false );
      polyPitchCounter.SetPosition( 100, 126 );
      polyPitchCounter.SetSize( 51, 22 );
      polyPitchCounter.SetEditable( false, false );
      polyPitchCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyPitchCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyPitchCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      polyPitchCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyPitchCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      polyPitchCounter.SetBorderSize( 1 );
      polyPitchCounter.SetMultiLineEdit( false );
      polyPitchCounter.SetIsNumberEditor( false );
      polyPitchCounter.SetNumberEditorRange( 0, 100 );
      polyPitchCounter.SetNumberEditorInterval( 1 );
      polyPitchCounter.SetNumberEditorUsesMouseWheel( false );
      polyPitchCounter.SetHasCustomTextHoverColor( false );
      polyPitchCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyPitchCounter.SetFont( "<Sans-Serif>", 14, false, false );

      polyGateCounter = new VoltageLabel( "polyGateCounter", "polyGateCounter", this, "0" );
      AddComponent( polyGateCounter );
      polyGateCounter.SetWantsMouseNotifications( false );
      polyGateCounter.SetPosition( 168, 125 );
      polyGateCounter.SetSize( 51, 22 );
      polyGateCounter.SetEditable( false, false );
      polyGateCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyGateCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyGateCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      polyGateCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyGateCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      polyGateCounter.SetBorderSize( 1 );
      polyGateCounter.SetMultiLineEdit( false );
      polyGateCounter.SetIsNumberEditor( false );
      polyGateCounter.SetNumberEditorRange( 0, 100 );
      polyGateCounter.SetNumberEditorInterval( 1 );
      polyGateCounter.SetNumberEditorUsesMouseWheel( false );
      polyGateCounter.SetHasCustomTextHoverColor( false );
      polyGateCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyGateCounter.SetFont( "<Sans-Serif>", 14, false, false );

      polyVelCounter = new VoltageLabel( "polyVelCounter", "polyVelCounter", this, "0" );
      AddComponent( polyVelCounter );
      polyVelCounter.SetWantsMouseNotifications( false );
      polyVelCounter.SetPosition( 237, 127 );
      polyVelCounter.SetSize( 51, 22 );
      polyVelCounter.SetEditable( false, false );
      polyVelCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      polyVelCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      polyVelCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      polyVelCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      polyVelCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      polyVelCounter.SetBorderSize( 1 );
      polyVelCounter.SetMultiLineEdit( false );
      polyVelCounter.SetIsNumberEditor( false );
      polyVelCounter.SetNumberEditorRange( 0, 100 );
      polyVelCounter.SetNumberEditorInterval( 1 );
      polyVelCounter.SetNumberEditorUsesMouseWheel( false );
      polyVelCounter.SetHasCustomTextHoverColor( false );
      polyVelCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      polyVelCounter.SetFont( "<Sans-Serif>", 14, false, false );

      bendCounter = new VoltageLabel( "bendCounter", "bendCounter", this, "0" );
      AddComponent( bendCounter );
      bendCounter.SetWantsMouseNotifications( false );
      bendCounter.SetPosition( 239, 240 );
      bendCounter.SetSize( 51, 22 );
      bendCounter.SetEditable( false, false );
      bendCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      bendCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      bendCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      bendCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      bendCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      bendCounter.SetBorderSize( 1 );
      bendCounter.SetMultiLineEdit( false );
      bendCounter.SetIsNumberEditor( false );
      bendCounter.SetNumberEditorRange( 0, 100 );
      bendCounter.SetNumberEditorInterval( 1 );
      bendCounter.SetNumberEditorUsesMouseWheel( false );
      bendCounter.SetHasCustomTextHoverColor( false );
      bendCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      bendCounter.SetFont( "<Sans-Serif>", 14, false, false );

      trigCounter = new VoltageLabel( "trigCounter", "trigCounter", this, "0" );
      AddComponent( trigCounter );
      trigCounter.SetWantsMouseNotifications( false );
      trigCounter.SetPosition( 167, 240 );
      trigCounter.SetSize( 51, 22 );
      trigCounter.SetEditable( false, false );
      trigCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      trigCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      trigCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      trigCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      trigCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      trigCounter.SetBorderSize( 1 );
      trigCounter.SetMultiLineEdit( false );
      trigCounter.SetIsNumberEditor( false );
      trigCounter.SetNumberEditorRange( 0, 100 );
      trigCounter.SetNumberEditorInterval( 1 );
      trigCounter.SetNumberEditorUsesMouseWheel( false );
      trigCounter.SetHasCustomTextHoverColor( false );
      trigCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      trigCounter.SetFont( "<Sans-Serif>", 14, false, false );

      gateCounter = new VoltageLabel( "gateCounter", "gateCounter", this, "0" );
      AddComponent( gateCounter );
      gateCounter.SetWantsMouseNotifications( false );
      gateCounter.SetPosition( 97, 240 );
      gateCounter.SetSize( 51, 22 );
      gateCounter.SetEditable( false, false );
      gateCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      gateCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      gateCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      gateCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      gateCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      gateCounter.SetBorderSize( 1 );
      gateCounter.SetMultiLineEdit( false );
      gateCounter.SetIsNumberEditor( false );
      gateCounter.SetNumberEditorRange( 0, 100 );
      gateCounter.SetNumberEditorInterval( 1 );
      gateCounter.SetNumberEditorUsesMouseWheel( false );
      gateCounter.SetHasCustomTextHoverColor( false );
      gateCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      gateCounter.SetFont( "<Sans-Serif>", 14, false, false );

      pitchCounter = new VoltageLabel( "pitchCounter", "pitchCounter", this, "0" );
      AddComponent( pitchCounter );
      pitchCounter.SetWantsMouseNotifications( false );
      pitchCounter.SetPosition( 22, 241 );
      pitchCounter.SetSize( 51, 22 );
      pitchCounter.SetEditable( false, false );
      pitchCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      pitchCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      pitchCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      pitchCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      pitchCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      pitchCounter.SetBorderSize( 1 );
      pitchCounter.SetMultiLineEdit( false );
      pitchCounter.SetIsNumberEditor( false );
      pitchCounter.SetNumberEditorRange( 0, 100 );
      pitchCounter.SetNumberEditorInterval( 1 );
      pitchCounter.SetNumberEditorUsesMouseWheel( false );
      pitchCounter.SetHasCustomTextHoverColor( false );
      pitchCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      pitchCounter.SetFont( "<Sans-Serif>", 14, false, false );

      velCounter = new VoltageLabel( "velCounter", "velCounter", this, "n/a" );
      AddComponent( velCounter );
      velCounter.SetWantsMouseNotifications( false );
      velCounter.SetPosition( 19, 338 );
      velCounter.SetSize( 51, 22 );
      velCounter.SetEditable( false, false );
      velCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      velCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      velCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      velCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      velCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      velCounter.SetBorderSize( 1 );
      velCounter.SetMultiLineEdit( false );
      velCounter.SetIsNumberEditor( false );
      velCounter.SetNumberEditorRange( 0, 100 );
      velCounter.SetNumberEditorInterval( 1 );
      velCounter.SetNumberEditorUsesMouseWheel( false );
      velCounter.SetHasCustomTextHoverColor( false );
      velCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      velCounter.SetFont( "<Sans-Serif>", 14, false, false );

      aftertouchCounter = new VoltageLabel( "aftertouchCounter", "aftertouchCounter", this, "0" );
      AddComponent( aftertouchCounter );
      aftertouchCounter.SetWantsMouseNotifications( false );
      aftertouchCounter.SetPosition( 97, 338 );
      aftertouchCounter.SetSize( 51, 22 );
      aftertouchCounter.SetEditable( false, false );
      aftertouchCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      aftertouchCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      aftertouchCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      aftertouchCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      aftertouchCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      aftertouchCounter.SetBorderSize( 1 );
      aftertouchCounter.SetMultiLineEdit( false );
      aftertouchCounter.SetIsNumberEditor( false );
      aftertouchCounter.SetNumberEditorRange( 0, 100 );
      aftertouchCounter.SetNumberEditorInterval( 1 );
      aftertouchCounter.SetNumberEditorUsesMouseWheel( false );
      aftertouchCounter.SetHasCustomTextHoverColor( false );
      aftertouchCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      aftertouchCounter.SetFont( "<Sans-Serif>", 14, false, false );

      susCounter = new VoltageLabel( "susCounter", "susCounter", this, "0" );
      AddComponent( susCounter );
      susCounter.SetWantsMouseNotifications( false );
      susCounter.SetPosition( 165, 338 );
      susCounter.SetSize( 51, 22 );
      susCounter.SetEditable( false, false );
      susCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      susCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      susCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      susCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      susCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      susCounter.SetBorderSize( 1 );
      susCounter.SetMultiLineEdit( false );
      susCounter.SetIsNumberEditor( false );
      susCounter.SetNumberEditorRange( 0, 100 );
      susCounter.SetNumberEditorInterval( 1 );
      susCounter.SetNumberEditorUsesMouseWheel( false );
      susCounter.SetHasCustomTextHoverColor( false );
      susCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      susCounter.SetFont( "<Sans-Serif>", 14, false, false );

      modWheelCounter = new VoltageLabel( "modWheelCounter", "modWheelCounter", this, "0" );
      AddComponent( modWheelCounter );
      modWheelCounter.SetWantsMouseNotifications( false );
      modWheelCounter.SetPosition( 238, 338 );
      modWheelCounter.SetSize( 51, 22 );
      modWheelCounter.SetEditable( false, false );
      modWheelCounter.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
      modWheelCounter.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
      modWheelCounter.SetColor( new Color( 0, 180, 255, 255 ) );
      modWheelCounter.SetBkColor( new Color( 65, 65, 65, 0 ) );
      modWheelCounter.SetBorderColor( new Color( 0, 158, 255, 0 ) );
      modWheelCounter.SetBorderSize( 1 );
      modWheelCounter.SetMultiLineEdit( false );
      modWheelCounter.SetIsNumberEditor( false );
      modWheelCounter.SetNumberEditorRange( 0, 100 );
      modWheelCounter.SetNumberEditorInterval( 1 );
      modWheelCounter.SetNumberEditorUsesMouseWheel( false );
      modWheelCounter.SetHasCustomTextHoverColor( false );
      modWheelCounter.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
      modWheelCounter.SetFont( "<Sans-Serif>", 14, false, false );



      canBeBypassed = true;
      SetSkin( "0e2de40010434168bca9b1b46c626949" );
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

      ResetConnectedStates();

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
            ResetConnectedStates();
         }
         break;
      
         case Jack_Disconnected:   // All cables have been disconnected from this jack
         {
            ResetConnectedStates();
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
            ResetConnectedStates();
         }
         break;
      
         case Variation_Loading_Start:    // sent when a variation is about to load
         {
         }
         break;
      
         case Variation_Loading_Finish:   // sent when a variation has just finished loading
         {
            ResetConnectedStates();
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
            ResetConnectedStates();
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

      // Get midi messages from either the connected jack
      // or the IO bus
      
      // ****** MIDI INPUT JACK
      //
      // If the midi input jack is connected, get the messages from that source
      // otherwise get the messages directly from the IO Panel.
      ArrayList<ShortMessage> midiMessages = 
         midiInputJack1.IsConnected() ? 
            midiInputJack1.GetMessages() :
            GetIOPanelMIDIMessages();
            
      // ****** GET IO PANEL Values for poly controls
      double polyPitch = 0, polyGate = 0, polyVel = 0;
      
      // This code doesn't do much useful, but does show you how to
      // differentiate between connected poly jacks and the default IO jack
      for (int channel = 0; channel < GetNumberOfPolyVoices(); channel++) {
      
         // Poly Pitch received from connected jack otherwise IO Panel
         polyPitch +=
            (polyPitchInputJack.IsConnected() ?
               polyPitchInputJack.GetPolyValue(channel) :
               GetIOPanelPolyValue(IOPanelPolyValues.IOPanel_PolyPitch, channel));
               
         // Poly Gate received from connected jack otherwise IO Panel
         polyGate +=
            (polyGateInputJack.IsConnected() ?
               polyGateInputJack.GetPolyValue(channel) :
               GetIOPanelPolyValue(IOPanelPolyValues.IOPanel_PolyGate, channel));

         // Poly Gate received from connected jack otherwise IO Panel
         polyVel +=
            (polyVelInputJack.IsConnected() ?
               polyVelInputJack.GetPolyValue(channel) :
               GetIOPanelPolyValue(IOPanelPolyValues.IOPanel_PolyVelocity, channel));
               
      }
      
      // ****** GET IO PANEL Values for mono controls
      double pitch = pitchInputJack.IsConnected() ?
         pitchInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Pitch);
         
      double gate = gateInputJack.IsConnected() ?
         gateInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Gate);
      
      double trigger = trigInputJack.IsConnected() ?
         trigInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Trigger);
      
      
      // Note: there is no IOPanel_Velocity value in the debug module!
      double vel = velInputJack.IsConnected() ?
         velInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Velocity);

      double aftertouch = aftertouchInputJack.IsConnected() ?
         aftertouchInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Aftertouch);
      
      double sustain = susInputJack.IsConnected() ?
         susInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Sustain);
      
      double bend = bendInputJack.IsConnected() ?
         bendInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_Bend);
      
      double modWheel = modWheelInputJack.IsConnected() ?
         modWheelInputJack.GetValue() :
         GetIOPanelValue(IOPanelValues.IOPanel_ModWheel);
      

      // Update the debug display
      // The controls reset quickly, so we leave any values on the display for a while

      // poly
      boolean resetTimer = false;

      if (polyPitch != 0) {
         polyPitchCounter.SetText("" + polyPitch);
         // poly pitch doesn't reset
         //resetTimer = true;
      } 
               
      if (polyGate != 0) {
         polyGateCounter.SetText("" + polyGate);
         resetTimer = true;
      } 
               
         
      if (polyVel != 0) {
         polyVelCounter.SetText("" + polyVel);
         // poly velocity doesn't reset
         // resetTimer = true;
      } 
               
      
      // mono
      if (pitch != 0) {
         pitchCounter.SetText("" + pitch);
         // mono Pitch never resets
         // resetTimer = true;
      } 
               
         
      if (gate != 0) {
         gateCounter.SetText("" + gate);
         resetTimer = true;
      } 
               
         
      if (trigger != 0) {
         trigCounter.SetText("" + trigger);
         resetTimer = true;
      } 
          
         
      if (vel != 0) {
         velCounter.SetText("" + vel);
         // velocity doesn't reset
         // resetTimer = true;
      } 
               
         
      if (aftertouch != 0) {
         aftertouchCounter.SetText("" + aftertouch);
         resetTimer = true;
      } 
               
         
      if (sustain != 0) {
         susCounter.SetText("" + sustain);
         resetTimer = true;
      } 
               
      
      // the mod wheel and bend counter always has a value
      modWheelCounter.SetText("" + modWheel);
      bendCounter.SetText("" + bend);
               
      
      // midi
      if (midiMessages != null) {
         fromHostCounter.SetText("" + midiMessages.size());
         resetTimer = true;
      } 
      
      
      // If there is a label change, reset the timer
      if (resetTimer) {
         waitToClear = 7000;
      }
      
      // if no updates have happened, 
      if (waitToClear < 0) {
         // do nothing
      }
      else if (waitToClear == 0) {
         // clear all the controls
         fromHostCounter.SetText("-");
         polyGateCounter.SetText("-");
         gateCounter.SetText("-");
         trigCounter.SetText("-");
         aftertouchCounter.SetText("-");
         susCounter.SetText("-");
      } else {
         // still waiting to clear the text
         waitToClear--;
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
   private VoltageLabel modWheelCounter;
   private VoltageLabel susCounter;
   private VoltageLabel aftertouchCounter;
   private VoltageLabel velCounter;
   private VoltageLabel pitchCounter;
   private VoltageLabel gateCounter;
   private VoltageLabel trigCounter;
   private VoltageLabel bendCounter;
   private VoltageLabel polyVelCounter;
   private VoltageLabel polyGateCounter;
   private VoltageLabel polyPitchCounter;
   private VoltageLabel fromHostCounter;
   private VoltageImage image12;
   private VoltageImage image11;
   private VoltageImage image10;
   private VoltageImage image9;
   private VoltageImage image8;
   private VoltageImage image7;
   private VoltageImage image6;
   private VoltageImage image5;
   private VoltageImage image4;
   private VoltageImage image3;
   private VoltageImage image2;
   private VoltageImage midiInputUpArrow;
   private VoltageLabel modWheel;
   private VoltageRectangle modWheelRectangle;
   private VoltageAudioJack modWheelInputJack;
   private VoltageAudioJack bendInputJack;
   private VoltageLabel bendLabel;
   private VoltageRectangle bendRectangle;
   private VoltageLabel susLabel;
   private VoltageRectangle susRectangle;
   private VoltageAudioJack susInputJack;
   private VoltageAudioJack aftertouchInputJack;
   private VoltageLabel aftertouchLabel;
   private VoltageRectangle aftertouchRectangle;
   private VoltageLabel velLabel;
   private VoltageRectangle velRectangle;
   private VoltageAudioJack velInputJack;
   private VoltageAudioJack trigInputJack;
   private VoltageLabel trigLabel;
   private VoltageRectangle trigRectangle;
   private VoltageLabel gateLabel;
   private VoltageRectangle gateRectangle;
   private VoltageAudioJack gateInputJack;
   private VoltageLabel pitchLabel;
   private VoltageRectangle pitchRectangle;
   private VoltageLabel polyGateLabel;
   private VoltageRectangle polyGateRectangle;
   private VoltageLabel polyPitchLabel;
   private VoltageRectangle polyPitchRectangle;
   private VoltageLabel polyVelLabel;
   private VoltageRectangle polyVelRectangle;
   private VoltagePolyJack polyVelInputJack;
   private VoltagePolyJack polyGateInputJack;
   private VoltageLabel fromHostLabel;
   private VoltageAudioJack pitchInputJack;
   private VoltagePolyJack polyPitchInputJack;
   private VoltageMidiJack midiInputJack1;
   private VoltageLabel moduleTitleLabel;
   private VoltageRectangle fromHostRectangle;


   //[user-code-and-variables]    Add your own variables and functions here

   // timer for resetting controls back to their default state
   int waitToClear = 1;
   
   // disconnectedColor is blue to indicate that the IO Panel is connected
   final Color disconnectedColor = new Color( 0, 32, 167, 255 );

   // connected color is grey to indicate that the IO panel is not connected
   final Color connectedColor = new Color( 167, 167, 167, 255 );

   // Update the rectangle colours for connected and disconnected controls
   void ResetConnectedStates() {
      // Set the midi label rectangle colours
      fromHostRectangle.SetBorderColor(midiInputJack1.IsConnected() ? connectedColor : disconnectedColor);
      
      // Set the poly label rectangle colours
      polyPitchRectangle.SetBorderColor(polyPitchInputJack.IsConnected() ? connectedColor : disconnectedColor);
      polyGateRectangle.SetBorderColor(polyGateInputJack.IsConnected() ? connectedColor : disconnectedColor);
      polyVelRectangle.SetBorderColor(polyVelInputJack.IsConnected() ? connectedColor : disconnectedColor);
      
      // Set the mono label rectangle colours
      pitchRectangle.SetBorderColor(pitchInputJack.IsConnected() ? connectedColor : disconnectedColor);
      gateRectangle.SetBorderColor(gateInputJack.IsConnected() ? connectedColor : disconnectedColor);
      trigRectangle.SetBorderColor(trigInputJack.IsConnected() ? connectedColor : disconnectedColor);
      bendRectangle.SetBorderColor(bendInputJack.IsConnected() ? connectedColor : disconnectedColor);
      velRectangle.SetBorderColor(velInputJack.IsConnected() ? connectedColor : disconnectedColor);
      aftertouchRectangle.SetBorderColor(aftertouchInputJack.IsConnected() ? connectedColor : disconnectedColor);
      susRectangle.SetBorderColor(susInputJack.IsConnected() ? connectedColor : disconnectedColor);
      modWheelRectangle.SetBorderColor(modWheelInputJack.IsConnected() ? connectedColor : disconnectedColor);
   }



   //[/user-code-and-variables]
}

 