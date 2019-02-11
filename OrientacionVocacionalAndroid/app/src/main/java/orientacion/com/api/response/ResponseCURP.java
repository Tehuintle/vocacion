package orientacion.com.api.response;

import com.google.gson.annotations.SerializedName;

public class ResponseCURP extends ResponseBase {

	@SerializedName("IdAlumno")
	public String idAlumno;

	@SerializedName("Nombre")
	public String nombre;

	@SerializedName("ApellidoP")
	public String apellidoP;

	@SerializedName("ApellidoM")
	public String apellidoM;

	@SerializedName("Grupo")
	public String grupo;

	@SerializedName("Genero")
	public String genero;

	@SerializedName("Curp")
	public String curp;

	@SerializedName("FechaRegistro")
	public String fechaRegistro;

	public String getIdAlumno() {
		return idAlumno;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getGenero() {
		return genero;
	}

	public String getCurp() {
		return curp;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}
}
