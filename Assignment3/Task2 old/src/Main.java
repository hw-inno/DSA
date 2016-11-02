import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String yearReg = "(201[6-9]|202[0-9])";
    final static String monthReg = "(0[1-9]|1[0-2])";
    final static String dayReg = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])";
    final static String hourReg = "([0-1][0-9]|2[0-3])";
    final static String minReg = "([0-5][0-9])";
    final static String regex = yearReg + '-' + monthReg + '-' +
            dayReg + 'T' + hourReg + ':' + minReg;

    final static Pattern patternDate = Pattern.compile(regex);
    final static Pattern patternCost = Pattern.compile("[0-9]+");
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    static List<String> list = new ArrayList<>();
    static Calendar dateStart, dateEnd;
    static int[] costs;
    static Calendar[] calendar;
    static String[] names;

    public static void main(String[] args) {
        readFromFile();
        getBossTime();
        Box box = getClientsTime();
        List<PQ<Integer, Object>> qList = justDoIt(box);
        writeToFile(qList);
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
        Matcher matcherDate = patternDate.matcher(boss);

        matcherDate.find();
        String from = matcherDate.group();

        matcherDate.find();
        String to = matcherDate.group();

        try {
            dateStart = Calendar.getInstance();
            dateEnd = Calendar.getInstance();

            dateStart.setTime(format.parse(from));
            dateEnd.setTime(format.parse(to));

            dateEnd.set(Calendar.MINUTE, 1); // To include upper bound while looping in calender

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        list.remove(0); // Remove boss'es time
    }

    public static Box getClientsTime() {
        /*calendar = new GregorianCalendar[list.size()];
        costs = new int[list.size()];
        names = new String[list.size()];*/
        Box box = new Box(list.size());

        int i = 0;
        Matcher matcherDate, matcherCost;

        for (String s : list) {
            matcherDate = patternDate.matcher(s);
            matcherCost = patternCost.matcher(s);
            matcherDate.find();
            matcherCost.find();

            try {
                /*costs[i] = Integer.parseInt(matcherCost.group());
                calendar[i] = Calendar.getInstance();
                calendar[i].setTime(format.parse(matcherDate.group()));
                int index = list.get(i).indexOf(':');
                names[i] = list.get(i).substring(0, index);
                */
                box.setCost(i, Integer.parseInt(matcherCost.group()));
                box.setCalendar(i, Calendar.getInstance());
                box.getCalendar(i).setTime(format.parse(matcherDate.group()));

                int index = list.get(i).indexOf(':');
                box.setName(i, list.get(i).substring(0, index));

                i++;
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return box;
    }

    public static List<PQ<Integer, Object>> justDoIt(Box box) {
        List<PQ<Integer, Object>> qList = new ArrayList<>();
        PQ<Integer, Object> tempQ;

        Calendar temp = new GregorianCalendar();
        Calendar tempLeft = new GregorianCalendar();

        temp.setTime(dateStart.getTime());
        tempLeft.setTime(temp.getTime());
        tempLeft.set(Calendar.HOUR, -1);

        int i = 0, j = 0;

        while (temp.before(dateEnd)){
            tempQ = new PQ<>();

            /*while(i < list.size() && calendar[i].before(temp)) {
                tempQ.add(costs[i], format.format(temp.getTime()) + ": " + names[i]);
                i++;
            }*/

            while(i < list.size() && box.getCalendar(i).before(temp)) {
                tempQ.add(box.getCost(i), format.format(temp.getTime()) + ": " + box.getName(i));
                i++;
            }
                i = 0;

                qList.add(tempQ);

                temp.add(Calendar.HOUR, 1);
                j++;
            }


        return qList;
    }

    public static void writeToFile(List<PQ<Integer, Object>> qList) {
        try {
            PrintWriter writer = new PrintWriter("output.txt");

            for(int i = 0; i < qList.size(); i++) {
                //if(qList.get(i).peek() != null)
                writer.write(qList.get(i).peek().value.toString() +"\n");
            }

            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
