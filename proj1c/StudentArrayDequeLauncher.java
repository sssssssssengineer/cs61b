/** If your computer is set up properly, this file should
  * compile when moved into the same directory as 
  * StudentArrayDeque.class, or if you're using
  * IntelliJ, after you have imported proj1b.jar */
public class StudentArrayDequeLauncher {
    public static void main(String[] args) {
        Deque<Character> sad1 = new ArrayDeque<>();
        sad1=Palindrome.wordToDeque("doublekill");
        sad1.printDeque();
        boolean t = Palindrome.isPalindrome("racecar");
        System.out.print(t);
        OffByOne offby1 = new OffByOne();
        offby1.equalChars('a', 'b');
        OffByN offby5 = new OffByN(5);
        offby5.equalChars('a', 'f');
        OffByOne offby11 = new OffByOne();
        boolean tt = Palindrome.isPalindrome("sbbecar",offby11);
        System.out.print(tt);
    }
} 
