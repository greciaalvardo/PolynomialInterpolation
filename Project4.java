// Name:    Grecia Alvarado
// Project: 4

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Project4
{
    public static void main(String[] args)
    {

        double[] x = new double[50];
        double[][] table = new double[50][50];
        String num = "";
        String inputFile;
        int count = 0;

        Scanner getFile = new Scanner(System.in);
        System.out.print("Enter file name: ");
        inputFile = getFile.nextLine();

        getFile.close();
        System.out.println();

        try{
        File file = new File(inputFile);
        Scanner scan = new Scanner(file);

        
        while(scan.hasNext()){
            num = scan.next();
            count++;
        }
        count /= 2;

        scan.close();

        Scanner scanner = new Scanner(file);

        int i = 0;
        while(scanner.hasNext() && i<count){
            num = scanner.next();
            x[i] = Double.parseDouble(num);
            i++;
        }
        i=0;
        while(scanner.hasNext() && i<count){
            num = scanner.next();
            table[i][0] = Double.parseDouble(num);
            i++;
        }
        scanner.close();

    } catch(FileNotFoundException e){
            System.out.println("File not found.");
    }


    System.out.printf("\n%-15s%-15s", "x", "f[]");
    printHeader(count);
    System.out.println();

    dividedDifference(x, table, count);

    //print the table:
    for(int col = 0; col<count; col++){
        System.out.printf("%-15s", x[col]);
        for(int row = 0; row < count-col; row++){
            System.out.printf("%.2f", table[col][row]);
            System.out.printf("%-11s", "");
        }
        System.out.println();
    }

    NewtonsForm(x, table, count);
    Lagrange(x, table, count);
    }

    static double[][] dividedDifference(double[] x, double[][] table, int length){
        for(int col=1; col<length; col++){
            for(int row = 0; row<length-col; row++){
                table[row][col] = (table[row][col-1]-table[row+1][col-1])/(x[row]-x[col+row]);
            }
        }
        return table;
    }

    static void printHeader(int length){
        String comma = "";
        String header = "";
        for(int i =0; i<length-1; i++){
            comma += ",";
            header = "f[" + comma + "]";
            System.out.printf("%-15s", header);
        }
    }

    static void NewtonsForm(double[] x, double[][] table, int length){
        String polynomial = String.valueOf(table[0][0]);
        String coeff = "";
        String paren = "";
        for(int i =1; i<length; i++){
            coeff = String.format("%.2f", table[0][i]);
            paren += "(x-" + String.valueOf(x[i-1]) + ")";
            polynomial += " + " + coeff + paren;
        }
        System.out.println("Newton's Polynomial: " + polynomial);
    }

    static void Lagrange(double[] x, double[][] table, int length){
        String polynomial = "";
        String paren = "";
        double coeff;
        for(int clash = 0; clash < length; clash++){
            coeff = 1;
            paren = "";
            for(int i = 0; i<length; i++){
                if(clash != i){
                coeff *= (x[clash] - x[i]);
                paren += "(x-" + x[i] + ")";
                }
            }
            polynomial += "(" + table[clash][0] + "/" + coeff + ")" + paren;
            if(clash != length-1)
            polynomial += " + ";
        }
        System.out.println("Lagrange polynomial: " + polynomial);
    }


}