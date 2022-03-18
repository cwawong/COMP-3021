package base;
import java.io.*;
public class ImageNote extends Note implements Serializable{
	public File image;
	public ImageNote(String title) {
		super(title);
	}

}
