import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;

public class Main {
    static List<String> list = new ArrayList<>(); // storage for input
    static Calendar dateStart, dateEnd;

    public static void main(String[] args) {
        Box box;
        List<PQ<Integer, String>> queues;

        readFromFile();
        getBossTime();
        box = getClientsTime();
        queues = processData(box);
        writeToFile(queues);
    }

    public static void readFromFile() {
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getBossTime() {
        String boss = list.get(0);
        Matcher matcherDate = Patterns.PATTERN_DATE.matcher(boss);

        matcherDate.find();
        String from = matcherDate.group();

        matcherDate.find();
        String to = matcherDate.group();

        try {
            dateStart = Calendar.getInstance();
            dateEnd = Calendar.getInstance();

            dateStart.setTime(Patterns.DATE_FORMAT.parse(from));
            dateEnd.setTime(Patterns.DATE_FORMAT.parse(to));

            dateEnd.set(Calendar.MINUTE, 1); // To include upper bound while looping in calender

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        list.remove(0); // Remove boss'es time
    }

    public static Box getClientsTime() {
        Box box = new Box(list.size());
        int i = 0;
        Matcher matcherDate, matcherCost;

        for (String s : list) {
            matcherDate = Patterns.PATTERN_DATE.matcher(s);
            matcherCost = Patterns.PATTERN_COST.matcher(s);
            matcherDate.find();
            matcherCost.find();

            try {
                box.setCost(i, Integer.parseInt(matcherCost.group()));
                box.setCalendar(i, Calendar.getInstance());
                box.getCalendar(i).setTime(Patterns.DATE_FORMAT.parse(matcherDate.group()));

                int index = list.get(i).indexOf(':');
                box.setName(i, list.get(i).substring(0, index));

                i++;
            }
            catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return box;
    }

    public static List<PQ<Integer, String>> processData(Box box) {
        List<PQ<Integer, String>> queues = new ArrayList<>();
        PQ<Integer, String> tempPQ;

        Calendar temp = new GregorianCalendar();
        Calendar tempLeft = new GregorianCalendar();

        temp.setTime(dateStart.getTime());
        tempLeft.setTime(temp.getTime());
        tempLeft.set(Calendar.HOUR, -1);

        int i = 0;

        while (temp.before(dateEnd)){
            tempPQ = new PQ<>();

            while(i < list.size() && box.getCalendar(i).before(temp)) {
                String date = Patterns.DATE_FORMAT.format(temp.getTime());
                String name = box.getName(i);

                tempPQ.add(box.getCost(i), date + ": " + name);
                i++;
            }
            queues.add(tempPQ);
            temp.add(Calendar.HOUR, 1);

            i = 0;
            }

        return queues;
    }

    public static void writeToFile(List<PQ<Integer, String>> queues) {
        try {
            PrintWriter writer = new PrintWriter("output.txt");

            for(int i = 0; i < queues.size(); i++) {
                writer.write(queues.get(i).peek().value.toString() +"\n");
            }

            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
