package au.com.arbuxmusic.corelib.midi;

import javax.sound.midi.ShortMessage;

/**
 * ShortMessageFormatter decodes a midi message into its constituent parts
 *
 * - A midi message consists of an eight bit status byte with zero-two data bytes.
 * - A midi message can be a channel or system message
 * - Channel messages apply to particular midi channels. The channel number is included in the status  byte.
 * - Channel messages can be for voice (music) or mode (device setup)
 *   - Channel is passed in the status byte
 *  - Voice:
 *      - Note On, Note Off, Velocity
 *          When a key is pressed, the keyboard sends a Note On message for one to 16 logical midi channels.
 *          The status byte for note on will indicate the Channel number
 *          The Note On status byte has two data bytes: The key number and the velocity
 *          When a key is released, a Note Off message is sent. The Note Off message includes data bytes for the key number and the velocity that the key was released.
 *      - Aftertouch / Poly Key Pressure / Channel Pressure
 *          Individual keys will be sent as Poly Key Pressure message with data bytes for the key number and pressure under the key
 *          If the keyboard only responds to have a single pressure level, this will be sent as a "Channel Aftertouch" using the Channel Pressure message. This will only have one data byte to specify the pressure value
 *      - Pitch Bend / Pitch Bend Change
 *          The Pitch Bend Change message is based on the pitch bend wheel for a channel. Two data bytes are the pitch bend value. (Fine resolution)
 *      - Program Change
 *          Used to specify the type of instrument to be played. Has a single data byte to select the program number
*       - Control Change messages
 *          The Control Change message is sent with a data byte for the controller number (CC number) and a second byte for the value.
 *          Some Control Change messages have specific functions:
 *              - Bank Select
 *                  CC 0 (with 32 as the LSB)
 *              - RPN / NRPN (Registered Parameter Numbers / Non Registered Parameter Numbers)
 *                  CC 6 (Data Entry) in combination with:
 *                      CC 96 (Data Increment)
 *                      CC 97 (Data Decrement)
 *                      CC 98 (Non-Registered Parameter Number LSB)
 *                      CC 99 (Non-Registered Parameter Number MSB)
 *                      CC 100 (Registered Parameter Number LSB)
 *                      CC 101 (Registered Parameter Number MSB)
 *                  ... to extend the number of controllers available by midi.
 *                  Transferred by:
 *                   - Selecting the parameter number using 98 & 99 or 100 & 101 and then passing CC 6, 96 or 97
 *                  - Typically used to edit sound patches or other data.
 *                  - Registered params are registered with particular organisations. Unregistered parameters are not assigned specific functions and can be used dependent on the manufacturer.
 *              - Channel Mode Messages
 *                  - CC 121-127 affect how a synth responds to midi data
 *                  - CC 121 : Reset all controllers
 *                  - CC 122 : Enable/Disable Local Control
 *                  - CC 124-127 used to select Omni Mode On, Omni Mode Off, Mono Mode or Poly Mode.
 *                      Omni mode ON will cause the midi receiver to respond to all channels
 *                      Omni Mode OFF will cause the midi received to only respond to a single channel
 *                      Poly Mode will play messages received polyphonically
 *                      Mono Mode will cause the midi receiver to play messages monophonically
 *              -
 * - System messages do not contain a channel number
 *
 * Note numbers:
 *
 */
