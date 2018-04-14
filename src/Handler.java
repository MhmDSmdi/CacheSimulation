public class Handler {

    public static int log2 (long a) {
        int count = 0;
        while (a != 1) {
            a /= 2;
            count++;
        }
        return count;
    }

}
