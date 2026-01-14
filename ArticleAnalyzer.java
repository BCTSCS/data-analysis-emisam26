import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 

    public ArticleAnalyzer(){
        stopWords = new ArrayList<>();
        articles = new ArrayList<>();
    }

    public void addStopWord(String word){
        stopWords.add(word);
    }

    public void addArticle(Article article){
        articles.add(article);
    }
    //use Pattern and matcher to create
    public Article parseJson(String jsonLine){
        String link = "";
        String headline = "";
        String category = "";
        String description = "";
        String author = "";
        String date = "";
        
        Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(jsonLine);
            
        while (matcher.find()) {
            String fieldName = matcher.group(1);
            String fieldValue = matcher.group(2);
            switch (fieldName) {
                case "link" -> link = fieldValue;
                case "headline" -> headline = fieldValue;
                case "category" -> category = fieldValue;
                case "description", "short_description" -> description = fieldValue;
                case "author", "authors" -> author = fieldValue;
                case "date" -> date = fieldValue;
                }
        }
        return new Article(link, headline, category, description, author, date);
    }
    //remove stop words from Description
    public String removeStopWords(String text){
        String result = "";
        String[] words = text.split(" ");
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!stopWords.contains(cleanWord)) {
                result += word + " ";
            }
        }
        return result.trim();
    }

    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> stopwords = FileOperator.getStringList("stopwords.txt");
        for (String word:stopwords){
            analyzer.addStopWord(word);
        }
        ArrayList<String> totalArticles = FileOperator.getStringList("News_Category_Dataset_v3.json");
        for (String art:totalArticles){
            Article a = analyzer.parseJson(art);
            analyzer.addArticle(a);
        }

        for (Article article : analyzer.articles) {
            String originalDescription = article.getDescription();
            String cleanedDescription = analyzer.removeStopWords(originalDescription);
            System.out.println("Headline: " + article.getHeadline());
            System.out.println("Description: " + cleanedDescription);
            System.out.println("-------------");
        }
    }

}
