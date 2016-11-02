import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class Program {
    static int[] m; //  number of words in each line

    static ConcurrentLinkedQueue<String> text = new ConcurrentLinkedQueue<>();
    static ConcurrentLinkedQueue<HashTable<String, Integer>> maps;
    static HashTable<String, Integer> mergedMap;
    static Entry<String, Integer> answer;
    static Thread mainThr;

    public static void main(String[] args) {
        mainThr = Thread.currentThread();

        /*ArrayList<String> text = new ArrayList<>();*/
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);

        readFromFile(text);
        maps = countThreaded(text);
      //  deleteWords(maps);
        mergedMap = merge(maps);
        answer = findMax(mergedMap);
        writeToFile(answer);

    }

    public static void readFromFile( ConcurrentLinkedQueue<String> text){
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            int i = 0;
            String line;
            while((line = br.readLine()) != null){
                text.add(line.toLowerCase());
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static HashTable<String, Integer>[] count(ArrayList<String> text){
        int size = text.size();
        HashTable<String, Integer>[] maps = new HashTable[size];
        m = new int[size];

        for( int i = 0; i < size; i++) {
            String[] parts = text.get(i).split(("\\W"));
            maps[i] = new HashTable<>((parts.length + 1 )* 7);
            m[i] = parts.length;

            for (int j = 0; j < parts.length; j++) {
                if(parts[j].equals(""))
                    continue;
                String token = parts[j];
                Integer value = maps[i].get(token);

                if (value != null) {
                    maps[i].put(token, value + 1);
                }
                else
                    maps[i].put(token, 1);
            }
        }

        return maps;
    }

    public static ConcurrentLinkedQueue<HashTable<String, Integer>> countThreaded( ConcurrentLinkedQueue<String> text){
        int size = text.size();
        ConcurrentLinkedQueue<HashTable<String,Integer>> maps = new ConcurrentLinkedQueue<>();
        m = new int[size];

        MyThread t = new MyThread(text.remove());
        MyThread t2 = new MyThread(text.remove());
        t.start();
        t2.start();


       // mainThr.join();
        return maps;
    }

   /* public static void deleteWords(ConcurrentLinkedQueue<HashTable<String, Integer>> maps){
        String exclude =  "a the in at to on not for s d re is are am has I we you";
        int size = maps.size();

        for( int i = 0; i < size; i++) {
            for(Entry<String, Integer> entry : maps.entrySet()){
                String key = entry.getKey();
                if(exclude.contains(key))
                    maps[i].remove(key);
            }
        }
    }*/

    public static HashTable<String, Integer> merge(ConcurrentLinkedQueue<HashTable<String, Integer>> maps){
        int size = numOfWordsInText();
        HashTable<String, Integer> mergedMap = new HashTable<>(size * 2);


            while(maps.peek() != null){
                HashTable<String,Integer> map = maps.remove();

                for(Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (key == null)
                        continue;


                /*------ This if block summarizes values for entries with the same key -----*/
                    if (mergedMap.containsKey(key)) {
                        Integer value = mergedMap.get(key);
                        mergedMap.put(key, value + entry.getValue());
                    } else {
                        mergedMap.put(key, entry.getValue());
                    }
                }
        }

        return mergedMap;
    }

    public static Entry<String, Integer> findMax(HashTable<String, Integer> mergedMap){
        int max = 0;
        Entry<String, Integer> answer = null;

        for (Entry<String, Integer> entry : mergedMap.entrySet()){
            if(entry.getValue() != null && entry.getValue() > max){
                max = entry.getValue();
                answer = entry;
            }
        }

        return answer;
    }

    public static void writeToFile(Entry<String, Integer> answer){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            if(answer != null) {
                writer.write(answer.getKey() + " " + answer.getValue());
                System.out.println("\nOutput: ");
                System.out.println(answer.getKey() + " " + answer.getValue());
            }
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static int numOfWordsInText(){
        int max = 0;
        for(int i = 0; i < m.length; i++){
            max += m[i];
        }
        return max;
    }
}
