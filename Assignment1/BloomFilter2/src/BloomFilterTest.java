import java.util.Scanner;

public class BloomFilterTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bloom Filter Test\n");

        //Set capacity and key size");
        BloomFilter bf = new BloomFilter(100 , 2);

        char ch;
        bf.add(2);
        bf.add(1);
        bf.add(1);
        bf.add(5);
        bf.add(6);
        bf.add(777777);
        System.out.println("Search result : "+ bf.contains( new Integer(scan.nextInt()) ));

       /* System.out.println("Enter integer element to insert");
        bf.add( new Integer(scan.nextInt()) );
        System.out.println("Enter integer element to search");
        System.out.println("Search result : "+ bf.contains( new Integer(scan.nextInt()) ));
        System.out.println("\nSize = "+ bf.getSize() );*/
    }
}