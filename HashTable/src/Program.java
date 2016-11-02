public class Program {
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();
        System.out.println(table.getTableSize());
        table.put("KEK", 5);
        table.get("KEK");
        table.put("z", 5);
        table.put("a", 2);
        table.put("b", 6);
        table.put("c", 7);
        for(Entry<String, Integer> e : table.entrySet()){
            System.out.println(e.key);
        }

    }
}
