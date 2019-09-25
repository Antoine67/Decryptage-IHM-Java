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
	private Decrypter decrypter;
	
	private static int MAX_KEY_LENGHT = 12;
	private int max_number= (int) Math.pow(2,MAX_KEY_LENGHT);

	public MultiThreading(
			Controller controller,
			int numberOfThreads,
			String textCrypted,
			String clueAboutKey,
			Decrypter decrypter) 
	{
		this.controller = controller;
		this.numberOfThreads = numberOfThreads;
		this.textCrypted = textCrypted;
		this.clueAboutKey = clueAboutKey;
		this.decrypter = decrypter;

		int part = max_number/numberOfThreads;
		
		for(int i=0; i<numberOfThreads; i++) {
			threads.add(new ThreadDecrypt(
								Integer.toString(i)
								, this
								, part*i
								, part*(i+1)
								, textCrypted)
						);
		}
		
		
	}
	
	public void launch(){
		threads.forEach((thr) -> { thr.start(); });
	}
	
	public void stopThread() {
		
	}
	
	public Controller getController() {
		return this.controller;
	}
}
