import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class VictimCache {
    private Cache mainCache;
    private BufferedReader bf;
    private FileReader fr;
    private FullyAssociativeCache fullyAssociativeCache;
    private static final int BIT_LENGTH = 32;
    private static final int BLOCK_SIZE_EXPONENT = 4;
    private static final int CACHE_SIZE_EXPONENT = 12;
    private static final int FULLY_CACHE_SIZE_EXPONENT = 8;
    private static final int NUMBER_OF_INPUT = 500;
    private Vector<Address> addresses;

    public VictimCache(String fileAddress) {
        try {
            addresses = new Vector<>();
            mainCache = new Cache((int) Math.pow(2, CACHE_SIZE_EXPONENT - BLOCK_SIZE_EXPONENT));
            fullyAssociativeCache = new FullyAssociativeCache((int) Math.pow(2,FULLY_CACHE_SIZE_EXPONENT),(int)Math.pow(2,BLOCK_SIZE_EXPONENT));
            fr = new FileReader("inputs/" + fileAddress);
            bf = new BufferedReader(fr);
            for (int i = 0 ; i < NUMBER_OF_INPUT ; i++){
                String addressString  = bf.readLine();
                Address cpuAddress = new Address(addressString, BIT_LENGTH, BLOCK_SIZE_EXPONENT, CACHE_SIZE_EXPONENT);
                addresses.add(cpuAddress);
            }
        }catch (IOException e){
            System.err.println("InputError");
        }
    }

    public void powerOn() {
        for (Address a : addresses) {
            mainCache.find(a);
        }


    }

    public static void main(String[] args) {
        new VictimCache("input1.txt").powerOn();

    }


}
