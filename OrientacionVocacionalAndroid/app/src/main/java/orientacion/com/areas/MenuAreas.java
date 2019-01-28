package orientacion.com.areas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

import orientacion.com.DatosAuxiliar;
import orientacion.com.R;
import orientacion.com.api.response.PreguntasResponse;
import orientacion.com.basedatos.DBHelper;
import orientacion.com.util.LoadJSONFromAsset;

public class MenuAreas extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int indexOfTarget = 1, contadorRegistroIteracionDos = 0;
    private DBHelper admin;
    private SQLiteDatabase bdatos;
    private ContentValues values;
    private String convertirTextoResultValidacion = "", convertirTextoResult = "", coneccionIP="", curp="";


    ArrayList<DatosAuxiliar> arrayListPreguntas = new ArrayList<DatosAuxiliar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_preguntas);
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);

        Bundle bundle = getIntent().getExtras();
        coneccionIP = bundle.getString("key_direccionIP");
        curp = bundle.getString("key_Curp");

        //CONFIGURAMOS CARDVIEW PARA LAS PREGUNTAS
        mSwipeView.disableTouchSwipe();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));


        //PINTAMOS LAS RESPUESTAS
        try {
            for(PreguntasResponse datos : LoadJSONFromAsset.loadContent(this.getApplicationContext(), "areas.json")){
                mSwipeView.addView(new CardViewAreas(this, datos, mSwipeView));
                arrayList.add(datos.getPregunta());
            }
        }catch (NullPointerException e){
            Log.i("RESULTADO", "No se encontro resultados");
        }


        /* conectamos la base de datos */
        admin = new DBHelper(this,null,null,1);
        bdatos = admin.getWritableDatabase();
        /* Limpiamos la tabla cada que inicie sesion al usuario */
        bdatos.execSQL("delete from areas");
        bdatos.execSQL("delete from sqlite_sequence where name='areas' ");
        values = new ContentValues();


        //boton siguiente -- tiene contador por cada click
        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* validamos si se ha seleccionado una respuesta */
                if (convertirTextoResultValidacion.length() > 0) {
                    mSwipeView.doSwipe(false);

                    int resultadoEntero = Integer.parseInt(convertirTextoResult);
                    DatosAuxiliar valores = new DatosAuxiliar(0, resultadoEntero,0,0, 0);
                    arrayListPreguntas.add(valores);
                    Log.d("RESULTADO", "INSERTADO: "+ resultadoEntero);

                    values.put("puntos", resultadoEntero);
                    bdatos.insert("areas", null, values);

                    indexOfTarget++;
                    contadorRegistroIteracionDos ++;
                    convertirTextoResultValidacion = "";
                }if (convertirTextoResultValidacion.equals("")) {
                    if (contadorRegistroIteracionDos <= 50 ){
                        Toast.makeText(MenuAreas.this, "Debes seleccionar una respuesta", Toast.LENGTH_LONG).show();
                    }
                }
                /* validamos si ya termino las preguntas para pasar al siguiente venta al resultado final */
                if (indexOfTarget == 51 ){
                    Log.d("RESULTADO", "TOTAL: "+arrayListPreguntas.size());

                    Intent intent = new Intent(MenuAreas.this, RespuestaAreas.class);
                    intent.putExtra("key_direccionIP", coneccionIP);
                    intent.putExtra("key_Curp", curp);
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
