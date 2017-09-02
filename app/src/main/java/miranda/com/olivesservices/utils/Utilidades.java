package miranda.com.olivesservices.utils;

import android.database.Cursor;
import android.os.Build;
import android.util.Log;

import miranda.com.olivesservices.estructuraProvider.Contrato;

import org.json.JSONException;
import org.json.JSONObject;

public class Utilidades {
    // Indices para las columnas indicadas en la proyección
    public static final int COLUMNA_ID = 0;
    public static final int COLUMNA_ID_REMOTA = 1;
    public static final int COLUMNA_KILOGRAMOS = 2;
    public static final int COLUMNA_CLIENTE = 3;
    public static final int COLUMNA_FECHA = 4;
    public static final int COLUMNA_ESCANDALLO = 5;

    /**
     * Determina si la aplicación corre en versiones superiores o iguales
     * a Android LOLLIPOP
     *
     * @return booleano de confirmación
     */
    public static boolean materialDesign() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Copia los datos de un registro almacenado en un cursor hacia un
     * JSONObject
     *
     * @param c cursor
     * @return objeto json
     */
    public static JSONObject deCursorAJSONObject(Cursor c) {
        JSONObject jObject = new JSONObject();
        String kilogramos;
        String cliente;
        String fecha;
        String escandallo;

        kilogramos = c.getString(COLUMNA_KILOGRAMOS);
        cliente = c.getString(COLUMNA_CLIENTE);
        fecha = c.getString(COLUMNA_FECHA);
        escandallo = c.getString(COLUMNA_ESCANDALLO);


        try {
            jObject.put(Contrato.Columnas.KILOGRAMOS, kilogramos);
            jObject.put(Contrato.Columnas.CLIENTE, cliente);
            jObject.put(Contrato.Columnas.FECHA, fecha);
            jObject.put(Contrato.Columnas.ESCANDALLO, escandallo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Cursor a JSONObject", String.valueOf(jObject));

        return jObject;
    }
}
