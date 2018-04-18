public class Cache {

    public int cacheSize_block;
    private Block[] cacheArray;

    public Cache(int cacheSize_block) {
        this.cacheSize_block = cacheSize_block;
        cacheArray = new Block[cacheSize_block];
    }

    public boolean find(Address cpuAddress) {
        Block temp = cacheArray[cpuAddress.getIndex()];
        if (temp != null && (temp.getTag() == cpuAddress.getTag()))
            return true;
            else
            return false;

    }

    public void put(Address address){
        cacheArray[address.getIndex()] = new Block(address.getTag(),address.getIndex());
    }

    public void put(Block block){
        cacheArray[(int) block.getIndex()] = block;
    }

    public Block getBlock(int index){
        return cacheArray[index];
    }
}
