/**
 * Created by Administrator on 2016/7/24.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word){
        int i=0;
        Deque a = new ArrayDeque();
        char b;
        while (i!=word.length()){
            b=word.charAt(i);
            a.addLast(word.charAt(i));
            i+=1;
        }
        return a;
    }

    public static boolean isPalindrome(String word){
        int i=0;
        Deque a = wordToDeque(word);
        while (i!=word.length()){
            if (i==word.length()-i-1){i+=1;continue;}
            if (a.get(i)!=a.get(word.length()-i-1)){
                return false;
            }
            i+=1;
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc){
        int i=0;
        Deque<Character> a = wordToDeque(word);
        while (i!=word.length()){
            if (i==word.length()-i-1){i+=1;continue;}
            if (!cc.equalChars(a.get(i),a.get(word.length()-i-1))){
                return false;
            }
            i+=1;
        }
        return true;
    }
}
