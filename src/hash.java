//for string
//define dictionary hash
public class hash {
	
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
				
				//increase hash_code value
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
			
			//increase hash_code value
			hash_value = (hash_value + i*i) % 65537;
			
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
