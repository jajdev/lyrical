package com.jaj.project.radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LyricSort {

	public static void main(String[] args) {

		File file = null;
		Scanner scanner = null;
		HashMap<String,Integer> cache = new HashMap<String,Integer>();
		RadixTree rdx = new RadixTree();
		
		try {
			file = new File("outkast.txt");
			scanner = new Scanner(file);
			
			/* 1. parse each word in the input file
			 * 2. add lyric to the RadixTree
			 * 3. cache each lyric in a HashMap
			 */
			while(scanner.hasNext()){				
				addLyric(rdx, scanner.next().toLowerCase(), cache);
			}
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			scanner.close();
		}
		
		//print traversals
		//rdx.traverseInOrder();
		//rdx.traversePreOrder();
		//rdx.traversePostOrder();
		
		//print
		//rdx.print();
		
		//sort tree
		rdx.mergesort();
		
		//print sorted
		//rdx.print();
		
		printStats(cache);

	}

	public static void addLyric(RadixTree rdx, String lyric, HashMap<String,Integer> cache){
		
		StringBuilder sb = new StringBuilder();
		char c;
		Node current = rdx.getRoot();
		
		for(int i = 0; i < lyric.length(); i++){
			c = lyric.charAt(i);
			if(Character.isLetter(c)){
				sb.append(c);
				current = rdx.addNode(current, c);
			}
		}
		cacheLyric(sb.toString(), cache);
	}
	
	public static void cacheLyric(String lyric, HashMap<String,Integer> cache){
		int count = cache.containsKey(lyric) ? cache.get(lyric) : 0;
		cache.put(lyric, count + 1);
	}
	
	public static void printStats(HashMap<String,Integer> cache){
		
	}
	
}

