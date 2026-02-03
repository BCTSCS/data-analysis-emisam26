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
    public static void processSentiments(String filePath) {
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
    }
    public int getNumGoodReviews(String prodName){
        double rating = 0.0;
        int goodCount = 0;
        for (ProductReview review : reviewList){
            if (review.getName().equals(prodName)) {
                String[] revWords = review.getReview().split(" ");
                for (String w : revWords){
                    rating += getSentiment(w); 
                }
            }
            if (rating > 1){
                goodCount+=1;
            }
            rating = 0.0;
        }
        return goodCount;
    }
    public static void main(String[] args){
        ReviewCollector reviewCollector= new ReviewCollector();
        processSentiments("sentiments.txt");
        ArrayList<String> lines =FileOperator.getStringList("product.txt");
        Pattern productPattern = Pattern.compile("Product:\s*(.+)");
        Pattern reviewPattern = Pattern.compile("Review:\s*(.+)");

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
                // System.out.println(product);
                productName = null;
                review = null;
            }
        }
        for (String product : reviewCollector.productList){
            System.out.println("--------\nProduct: " + product + "\nNumber of Good Reviews: " + reviewCollector.getNumGoodReviews(product));
        }
    }
}
