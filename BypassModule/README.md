# BypassModule Module

This module shows how to setup and implement a bypassed module.

## Configuring bypass in a Voltage Module

To configure a module to be bypassable, the module's "Can be Bypassed" property should be checked in VMD.

This will automatically add the "ProcessBypassedSample()" method to the Module code. This method must be implemented for bypass to work correctly.

There is no need for any graphical changes in modules for bypassed modules. Voltage Modular visibly dims the whole module when bypassed.

## Implementing ProcessBypassedSample

Add code in to pass any inputs to their respective outputs without processing.
As per Cherry Audio ProcessSample recommendations, to improve performance, if an output jack is not connected, don't bother passing a value to it.

```java
	// any code here
	public void ProcessBypassedSample()
	{
		// ...

		// Copy input to output without processing
		
		// From the documentation, the ProcessSample-type methods should be as performant as possible.
		// Thus checking if an output jack is connected should always be checked before any more work is completed.
		if (outputJack1.IsConnected()) {
		 // Get the value from the input jack
		 double inputValue = inputJack1.GetValue();
		 
		 // Set the output to the input value without any processing
		 outputJack1.SetValue(inputValue);
		}


	}
```


## How to Bypass a module

To bypass a module in Voltage Module Debugger, right click on the module (e.g. in the title bar) and check the "Bypass Module" option.
The module will then gray out.