/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author heloel
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


//cd /cygdrive/c/Users/heloe/Documents/NetBeansProjects/Parcial1/src/   
public class Parcial1 {

    /**
     * @param args the command line arguments
     */
    
    static int[][] inicial;
    static int[][] tableroObjetivo;
    static int tamano;
    static String[][] inicialS;
    static String[][] tableroObjetivoS;
    static int filaCero;
    static Tablero[] open;
    static Tablero[] close;
    static int columnaCero;
    
    static int iteracion=0, posVacioX, posVacioY;
    
    static int maxCalculaIguales = tamano * tamano;
    static int maxPasosFaltantes = (int)Math.pow(tamano,3)+ tamano;
    static int maxDistanciaRaiz = tamano * tamano - 1;
    
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
        
        inicialS = new String[tamano][tamano];
        tableroObjetivoS = new String[tamano][tamano];
        //System.out.println(tamano);
        int fila = tamano;
        int columna = tamano; 
        int iter = 0;
        int iter2 = 0;
        
        
        while ((line = in.readLine()) != null) {
            
            line=line.replaceAll("\\s+","");
             
            if (iter<tamano) {
             String[] datos = line.split(cvsSplitBy);
             inicialS[iter]=datos; 
                 for (int i = 0; i < datos.length; i++) {
                     if (datos[i].matches("0")) {
                         filaCero=iter;
                         columnaCero=i;
                     }
                 }
            }
             
            if (iter>=tamano) {
             String[] datos = line.split(cvsSplitBy);
             tableroObjetivoS[iter2]=datos;  
             iter2++;
            }
            iter++;
        }
           inicial = parseaMatriz(inicialS);
           tableroObjetivo = parseaMatriz(tableroObjetivoS);
        //imprimeMatriz(inicial);
        //imprimeMatriz(tableroObjetivo);
           System.out.println(filaCero);
           System.out.println(columnaCero);
                         
    // Null was received, so loop was aborted.

        } catch (IOException e) {
                   System.out.println(e.getCause());
                   System.out.println(e.getMessage());
            // TODO: Add error handler
        }
       
    }
    /*
    open.append (tableroInicial)
    open.order()
    while open != vacio
            X = open.pop()
            if  X== objetivo
                    pathToX(x)
            else 
                    X es objeto.matriz
                    generaHijoDerecha(X, posVacioX, posVacioY)
                    generaHijoIzquierda(X, posVacioX, posVacioY)
                    generaHijoArriba(X, posVacioX, posVacioY)
                    generaHijoAbajo(X, posVacioX, posVacioY)
                    close.append(X)
                    open.order()
    */
    public static int[][] swap(int[][] tablero, int posXinicial, int posXfinal, int posYinicial, int posYfinal){
        int tmp;
        tmp = tablero[posYinicial][posXinicial];
        tablero[posYinicial][posXinicial] = tablero[posYfinal][posXfinal];
        tablero[posYfinal][posXfinal] = tmp;
        return tablero;
    }
    
    public static void generaHijoDerecha(Tablero papa,int  vacioX,int vacioY,int distaARaiz){
	try {
                Tablero hijo = new Tablero();
                int[][] hijo = swap(tablero, vacioX, vacioY,vacioX+1,vacioY);
                
                
		if (indexOpen(hijo)>=0){
                    
			if (revisarPesoActualVsPesoenOpen(hijo, papa,distaARaiz)){
                            open[indexOpen(hijo)].setPeso();
                            cambiarPesoenOpen
                                        }
                }
		else if (estaClose(hijo))
			if (revisarPesoActualVsPesoenOpen(hijo,papa, distaARaiz))
				removeClose(hjo)
				open.append(hijo)
            else	
                                    calculaPeso(hijo)
                                    open.append()hijo
            }
            catch{
                    exception por salir del arreglo, continue
            }
        }
    //Recuerda que no se van a eliminar sino que el objeto tendra un tributo que va a decir si esta eliminado
 public static double calculaPeso(int[][] matriz,int distaARaiz){
	//Hay que dividir entre el maximo de cada uno para que quede de 0-1
	double peso = 0;
	peso+= calculaIguales(matriz, tableroObjetivo)/maxCalculaIguales;
	peso += pasosFaltantes(matriz)/maxPasosFaltantes;
	peso+= distaARaiz/maxDistanciaRaiz;
	return peso;
}

