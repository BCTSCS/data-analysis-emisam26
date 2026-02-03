import java.util.ArrayList;

public class ReviewCollector {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;
    public ReviewCollector(){
        reviewList = new ArrayList<>();
        productList = new ArrayList<>();
    }
    public void addReview(ProductReview prodReview){
        reviewList.add(prodReview);
        if (!productList.contains(prodReview.getName())){
            productList.add(prodReview.getName());
        }
    }
    public int getNumGoodReviews(String prodName){
        int count = 0;
        for (ProductReview review : reviewList){
            if (review.getName().equals(prodName)){
                if (review.getReview().contains("best")){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        ReviewCollector collector = new ReviewCollector();
        ArrayList<String> lines = FileOperator.getStringList("product.txt");
        ArrayList<String> prod = new ArrayList<>();
        ArrayList<String> rev = new ArrayList<>();
        // Product:\s([^\n]*)\nReview:\s([^\n]*)
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
                System.out.println("Product: " + review.getName() + "\nReview: " + review.getReview() + "Sentiment:" + review.);
                int bestnum = collector.getNumGoodReviews(review.getName());
                System.out.println(bestnum);
                num++;
            }
        }
    }
}
