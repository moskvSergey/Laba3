
class TR implements Runnable {
	private Thread t;
	private Object lock;

	
	public TR(String name, Object object) {
		this.t = new Thread(this,name);
		this.lock = object;
		t.start();
	}
	
	@Override
	public void run() {
		while(true){
			synchronized(lock){
				try{
					System.out.println(Thread.currentThread().getName());
                    lock.notify();
                    lock.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
			   
	}

	public void join() {
		try {
		 t.join();			   				
		} catch (Exception e) {} 
	}
	
		
}


public class A {
	public static void main(String args[]) {
		Object lock = new Object();
		TR t1 = new TR("Parallel 1", lock);
		TR t2 = new TR("Parallel 2", lock);
		   
	}
}