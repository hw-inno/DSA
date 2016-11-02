

public class Program {
    public static void main(String[] args) {
        HashTable<String, Integer> map = new HashTable<>();


            map.put("KEK", 0);
        map.put("2", 0);
        map.put("45", 0);
        map.put("555", 0);

        for(Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
            map.entrySet().iterator();

        }

    }
}
