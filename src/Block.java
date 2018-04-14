public class Block {

    public static int blockSize;
    private Address[] dataArray;
    private long tag;

    public Block(int blockSize) {
        Block.blockSize = blockSize;
        dataArray = new Address[blockSize];
    }

    public Block(Address[] dataArray) {
        this.dataArray = dataArray;
    }

    public Address getDataByOffset(int offset) {
        return dataArray[offset];
    }

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }
}
