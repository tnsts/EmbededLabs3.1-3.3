package com.example.neuron;

public class Neuron {
    private double p;
    private Point A, B;

    Neuron(double p, Point A, Point B){
        this.p = p;
        this.A = A;
        this.B = B;
    }

    public Point[] generate(double spead, double time, int iter){
        double w1 = 0, w2 = 0;
        double y = 0, delta = 0;

        boolean aReady = false;
        boolean bReady = false;

        long startTime = System.currentTimeMillis();
        int i = 0;

        Point[] res = new Point[2];

        while(true) {

            if (aReady && bReady) break;
            if (i > iter) break;
            if ((startTime - System.currentTimeMillis()) / 1000.0 > time) break;

            i++;
            int astep = i % 2;
            switch(astep) {
                case 1:
                    y = w1 * A.x + w2 * A.y;

                    if (y > p) {
                        aReady = true;
                        break;
                    }

                    delta = p - y;
                    w1 = w1 + delta * A.x * spead;
                    w2 = w2 + delta * A.y * spead;
                    break;
                case 0:
                    y = w1 * B.x + w2 * B.y;

                    if (y < p) {
                        bReady = true;
                        break;
                    }

                    delta = p - y;
                    w1 = w1 + delta * B.x * spead;
                    w2 = w2 + delta * B.y * spead;
                    break;
            }

            System.out.println(i + "\t" + w1 + "\t" + w2);
        }

        System.out.println(w1 + " " + w2);

        res[0] = new Point(w1, w2);
        res[1] = new Point(i, (startTime - System.currentTimeMillis()));

        return res;
    }
}