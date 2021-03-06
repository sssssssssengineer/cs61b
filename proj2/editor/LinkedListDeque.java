/**
 * Created by Administrator on 2016/7/22.
 */
package editor;

import edu.princeton.cs.algs4.Stopwatch;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.lang.reflect.Array;
import groovy.swing.factory.LineBorderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import editor.Editor;

public class LinkedListDeque<Item> {
    private int size = 0;
    public List sentinel;
    private List P;//pointer   delete
    public List CurrentPos;
    public ArrayDeque<List> LineFirsts = new ArrayDeque<>();
    public ArrayDeque<List> LineFirstsArtificial = new ArrayDeque<>();
    public ArrayDeque<List> LineFirstsTotal = new ArrayDeque<>();
    public int CurrentX;
    public int CurrentY;
    private int CurrentLine;
    private int line=0;
    public Map<Integer,Integer> countxMap = new HashMap<Integer, Integer>();
    public double wordHeight;

    //: Creates an empty linked list deque.
    //this runs when the LinkedListDeque class is instantiated.
    public LinkedListDeque() {
        sentinel = new List(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        P = sentinel;//delete
        LineFirsts.addFirst(sentinel);
        LineFirstsTotal.addFirst(sentinel);
        countxMap.put(0,0);
        temptext.setFont(Font.font(fontName, fontSize));
        char temp = 97;
        wordHeight=textbuilder((Character) temp).getLayoutBounds().getHeight();
    }

    //}
    //create list
    public class List {
        public List next;
        public List prev;
        public Item items;

        //add an item at the specified place.
        public List(Item itm, List p, List n) {
            items = itm;
            prev = p;
            next = n;
        }
    }

    //: Adds an item to the front of the Deque.
    public void addFirst(Item itm) {
        List pointer = sentinel.next;
        List first = new List(itm, sentinel, sentinel.next);
        sentinel.next = first;
        pointer.prev = first;
        size += 1;
    }

    //: Adds an item to the back of the Deque.
    public void addLast(Item itm) {
        List pointer = sentinel.prev;
        List last = new List(itm, sentinel.prev, sentinel);
        sentinel.prev = last;
        pointer.next = last;
        size += 1;
    }
    //: Adds an item to the back of the Deque.
    public void addAfterCurrentPos(Item itm) {
        List CurrentNext = CurrentPos.next;
        List addList = new List(itm, CurrentPos, CurrentNext);
        CurrentPos.next = addList;
        CurrentNext.prev = addList;
        size += 1;
        CurrentPos = addList;
        /**
        System.out.print("@"+CurrentPos.prev.items);
        System.out.print("@"+CurrentPos.items);
        System.out.print("@"+CurrentPos.next.items);
        System.out.print("@"+sentinel.prev.items);*/
    }
    /**add at pos*/
    public void addAtPos(List Pos, Item itm){
        List Posnext = Pos.next;
        List addList = new List(itm, Pos, Posnext);
        Pos.next = addList;
        Posnext.prev = addList;
    }


    //: Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (sentinel.next.items == null) {
            return true;
        } else {
            return false;
        }
    }

    //: Returns the number of items in the Deque.
    public int size() {
        return size;
    }

    //: Prints the items in the Deque from first to last, separated by a space.
    public void printDeque() {
        int i = 0;
        List pointer = sentinel.next;
        while (i != size) {
            System.out.print(pointer.items + " ");
            i += 1;
        }
    }

    //: Removes and returns the item at the front of the Deque.
    // If no such item exists, returns null.
    public Item removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        List pointer = sentinel.next.next;
        Item a = sentinel.next.items;
        sentinel.next.items = null;
        sentinel.next = pointer;
        pointer.prev = sentinel;
        size -= 1;
        return a;
    }

    //: Removes and returns the item at the back of the Deque.
    // If no such item exists, returns null.
    public Item removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        List pointer = sentinel.prev.prev;
        Item a = sentinel.prev.items;
        sentinel.prev.items = null;
        sentinel.prev = pointer;
        pointer.next = sentinel;
        size -= 1;
        return a;
    }
    /**remove at CurrentPos*/
    public Item removeAtCurrentPos() {
        if (CurrentPos == sentinel) {
            return null;
        }
        List CurrentPrev = CurrentPos.prev;
        List CurrentNext = CurrentPos.next;
        Item a = CurrentPos.items;
        CurrentPos.items = null;
        CurrentPrev.next = CurrentNext;
        CurrentNext.prev = CurrentPrev;
        size -= 1;
        CurrentPos = CurrentPrev;
        return a;
    }


    //: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public Item get(int index) {
        //iteratively
        if (index > size - 1) {
            return null;
        }
        List pointer = sentinel.next;
        int i = 0;
        while (i != index) {
            pointer = pointer.next;
            i+=1;
        }
        return pointer.items;
    }

    //: Same as get, but uses recursion.
    public Item getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        P = P.next;
        if (index!=0) {
            return getRecursive(index-1);
        } else {
            return P.items;
        }
    }

    public class ArrayDeque<Item> {
        public int size = 8;//initial spaces
        public int numbers = 0;//number of items
        public int nextFirst;
        public int nextLast;
        public Item[] items;


        //: Creates an empty linked list deque.
        //this runs when the LinkedListDeque class is instantiated.
        public ArrayDeque() {
            items = (Item[]) new Object[8];//different when instantiating a generic array.
            nextFirst = 0;
            nextLast = 1;
        }

        //add out of size
        public Item[] reSize() {
            int inisize = size;
            size *= 2;//final size
            Item[] newitems = (Item[]) new Object[size];
            nextLast += 1;
            int i = nextLast;
            //nextFirst, not final yet

            while (nextLast != nextFirst + inisize) {
                if (i >= inisize) {
                    i -= inisize;
                }
                if (i < 0) {
                    i += inisize;
                }
                newitems[nextLast] = items[i];
                i += 1;

                nextLast += 1;
            }
            return newitems;
        }

        //: Adds an item to the front of the Deque.
        public void addFirst(Item itm) {
            if (nextFirst == nextLast) {
                items = reSize();//final items
            }
            items[nextFirst] = itm;
            nextFirst = nextFirst - 1;//final nextFirst
            if (nextFirst < 0) {
                nextFirst += size;
            }
            numbers += 1;//final numbers
            /**hello world*/
            //countxMap.put(itm,Editor.wholetext.size);
        }


        //: Adds an item to the back of the Deque.
        public void addLast(Item itm) {
            if (nextFirst == nextLast) {
                items = reSize();//final items
            }
            items[nextLast] = itm;
            nextLast = nextLast + 1;
            if (nextLast >= size) {
                nextLast -= size;
            }
            numbers += 1;

        }



        //: Returns true if deque is empty, false otherwise.
        public boolean isEmpty() {
            if (numbers == 0) {
                return true;
            } else {
                return false;
            }
        }

        //: Returns the number of items in the Deque.
        public int size() {
            return numbers;
        }

        //: Prints the items in the Deque from first to last, separated by a space.
        public void printDeque() {
            int i = nextFirst + 1;
            while (i != nextLast) {
                if (i >= size) {
                    i = i - size;
                }
                System.out.print(items[i] + " ");
                i += 1;
            }
        }

        //resize when memory is not effeciently used
        public Item[] reSizeSmaller() {
            if (size / numbers < 4) {
                return items;
            } else {
                int inisize = size;
                if (size / 2 >= 8) {
                    size /= 2;
                } else {
                    size = 8;
                }//final size

                Item[] newitems = (Item[]) new Object[size];
                int i = nextFirst + 1;
                nextFirst = 0;
                nextLast = 1;

                while (nextLast != numbers + 1) {
                    if (i >= inisize) {
                        i -= inisize;
                    }
                    if (i < 0) {
                        i += inisize;
                    }
                    newitems[nextLast] = items[i];
                    i += 1;
                    nextLast += 1;
                }
                return newitems;
            }
        }

        //: Removes and returns the item at the front of the Deque.
        // If no such item exists, returns null.
        public void removeFirst() {
            items = reSizeSmaller();
            nextFirst += 1;
            if (nextFirst >= size) {
                nextFirst -= size;
            }
            items[nextFirst] = null;
            numbers -= 1;
        }

        //: Removes and returns the item at the back of the Deque.
        // If no such item exists, returns null.
        public void removeLast() {
            items = reSizeSmaller();
            nextLast -= 1;
            if (nextLast < 0) {
                nextLast += size;
            }
            items[nextLast] = null;
            numbers -= 1;
        }

        //: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
        // If no such item exists, returns null. Must not alter the deque!
        public Item get(int index) {
            if (index > numbers - 1) {
                return null;
            }
            int i = 0;
            int m = nextFirst + 1;
            if (m >= size) {
                m -= size;
            }
            while (i != index) {
                i += 1;
                m += 1;
                if (m >= size) {
                    m -= size;
                }
            }
            return items[m];
        }


    }

    /**first is outputfilename, seconde is output strings*/
    public void SaveFile(String args){
        String outputFilename = args;
        // Create a FileWriter to write to outputFilename. FileWriter will overwrite any data
        // already in outputFilename.
        try {
            FileWriter writer = new FileWriter(outputFilename);

            List position;
            position=sentinel.next;

            /**use stirngbuilder to convert Character to string.*/
            StringBuilder stringbuilder = new StringBuilder(size);
            while (position.items != null){
                stringbuilder.append(position.items);
                position = position.next;
            }
            String output = stringbuilder.toString();
            writer.write(output);

            System.out.println("Successfully saved file to "
                    + outputFilename);

            writer.close();
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        }catch (IOException ioException){
            System.out.println("Error when copying; exception was: " + ioException);
        }
    }

    /**when there is a newline, create a new List that items point at \n*/
    public void NewLineAndArrayitem(List newline){
        /**bug here, not always at last.*/
        LineFirsts.addLast(newline);
        LineFirstsTotal.addLast(newline);
        /**hello world*/
        NewLinetocountxMap(line);
        line += 1;
    }
    public void NewArtificialLine(List newline){
        LineFirstsArtificial.addLast(newline);
        LineFirstsTotal.addLast(newline);
        NewLinetocountxMap(line);
        line += 1;

    }
    public void NewLinetocountxMap(int line){
        NewLinetocountxMap(line,LineFirstsTotal.get(line),LineFirstsTotal.get(line+1));
    }
    private void NewLinetocountxMap(int line,List start,List end){
        int numberbefore=countxMap.get(line);
        int numberthis=countThisLineNumber(start,end);
        /**next line and the missing /n*/
        countxMap.put(line+1,numberbefore+numberthis+1);
    }
    /**item number of List LineFirsts*/
    public int LineFirstsNumber(){
        return LineFirstsTotal.size();
    }
    /**print next item of every item in Array LineFirsts \n*/
    public void printlineItem(){
        int i = 0;
        while (i!=LineFirstsNumber()) {
            System.out.print(i);
            System.out.println(LineFirstsTotal.get(i).items);
            i+=1;
        }
    }
    /**get the address of the last LIST!!!! in the linkedlist*/
    public List getLast(){
        return sentinel.prev;
    }
    public ArrayDeque removeNull(){
        /**and linefirsttotal linefirstsartificial*/
        int totallinebf = LineFirstsTotal.numbers;
        removeNull(LineFirstsArtificial,0);
        removeNull(LineFirstsTotal,1);
        int totallineaf = LineFirstsTotal.numbers;
        line = line - totallinebf + totallineaf;
        return removeNull(LineFirsts,1);
    }
    /**
     * check if the lineFirsts array contains null, if so, remove it.
     */
    private ArrayDeque<List> removeNull(ArrayDeque<List> LineFirst, int start) {
        if (start <= LineFirst.numbers - 1) {
            if (LineFirst.get(start).items == null) {
                int a = LineFirst.numbers-1;
                int i = 0;

                GenSet temp = new GenSet(LineFirst.numbers);
                /**2 situations:1.the last is removed, 2. item in the middle is removed.*/
                if (a != start) {
                    while (a != start) {
                        temp.put(LineFirst.get(i),i);
                        removeLast();
                        a -= 1;
                        i += 1;
                    }
                    int j = 0;
                    int k = i;
                    while (j != i) {
                        LineFirst.addLast((List) temp.get(k - 1));
                        k -= 1;
                        j += 1;
                    }
                }else {
                    LineFirst.removeLast();
                }
                //System.out.print(" remove");
            }
            start+=1;
            return removeNull(LineFirst,start);
        }else {
            //System.out.print(" end2 "+LineFirst.numbers+"\n");
            return LineFirst;
        }
    }
    private class GenSet<List> {
        /**this is an array of class List*/
        public Object[] obj;

        public GenSet(int s) {
            obj = new Object[s];
        }

        void put(List listA,int i) {
            obj[i] = listA;
        }
        List get(int i) {
            return (List) obj[i];
        }
    }

    /**wrap till the end*/
    public static int countLine = 1;
    private listandlinetype start;
    public void WrapTillEnd(Item newline1,Item newline2){

        start = Wraptillenddistance(LineFirstsTotal.get(0));
        while (start!=null){
            if (start.linetype=="natural") {
                WrapAtPosnewline(start.list, newline1, newline2);
                countLine += 1;
            }else{
                WrapAtPos(start.list, newline1, newline2);
                countLine += 1;
            }
            start = Wraptillenddistance(LineFirstsTotal.get(countLine-1));
        }
    }
    public class listandlinetype{
        private List list;
        private String linetype;
        public listandlinetype(List lis,String lin){
            list = lis;
            linetype = lin;
        }
    }
    /**wrap*/
    public String Wrap(Item newline1,Item newline2){
        /**this is left to revise*/
        //System.out.print(LineFirsts.numbers);
        //System.out.print(LineFirsts.get(LineFirsts.numbers-1).next.items);
        List start = Wrapdistance(LineFirstsTotal.get(LineFirstsTotal.numbers-1));
        if (start!= null) {
            WrapAtPos(start, newline1, newline2);//start is now the position of the space
            //System.out.print(reCulDisplayText());
            return reCulDisplayText();
        } else {return null;}
    }
    /**count the length of a line, from \n to \r*/
    public List Wrapdistance(List start){
        int i = 0;
        double wordlength = 0;
        List space = null;
        while (Math.round(wordlength)<450){
            if (start.next == sentinel){return null;}
            char readchar = CharacterTochar((Character) start.next.items);
            /**is this valid,
             * when reads a CR, it should return the reference of it
             * and maybe add a NewLineAndArrayitem*/
            if (readchar == 10){
                return null;
            }
            if (readchar ==32){space = start.next;}
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            i+=1;
            start=start.next;
        }
        //if (wordlength<450){return null;}
        if (space == null) {
            return start;
        }
        List temp = start;
        char readchar = CharacterTochar((Character) temp.items);
        while (i>0 && readchar!=32){
            i-=1;
            temp=temp.prev;
            readchar = CharacterTochar((Character) temp.items);
        }

        return temp;
    }
    public listandlinetype Wraptillenddistance(List start){
        int i = 0;
        double wordlength = 0;
        List space = null;
        while (Math.round(wordlength)<450){
            if (start.next == sentinel){return null;}
            char readchar = CharacterTochar((Character) start.next.items);
            /**is this valid,
             * when reads a CR, it should return the reference of it*/
            if (readchar == 10){
                //System.out.println("wordlength:"+wordlength);
                listandlinetype temp = new listandlinetype(start.next, "natural");
                return temp;
            }
            if (readchar ==32){space = start.next;}
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            i+=1;
            start=start.next;
            //System.out.println("linefirsttotal:"+LineFirstsTotal.numbers);
        }
        //if (wordlength<450){return null;}
        /**in order to wrap before space*/
        if (space == null) {
            listandlinetype temp = new listandlinetype(start, "artificial");
            return temp;
        }
        /**wordlength at 450 and now wrap*/

        listandlinetype temp1 = new listandlinetype(space, "artificial");
        //System.out.println("wordlength:"+wordlength);
        return temp1;
    }
    /**do the wrap*/
    public void WrapAtPos(List Pos, Item newline1,Item newline2){
        /** add newline after the nearest space on the left
         * or just the rightest of the line*/
        this.addAtPos(Pos,newline1);
        this.addAtPos(Pos,newline2);
        /**this should be NewArtificialLine*/
        this.NewArtificialLine(Pos.next.next);
    }
    public void WrapAtPosnewline(List Pos, Item newline1,Item newline2){
        /** add newline after the nearest space on the left
         * or just the rightest of the line*/
        /**this should be NewArtificialLine*/
        this.NewLineAndArrayitem(Pos);
    }
    /**convert Character to Text*/
    private int fontSize = 12;
    private String fontName = "Verdana";
    private Text temptext = new Text();

    public Text textbuilder(Character C){
        StringBuilder stringbuilder = new StringBuilder(10);
        stringbuilder.append(C);
        String output = stringbuilder.toString();
        temptext.setText(output);
        return temptext;
    }
    private char CharacterTochar(Character C){
        StringBuilder sb = new StringBuilder(1);
        sb.append(C);
        String tempstring = sb.toString();
        char result = tempstring.charAt(0);
        return result;
    }
    public String reCulDisplayText(){
        List position;
        position=sentinel.next;

        /**use stirngbuilder to convert Character to string.*/
        StringBuilder stringbuilder = new StringBuilder(size);
        while (position.items != null){
            stringbuilder.append(position.items);
            position = position.next;
        }
        String output = stringbuilder.toString();
        return output;
    }
    public void CurrentPosToLast(){
        CurrentPos = this.getLast();
    }
    public char getFirst(){
        return CharacterTochar((Character) sentinel.next.items);
    }
    public char getCurrentPos(){
        return CharacterTochar((Character) CurrentPos.items);
    }
    public List returnListCurPos(){
        return CurrentPos;
    }
    public Text getLastText(){
        return textbuilder((Character) this.getLast().items);
    }
    public void CurrentPosLeft(){
        if (CurrentPos != sentinel) {
            CurrentPos = CurrentPos.prev;
            if (CharacterTochar((Character) CurrentPos.items) == 13) {
                CurrentPos = CurrentPos.prev;
                line -= 1;
            }
        }
    }
    public void CurrentPosRight(){
        if (CurrentPos.next != sentinel) {
            CurrentPos = CurrentPos.next;
            if (CharacterTochar((Character) CurrentPos.items) == 10) {
                CurrentPos = CurrentPos.next;
                line += 1;
            }
        }
    }
    public void CurrentPosUp(){
        /**CurrentPos point at the char before the cursor*/
        if (line >0) {
            int linelength = Distance(LineFirstsTotal.get(line - 1));
            CurrentPos = countDistance(LineFirstsTotal.get(line - 1), Math.min(CurrentX, linelength));
            line -= 1;
        }
    }
    public void CurrentPosDown(){
        if (line < LineFirstsTotal.numbers-1) {
            int linelength = Distance(LineFirstsTotal.get(line + 1));
            CurrentPos = countDistance(LineFirstsTotal.get(line + 1), Math.min(CurrentX, linelength));
            line += 1;
        }
    }
    private int Distance(List start){
        double wordlength = 0;
        while (true){
            if (start.next == sentinel){return (int) Math.round(wordlength);}
            char readchar = CharacterTochar((Character) start.next.items);
            if (readchar == 13){return (int) Math.round(wordlength);}
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            start=start.next;
        }
    }
    public List countDistance(List start,int distance){
        /**count the distance and return the List at the specified position*/
        double wordlength = 0;
        start=start.next;
        while (Math.round(wordlength) <= distance){
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            start=start.next;
        }
        return start.prev;
    }
    public int theNumberAtLineFirst(int line){
        return countxMap.get(line);
    }

    public int SplitPosNum(){
        CurrentpRecalibrate();
        return SplitPosNum(LineFirstsTotal.get(line),CurrentX);
    }
    public int SplitPosNum(List start,int distance){
        /**count the distance and return the List at the specified position*/
        double wordlength = 0;
        int i = 0;
        start=start.next;
        while (Math.round(wordlength) < distance){
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            start=start.next;
            i++;
        }
        return i+theNumberAtLineFirst(line);
    }
    private int countListDistance(List start,List end){
        /**count the distance and return the List at the specified position*/
        double wordlength = 0;
        if (start == end){return 0;}
        start = start.next;
        Stopwatch sw4 = new Stopwatch();
        int i =0;

        while (start!=end) {
            wordlength+=textbuilder((Character) start.items).getLayoutBounds().getWidth();
            start=start.next;
            i++;
        }
        System.out.println("line "+line);
        System.out.println("oopss "+i);
        System.out.println("ohchy "+sw4.elapsedTime());
        return (int) Math.round(wordlength+textbuilder((Character) start.items).getLayoutBounds().getWidth());

    }
    private int countThisLineNumber(List start,List end){
        /**count the distance and return the List at the specified position*/
        int i = 0;
        /**avoid mistake at the sentinel, all start should*/
        start=start.next;
        while (true){
            /**.next might be wrong*/
            if (start == end){
                return i;
            }
            start=start.next;
            i++;
        }
    }
    public void CurrentpRecalibrate(){
        //System.out.print(CurrentPos.items);
        /**the length of text is determined by the total line(not the length of a single word)*/
        //Character tempc = 98;
        //Text tempt = textbuilder(tempc);
        //System.out.print(tempt.getLayoutBounds().getHeight());
        Stopwatch sw3 = new Stopwatch();
        CurrentY = (int) Math.round(line *wordHeight);
        System.out.println("aha "+sw3.elapsedTime());
        Stopwatch sw4 = new Stopwatch();
        CurrentX = countListDistance(LineFirstsTotal.get(line),CurrentPos);
        System.out.println("bitching "+sw4.elapsedTime());
        //System.out.print(" X="+CurrentX+" ");
        /**
        double a = getLastText().getLayoutBounds().getWidth());
        sum +=a;
        System.out.print(" a1="+a+" ");
        System.out.print(" a2="+getLastText().getLayoutBounds().getWidth()+" ");
        System.out.print(" s="+sum+"\n");*/
    }
    public void MouseClickPos(double x, double y){
        line =(int) Math.round(y / wordHeight);
        CurrentPos = countDistance(LineFirstsTotal.get(line),(int)Math.round(x));
    }
    private class storedInfo{
        private List storedCurrentPos;
        private Item storedItems;
        private String addOrRemove;

        private storedInfo(List lis1, Item itm, String str){
            storedCurrentPos = lis1;
            storedItems = itm;
            addOrRemove = str;
        }
    }
    public void callstoredInfo(List lis1, Item itm, String str){
        storedInfo a = new storedInfo(lis1, itm, str);
        Undo.push(a);
        //System.out.print(Undo.empty());
    }
    public Stack<storedInfo> Redo = new Stack<storedInfo>();
    public Stack<storedInfo> Undo = new Stack<storedInfo>();
    public void undo(){
        storedInfo temps = Undo.pop();
        Redo.push(temps);
        if (temps.addOrRemove == "add"){/**bug at add*/
            CurrentPos = temps.storedCurrentPos;
            removeAtCurrentPos();
        }else if (temps.addOrRemove == "enter"){
            CurrentPos = temps.storedCurrentPos;
            removeAtCurrentPos();
            removeAtCurrentPos();
        }else if (temps.addOrRemove == "remove"){
            CurrentPos = temps.storedCurrentPos;
            reviveRemoved(temps.storedItems);
        }
    }
    public void redo(){
        storedInfo temps = Redo.pop();
        Undo.push(temps);
        if (temps.addOrRemove == "add"){
            CurrentPos = temps.storedCurrentPos;
            reviveRemoved(temps.storedItems);
            System.out.print("333");
        }else if (temps.addOrRemove == "enter"){/**bug at enter*/
            CurrentPos = temps.storedCurrentPos;
            Character a =(char) 13;
            Character b =(char) 10;
            addAfterCurrentPos((Item) a);
            addAfterCurrentPos((Item) b);
            NewLineAndArrayitem(returnListCurPos());
            System.out.print("222");
        }else if (temps.addOrRemove == "remove"){
            CurrentPos = temps.storedCurrentPos;
            removeAtCurrentPos();
            removeNull();
            System.out.print("111");
        }
    }
    public void reviveRemoved(Item itm){
        List CurrentPrev = CurrentPos.prev;
        List CurrentNext = CurrentPos.next;
        CurrentPrev.next = CurrentPos;
        CurrentNext.prev = CurrentPos;
        CurrentPos.items = itm;
    }
    public String toString(){
        /**List runner = sentinel.next;
        StringBuilder stringbuilder = new StringBuilder(size);

        while (runner.items!=null){
            stringbuilder.append(runner.items);
        }
        String output = stringbuilder.toString();
        return output;*/
        List position;
        position=sentinel.next;

        /**use stirngbuilder to convert Character to string.*/
        StringBuilder stringbuilder = new StringBuilder(1);
        while (position.items != null){
            stringbuilder.append(position.items);
            position = position.next;
        }
        String output = stringbuilder.toString();
        return output;
    }
}
