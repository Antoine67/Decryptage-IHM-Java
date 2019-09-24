package Controller;

import java.math.BigInteger;

public class Decrypter {
	Controller controller;
	public Decrypter(Controller controller) {
		this.controller = controller;
	}
	
	public static int MAX_KEY_LENGHT = 12;
	
	String temp_key ;
;
	public boolean letsDecrypt(String messageADecrypter){

		if(messageADecrypter.length() <= 0 ) {
			return false;
		}
		
		messageADecrypter = messageADecrypter.replace("\n", "").replace("\r", "");
		
		String crypt = intArrayToString(controller.getModel().encrypt("soit","^"));
		System.out.println("Exemple : 'soit' avec une clé '^' donne crypté :"
				+ crypt );
		
		int[] decrypt = controller.getModel().encrypt(crypt, "^");
		System.out.println("Soit après decryptage :"
				+intArrayToString(decrypt));
		
		
		for(int a=0; a<crypt.length() ;a++) {
			System.out.println((int)crypt.charAt(a));
		}
		
		System.out.println("Message à décrypter : "+messageADecrypter);
		
        int quotient, reste;
        
        //int[] enter = messageADecrypter;

        temp_key = "";
        
        int temp_key_hexa = 0;
         
        
        for(long i = 0; i < 1000 /*Math.pow(256 /* 8 in one byte -> 2^8 , MAX_KEY_LENGHT) */ ; i++ ) {
        	 temp_key = "";
       	 
        	 
        	 temp_key = Integer.toBinaryString(temp_key_hexa);
        	 while(temp_key.length()%8 != 0) {
            	 temp_key = '0' + temp_key; 
             }
        	 temp_key_hexa++;
             
             //Decrypted data with current temp key
             //System.out.println(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(temp_key)))));
             
             //Temporary key (binary)
             //System.out.println("\n"+temp_key);
        	 
        	 //Temporay key (ASCII)
        	 //System.out.println(binaryToAscii(temp_key));
        	 
        	 /*
        	 if(binaryToAscii(temp_key).equals("^")) {
        		 System.out.println(messageADecrypter +" --> "+
        				 intArrayToString(controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))));
        		 return true;
        	 }*/

             
 
             
             
             if(validateKey(
            		 intArrayToString(controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))))) 
             {
            	 //There, the key is considered as correct
            	 System.out.println(intArrayToString(
            			 controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))));
            	 return true;
             }
        } 
        
        //All possibilities have been tried for a key of length 'MAX_KEY_LENGHT' (default : 12)
        return false;
        
	}
	/*
	public String envers(String base)
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
    }*/
	
	  /**
	   * Convert int array to String (ASCII)
	   * "50,50,50,62" to "222<"
	   * @param array
	   * @return
	   */
	  private String intArrayToString(int[] array) {
		  StringBuilder str = new StringBuilder();
		  for(int i=0; i<array.length; i++) {
			  //str.append((char)array[i]);
			  str.append(Character.toString((char) array[i]));
		  }
		  return str.toString();
	  }
	
	private Boolean validateKey(String toValidate) {
		System.out.println(toValidate);
		if(toValidate == null || toValidate.length() ==0) return false;
		
		String[] toTry = toValidate.split(" ");
		for(int e = 0; e < toTry.length; e++) {
			if(!toTry[e].matches("^[a-z0-9]+$")) { return false;} //Only alpha numeric char, except majs "^[a-zA-Z0-9]+$"
			if(controller.getModel().selectWord(toTry[e]) == null) {
				return false;
			}else {
				System.out.println("Word found:"+toTry[e]+"\nKey:"+temp_key);
			}
		}
		return true;
	}
	
	public String binaryToAscii(String binary) {
		
		String tmp =""; int tmp_addition;
		while(binary.length() > 0) {
			tmp_addition = 0;
			
			//Calcul decimal value of the first byte
			for(int i=8; i>0; i--) { 
				if(binary.charAt(8-i) == '1') {
					tmp_addition+= Math.pow(2, i-1);
				}
			}
			tmp += Character.toString((char) tmp_addition);//Convert int to char (ASCII) and add it to tmp string
			binary = binary.substring(8);// Remove first byte
		}
		return tmp;			
	}
		
	
	
	public int[] stringToIntArray(String str) {
		int[] array = new int[str.length()];
		for(int i=0;i<str.length(); i++) {
			array[i] = (int) str.charAt(i);
		}
		return array;
	}
	
		 
}
