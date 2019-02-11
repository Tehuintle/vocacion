package orientacion.com.tics;

import android.app.Activity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import orientacion.com.R;
import orientacion.com.api.response.TicsResponse;

@Layout(R.layout.card_view_tics)
public class CardViewTics {

    @View(R.id.preguntaNameTxt)
    private TextView preguntaNameTxt;
    //-------->
    @View(R.id.radioButtonA)
    private CheckBox radioButtonA;

    @View(R.id.radioButtonB)
    private CheckBox radioButtonB;

    @View(R.id.radioButtonC)
    private CheckBox radioButtonC;

    @View(R.id.radioButtonD)
    private CheckBox radioButtonD;

    @View(R.id.radioButtonE)
    private CheckBox radioButtonE;

	@View(R.id.radioButtonF)
	private CheckBox radioButtonF;

	@View(R.id.radioButtonH)
	private CheckBox radioButtonH;

	@View(R.id.radioButtonI)
	private CheckBox radioButtonI;

	@View(R.id.radioButtonJ)
	private CheckBox radioButtonJ;

	@View(R.id.radioButtonK)
	private CheckBox radioButtonK;

    private TicsResponse mDatos;
    private Activity activity;
    private SwipePlaceHolderView mSwipeView;

    public CardViewTics(Activity activity, TicsResponse datos, SwipePlaceHolderView swipeView) {
        this.activity =  activity;
        mDatos = datos;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){
       Log.d("RESULTADO", "Pintamos los resultados");
       preguntaNameTxt.setText(mDatos.getPregunta());
        radioButtonA.setText(mDatos.getPregunta1());
        radioButtonB.setText(mDatos.getPregunta2());

        if (!mDatos.getPregunta3().equals("")){
			radioButtonC.setVisibility(android.view.View.VISIBLE);
			radioButtonC.setText(mDatos.getPregunta3());
		}else {
			radioButtonC.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta4().equals("")){
			radioButtonD.setVisibility(android.view.View.VISIBLE);
			radioButtonD.setText(mDatos.getPregunta4());
		}else {
			radioButtonD.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta5().equals("")){
			radioButtonE.setVisibility(android.view.View.VISIBLE);
			radioButtonE.setText(mDatos.getPregunta5());
		}else
			radioButtonE.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta6().equals("")){
			radioButtonF.setVisibility(android.view.View.VISIBLE);
			radioButtonF.setText(mDatos.getPregunta6());
		}else {
			radioButtonF.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta7().equals("")){
			radioButtonH.setVisibility(android.view.View.VISIBLE);
			radioButtonH.setText(mDatos.getPregunta7());
		}else {
			radioButtonH.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta8().equals("")){
			radioButtonI.setVisibility(android.view.View.VISIBLE);
			radioButtonI.setText(mDatos.getPregunta8());
		}else {
			radioButtonI.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta9().equals("")){
			radioButtonJ.setVisibility(android.view.View.VISIBLE);
			radioButtonJ.setText(mDatos.getPregunta9());
		}else {
			radioButtonJ.setVisibility(android.view.View.GONE);
		}

		if (!mDatos.getPregunta10().equals("")){
			radioButtonK.setVisibility(android.view.View.VISIBLE);
			radioButtonK.setText(mDatos.getPregunta10());
		}else {
			radioButtonK.setVisibility(android.view.View.GONE);
		}


		//Metodos OnClick que enviara un (SI) o (NO)
		radioButtonB.setOnCheckedChangeListener((buttonView, isChecked) -> {
				Log.d("RESULTADO", "seleccionado egergergergergerg: ");
				MenuTics view = (MenuTics) activity;
				if (isChecked){
					view.setValorSeleccionado1("SI");
				}else {
					view.setValorSeleccionado1("NO");
				}
		});

		radioButtonB.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado2("SI");
			}else {
				view.setValorSeleccionado2("NO");
			}
		});radioButtonC.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado3("SI");
			}else {
				view.setValorSeleccionado3("NO");
			}
		});radioButtonD.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado4("SI");
			}else {
				view.setValorSeleccionado4("NO");
			}
		});radioButtonE.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado5("SI");
			}else {
				view.setValorSeleccionado5("NO");
			}
		});radioButtonF.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado6("SI");
			}else {
				view.setValorSeleccionado6("NO");
			}
		});radioButtonH.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado7("SI");
			}else {
				view.setValorSeleccionado7("NO");
			}
		});radioButtonI.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado8("SI");
			}else {
				view.setValorSeleccionado8("NO");
			}
		});radioButtonJ.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado9("SI");
			}else {
				view.setValorSeleccionado9("NO");
			}
		});radioButtonK.setOnCheckedChangeListener((buttonView, isChecked) -> {
			MenuTics view = (MenuTics) activity;
			if (isChecked){
				view.setValorSeleccionado10("SI");
			}else {
				view.setValorSeleccionado10("NO");
			}
		});

	}

    @SwipeOut
    private void onSwipedOut(){
        Log.d("RESULTADO", "onSwipedOut");
        //mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("RESULTADO", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("RESULTADO", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("RESULTADO", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("RESULTADO", "onSwipeOutState");
    }
}
