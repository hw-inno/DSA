import java.util.BitSet;
import java.util.Random;

public class FastBloomFilter {

    private final BitSet bs;

    final int [] hashSeeds;

    final int capacity;

    public FastBloomFilter(int slots, int hashFunctions) {
        bs = new BitSet(slots);
        Random r = new Random(System.currentTimeMillis());
        hashSeeds = new int[hashFunctions];
        for (int i=0; i<hashFunctions; ++i) {
            hashSeeds[i] = r.nextInt();
        }
        capacity = slots;
    }

    public void add(int value) {
        byte [] b = new byte[] {(byte)(value >>> 24), (byte)(value >>> 16), (byte)(value >>> 8), (byte)value};
        for (int i=0; i<hashSeeds.length; ++i) {
            int h = MurmurHash.hash32(b, 4, hashSeeds[i]);
            bs.set(Math.abs(h)%capacity, true);
        }
    }

    public void clear() {
        bs.clear();
    }

    public boolean mightContain(int value) {
        byte[] b = new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value};
        for (int i = 0; i < hashSeeds.length; ++i) {
            int h = MurmurHash.hash32(b, 4, hashSeeds[i]);

            if (!bs.get(Math.abs(h) % capacity)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String [] args) {
        FastBloomFilter bf = new FastBloomFilter(1000, 1);
        //System.out.println("Query for 2000: " + bf.mightContain(2000));
        //System.out.println("Adding 2000");
        //bf.add(2000);
       // System.out.println("Query for 2000: " + bf.mightContain(2000));

        bf.add(1);
        bf.add(2);
        bf.add(2);
        bf.add(1);
        bf.add(34);
        bf.add(4);
        bf.add(6);
        bf.add(5);
        bf.add(4);
        bf.add(10000);

        System.out.println("Query for 1: " + bf.mightContain(1));
        System.out.println("Query for 3: " + bf.mightContain(3));
        System.out.println("Query for 10000: " + bf.mightContain(10000));

    }
}