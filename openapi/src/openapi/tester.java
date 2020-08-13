package openapi;

public class tester {
    public static void main(String[] args) {
//        int total = 1237;
//        int tmp = total;
//        final int DENOMINATOR = 100;
//        int start, end = 0;
//
//        while (tmp > DENOMINATOR) {
//
//            int i = tmp / DENOMINATOR;
//            tmp %= DENOMINATOR;
//
//            for(int j = 0; j < i; j++){
//
//                start = end + 1;
//                end = (j+1) * DENOMINATOR;
//                System.out.println("start: " + start + " end: " + end);
//            }
//
//            start = end + 1;
//            end += tmp;
//            System.out.println("start: " + start + " end: " + end);
//        }

        int i = 1;
        String test = "test";
        test = test + String.format("%02d", i);
        System.out.println(test);
    }
}
