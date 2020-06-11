/*
 * author: Pradeep Sudheer Reddy Chelamala
 * date : 03/09/2020
 * 
 *  
 */
//importing required packages
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


/*start of encoding class*/
public class LZW_encoding {
	/*
	  Input:   Input1: String
	  		   Input2: Integer(bit_length)
	  Output:  List of Integers
	 */
	public static List<Integer> encode(String input, int n)throws OutOfMemoryError {
		HashMap<String,Integer> map = new HashMap<>();    			//table to store the mapping between ascii numbers and characters
		List<Integer> intlist = new ArrayList<>();					// intialization of resultant arraylist
		int max_size = (int)Math.pow(2, n);							// max_size of the table based on the input n
		
		
		for(int i=0;i<256;++i) {									//character to the ascii codes are mapped in the table
			String ch = Character.toString((char)i);
			map.put(ch, i);
		}
		
		String present = "";										//variable to keep track of present character
		String next = "";											//variable to keep track of next character
		int ascii = 256;											//maximum value of the ascii code
		present = Character.toString(input.charAt(0));			
		
		for(int i=0;i<input.length();++i) {							//every character of the input string is traversed
			if(i!=input.length()-1) {
				next = Character.toString(input.charAt(i+1));
			}
			
			if(map.containsKey(present+next)) {						//checked if the present+next is available in table
				present = present + next;
			}else {
				intlist.add(map.get(present));						//added to intlist
				if(map.size() < max_size) {
					map.put(present+next, ascii++);					// mapping are added to table for future patterns
				}else {
					throw new OutOfMemoryError("Memory not sufficient"); // error is thrown if the size of the table exceeds max_size
				}
				present = next;										// present is referenced to the next
			}
			next = "";
		}
		intlist.add(map.get(present));
		
		return intlist;
	}
	
	
	/*
	 * Input - String(path to file)
	 * Output - String(content of the files are read and returned
	 */
	public static String readInput(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path)); // BufferedReader to open reader
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line!=null) {
				sb.append(line);
				line = br.readLine();   								//every line is read iteratively until the null is returned
				
			}
			
			return sb.toString();                                       //String is returned
		}finally {
			br.close();													//BufferedReader is closed
		}
			
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = args[0];									//input is taken as args[0]
		String input = readInput(path);							//readInput is called to read the input
		int n = Integer.parseInt(args[1]);						//Integer input is taken as args[1]
		List<Integer> res = new ArrayList<>();
		try {
			res = encode(input,n);								//encode is called with the input and n and list of integers are returned
		}catch(OutOfMemoryError E){
			System.out.println("Memory Overloaded");			//Incase of memory exception, exception is catched here
		}
		
		String out_path = path.substring(0,path.length()-4) + ".lzw";   //path for the output file
		
		BufferedWriter writer = new BufferedWriter(						//BufferedWriter to write the outputs to file
				new OutputStreamWriter(new FileOutputStream(
						out_path),"UTF-16BE"));
		
		
		for(int i: res) {
			System.out.print(i+" ");						
			writer.write(i);											//list is iterated and outputs are written to the file
		}
		writer.close();													//BufferedWriter is closed.
		
		
	}
}
