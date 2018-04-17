public class Cache {

    public int cacheSize_block;
    private Block[] cacheArray;

    public Cache(int cacheSize_block) {
        this.cacheSize_block = cacheSize_block;
        cacheArray = new Block[cacheSize_block];
        for (int i = 0 ; i < cacheSize_block ; i++) {
            cacheArray[i] = new Block(16);
        }
    }

    public boolean find(Address cpuAddress) {
        Block temp = cacheArray[cpuAddress.getIndex()];
        if (temp.getTag() == cpuAddress.getTag())
            return true;
            else
            return false;

    }
}
