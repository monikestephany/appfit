package br.edu.sumare.appfit.Presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.edu.sumare.appfit.Domain.Aula.AulaAux;
import br.edu.sumare.appfit.Domain.Treino.Treino;
import br.edu.sumare.appfit.Infraestruture.AulaAdapter;
import br.edu.sumare.appfit.Domain.Aula.Aula;
import br.edu.sumare.appfit.R;

public class AulaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {
    private Context context;
    private ListView lv_contatos;
    public ArrayList<AulaAux> lista;
    private ArrayList<Aula> listaAula;
    public AulaAdapter adapter;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;

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

        listaAula = gerarAulas(10);
        context = getBaseContext();

        lv_contatos = (ListView)
                findViewById(R.id.lv_contatos);
        floatingActionButton = (FloatingActionButton)
                findViewById(R.id.float_add);

        lista = new ArrayList<AulaAux>();

        for (int i = 1; i < listaAula.size() -1; i++)
        {
            lista.add(convertAulasHM(listaAula.get(i)));
        }
        adapter = new AulaAdapter(context, lista);
        lv_contatos.setAdapter(adapter);

    }
    private void inicializarAcao() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatAdd();
            }
        });
    }
    public void FloatAdd() {
        lista.add(convertAulasHM( new Aula()));
        adapter.notifyDataSetChanged();
        lv_contatos.setSelection(lv_contatos.getAdapter().getCount() - 1);
    }
    private ArrayList<Aula> gerarAulas(int quantidade) {

        ArrayList<Aula> treinoDTOS = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.US);
        String hour = format.format(new Date());
        for (int i = 1; i <= quantidade; i++) {
            Aula fAux = new Aula();
            fAux.setProfessor("Professor - " + String.valueOf(i));
            fAux.setHora(hour);
            fAux.setAtivo(true);
            treinoDTOS.add(fAux);
        }

        return treinoDTOS;
    }
    private AulaAux convertAulasHM(Aula dto)
    {
        AulaAux aulaAux = new AulaAux();
        aulaAux.put(AulaAux.HORA,  dto.getHora());
        aulaAux.put(AulaAux.PROFESSOR, dto.getProfessor());

        return aulaAux;
    }
    private  void showMensalidade() {
        Intent it = new Intent(this, MensalidadeActivity.class);
        startActivity(it);
    }
    private void showTreino() {
        Intent it = new Intent(this, TreinoActivity.class);
        startActivity(it);
    }
    private  void showAula() {
        Intent it = new Intent(this, AulaActivity.class);
        startActivity(it);
    }
}