package orientacion.com.api;

import orientacion.com.api.response.ResponseBase;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/connectionIP")
	Call<ResponseBase> conectarRemoto(@Field("IP") String name);

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarAreas")
	Call<ResponseBase> registrarAreas(@Field("Curp") String curp, @Field("Vocacion1") String vocacion1, @Field("Vocacion2") String vocacion2,@Field("Vocacion3") String vocacion3 );

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarCapacitacion")
	Call<ResponseBase> registrarCapacitacion(@Field("Curp") String curp, @Field("Capacitacion1") String vocacion1, @Field("Capacitacion2") String vocacion2,@Field("Capacitacion3") String vocacion3 );

}
