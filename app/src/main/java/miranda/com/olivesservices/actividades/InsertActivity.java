package miranda.com.olivesservices.actividades;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import miranda.com.olivesservices.estructuraProvider.Contrato;
import miranda.com.olivesservices.sincronizacion.SyncAdapter;
import miranda.com.olivesservices.utils.Utilidades;
import miranda.com.olivesservices.R;

/**
 * Created by root on 6/12/16.
 */
public class InsertActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText kilogramos;
    Spinner cliente;
    TextView fecha;
    EditText escandallo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        setToolbar();

        kilogramos = (EditText) findViewById(R.id.kilogramos);
        cliente = (Spinner) findViewById(R.id.cliente);
        fecha = (TextView) findViewById(R.id.fecha);
        escandallo = (EditText) findViewById(R.id.escandallo);

        fecha.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DateDialog().show(getSupportFragmentManager(), "DatePicker");
                    }
                }
        );

      }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void alClickearBoton(View v) {
        String kilosText = kilogramos.getText().toString();
        String clienteText = cliente.getSelectedItem().toString();
        String fechaText = fecha.getText().toString();
        String escandalloText = escandallo.getText().toString();

        ContentValues values = new ContentValues();
        values.put(Contrato.Columnas.KILOGRAMOS, kilosText);
        values.put(Contrato.Columnas.CLIENTE, clienteText);
        values.put(Contrato.Columnas.FECHA, fechaText);

        // si dejamos el campo escandallo vacio, insertamos "pendiente..." para que el operario sepa la situción actual.
        if(escandalloText==null || escandalloText.equals("")){
            values.put(Contrato.Columnas.ESCANDALLO, "PENDIENTE INSERTAR DATOS");
        }else {
            values.put(Contrato.Columnas.ESCANDALLO, escandalloText);
        }
        values.put(Contrato.Columnas.PENDIENTE_INSERCION, 1);

        getContentResolver().insert(Contrato.CONTENT_URI, values);
        //sincronizamos tras la insercion de nuevos registros
        SyncAdapter.sincronizarAhora(this, true);

        //si la inserción ha sido exitosa, lo mostramos a través de un Toast
        Toast toast1 = Toast.makeText(getApplicationContext(),"Entrada creada con éxito", Toast.LENGTH_LONG);
        toast1.show();;

        if (Utilidades.materialDesign())
            finishAfterTransition();
        else finish();
    }
        //Este método es invocado cada vez que el usuario cambia la fecha en el DatePicker.
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outDate = dateFormat.format(date);

        fecha.setText(outDate);
    }

}
