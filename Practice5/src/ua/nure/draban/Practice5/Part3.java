package ua.nure.draban.Practice5;

public class Part3 implements Runnable{
    private static final Object lock = new Object();
	private int count = 0;
    private int countTwo = 0;

	public static void main(String[] args) {
        Part3 part3 = new Part3();
        Thread t1 = new Thread(part3);
        Thread t2 = new Thread(part3);
        t1.start();
        t2.start();
	}

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                System.out.print("count == countTwo      ");
                System.out.println(count == countTwo);
                try {
                    count++;
                    Thread.sleep(10);
                    countTwo++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
