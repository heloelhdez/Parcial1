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
    static ArrayList<Tablero> listaOpen = new ArrayList<Tablero>();
    static ArrayList<Tablero> listaClose = new ArrayList<>();
    
    static Tablero tableroInicial;
    static int columnaCero;
    
    static int iteracion=0, posVacioX, posVacioY;
    
    static int maxCalculaIguales;
    static int maxPasosFaltantes;
    static int maxDistanciaRaiz;
    
     /**
     * This will return the factorial of the given number n.
     * @param n the number to getFromOrigin the factorial for
     * @return the factorial for this number
     */
    public static double factorial(double n) {
        if (n == 1 || n == 0)
            return 1;
        for (double i = n; i > 0; i--, n *= (i > 0 ? i : 1)) {
        }
        return n;
    }//end factorial
    
    
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
        maxCalculaIguales = tamano * tamano;
        maxPasosFaltantes = (int)(Math.pow(tamano,3)+tamano);
        maxDistanciaRaiz = (int)(factorial((double)Math.pow(tamano,2)-tamano)/factorial((double)(tamano)));
        inicialS = new String[tamano][tamano];
        tableroObjetivoS = new String[tamano][tamano];
        //System.out.println(tamano);
       
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
           
           tableroInicial = new Tablero(inicial, 0,0,columnaCero, filaCero,' ');
            listaOpen.add(tableroInicial);
            
            Tablero x;
            
            while(!listaOpen.isEmpty()){
                
                    x = listaOpen.get(listaOpen.size()-1);
                    if (sonIguales(x.getMatriz(), tableroObjetivo)) {
                        System.out.println("Entre son iguales");
                        pathTo(x);
                    }
                    else{
                        System.out.println("Entre Else");
                            generaHijoDerecha(x);
                            generaHijoIzquierda(x);
                            generaHijoArriba(x);
                            generaHijoAbajo(x);
                            listaClose.add(x);
                            Collections.sort(listaOpen, Collections.reverseOrder());
                            //ordenaMayorMenor(listaOpen);
                    }
                
            
            }

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
   
    public static int[][] swap(int[][] tablero, int posXinicial, int posXfinal, int posYinicial, int posYfinal){
        int tmp;
        tmp = tablero[posYinicial][posXinicial];
        tablero[posYinicial][posXinicial] = tablero[posYfinal][posXfinal];
        tablero[posYfinal][posXfinal] = tmp;
        return tablero;
    }
    
    public static void generaHijoArriba(Tablero papa){
        System.out.println("arriba");
	if (papa.getPosYVacio()-1 < 0) {
                //public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posYVacio,int posXVacio, char movimiento) {
                int[][] hijoMatriz = swap(papa.getMatriz(), papa.getPosXVacio(), papa.getPosYVacio(), papa.getPosXVacio(),papa.getPosYVacio()-1);
                Tablero hijo = new Tablero(hijoMatriz, listaClose.size(),papa.getDistanciaRaiz()+1, papa.getPosYVacio()-1,papa.getPosXVacio(), 'U');
                
                if (indexOpen(hijo)>=0){
                    //Aqui vamos
			if (revisarPesoActualVsPesoenOpen( hijo, listaOpen.get(indexOpen(hijo)))){
                            listaOpen.get(indexOpen(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaOpen.get(indexOpen(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaOpen.get(indexOpen(hijo)).setPeso(calculaPeso(hijo));
                      }
                }
		else if (indexClose(hijo)>=0){
			if (revisarPesoActualVsPesoenClose(hijo,papa)){
                            listaClose.get(indexClose(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaClose.get(indexClose(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaClose.get(indexClose(hijo)).setPeso(calculaPeso(hijo));
                            }
                }
                else{
                        hijo.setPeso(calculaPeso(hijo));
                        listaOpen.add(hijo);
            }
        }
    }
    public static void generaHijoAbajo(Tablero papa){
        System.out.println("abajo");
	if (papa.getPosYVacio()+1 < tamano) {
                //public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posYVacio,int posXVacio, char movimiento) {
                int[][] hijoMatriz = swap(papa.getMatriz(), papa.getPosXVacio(), papa.getPosYVacio(), papa.getPosXVacio(),papa.getPosYVacio()+1);
                Tablero hijo = new Tablero(hijoMatriz, listaClose.size(),papa.getDistanciaRaiz()+1, papa.getPosYVacio()+1,papa.getPosXVacio(), 'D');
                
                if (indexOpen(hijo)>=0){
                    //Aqui vamos
			if (revisarPesoActualVsPesoenOpen( hijo, listaOpen.get(indexOpen(hijo)))){
                            listaOpen.get(indexOpen(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaOpen.get(indexOpen(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaOpen.get(indexOpen(hijo)).setPeso(calculaPeso(hijo));
                      }
                }
		else if (indexClose(hijo)>=0){
			if (revisarPesoActualVsPesoenClose(hijo,papa)){
                            listaClose.get(indexClose(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaClose.get(indexClose(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaClose.get(indexClose(hijo)).setPeso(calculaPeso(hijo));
                            }
                }
                else{
                        hijo.setPeso(calculaPeso(hijo));
                        listaOpen.add(hijo);
            }
        }
    }
    
    public static void generaHijoIzquierda(Tablero papa){
        System.out.println("izquierda");
	if (papa.getPosXVacio()-1 < 0) {
                //public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posYVacio,int posXVacio, char movimiento) {
                int[][] hijoMatriz = swap(papa.getMatriz(), papa.getPosXVacio(), papa.getPosYVacio(), papa.getPosXVacio()-1,papa.getPosYVacio());
                Tablero hijo = new Tablero(hijoMatriz, listaClose.size(),papa.getDistanciaRaiz()-1, papa.getPosYVacio(),papa.getPosXVacio()-1, 'L');
                
                if (indexOpen(hijo)>=0){
                    //Aqui vamos
			if (revisarPesoActualVsPesoenOpen( hijo, listaOpen.get(indexOpen(hijo)))){
                            listaOpen.get(indexOpen(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaOpen.get(indexOpen(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaOpen.get(indexOpen(hijo)).setPeso(calculaPeso(hijo));
                      }
                }
		else if (indexClose(hijo)>=0){
			if (revisarPesoActualVsPesoenClose(hijo,papa)){
                            listaClose.get(indexClose(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaClose.get(indexClose(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaClose.get(indexClose(hijo)).setPeso(calculaPeso(hijo));
                            }
                }
                else{
                        hijo.setPeso(calculaPeso(hijo));
                        listaOpen.add(hijo);
            }
        }
    }
    
    
    public static void generaHijoDerecha(Tablero papa){
        System.out.println("derecha");
                if (papa.getPosXVacio()+1 < tamano) {
            //public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posYVacio,int posXVacio, char movimiento) {
                int[][] hijoMatriz = swap(papa.getMatriz(), papa.getPosXVacio(), papa.getPosYVacio(), papa.getPosXVacio()+1,papa.getPosYVacio());
                Tablero hijo = new Tablero(hijoMatriz, listaClose.size(),papa.getDistanciaRaiz()+1, papa.getPosYVacio(),papa.getPosXVacio()+1, 'R');
                System.out.println("Entre Derecha Try");
                if (indexOpen(hijo)>=0){
                    //Aqui vamos
			if (revisarPesoActualVsPesoenOpen( hijo, listaOpen.get(indexOpen(hijo)))){
                            listaOpen.get(indexOpen(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaOpen.get(indexOpen(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaOpen.get(indexOpen(hijo)).setPeso(calculaPeso(hijo));
                      }
                }
		else if (indexClose(hijo)>=0){
			if (revisarPesoActualVsPesoenClose(hijo,papa)){
                            listaClose.get(indexClose(hijo)).setDistanciaRaiz(hijo.getDistanciaRaiz());
                            listaClose.get(indexClose(hijo)).setUbicacionPadre(hijo.getUbicacionPadre());
                            listaClose.get(indexClose(hijo)).setPeso(calculaPeso(hijo));
                            }
                }
                else{
                        hijo.setPeso(calculaPeso(hijo));
                        listaOpen.add(hijo);
            }
        }
    }
    
public static void ordenaMayorMenor(ArrayList<Tablero> lista){
    Collections.sort(lista,  new Comparator<Tablero>() {
            @Override
            public int compare(Tablero o1, Tablero o2) {
                if (o1.getPeso()-o2.getPeso()>0) {
                    return -1;
                }
                if (o1.getPeso()-o2.getPeso()<0) {
                    return 1;
                }
                if (o1.getPeso()-o2.getPeso()==0) {
                    return 0;
                }
                return 0;
                }
            });
}
    //Recuerda que no se van a eliminar sino que el objeto tendra un tributo que va a decir si esta eliminado
 public static double calculaPeso(Tablero tablero){
            //Hay que dividir entre el maximo de cada uno para que quede de 0-1 y multiplicar por un persontaje para dar mayor relevancia a un peso en especifico
            double peso = 0;
            peso += (calculaIguales(tablero.getMatriz(), tableroObjetivo)/maxCalculaIguales)*0.25;
            peso += (pasosFaltantes(tablero.getMatriz())/maxPasosFaltantes)*0.5;
            peso += (tablero.getDistanciaRaiz()/maxDistanciaRaiz)*0.25;
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
//Falta hacer un String global que tenga el path y luego hacerle reverse
public static void pathTo(Tablero tablero){
    if (tablero.getDistanciaRaiz() == 0) {
        System.out.print(tablero.getMovimiento()+"\n");
    }
    else{
        System.out.print(tablero.getMovimiento()+",");
        pathTo(listaClose.get(tablero.getUbicacionPadre()));
    }
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

public static boolean revisarPesoActualVsPesoenOpen(Tablero tabla,Tablero matrizEnOpen) {
	boolean bandera = false;
        //Recuerda que distaARaiz es mas uno porque es el hijo
        //solo ver que esta en open y validar que la distancia a la raiz sea menor a la otra
	
	if (tabla.getDistanciaRaiz() < matrizEnOpen.getDistanciaRaiz())
		bandera = true;
	return bandera;
}
public static boolean revisarPesoActualVsPesoenClose(Tablero tabla,Tablero matrizEnClose) {
	boolean bandera = false;
        //Recuerda que distaARaiz es mas uno porque es el hijo
        //solo ver que esta en open y validar que la distancia a la raiz sea menor a la otra
	
	if (tabla.getDistanciaRaiz() < matrizEnClose.getDistanciaRaiz())
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
