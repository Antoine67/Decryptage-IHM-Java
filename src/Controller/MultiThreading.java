package Controller;

public class MultiThreading {
	Controller controller;
	int a = 0;
	boolean keyFinded = false;
	ThreadDecrypt thread1;
	ThreadDecrypt thread2;
	ThreadDecrypt thread3;
	ThreadDecrypt thread4;
	
	public MultiThreading(Controller controller) {
		this.controller = controller;
		this.thread1 = new ThreadDecrypt("1", this, 0, 3);
		this.thread2 = new ThreadDecrypt("2", this, 1000, 3);
		this.thread3 = new ThreadDecrypt("3", this, 100000, 3);
		this.thread4 = new ThreadDecrypt("4", this, 10000000, 3);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
