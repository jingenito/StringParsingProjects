import java.util.Scanner;

public class Parser_Implementation
{
    public static void main(String[] args){
        // Using Scanner for Getting Input from User 
        Scanner in = new Scanner(System.in);
  
        String exp = "";
        do{
            System.out.println("Enter an expression below.");
            exp = in.nextLine();
        }while(exp == "");
        
        double d = 0;
        try{
            d = MDASParser.evaluateExp(exp);
        }catch(Exception ex){
            System.out.println("Error");
            return;
        }

        System.out.println("" + d);
    }
}