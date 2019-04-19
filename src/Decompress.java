import java.io.*;
public class decompress {
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
					
					//increase i as
					i++;
					if (i % 2 == 1) {
						hash_value = (hash_value + i*i) % 65537;
					}
					else {
					//increase hash_code value
					hash_value = (hash_value - i*i) % 65537;
				}}
				
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
				
				i++;
				if (i % 2 == 1) {
					hash_value = (hash_value + i*i) % 65537;
				}
				else {
				//increase hash_code value
				hash_value = (hash_value - i*i) % 65537;
			}
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
		
	 hash hash_table = new hash();
	 try {
	 FileInputStream input =new FileInputStream("output_aesop11.txt");
	 FileWriter fw = new FileWriter("1.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	 String last;
	 String current;
	 String output;
	 int curr_pos = 128;
	 byte read_code[] = new byte[2];
	 int i = input.read(read_code);
	 last = hash_table.searchs(read_code);
	 output = last;
	 out.write(output);
	 i = input.read(read_code);
	 current = hash_table.searchs(read_code);
	 while(i != -1) {
		 if (current == null) {
			 output = output + output.charAt(0);
			 out.write(output);
			 hash_table.insert(curr_pos, output);
			 curr_pos++;
		 }
		 else {
			 output = output + current.charAt(0);
			 out.write(current);
			 hash_table.insert(curr_pos, output);
			 output = current;
			 curr_pos++;
		 }
		 i = input.read(read_code);
		 current = hash_table.searchs(read_code);
	 }
	 out.close();
	 input.close();
	}
	 catch(Exception e) {
		 }
	 }

}
