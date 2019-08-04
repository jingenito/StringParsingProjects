public class MDASParser
{
    public static double evaluateExp(String exp) throws Exception{
        return run_AS_parse(exp);
    }

    private static double run_MD_parse(String exp) throws Exception{
        char[] thisLevel = exp.toCharArray();
        int index = find_MD_index(thisLevel);
        StringBuilder leftSide = new StringBuilder();
        StringBuilder rightSide = new StringBuilder();

        double num1 = 0;
        double num2 = 0;

        if(index > -1){
            //mult or div found
            //store value
            //run same level
            leftSide.append(thisLevel, 0, index);
            num1 = run_MD_parse(leftSide.toString());

            int offSet = index + 1;
            rightSide.append(thisLevel, offSet, thisLevel.length - offSet);
            num2 = run_MD_parse(rightSide.toString());

            return num1 * num2;
        }
        
        return Double.parseDouble(exp);
    }

    private static double run_AS_parse(String exp) throws Exception{
        char[] thisLevel = exp.toCharArray();
        int index = find_AS_index(thisLevel);
        StringBuilder leftSide = new StringBuilder();
        StringBuilder rightSide = new StringBuilder();

        double num1 = 0;
        double num2 = 0;

        if(index > -1){
            //add or sub found
            //store value
            //eval next level
            leftSide.append(thisLevel, 0, index);
            String lsStr = leftSide.toString();
            //run same level on left side
            num1 = run_AS_parse(lsStr);

            int offSet = index + 1;
            rightSide.append(thisLevel, offSet, thisLevel.length - offSet);
            String rsStr = rightSide.toString();
            //run same level on right side
            num2 = run_AS_parse(rsStr);

            return num1 + num2;
        }

        return run_MD_parse(exp);
    }

    private static int find_MD_index(char[] exp){
        return find(exp, '*', '@');
    }

    private static int find_AS_index(char[] exp){
        return find(exp, '+', '-');
    }

    private static int find(char[] arr, char value1, char value2){
        for(int i = 0; i < arr.length; i++){
            char c = arr[i];
            if(c == value1 || c == value2){ return i;}
        }
        return -1;
    }
}