public class Cache {

    public int cacheSize_block;
    private Block[] cacheArray;
    private int hitCount = 0, missCount = 0;

    public Cache(int cacheSize_block) {
        this.cacheSize_block = cacheSize_block;
        cacheArray = new Block[cacheSize_block];
        for (int i = 0 ; i < cacheSize_block ; i++) {
            cacheArray[i] = new Block(16);
        }
    }

    public Address getDataFromCache(Address cpuAddress) {
        Block temp = cacheArray[cpuAddress.getIndex()];
        if (temp.getTag() == cpuAddress.getTag()) {
            hitCount++;
            return temp.getDataByOffset(cpuAddress.getOffset());
        } else {
            missCount++;
            temp.setTag(cpuAddress.getTag());
            return null;
        }
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }
}
