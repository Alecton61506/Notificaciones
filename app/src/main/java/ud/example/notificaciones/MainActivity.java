package ud.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noti01(View v){
      String mess=getResources().getString(R.string.mensaje01); //traigo del string.xml lo que dice en mensaje01
        Toast toast1= Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_LONG);  // Objeto toast - mensaje que va a salir en pantalla
        toast1.show();
    }

    public void noti02(View v){
        String mess=getResources().getString(R.string.mensaje01); //traigo del string.xml lo que dice en mensaje01
        Toast toast1= Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_LONG);  // Objeto toast - mensaje que va a salir en pantalla
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT, 10, 10);
        toast1.show();
    }

    public void noti03(View v){

        Toast toast1= new Toast(getApplicationContext());  // Objeto toast - mensaje que va a salir en pantalla
        LayoutInflater inflater = getLayoutInflater();
        View layOut = inflater.inflate(R.layout.layout_toast,(ViewGroup) findViewById(R.id.layoutToast));
        TextView txtMsg = (TextView) layOut.findViewById(R.id.mensajeLb1);
        //txtMsg.setText("Mensaje con diseño por nosotros");
        String mcn = getResources().getString(R.string.mensajeP); //traigo del string.xml lo que dice en MensajeP
        txtMsg.setText(mcn);
        toast1.setView(layOut);
        toast1.show();
    }

    public void noti04(View v){
        String ns = Context.NOTIFICATION_SERVICE;
        String CHANNEL_ID = "ud.com.ANDROID";
        NotificationManager notManager = (NotificationManager) getSystemService(ns);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1){
            CharSequence name = "ANDROID CHANNEL";
            String descripcion = "Canal de notificacion de Android para la UD ";
            int Importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,Importance);
            channel.setDescription(descripcion);
            notManager.createNotificationChannel(channel);
        }

        int icono = android.R.drawable.stat_sys_warning;
        CharSequence textEstado = "!Atencion!";
        CharSequence titulo = "Mensaje de Alerta";
        CharSequence descripcion = "Ejemplo de Notificación";
        long hora= System.currentTimeMillis();

        Context contexto = getApplicationContext();
        Intent noIntent =new Intent(contexto,MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, noIntent, 0);


        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                new  NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                    .setSmallIcon(icono)
                    .setLargeIcon((((BitmapDrawable)getResources().getDrawable(R.drawable.info)).getBitmap()))
                    .setContentTitle(titulo)
                    .setContentText(descripcion)
                    .setContentInfo(textEstado)
                    .setWhen(hora)
                    .setContentIntent(contIntent)
                    .setAutoCancel(true)
                    .setColor(getResources().getColor(R.color.colorPrimaryDark))
                .setVibrate(new long[]{100,250, 100, 500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        notManager.notify(ID_MEN_BARRA_NOTIF, mBuilder.build());
    }
    private static final int ID_MEN_BARRA_NOTIF =1;

    public void noti05(View v){
        String mess=getResources().getString(R.string.mensaje01); //traigo del string.xml lo que dice en mensaje01
        Snackbar.make(v, mess,Snackbar.LENGTH_LONG)
                .setAction("Aqui", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast1= Toast.makeText(getApplicationContext(),"Hola bb que mas pues",Toast.LENGTH_LONG);  // Objeto toast - mensaje que va a salir en pantalla
                        toast1.show();
                        Log.i("Ojo Aqui Aca", "Aqui llegue");
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.design_default_color_primary))
                .show();

    }
}