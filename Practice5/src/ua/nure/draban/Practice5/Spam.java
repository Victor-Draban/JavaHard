package ua.nure.draban.Practice5;

public class Spam implements Runnable {
    private int delay[];
    private String message[];

    public Spam(int[] delay, String[] message) {
        this.delay = delay;
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < message.length; i++) {
                System.out.println(message[i]);
                try {
                    Thread.sleep(delay[i]);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
