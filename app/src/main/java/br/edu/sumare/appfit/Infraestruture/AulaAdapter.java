package br.edu.sumare.appfit.Infraestruture;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.sumare.appfit.Domain.Aula.AulaAux;
import br.edu.sumare.appfit.R;

public class AulaAdapter extends ArrayAdapter<AulaAux> {
    private Context context;
    private ArrayList<AulaAux> dados;
    private AulaAux aulaAux;
    private Calendar myCalendar;
    private LayoutInflater mInflater;
    private EditText tv_hora;

    public AulaAdapter(Context context, ArrayList<AulaAux> lista) {
        super(context, 0, lista);
        this.context = context;
        this.dados = lista;

        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dados.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AulaAux item = getItem(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.celulaaula, parent, false);
        }
        final EditText tv_professor = (EditText)
                convertView.findViewById(R.id.Edit_Professor);
        tv_hora = (EditText)
                convertView.findViewById(R.id.Edit_Hora);
        ImageButton btn_delete = (ImageButton)
                convertView.findViewById(R.id.btn_remover_aula);

        aulaAux = dados.get(position);

        final int pos = position;

        tv_professor.setText(aulaAux.get(AulaAux.PROFESSOR));
        tv_hora.setText(aulaAux.get(AulaAux.HORA));

        tv_professor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    aulaAux.put(AulaAux.PROFESSOR, tv_professor.getText().toString());
                    dados.set(pos, aulaAux);
                    notifyDataSetChanged();
                }
            }
        });
        tv_hora.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    aulaAux.put(AulaAux.HORA, tv_hora.getText().toString());
                    dados.set(pos, aulaAux);
                    notifyDataSetChanged();
                } else {

                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dados.remove(item);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
