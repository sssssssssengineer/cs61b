/**
 * Created by Administrator on 2016/7/30.
 */
package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import editor.LinkedListDeque;
import javafx.scene.text.Text;

import javafx.scene.input.KeyCode;

public class readAsLinkedList {
    private static LinkedListDeque<Character> readLinkedList = new LinkedListDeque<>();

    public static LinkedListDeque<Character> read(String input){


        try {
            File inputFile = new File(input);
            FileReader reader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int intRead = -1;


            // Keep reading from the file input read() returns -1, which means the end of the file
            // was reached.
            while ((intRead = bufferedReader.read()) != -1) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = (char) intRead;
                /**if (charRead == 13){
                    readLinkedList.removeLast();
                    String newline = "\r";
                    readLinkedList.addLast(newline.charAt(0));
                }else if(charRead == 10){
                    readLinkedList.removeLast();
                    String newline = "\n";
                    readLinkedList.addLast(newline.charAt(0));
                }else {*/
                    readLinkedList.addLast(charRead);

                /***/
/**
                if (charRead == 10){
                    readLinkedList.NewLineAndArrayitem(readLinkedList.getLast());
                }*/
            }
            char a = 13;
            char b = 10;
            //System.out.println("linefirsttotal@:"+readLinkedList.LineFirstsTotal.numbers);
            readLinkedList.WrapTillEnd(b,a);
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
        return readLinkedList;
    }
}
