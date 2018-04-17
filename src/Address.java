public class Address {

    public static int blockSizeByte, cacheSizeByte;
    private String byteAddress;
    private long tag;
    private int offset;
    private int index;

    public Address(String byteAddress, int bitLenght, int blockSizeExponent, int cacheSizeExponent) {
        this.byteAddress = byteAddress;
        blockSizeByte = (int) Math.pow(2, blockSizeExponent);
        cacheSizeByte = (int) Math.pow(2, cacheSizeExponent);
        deserializeAddress(byteAddress, bitLenght, blockSizeExponent, cacheSizeExponent);
//        print();

    }

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getByteAddress() {
        return byteAddress;
    }

    public void setByteAddress(String byteAddress) {
        this.byteAddress = byteAddress;
    }

    private void deserializeAddress(String byteAddress, int bitLentgh, int blockSizeExponent, int cacheSizeExponent) {
        tag = Integer.parseInt(byteAddress.substring(0, bitLentgh - cacheSizeExponent), 2);
        index = Integer.parseInt(byteAddress.substring(bitLentgh - cacheSizeExponent, bitLentgh - blockSizeExponent), 2);
        offset = Integer.parseInt(byteAddress.substring(bitLentgh - blockSizeExponent, bitLentgh), 2);
    }

    private void print() {
        System.out.println("Value: " + byteAddress);
        System.out.println("offset: " + offset);
        System.out.println("index: " + index);
        System.out.println("tag: " + tag);
    }

    public static void main(String[] args) {
        Address a = new Address("11100101011001010110010101100000", 32, 2, 4);
    }
}
