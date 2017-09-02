package miranda.com.olivesservices.sincronizacion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by root on 6/12/16.
 */
public class ServicioAutenticador extends Service {

    // Instancia del autenticador
    private Autenticador autenticador;

    @Override
    public void onCreate() {
        // Nueva instancia del autenticador
        autenticador = new Autenticador(this);
    }

    /*
     * Ligando el servicio al framework de Android
     */
    @Override
    public IBinder onBind(Intent intent) {
        return autenticador.getIBinder();
    }
}