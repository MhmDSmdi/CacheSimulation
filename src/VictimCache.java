import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class VictimCache {
    private BufferedReader bf;
    private FileReader fr;
    private Cache mainCache;
    private FullyAssociativeCache fullyAssociativeCache;
    private static final int BIT_LENGTH = 32;
    private static final int BLOCK_SIZE_EXPONENT = 4;
    private static final int CACHE_SIZE_EXPONENT = 12;
    private static final int FULLY_CACHE_SIZE_EXPONENT = 8;
    private static final int NUMBER_OF_ADDRESS = 500;
    private static final int NUMBER_OF_INPUT_FILE = 5;
    private Vector<Address> addresses;
    private int hitCounter = 0, missCounter = 0;

    public VictimCache(String fileAddress) {
        try {
            addresses = new Vector<>();
            mainCache = new Cache((int) Math.pow(2, CACHE_SIZE_EXPONENT - BLOCK_SIZE_EXPONENT));
            fullyAssociativeCache = new FullyAssociativeCache((int) Math.pow(2, FULLY_CACHE_SIZE_EXPONENT), (int)Math.pow(2, BLOCK_SIZE_EXPONENT));
            fr = new FileReader("inputs/" + fileAddress);
            bf = new BufferedReader(fr);
            for (int i = 0; i < NUMBER_OF_ADDRESS; i++){
                String addressString  = bf.readLine();
                Address cpuAddress = new Address(addressString, BIT_LENGTH, BLOCK_SIZE_EXPONENT, CACHE_SIZE_EXPONENT);
                addresses.add(cpuAddress);
            }
        }catch (IOException e){
            System.err.println("InputError");
        }
    }

    public void useVictimCache() {
        for (Address a : addresses) {
           boolean foundMainCache =  mainCache.find(a);
           boolean foundFullyCache = fullyAssociativeCache.find(a);
           if(!foundMainCache && !foundFullyCache){
                fullyAssociativeCache.put(mainCache.getBlock(a.getIndex()));
                mainCache.put(a);
                missCounter++;
           }
           else {
               hitCounter++;
               if(!foundMainCache && foundFullyCache){
                   Block A = mainCache.getBlock(a.getIndex());
                   Block B = null;
                   int index = -1;
                   for (int i = 0; i < fullyAssociativeCache.getCacheSize() / fullyAssociativeCache.getBlockSize(); i++) {
                       if(a.getTag() == fullyAssociativeCache.getBlock(i).getTag()) {
                           B = fullyAssociativeCache.getBlock(i);
                           index = i;
                           break;
                       }
                   }
                   mainCache.put(B);
                   fullyAssociativeCache.put(index, A);
               }
           }
        }

        System.out.println("\t\tHit Ratio : " + ((double) hitCounter)/addresses.size());
    }

    public void useDirectMapCache(){
        for (Address a : addresses) {
            boolean foundMainCache =  mainCache.find(a);
            if(!foundMainCache){
                mainCache.put(a);
                missCounter++;
            }
            else
                hitCounter++;
        }

        System.out.println("\t\tHit Ratio : " + ((double) hitCounter)/addresses.size());
    }

    public static void main(String[] args) {
        //Create Input File Address : 5 files
//        new InputCreator().createInputFiles();

        //Start Simulating For Each Input File
        for (int i = 1; i <= NUMBER_OF_INPUT_FILE; i++) {
            System.out.println("#input " + i);
            System.out.println("\t *Use Victim Cache* ");
            new VictimCache("input" + i + ".txt").useVictimCache();
            System.out.println("\t *Use Direct Map Cache* ");
            new VictimCache("input" + i + ".txt").useDirectMapCache();
            System.out.println("*************************");
        }
        System.out.println("End! :)");
    }


}
