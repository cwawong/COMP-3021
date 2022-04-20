package base;
import java.util.*;
import java.io.*;

public class Folder implements Comparable<Folder>,Serializable{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = new String(name);
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}

	public ArrayList<Note> getNotes(){
		return notes;
	}

	public boolean removeNotes(String title){
		int index = -1;
		for(int i = 0; i < notes.size(); i++){
			if(notes.get(i).getTitle().equals(title)) {
				index = i;
				break;
			}
		}
		if(index >= 0){
			notes.remove(index);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean searchNotesHelper(String[] keywordsArrays, String searchArea) {
		String binaryOutput = "";
		searchArea = searchArea.toLowerCase();

		String[] keywordsArray = new String[keywordsArrays.length];
		for(int i = 0; i < keywordsArray.length; i++) keywordsArray[i] = keywordsArrays[i];
		for(int i = 0; i < keywordsArray.length; i++) {
			keywordsArray[i] = keywordsArray[i].toLowerCase();
			if(keywordsArray[i].equals("or")) continue;
			if(searchArea.contains(keywordsArray[i]) ) {
				keywordsArray[i] = "1";
			}else {
				keywordsArray[i] = "0";
			}
		}
		for(String k: keywordsArray) binaryOutput += k;
		
		

		binaryOutput = "";
		for(int i = 0; i < keywordsArray.length; i++) {
			if(keywordsArray[i].equals("or")) {
				if(keywordsArray[i - 1].equals("1") || keywordsArray[i + 1].equals("1")) {
					keywordsArray[i] = "1";
				}else {
					keywordsArray[i] = "0";
				}
				keywordsArray[i - 1] = "";
				keywordsArray[i + 1] = "";
			}
			
		}
		
		for(String k: keywordsArray) {
			binaryOutput += k;
		}

		for(int i = 0; i < binaryOutput.length(); i++) if(binaryOutput.charAt(i) == '0') return false;
		return true;
	}
	public List<Note> searchNotes(String keywords){
		List<Note> list = new ArrayList<Note>();
		String[] keywordsArrays = keywords.split(" ", keywords.length());
		for(Note n: notes) {
			if(n instanceof ImageNote) {
				if(searchNotesHelper(keywordsArrays,n.getTitle())) list.add(n);
			}else {
				if(searchNotesHelper(keywordsArrays,n.getTitle() + " " + ((TextNote)n).content)) list.add(n);
			}
		}
		return list;
			
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		TextNote TNtest = new TextNote("TNtest");
		ImageNote IMtest = new ImageNote("IMtest");
		for(Note test: notes) {
			if(test.getClass() == TNtest.getClass()) {
				nText++;
			}else {
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
		
	}
	
	
	

}
