package rolodex;

import java.util.ArrayList;

//Burak Yesil
//"I pledge my honor that I have abided by the Stevens Honor System."

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;
	
	// Constructor

	Rolodex() {
	    index = new Entry [26];
	    Entry prev = null;
		for (int i = 0; i < 26; i ++) {
			index[i] = new Separator (prev, null, (char) (65+i));
			
			if (prev != null) {
				prev.next = index[i];
			}
			
			prev = index[i];
			
		}
		index[25].next = index[0];
		
	}

	
	public Boolean contains(String name) {
		//Checks if the rolodex contains anyone under this name.
		Entry current = index[name.charAt(0) - 'A'];
		current = current.next;
		
		while(!current.isSeparator()) {
			if(name.equals(current.getName())) {
			return true;
			}
			current = current.next;
		}
		return false;
	}
	
	
	public int size() { 
		//Returns the size of the rolodex
		Entry current = index[0];
		int i = 0;
		
		while (current != index[25]) {
			if (!current.isSeparator()) {
				i++;
			}
			current = current.next;
		}
		return i;
	}
	
	
	public ArrayList<String> lookup(String name) {
		//returns an ArrayList containing the cell numbers under the input name
		Entry current = index[name.charAt(0) - 'A'];
		ArrayList <String>  L = new ArrayList  <String>();
		boolean exist = false;
		current = current.next;
		
		while(!current.isSeparator()){
			if(name.equals(current.getName())) {
				L.add(((Card)current).getCell());
				exist = true;
			}
			current = current.next;
		}	
		
		if (!exist) {
		throw new IllegalArgumentException("lookup: name not found");	
		}
		
		return L;
		    }
	

	
	public void addCard(String name, String cell) {
		//adds a card to the rolodex under the name input and cell input

		Entry current = index[name.charAt(0) - 'A'];
		boolean added = false;
		current = current.next;
		
		while(!added && !current.isSeparator()) {
			//If there all ready exists a name with the same cell input in the rolodex, an IllegalArgumentException is shown
			if (name.equals(current.next.getName()) && cell.equals(((Card)current).getCell())) {
			throw new IllegalArgumentException("addCard: duplicate entry");
			}
			
			if (name.compareTo(current.getName())<0){
				Entry N = new Card(current.prev, current, name, cell);
				current.prev.next = N;
				current.prev = N;
				added = true; 
			}
			current = current.next;
		}	
		if (!added) {
			current = current.prev;
			Entry N = new Card(current, current.next, name, cell);
			current.next.prev = N;
			current.next = N;
		}
	} 
	
	
	
	public void removeCard(String name, String cell) {
		//Removes card given name and cell
		if (!this.contains(name)){
			//if name doesn't exist in rolodex a message is displayed
			throw new IllegalArgumentException("removeCard: name does not exist");
		}
				
		Entry current = index[name.charAt(0) - 'A'];  
		boolean removed = false;
		current = current.next;
		
		while(!removed && !current.isSeparator()) {						
			if (name.equals(current.getName()) && cell.equals(((Card)current).getCell())) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				removed = true;
			} 
			current = current.next;
		}
		
		
		if (!removed) {
			//returns message if name with inputed cell number doesn't exist.
			throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		}
		
	}
	
	
	public void removeAllCards(String name) {	
		
		    if (!this.contains(name)) {
		    	//returns message if card doesn't exist in rolodex
		    	throw new IllegalArgumentException("removeAllCards: name does not exist");
		    }
		    
		    ArrayList <String> L  = this.lookup(name);

		    
		    for(int i = 0; i<L.size(); i++) {
		    	this.removeCard(name, L.get(i));
		    }

	}
	
	
	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}

	// Cursor operations

	public void initializeCursor() {
		//sets the cursor to separator A
		    cursor = index[0];

	}

	
	public void nextSeparator() {
		//goes to the next separator
			cursor = cursor.next;
			while (true) {
				if(cursor.isSeparator()) {
					break;
				}
				
				cursor = cursor.next;
			}
	}

	
	public void nextEntry() {
		//goes to the next entry 
		    cursor = cursor.next;

	}

	
	public String currentEntryToString() {
		//gives a string out put of current entry
			if (!cursor.isSeparator()) {
				//if the cursor is pointing to a card it follows the format below
				Card X = (Card)cursor;
				return ("Name: " + cursor.getName() + ", " + "Cell: " + X.getCell());
			}
			
			else {
				//if the cursor is pointing to a separator it follows the format below 
				Separator X = (Separator)cursor;
				return ("Separator " + X.getName());
			}
	}
	

}
