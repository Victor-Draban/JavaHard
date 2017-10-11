package ua.nure.draban.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Note!!! Without synchronization this application not work properly!! Most
 * likely a runtime exception will be thrown..
 * 
 */
public class Part51 {

    private static final Object LOCK = new Object();
    private static AtomicInteger index = new AtomicInteger(0);
    private static ThreadLocal<Boolean> canRead = new ThreadLocal<>();

	private static final int ITERATION_NUMBER = 3;

	private static final int READERS_NUMBER = 3;

	// shared resource (not thread-safe!!!)
	private static final StringBuilder BUFFER = new StringBuilder();

	private static final int BUFFER_LENGTH = 5;

	// speed parameter
	private static final int PAUSE = 5;

	// stop signal
	private static boolean stop;

	// reader
	private static class Reader extends Thread {
		public void run() {
			while (!stop) {
				try {
					// read from the buffer
					synchronized (LOCK) {
                        while (index.intValue() == 0 || !canRead.get()) {
                            LOCK.wait();
                            canRead.set(true);
                        }
                        read(getName());
                        canRead.set(false);
                        if (index.incrementAndGet() == 4) {
                            index = new AtomicInteger(0);
                        }
                        LOCK.notifyAll();
                    }
                } catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// writer
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
                    // write to the buffer
					synchronized (LOCK) {
                        while (index.intValue() != 0) {
                            LOCK.wait();
                        }
                        write();
                        index.incrementAndGet();
                        LOCK.notifyAll();
					}
                } catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	private synchronized static void read(String threadName) throws InterruptedException {
            System.out.printf("Reader %s:", threadName);
            for (int j = 0; j < BUFFER_LENGTH; j++) {
                Thread.sleep(PAUSE);
                System.out.print(BUFFER.charAt(j));
            }
            System.out.println();
            Thread.sleep(5);
    }

	private synchronized static void write() throws InterruptedException {
            // clear buffer
            BUFFER.setLength(0);
            // write to buffer
            System.err.print("Writer writes:");

            Random random = new Random();
            for (int j = 0; j < BUFFER_LENGTH; j++) {
                Thread.sleep(PAUSE);
                char ch = (char) ('A' + random.nextInt(26));
                System.err.print(ch);
                BUFFER.append(ch);
            }
            System.err.println();
            Thread.sleep(5);
	}

	public static void main(String[] args) throws InterruptedException {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start readers
		Thread.sleep(10);
		for (Thread reader : readers) {
			reader.start();
		}

		// start writer
		Thread.sleep(10);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
	}

}