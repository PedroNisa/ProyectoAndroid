package miranda.com.olivesservices.actividades;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import miranda.com.olivesservices.R;

/**
 * Created by root on 6/12/16.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private Cursor cursor;
    private Context context;
    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView kilogramos;
        public TextView cliente;
        public TextView fecha;
        public TextView escandallo;


        public ViewHolder(View v) {
            super(v);
            kilogramos = (TextView) v.findViewById(R.id.kilogramos);
            cliente = (TextView) v.findViewById(R.id.cliente);
            fecha = (TextView) v.findViewById(R.id.fecha);
            escandallo = (TextView) v.findViewById(R.id.escandallo);
        }
    }

    public Adaptador(Context context) {
        this.context= context;
    }

    @Override
    public int getItemCount() {
        if (cursor!=null)
            return cursor.getCount();
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        cursor.moveToPosition(position);

        String kilogramos;
        String cliente;
        String fecha;
        String escandallo;

        kilogramos = cursor.getString(1);
        cliente = cursor.getString(2);
        fecha = cursor.getString(3);
        escandallo = cursor.getString(4);

        viewHolder.kilogramos.setText(kilogramos);
        viewHolder.cliente.setText(cliente);
        viewHolder.fecha.setText(fecha);
        viewHolder.escandallo.setText(escandallo);
    }

    public void swapCursor(Cursor newCursor) {
        cursor = newCursor;
        notifyDataSetChanged();
    }

    public Cursor getCursor() {
        return cursor;
    }
}