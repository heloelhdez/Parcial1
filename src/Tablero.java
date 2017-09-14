
import java.util.Comparator;

class Tablero{
    
    private int[][] matriz;
    private  int ubicacionPadre;//En close
    private  int distanciaRaiz;
    private  int posXVacio;
    private  int posYVacio;
    private  boolean noEliminado = false;
    private  double peso;
    private char movimiento;
    
    //Debe tener una matriz, el peso, ubicacion del nodo padre, distancia a la raiz, poscicion x y y de vacio 

    public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posYVacio,int posXVacio, char movimiento) {
        this.matriz=matriz;
        this.ubicacionPadre=ubicacionPadre;
        this.distanciaRaiz=distanciaRaiz;
        this.posXVacio=posXVacio;
        this.posYVacio=posYVacio;
        this.movimiento=movimiento;
    }

    public char getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(char movimiento) {
        this.movimiento = movimiento;
    }
    
    /**
     * @return the matriz
     */
    public  int[][] getMatriz() {
        return matriz;
    }

    /**
     * @param aMatriz the matriz to set
     */
    public  void setMatriz(int[][] aMatriz) {
        matriz = aMatriz;
    }

    /**
     * @return the ubicacionPadre
     */
    public  int getUbicacionPadre() {
        return ubicacionPadre;
    }

    /**
     * @param aUbicacionPadre the ubicacionPadre to set
     */
    public  void setUbicacionPadre(int aUbicacionPadre) {
        ubicacionPadre = aUbicacionPadre;
    }

    /**
     * @return the distanciaRaiz
     */
    public  int getDistanciaRaiz() {
        return distanciaRaiz;
    }

    /**
     * @param aDistanciaRaiz the distanciaRaiz to set
     */
    public  void setDistanciaRaiz(int aDistanciaRaiz) {
        distanciaRaiz = aDistanciaRaiz;
    }

    /**
     * @return the posXVacio
     */
    public  int getPosXVacio() {
        return posXVacio;
    }

    /**
     * @param aPosXVacio the posXVacio to set
     */
    public  void setPosXVacio(int aPosXVacio) {
        posXVacio = aPosXVacio;
    }

    /**
     * @return the posYVacio
     */
    public  int getPosYVacio() {
        return posYVacio;
    }

    /**
     * @param aPosYVacio the posYVacio to set
     */
    public  void setPosYVacio(int aPosYVacio) {
        posYVacio = aPosYVacio;
    }

    /**
     * @return the noEliminado
     */
    public  boolean isNoEliminado() {
        return noEliminado;
    }

    /**
     * @param aNoEliminado the noEliminado to set
     */
    public  void setNoEliminado(boolean aNoEliminado) {
        noEliminado = aNoEliminado;
    }

    /**
     * @return the peso
     */
    public  double getPeso() {
        return peso;
    }

    /**
     * @param aPeso the peso to set
     */
    public  void setPeso(double aPeso) {
        peso = aPeso;
    }
    
}