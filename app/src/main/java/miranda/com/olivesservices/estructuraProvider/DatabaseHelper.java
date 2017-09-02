package miranda.com.olivesservices.estructuraProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 6/12/16.
 * Clase envoltura para el gestor de Bases de datos
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase database) {
        createTable(database); // Crear la tabla "entrada"
    }

    /**
     * Crear tabla en la base de datos
     *
     * @param database Instancia de la base de datos
     */
    private void createTable(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + Contrato.ENTRADA + " (" +
                Contrato.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contrato.Columnas.KILOGRAMOS + " TEXT, " +
                Contrato.Columnas.CLIENTE + " TEXT, " +
                Contrato.Columnas.FECHA + " TEXT, " +
                Contrato.Columnas.ESCANDALLO + " TEXT," +
                Contrato.Columnas.ID_REMOTA + " TEXT UNIQUE," +
                Contrato.Columnas.ESTADO + " INTEGER NOT NULL DEFAULT "+ Contrato.ESTADO_OK+"," +
                Contrato.Columnas.PENDIENTE_INSERCION + " INTEGER NOT NULL DEFAULT 0)";
        database.execSQL(cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try { db.execSQL("drop table " + Contrato.ENTRADA); }
        catch (SQLiteException e) { }
        onCreate(db);
    }

}
