import java.util.Calendar;
import java.util.GregorianCalendar;

public class Box {
    private Calendar[] calendar;
    private int[] costs;
    private String[] names;
    private int size;

    public Box(int size){
        size = size;
        calendar = new GregorianCalendar[size];
        costs = new int[size];
        names = new String[size];
    }

    private boolean checkIndex(int i){
        if(i < 0 || i >= size)
            return false;
        return true;
    }

    /* --- Getters and Setters --- */
    public String getName(int i) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException()*/;
        return names[i];
    }
    public void setName(int i, String name) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException();*/
        names[i] = name;
    }
    public Calendar getCalendar(int i) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException();*/
        return calendar[i];
    }
    public void setCalendar(int i, Calendar cal) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException();*/
        calendar[i] = cal;
    }
    public int getCost(int i) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException();*/
        return costs[i];
    }
    public void setCost(int i, int cost) {
        /*if(!checkIndex(i))
            throw new IndexOutOfBoundsException();*/
        costs[i] = cost;
    }
    /* --- End of Getters and Setters --- */
}
