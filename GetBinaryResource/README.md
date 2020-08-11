# GetImageBinaryResource Voltage Module Example

This module example includes:
- Adding an Extra Resource for reference in an VoltageModule
- Loading an image using VoltageModule's GetBinaryResource(resourceName) method
- Basic manual canvas painting - rectangle & image

## Example breakdown

Items of interest:

### External files

- ByteBufferInputStream.java
Contains a stream conversion class from a ByteBuffer to an InputStream.
This file is referenced in the Module's *Additional Java Files* property.

- Assets\ExampleImage.png
This file is the image to be displayed in this example.
This file is referenced in the Module's *Extra Resources* property. This property is used to add any media items in to the built VoltageModule.
While generally less efficient, I tend to use PNG files for images as they honour transparency.

### Module Design - GetBinaryResourceImage

The module design has a canvas control (canvas1).

### Imports

- import java.nio.ByteBuffer;

The return object type from GetBinaryResource is a ByteBuffer. Great for reading bytes, but not so great for images.
The external class ByteBufferInputStream is a conversion class from a ByteBuffer to an InputStream.

- import javax.imageio.ImageIO;
The Java ImageIO class allows the conversion of an InputStream (in our case the ByteBufferInputStream) to a BufferedImage using the read function.

- import java.awt.image.BufferedImage;
The Java representation of the image that we'll use for later painting.

### The Initialize function

The module loads in its image on startup (recommended for performance reasons) into the myImage variable for later painting.

### Notify function, Canvas_Painting notification

If a Canvas_Painting notification is received for the canvas1 component, a repaint of the canvas is requested by calling the PaintCanvas() method.

### User area - myImage field declaration

The declaration for myImage exists here to load the image.
Why don't I initialise the image here? If the image load has an exception (e.g. missing resource), I don't want the module to fail.

### User area - GetImageResource method

This function is the important one for this example. This function takes the full resource name (including any file extension name) and returns the loaded image.
This is a three stage function:
- Read the named VoltageModule resource into a ByteBuffer
- Convert the ByteBuffer into an InputStream so that ImageIO can process items
- Read the image using Java's ImageIO class

### User area - PaintCanvas method

This function demonstates an example of drawing the image loaded from the resources onto a canvas.
The function draws a border (the g.drawRect statement) and a centered image.
