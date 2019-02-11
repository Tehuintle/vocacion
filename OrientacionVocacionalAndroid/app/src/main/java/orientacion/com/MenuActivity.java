package orientacion.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import orientacion.com.areas.MenuAreas;
import orientacion.com.capacitacion.MenuCapacitacion;
import orientacion.com.tics.MenuTerminales;

public class MenuActivity extends AppCompatActivity {

	private String coneccionIP="", curp="", idAlumno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

		Bundle bundle = getIntent().getExtras();
		coneccionIP = bundle.getString("key_direccionIP");
		curp = bundle.getString("key_Curp");
		try{
			idAlumno = bundle.getString("key_IdAlumno", "");
		}catch (Exception e){}

		findViewById(R.id.btnAreas).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuAreas.class);
				intent.putExtra("key_direccionIP", coneccionIP);
				intent.putExtra("key_Curp", curp);
				startActivityForResult(intent, 1);
			}
		});


		findViewById(R.id.btnTics).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuTerminales.class);
				intent.putExtra("key_direccionIP", coneccionIP);
				intent.putExtra("key_Curp", curp);
				intent.putExtra("key_IdAlumno", idAlumno);
				startActivityForResult(intent, 1);
			}
		});

		findViewById(R.id.btnCapacitacion).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuCapacitacion.class);
				intent.putExtra("key_direccionIP", coneccionIP);
				intent.putExtra("key_Curp", curp);
				startActivityForResult(intent, 1);
			}
		});

    }
}
