package au.com.arbuxmusic.userstatelifecycle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import javax.imageio.ImageIO;

// The module state of the module
public class ModuleState implements Serializable {
   private static final long serialVersionUID = 1L;

	public double vuMeterValue;
	public double animationValue;
	public double exampleCanvasCursorPositionX = 50;
	public transient BufferedImage canvasBitmap;
	public double digitalCounterValue;
	public int imageValue;
	
	
	// Serialise out the class
   private void writeObject(ObjectOutputStream stream) throws IOException {
      // Write out the rest
      stream.defaultWriteObject();

      // Convert BufferedImage to string. A null bitmap is converted to an empty string
      if (canvasBitmap == null) {
         stream.writeObject("");
      } else {
         final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
         ImageIO.write(canvasBitmap, "png", byteStream);
         stream.writeObject(Base64.getEncoder().encodeToString(byteStream.toByteArray()));
         byteStream.close();
      }      
   }
   
   // Deserialise
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      // Read in the standard object fields
      stream.defaultReadObject();

      // convert PNG to BufferedImage
      var object = stream.readObject();
      String imageBase64 = (String)(object);
      if (imageBase64 == "") {
         canvasBitmap = null;
      } else {
         ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(imageBase64));
         canvasBitmap = ImageIO.read(inputStream);
         inputStream.close();
      }

   }

	
}