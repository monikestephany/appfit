package br.edu.sumare.appfit.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.sumare.appfit.Domain.Aluno.Aluno;
import br.edu.sumare.appfit.R;

public class AlunoActivity extends AppCompatActivity {

    private Context context;
    private ArrayList<Aluno> dados;
    private ImageView img_treino;
    private ListView lv_contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        getSupportActionBar().setTitle("Alunos");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorBack));
        inicializarVariavel();
        inicializarAcao();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menupage; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menupage, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuTreinos:
                showTreino();
                return true;
            case R.id.menuAulas:
                showAula();
                return true;
            case R.id.menuMensalidades:
                showMensalidade();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inicializarVariavel() {

        context = getBaseContext();

        img_treino = (ImageView)findViewById(R.id.img_conta);
        lv_contatos = (ListView)
                findViewById(R.id.lv_contatos);
        //
        // representa as chaves do HashMap
        String[] De = {"idAluno"};

        // representa os campos da tela (celula)
        int[] Para = {R.id.tv_aluno};
        SimpleAdapter adapter = new  SimpleAdapter(
                context,
                gerarAlunosHM(gerarAlunos(10)),
                R.layout.celulaaluno,
                De,
                Para
        );
        lv_contatos.setAdapter(adapter
        );
    }

    private void inicializarAcao() {
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> hmAux =
                        (HashMap<String, String>) parent.getItemAtPosition(position);

                Toast.makeText(
                        context,
                        hmAux.get("idAluno"),
                        Toast.LENGTH_SHORT
                ).show();
                Intent it = new Intent(context, TreinoActivity.class);
                startActivity(it);
            }
        });
    }
    private ArrayList<Aluno> gerarAlunos(int quantidade) {
        // Criar uma colecao VAZIA
        ArrayList<Aluno> alunos = new ArrayList<>();
        //
        // Acessar o banco de dados e buscar esse funcionarios ( ainda nao sei )
        // Vou criar funcionarios ficticios.
        for (int i = 1; i <= quantidade; i++) {
            Aluno fAux = new Aluno();
            fAux.setCpf("CPF - " + String.valueOf(i));
            fAux.setNome("Nome - " + String.valueOf(i));
            fAux.setSede("Sede - " + String.valueOf(i));
            alunos.add(fAux);
        }
        //
        return alunos;
    }
    private ArrayList<HashMap<String, String>> gerarAlunosHM(ArrayList<Aluno> dto) {
        // Criar uma colecao VAZIA
        ArrayList<HashMap<String, String>> alunos = new ArrayList<>();
        //
        // Acessar o banco de dados e buscar esse funcionarios ( ainda nao sei )
        // Vou criar funcionarios ficticios.
        for (int i = 1; i <= dto.size() -1; i++) {
            HashMap<String, String> hmAux = new HashMap<>();
            hmAux.put("idAluno",  dto.get(i).getCpf() + " - " + dto.get(i).getNome());
            alunos.add(hmAux);
        }
        //
        return alunos;
    }
    private  void showMensalidade() {
        Intent it = new Intent(this, MensalidadeActivity.class);
        startActivity(it);
    }
    private void showAula() {
        Intent it = new Intent(this, AulaActivity.class);
        startActivity(it);
    }
    private void showTreino() {
        Intent it = new Intent(this, TreinoActivity.class);
        startActivity(it);
    }

}

