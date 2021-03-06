package orientacion.com.areas;

import android.app.Activity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import orientacion.com.api.response.PreguntasResponse;

@Layout(R.layout.card_view)
public class CardViewAreas {

    @View(R.id.preguntaNameTxt)
    private TextView preguntaNameTxt;

    @View(R.id.radioGroup)
    private RadioGroup radioGroup;

    @View(R.id.radioButtonA)
    private RadioButton radioButtonA;

    @View(R.id.radioButtonB)
    private RadioButton radioButtonB;

    @View(R.id.radioButtonC)
    private RadioButton radioButtonC;

    @View(R.id.radioButtonD)
    private RadioButton radioButtonD;

    @View(R.id.radioButtonE)
    private RadioButton radioButtonE;

    private int selec = 0;

    private PreguntasResponse mDatos;
    private Activity activity;
    private SwipePlaceHolderView mSwipeView;

    public CardViewAreas(Activity activity, PreguntasResponse datos, SwipePlaceHolderView swipeView) {
        this.activity =  activity;
        mDatos = datos;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){
        Log.d("RESULTADO", "Pintamos los resultados");
        preguntaNameTxt.setText(mDatos.getPregunta());
        radioButtonA.setText(mDatos.getOpcion1());
        radioButtonB.setText(mDatos.getOpcion2());
        radioButtonC.setText(mDatos.getOpcion3());
        radioButtonD.setText(mDatos.getOpcion4());
        radioButtonE.setText(mDatos.getOpcion5());

        //Aqui vamos a seleccionar una y la respuesta lo enviamos a la clase MenuAreas.class
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
				MenuAreas view = (MenuAreas) activity;
				switch(checkedId){
					case R.id.radioButtonA:
						selec = 4;
						view.setValorSeleccionado(selec);
						break;
					case R.id.radioButtonB:
						selec = 3;
						view.setValorSeleccionado(selec);
						break;
					case R.id.radioButtonC:
						selec = 2;
						view.setValorSeleccionado(selec);
						break;
					case R.id.radioButtonD:
						selec = 1;
						view.setValorSeleccionado(selec);
						break;
					case R.id.radioButtonE:
						selec = 0;
						view.setValorSeleccionado(selec);
						break;
				}
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
