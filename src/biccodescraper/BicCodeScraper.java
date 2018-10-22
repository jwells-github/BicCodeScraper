/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biccodescraper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File file = new File("BicCodes-Output.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(BicCodeScraper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (char letter = 'a'; letter <= 'z'; letter++) {
            BufferedWriter bw = null; 
            boolean lastPage = false;
            int pageNumber = 1;
            while(!lastPage){
                try{
                    Document webpage = Jsoup.connect("https://www.bic-code.org/bic-letter-search/?resultsperpage=500&searchterm="+letter+"&cpage="+pageNumber).get();
                    Elements bicCodes = webpage.select("[data-label='Code']");
                    if(bicCodes.size() < 1){
                        break;
                    }
                    try{
                        bw = new BufferedWriter(new FileWriter(file, true)); 
                        for(Element code: bicCodes){
                            bw.append(code.text());
                            bw.newLine();
                            System.out.println(code.text());
                        }
                    }
                    catch (IOException ioe){
                        System.out.println(ioe);
                    }
                    finally{
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                 
                    pageNumber++;
                }   
                catch(IOException e){
                    System.out.println(e);
                }
            }
            
        }

       

    }
    
}
