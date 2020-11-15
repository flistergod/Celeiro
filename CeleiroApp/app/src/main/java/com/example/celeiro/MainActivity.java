package com.example.celeiro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.celeiro.ui.SessionManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity<bullshit> extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    String[] quotes = new String[] {"Não podemos cozinhar se não gostamos de pessoas.", "Gastronomia é a arte de usar comida para criar felicidade.", "Aprendemos a cozinhar com a memória dos outros... E em algum momento, nós fazemos a nossa.", "A cozinha é um pouco como cinema. É a emoção que conta.", "Os animais pastam, os homem comem, mas apenas os nobres de espírito sabem comer.", "Se não sois capaz de um pouco de feitiçaria, não vale a pena meter-vos a cozinheiro.", "Quem é cozinheiro pode sê-lo plenamente pela mobilização dos seus cinco sentidos.", "É uma história de amor a cozinha, você tem que se apaixonar pelos produtos e pelas pessoas que os fazem.", "O mau cozinheiro é aquele que tenta esconder o sabor original do ingrediente em vez de revelá-lo.", "Todos os homens se nutrem, mas poucos sabem distinguir os sabores.", "Cozinhar é fazer poesia para ser degustada.", "O prazer dos banquetes não está na abundância dos pratos e, sim, na reunião dos amigos e na conversação.", "A gastronomia é uma aquisição. Uma vez assimilada, a pessoa não consegue se livrar dela.", "Sem condimentos uma boa culinária não existe. Sem o sabor que vem da terra, as tradições ficam incompletas.", "Não há boa culinária se, à princípio, ela não é feita pela amizade a quem ela se destina.", "Só quem ama gastronomia de verdade sabe o quanto é gratificante ouvir elogios após uma bela garfada.", "Um grande cozinheiro não é o que elabora grandes pratos, é o que os prepara com amor.", "A gastronomia transforma alimentos em arte."};

    int radioId;
    RadioButton radioButton;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView b_exit=findViewById(R.id.imageExit);
        ImageView fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String randomQuote = quotes[(int) (Math.random() * quotes.length)];

                Snackbar.make(view, randomQuote, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //colocar aqui todas as navs, o back não é necessário
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_menudia, R.id.nav_menu, R.id.nav_clientes, R.id.nav_servicos, R.id.nav_galeria_pratos, R.id.nav_contactos
        ,R.id.nav_galeria_casa, R.id.nav_galeria_festas, R.id.nav_galeria_pratos, R.id.nav_galeria_saladas, R.id.nav_galeria_snacks,
                R.id.nav_menuClientesBase, R.id.menuClientesEncomenda, R.id.menuClientesDados, R.id.menuClientesPromo
              )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        /**para sair da area de cliente em qualquer lado
         * fazer override do finish para não ser tão agressivo
         * depois de sair, o botao exit desaparece
         * */


        b_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b_exit.setVisibility(View.INVISIBLE);

                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

        //check if user is logged in
        //if user is logged in --> move to client profile

        SessionManagement sessionManagement= new SessionManagement(this);
        int userID= sessionManagement.getSession();

        if(userID!=-1){

            Bundle bundle= new Bundle();
            bundle.putString("username", sessionManagement.getSessionUsername());
            ImageView b_exit=findViewById(R.id.imageExit);
            b_exit.setVisibility(View.VISIBLE);

        }
    }







    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(17432578,17432579);

    }







    /**
     public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Tem a certeza que quer sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
