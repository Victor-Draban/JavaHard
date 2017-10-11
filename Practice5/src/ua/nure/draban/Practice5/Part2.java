package ua.nure.draban.Practice5;

import java.io.IOException;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		int delay[] = {1000, 500, 900};
		String[] message = {"Yes", "No", "I don't know"};
        Thread thread = new Thread(new Spam(delay, message));
        thread.start();
        try {
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
