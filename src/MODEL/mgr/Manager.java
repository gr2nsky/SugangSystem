package MODEL.mgr;

import java.io.*;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.*;

public class Manager<T extends Manageable> {

	public ArrayList<T> tList = new ArrayList<>();
	Scanner scn = new Scanner(System.in);

	public void readAll(String filename, Factory<T> fac) {
		T t = null;
		Scanner scanFile = openFile(filename);
		while (scanFile.hasNext()) {
			t = fac.create();
			t.read(scanFile);
			if (tList.contains(t))
				continue;
			tList.add(t);
		}
		scanFile.close();
	}

	public void printAll() {
		for (T t : tList)
			t.print();
	}

	public Scanner openFile(String fileName) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		scan.nextLine();
		return scan;
	}

	public T find(String kwd) {
		for (T t : tList) {
			if (t.compare(kwd)) {
				return t;
			}
		}
		return null;
	}

	public void search(String kwd) {
		for (T t : tList) {
			if (t.matches(kwd))
				t.print();
		}
		return;
	}
	
	public ArrayList<T> getLecArr(){
		return tList;
	}
	
	public Manager<T> getManager() {
		return this;
	}
	
	public Manageable getIndexData(int i){
		return tList.get(i);
	}
}
