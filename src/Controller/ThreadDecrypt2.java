package Controller;

import java.math.BigInteger;

public class ThreadDecrypt2 extends Thread{
	  Thread t;
	  MultiThreading multiThreading;
	  private String messageADecrypter;
	  private Controller controller;
	  private String clueAboutKey;
	  private String name;
	  
	  private boolean caracterAccepted = true;
	  
	  //Temp key of the current thread
	  String temp_key;
	  private int maxKeyLenght;
	private boolean startFromBigger;
	
	
	  
	  public ThreadDecrypt2(String name, MultiThreading multiThreading, String messageADecrypter, String clueAboutKey, int maxKeyLenght, boolean startFromBigger){
	    super(name);
	    this.name = name;
	    this.multiThreading = multiThreading;
	    this.controller = multiThreading.getController();
	    this.clueAboutKey = clueAboutKey;
	    this.maxKeyLenght = maxKeyLenght;
	    this.messageADecrypter = messageADecrypter;
	    this.startFromBigger = startFromBigger;
	  }


	  public void run(){
		  
		  
		multiThreading.setThreadDecrypt(letsDecrypt(startFromBigger), name);  
		
	    /*while(beginValue != wantedValue && multiThreading.keyFinded == false && !multiThreading.shouldStop) {
	    	beginValue++;
	    }
	    if(multiThreading.keyFinded == false) {
	    	
	    }*/

	  }
	  
	  
	  int[] values;// = new int[] {97,97,97,97,97,97,97,97};
	  
	  public String letsDecrypt(boolean startFromBigger) {
		  
		  values = new int[maxKeyLenght - clueAboutKey.length()];
		  
		  for (int v=0; v<values.length ; v++) {
			  if(!startFromBigger) values[v] = 97;
			  else  values[v] = 122;
			  
		  }
		  
		  
		  System.out.println("Message à décrypter : "+messageADecrypter);

		  if( !startFromBigger ) {
			  while(values[0] < 122) {  //decrypt  while first byte isn't completed
				  increments(values);	

				  String value = checkValues();
	              if(value != null) {
	            	  return value;
	              }
			  }
		  }else {
			  while(values[values.length-1] > 97) {  //decrypt  while first byte isn't completed
				  decrements(values);	

				  String value = checkValues();
	              if(value != null) {
	            	  return value;
	              }
			  }
		  }
		  
		  
		  return null;
	  }
	  private String checkValues() {
		  String tempo = "";
          for(int w=0; w<values.length;w++) {
              //System.out.print(temp_key_array[w]+";");
              tempo += (char) values[w];
          }//System.out.println("");
          
          temp_key = clueAboutKey + tempo;
          System.out.println("Thread n°"+this.getName()+" -> " + temp_key);

          controller.increaseTriedKey();
             if(validateKey(
                     intArrayToString(controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))))) 
             {
                 //There, the key is considered as correct
                 controller.setProgressBarState(false);
                 System.out.println(intArrayToString(
                         controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key))));
                 return intArrayToString(controller.getModel().encrypt(messageADecrypter, binaryToAscii(temp_key)));
             }
          return null;
		
	}


	private void decrements(int[] array) {
		  for( int i = 0; i< array.length; i++) {
			  if(array[i]<=97) {
				  array[i] = 122;
				  
			  }else {
				  array[i]-=1;
				  break;
 			  }
		  }  
		  //afficher(array);
	}


	public void increments(int[] array) {
		  for( int i = 0; i< array.length; i++) {
			  if(array[array.length - i -1]>=122) {
				  array[array.length - i -1] = 97;

			  }else {
				  array[array.length - i -1]+=1;
				  break;
 			  }
		  }
		  
		  //afficher(array);
	  }
		  
	  
	  private void afficher(int[] array) {
		  for( int i = 0; i< array.length; i++) {
			  System.out.print(array[i]+";");
		  }
		  System.out.println("");
		
	}


	public void setThread(Thread t){
	    this.t = t;
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
				//if(!toTry[e].matches("^[a-z0-9]+$")) { return false;} //Only alpha numeric char, except majs "^[a-zA-Z0-9]+$"
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
			
			while(binary.length()%8 != 0) {
				binary = '0' + binary; 
            }
			
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
