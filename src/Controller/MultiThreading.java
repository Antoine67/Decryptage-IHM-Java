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
	
	//private ArrayList<Boolean> completedThreads;
	private boolean[] completedThreads;
	
	private static int MAX_KEY_LENGHT = 12;
	private int max_number= (int) Math.pow(2,MAX_KEY_LENGHT);
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
		
		//completedThreads =  new ArrayList<Boolean>(numberOfThreads);
		completedThreads = new boolean[numberOfThreads];

		int part = max_number/numberOfThreads;
		
		for(int i=0; i<numberOfThreads; i++) {
			threads.add(new ThreadDecrypt(
								Integer.toString(i)
								, this
								, part*i
								, part*(i+1)
								, textCrypted
								,clueAboutKey
								,MAX_KEY_LENGHT)
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
		}
		
		System.out.println(threadId);
		//completedThreads.add(threadId, true);
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
