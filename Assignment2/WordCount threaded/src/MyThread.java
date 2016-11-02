import java.util.concurrent.ConcurrentLinkedQueue;

public class MyThread extends Thread{

    HashTable<String ,Integer> map;
    String text;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String[] parts = text.split("\\W");
            map = new HashTable<>((parts.length + 1) * 2);
            //maps.add(map);

            for (int j = 0; j < parts.length; j++) {
                if (parts[j].equals(""))
                    continue;
                String token = parts[j];
                Integer value = map.get(token);

                if (value != null) {
                    map.put(token, value + 1);
                } else
                    map.put(token, 1);
                System.out.println(Thread.currentThread());
            }
            Program.maps.add(map);
        }


    };

    MyThread(String text){
        this.text = text;
    }
}
