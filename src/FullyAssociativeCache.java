public class FullyAssociativeCache {
    private int cacheSize, blockSize;
    private Block[] cache;
    public FullyAssociativeCache(int cacheSize, int blockSize) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        cache = new Block[cacheSize/blockSize];
    }



}
