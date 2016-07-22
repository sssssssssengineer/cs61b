import java.util.Scanner;

/**
 * Created by Administrator on 2016/7/21.
 */
public class A {
    int i;
    static int j=3;

    public void methodB(){
        i = 5; //cannot use since i is not static
        j = 2; //can use.

        int k = 3; //k is local variable and can be used no problem

        //if you want to access i
        //A a = new A();
        //A.i = 5; //can use.
        //a.i = 5; // it should be non-capital "a" right?
    }
    public static void methodA(A a){
        //i = 5; //cannot use since i is not static
        j = 2; //can use.

        int k = 3; //k is local variable and can be used no problem

        //if you want to access i
        //A a = new A();
        //A.i = 5; //can use.
        a.i = 5; // it should be non-capital "a" right?
    }
    public static void main(String[] args){
        A a= new A();
        a.i=8;
        methodA(a);
        a.methodB();
        System.out.println(a.i);
        System.out.println(a.j);
    }
}

