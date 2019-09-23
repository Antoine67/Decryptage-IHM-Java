package Controller;

import java.util.Scanner;

public class Decrypter {
	public void letsDecrypt(){
		Scanner sc = new Scanner(System.in);
        int nombre, quotient, reste, result1, result2;
        String enterNumber;
        char charEnterNumber;
        String bin = "";
        String bin2 = "";
         
        System.out.println("Entrez un nombre décimal :");
        enterNumber = sc.next();
        charEnterNumber = enterNumber.charAt(0);
        nombre = (int) charEnterNumber;
        int nombre2 = (int) '1';
         
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
         
        quotient = nombre2;
        
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
        }
        
        System.out.println("La valeur binaire de " + nombre + " est :");
        System.out.println(envers(bin));
        System.out.println(envers(bin2));
        result1 = Integer.parseInt(envers(bin),2);
        result2 = Integer.parseInt(envers(bin2),2);
        System.out.println(Integer.toBinaryString( result1 ^ result2 ));
        
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
