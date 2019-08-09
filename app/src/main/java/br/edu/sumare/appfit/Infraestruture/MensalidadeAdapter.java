package br.edu.sumare.appfit.Infraestruture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.sumare.appfit.Domain.Aula.AulaAux;
import br.edu.sumare.appfit.Domain.Mensalidade.MensaAux;
import br.edu.sumare.appfit.Domain.Mensalidade.Mensalidade;
import br.edu.sumare.appfit.R;

public class MensalidadeAdapter extends BaseAdapter {
    private Context context;

    // Minha colecao
    private ArrayList<MensaAux> dados;

    // Layout de Celula da minha lista
    private int resource;

    // Le o xml e transforma em um binario.
    private LayoutInflater mInflater;

    public MensalidadeAdapter(Context context, ArrayList<MensaAux> dados, int resource) {
        this.context = context;
        this.dados = dados;
        this.resource = resource;
//        this.mInflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mInflater = LayoutInflater.from(context);
    }

    // Devolvo a quantidade de registros no meu adaptador.
    @Override
    public int getCount() {
        return dados.size();
    }

    // Devolve um registro/hmaux para posicao especifica
    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    // Devolver o ID do resgitro de uma posicao especifica
    @Override
    public long getItemId(int position) {
        MensaAux aulaAux = dados.get(position);

        return Long.parseLong(aulaAux.get(MensaAux.ID));
    }

    // Retorna uma Celula/View composta dados e junta com o layout da celula
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = mInflater.inflate(resource, parent, false);
        }
        TextView tv_status = (TextView)
                convertView.findViewById(R.id.tv_status);
        TextView tv_venc = (TextView)
                convertView.findViewById(R.id.tv_Venc);
        TextView tv_valor = (TextView)
                convertView.findViewById(R.id.tv_Valor);

        MensaAux aulaAux = dados.get(position);

        if (aulaAux.get(MensaAux.STATUS) == "A")
        {
            tv_status.setBackgroundColor((int)R.color.colorStatusAberto);
            tv_status.setText("Aberto");
        }
        else if (aulaAux.get(MensaAux.STATUS) == "V")
        {
            tv_status.setBackgroundColor((int)R.color.colorStatusVencido);
            tv_status.setText("Vencido");
        }
        else if (aulaAux.get(MensaAux.STATUS) == "P")
        {
            tv_status.setBackgroundColor((int)R.color.colorStatusPago);
            tv_status.setText("Pago");
        }

        tv_venc.setText(aulaAux.get(MensaAux.VENCIMENTO));
        tv_valor.setText(aulaAux.get(MensaAux.VALOR));

        return convertView;
    }
}
