public class Ram {

    public int ramSize_byte;
    private Block[] ramArray;

    public Ram(Block[] ramArray, int ramSize_byte) {
        this.ramArray = ramArray;
        this.ramSize_byte = ramSize_byte;
    }

    public Ram(int cacheSize_block) {
        ramArray = new Block[cacheSize_block];
    }

    public Block getDataFromRam(Address cpuAddress) {
        //Block temp = ramArray[(int) cpuAddress.getBlockAddress()];
        return temp;
    }

    //read file and fill address_array
    public boolean initRam() {

        return true;
    }
}
