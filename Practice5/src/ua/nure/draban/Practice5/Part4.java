package ua.nure.draban.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part4 {
	private static final int ROWS = 4;
	private static final int COLUMS = 100;

	int matrix[][] = new int[ROWS][COLUMS];

    public Part4(int[][] matrix) {
        this.matrix = matrix;
    }

    public static void main(String[] args) {
        int matrix[][] = new int[ROWS][COLUMS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMS; j++) {
                matrix[i][j] = new Random().nextInt(100);
            }
        }
        Part4 part4 = new Part4(matrix);
        part4.executeTask();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        part4.cuncurencyExecuteTask();
    }

    public void cuncurencyExecuteTask() {
        List<Thread> list = new ArrayList<>();
        final int resFromThread[] = new int[ROWS];
        long start = System.currentTimeMillis();

        for (int i = 0; i < ROWS; i++) {
            final int index = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    int max = matrix[index][0];
                    for (int j = 1; j < COLUMS; j++) {
                        try {
                            Thread.sleep(1);
                            if (matrix[index][j] > max) {
                                max = matrix[index][j];
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    resFromThread[index] = max;
                }
            };
            thread.start();
            list.add(thread);
        }

        for (Thread iter : list) {
            try {
                iter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = resFromThread[0];
        for (int i = 1; i < ROWS; i++) {
            if (result < resFromThread[i]) {
                result = resFromThread[i];
            }
        }
        System.out.println("max = " + result);
        System.out.println("with parralel " + (System.currentTimeMillis() - start));
    }

    public void executeTask() {
        int result = Integer.MIN_VALUE;
        long start = System.currentTimeMillis();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (result < matrix[i][j]) {
                    result = matrix[i][j];
                }
            }
        }
        System.out.println("max = " + result);
        System.out.println("without parralel " + (System.currentTimeMillis() - start));
    }

}
