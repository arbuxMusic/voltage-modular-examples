# User State Lifecycle Module

This module is intended to show how Voltage Modular manages:
- undo / redo
- reset module
- control state for saving/loading presets

--

TODO:
	- Create serialisable class based on VM instructions
	
	- Set Analog VU Meter	- Knob - Serialise State - Undo/Redo
	- Set Animation			- Knob - Serialise State - Undo/Redo
	- Set Canvas			- Knob - Serialise State - Undo/Redo
	- Set Digital Counter	- Knob - Serialise State - Undo/Redo
	- Set Image				- Knob - Serialise State - Undo/Redo
	- Set LED				- Knob - Serialise State - Undo/Redo
	- Set Text Label		- Knob - Serialise State - Undo/Redo
	- Set Scrollbar			- Knob - Serialise State - Undo/Redo
	
	
--

	Can we have a canary for reset and randomize
		If the canary changes value then the current non-saved state is added to the "last saved state" canary state object.
		The canary only changes on reset or randomize.
		In theory (but not perfectly), the canary changes to a new value 
		
		Programatic changes don't create an undo event on a control, but do cause notifications to fire on that event (any any it updates)
		
		Reset -> Control changes -> Canary (default) -> Reset notification
			OR -> Save off the whole previous state as an undo node
		Randomize -> Control changes -> Canary (random number) -> Randomize notification
			OR -> Save off the whole previous state as an undo node
			
		Undo -> Control Changes -> Canary (previous random number)
		Redo -> Control Changes -> Canary (next random number)
		
			We could almost have a timed undo queue where undo/redos are logged against the random number 
				If the previous node matches the random number then go back otherwise move forward.
				NEVER stay on the random number
		
	Changing a knob to indicate changed since last reset would help keep binaries in sync.
		Randomize will set the knob to a random value with decimal places.
		
	Randomize 
	

--


   // cached image to display in the canvas
   private BufferedImage myImage;

   // Read the specified image
   private BufferedImage GetImageResource(String resourceName) {
      
      try {
         // Get the binary resource from Voltage Modular's resource library
         ByteBuffer buffer = GetBinaryResource(resourceName);
         
         // Read in the image
         ByteBufferInputStream inputStream = new ByteBufferInputStream(buffer);
         return ImageIO.read(inputStream);
         
      } catch (Exception ex) {
         Log("Exception:" + ex);
         return null;
      }
   }


   // Paint a canvas with the image centered in the canvas
   private void PaintCanvas() {
      try {
         Graphics2D g = null;
         
         // Get the bounds of the canvas box
         int width = canvas1.GetBitmapWidth(); // takes HighPixelDensity into acount
         int height = canvas1.GetBitmapHeight(); // takes HighPixelDensity into acount
      
         try{
            // Get the graphics object to enable drawing
            g = canvas1.GetGraphics();
            
            // Draw a border line around the canvas
            g.drawRect(0,0,width-1,height-1);
         
            // Draw the centered image
            int centerX = width / 2 - myImage.getWidth() / 2;
            int centerY = height / 2 - myImage.getHeight() / 2;
            g.drawImage(myImage, centerX, centerY, null);
         
         } finally {
            if (g != null)
               g.dispose();
         }
      } catch (Exception ex) {
         Log("Exception:" + ex);
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