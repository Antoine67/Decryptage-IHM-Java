package Controller;

import java.util.Scanner;

import Model.Model;

public class Decrypter {
	Controller controller;
	public void letsDecrypt(Controller controller){
		this.controller = controller;
		Scanner sc = new Scanner(System.in);
        int nombre, quotient, reste, result1, result2;
        String enterNumber;
        char charEnterNumber;
        String messageACrypter = "ceci";
        String clef = "54";
        int[] enter = controller.getModel().encrypt(messageACrypter,clef);
        String bin = "";
        String bin2 = "";
        
        
	  	  
	  	  //Crypté
	  	  
	  	  //Décrypté
	  	  //System.out.println(intArrayToString(model.encrypt(intArrayToString(array), "qw")));
        /*String[] binbis = enter.split("");
        int intArray[] = new int[enter.length()];*/
        
        
       /* enterNumber = sc.next();
        charEnterNumber = enterNumber.charAt(0);*/
        /*for(int z = 0; z < binbis.length; z++) {
        	bin = "";
        	nombre = (int) binbis[z].charAt(0);
        	quotient = nombre;
         
	        while (quotient>=1)
	        {
	            reste = quotient % 2;
	            quotient = quotient / 2;
	             
	            if (reste == 1)
	            {
	                bin = bin + '1';
	            }
	             
	            else
	            {
	                bin = bin + '0';
	            }
	        }
	        intArray[z] = Integer.parseInt(envers(bin),2);
        }*/
        int nombre2 = 1;
         
        
         
       
        
        //System.out.println(envers(bin));
        for(long i = 0; i < Math.pow(256,12); i++ ) {
        	bin2 = "";
        	 quotient = nombre2;
             int separation = 0;
             while (quotient>=1)
             {
                 reste = quotient % 2;
                 quotient = quotient / 2;
                  
                 if (reste == 1)
                 {
                     bin2 = bin2 + '1';
                 }
                  
                 else
                 {
                     bin2 = bin2 + '0';
                 }
                 /*separation++;
                 if(separation == 8) {
                     bin2 = bin2 + '-';
                     separation = 0;
                 }*/
             }
             nombre2++;
             
             System.out.println(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))));
             System.out.println(envers(bin2));
             if(validator(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))))) {
            	 System.out.println(intArrayToString(controller.getModel().encrypt(intArrayToString(enter), binaryToAscii(envers(bin2)))));
            	 break;
             }
        }
        //result1 = Integer.parseInt(envers(bin),2);
        //result2 = Integer.parseInt(envers(bin2),2);
        //System.out.println(Integer.toBinaryString( result1 ^ result2 ));
        
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
			if(controller.getModel().selectWord(toTry[e]) == null) {
				return false;
			}
		}
		return true;
	}
	
	 
	public String binaryToAscii(String binary) {
		     //java solution
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
		 
}
