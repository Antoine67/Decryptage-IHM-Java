package Controller;

import java.awt.Component;
import java.math.BigInteger;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import View.SelectPanel;

public class Decrypter {
	Controller controller;
	public Decrypter(Controller controller) {
		this.controller = controller;
	}
	
	public static int MAX_KEY_LENGHT = 12;
	
	String temp_key ;
;
	public String letsDecrypt(String messageADecrypter){

		if(messageADecrypter.length() <= 0 ) {
			return null;
		}
		controller.setProgressBarState(true);
		messageADecrypter = messageADecrypter.replace("\n", "").replace("\r", "");
		
		String crypt = intArrayToString(controller.getModel().encrypt("soit","^"));
		System.out.println("Exemple : 'soit' avec une cl� '^' donne crypt� :"
				+ crypt );
		
		int[] decrypt = controller.getModel().encrypt(crypt, "^");
		System.out.println("Soit apr�s decryptage :"
				+intArrayToString(decrypt));
		
		
		for(int a=0; a<crypt.length() ;a++) {
			System.out.println((int)crypt.charAt(a));
		}
		
		System.out.println("Message � d�crypter : "+messageADecrypter);
		

        temp_key = "";
        
        int temp_key_hexa = 0;
         
        
        
        
        
        
        for(long i = 0; i < Math.pow(256 /* 8 in one byte -> 2^8 */, MAX_KEY_LENGHT)  ; i++ ) {
        	 temp_key = "";
       	 
        	 
        	 temp_key = Integer.toBinaryString(temp_key_hexa);
        	 while(temp_key.length()%8 != 0) {
            	 temp_key = '0' + temp_key; 
             }
        	 temp_key_hexa++;
        	 
        	 controller.increaseTriedKey();
        	
        	 
             
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
            	 controller.setProgressBarState(false);
            	 System.out.println(intArrayToString(
            			 controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))));
            	 return intArrayToString(controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key)));
             }
        } 
        
        //All possibilities have been tried for a key of length 'MAX_KEY_LENGHT' (default : 12)
        controller.setProgressBarState(false);
        return null;
        
	}
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
	  
	  /*public void hello(int a) throws InterruptedException {
		  this.a = a;
		  this.keyFinded = true;
		   System.out.println(a);
		    System.out.println("statut du thread " + thread1.getName() + " = " + thread1.getState());
		    System.out.println("statut du thread " + thread2.getName() + " = " + thread2.getState());
		    System.out.println("statut du thread " + thread3.getName() + " = " + thread3.getState());
		    System.out.println("statut du thread " + thread4.getName() + " = " + thread4.getState());
	  }*/
	
	private Boolean validateKey(String toValidate) {
		
		if(toValidate == null || toValidate.length() ==0) return false;
		
		String[] toTry = toValidate.split(" ");
		for(int e = 0; e < toTry.length; e++) {
			if(!toTry[e].matches("^[a-z0-9]+$")) { return false;} //Only alpha numeric char, except majs "^[a-zA-Z0-9]+$"
			if(controller.getModel().selectWord(toTry[e]) == null) {
				return false;
			}else {
				System.out.println("Word found:"+toTry[e]+"\nKey:"+temp_key);
				controller.addWordFound(toTry[e],temp_key);
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
