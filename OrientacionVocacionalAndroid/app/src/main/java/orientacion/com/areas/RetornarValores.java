package orientacion.com.areas;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetornarValores {

	private int nume = 3, numer2 = 0, selecciondo= 9;
	private int[] listaNumerosFinal = {nume, numer2, selecciondo, 7};
	//private int[] listaNumeros = {1,4,7,8,9,2,15,4,6,9,13};


	private static int numero1, numero11, numero21, numero31, numero41;
	private static int numero2, numero12, numero22, numero32, numero42;
	private static int numero3, numero13, numero23, numero33, numero43;
	private static int numero4, numero14, numero24, numero34, numero44;
	private static int numero5, numero15, numero25, numero35, numero45;
	private static int numero6, numero16, numero26, numero36, numero46;
	private static int numero7, numero17, numero27, numero37, numero47;
	private static int numero8, numero18, numero28, numero38, numero48;
	private static int numero9, numero19, numero29, numero39, numero49;
	private static int numero10, numero20, numero30, numero40, numero50;
    /*String total = "235546547";
   */

    public static int getSumarArea1(String total){
		numero1 = Integer.parseInt(String.valueOf(total.charAt(0))); numero11 = Integer.parseInt(String.valueOf(total.charAt(10))); numero21 = Integer.parseInt(String.valueOf(total.charAt(20))); numero31 = Integer.parseInt(String.valueOf(total.charAt(30))); numero41 = Integer.parseInt(String.valueOf(total.charAt(40)));
		int resultado =  numero1 + numero11 + numero21 + numero31 + numero41;
		Log.d("RESULTADO", "Areas return 1: "+resultado);
        return resultado;
    }

	public static int getSumarArea2(String total){
		numero2 = Integer.parseInt(String.valueOf(total.charAt(1))); numero12 = Integer.parseInt(String.valueOf(total.charAt(11))); numero22 = Integer.parseInt(String.valueOf(total.charAt(21))); numero32 = Integer.parseInt(String.valueOf(total.charAt(31))); numero42 = Integer.parseInt(String.valueOf(total.charAt(41)));
		int resultado =  numero2 + numero12 + numero22 + numero32 + numero42;
		Log.d("RESULTADO", "Areas return 2: "+resultado);
		return resultado;
	}

	public static int getSumarArea3(String total){
		numero3 = Integer.parseInt(String.valueOf(total.charAt(2))); numero13 = Integer.parseInt(String.valueOf(total.charAt(12))); numero23 = Integer.parseInt(String.valueOf(total.charAt(22))); numero33 = Integer.parseInt(String.valueOf(total.charAt(32))); numero43 = Integer.parseInt(String.valueOf(total.charAt(42)));
		int resultado =  numero3 + numero13 + numero23 + numero33 + numero43;
		Log.d("RESULTADO", "Areas return 3: "+resultado);
		return resultado;
	}

	public static int getSumarArea4(String total){
		numero4 = Integer.parseInt(String.valueOf(total.charAt(3))); numero14 = Integer.parseInt(String.valueOf(total.charAt(13))); numero24 = Integer.parseInt(String.valueOf(total.charAt(23))); numero34 = Integer.parseInt(String.valueOf(total.charAt(33))); numero44 = Integer.parseInt(String.valueOf(total.charAt(43)));
		int resultado =  numero4 + numero14 + numero24 + numero34 + numero44;
		Log.d("RESULTADO", "Areas return 4: "+resultado);
		return resultado;
	}

	public static int getSumarArea5(String total){
		numero5 = Integer.parseInt(String.valueOf(total.charAt(4))); numero15 = Integer.parseInt(String.valueOf(total.charAt(14))); numero25 = Integer.parseInt(String.valueOf(total.charAt(24))); numero35 = Integer.parseInt(String.valueOf(total.charAt(34))); numero45 = Integer.parseInt(String.valueOf(total.charAt(44)));
		int resultado =  numero5 + numero15 + numero25 + numero35 + numero45;
		Log.d("RESULTADO", "Areas return 5: "+resultado);
		return resultado;
	}

	public static int getSumarArea6(String total){
		numero6 = Integer.parseInt(String.valueOf(total.charAt(5))); numero16 = Integer.parseInt(String.valueOf(total.charAt(15))); numero26 = Integer.parseInt(String.valueOf(total.charAt(25))); numero36 = Integer.parseInt(String.valueOf(total.charAt(35))); numero46 = Integer.parseInt(String.valueOf(total.charAt(45)));
		int resultado =  numero6 + numero16 + numero26 + numero36 + numero46;
		Log.d("RESULTADO", "Areas return 6: "+resultado);
		return resultado;
	}

	public static int getSumarArea7(String total){
		numero7 = Integer.parseInt(String.valueOf(total.charAt(6))); numero17 = Integer.parseInt(String.valueOf(total.charAt(16))); numero27 = Integer.parseInt(String.valueOf(total.charAt(26))); numero37 = Integer.parseInt(String.valueOf(total.charAt(36))); numero47 = Integer.parseInt(String.valueOf(total.charAt(46)));
		int resultado =  numero7 + numero17 + numero27 + numero37 + numero47;
		Log.d("RESULTADO", "Areas return 7: "+resultado);
		return resultado;
	}

	public static int getSumarArea8(String total){
		numero8 = Integer.parseInt(String.valueOf(total.charAt(7))); numero18 = Integer.parseInt(String.valueOf(total.charAt(17))); numero28 = Integer.parseInt(String.valueOf(total.charAt(27))); numero38 = Integer.parseInt(String.valueOf(total.charAt(37))); numero48 = Integer.parseInt(String.valueOf(total.charAt(47)));
		int resultado =  numero8 + numero18 + numero28 + numero38 + numero48;
		Log.d("RESULTADO", "Areas return 8: "+resultado);
		return resultado;
	}

	public static int getSumarArea9(String total){
		numero9 = Integer.parseInt(String.valueOf(total.charAt(8))); numero19 = Integer.parseInt(String.valueOf(total.charAt(18))); numero29 = Integer.parseInt(String.valueOf(total.charAt(28))); numero39 = Integer.parseInt(String.valueOf(total.charAt(38))); numero49 = Integer.parseInt(String.valueOf(total.charAt(48)));
		int resultado =  numero9 + numero19 + numero29 + numero39 + numero49;
		Log.d("RESULTADO", "Areas return 9: "+resultado);
		return resultado;
	}


	public static int getSumarArea10(String total){
		numero10 = Integer.parseInt(String.valueOf(total.charAt(9))); numero20 = Integer.parseInt(String.valueOf(total.charAt(19))); numero30 = Integer.parseInt(String.valueOf(total.charAt(29))); numero40 = Integer.parseInt(String.valueOf(total.charAt(39))); numero50 = Integer.parseInt(String.valueOf(total.charAt(49)));
		int resultado =  numero10 + numero20 + numero30 + numero40 + numero50;
		Log.d("RESULTADO", "Areas return 10: "+resultado);
		return resultado;
	}



	/*******************  AQUI SUMANOS LOS PUNTOS DE CAPACITACIONES ******************/

	public static int getSumarCapacitacion1(String total){
		numero1 = Integer.parseInt(String.valueOf(total.charAt(0))); numero2 = Integer.parseInt(String.valueOf(total.charAt(1))); numero3 = Integer.parseInt(String.valueOf(total.charAt(2))); numero4 = Integer.parseInt(String.valueOf(total.charAt(3)));
		numero5 = Integer.parseInt(String.valueOf(total.charAt(4))); numero6 = Integer.parseInt(String.valueOf(total.charAt(5))); numero7 = Integer.parseInt(String.valueOf(total.charAt(6))); numero8 = Integer.parseInt(String.valueOf(total.charAt(7)));
		int resultado =  numero1 + numero2 + numero3 + numero4 + numero5 + numero6 + numero7 + numero8;
		Log.d("RESULTADO", "Capacitacion1 return: "+resultado);
		return resultado;
	}

	public static int getSumarCapacitacion2(String total){
		numero9 = Integer.parseInt(String.valueOf(total.charAt(8))); numero10 = Integer.parseInt(String.valueOf(total.charAt(9))); numero11 = Integer.parseInt(String.valueOf(total.charAt(10))); numero12 = Integer.parseInt(String.valueOf(total.charAt(11)));
		numero13 = Integer.parseInt(String.valueOf(total.charAt(12))); numero14 = Integer.parseInt(String.valueOf(total.charAt(13))); numero15 = Integer.parseInt(String.valueOf(total.charAt(14))); numero16 = Integer.parseInt(String.valueOf(total.charAt(15)));
		int resultado =  numero9 + numero10 + numero11 + numero12 + numero13 + numero14 + numero15 + numero16;
		Log.d("RESULTADO", "Capacitacion2 return: "+resultado);
		return resultado;
	}

	public static int getSumarCapacitacion3(String total){
		numero17 = Integer.parseInt(String.valueOf(total.charAt(16))); numero18 = Integer.parseInt(String.valueOf(total.charAt(17))); numero19 = Integer.parseInt(String.valueOf(total.charAt(18))); numero20 = Integer.parseInt(String.valueOf(total.charAt(19)));
		numero21 = Integer.parseInt(String.valueOf(total.charAt(20))); numero22 = Integer.parseInt(String.valueOf(total.charAt(21))); numero23 = Integer.parseInt(String.valueOf(total.charAt(22))); numero24 = Integer.parseInt(String.valueOf(total.charAt(23)));
		int resultado =  numero17 + numero18 + numero19 + numero20 + numero21 + numero22 + numero23 + numero24;
		Log.d("RESULTADO", "Capacitacion3 return: "+resultado);
		return resultado;
	}

	public static int getSumarCapacitacion4(String total){
		numero25 = Integer.parseInt(String.valueOf(total.charAt(24))); numero26 = Integer.parseInt(String.valueOf(total.charAt(25))); numero27 = Integer.parseInt(String.valueOf(total.charAt(26))); numero28 = Integer.parseInt(String.valueOf(total.charAt(27)));
		numero29 = Integer.parseInt(String.valueOf(total.charAt(28))); numero30 = Integer.parseInt(String.valueOf(total.charAt(29))); numero31 = Integer.parseInt(String.valueOf(total.charAt(30))); numero32 = Integer.parseInt(String.valueOf(total.charAt(31)));
		int resultado =  numero25 + numero26 + numero27 + numero28 + numero29 + numero30 + numero31 + numero32;
		Log.d("RESULTADO", "Capacitacion4 return: "+resultado);
		return resultado;
	}




	//

	/*public static int buscarNumero(int[] listaNumeros) {
		for (int x = 1; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > primerNumero) {
				primerNumero = listaNumeros[x];
				iPosicion = x;
			}
		}
		Log.d("RESULTADO", "Posicion1: " + iPosicion + "    Es mayor1: " + primerNumero);
		buscarNumero2(primerNumero);
	}

	private void buscarNumero2(int primerNumero) {
		for (int x = 1; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > segundoNumero) {
				if (listaNumeros[x] != primerNumero) {
					segundoNumero = listaNumeros[x];
					iPosicion = x;
				}
			}
		}
		//segundoNumero = iNumeroMayor;
		Log.d("RESULTADO", "Posicion2: " + iPosicion + "    Es mayor2: " + segundoNumero);
		buscarNumero3(primerNumero, segundoNumero);
	}

	private void buscarNumero3(int primerNumero, int segundoNumero) {
		for (int x = 1; x < listaNumeros.length; x++) {
			if (listaNumeros[x] > tercerNumero) {
				if (listaNumeros[x] != primerNumero) {
					if (listaNumeros[x] != segundoNumero) {
						tercerNumero = listaNumeros[x];
						iPosicion = x;
					}
				}
			}
		}
		//tercerNumero = iNumeroMayor;
		Log.d("RESULTADO", "Posicion3: " + iPosicion + "    Es mayor3: " + tercerNumero);
		Log.d("RESULTADO", "Callcular: "+getSumarArea1(""));
		/*Log.i("RESULTADO", "RESULTADO P1: "+  "CI" );
		Log.i("RESULTADO", "POSICION 1: "+  total.charAt(0) );
		Log.i("RESULTADO", "POSICION 2: "+  total.charAt(10) );
		Log.i("RESULTADO", "POSICION 3: "+  total.charAt(20) );
		Log.i("RESULTADO", "POSICION 4: "+  total.charAt(30) );
		Log.i("RESULTADO", "POSICION 5: "+  total.charAt(40) );*/
	/*}*/

	private void ordenarLista() {
		List theList = new ArrayList<>();
		theList.add("xxx");
		theList.add("AB");
		theList.add("Abc");
		Log.d("ORDENACION", "Lista original: -----");
		showList(theList);
		Collections.sort(theList);
		Log.d("ORDENACION", "Lista ordenada: -----");
		showList(theList);
	}

	private static void showList(List theList) {
		String data = "";
		int size = theList.size();
		for(int i=0; i<size; i++){
			data += String.valueOf(theList.get(i));
		}
		Log.d("ORDENACION", "Resultado: " +data);
	}
}
