package Lesson5;

import java.util.Arrays;

public class Main5 {

    static final int size = 10000000;
    static final int half = size / 2;
    static final int quarter = half / 2;
    static final int oneEighth = quarter / 2;



    public static void main(String[] args) {
        float[] arr = new float[size];
        float[] arr2 = new float[size];
        calc1(arr);
        try {
            calc2(arr2, arr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            calc3(arr2, arr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            calc4(arr2, arr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void calc1(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.print("Время выполнения одним потоком - ");
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void calc2(float[] arr, float[] arr2) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        float[] a1 = new float[half];
        float[] a2 = new float[half];
        System.arraycopy(arr, 0, a1, 0, half);
        System.arraycopy(arr, half, a2, 0, half);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < half; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < half; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + (i + half) / 5) * Math.cos(0.2f + (i + half) / 5) * Math.cos(0.4f + (i + half) / 2));
                }
            }
        });
        t2.start();
        t1.join();
        t2.join();

        System.arraycopy(a1, 0, arr, 0, half);
        System.arraycopy(a2, 0, arr, half, half);

        System.out.print("Время выполнения двумя потоками - ");
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(Arrays.equals(arr, arr2));


    }

    private static void calc3(float[] arr, float[] arr2) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        float[] a1 = new float[quarter];
        float[] a2 = new float[quarter];
        float[] a3 = new float[quarter];
        float[] a4 = new float[quarter];
        System.arraycopy(arr, 0, a1, 0, quarter);
        System.arraycopy(arr, quarter, a2, 0, quarter);
        System.arraycopy(arr, quarter * 2, a3, 0, quarter);
        System.arraycopy(arr, quarter * 3, a4, 0, quarter);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < quarter; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < quarter; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + (i + quarter) / 5) * Math.cos(0.2f + (i + quarter) / 5) * Math.cos(0.4f + (i + quarter) / 2));
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < quarter; i++) {
                    a3[i] = (float) (a3[i] * Math.sin(0.2f + (i + quarter * 2) / 5) * Math.cos(0.2f + (i + quarter * 2) / 5) * Math.cos(0.4f + (i + quarter * 2) / 2));
                }
            }
        });
        t3.start();
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < quarter; i++) {
                    a4[i] = (float) (a4[i] * Math.sin(0.2f + (i + quarter * 3) / 5) * Math.cos(0.2f + (i + quarter * 3) / 5) * Math.cos(0.4f + (i + quarter * 3) / 2));
                }
            }
        });
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.arraycopy(a1, 0, arr, 0, quarter);
        System.arraycopy(a2, 0, arr, quarter, quarter);
        System.arraycopy(a3, 0, arr, quarter * 2, quarter);
        System.arraycopy(a4, 0, arr, quarter * 3, quarter);

        System.out.print("Время выполнения четырьмя потоками - ");
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(Arrays.equals(arr, arr2));

    }

    private static void calc4(float[] arr, float[] arr2) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        float[] a1 = new float[oneEighth];
        float[] a2 = new float[oneEighth];
        float[] a3 = new float[oneEighth];
        float[] a4 = new float[oneEighth];
        float[] a5 = new float[oneEighth];
        float[] a6 = new float[oneEighth];
        float[] a7 = new float[oneEighth];
        float[] a8 = new float[oneEighth];
        System.arraycopy(arr, 0, a1, 0, oneEighth);
        System.arraycopy(arr, oneEighth, a2, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 2, a3, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 3, a4, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 4, a5, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 5, a6, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 6, a7, 0, oneEighth);
        System.arraycopy(arr, oneEighth * 7, a8, 0, oneEighth);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + (i + oneEighth) / 5) * Math.cos(0.2f + (i + oneEighth) / 5) * Math.cos(0.4f + (i + oneEighth) / 2));
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a3[i] = (float) (a3[i] * Math.sin(0.2f + (i + oneEighth * 2) / 5) * Math.cos(0.2f + (i + oneEighth * 2) / 5) * Math.cos(0.4f + (i + oneEighth * 2) / 2));
                }
            }
        });
        t3.start();
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a4[i] = (float) (a4[i] * Math.sin(0.2f + (i + oneEighth * 3) / 5) * Math.cos(0.2f + (i + oneEighth * 3) / 5) * Math.cos(0.4f + (i + oneEighth * 3) / 2));
                }
            }
        });
        t4.start();
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a5[i] = (float) (a5[i] * Math.sin(0.2f + (i + oneEighth * 4) / 5) * Math.cos(0.2f + (i + oneEighth * 4) / 5) * Math.cos(0.4f + (i + oneEighth * 4) / 2));
                }
            }
        });
        t5.start();
        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a6[i] = (float) (a6[i] * Math.sin(0.2f + (i + oneEighth * 5) / 5) * Math.cos(0.2f + (i + oneEighth * 5) / 5) * Math.cos(0.4f + (i + oneEighth * 5) / 2));
                }
            }
        });
        t6.start();
        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a7[i] = (float) (a7[i] * Math.sin(0.2f + (i + oneEighth * 6) / 5) * Math.cos(0.2f + (i + oneEighth * 6) / 5) * Math.cos(0.4f + (i + oneEighth * 6) / 2));
                }
            }
        });
        t7.start();
        Thread t8 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < oneEighth; i++) {
                    a8[i] = (float) (a8[i] * Math.sin(0.2f + (i + oneEighth * 7) / 5) * Math.cos(0.2f + (i + oneEighth * 7) / 5) * Math.cos(0.4f + (i + oneEighth * 7) / 2));
                }
            }
        });
        t8.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();

        System.arraycopy(a1, 0, arr, 0, oneEighth);
        System.arraycopy(a2, 0, arr, oneEighth, oneEighth);
        System.arraycopy(a3, 0, arr, oneEighth * 2, oneEighth);
        System.arraycopy(a4, 0, arr, oneEighth * 3, oneEighth);
        System.arraycopy(a5, 0, arr, oneEighth * 4, oneEighth);
        System.arraycopy(a6, 0, arr, oneEighth * 5, oneEighth);
        System.arraycopy(a7, 0, arr, oneEighth * 6, oneEighth);
        System.arraycopy(a8, 0, arr, oneEighth * 7, oneEighth);

        System.out.print("Время выполнения восемью потоками - ");
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(Arrays.equals(arr, arr2));
    }
}
