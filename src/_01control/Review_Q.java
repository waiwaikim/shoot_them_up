package _01control;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.Math;

public class Review_Q {

    public static void main(String[] args) {

        /*
        int s0 = 4 ;
        double v0 = 0.5;
        int t = 1;
        int g = 10;
        double s = s0 + (v0*t)  + (0.5*g*Math.pow(t, 2));
        */

        // 4 + 0.5 + (0.5 * 10 * 1)
        // 4 + 0.5 + 5
        //System.out.println(s);

        double PV = 100;
        double INT = 6 ;
        double YRS = 30;
        double FV = PV * Math.pow((1 + INT/100), YRS);

        //System.out.println(FV);
        /*
        double a = 1;
        double p = 1;
        double m1 = 2*Math.PI ;
        double m2 = 2*Math.PI ;
        double G = 4*Math.pow(Math.PI, 2)*Math.pow(a,3)/ ((Math.pow(p,2) * (m1 + m2)));
        System.out.println(G);
        */

        /*
        double a = 1;
        double b = 1;
        double gamma = 90 ;
        double c = Math.sqrt(Math.pow(a,2)+Math.pow(b,2) - (2*a*b* Math.cos(gamma)));
        */
        //System.out.println(c);

        /*
        int n = 17;
        int m = 18;

        System.out.println(n/10 + n%10);
        System.out.println(n%2 +m%2);
        System.out.println((m+n)/2);
        System.out.println((m+n)/2.0);
        System.out.println((int)(0.5*(m+n)));
        System.out.println((int)Math.round(0.5*(m+n)));
        */
        boolean b = false;
        int x = 0;


        System.out.println(b && x == 0 );
        System.out.println(b || x == 0 );
        System.out.println(!b && x ==0 );
        System.out.println(!b ||x ==0);
        System.out.println(b && x != 0);
        System.out.println(b ||x != 0 );
        System.out.println(!b && x != 0 );
        System.out.println(!b || x != 0);


    }
}
