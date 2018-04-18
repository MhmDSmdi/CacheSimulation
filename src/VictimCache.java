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
    private int hit = 0, miss = 0;

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

    public void useVictimCahce() {
        for (Address a : addresses) {
           boolean found1 =  mainCache.find(a);
           boolean found2 = fullyAssociativeCache.find(a);
           if(!found1 && !found2){
                fullyAssociativeCache.put(mainCache.getBlock(a.getIndex()));
                mainCache.put(a);
                miss++;
           }
           else
               hit++;
        }

        System.out.println("Hit Ratio : " + ((double)hit)/addresses.size());
        System.out.println("Miss Ratio : " + ((double)miss)/addresses.size());


    }

    public void useDirectMapCache(){
        for (Address a : addresses) {
            boolean found1 =  mainCache.find(a);
            if(!found1){
                mainCache.put(a);
                miss++;
            }
            else
                hit++;
        }

        System.out.println("Hit Ratio : " + ((double)hit)/addresses.size());
        System.out.println("Miss Ratio : " + ((double)miss)/addresses.size());
    }

    public static void main(String[] args) {
        new InputCreator().createInputFiles();
        System.out.println("input 1 : ");
        System.out.println("Use Victim Cache :");
        new VictimCache("input1.txt").useVictimCahce();
        System.out.println("Use Direct Map Cache : ");
        new VictimCache("input1.txt").useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 2 : ");
        System.out.println("Use Victim Cache :");
        new VictimCache("input2.txt").useVictimCahce();
        System.out.println("Use Direct Map Cache : ");
        new VictimCache("input2.txt").useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 3 : ");
        System.out.println("Use Victim Cache :");
        new VictimCache("input3.txt").useVictimCahce();
        System.out.println("Use Direct Map Cache : ");
        new VictimCache("input3.txt").useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 4 : ");
        System.out.println("Use Victim Cache :");
        new VictimCache("input4.txt").useVictimCahce();
        System.out.println("Use Direct Map Cache : ");
        new VictimCache("input4.txt").useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 5 : ");
        System.out.println("Use Victim Cache :");
        new VictimCache("input5.txt").useVictimCahce();
        System.out.println("Use Direct Map Cache : ");
        new VictimCache("input5.txt").useDirectMapCache();
        System.out.println("*********************");
        System.out.println("End! :)");

    }


}
