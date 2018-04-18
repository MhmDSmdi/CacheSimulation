public class Block {
    private long tag;

    public long getIndex() {
        return index;
    }

    private long index;

   /* public Block(long tag) {
        this.tag = tag;
    }*/

    public Block(long tag, long index){
        this.tag = tag;
        this.index = index;
    }

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }
}
