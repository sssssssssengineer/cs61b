public class SList {
    private class SNode {
        public int item;
        public SNode next;

        public SNode(int item, SNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private SNode front;

    public void insertFront(int x) {
        front = new SNode(x, front);
    }

    public static int[] insert(int[] x, int item, int position) {
        int[] y = new int[x.length];
        int i=0;
        while (i!=position){
            y[i]=x[i];
            i+=1;
        }
        y[i]=item;
        while (i!=x.length){
            y[i+1]=x[i];
            i+=1;
        }
        return y;
    }

    public static void reverse(int[] x) {
    int i=0;
        int m;
        while (i!=x.length){
            m=x[i];
            x[i]=x[x.length-i];
            x[x.length-i]=m;
            i+=1;
        }
    }
    }
