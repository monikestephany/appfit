package br.edu.sumare.appfit.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import br.edu.sumare.appfit.Domain.Mensalidade.MensaAux;
import br.edu.sumare.appfit.Domain.Mensalidade.Mensalidade;
import br.edu.sumare.appfit.Domain.Treino.Treino;
import br.edu.sumare.appfit.Infraestruture.MensalidadeAdapter;
import br.edu.sumare.appfit.R;

public class MensalidadeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {

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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

        context = getBaseContext();
        //
        lv_contatos = (ListView)
                findViewById(R.id.lv_contatos);
        //
        // representa as chaves do HashMap


        lv_contatos.setAdapter(
                new MensalidadeAdapter(
                        context,
                        gerarMensalidadeHM(gerarMensalidade(10)),
                        R.layout.celulamensalidade
                )
        );
    }
    private void inicializarAcao() {

        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> hmAux =
                        (HashMap<String, String>) parent.getItemAtPosition(position);

            }
        });
    }
    private ArrayList<Mensalidade> gerarMensalidade(int quantidade) {
        // Criar uma colecao VAZIA
        ArrayList<Mensalidade> mensalidadeDTOS = new ArrayList<>();
        //
        // Acessar o banco de dados e buscar esse funcionarios ( ainda nao sei )
        // Vou criar funcionarios ficticios.
        Date date = new Date();
        for (int i = 1; i <= quantidade; i++) {
            Mensalidade fAux = new Mensalidade();
            fAux.setStatus("A");
            fAux.setValor(10);
            fAux.setVencimento(date.toString());

            mensalidadeDTOS.add(fAux);
        }
        //
        return mensalidadeDTOS;
    }
    private ArrayList<MensaAux> gerarMensalidadeHM(ArrayList<Mensalidade> mensalidade) {

        ArrayList<MensaAux> mensalidades = new ArrayList<>();

        for (int i = 1; i <= mensalidade.size() -1; i++)
        {
            MensaAux mensaAux = new MensaAux();
            mensaAux.put(MensaAux.STATUS, mensalidade.get(i).getStatus());
            mensaAux.put(MensaAux.BOLETO, mensalidade.get(i).getBoleto());
            mensaAux.put(MensaAux.VALOR, String.valueOf(mensalidade.get(i).getValor()));
            mensaAux.put(MensaAux.VENCIMENTO, mensalidade.get(i).getVencimento());
            mensalidades.add(mensaAux);
        }

        return mensalidades;
    }
    private  void showAula() {
        Intent it = new Intent(this, AulaActivity.class);
        startActivity(it);
    }
    private void showTreino() {
        Intent it = new Intent(this, TreinoActivity.class);
        startActivity(it);
    }
    private  void showMensalidade() {
        Intent it = new Intent(this, MensalidadeActivity.class);
        startActivity(it);
    }
}