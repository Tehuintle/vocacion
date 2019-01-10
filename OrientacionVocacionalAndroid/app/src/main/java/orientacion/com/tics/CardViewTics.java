package orientacion.com.tics;

import android.app.Activity;
import android.util.Log;
import android.widget.RadioButton;
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
    private RadioButton radioButtonA;

    @View(R.id.radioButtonB)
    private RadioButton radioButtonB;

    @View(R.id.radioButtonC)
    private RadioButton radioButtonC;

    @View(R.id.radioButtonD)
    private RadioButton radioButtonD;

    @View(R.id.radioButtonE)
    private RadioButton radioButtonE;

	@View(R.id.radioButtonF)
	private RadioButton radioButtonF;

	@View(R.id.radioButtonH)
	private RadioButton radioButtonH;

	@View(R.id.radioButtonI)
	private RadioButton radioButtonI;

	@View(R.id.radioButtonJ)
	private RadioButton radioButtonJ;

	@View(R.id.radioButtonK)
	private RadioButton radioButtonK;

    private int selec = 0;

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

        if (!mDatos.getPregunta3().isEmpty()){
			radioButtonC.setVisibility(android.view.View.VISIBLE);
			radioButtonC.setText(mDatos.getPregunta3());
		}else
			radioButtonC.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta4().isEmpty()){
			radioButtonD.setVisibility(android.view.View.VISIBLE);
			radioButtonD.setText(mDatos.getPregunta4());
		}else
			radioButtonD.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta5().isEmpty()){
			radioButtonE.setVisibility(android.view.View.VISIBLE);
			radioButtonE.setText(mDatos.getPregunta5());
		}else
			radioButtonE.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta6().isEmpty()){
			radioButtonF.setVisibility(android.view.View.VISIBLE);
			radioButtonF.setText(mDatos.getPregunta6());
		}else
			radioButtonF.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta7().isEmpty()){
			radioButtonH.setVisibility(android.view.View.VISIBLE);
			radioButtonH.setText(mDatos.getPregunta7());
		}else
			radioButtonH.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta8().isEmpty()){
			radioButtonI.setVisibility(android.view.View.VISIBLE);
			radioButtonI.setText(mDatos.getPregunta8());
		}else
			radioButtonI.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta9().isEmpty()){
			radioButtonJ.setVisibility(android.view.View.VISIBLE);
			radioButtonJ.setText(mDatos.getPregunta9());
		}else
			radioButtonJ.setVisibility(android.view.View.GONE);

		if (!mDatos.getPregunta10().isEmpty()){
			radioButtonK.setVisibility(android.view.View.VISIBLE);
			radioButtonK.setText(mDatos.getPregunta10());
		}else
			radioButtonK.setVisibility(android.view.View.GONE);


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
