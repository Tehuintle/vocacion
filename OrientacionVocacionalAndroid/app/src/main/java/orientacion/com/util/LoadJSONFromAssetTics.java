package orientacion.com.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import orientacion.com.api.response.TicsResponse;

public class LoadJSONFromAssetTics {
    private static final String TAG = "LoadJSONFromAsset";

    public static List<TicsResponse> loadContent(Context context, String jsonFileName){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, jsonFileName));
            List<TicsResponse> datosList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
				TicsResponse datos = gson.fromJson(array.getString(i), TicsResponse.class);
                datosList.add(datos);
            }
            return datosList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
