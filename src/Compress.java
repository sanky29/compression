import java.io.*;
import java.util.*;
import java.lang.Byte;
public class compress {
	//for string
	//define dictionary hash
	 static class hash {
		
		//first define table
		public String data[] = new String[65537]; 
		
		//also current capacity
		public int capacity = 128;
		
		//define byte data array
		public byte byte_data[][] = new byte[65537][2];

		//define add function as
		public void add (String element , long hash_code , int curr) {
		
			//just add like this
			int hash_value =(int) hash_code % 65537;
			
			//just check position of hash value
			if (data[hash_value] == null) {
				
				//just add string
				data[hash_value] = element;
				
				//increase capacity by one
				capacity++;
				
			}
			//else go for quadratic probing
			else {
				
				//define int_ i as reference
				int i = 1;
				
				//increase hash_code as
				hash_value = hash_value + 1;
				
				//now do this thing
				while(data[hash_value] != null) {
					
					i++;

						hash_value = (hash_value + i*i) % 65537;
			
					
				}
			
				//now add the element
				data[hash_value] = element;
				
				//increase capacity
				capacity++;
			}
			int z = curr&255;
			int q = curr>>8;
			byte w = (byte) z;
			byte r = (byte) q;
			byte_data[hash_value][0] = r;
			byte_data[hash_value][1] = w;
		}
		//define add function as
		public byte[] search (long hash_code, String element) {
			
			//just add like this
			int hash_value =(int) hash_code % 65537;
			//just check position of hash value
			
			//define int as
			int i = 1;
		
			//now do this thing
			while(data[hash_value] != null && element.equals(data[hash_value]) == false) {
				

					hash_value = ( hash_value + i*i) % 65537;
				
				i++;
			}
	
			if (data[hash_value] != null) {
				
				return(byte_data[hash_value]);
				
			}
			else {
				
				throw new NullPointerException("not foud");
			}
		}
		public String searchs( byte[] x) {
			int z = (int) (x[1]&255);
			int p = (int) (x[0]&255);
			
			int a = z + p*256;
			return(data[a]);
		}
		public void insert(int i , String s) {
			data[i] = s;
		}
		public hash(){
			
			int z;
			int q ;
			byte w;
			byte r ;
		
			//initiate the hash-table as
			for (int i = 0 ; i <= 127 ; i++) {
				
				//for first 127 character do this
				data[i]= String.valueOf((char) i);
				z = i&255;
				q = i>>8;
				w = (byte) z;
				r = (byte) q;
				byte_data[i][1] = w;
				byte_data[i][0] = r;
				
			}
		}
	}

public static void main(String args[]) {
	
	//define current character as
	char file_char;
	
	//define current string as
	String str_curr;
	
	String str_next;
	
	hash hash_table = new hash();
	//define input file as
	String input_file = "1.txt";
	
	//now the output file is as
	String output_file = "2.txt";
	
	//go for try block
	try {
		
		FileInputStream input = new FileInputStream(input_file);
		FileOutputStream output = new FileOutputStream(output_file);
		file_char = (char) input.read();
		str_next = String.valueOf(file_char);
		long next_hash_code = (int) file_char;
		file_char = (char) input.read();
		int curr_pos = 128;
		long curr_hash_code = 0;
		char ref = (char) -1;
		byte z[] = new byte[2];
		while (file_char != ref) {
	
		
			str_curr = str_next;
			str_next = str_next + file_char;
			curr_hash_code = next_hash_code;
			next_hash_code =  (((next_hash_code % 65537) *13)%65537 + (int) file_char)%65537;
	
			try {
				
			z = hash_table.search(next_hash_code , str_next).clone();
			
			file_char = (char) input.read();
		}
			catch(Exception e) {
				z = hash_table.search(curr_hash_code , str_curr).clone();
				
				output.write(z);
				if (curr_pos < 65537) {
					
				hash_table.add(str_next, next_hash_code, curr_pos);
				
				curr_pos ++;}
				str_next = "" + file_char;
				next_hash_code = (int) file_char;
				curr_hash_code = 0;
				str_curr = "";
				file_char = (char) input.read();
				}
			}
		
		z = hash_table.search(next_hash_code , str_next).clone();
		output.write(z);
		input.close();
		output.close();
		
	}
	catch(Exception e) {
			}
	
	
	}

}
