package br.edu.sumare.appfit.Presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.sumare.appfit.R;


public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button btn_login;
    private EditText edt_cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inicializarVariavel();
        inicializarAcao();
    }

    private void inicializarVariavel() {
        context = getBaseContext();
        btn_login = (Button)
                findViewById(R.id.btn_login_logar);
        edt_cpf = (EditText)findViewById(R.id.edt_login_cpf);

    }
    private void inicializarAcao() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(edt_cpf.getText().toString().trim().equals("39124368865"))
              {
                  Intent it = new Intent(MainActivity.this, AlunoActivity.class);
                  startActivity(it);
              }
              else {
                  Intent it = new Intent(MainActivity.this, TreinoActivity.class);
                  startActivity(it);
              }
            }
        });
    }
   }