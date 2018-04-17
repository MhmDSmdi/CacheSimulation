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

    public int find(Block block){
        for (int i = 0; i < cacheSize/blockSize; i++) {
            if(block.getTag() == cache[i].getTag()) {
                use[i]++;
                return i;
            }
        }
        return -1;
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
            if(block.getTag() == cache[i].getTag())
                return;
            else if(use[i] < use[min]){
                min = i;
            }
        }

        cache[min] = block;

    }
}
