/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author heloe
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//cd /cygdrive/c/Users/heloe/Documents/NetBeansProjects/Parcial1/src/   
public class Parcial1 {

    /**
     * @param args the command line arguments
     */
    static int[][] original;
    static int[][] objetivo;
    static int tamano;
    static String[][] originalS;
    static String[][] objetivoS;
    static int filaCero;
    static int columnaCero;
    public static void main(String[] args) {
       try {
    // Wrap the System.in inside BufferedReader
    // But do not close it in a finally block, as we 
    // did no open System.in; enforcing the rule that
    // he who opens it, closes it; leave the closing to the OS.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String cvsSplitBy = ",";
        
       tamano = Integer.parseInt(in.readLine());
        
        originalS = new String[tamano][tamano];
        objetivoS = new String[tamano][tamano];
        //System.out.println(tamano);
        int fila = tamano;
        int columna = tamano;
        int iter = 0;
        int iter2 = 0;
        
        
        while ((line = in.readLine()) != null) {
            line=line.replaceAll("\\s+","");
             if (iter<tamano) {
             String[] datos = line.split(cvsSplitBy);
             originalS[iter]=datos; 
                 for (int i = 0; i < datos.length; i++) {
                     if (datos[i].matches("0")) {
                         filaCero=iter;
                         columnaCero=i;
                     }
                 }
              
            }
            if (iter>=tamano) {
             String[] datos = line.split(cvsSplitBy);
             objetivoS[iter2]=datos;  
             iter2++;
            }
            iter++;
        }
           original = parseaMatriz(originalS);
           objetivo = parseaMatriz(objetivoS);
        //imprimeMatriz(original);
        //imprimeMatriz(objetivo);
           System.out.println(filaCero);
                   System.out.println(columnaCero);
                         
    // Null was received, so loop was aborted.

        } catch (IOException e) {
                   System.out.println(e.getCause());
                   System.out.println(e.getMessage());
            // TODO: Add error handler
        }
    }
    public static void imprimeMatriz(int[][] matriz){
        for (int fila=0; fila<matriz.length; fila++) {
                        for (int column=0; column<matriz[0].length;column++) {
                                System.out.printf("%d|",matriz[fila][column]);
                        }
                        System.out.println();
                }
    }
     public static void imprimeMatriz(String[][] matriz){
        for (int fila=0; fila<matriz.length; fila++) {
                        for (int column=0; column<matriz[0].length;column++) {
                                System.out.printf("%s|",matriz[fila][column]);
                        }
                        System.out.println();
                }
    }
    public static int [][] parseaMatriz(String[][] matriz){
        int[][] arreglo = new int[tamano][tamano];
        for (int fila=0; fila<matriz.length; fila++) {
                        for (int column=0; column<matriz[0].length;column++) {
                                arreglo[fila][column] = Integer.parseInt(matriz[fila][column]);
                        }
                      
                }
        return arreglo;
    }
}
