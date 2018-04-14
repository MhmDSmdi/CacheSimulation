public class Cache {

    public int cacheSize_byte;
    private Block[] cacheArray;

    public Cache(Block[] cache, int cacheSize_byte) {
        this.cacheArray = cache;
        this.cacheSize_byte = cacheSize_byte;
    }

    public Cache(int cacheSize_block) {
        cacheArray = new Block[cacheSize_block];
    }

    public Address getDataFromCache(Address cpuAddress) {
        Block temp = cacheArray[cpuAddress.getIndex()];
        if (temp.getTag() == cpuAddress.getTag()) {
            System.out.println("hit :)");
            //Do Something
            return temp.getDataByOffset(cpuAddress.getOffset());
        } else {
            System.out.println("Miss :|");
            //getDataFromRAM()
            //Interchange Block
            return null;
        }

    }


}
