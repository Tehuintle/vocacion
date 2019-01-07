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


}
