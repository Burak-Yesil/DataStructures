
// Burak Yesil        CS-284-B
// "I pledge my honor that I have abided with the Stevens honor System."

public class BinaryNumber {
	
	//Data Fields
	
	private int length;
	public int data[];
	
	
	
	//Constructors

	public BinaryNumber(int length) {
		super();
		this.length = length;
		data = new int[length];					
		for (int i = 0; i<this.length; i++) {
			data[i] = 0;
		}
	}
	
	public BinaryNumber(String str) {
		super();
		this.length = str.length();
		data = new int[this.length];														
		for (int i = 0; i< this.length; i++) {
			data[i] = Character.getNumericValue(str.charAt(i));
			}
	}
	
	
	//Functions
	
	public int getLength() {
		//Returns the length of the binary number.
		return this.length;         												
	}
	
	
	public int getDigit(int index) {
		//Returns a specific element of a binary number at a given index.      
		if (index>=this.getLength()) {
			throw new IllegalStateException("Index is out of bounds.");               
		}
		else {
			return data[index];
		}
	}
	
	
	public int[] getInnerArray() {
		//Returns the int array representing a binary number.
		return data;
	}
	
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		//Applies the bitwise or operation to bn1 and bn2 returning a new int array.
		if (((bn1.getLength()) - (bn2.getLength())) != 0) {
			System.out.println("Not working");
			throw new IllegalStateException("Binary numbers are not the same length");
		}
		
		int size = bn1.getLength();
		int [] temp = new int[size];
				
		for (int i = 0; i< size; i++) {
			temp[i] = (bn1.getDigit(i) | bn2.getDigit(i));
		}
		return temp;
	}
	
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		//Applies the bitwise and operation to bn1 and bn2 returning a new int array.
		if (bn1.getLength()-bn2.getLength()!=0) {
			throw new IllegalStateException("Binary numbers are not the same length");
		}
		int size = bn1.getLength();
		int [] temp = new int[size];
				
		for (int i = 0; i< size; i++) {
			temp[i] = (bn1.getDigit(i) & bn2.getDigit(i));
		}
		return temp;
	}
	
	
	public void bitShift(int direction, int amount) {
		//Shifts binary number either to the left or right

			if (!(direction == 1 || direction == -1)) {
				//direction exception
				throw new IllegalStateException("Invalid Index Parameter");
			}
			
			else if(amount<0) {
				//amount exception
				throw new IllegalStateException("Invalid Amount Parameter");
			}
			
			else {
				String s ="";
				
				for (int i = 0; i<this.getLength(); i++) {
					s += Integer.toString(this.getDigit(i));
				}
				
				if (direction == -1) {
					for (int i = 0; i<amount; i++) {
						s+="0";
					}
					this.data = new int[this.length+amount];		
					this.length += amount;
					for (int i = 0; i< this.length; i++){
					int k =	Character.getNumericValue(s.charAt(i));
					data[i] = k;
					//Proof that this works
					System.out.print(data[i]);
					}
					
				}
				else {
					this.data = new int[this.length-amount];		
					this.length -= amount;
					for (int i = 0; i< this.length; i++){
					int k =	Character.getNumericValue(s.charAt(i));
					data[i] = k;
					//Proof that this works
					System.out.print(data[i]);	
					}
				}	
			}
		}		
	
	
	public void add(BinaryNumber aBinaryNumber) {
				//Adds two binary numbers
				String t = this.toString();
				String a = aBinaryNumber.toString();
				String zeros = "";
				
				StringBuilder sb = new StringBuilder();
				
				int diff = Math.abs(t.length()-a.length());
				
				for (int i = 0; i<diff; i++) {
					zeros += "0";
				}
				
				if (t.length()<a.length()) {
					t = zeros + t;
				}
				else if(t.length()>a.length()){
					a = zeros + a;
				}
				else {
					int i = t.length()-1;
					int j = a.length()-1;
					int carry = 0;
					
					while(i>=0) {
						int sum = carry; 
						
						if(i>= 0) {
							sum += t.charAt(i--) - '0';
						}
						if(j>=0) {
							sum += a.charAt(j--) - '0';
						}
						
						sb.insert(0, sum % 2);
						carry = sum/2;
					}
					
					if (carry>0) {
						sb.insert(0,1);
					}
					
					String resultString = sb.toString();
					int size = resultString.length();
					this.data = new int[size];
					
					for (int m = 0; m< size; m++) {
						data[m] = Character.getNumericValue(resultString.charAt(m));
					}
				}
			}

	
	
		public String toString() {
			//Overrides Object classes toString method
			String result = "";
			for (int i = 0; i < data.length; i++) {
				result += data[i];
			}
			return result;
		}
		
		
		
		public int toDecimal() {	
			//Returns the decimal version of the binary number
			int temp = 0;
			int j = 0;
			
			for (int i = this.getLength()-1; i>=0; i--) {	
				if (this.getDigit(i)!=0) {
				temp += Math.pow(2, j);
				}	
				j++;
			}
			return temp;
		}
		
		
}
