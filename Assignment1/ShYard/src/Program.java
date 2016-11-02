import java.io.*;

public class Program {
    static LinkedQueue<Character> queue = new LinkedQueue<Character>();
    static LinkedStack<Integer> stack = new LinkedStack<Integer>(); // Stack for operators when converting to RPM
    static LinkedStack<Double> stack2 = new LinkedStack<Double>(); // Stack2 for numbers when counting
    static final String operators = "-+/*";

    public static void main(String[] args){
        readFromFile();
    }

    public static void readFromFile(){
        try {
            File input = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(input));
            String s = br.readLine();

            convert(s);
            print();
            count();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void convert(String s){

        char token;
        int op;

        for (int i = 0; i < s.length(); i++){
            token = s.charAt(i);

            if(token == ' ')
                continue;

            op = operators.indexOf(token);

             if(op != -1){  // if token is an operator

                if(stack.isEmpty()){
                    stack.push(op);
                }
                else{
                    while (!stack.isEmpty()){
                        int operInStack = stack.top() / 2;
                        int operIncoming = op / 2;
                        if ((operInStack > operIncoming) || (operInStack == operIncoming))
                                queue.enqueue(operators.charAt(stack.pop()));
                        else break;
                    }
                    stack.push(op);
                }
            }

            else if(token == '('){
                    stack.push(-10);
            }

            else if ( token == ')'){
                while ( stack.top() != -10)
                    queue.enqueue(operators.charAt(stack.pop()));
                 stack.pop();
            }
            else {
                queue.enqueue(token);
            }
        }
        while (!stack.isEmpty())
            queue.enqueue(operators.charAt(stack.pop()));
        stack.pop();
    }

    public static void count(){
        int token;
        int result = 0;
        Character c;

        while(!queue.isEmpty()){
            c = queue.dequeue();
            token = operators.indexOf(c);
            String fractionalNum;

        // this try block handles floating point numbers
        try {
            if(queue.first() == '.'){
                int k = c - 48;
                fractionalNum = "" + k + queue.dequeue() + queue.dequeue() + queue.dequeue();
                stack2.push(Double.parseDouble(fractionalNum));
                continue;
            }
        }
        catch (NullPointerException ex){}

            if (token == -1){  // If token is a number, push to the stack
                stack2.push( (double) (c - 48));
                continue;
            }

           /* try {
                if (queue.first() >= 48 && queue.first() <= 57) {


                    stack2.push(Double.parseDouble("" + (c - 48) + queue.dequeue()));
                }
            }
            catch (NullPointerException ex){}*/

                switch (c){
                        case '*':  stack2.push(stack2.pop() * stack2.pop());
                            break;
                        case '+': stack2.push(stack2.pop() + stack2.pop());
                            break;
                        case '-':stack2.push( -stack2.pop() + stack2.pop());
                            break;
                        case '/':stack2.push(1/stack2.pop() * stack2.pop());
                            break;
                }
        }
        writeToFile(stack2.pop());
    }

    public static void writeToFile(Double result){
        try{
            PrintWriter writer = new PrintWriter("output.txt");
            writer.format("%.2f", result);
            writer.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void print(){
        queue.printAll();
    }
}
