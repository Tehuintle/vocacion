package orientacion.com.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreguntasResponse {

    @SerializedName("pregunta")
    @Expose
    private String pregunta;

    @SerializedName("opcion1")
    @Expose
    private String opcion1;

    @SerializedName("opcion2")
    @Expose
    private String opcion2;

    @SerializedName("opcion3")
    @Expose
    private String opcion3;

    @SerializedName("opcion4")
    @Expose
    private String opcion4;

    @SerializedName("opcion5")
    @Expose
    private String opcion5;

    @SerializedName("resultado")
    @Expose
    private Integer resultado;



    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getOpcion5() {
        return opcion5;
    }

    public void setOpcion5(String opcion5) {
        this.opcion5 = opcion5;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

}
