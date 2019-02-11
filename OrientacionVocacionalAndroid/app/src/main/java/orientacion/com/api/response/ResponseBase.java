package orientacion.com.api.response;

import com.google.gson.annotations.SerializedName;

public class ResponseBase {

	@SerializedName("Estatus")
	public boolean estatus = false;
	@SerializedName("Mensaje")
	public String mensaje;


	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
