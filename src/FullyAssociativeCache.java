public class FullyAssociativeCache {
    private int cacheSize, blockSize;
    private Block[] cache;
    private int[] use;

    public FullyAssociativeCache(int cacheSize, int blockSize) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        cache = new Block[cacheSize/blockSize];
        use = new int[cacheSize/blockSize];
        for (int i = 0; i < cacheSize/blockSize; i++) {
            use[i] = 0;
        }
    }

    public boolean find(Address address){
        for (int i = 0; i < cacheSize/blockSize; i++) {
            if(address.getTag() == cache[i].getTag()) {
                use[i]++;
                return true;
            }
        }
        return false;
    }


  /*  public Block getBlock(int index) {
        if(index >= cacheSize/blockSize || index < 0)
        return null;

        return cache[index];
    } */


    public void put(Block block){
        if(block == null)
            return;
        int min = 0;

        for (int i = 0; i < cacheSize/blockSize; i++) {
            if(cache[i] == null){
                cache[i] = block;
                return;
            }
            else if(block.getTag() == cache[i].getTag())
                return;
            else if(use[i] < use[min])
                min = i;
        }

        cache[min] = block;

    }
}
