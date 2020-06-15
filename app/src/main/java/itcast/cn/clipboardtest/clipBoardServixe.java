package itcast.cn.clipboardtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class clipBoardServixe extends Service {
    public clipBoardServixe() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "监听剪贴板服务已启动", Toast.LENGTH_SHORT).show();



    }

    private void initNotification() {

        Intent toMain = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,toMain,0);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null,clipboardManager.getText());
        clipboardManager.setPrimaryClip(clipData);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("监听剪贴板服务")
                .setContentText("正在运行"+"\n"+"剪贴板内容为"+clipboardManager.getText())
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initNotification();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "服务关闭", Toast.LENGTH_SHORT).show();
    }
}
