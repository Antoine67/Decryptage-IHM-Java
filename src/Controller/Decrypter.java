package Controller;

public class Decrypter {
	Controller controller;
	public Decrypter(Controller controller) {
		this.controller = controller;
	}
	
	public static int MAX_KEY_LENGHT = 12;
;
	public boolean letsDecrypt(String messageADecrypter){

		if(messageADecrypter.length() <= 0 ) {
			return false;
		}
		
		
		System.out.println("Message à décrypter : "+messageADecrypter);
		
        int quotient, reste;
        
        int[] enter = stringToIntArray(messageADecrypter);

        String bin2 = "";
        
        int nombre2 = 1;
         
        
        for(long i = 0; i < Math.pow(256 /* 8 in one byte -> 2^8 */, MAX_KEY_LENGHT); i++ ) {
        	bin2 = "";
        	 quotient = nombre2;

             while (quotient>=1)
             {
                 reste = quotient % 2;
                 quotient = quotient / 2;
                  
                 if (reste == 1) {
                     bin2 = bin2 + '1';
                 } else {
                     bin2 = bin2 + '0';
                 }
             } nombre2++;
             
             
             System.out.println(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))));
             System.out.println(envers(bin2));
             if(validator(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))))) {
            	 System.out.println(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))));
            	 return true;
             }
        } return false;
        
	}
	
	public static String envers(String base)
    {
        int longueur = base.length() - 1;
        String oui = "";
        int n = 0;
         
        while (n<=longueur)
        {
            oui = oui + base.charAt(longueur-n);
            n++;
        }
        
        return oui;
    }
	
	  /**
	   * Convert int array to String (ASCII)
	   * "50,50,50,62" to "222<"
	   * @param array
	   * @return
	   */
	  private static String intArrayToString(int[] array) {
		  StringBuilder str = new StringBuilder();
		  for(int i=0; i<array.length; i++) {
			  str.append((char)array[i]);
		  }
		  return str.toString();
	  }
	
	private Boolean validator(String toValidate) {
		String[] toTry = toValidate.split(" ");
		for(int e = 0; e < toTry.length; e++) {
			if(Integer.toString(e).matches("^[a-z0-9_.]+$")) { return false;	}
			if(controller.getModel().selectWord(toTry[e]) == null) {
				return false;
			}
		}
		return true;
	}
	
	 
	public String binaryToAscii(String binary) {
		     
	String binary2 = "";
	if((binary.length() % 8) != 0){
		int temp2 = 8 - binary.length() % 8;
		for(int o = 0; o < temp2; o++) {
			binary2 = binary2 + "0";
		}
		binary = binary2 + binary;
	}
	 String ascii = "";
	 for(int index = 0; index < binary.length(); index+=8) {
	     String temp = binary.substring(index, index+7);
	     int num = Integer.parseInt(temp,2);
	     char letter = (char) num;
	     ascii = ascii + letter;
	 }
	    return ascii;
	 }
	
	
	public int[] stringToIntArray(String str) {
		int[] array = new int[str.length()];
		for(int i=0;i<str.length(); i++) {
			array[i] = (int) str.charAt(i);
		}
		return array;
	}
		 
}