public static int pasosFaltantes(int[][] matriz){
	int total = 0;
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                for (int k = 0; k < tamano; k++) {
                    for (int l = 0; l < tamano; l++) {
                        if (matriz [i][j] == tableroObjetivo[k][l]){
						total+= (Math.abs(k-i) + Math.abs(l-j));
						k=tamano;
                                                l=tamano;
                        }
                    }
                }
            }
    }
 return total;
}


public static int indexOpen(int[][] matriz){
    int bandera = -1;
    
    for (int i = 0; i < open.length; i++) {
        if (calculaIguales(matriz, open[i].getMatriz()) == (tamano*tamano) && open[i].getNoEliminado()){
            bandera = i;
            return bandera;
        }
    }
    return bandera;
}
public static int indexClose(int[][] matriz){
    int bandera = -1;

    for (int i = 0; i < close.length; i++) {
        if (calculaIguales(matriz, close[i].getMatriz()) == (tamano*tamano) && close[i].getNoEliminado()){
            bandera = i;
            return bandera;
        }
    }
    return bandera;
}    
public static boolean estaOpen(int[][] matriz){
    boolean bandera = false;
    
    for (int i = 0; i < open.length; i++) {
        if (calculaIguales (matriz, open[i].getMatriz()) == (tamano*tamano) && open[i].getNoEliminado())
            bandera = true;
    }
    return bandera;
}
public static boolean estaClose(int[][] matriz){
    boolean bandera = false;
    
    for (int i = 0; i < close.length; i++) {
        if (calculaIguales (matriz, close[i].getMatriz()) == (tamano*tamano) && close[i].getNoEliminado())
            bandera = true;
    }
    return bandera;
}
public static int calculaIguales(int[][]matriz, int[][] matrizAComparar){
    int posIguales=0;
    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            if (matriz [i][j] == matrizAComparar[i][j])
				posIguales++;
        }
    }
    return posIguales;
}

public static boolean revisarPesoActualVsPesoenOpen(int[][] hijo,int[][] matrizEnOpen,int distaARaiz) {
	boolean bandera = false;
        //Recuerda que distaARaiz es mas uno porque es el hijo
        //solo ver que esta en open y validar que la distancia a la raiz sea menor a la otra
	double pesoHijo = calculaPeso(hijo, distaARaiz+1);
	double pesoOpen = calculaPeso(matrizEnOpen, distaARaiz);
	if (pesoHijo < pesoOpen)
		bandera = true;
	return bandera;
}
    /*
    generaHijoIzquierda(tablero, vacioX,vacioY){
	try {
		hijo = swap (tablero [vacioY][vacioX], tablero[vacioY][vacioX-1]
		if (estaOpen(hijo))
			if (revisarPesoActualVsPesoenOpen(hijo))
				cambiarPesoenOpen
		else if (estaClose(close))
			if (revisarPesoActualVsPesoenOpen(hijo))
				removeClose(hjo)
				open.append(hijo)
    else	
                            calculaPeso(hijo)
                            open.append()hijo
    }
    catch{
            exception por salir del arreglo, continue
    }
}

    generaHijoArriba(tablero, vacioX,vacioY){
	try {
		hijo = swap (tablero [vacioY][vacioX], tablero[vacioY-1][vacioX]
		if (estaOpen(hijo))
			if (revisarPesoActualVsPesoenOpen(hijo))
				cambiarPesoenOpen
		else if (estaClose(close))
			if (revisarPesoActualVsPesoenOpen(hijo))
				removeClose(hjo)
				open.append(hijo)
    else	
                            calculaPeso(hijo)
                            open.append()hijo
    }
    catch{
            exception por salir del arreglo, continue
    }
}

    generaHijoAbajo(tablero, vacioX,vacioY){
	try {
		hijo = swap (tablero [vacioY][vacioX], tablero[vacioY+1][vacioX]
		if (estaOpen(hijo))
			if (revisarPesoActualVsPesoenOpen(hijo))
				cambiarPesoenOpen
		else if (estaClose(close))
			if (revisarPesoActualVsPesoenOpen(hijo))
				removeClose(hjo)
				open.append(hijo)
    else	
                            calculaPeso(hijo)
                            open.append()hijo
    }
    catch{
            exception por salir del arreglo, continue
    }
}


    //Falta distancia a la raiz
    
pasosFaltantes (matriz){
	int total = 0
	for i=0 hasta i < tamaño  i++
		for j=0 hasta j < tamaño  j++
			for y=0 hasta i < tamaño  i++
				for x=0 hasta j < tamaño  j++
					if (matriz [i][j] == tableroObjetivo[y][x])
						total+= (abs(y-i) + abs (x-j))
						x=3, y=3
	return total
}
*/

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
