package br.edu.sumare.appfit.Presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import android.view.MenuItem;

import br.edu.sumare.appfit.Domain.Aula.Aula;
import br.edu.sumare.appfit.Domain.Treino.Treino;
import br.edu.sumare.appfit.R;

public class TreinoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {

    private Context context;

    private ListView lv_contatos;
    private EditText edt_execicio;
    public ArrayList<HashMap<String, String>> lista;
    private ArrayList<Treino> listaTreino;
    public SimpleAdapter adapter;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private int _position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        inicializarVariavel();
        inicializarAcao();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

        listaTreino = gerarTreinos(10);
        context = getBaseContext();

        lv_contatos = (ListView)
                findViewById(R.id.lv_contatos);
        //
        lista = new ArrayList<HashMap<String, String>>();
        // representa as chaves do HashMap
        String[] De = {"idAparelhos","idSerie","idDescanso"};
        for (int i = 1; i < listaTreino.size() -1; i++)
        {
            lista.add(convertTreinosHM(listaTreino.get(i)));
        }
        // representa os campos da tela (celula)
        int[] Para = {R.id.Edit_Execicio, R.id.Edit_Serie,R.id.Edit_Descanso};
        adapter = new  SimpleAdapter(
                context,
                lista,
                R.layout.celulatreino,
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
                _position = position;
            }
        }
        );

    }

    private ArrayList<Treino> gerarTreinos(int quantidade) {
        // Criar uma colecao VAZIA
        ArrayList<Treino> treinos = new ArrayList<>();
        //
        // Acessar o banco de dados e buscar esse funcionarios ( ainda nao sei )
        // Vou criar funcionarios ficticios.
        for (int i = 1; i <= quantidade; i++) {
            Treino fAux = new Treino();
            fAux.setAparelho("Aparelho - " + String.valueOf(i));
            fAux.setDescanso("1" + String.valueOf(i));
            fAux.setSerie("X - " + String.valueOf(i));
            treinos.add(fAux);
        }
        //
        return treinos;
    }
    private HashMap<String, String> convertTreinosHM(Treino dto)
    {
            HashMap<String, String> hmAux = new HashMap<>();
            hmAux.put("idAparelhos",  dto.getAparelho());
            hmAux.put("idSerie", dto.getSerie());
            hmAux.put("idDescanso", dto.getSerie());

        return hmAux;
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

