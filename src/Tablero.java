class Tablero {
    
    static int[][] matriz;
    static int ubicacionPadre;
    static int distanciaRaiz;
    static int tamano;
    static int posXVacio;
    static int posYVacio;
    static int posicionClose;
    static boolean noEliminado = false;
    
    //Debe tener una matriz, el peso, ubicacion del nodo padre, distancia a la raiz, poscicion x y y de vacio 

    public Tablero(int[][] matriz,int ubicacionPadre, int distanciaRaiz,int posXVacio,int posYVacio) {
        this.matriz=matriz;
        this.ubicacionPadre=ubicacionPadre;
        this.distanciaRaiz=distanciaRaiz;
        this.posXVacio=posXVacio;
        this.posYVacio=posYVacio;
    }

    public static void setPosicionClose(int posicionClose) {
        Tablero.posicionClose = posicionClose;
    }

    public static void setNoEliminado(boolean eliminado) {
        Tablero.noEliminado = eliminado;
    }
    
    public static int getPosicionClose() {
        return posicionClose;
    }
    
    public static void setDistanciaRaiz(int distanciaRaiz) {
        Tablero.distanciaRaiz = distanciaRaiz;
    }

    public static void setMatriz(int[][] matriz) {
        Tablero.matriz = matriz;
    }

    public static void setPosXVacio(int posXVacio) {
        Tablero.posXVacio = posXVacio;
    }

    public static void setPosYVacio(int posYVacio) {
        Tablero.posYVacio = posYVacio;
    }

    public static void setTamano(int tamano) {
        Tablero.tamano = tamano;
    }

    public static void setUbicacionPadre(int ubicacionPadre) {
        Tablero.ubicacionPadre = ubicacionPadre;
    }

    public static int getDistanciaRaiz() {
        return distanciaRaiz;
    }

    public static int[][] getMatriz() {
        return matriz;
    }

    public static int getPosXVacio() {
        return posXVacio;
    }

    public static int getPosYVacio() {
        return posYVacio;
    }

    public static int getTamano() {
        return tamano;
    }

    public static int getUbicacionPadre() {
        return ubicacionPadre;
    }
    public static boolean getNoEliminado(){
        return noEliminado;
    }

    public static int peso(){
            int peso = 0;
            for (int fila=0; fila<tamano; fila++) {
                    for (int column=0; column<tamano;column++) {
                            try{
                                if (fila < tamano-1 && column < tamano-1) {
                                        if (matriz[fila][column] == matriz[fila][column+1])
                                                peso++;
                                        if (matriz[fila][column] == matriz[fila+1][column])
                                                peso++;
                                }
                                if (fila == tamano-1) {
                                        if (matriz[fila][column] == matriz[fila][column+1])
                                                peso++;
                                }
                                if (column == tamano-1) {
                                        if (matriz[fila][column] == matriz[fila+1][column])
                                                peso++;
                                }	
                            }
                            catch(ArrayIndexOutOfBoundsException e){
                                    continue;
                            }
                    }
            }
            return peso;
    }
}