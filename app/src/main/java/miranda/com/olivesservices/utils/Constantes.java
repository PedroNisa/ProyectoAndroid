package miranda.com.olivesservices.utils;

/**
 * Constantes
 */
public class Constantes {

    /**
     * Puerto para la conexión.
     * En blanco utiliza el puerto por defecto
     */
    private static final String PUERTO_HOST = "";

    /**
     * Dirección IP de genymotion o AVD en local
     */
    private static final String IP = "http://10.0.2.2";

    /**
     * Dirección IP de genymotion o AVD en remoto
     */
  //  private static final String IP = "http://servicesolives.000webhostapp.com";

    /**
     * URLs del Web Service en local
     */
    public static final String GET_URL = IP + PUERTO_HOST + "/ServicesOlives/data/obtener.php";
    public static final String INSERT_URL = IP + PUERTO_HOST + "/ServicesOlives/data/insertar.php";
    /**
     * URLs del Web Service en remoto
     */
  //  public static final String GET_URL = IP + PUERTO_HOST + "/obtener.php";
  //  public static final String INSERT_URL = IP + PUERTO_HOST + "/insertar.php";



    /**
     * Campos de las respuestas Json
     */
    public static final String ID_ENTRADA = "idEntrada";
    public static final String ESTADO = "estado";
    public static final String ENTRADAS = "entradas";
    public static final String MENSAJE = "mensaje";

    /**
     * Códigos del campo {@link ESTADO}
     */
    public static final String SUCCESS = "1";
    public static final String FAILED = "2";

    /**
     * Tipo de cuenta para la sincronización
     */
    public static final String ACCOUNT_TYPE = "miranda.com.olivesservices.account";


}
