package orientacion.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import dmax.dialog.SpotsDialog;
import orientacion.com.api.APIInterface;

public class AccederCurp extends AppCompatActivity {

	private Button btnCURP;
	private EditText edtCURP;
	private SpotsDialog dialog;
	private APIInterface apiInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceder_curp);

		dialog=new SpotsDialog(AccederCurp.this);
		btnCURP=(Button)findViewById(R.id.btnValidarCurp);
		edtCURP = (EditText)findViewById(R.id.edtCURP);

		btnCURP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//dialog.show();
				String curp = edtCURP.getText().toString();
				validarCurp(curp);
				if (!curp.isEmpty()) {
					Intent intent = new Intent(AccederCurp.this, MenuActivity.class);
					startActivityForResult(intent, 1);
				}else {
					FancyToast.makeText(AccederCurp.this, "Favor digite su CURP de 18 carácteres", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
				}
			}
		});
	}

	private void validarCurp(String curp) {

	}
}