public class ShortMessageFormatter {
    // Middle C / Note Number 60 / C4
    private static final String[] midiNoteNames = new String[] {
        // 	0	1	2	3	4	5	6	7	8	9	10	11
            "C-1", 	"C#-1", 	"D-1", 	"D#-1", 	"E-1", 	"F-1", 	"F#-1", 	"G-1", 	"G#-1", 	"A-1", 	"A#-1", 	"B-1",
        // 	12	13	14	15	16	17	18	19	20	21	22	23
            "C0", 	"C#0", 	"D0", 	"D#0", 	"E0", 	"F0", 	"F#0", 	"G0", 	"G#0", 	"A0", 	"A#0", 	"B0",
        // 	24	25	26	27	28	29	30	31	32	33	34	35
            "C1", 	"C#1", 	"D1", 	"D#1", 	"E1", 	"F1", 	"F#1", 	"G1", 	"G#1", 	"A1", 	"A#1", 	"B1",
        // 	36	37	38	39	40	41	42	43	44	45	46	47
            "C2", 	"C#2", 	"D2", 	"D#2", 	"E2", 	"F2", 	"F#2", 	"G2", 	"G#2", 	"A2", 	"A#2", 	"B2",
        // 	48	49	50	51	52	53	54	55	56	57	58	59
            "C3", 	"C#3", 	"D3", 	"D#3", 	"E3", 	"F3", 	"F#3", 	"G3", 	"G#3", 	"A3", 	"A#3", 	"B3",
        // 	60	61	62	63	64	65	66	67	68	69	70	71
            "C4", 	"C#4", 	"D4", 	"D#4", 	"E4", 	"F4", 	"F#4", 	"G4", 	"G#4", 	"A4", 	"A#4", 	"B4",
        // 	72	73	74	75	76	77	78	79	80	81	82	83
            "C5", 	"C#5", 	"D5", 	"D#5", 	"E5", 	"F5", 	"F#5", 	"G5", 	"G#5", 	"A5", 	"A#5", 	"B5",
        // 	84	85	86	87	88	89	90	91	92	93	94	95
            "C6", 	"C#6", 	"D6", 	"D#6", 	"E6", 	"F6", 	"F#6", 	"G6", 	"G#6", 	"A6", 	"A#6", 	"B6",
        // 	96	97	98	99	100	101	102	103	104	105	106	107
            "C7", 	"C#7", 	"D7", 	"D#7", 	"E7", 	"F7", 	"F#7", 	"G7", 	"G#7", 	"A7", 	"A#7", 	"B7",
        // 	108	109	110	111	112	113	114	115	116	117	118	119
            "C8", 	"C#8", 	"D8", 	"D#8", 	"E8", 	"F8", 	"F#8", 	"G8", 	"G#8", 	"A8", 	"A#8", 	"B8",
        // 	120	121	122	123	124	125	126	127
            "C9", 	"C#9", 	"D9", 	"D#9", 	"E9", 	"F9", 	"F#9", 	"G9"

    };

    private static final String[] midiCcNames = new String[]{
            "Bank Select", // 0
            "Mod Wheel", // 1
            "Breath Controller", // 2
            "Undefined", // 3
            "Foot Controller", // 4
            "Portamento Time", // 5
            "Data Entry MSB", // 6
            "Channel Volume", // 7
            "Balance", // 8
            "Undefined", // 9
            "Pan", // 10
            "Expression Controller", // 11
            "Effect Control 1", // 12
            "Effect Control 2", // 13
            "Undefined", "Undefined", // 14,15
            // 16,                          17,                             18,                             19
            "General Purpose Controller 1", "General Purpose Controller 2", "General Purpose Controller 3","General Purpose Controller 4",
            // 20,      21,         22,         23,         24,         25,         26,         27,         28,         29,         30,         31
            "Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined",
            // 32       33      34      35      36      37      38          39     40       41      42          43      44          45      46       47
            "LSB 0", "LSB 1", "LSB 2", "LSB 3", "LSB 4","LSB 5", "LSB 6", "LSB 7", "LSB 8", "LSB 9","LSB 10", "LSB 11", "LSB 12", "LSB 13", "LSB 14","LSB 15",
            // 48       49      50          51      52          53      54          55      56          57      58          59      60          61      62          63
            "LSB 16", "LSB 17", "LSB 18", "LSB 19", "LSB 20", "LSB 21", "LSB 22", "LSB 23", "LSB 24", "LSB 25", "LSB 26", "LSB 27", "LSB 28", "LSB 29", "LSB 30", "LSB 31",
            "Damper/Sustain Pedal", // 64
            "Portamento On/Off", // 65
            "Sostenuto", // 66
            "Soft Pedal", // 67
            "Legato Footswitch", // 68 < 0x40 = Normal, >=0x40 = Legato
            "Hold 2", // 69
            "Sound Controller 1", "Sound Controller 2", "Sound Controller 3", "Sound Controller 4", "Sound Controller 5", // 70, 71, 72, 73, 74
            "Sound Controller 6", "Sound Controller 7", "Sound Controller 8", "Sound Controller 9", "Sound Controller 10",  // 75, 76, 77, 78, 79
            "General Purpose Controller 5", "General Purpose Controller 6", "General Purpose Controller 7", "General Purpose Controller 8", // 80,81,82,83
            "Portamento Control", // 84
            // 85        86             87          88          89              90
            "Undefined", "Undefined", "Undefined", "Undefined", "Undefined", "Undefined",
            // 91               92                  93                  94              95
            "Effects 1 Depth", "Effects 2 Depth", "Effects 3 Depth", "Effects 4 Depth", "Effects 5 Depth",
            "Data increment", // 96
            "Data decrement", // 97
            "NRPN LSB", // 98
            "NRPN MSB", // 99
            "RPN LSB", // 100
            "RPN MSB", // 101
            // 102          103         104         105         106             107         108         109
            "Undefined", "Undefined", "Undefined", "Undefined", "Undefined", "Undefined", "Undefined", "Undefined",
            // 110      111         112             113         114         115     116         117         118         119
            "Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined","Undefined",
            // 120                      121                     122                 123                 124                     125                     126                     127
            "Channel Mode Message","Channel Mode Message","Channel Mode Message","Channel Mode Message","Channel Mode Message","Channel Mode Message","Channel Mode Message","Channel Mode Message"
    };

