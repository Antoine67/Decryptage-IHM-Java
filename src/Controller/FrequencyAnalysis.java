package Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Model.Model;

public class FrequencyAnalysis {

	public Boolean decryptWithFrequency(String dataToDecrypt, String destinationPath, Model model) {

		
		try {
			
			ArrayList<String> bytesArray = stringToBinary(dataToDecrypt);
			System.out.println(bytesArray);
			System.out.println(bytesArray.size());
			int byteNumber = 0;
			
			HashMap<String, Integer> tmp_frequency_binary = new HashMap<String,Integer>();
			HashMap<String, Integer> frequency_byte = new HashMap<String, Integer>();
			
			for(String n : bytesArray) {
				byteNumber++;
				//System.out.println(Collections.frequency(bytesArray, n));
				if(!tmp_frequency_binary.containsKey(n)) {
					tmp_frequency_binary.put(n, Collections.frequency(bytesArray, n));
				}
				
				if(byteNumber == 12) {
					
					byteNumber = 0;
					
					String maxOccurenceKey = getMaxOcc(tmp_frequency_binary);
					
					Integer maxOccurenceValue;
					if(frequency_byte.get(maxOccurenceKey)!=null) {
						maxOccurenceValue = frequency_byte.get(maxOccurenceKey) + 1;
					}else {
						maxOccurenceValue = 1;
					}
					
					frequency_byte.put(
							maxOccurenceKey
							,maxOccurenceValue
					);
					
					tmp_frequency_binary = new HashMap<String,Integer>(); 
				}
			}; 
			
			System.out.println(frequency_byte);
			int[] aa = model.encrypt(binaryToAscii(getMaxOcc(frequency_byte)),"e");
			System.out.println(intArrayToString(aa));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	private String getMaxOcc(HashMap<String, Integer> map) {
		
		 int max = 0;	String toReturn = null;
		 Iterator it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        if((Integer) pair.getValue() > max ) {
		        	max = (Integer) pair.getValue();
		        	toReturn = pair.getKey().toString();
		        }
		        //System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    
		 System.out.println(max +" "+toReturn);
		 return toReturn;
		
	}


	private ArrayList<String> stringToBinary(String str) throws UnsupportedEncodingException {
        byte[] infoBin = null;
        infoBin = str.getBytes("UTF-8");
        ArrayList<String> returnValue = new ArrayList<String>();
        for (byte b : infoBin) {
        	String bin = Integer.toBinaryString(b); 
            while ( bin.length() < 8 ) {
            	bin = "0" + bin;
            }
            	  
            
            returnValue.add(bin);
        }
        return returnValue;
	}
	
	public String binaryToAscii(String binary) {

		// System.out.println("toAscii: " +binary);

		while (binary.length() % 8 != 0) {
			binary = '0' + binary;
		}

		String tmp = "";
		int tmp_addition;
		while (binary.length() > 0) {
			tmp_addition = 0;

			// Calcul decimal value of the first byte
			for (int i = 8; i > 0; i--) {
				if (binary.charAt(8 - i) == '1') {
					tmp_addition += Math.pow(2, i - 1);
				}
			}
			tmp += Character.toString((char) tmp_addition);// Convert int to char (ASCII) and add it to tmp string
			binary = binary.substring(8);// Remove first byte
		}
		return tmp;
	}
	
	public static String intArrayToString(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			// str.append((char)array[i]);
			str.append(Character.toString((char) array[i]));
		}
		return str.toString();
	}

}
