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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    static ArrayList<Tablero> listaOpen = new ArrayList<>();
    static ArrayList<Tablero> listaClose = new ArrayList<>();
    
    static Tablero tableroInicial;
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
           tableroInicial = new Tablero(inicial, 0,0,columnaCero, filaCero);
           tableroInicial.setPeso(0.9);
           listaOpen.add(tableroInicial);
           //imprimeMatriz(listaOpen.get(0).getMatriz());
           Tablero t2 = tableroInicial;
           t2.setPeso(0.5);
           listaOpen.add(t2);
           t2.setPeso(0.3);
           listaOpen.add(t2);
           for (Iterator<Tablero> iterator = listaOpen.iterator(); iterator.hasNext();) {
               Tablero next = iterator.next();
               System.out.println(next.getPeso());
               
           }
           System.out.println("Desordenada");
           Collections.sort(listaOpen,  new Comparator<Tablero>() {
            @Override
            public int compare(Tablero o1, Tablero o2) {
                if (o1.getPeso()-o2.getPeso()<0) {
                    return -1;
                }
                if (o1.getPeso()-o2.getPeso()>0) {
                    return 1;
                }
                if (o1.getPeso()-o2.getPeso()==0) {
                    return 0;
                }
                return 0;
            }
        });
           
           for (Iterator<Tablero> iterator = listaOpen.iterator(); iterator.hasNext();) {
               Tablero next = iterator.next();
               System.out.println(next.getPeso());
               
           }
           //open.append (tableroInicial)
           //open.order()
            /*
            Tablero x;
            while(!listaOpen.isEmpty()){
                try{
                    x = listaOpen.get(listaOpen.size()-1);
                    if (sonIguales(x.getMatriz(), tableroObjetivo)) {
                        //pathToX(x)
                    }
                    else{
                            //generaHijoDerecha(x);
                            //generaHijoIzquierda(x)
                            //generaHijoArriba(x)
                            //generaHijoAbajo(x)
                            listaClose.add(x);
                            Collections.sort(listaOpen, Collections.reverseOrder());
                            //listaOpen.sort(cmprtr);
                    }
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            
            }
           
            */            


           //Hay que ordenar de mayor a menor de acuerdo a su peso, del objeto!!!!!!!!
           
           //imprimeMatriz(inicial);
           //imprimeMatriz(tableroObjetivo);
           //System.out.println(filaCero);
           //System.out.println(columnaCero);
                         
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
    /*
    public static void generaHijoDerecha(Tablero papa){
	try {
                Tablero hijo = papa;
                int[][] hijoMatriz = swap(papa.getMatriz(), papa.getPosXVacio(), papa.getPosYVacio(), papa.getPosXVacio()+1,papa.getPosYVacio());
                papa.setMatriz(hijoMatriz);
                papa.setDistanciaRaiz(papa.getDistanciaRaiz()+1);
                papa.setPosXVacio(posVacioX+1);
                papa.setPosYVacio(posVacioX);
                
		if (indexOpen(hijo)>=0){
                    //Aqui vamos
			if (revisarPesoActualVsPesoenOpen(hijo, papa)){
                            open[indexOpen(hijo)].setPeso();
                            cambiarPesoenOpen
                                        }
                }
		else if (estaClose(hijo))
			if (revisarPesoActualVsPesoenOpen(hijo,papa))
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
    */
    //Recuerda que no se van a eliminar sino que el objeto tendra un tributo que va a decir si esta eliminado
 public static double calculaPeso(Tablero tablero){
	//Hay que dividir entre el maximo de cada uno para que quede de 0-1
	double peso = 0;
	peso += calculaIguales(tablero.getMatriz(), tableroObjetivo)/maxCalculaIguales;
	peso += pasosFaltantes(tablero.getMatriz())/maxPasosFaltantes;
	peso += tablero.getDistanciaRaiz()/maxDistanciaRaiz;
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

public static int indexOpen(Tablero tablero){
    int bandera = -1;
    
    for (int i = 0; i < listaOpen.size(); i++) {
        if (calculaIguales(tablero.getMatriz(), listaOpen.get(i).getMatriz()) == (tamano*tamano) && listaOpen.get(i).isNoEliminado()){
            bandera = i;
            return bandera;
        }
    }
    return bandera;
}
public static int indexClose(Tablero tablero){
    int bandera = -1;

    for (int i = 0; i < listaClose.size(); i++) {
        if (calculaIguales(tablero.getMatriz(), listaClose.get(i).getMatriz()) == (tamano*tamano) && listaClose.get(i).isNoEliminado()){
            bandera = i;
            return bandera;
        }
    }
    return bandera;
}    
public static boolean estaOpen(int[][] matriz){
    boolean bandera = false;
    
    for (int i = 0; i < listaOpen.size(); i++) {
        if (calculaIguales (matriz,  listaOpen.get(i).getMatriz()) == (tamano*tamano) &&  listaOpen.get(i).isNoEliminado())
            bandera = true;
    }
    return bandera;
}
public static boolean estaClose(int[][] matriz){
    boolean bandera = false;
    
    for (int i = 0; i < listaClose.size(); i++) {
        if (calculaIguales (matriz, listaClose.get(i).getMatriz()) == (tamano*tamano) && listaClose.get(i).isNoEliminado())
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
public static boolean sonIguales(int[][]matriz, int[][] matrizAComparar){
    int posIguales=0;
    for (int i = 0; i < tamano; i++) {
        for (int j = 0; j < tamano; j++) {
            if (matriz [i][j] == matrizAComparar[i][j])
				posIguales++;
        }
    }
    return posIguales == maxCalculaIguales;
}

public static boolean revisarPesoActualVsPesoenOpen(Tablero hijo,Tablero matrizEnOpen) {
	boolean bandera = false;
        //Recuerda que distaARaiz es mas uno porque es el hijo
        //solo ver que esta en open y validar que la distancia a la raiz sea menor a la otra
	double pesoHijo = calculaPeso(hijo);
	double pesoOpen = calculaPeso(matrizEnOpen);
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
