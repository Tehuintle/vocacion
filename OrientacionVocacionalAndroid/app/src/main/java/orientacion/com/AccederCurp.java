package orientacion.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
				dialog.show();
				String curp = edtCURP.getText().toString();
				validarCurp(curp);
			}
		});
	}

	private void validarCurp(String curp) {

	}
}
