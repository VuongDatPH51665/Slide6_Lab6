package house.vietbvph52434.lap6_android2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class BackgroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Chuyển hướng đến trình duyệt sau 5 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.youtube.com/?app=desktop&hl=vi"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
                // Dừng service sau khi mở trình duyệt
                stopSelf();
            }
        }, 2000); // 5000 milliseconds = 5 seconds

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
