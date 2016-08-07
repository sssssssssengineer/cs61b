# CS61B

Googled how to import existing projects to github...and how to delete them.
[how to add projects using command line](https://help.github.com/articles/adding-an-existing-project-to-github-using-the-command-line/)

##week3
made some exciting changes to lab3/Flik 
test Flik.java with TestFlik.java around 128 where the program went wrong(127,128,129).Turns out it is the Flik.java 's problem.

Then I have to rule out the possibility that HorribleSteve is also no good. So I eliminated the Flik.java from HorribleSteve and repalced it with my own code. works fine.
So it is the Flik.java only.

This kind of testing procedure is interesting:
* test where it went wrong
* rule out the possibility that other file is also no good.


```java
    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(128, 128));
        assertTrue(Flik.isSameNumber(129, 129));
    }
```
`just messing with it`

##proj1
getting better, pretty exciting, add sth in the morning.
* methods that share the name with class that contains them, will be carried out when the class is instantiated.
* generic array and generic linkedlist both can contain different types at the same. types don't need to be specified.
* remember to renew all the attributes of the class
* the instantiation of generic array is unique
* if the first situation is done before a loop, then the determine statements should be made at the beginning of the loop

##finished hw2, and gave a very interesting solution to the back wash problem
use stopwatch to calculate the computing time:
```java
Stopwatch sw = new Stopwatch();
blahblahblah
return sw.elapsedTime();
```

an interesting way of recursion:
specify the end conditions first.
```java
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
```
