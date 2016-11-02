import java.util.ArrayList;

class HashTable<K,V> {
    private int CAPACITY;
    private int n; // number of elements in table
    int prime;
    private Entry<K,V>[] table;
    private Entry<K,V> DEFUNCT = new Entry<K, V>(null, null);

    /* Constructor */
    public HashTable(int size){
        this.CAPACITY = size;
        this.n = 0;
        createTable();
        prime = getPrime();
    }
    public HashTable(){ this(17);}

    public V put(K key, V value){
        int i = findSlot(key);
        if(i >= 0)
            return table[i].setValue(value);
        table[-(i+1)] = new Entry<>(key, value);
        n++;
        if(n > 2 * CAPACITY / 2)
            resize(2 * CAPACITY - 1);
        return null;

    }
    public V get(K key) {
       int i = findSlot(key);
        if(i < 0) return null;
        return table[i].getValue();
    }
    public V remove(K key){
        int i = findSlot(key);
        if(i < 0) return null;
        V del = table[i].getValue();
        table[i] = DEFUNCT;
        n--;
        return del;
    }

    public int getCAPACITY() { return n;}
    public boolean containsKey(K key){
        return get(key) == null ? false : true;

    }
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h = 0; h < CAPACITY; h++){
            if(table[h] != null)
                buffer.add(table[h]);
        }
        return buffer;
    }
    public int size(){ return n;}


    /* --- private utility functions --- */
    private int hashFunc1(K key){
        int hash = key.hashCode() % CAPACITY;
        if(hash < 0)
            hash += CAPACITY;
        return hash;
    }
    private int hashFunc2(K key){
        int hash = key.hashCode() % CAPACITY;
        if(hash < 0)
            hash += CAPACITY;
        return prime - hash % prime;
    }
    private int getPrime() {
        for (int i = CAPACITY - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
    private int findSlot(K key){
        int hash = hashFunc1(key);
        int step = hashFunc2(key);

        int avail = -1;
        int i = hash;

        do{
            if(isAvailable(i)){
                if (avail == -1) avail = i;
                if(table[i] == null) break;
            }
            else if(table[i].getKey().equals(key))
                return i;
            i = (i + step) % CAPACITY;
        }while (i != hash);

        return -(avail + 1);
    }
    private boolean isAvailable(int i){
        return table[i] == null || table[i] == DEFUNCT;
    }
    private void createTable(){
        table = (Entry<K,V>[]) new Entry[CAPACITY];
    }
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for(Entry<K,V> e : entrySet())
            buffer.add(e);
        CAPACITY = newCap;
        createTable();
        n = 0;
        for(Entry<K,V> e : buffer)
            put(e.getKey(), e.getValue());
        System.out.println("RESIZED!!!!!");
    }

}

