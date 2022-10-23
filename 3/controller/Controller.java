package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.application.Platform;
import javafx.animation.AnimationTimer;

class StartProgressBar implements Runnable {
	static int flag = 0;
	private Thread t;
	private double counter = 0;
	private ProgressBar bar;
	public Object lock;

	
	public StartProgressBar(String name, ProgressBar bar)
	{
		t = new Thread(this, name);
		this.bar = bar;
		this.lock = new Object();
		t.start();
	}
	
	public void run()
	{
		synchronized(lock) {
			flag = 1;
			for(int i = 0; i<1000; i++) {
				if(flag == 2) {
					flag = 0;
					counter = 0;
					at.handle(29);
					break;
				}
				if(flag == 3) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				counter += 0.01;
				at.handle(29);
				try {t.sleep(20);} catch (Exception e) {}
			}
		}
			
	}
	
	public void join() {
		try {
		 t.join();			   				
		} catch (Exception e) {} 
	}
	
	protected AnimationTimer at = new AnimationTimer(){
        @Override
        public void handle(long now) {
        	bar.setProgress(counter);
        }
    };

}




 
 public class Controller {
	private StartProgressBar t;
	@FXML
	public ProgressBar progressBar = new ProgressBar(0);
	@FXML
    public Button btnPause = new Button();
    @FXML
    private void clickStart(ActionEvent event) {
    	if (StartProgressBar.flag == 3) {
			StartProgressBar.flag = 1;
			btnPause.setText("Pause");
			synchronized(t.lock) {t.lock.notify();}
		}
    	if (StartProgressBar.flag == 1) {
    		StartProgressBar.flag = 2;
    		t.join();
    	}
    	
    	if (StartProgressBar.flag == 0) {
    		t = new StartProgressBar("Paralel", progressBar);
    	}
    }
    @FXML
    private void clickPause(ActionEvent event) {
    	if (StartProgressBar.flag != 0) {
    		if (StartProgressBar.flag == 3) {
    			StartProgressBar.flag = 1;
    			btnPause.setText("Pause");
    			synchronized(t.lock) {t.lock.notify();}
    		}else {
    			btnPause.setText("Continue");
    			StartProgressBar.flag = 3;
    		}
    	}
    }
    @FXML
    private void clickStop(ActionEvent event) {
    	if (StartProgressBar.flag != 0) {
    		if (StartProgressBar.flag == 3) {
    			btnPause.setText("Pause");
    			synchronized(t.lock) {t.lock.notify();}
    		}
    		StartProgressBar.flag = 2;
    	}
    }
    
    
}