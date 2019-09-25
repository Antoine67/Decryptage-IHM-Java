package Controller;

public class ThreadDecrypt extends Thread{
	  Thread t;
	  MultiThreading multiThreading;
	  int beginValue;
	  int wantedValue;
	  public ThreadDecrypt(String name, MultiThreading multiThreading, int beginValue, int wantedValue){
	    super(name);
	    this.multiThreading = multiThreading;
	    this.beginValue = beginValue;
	    this.wantedValue = wantedValue;
	  }


	  public void run(){
	    while(beginValue != wantedValue && multiThreading.keyFinded == false && !multiThreading.shouldStop) {
	    	beginValue++;
	    }
	    if(multiThreading.keyFinded == false) {
	    	
	    }
		   System.out.println("");
	  }

	  public void setThread(Thread t){
	    this.t = t;
	  }
}
