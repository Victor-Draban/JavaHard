package ua.nure.draban.Practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Part6 extends Thread {

	private static final Object MONITOR = new Object();

	private static final int THREADS_NUMBER = 10;

	private static final int COLUMNS = 20;

	private static final int EOL_LENGTH = System.lineSeparator().length();

    private static final int ROW_LENGTH = 22;

	private static String fileName = "test.txt";

	private RandomAccessFile out;

	@Override
	public void run() {
        List<Thread> threads = new ArrayList<>();
        try {
            out = new RandomAccessFile(fileName, "rw");

            for (int i = 0; i < THREADS_NUMBER; i++) {
                final int idThread = i;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 0; i < COLUMNS; i++) {
                                synchronized (MONITOR) {
                                    out.seek(ROW_LENGTH * idThread + i);
                                    out.write('0' + idThread);
                                    if (i == COLUMNS - 1) {
//                                        out.write(EOL_LENGTH);
                                        out.writeBytes(System.lineSeparator());
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                threads.add(thread);
            }

            for (Thread iter : threads) {
                try {
                    iter.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(out.getFilePointer());
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) throws IOException,
			InterruptedException {
        Part6 part6 = new Part6();
        part6.start();
	}

}