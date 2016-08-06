package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int Na;//Na*Na
    private int openednumber = 0;
    private boolean[] opened;
    private WeightedQuickUnionUF WQUUF;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        Na = N;
        opened = new boolean[N*N];
        int i = 0;
        while (i != N*N){
            opened [i] = false;
            i++;
        }

        //might change here to avoid back wash
        WQUUF = new WeightedQuickUnionUF(N*N+2*N);
        /**avoid back wash : multiple virtual sites*/
        int j = 0;//top row connected to a virtual top site
        while (j != N) {
            WQUUF.union(j, N * N + j);
            j +=1;
        }
        j = 0;//bottom row connected to a virtual bottom site
        while (j != N) {
            WQUUF.union(N*(N-1)+j, N*(N+1)+j);
            j+=1;
        }
    }
    private int xyTo1d(int x,int y){
        if (x < 0 || y<0)
        {return -1;//-1 means exception
        }else if (x < Na && y< Na) {return x * Na + y;
        }else{return -1;}
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col)    {
        if (opened[xyTo1d(row,col)] == false) {
            opened[xyTo1d(row, col)] = true;
            openednumber++;
        }
        if (xyTo1d(row-1,col) != -1){
            if (opened[xyTo1d(row-1,col)]){WQUUF.union(xyTo1d(row,col),xyTo1d(row-1,col));}}
        if (xyTo1d(row,col-1) != -1){
            if (opened[xyTo1d(row,col-1)]){WQUUF.union(xyTo1d(row,col),xyTo1d(row,col-1));}}
        if (xyTo1d(row+1,col) != -1){
            if (opened[xyTo1d(row+1,col)]){WQUUF.union(xyTo1d(row,col),xyTo1d(row+1,col));}}
        if (xyTo1d(row,col+1) != -1){
            if (opened[xyTo1d(row,col+1)]){WQUUF.union(xyTo1d(row,col),xyTo1d(row,col+1));}}
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return opened[xyTo1d(row,col)];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (isOpen(row,col)) {
            int i = 0;
            while (i != Na) {
                if (WQUUF.connected(xyTo1d(row, col), Na * Na + i)) {
                    return true;
                }i+=1;
            }
            return false;
        }else return false;
    }
    // number of open sites
    public int numberOfOpenSites(){
        return openednumber;
    }
    // does the system percolate?
    public boolean percolates() {
        int i = 0;
        int j = 0;
        while (i != Na){
            while (j != Na) {
                if (WQUUF.connected(Na*Na+i, Na*Na+j)) {
                    return true;
                }j+=1;
            }i+=1;
        }
        return false;
    }
    // unit testing (not required)
    public static void main(String[] args){

    }
}                       
