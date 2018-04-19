public class Block {

    private long tag;
    private long index;

    public Block(long tag, long index){
        this.tag = tag;
        this.index = index;
    }

    public long getIndex() {
        return index;
    }

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }
}
