import java.util.*;
import java.io.*;

public class Library {
    

    private ArrayList<Book>  books= new ArrayList<Book>(); 
    private int size ;    
    public Library (){
	books= new ArrayList();
    }

    //reading csv file 
    public  void  makeArray(){
	//236 books, 2 fields each 
	try {
	    File file = new File("Books.txt");
	    Scanner doc= new Scanner (file);
	    while (doc.hasNextLine()){
		String line= doc.nextLine();
		if (line.equals(null)){
		}
		String[]split = line.split(",, ");
		books.add(new Book(split[0], split[1]));
		    }
	}
	
	catch (FileNotFoundException e ){
	    System.out.println("boo");
	}
	qsort(books);
    }


    public String toString(){ 
	String z="";
	for (int x=0; x<books.size(); x++)
	    z+=(books.get(x)+"\n");
	return z;
    }
 
    /*add book to library 
    - check if book already exists
    - place in order*/
    public void addBook(String title, String author){
    	books.add(new Book(title, author));
	qsort(books);
	String newList="";
	for (int x=0; x<books.size(); x++)
	    newList+=(books.get(x)+"\n");
	try{
	    FileWriter fstream = new FileWriter("Books.txt");
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write(newList);
	    out.close();
	}
	catch (Exception e){
	    System.err.println("boo");
	}
    }
 
    public int size(){
	return books.size();
    }

    /*****************************************************
     * void qsort(int[])
     * @param d -- array of ints to be sorted in place
     *****************************************************/
    public static void qsort( ArrayList<Book> d ) {
	System.out.println("sorting");
	qsHelp( 0, d.size()-1, d );
    }
    public static void qsHelp( int lo, int hi, ArrayList<Book> d ) {

	if ( lo >= hi )
	    return;

	int tmpLo = lo;
	int tmpHi = hi;
	Book pivot = d.get(lo);

	while( tmpLo < tmpHi ) {
	    //first, slide markers in as far as possible without swaps
	    while( d.get(tmpLo).compareTo(pivot) < 0) {
		System.out.println("2");
		tmpLo++;
	    }
	    while( d.get(tmpHi).compareTo(pivot) > 0) {
		System.out.println("3");
		tmpHi--;
	    }

	    swap( tmpLo, tmpHi, d );
	}

	//pivot has been floating around... plant it where it belongs
	d.set(tmpLo, pivot);

	//recurse on lower and upper ranges
	qsHelp( lo, tmpLo-1, d );
	qsHelp( tmpLo+1, hi, d );

    }//end qsHelp

    public static void swap( int x, int y, ArrayList<Book> o ) {
	Book tmp = o.get(x);
	o.set(x,o.get(y));
	o.set(y, tmp);
    }


    /////////////////////////GET WORKING!
    /*
    /////copied from the basement
    /////currently is used only to  make reading user checkedOut list easier
    public static int binSearchIter( String title, String author, int lo, int hi ) {
	int tPos = -1; 
	int m = (lo + hi) / 2; 

	while( lo <= hi ) {
	    m = (lo + hi) / 2;
	    if ( books.get(m).getTitle() == target ) {
		//continue till correct author found
	    }
		
	    else if ( books.get(m).getTitle() > target )
		hi = m - 1; 
	    else if ( books.get(m).getTitle < target )
		lo = m + 1; 
	}
	return tPos;
    }
    */

    public static void main (String[]args){
	Library test = new Library();
	test.makeArray();
	test.addBook("An Abundance of Katherines", "John Green");
	System.out.println(test);
    }
    
   
    
}
