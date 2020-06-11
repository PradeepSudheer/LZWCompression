/*
 * author: Pradeep Sudheer Reddy Chelamala
 * date : 03/09/2020
 * 
 *  
 */
//importing required packages
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.*;

/* start of decoding class*/
public class LZW_decoding {
	/* 
	 	List of integers and integer as input and string is returned as output
	 */
	public static String decode(List<Integer> input, int n) throws OutOfMemoryError {
		int max_size = (int)Math.pow(2, n);		//max_size for the hashmap
		/* creating table for ascii to String in range of 0 to 255 
		 */
		HashMap<Integer,String> table= new HashMap<>();
		StringBuffer res = new StringBuffer();
		
		for(int i=0;i<256;++i) {
			table.put(i,Character.toString((char)i));
		}
		
		int present = input.get(0),next;		// variables present and next to keep track present and next value
		String sb = table.get(present);  		
		res.append(sb);							//table[present] is appended to the result
		String c = "";
		c+= sb.charAt(0);
		int count = 256;
		for(int i=0;i<input.size()-1;++i) {  	//iterated through the input and values not present in the table are appended
			next = input.get(i+1);
			if(!table.containsKey(next)) {
				sb = table.get(present);
				sb += c;
			}else {
				sb = table.get(next);
			}
			res.append(sb);      				//appended to the result string
			c = "";
			c+= sb.charAt(0);
			// new values are put into the table and error is thrown if table exceeds max_size
			if(table.size() < max_size) {
				table.put(count, table.get(present)+c);
			}else {
				throw new OutOfMemoryError("Memory not sufficient");
			}
			
			count++;          					//count is incremented
			present = next;     				// present is pointed to the next
		}
		return res.toString();        			//return result in the form of string
	}
	
	/* 
	 Input: Type:String
	 Input-description:path to the file
	 Output: Type: list of integers
	 Output-description : content of the file in integers 
	 */
	public static List<Integer> readInput(String path) throws IOException {
		InputStream inputStream = new FileInputStream(path);    // Input Stream for the file
		List<Integer> res = new ArrayList<>();
		try(InputStreamReader inputStreamReader =
			      new InputStreamReader(inputStream, Charset.forName("UTF-16BE"))){    //character unicode encoding of the file is given
			int data = inputStreamReader.read();
			while(data != -1) {
				  res.add(data);
			      data = inputStreamReader.read();		// data is read iteratively and appended to the result until the end of the file
			  }
			}finally {
				  inputStream.close();   				//inputStream is closed.
			  }
		return res;   									//list of integers is returned
	}
	
	/*
	 Input --- two command line arguments are taken as input.
	 		input1: path to the file
	 		input2: bit_length
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Integer> input = new ArrayList<>();
		String path = args[0];
		int n = Integer.parseInt(args[1]);				// bit_length is taken as command line argument
		input = readInput(path);						// path to the file is taken as command line arguments and file content is read using readInput Method
		
		for(Integer s: input) {							//input is printed to the console for decoding
			System.out.print(s+" ");
		}
		
		String res = "";
		try {
			res = decode(input,n);						//Output of the decode is stored in res
		}catch(OutOfMemoryError E){
			System.out.println("Memory Limit Exceeded"); // exception is catched if out of memory error is thrown
		}
		String out_path = path.substring(0,path.length()-4)+"_decoded.txt";	//output path is generated
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(
						out_path)));
		writer.append(res);													//result appended to file
		writer.close();														//Bufferedwriter closed	
	}
}
