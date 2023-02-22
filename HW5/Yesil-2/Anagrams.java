package anagrams;

//Burak Yesil
//I pledge my honor that I have abided by the Stevens Honor System.

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	public void buildLetterTable() {
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z'};
		letterTable = new HashMap<Character,Integer>();
		for (int i = 0; i< letters.length; i++) {
			letterTable.put(letters[i], primes[i]);
		}		
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	public void addWord(String s) {
		    // Complete
		
		for (int i = 0; i < s.length(); i++) {
			if(letterTable.containsKey(s.charAt(i))) continue;
			else throw new IllegalArgumentException("addWord: string input contains more than just letters");
		}
		
		
		//Does the process
		long hC = myHashCode(s);
		
		ArrayList <String> result = anagramTable.get(hC);
		
		if(result == null) result = new ArrayList();
		
		for (String a : result) {
			if (a.equals(s)) throw new IllegalArgumentException("addWord: duplicate value");
		}
		result.add(s);
		
		anagramTable.put(hC, result);

	}
	
	public long myHashCode(String s) {
		    // Complete
	    if(s.isEmpty()) throw new IllegalArgumentException("Not implemented");
	    
	    long result = 1;


	    
	    for (int i = 0; i < s.length(); i++) {
	    	
	    	char x = s.charAt(i);
	    	result *= letterTable.get(x);
	    }
	    
	    return result;
	    
		
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    // Complete
		long max = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> L = new ArrayList(); 
		
		
		for(Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()) {
			if (max < entry.getValue().size()) max = entry.getValue().size();
			
		}
		
		for(Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()) {
			
			if (max == entry.getValue().size()) L.add(entry);
			
		}
		
		return L;
		
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
		
	}
}
