/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.math.*;

/**
 *
 * @author adolf
 */
public class fibfast {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new fibfast().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        numbers[0] = new BigInteger("0");
        numbers[1] = new BigInteger("1");
        numbers[2] = new BigInteger("1");
        numbers[3] = new BigInteger("2");
        numbers[4] = new BigInteger("3");
        numbers[5] = new BigInteger("5");

        int requests[] = new int[10];
        int requests_0[] = new int[10];
        int n = 0;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (line != null) {
            try {
                requests[n] = Integer.parseInt(line);
                requests_0[n] = requests[n];
                line = in.readLine();
                n++;
            } catch (java.lang.NumberFormatException e) {
                line=null;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (requests[i] < requests[j]) {
                    int tmp = requests[i];
                    requests[i] = requests[j];
                    requests[j] = tmp;
                }
            }
        }
        /*
        for(int i=0;i<n;i++)
            System.out.println(requests[i]);*/

        Matrix m2=new Matrix(2,numbers);
        //m2.print();
        int m=2;
        for(m=2;m<requests[0];m*=2){
            m2.multiply(m2);
        }
        for(int i=0;i<n;i++){
            //System.out.println("request for number "+requests_0[i]);
            getNumber(requests_0[i],numbers);
            System.out.println("The Fibonacci number for "+requests_0[i]+" is "+numbers[requests_0[i]]);
        }
    }

    public void getNumber(int i,BigInteger numbers[]){
        int m=i;
        Matrix m1=new Matrix(1,numbers);
        while(m>0){
            int low=Integer.lowestOneBit(m);
            //System.out.println(low);
            m1.multiply(new Matrix(low,numbers));
            m=m-low;
        }
    }

    public fibfast() {
        numbers = new BigInteger[10000];
    }
    private java.math.BigInteger numbers[];

    public class Matrix{
        private BigInteger members[];
        private BigInteger numbers[];
        public int id;
        public Matrix(int i,BigInteger m[]){
            members=new BigInteger[4];
            members[0]=m[i+1];
            members[1]=members[2]=m[i];
            members[3]=m[i-1];
            id=i;
            numbers=m;
        }

        /*public void print(){
            System.out.println(this.id+1 + "," + this.members[0]);
            System.out.println(this.id + "," + this.members[1]);
            System.out.println(this.id-1 + "," + this.members[3]);
        }*/

        public void multiply(Matrix right){
            int i=id+right.id;
            //System.out.println("i="+i);
            numbers[i+1]=members[0].multiply(right.members[0]).add(members[1].multiply(right.members[2]));
            numbers[i-1]=members[2].multiply(right.members[1]).add(members[3].multiply(right.members[3]));
            numbers[i]=numbers[i+1].subtract(numbers[i-1]);

            //System.out.println("fib "+(i+1) + "," + this.numbers[i+1]);
            //System.out.println("fib "+i + "," + this.numbers[i]);
            //System.out.println("fib "+(i-1) + "," + this.numbers[i-1]);

            this.id=i;
            this.members[0]=numbers[i+1];
            this.members[1]=this.members[2]=numbers[i];
            this.members[3]=numbers[i-1];
        }
    }
}
