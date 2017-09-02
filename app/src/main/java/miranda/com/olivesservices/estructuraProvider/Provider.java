package miranda.com.olivesservices.estructuraProvider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by root on 6/12/16.
 */
public class Provider extends ContentProvider

    {
        /**
         * Nombre de la base de datos
         */
        private static final String DATABASE_NAME = "stockolives.db";
        /**
         * Versión actual de la base de datos
         */
        private static final int DATABASE_VERSION = 1;
        /**
         * Instancia global del Content Resolver
         */
        private ContentResolver resolver;
        /**
         * Instancia del administrador de BD
         */
        private DatabaseHelper databaseHelper;

        @Override
        public boolean onCreate() {
        // Inicializando gestor BD
        databaseHelper = new DatabaseHelper(
                getContext(),
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );

        resolver = getContext().getContentResolver();

        return true;
    }

        @Override
        public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        // Obtener base de datos
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        // Comparar Uri
        int match = Contrato.uriMatcher.match(uri);

        Cursor c;

        switch (match) {
            case Contrato.ALLROWS:
                // Consultando todos los registros
                c = db.query(Contrato.ENTRADA, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(
                        resolver,
                        Contrato.CONTENT_URI);
                break;
            case Contrato.SINGLE_ROW:
                // Consultando un solo registro basado en el Id del Uri
                long idEntrada = ContentUris.parseId(uri);
                c = db.query(Contrato.ENTRADA, projection,
                        Contrato.Columnas._ID + " = " + idEntrada,
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(
                        resolver,
                        Contrato.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;


    }
    

        @Override
        public String getType(Uri uri) {
        switch (Contrato.uriMatcher.match(uri)) {
            case Contrato.ALLROWS:
                return Contrato.MULTIPLE_MIME;
            case Contrato.SINGLE_ROW:
                return Contrato.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de entrada desconocida: " + uri);
        }
    }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
        // Validar la uri
        if (Contrato.uriMatcher.match(uri) != Contrato.ALLROWS) {
            throw new IllegalArgumentException("URI desconocida : " + uri);
        }
        ContentValues contentValues;
        if (values != null) {
            contentValues = new ContentValues(values);
        } else {
            contentValues = new ContentValues();
        }

        // Inserción de nueva fila
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long rowId = db.insert(Contrato.ENTRADA, null, contentValues);
        if (rowId > 0) {
            Uri uri_entrada = ContentUris.withAppendedId(
                    Contrato.CONTENT_URI, rowId);
            resolver.notifyChange(uri_entrada, null, false);
            return uri_entrada;
        }
        throw new SQLException("Falla al insertar fila en : " + uri);
    }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        int match = Contrato.uriMatcher.match(uri);
        int affected;

        switch (match) {
            case Contrato.ALLROWS:
                affected = db.delete(Contrato.ENTRADA,
                        selection,
                        selectionArgs);
                break;
            case Contrato.SINGLE_ROW:
                long idEntrada = ContentUris.parseId(uri);
                affected = db.delete(Contrato.ENTRADA,
                        Contrato.Columnas.ID_REMOTA + "=" + idEntrada
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                // Notificar cambio asociado a la uri
                resolver.
                        notifyChange(uri, null, false);
                break;
            default:
                throw new IllegalArgumentException("Elemento desconocido: " +
                        uri);
        }
        return affected;
    }

        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int affected;
        switch (Contrato.uriMatcher.match(uri)) {
            case Contrato.ALLROWS:
                affected = db.update(Contrato.ENTRADA, values,
                        selection, selectionArgs);
                break;
            case Contrato.SINGLE_ROW:
                String idEntrada = uri.getPathSegments().get(1);
                affected = db.update(Contrato.ENTRADA, values,
                        Contrato.Columnas.ID_REMOTA + "=" + idEntrada
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        resolver.notifyChange(uri, null, false);
        return affected;
    }

    }


