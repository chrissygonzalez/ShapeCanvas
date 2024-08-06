package shapecanvas;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class TestRectangle {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		Rectangle shape = new Rectangle();
		NamedRectangle test = new NamedRectangle(Color.BLACK, Color.BLUE, 1.0f, shape);
		
	    FileOutputStream fileOutputStream = new FileOutputStream("yourfile.sc");
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

	    objectOutputStream.writeObject(test);
	    objectOutputStream.flush();
	    objectOutputStream.close();
	    
	    FileInputStream fileInputStream = new FileInputStream("yourfile.sc");
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    NamedRectangle r2 = (NamedRectangle)objectInputStream.readObject();
	    objectInputStream.close();
	    
	    System.out.println(test.getStrokeColor());
	    System.out.println(r2.getStrokeColor());
	    
	    System.out.println(test.getFillColor());
	    System.out.println(r2.getFillColor());
	    
	    System.out.println(test.getStrokeWidth());
	    System.out.println(r2.getStrokeWidth());
	    
	    System.out.println(test);
	    System.out.println(r2);
	    
	    System.out.println(test.getRectangle().getBounds());
	    System.out.println(r2.getRectangle().getBounds());
	}
}