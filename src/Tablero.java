
import java.util.Comparator;

class Tablero implements Comparable<Tablero>{
    
    private static int[][] matriz;
    private static int ubicacionPadre;
    private static int distanciaRaiz;
    private static int posXVacio;
    private static int posYVacio;
    private static int posicionClose;
    private static int posicionOpen;
    private static boolean noEliminado = false;
    private static double peso;
    
    //Debe tener una matriz, el peso, ubicacion del nodo padre, distancia a la raiz, poscicion x y y de vacio 

    public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posXVacio,int posYVacio ) {
        this.matriz=matriz;
        this.ubicacionPadre=ubicacionPadre;
        this.distanciaRaiz=distanciaRaiz;
        this.posXVacio=posXVacio;
        this.posYVacio=posYVacio;
    }

    /**
     * @return the matriz
     */
    public static int[][] getMatriz() {
        return matriz;
    }

    /**
     * @param aMatriz the matriz to set
     */
    public static void setMatriz(int[][] aMatriz) {
        matriz = aMatriz;
    }

    /**
     * @return the ubicacionPadre
     */
    public static int getUbicacionPadre() {
        return ubicacionPadre;
    }

    /**
     * @param aUbicacionPadre the ubicacionPadre to set
     */
    public static void setUbicacionPadre(int aUbicacionPadre) {
        ubicacionPadre = aUbicacionPadre;
    }

    /**
     * @return the distanciaRaiz
     */
    public static int getDistanciaRaiz() {
        return distanciaRaiz;
    }

    /**
     * @param aDistanciaRaiz the distanciaRaiz to set
     */
    public static void setDistanciaRaiz(int aDistanciaRaiz) {
        distanciaRaiz = aDistanciaRaiz;
    }

    /**
     * @return the posXVacio
     */
    public static int getPosXVacio() {
        return posXVacio;
    }

    /**
     * @param aPosXVacio the posXVacio to set
     */
    public static void setPosXVacio(int aPosXVacio) {
        posXVacio = aPosXVacio;
    }

    /**
     * @return the posYVacio
     */
    public static int getPosYVacio() {
        return posYVacio;
    }

    /**
     * @param aPosYVacio the posYVacio to set
     */
    public static void setPosYVacio(int aPosYVacio) {
        posYVacio = aPosYVacio;
    }

    /**
     * @return the posicionClose
     */
    public static int getPosicionClose() {
        return posicionClose;
    }

    /**
     * @param aPosicionClose the posicionClose to set
     */
    public static void setPosicionClose(int aPosicionClose) {
        posicionClose = aPosicionClose;
    }

    /**
     * @return the posicionOpen
     */
    public static int getPosicionOpen() {
        return posicionOpen;
    }

    /**
     * @param aPosicionOpen the posicionOpen to set
     */
    public static void setPosicionOpen(int aPosicionOpen) {
        posicionOpen = aPosicionOpen;
    }

    /**
     * @return the noEliminado
     */
    public static boolean isNoEliminado() {
        return noEliminado;
    }

    /**
     * @param aNoEliminado the noEliminado to set
     */
    public static void setNoEliminado(boolean aNoEliminado) {
        noEliminado = aNoEliminado;
    }

    /**
     * @return the peso
     */
    public static double getPeso() {
        return peso;
    }

    /**
     * @param aPeso the peso to set
     */
    public static void setPeso(double aPeso) {
        peso = aPeso;
    }
    
   /*
    **  Implement the natural order for this class
    */
    @Override
    public int compareTo(Tablero o2) {
                if (getPeso()-o2.getPeso()<0) {
                    return -1;
                }
                if (getPeso()-o2.getPeso()>0) {
                    return 1;
                }
                if (getPeso()-o2.getPeso()==0) {
                    return 0;
                }
                return 0;
            }
}