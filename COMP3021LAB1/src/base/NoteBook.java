package base;
import java.util.*;

public class NoteBook {
	private ArrayList<Folder> folders;
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
		
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> list = new ArrayList<Note>();
		for(Folder f: folders) { 
			list.addAll(f.searchNotes(keywords)); 
		}
		return list;
	
	}
	public boolean insertNote(String folderName, Note note) {
		boolean found = false;
		Folder folder = new Folder(folderName);
		for(int i = 0; i < folders.size(); i++) {
			if(folders.get(i).getName().equals(folderName)) {
				found = true;
				folder = folders.get(i);
				break;
				
			}
		}

		if(!found) {
			folders.add(folder);
		}
		ArrayList<Note> notes  = folder.getNotes();
		found = false;
		for(int i = 0; i < notes.size(); i++) {
			if(notes.get(i).getTitle().equals(note.getTitle())) {
				found = true;
			}
		}
		if(found) {
			System.out.println("Creating note " + note.getTitle()+ " under folder " + folderName + " failed");
			return false;
		}else {
			folder.addNote(note);
			return true;
		}
		
	}

	public boolean createTextNote(String folderName, String title, String content) {
		
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
		
		
	}
	public void sortFolders() {
		for(Folder f:folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
}
