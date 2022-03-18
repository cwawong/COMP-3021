package base;
import java.io.*;
public class TextNote extends Note implements Serializable{
	public String content;
	public TextNote(String title) {
		super(title);
	}
	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis);
			TextNote object= (TextNote) in.readObject();
			result = object.content;
			in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
		}
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	public void exportTextToFile(String pathFolder) {
		try {
			FileOutputStream fos = null;
			ObjectOutputStream out = null;          
			File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
			fos = new FileOutputStream(file.getName());
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	

}
