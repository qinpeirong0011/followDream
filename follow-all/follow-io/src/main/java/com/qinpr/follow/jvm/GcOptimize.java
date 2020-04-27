package com.qinpr.follow.jvm;

/**
 * Created by qinpr on 2020/4/15.
 */
public class GcOptimize {
    public static void main(String[] args) {
        Gc gc = new Gc(10, 20, new Double("1"), new Float(1), 10L, 20L);
        long start = System.currentTimeMillis();
        try {
            while (true) {
                new Thread(gc).start();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            System.out.println("运行总时间:" + (System.currentTimeMillis() - start));
        }


    }

    static class Gc implements Runnable {
        static Long x1 = 10000L;
        static Long x2 = 10000L;
        static Long x3 = 10000L;
        static Long x4 = 10000L;
        static Long x5 = 10000L;
        static Long x6 = 10000L;
        static Long x7 = 10000L;
        static Long x8 = 10000L;
        static Long x9 = 10000L;
        static Long x10 = 10000L;

        Integer a;
        Integer b;
        Double c;
        float d;
        Long e;
        Long f;

        public Gc(Integer a, Integer b, Double c, float d, Long e, Long f) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }

        @Override
        public String toString() {
            return "Gc{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    '}';
        }

        @Override
        public void run() {
            System.out.println(this.toString());
        }
    }
}
