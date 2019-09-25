package Controller;

public class ThreadDecrypt extends Thread{
	  Thread t;
	  MultiThreading controller;
	  int beginValue;
	  int wantedValue;
	  public ThreadDecrypt(String name, MultiThreading controller, int beginValue, int wantedValue){
	    super(name);
	    this.controller = controller;
	    this.beginValue = beginValue;
	    this.wantedValue = wantedValue;
	  }


	  public void run(){
		  System.out.println("dwad");
	    while(beginValue != wantedValue && controller.keyFinded == false) {
	    	beginValue++;
	    }
	    if(controller.keyFinded == false) {
	    }
		   System.out.println("tets");
	  }

	  public void setThread(Thread t){
	    this.t = t;
	  }
}
