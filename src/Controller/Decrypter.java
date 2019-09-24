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
        String enter = "020f2c084952001e451b0a1c411a27130844164a110b170d";
        String bin = "";
        String bin2 = "";
        String[] binbis = enter.split("");
        int intArray[] = new int[enter.length()];
        
        
       /* enterNumber = sc.next();
        charEnterNumber = enterNumber.charAt(0);*/
        for(int z = 0; z < binbis.length; z++) {
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
        }
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
             
             System.out.println(controller.getModel().decrypt(intArray, envers(bin2)));
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
}
