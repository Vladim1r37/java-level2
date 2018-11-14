package Lesson5;

import java.util.Arrays;

public class Main5 {

    static final int SIZE = 10000000;


    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        float[] arr2 = new float[SIZE];
        calc(arr);
        calc(arr2, 1);
        System.out.println(Arrays.equals(arr, arr2));
        calc(arr, 2);
        calc(arr, 4);
        calc(arr, 8);

    }

    private static void calc(float[] arr) {
        Arrays.fill(arr, 1f);
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.print("Время выполнения одним потоком - ");
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calc(float[] arr, int threadsCount) {
        Arrays.fill(arr, 1f);
        int partSize = SIZE / threadsCount;
        long a = System.currentTimeMillis();
        float[][] arrs = new float[threadsCount][partSize];
        Thread[] t = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            System.arraycopy(arr, partSize * i, arrs[i], 0, partSize);
            int u = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int n = u * partSize;
                    for (int j = 0; j < partSize; j++, n++) {
                        arrs[u][j] = (float) (arrs[u][j] * Math.sin(0.2f + n / 5) * Math.cos(0.2f + n / 5) * Math.cos(0.4f + n / 2));
                    }
                }
            });
            t[i].start();

        }

        for (int i = 0; i < threadsCount; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threadsCount; i++) {
            System.arraycopy(arrs[i], 0, arr, i * partSize, partSize);
        }

        System.out.print("Время выполнения " + threadsCount + " потоками - ");
        System.out.println(System.currentTimeMillis() - a);

    }
}
