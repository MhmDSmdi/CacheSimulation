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
    private double hitRatio = 0;
    private double missRatio = 0;

    public double getHitRatio() {
        return hitRatio;
    }

    public double getMissRatio() {
        return missRatio;
    }



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

    public void useVictimCache() {
        for (Address a : addresses) {
           boolean found1 =  mainCache.find(a);
           boolean found2 = fullyAssociativeCache.find(a);
           if(!found1 && !found2){
                fullyAssociativeCache.put(mainCache.getBlock(a.getIndex()));
                mainCache.put(a);
                miss++;
           }
           else {
               hit++;
               if(!found1 && found2){
                   Block A = mainCache.getBlock(a.getIndex());
                   Block B = new Block(1,2);
                   int ind = -1;
                   for (int i = 0; i < fullyAssociativeCache.getCacheSize()/fullyAssociativeCache.getBlockSize(); i++) {
                       if(a.getTag() == fullyAssociativeCache.getBlock(i).getTag()) {
                           B = fullyAssociativeCache.getBlock(i);
                           ind = i;
                           break;
                       }
                   }
                   mainCache.put(B);
                   fullyAssociativeCache.put(ind,A);
               }
           }
        }

        hitRatio = ((double)hit)/addresses.size();
        missRatio = ((double)miss)/addresses.size();
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

        hitRatio = ((double)hit)/addresses.size();
        missRatio = ((double)miss)/addresses.size();
        System.out.println("Hit Ratio : " + ((double)hit)/addresses.size());
        System.out.println("Miss Ratio : " + ((double)miss)/addresses.size());
    }

    public static void main(String[] args) {
        // new InputCreator().createInputFiles();
        VictimCache[] x = new VictimCache[10];
        System.out.println("input 1 : ");
        System.out.println("Use Victim Cache :");
        x[0] = new VictimCache("input1.txt");
        x[0].useVictimCache();
        System.out.println("Use Direct Map Cache : ");
        x[1] = new VictimCache("input1.txt");
        x[1].useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 2 : ");
        System.out.println("Use Victim Cache :");
        x[2] = new VictimCache("input2.txt");
        x[2].useVictimCache();
        System.out.println("Use Direct Map Cache : ");
        x[3] = new VictimCache("input2.txt");
        x[3].useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 3 : ");
        System.out.println("Use Victim Cache :");
        x[4] = new VictimCache("input3.txt");
        x[4].useVictimCache();
        System.out.println("Use Direct Map Cache : ");
        x[5] = new VictimCache("input3.txt");
        x[5].useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 4 : ");
        System.out.println("Use Victim Cache :");
        x[6] = new VictimCache("input4.txt");
        x[6].useVictimCache();
        System.out.println("Use Direct Map Cache : ");
        x[7] = new VictimCache("input4.txt");
        x[7].useDirectMapCache();
        System.out.println("*********************");
        System.out.println("input 5 : ");
        System.out.println("Use Victim Cache :");
        x[8] = new VictimCache("input5.txt");
        x[8].useVictimCache();
        System.out.println("Use Direct Map Cache : ");
        x[9] = new VictimCache("input5.txt");
        x[9].useDirectMapCache();
        System.out.println("*********************");
        double VHsum = 0;
        double VMsum = 0;
        double DHsum = 0;
        double DMsum = 0;
        for (int i = 0; i < 5; i++) {
            VHsum += x[i*2].getHitRatio();
            DHsum += x[(i*2) + 1].getHitRatio();
            VMsum += x[i*2].getMissRatio();
            DMsum += x[(i*2) + 1].getMissRatio();
        }

        System.out.println("Average Hit Ratio in Victim Cache : " + (VHsum/5));
        System.out.println("Average Miss Ratio in Victim Cache : " + (VMsum/5));
        System.out.println("Average Hit Ratio in Direct Map Cache : " + (DHsum/5));
        System.out.println("Average Miss Ratio in Direct Map Cache : " + (DMsum/5));



        System.out.println("End! :)");

    }


}
