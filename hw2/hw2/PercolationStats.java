package hw2;
import edu.princeton.cs.algs4.StdRandom;
import hw2.Percolation;
public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
        public PercolationStats(int N, int T){
            if (N<=0 || T<=0){throw new IllegalArgumentException();}
            Percolation per = new Percolation(N);

            int i = 0;
            int j = 0;
            double[] values = new double[T];
            while (j != T){
                while (!per.percolates()) {
                    per.open(StdRandom.uniform(N),StdRandom.uniform(N));
                    i++;
                }
                values[j] = i/N*N;
            }

            //double mean = mean(values);

        }

    // sample mean of percolation threshold
        public double mean(double[] values){
            int i = 0;
            double sum = 0.0;
            while (i != values.length) {
                sum += values[i];
            }
            return sum/values.length;
        }
    // sample standard deviation of percolation threshold
        public double stddev(double[] values){
            int i = 0;
            double stddev = 0.0;
            double mean = mean(values);
            int T = values.length;
            while (i != T) {
                stddev += Math.pow((values[i]-mean),2)/(T-1);
            }
            return stddev;
        }
    // low  endpoint of 95% confidence interval
        public double confidenceLow(double[] values){
            double mean = mean(values);
            double stddev = stddev(values);
            int T = values.length;
            double confidenceLow = mean - 1.96*Math.pow(stddev/T,0.5);
            return confidenceLow;
        }
    // high endpoint of 95% confidence interval
        public double confidenceHigh(double[] values) {
            double mean = mean(values);
            double stddev = stddev(values);
            int T = values.length;
            double confidenceHigh = mean + 1.96*Math.pow(stddev/T,0.5);
            return confidenceHigh;
        }
    }

