package com.example.supermegaultraturbofacturas2remix.io.facturas;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermegaultraturbofacturas2remix.R;

import java.util.List;


//Esta clase añade la lista de la factura  que hemos cogido de internet al RecyclerView
public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolder> {

    private List<FacturasVO.Factura> listaFactura;
    Dialog mDialog;


    public FacturaAdapter(List<FacturasVO.Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }

    @NonNull
    @Override
    public FacturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //La siguiente línea se memoriza
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_factura, parent, false));
    }

    //Añade el texto adecuado a la facruta
    @Override
    public void onBindViewHolder(@NonNull FacturaAdapter.ViewHolder holder, int position) {
        holder.tvFecha.setText(listaFactura.get(position).getFecha());
        holder.tvDescEstado.setText(listaFactura.get(position).getDescEstado());
        holder.tvImporteOrdenacion.setText(String.valueOf(listaFactura.get(position).getImporteOrdenacion()) + "€");


        //Para cambiar el color del textView tvDescEstado en función de lo que ponga
        if (listaFactura.get(position).getDescEstado().equals("Pendiente de pago")) {
            holder.tvDescEstado.setTextColor(Color.RED);
        } else if (listaFactura.get(position).getDescEstado().equals("Pagada")) {
            holder.tvDescEstado.setTextColor(0xFF8BC34A);
        } else {
            holder.tvDescEstado.setTextColor(Color.BLACK);
        }

    }

    //Cuenta el tamaño de la lista
    @Override
    public int getItemCount() {
        return listaFactura.size();
    }


    //Coge los componentes del layout (layout_factura) y los vincula con la vista
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFecha;

        TextView tvDescEstado;

        TextView tvImporteOrdenacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvDescEstado = itemView.findViewById(R.id.tvDescEstado);
            tvImporteOrdenacion = itemView.findViewById(R.id.tvImporteOrdenacion);

            //Se realiza la inicialización del Dialog mDialog
            mDialog = new Dialog(itemView.getContext());

            itemView.setOnClickListener(view -> {
                mDialog.setContentView(R.layout.layout_popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView mensajePopup = mDialog.findViewById(R.id.mensajePopup);
                mensajePopup.setText("Esta funcionalidad aún no está disponible");
                mDialog.show();
                Button cerrarButton = mDialog.findViewById(R.id.botonCerrar);
                cerrarButton.setOnClickListener(new View.OnClickListener() {

                    // Para cerrar el diálogo al pulsar el botón "CERRAR"
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
            });
        }
    }
}
