/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biccodescraper;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jaked
 */
public class BicCodeScraper {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            boolean lastPage = false;
            int pageNumber = 1;
            while(!lastPage){
                try{
                    Document webpage = Jsoup.connect("https://www.bic-code.org/bic-letter-search/?resultsperpage=500&searchterm="+letter+"&cpage="+pageNumber).get();
                    Elements bicCodes = webpage.select("[data-label='Code']");
                    if(bicCodes.size() < 1){
                        break;
                    }
                    for(Element code: bicCodes){
      
                        System.out.println(code.text());
                    }
                    pageNumber++;
                }   
                catch(Exception e){
                    continue;
                }
            }
            
        }

       

    }
    
}
