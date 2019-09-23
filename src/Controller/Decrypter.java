package Controller;

import java.util.Scanner;

public class Decrypter {
	public void letsDecrypt(){
		Scanner sc = new Scanner(System.in);
        int nombre, quotient, reste;
        String enterNumber;
        char charEnterNumber;
        String bin = "";
         
        System.out.println("Entrez un nombre décimal :");
        enterNumber = sc.next();
        charEnterNumber = enterNumber.charAt(0);
        nombre = (int) charEnterNumber;
        int nombre2 = (int) 'e';
         
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
         
        System.out.println("La valeur binaire de " + nombre + " est :");
        int oui = Integer.parseInt(bin);
        System.out.println(oui);
        System.out.println(envers(bin));
	}
	
	public static String envers(String base)
    {
        int longueur = base.length() - 1;
        String oui = "";
        int n = 0;
         
        while (n<=longueur)
        {
            //System.out.print(base.charAt(longueur-n));
            oui = oui + base.charAt(longueur-n);
            n++;
        }
        
        return oui;
    }
}
