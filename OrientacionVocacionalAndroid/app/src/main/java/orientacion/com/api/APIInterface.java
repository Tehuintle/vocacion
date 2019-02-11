package orientacion.com.api;

import orientacion.com.api.response.ResponseBase;
import orientacion.com.api.response.ResponseCURP;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/conecctionIP")
	Call<ResponseBase> conectarRemoto(@Field("IP") String name);

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/logInCURP")
	Call<ResponseCURP> logInCurp(@Field("Curp") String curp);

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarAreas")
	Call<ResponseBase> registrarAreas(@Field("Curp") String curp, @Field("Vocacion1") String vocacion1, @Field("Vocacion2") String vocacion2,@Field("Vocacion3") String vocacion3 );

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarCapacitacion")
	Call<ResponseBase> registrarCapacitacion(@Field("Curp") String curp, @Field("Capacitacion1") String vocacion1, @Field("Capacitacion2") String vocacion2,@Field("Capacitacion3") String vocacion3 );


	/*************** Registro de TICs ***************/
	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarTerminales")
	Call<ResponseBase> registrarTicsTerminales(@Field("IdAlumno") String id,
									 @Field("Tics1") String tics1, @Field("Tics2") String tics2, @Field("Tics3") String tics3,
									 @Field("Tics4") String tics4, @Field("Tics5") String tics5, @Field("Tics6") String tics6,
									 @Field("Tics7") String tics7, @Field("Tics8") String tics8, @Field("Tics9") String tics9,
									 @Field("Tics10") String tics10);
	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarRedes")
	Call<ResponseBase> registrarTicsRedes(@Field("IdAlumno") String id,
									  @Field("Tics1") String tics1, @Field("Tics2") String tics2, @Field("Tics3") String tics3,
									  @Field("Tics4") String tics4, @Field("Tics5") String tics5, @Field("Tics6") String tics6);

	@FormUrlEncoded
	@POST("vocacion/api/v1/user/registrarAlmacenamiento")
	Call<ResponseBase> registrarTicsAlmacenamiento(@Field("IdAlumno") String id,
									  @Field("Tics1") String tics1, @Field("Tics2") String tics2);

}
