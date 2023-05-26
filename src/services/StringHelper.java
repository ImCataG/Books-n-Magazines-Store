package services;

import models.Book;
import models.Magazine;

import java.util.Dictionary;

public class StringHelper {

    // for dictionary of <Book, Integer> return a string with all the books and their quantities formatted as a list of tuples
    public static String dictionaryBookToString(Dictionary<Book, Integer> dictionary){
        String ans = "[";
        // for every entry in the dictionary get isbn of the book and quantity
        for (int i = 0; i < dictionary.size(); i++) {
            ans += "(";
            Book book = dictionary.keys().nextElement();
            Integer quantity = dictionary.get(book);
            ans += book.getIsbn() + ", " + quantity;
            if(i != dictionary.size() - 1){
                ans += "), ";
            }
            else{
                ans += ")";
            }
        }
        ans += "]";
        return ans;
    }

    public static String dictionaryMagazineToString(Dictionary<Magazine, Integer> dictionary){
        String ans = "[";
        // for every entry in the dictionary get isbn of the book and quantity
        for (int i = 0; i < dictionary.size(); i++) {
            ans += "(";
            Magazine magazine = dictionary.keys().nextElement();
            Integer quantity = dictionary.get(magazine);
            ans += magazine.getIssn() + ", " + quantity;
            if(i != dictionary.size() - 1){
                ans += "), ";
            }
            else{
                ans += ")";
            }
        }
        ans += "]";
        return ans;
    }
}
