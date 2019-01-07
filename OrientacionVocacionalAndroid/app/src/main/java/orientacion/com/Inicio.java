package orientacion.com;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        final EditText nombre = (EditText)findViewById(R.id.edtNombre);

        findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String valor = nombre.getText().toString();
                if (!valor.isEmpty()){
                    Intent intent = new Intent(Inicio.this, MenuPreguntas.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", nombre.getText().toString());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);
                } else {
                    Toast.makeText(Inicio.this, "Campo vacío, digite su nombre", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
