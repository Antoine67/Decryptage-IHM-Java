package Controller;

public class ThreadDecrypt extends Thread{
	  Thread t;
	  Decrypter controller;
	  int beginValue;
	  int wantedValue;
	  public ThreadDecrypt(String name, Decrypter controller, int beginValue, int wantedValue){
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
	    	try {
				controller.hello(beginValue);
		    	System.out.println("tets");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	  }

	  public void setThread(Thread t){
	    this.t = t;
	  }
}
