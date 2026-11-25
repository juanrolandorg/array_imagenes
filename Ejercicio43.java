public class Ejercicio43 {
    private static final int FILAS = 6;
    private static final int COLUMNAS = 6;

    public static void main(String[] args) {
        // la imagen se almacena en un 6x6
        // los valores de los píxeles oscilan entre 255 y 0

        int img[][] = new int[FILAS][COLUMNAS];
        // doy valores aleatorios entre 255 y 0 para crear la imagen
        // ya que los asigno, los muestro
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                img[i][j] = (int) (Math.random() * 256);
            }
        }
        // Menu
        mostrarMenu();
        menuNav(img);
    }

    // voy a ir implementando los métodos
    public static void mostrarImg(int img[][]) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.printf("%-5d", img[i][j]);
            }
            System.out.println();
        }
    }

    public static void negativoImg(int img[][]) {
        // invierte el brillo de la imagen que es 255 menos el valor actual de i
        int[][] invert = new int[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                invert[i][j] = 255 - img[i][j];
            }
        }
        mostrarImg(invert);
        // ya que estoy lo imprimo tmb
    }

    public static void binarizacionImg(int img[][]) {
        // pide un valor umbral; si es mayor = 255; si menos =0
        int binarizado[][] = new int[FILAS][COLUMNAS];
        System.out.println();
        System.out.print("Por favor introduzca el valor umbral: ");
        int umbral = Integer.parseInt(System.console().readLine());
        // voy a mostrarlo aqui del tiron
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (img[i][j] >= umbral) {
                    binarizado[i][j] = 255;
                } else {
                    binarizado[i][j] = 0;
                }
            }
        }
        mostrarImg(binarizado);
    }

    public static void espejoHorizontal(int img[][]) {
        // tengo que hacer que la fila 1 pase a la 6 y viceversa
        // la 2 a la 5 y viceversa
        // la 3 a la 4
        int[][] espejo = new int[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                espejo[i][COLUMNAS - 1 - j] = img[i][j];
            }
        }
        mostrarImg(espejo);
    }

    public static void rotacionImg(int img[][]) {
        // la primera fila pasa a ser la ultima columna
        int[][] rotar = new int[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                rotar[j][COLUMNAS - 1 - i] = img[i][j];
            }
        }
        mostrarImg(rotar);
    }

    public static void suavizadoImg(int img[][]) {
        int[][] suave = new int[FILAS][COLUMNAS];

        // 1. copiar todo (los bordes se quedan con su valor original)
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                suave[i][j] = img[i][j];
            }
        }

        // 2. recorrer solo los interiores (índices 1 a 4)
        for (int i = 1; i < FILAS - 1; i++) {
            for (int j = 1; j < COLUMNAS - 1; j++) {
                int suma = 0; // se reinicia en cada píxel
                for (int ii = -1; ii <= 1; ii++) {
                    for (int jj = -1; jj <= 1; jj++) {
                        suma += img[i + ii][j + jj];
                    }
                }
                suave[i][j] = suma / 9;
            }
        }
        mostrarImg(suave);
    }

    public static void histogramaBrillo(int img[][]) {
        int oscuro = 0;
        int medio = 0;
        int claro = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (img[i][j] <= 84) {
                    oscuro++;
                } else if (img[i][j] <= 170) {
                    medio++;
                } else {
                    claro++;
                }
            }
        }
        System.out.printf("%15s %4s %8s\n", "Oscuros (0-84):", oscuro, "pixeles");
        System.out.printf("%15s %4s %8s\n", "Medios  (85-170):", medio, "pixeles");
        System.out.printf("%15s %4s %8s\n", "Claros  (171-255):", claro, "pixeles");
    }

    public static void mostrarMenu() {
        System.out.println("=== PROCESADOR DE IMAGEN (6x6) ===");
        System.out.println("1. Negativo");
        System.out.println("2. Binarizar (umbral)");
        System.out.println("3. Espejo horizontal");
        System.out.println("4. Rotar 90° derecha  ");
        System.out.println("5. Filtro de suavizado (3x3)");
        System.out.println("6. Histograma de tonos  ");
        System.out.println("0.Salir");
    }

    public static void menuNav(int img[][]) {
        boolean menu = true;
        do {
            System.out.println();
            mostrarMenu();
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 0 -> {
                    System.out.println("Saliendo...");
                    menu = false;
                }
                case 1 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Pasando imágen a negativo...");
                    negativoImg(img);
                }
                case 2 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Binarizando imagen...");
                    binarizacionImg(img);
                }
                case 3 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Espejando horizantalmente...");
                    espejoHorizontal(img);
                }
                case 4 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Rotando imagen 90º...");
                    rotacionImg(img);
                }
                case 5 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Suavizando imagen...");
                    suavizadoImg(img);
                }
                case 6 -> {
                    System.out.println();
                    mostrarImg(img);
                    System.out.println("Cargando histograma de tonos...");
                    histogramaBrillo(img);
                    ;
                }

                default -> System.out.println("por favor introduzca una opción valida");
            }
        } while (menu == true);
    }
}