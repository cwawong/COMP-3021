package base;
import java.util.*;
import java.io.*;

public class Note implements Comparable<Note>,Serializable{
	
	private Date date;
	private String title ;
	public Note(String title) {
		this.title = new String(title);
		date = new Date();
	}
	public String getTitle() {
		return title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		if(date.compareTo(o.date) > 0) {
			return 1;
		}else if(date.compareTo(o.date) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
}
