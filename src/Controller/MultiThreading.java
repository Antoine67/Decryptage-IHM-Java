package Controller;

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
	private long max_number;
	private String textDecrypted = null;

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
		
		max_number = (long) Math.pow(Math.pow(2,8),MAX_KEY_LENGHT - clueAboutKey.length());
		System.out.println(max_number);

		long part = max_number/numberOfThreads ;
		
		for(int i=0; i<numberOfThreads; i++) {
			System.out.println("Thread "+i+" "+part*i+" à "+part*(i+1));
			threads.add(new ThreadDecrypt(
								Integer.toString(i)
								, this
								, part*i
								, part*(i+1)
								, textCrypted
								, clueAboutKey
								, MAX_KEY_LENGHT)
						);
		}
		
		
	}
	
	public void launch(){
		threads.forEach((thr) -> { thr.start(); });
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
			stopThread();
		}
		
		completedThreads[threadId] = true;
		
		for(int i=0; i<numberOfThreads; i++) {
			if(completedThreads[i] == false) {
				return;
			}
		}
		
		
		//TODO validate threads
		stopThread();
		System.out.println("Thread stopped");
	}
}
