import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewCollector {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    private static ArrayList<String> words; 
    private static ArrayList<Double> values;
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
    }
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
        if (!productList.contains(prodReview.getName())){
            productList.add(prodReview.getName());
        }
        System.out.println("Size: " + reviewList.size());
    }
    /*public static void processSentiments(String filePath) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList(filePath);

        // Regex pattern to match word,decimal pairs
        words = new ArrayList<>();
        values = new ArrayList<>();
        for (String sentiment : lines){
            Pattern w = Pattern.compile("([A-Za-z0-9]+)\\,(\\-?\\d+\\.?\\d*)");
            Matcher wm = w.matcher(sentiment);
            boolean found = wm.find(); 
            String word = found ? wm.group(1) : ""; 
            Double value = found ? Double.parseDouble(wm.group(2)) : 0.0;
            words.add(word);
            values.add(value);
        }
    }
    public double getSentiment(String word){
        for (int i = 0; i < words.size(); i++){
            if (words.get(i).equalsIgnoreCase(word)){
                return values.get(i);
            }
        }
        return 0.0;
    }*/
    public double getSentiments(String wordCheck) {
        // Read lines from sentiments.txt
        ArrayList<String> lines = FileOperator.getStringList("sentiments.txt");

        // Regex pattern to match word, decimal pairs
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]+),(-?\\d+\\.\\d+)");
        // Process each line
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String word = matcher.group(1); // Extract the word
                Double value = Double.parseDouble(matcher.group(2)); // Extract the value
                // Add to instance variables
                words.add(word);
                values.add(value);
                if (word.equals(wordCheck)){
                        // System.out.println(word+"     "+value);
                        return value;
                 } //if wordCheck end
            }//if matcher ends
        } //for each line ends
    } //method ends
    public double getNumGoodReviews(String prodName){
        double rating = 0.0;
        for (ProductReview review : reviewList){
            if (review.getName().equals(prodName)) {
                String[] revWords = review.getReview().split(" ");
                for (String w : revWords){
                    rating += getSentiments(w); 
                }
            }
        }
        return rating;
    }
    /* public static void main(String[] args) {
        // Process sentiments once at the beginning
        processSentiments("sentiments.txt");
        
        ReviewCollector collector = new ReviewCollector();
        ArrayList<String> lines = FileOperator.getStringList("product.txt");
        
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<String> rev = new ArrayList<>();
        
        for (String line : lines){
            if (line.startsWith("Product: ")){
                prod.add(line);
            }
            if (line.startsWith("Review: ")){
                rev.add(line);
            }
        }
        
        for (int i = 0; i < prod.size(); i++){
            String pname = prod.get(i).replace("Product: ", "");
            String preview = rev.get(i).replace("Review: ", "");
            ProductReview p = new ProductReview(pname, preview);
            collector.addReview(p);
        }

        int num = 1;
        for (ProductReview review : collector.reviewList){
            if (num <= 4){
                double sentiment = collector.getNumGoodReviews(review.getName());
                String printStr = "\nProduct: " + review.getName() + 
                                 "\nReview: " + review.getReview() + 
                                 "\nSentiment: " + sentiment + 
                                 "\n -----";
                System.out.println(printStr);
                num++;
            }
        }
    }*/
    public static void main(String[] args){

        ReviewCollector reviewCollector= new ReviewCollector();
        ArrayList<String> lines =FileOperator.getStringList("product.txt");
        Pattern productPattern = Pattern.compile("Product:\\s*(.+)");
        Pattern reviewPattern = Pattern.compile("Review:\\s*(.+)");

        String productName = null;
        String review = null;
        for (String line : lines) {
            Matcher productMatcher = productPattern.matcher(line);
            Matcher reviewMatcher = reviewPattern.matcher(line);
            if (productMatcher.find()) {
                productName = productMatcher.group(1);
            } else if (reviewMatcher.find()) {
                review = reviewMatcher.group(1);
            }
            if (productName != null && review != null) {
                ProductReview product = new ProductReview(productName, review);
                reviewCollector.addReview(product);
                System.out.println(product);
            }
        }
    }
}
