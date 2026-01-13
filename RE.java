import java.util.ArrayList;
public class RE {
    public static void main(String[] args) {

        //phone number
        String phonere = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("123-456-7890");
        numbers.add("(123) 456-7890");
        numbers.add("(123)-456-7890");
        numbers.add("1234567890");
        for (String number:numbers){
            if (number.matches(phonere) == true) {
                System.out.println("Phone number "+number+" is valid.");
            } else {
                System.out.println("Phone number "+number+" is not valid");
            }
        }


        String wordre = "[A-Z]{1}[a-z]+";
        String para = "It is time to go to to the store. I want to buy Egglands best eggs, Lactaid milk, and Kerrygold butter. Please give me $30 to buy it all. Thank you!";
        System.out.println(para);
        String[] wordArray = para.split("\\s+"); 
        ArrayList<String> words = new ArrayList<>();
        for (String word : wordArray) {
            words.add(word);
        }
        for (String w:words){
            if (w.matches(wordre) == true) {
                System.out.println("The word "+w+" starts with a capital letter.");
            }
        }




        // ArrayList<String> posts = FileOperator.getStringList("posts.txt");

        // System.out.println(posts);
        //iterate each post and find hashtag #\w
        // for (String post:posts){
        //     String result = post.matches(re);
        // }
        // Boolean result = text.matches(re);
        // System.out.println("Does '"+text+"' match '"+re+"'? = "+result);
        // System.out.println(result);
    }
}
