package orientacion.com;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

import orientacion.com.util.Utils;

public class MenuPreguntas extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int indexOfTarget = 1, contadorRegistro = 0, contadorRegistroIteracionUno = 0, contadorRegistroIteracionDos = 0, contadorRegistroIteracionTres = 0;
    private DBHelper admin;
    private SQLiteDatabase bdatos;
    private ContentValues values;
    private String convertirTextoResultValidacion = "", convertirTextoResult = "", nombre="";


    ArrayList<DatosAuxiliar> arrayListPreguntas = new ArrayList<DatosAuxiliar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);

        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString("nombre");

        //CONFIGURAMOS CARDVIEW PARA LAS PREGUNTAS
        mSwipeView.disableTouchSwipe();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));


        //PINTAMOS LAS RESPUESTAS
        try {
            for(Datos datos : Utils.loadProfiles(this.getApplicationContext())){
                mSwipeView.addView(new CardView(this, datos, mSwipeView));
                arrayList.add(datos.getPregunta());
            }
        }catch (NullPointerException e){
            Log.i("RESULTADO", "No se encontrÛ resultados");
        }


        /* conectamos la base de datos */
        admin = new DBHelper(this,null,null,1);
        bdatos = admin.getWritableDatabase();
        /* Limpiamos la tabla cada que inicie sesion al usuario */
        bdatos.execSQL("delete from usuarios");
        bdatos.execSQL("delete from sqlite_sequence where name='usuarios' ");
        values = new ContentValues();


        //boton siguiente -- tiene contador por cada click
        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* validamos si se ha seleccionado una respuesta */
                if (convertirTextoResultValidacion.length() > 0) {
                    mSwipeView.doSwipe(false);

                    int resultadoEntero = Integer.parseInt(convertirTextoResult);
                    DatosAuxiliar valores = new DatosAuxiliar(resultadoEntero,0,0,0, 0);
                    arrayListPreguntas.add(valores);
                    Log.d("RESULTADO", "INSERTADO: "+ resultadoEntero);

                    values.put("puntos", resultadoEntero);
                    bdatos.insert("usuarios", null, values);

                    indexOfTarget++;
                    contadorRegistro ++;
                    contadorRegistroIteracionUno ++;
                    contadorRegistroIteracionDos ++;
                    contadorRegistroIteracionTres ++;
                    convertirTextoResultValidacion = "";
                }if (convertirTextoResultValidacion.equals("")) {
                    if (contadorRegistroIteracionDos <= 16 ){
                        Toast.makeText(MenuPreguntas.this, "Debes seleccionar una respuesta", Toast.LENGTH_LONG).show();
                    }
                }
                /* validamos si ya termino las preguntas para pasar al siguiente venta al resultado final */
                if (indexOfTarget == 17 ){
                    Log.d("RESULTADO", "TOTAL: "+arrayListPreguntas.size());

                    Intent intent = new Intent(MenuPreguntas.this, Respuesta.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", nombre);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                    bdatos.close();
                    finish();
                }

                Log.i("RESULTADO", "click contador: " + indexOfTarget);
            }
        });
    }


    /* Recibimos el valor selecionado */
    public void setValorSeleccionado(final int resultado) {
        Log.d("RESULTADO", "seleccionado: "+resultado);
        convertirTextoResult = String.valueOf(resultado);
        convertirTextoResultValidacion = String.valueOf(resultado);
    }
}
