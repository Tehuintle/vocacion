package orientacion.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import orientacion.com.areas.MenuAreas;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

		findViewById(R.id.btnAreas).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuAreas.class);
				Bundle bundle = new Bundle();
				bundle.putString("nombre", "Robot");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});

		findViewById(R.id.btnTics).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuAreas.class);
				Bundle bundle = new Bundle();
				bundle.putString("nombre", "Robot");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});

		findViewById(R.id.btnCapacitacion).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, MenuAreas.class);
				Bundle bundle = new Bundle();
				bundle.putString("nombre", "Robot");
				intent.putExtras(bundle);
				startActivityForResult(intent, 1);
			}
		});

    }
}
