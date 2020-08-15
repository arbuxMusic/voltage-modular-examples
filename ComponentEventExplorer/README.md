# Component Event Explorer

Provides a real-time debug view of events in VoltageModular as they fire.


To use it as a debugger for another module in the Voltage Modular Designer / Voltage Modular, Publish up for personal/development use.

Some notes on usage:
* The log viewer is "realtime", but experimental and hacked together in a rudimentary way to try to avoid inter-thread comms issues. However, it is not yet proven to work in a "production" sense.
* Midi notes/CCs were hacked in tonight as a prototype. They still require in-depth testing.
* dumping the poly/mono jack input info works fine on my machine. Due to the amount of data (48k samples per second), it may not work on yours.

This module _should_ continue to be developed with new features as I continue to learn VMD. 

## Getting the code for the module.

This  module depends on CoreLib in the same voltage-modular-examples Github project.
You'll need to:
- Build CoreLib using IntelliJ Idea (or Eclipse (Maven build))
- Remove the current External JAR link in the ComponentEventExplorer module
- Add the newly built CoreLib
... You should now be able to run the module in Voltage Module Designer

While I am still investigating the best way to 

## Using the module

The module is split into 4 sections:
- Top left: built in displays/knobs/buttons/switches etc.
    - Feed a signal into the VUs Mono Input to see those dials animate.
    - The +/- buttons are connected to the digital counter to increment or decrement the counter. Hold down the buttons to get an auto repeat going.
    
- Bottom left: Inputs and outputs. 
    - The inputs automatically route to the outputs.

- Middle section: Lines, Rectangles and user-definable Graphical controls.
    - The canvas is set to "Wants Mouse Notifications" so:
        - Right click won't show the standard context menu
        - You'll see what mouse notifications look like in the log.
 
- Right section: The log. This section displays the last ~28 rows of notifications/method calls/samples/midi messages (that's how many seem to fit) of log info on screen.
    - The scroll bar lets you scroll left/right to see the whole message.
    - (No up/down scroll bar yet)
    - Capture allows various options to be toggled on/off. 
        - Tooltip events... are really annoying as they fire continuously. So you can turn them off using this button to avoid the log being flooded.
        - Midi In... Dump any messages received to the log. The decoder is very naive - there are a number of messages that are not yet well decoded.
        - Mono In / Poly In turn on/off the capture of individual samples. This will flood the log (48k samples per second), but if you can speed read, it may help you debug a module.
        - Clear Log. Clears the log to blank in case you want to start a new log debug.
        - Pause Log. Stops all logging temprarily. Un-pause the log to continue logging. 

        
If your DAW supports it, or you are using the standalone app, you can also press keys on your keyboard if you select the module and then start typing. In my experience, the usefulness of this functionality varies from DAW to DAW as some do not pass on keypresses to modules.
 
--------

## Ideas on using the module

### See midi messages

To see midi messages from the host:
- Hook up the VM/VMD Midi From Host jack to the Midi Input.
- Check that the Capture Midi In button is highlighted.

When you press a key, wiggle a knob or fade a fader, you should see the respective midi messages in the log.
BUT... Not all DAWs honour midi messages to VSTs in the same way. Your mileage may vary on how successful with CCs getting through without assistance. I'll review further in future.

### See the result of switching on mouse notification on the different controls.

In Voltage Module Designer, try changing some of the left-hand controls to "Wants Mouse Notifications" and then try interacting with the control to see what events get raised.
The canvas has this setting switched on by default.

-----


## One more thing...

You are going to see a lot of canvas1 repaint events. VM seems to want to repaint the canvas on:
- Cable move
- Module mouse click (unhandled by a control)
- Control mouse click
- VM load
- Canvas1 invalidate requests (legitimate).

If there was ever a strong argument to have a buffered image for canvas repaints, this is it.

I have implemented a rudimentary "update region" indicator (the blue box). if you move a jack over the image, you can see the portion of the image that Voltage Modular wants to update.