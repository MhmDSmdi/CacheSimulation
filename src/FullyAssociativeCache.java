public class FullyAssociativeCache {
    private int cacheSize, blockSize;
    private Block[] cache;
    private int[] findCounter;

    public FullyAssociativeCache(int cacheSize, int blockSize) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        cache = new Block[cacheSize/blockSize];
        findCounter = new int[cacheSize/blockSize];

        for (int i = 0; i < cacheSize/blockSize; i++)
            findCounter[i] = 0;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public boolean find(Address address){
        for (int i = 0; i < cacheSize / blockSize; i++) {
            if(cache[i] != null && address.getTag() == cache[i].getTag()) {
                findCounter[i]++;
                return true;
            }
        }
        return false;
    }

    public Block getBlock(int index) {
        if(index >= cacheSize / blockSize || index < 0)
            return null;
        return cache[index];
    }

    public void put(Block block){
        if(block == null)
            return;
        int lessUsed = 0;

        for (int i = 0; i < cacheSize / blockSize; i++) {
            if(cache[i] == null){
                cache[i] = block;
                findCounter[i] = 0;
                return;
            }
            else if(block.getTag() == cache[i].getTag())
                return;
            else if(findCounter[i] < findCounter[lessUsed])
                lessUsed = i;
        }
        cache[lessUsed] = block;
        findCounter[lessUsed] = 0;
    }

    public void put(int index, Block block){
        cache[index] = block;
        findCounter[index] = 0;
    }

}
