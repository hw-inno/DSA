class Entry<K,V>{
    K key;
    V val;

    Entry(K k, V v){
        this.key = k;
        this.val = v;
    }
    public K getKey(){ return key; }
    public V getValue() { return val; }
    public V setValue(V v){ return this.val = v;}
}