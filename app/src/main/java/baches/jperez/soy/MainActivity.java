package baches.jperez.soy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import baches.jperez.soy.utils.SoyBacheroUtils;


public class MainActivity extends ActionBarActivity {

    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000014")));
        Button boton = (Button) findViewById(R.id.btnIniciar);

        final EditText User = (EditText) findViewById(R.id.txtusr);



        final EditText Password = (EditText) findViewById(R.id.txtpwd);


        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {





                TareaWSInsertar tarea = new TareaWSInsertar();
                tarea.execute(

                User.getText().toString(),
                        Password.getText().toString());
            }




        });


    }

    private class TareaWSInsertar extends AsyncTask<String, Void, Integer> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPostExecute(Integer result) {
            mProgressDialog.dismiss();
            if (result == 404) {
                Toast.makeText(getBaseContext(), "error de red", Toast.LENGTH_SHORT).show();
            } else if (result == 400) {

                Toast.makeText(getBaseContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
            else if  (!verificaConexion(MainActivity.this)) {
                Toast.makeText(getBaseContext(),
                        "Comprueba tu conexi�n a Internet ", Toast.LENGTH_SHORT)
                        .show();
            }


        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(MainActivity.this, "Loading...", "Data is Loading...");

        }


        protected Integer doInBackground(String... params) {
            int responseCode = 0;
            //Creamos un cliente HTTP
            HttpClient Httpclient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://192.168.1.93:8001/token-auth/");
            //Le decimos que acepte datos JSON
            post.setHeader("content-type", "application/json");


            try {
                //Construimos el objeto cliente en formato JSON
                JSONObject dato = new JSONObject();

                dato.put("username", params[0]);
                dato.put("password", params[1]);

                StringEntity entity = new StringEntity(dato.toString());
                post.setEntity(entity);
                HttpResponse response;
                response = Httpclient.execute(post);

                responseCode = response.getStatusLine().getStatusCode();


                //Creamos una variable para guardar el estado del HTTP
                int status = response.getStatusLine().getStatusCode();
                //imprimimos el estado en la consola
                Log.d("Http code:", String.valueOf(status));

                //Creamos una variable para guardar la respuesta del Servidor
                String respStr = EntityUtils.toString(response.getEntity());

                Log.d("Http Post Response:", respStr.toString());

                Gson tokensecret = new GsonBuilder().create();

                token token =tokensecret.fromJson(respStr, token.class);
                Log.d("Http Post Response:", token.getToken());




                if (status == 200) {

                    Intent Login = new Intent(MainActivity.this,Home.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("key", token.getToken());
                    Login.putExtras(mBundle);
                    startActivity(Login);
                    finish();

                }

            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);

            }


            return responseCode;
        }


    }
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No s�lo wifi, tambi�n GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle deber�a no ser tan �apa
        for (int i = 0; i < 2; i++) {
            // �Tenemos conexi�n? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
