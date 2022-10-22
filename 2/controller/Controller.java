package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Platform;

class T implements Runnable {
	private Thread t;
	private int counter = 0;
	
	public T(String name)
	{
		t = new Thread(this,name);
		t.start();
	}
	
	public void run()
	{
		
		while(true) {
			
			System.out.print(counter);
			counter++;
			try {t.sleep(100);} catch (Exception e) {}
		}

			
	}

}
 
 public class Controller {
     
	 
    @FXML
    private void click(ActionEvent event) {
    	/*int counter = 0;
    	while(true) {
			
			System.out.print(counter);
			counter++;
		}
    	*/
        T tPrl = new T("Parallel"); 
	
	    
    }
}