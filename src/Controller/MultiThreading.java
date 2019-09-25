package Controller;

import java.math.BigInteger;
import java.util.ArrayList;

public class MultiThreading {
	Controller controller;
	int a = 0;
	boolean keyFinded = false;
	boolean shouldStop = false;

	
	ArrayList<ThreadDecrypt> threads = new ArrayList<ThreadDecrypt>();
	
	private int numberOfThreads;
	private String textCrypted;
	private String clueAboutKey;
	
	private boolean[] completedThreads;
	
	private static int MAX_KEY_LENGHT = 12;
	private BigInteger max_number;
	private String textDecrypted = null;
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
		
		
		BigInteger number_byte = BigInteger.valueOf(256);
		max_number = (BigInteger) number_byte.pow(MAX_KEY_LENGHT - clueAboutKey.length());
		System.out.println(max_number);
		




		BigInteger part = max_number.divide(BigInteger.valueOf(numberOfThreads)) ;
		
		for(int i=0; i<numberOfThreads; i++) {
			System.out.println("Thread "+i+" "+part.multiply(BigInteger.valueOf(i))+" à "+part.multiply(BigInteger.valueOf(i+1)));
			threads.add(new ThreadDecrypt(
								Integer.toString(i)
								, this
								, part.multiply(BigInteger.valueOf(i))
								, part.multiply(BigInteger.valueOf(i+1))
								, textCrypted
								, clueAboutKey
								, MAX_KEY_LENGHT)
						);
		}
		
		
	}
	
	public String launch(){
		threads.forEach((thr) -> { thr.start(); });
		while(this.shouldStop != true) {
			try {
				if(noSolutionFound) { return null; }
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return textDecrypted;
		//return null;
		
	}
	
	public void stopThread() {
		this.shouldStop = true;
	}
	
	public Controller getController() {
		return this.controller;
	}

	public void setThreadDecrypt(String decryptedText, String threadName) {
		int threadId = Integer.parseInt(threadName);
		
		if(decryptedText != null) {
			textDecrypted  = decryptedText;
			this.shouldStop = true;
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