    private static final int channelVoiceNoteOff = 0x80;
    private static final int channelVoiceNoteOn = 0x90;
    private static final int channelVoicePolyKeyPressure = 0xA0;
    private static final int channelVoiceControlChange = 0xB0;
    private static final int channelVoiceProgramChange = 0xC0;
    private static final int channelVoiceChannelPressure = 0xD0;
    private static final int channelVoicePitchBend = 0xE0;



    /**
     * Constructor for a Midi
     */
    public ShortMessageFormatter() {
        // nothing to do at the moment
    }

    public String toString(ShortMessage message) {
        if (message == null)
            return "(null message)";

        int command = message.getCommand() & 0xF0;
        int channel = message.getChannel() & 0x0F;
        int byte1 = message.getData1();
        int byte2 = message.getData2();

        String rawDataHex1 = ", (x:" + String.format("%02X %02X", command + channel, byte1) + ")";
        String rawDataHex2 = ", (x:" + String.format("%02X %02X %02X", command + channel, byte1, byte2) + ")";

        if (command == channelVoiceNoteOff) {
            // 2 bytes
            return "Note Off: Ch:" + channel + " " + midiNoteNames[byte1] + ", vel:" + byte2 + rawDataHex2;
        } else if (command == channelVoiceNoteOn) {
            // 2 bytes
            return "Note On: Ch:" + channel  + " " + midiNoteNames[byte1] + ", vel:" + byte2 + rawDataHex2;
        } else if (command == channelVoicePolyKeyPressure) {
            // 2 bytes
            return "Poly Pressure: Ch:" + channel  + " " + midiNoteNames[byte1] + ", vel:" + byte2 + rawDataHex2;
        }   else if (command == channelVoiceControlChange) {
            // 2 bytes
            return "Control Change: Ch:" + channel  + ", CC:" + byte1 + ", val:" + byte2 + " (" + midiCcNames[byte1] + ")" + rawDataHex2;
        }   else if (command == channelVoiceProgramChange) {
            return "Program Change: Ch:" + channel  + " prgrm:" + byte1 + rawDataHex1;
        }   else if (command == channelVoiceChannelPressure) {
            return "ChannelPressure/Aftertouch: Ch:" + channel  + " " + byte1 + rawDataHex1;
        }   else if (command == channelVoicePitchBend) {
            return "Pitch Bend: Ch:" + channel  + " " + (byte2 * 128 + byte1) + rawDataHex2;
        }
        else
        {
            return "Unknown data:" + command + ", ch: " + channel + " data:" + byte1 + " " + byte2 + rawDataHex2;
        }
    }
}
