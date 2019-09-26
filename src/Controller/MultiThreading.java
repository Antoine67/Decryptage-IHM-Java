package Controller;

import java.math.BigInteger;
import java.util.ArrayList;

public class MultiThreading {
	Controller controller;
	int a = 0;
	
	//Key found
	boolean keyFinded = false;
	
	//should we stop the program
	boolean shouldStop = false;

	ArrayList<ThreadDecrypt2> threads = new ArrayList<ThreadDecrypt2>();
	
	//Number of threads
	private int numberOfThreads;
	
	//we store the encrypted text
	private String textCrypted;
	
	//We store the clues of the key
	private String clueAboutKey;
	
	private boolean[] completedThreads;
	
	//Size of the key
	private static int MAX_KEY_LENGHT = 12;
	
	private BigInteger max_number;
	
	//Here we store the decrypted text
	private String textDecrypted = null;
	
	//Did we found a solution
	private boolean noSolutionFound = false;

	public MultiThreading(
			Controller controller,
			int numberOfThreads,
			String textCrypted,
			String clueAboutKey) 
	{
		this.controller = controller;
		this.numberOfThreads = numberOfThreads;
		this.textCrypted = textCrypted;
		this.clueAboutKey = clueAboutKey;

		completedThreads = new boolean[numberOfThreads];
		
		BigInteger available_values = BigInteger.valueOf(27); //alphabetic values
		max_number = (BigInteger) available_values.pow(MAX_KEY_LENGHT - clueAboutKey.length());
		System.out.println(max_number);
		
		BigInteger part = max_number.divide(BigInteger.valueOf(numberOfThreads)) ;
		
		for(int i=0; i<numberOfThreads; i++) {
			System.out.println("Thread "+i+" "+part.multiply(BigInteger.valueOf(i))+" à "+part.multiply(BigInteger.valueOf(i+1)));
			threads.add(new ThreadDecrypt2(
								Integer.toString(i)
								, this/*
								, part.multiply(BigInteger.valueOf(i))
								, part.multiply(BigInteger.valueOf(i+1))*/
								, textCrypted
								, clueAboutKey
								, MAX_KEY_LENGHT
								,i%2 == 0
								,part.multiply(BigInteger.valueOf(i))
								,part.multiply(BigInteger.valueOf(i+1)))
						);
		}
			
	}
	
	public String launch(){
		threads.forEach((thr) -> { thr.start(); });
		
		//while the thread are in operation
		while(this.shouldStop != true) {
			try {
				//If we dont fond a solution
				if(noSolutionFound) { return null; }
				
				//stop the thread for 1/10 second
				Thread.sleep(100);
				
			//Catch the exception	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return textDecrypted;
		//return null;
		
	}
	
	public void stopThread() {
		
		// Turn shouldstop boolean to true
		this.shouldStop = true;
	}
	
	public Controller getController() {
		return this.controller;
	}

	public void setThreadDecrypt(String decryptedText, String threadName) {
		int threadId = Integer.parseInt(threadName);
		
		if(decryptedText != null) {
			textDecrypted  = decryptedText;
			
			//Turn shouldstop boolean to true
			this.shouldStop = true;
			
			//we stop the thread
			stopThread();
			return;
			
		}
		
		completedThreads[threadId] = true;
		
		for(int i=0; i<numberOfThreads; i++) {
			if(completedThreads[i] == false) {
				return;
			}
		}
		
		//TODO validate threads
		stopThread();
		this.noSolutionFound  = true;
		this.shouldStop = true;
		System.out.println("Threads stopped");
	}
}
