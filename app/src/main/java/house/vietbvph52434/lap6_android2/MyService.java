package house.vietbvph52434.lap6_android2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // viết các câu lệnh tại đây
        String name = intent.getStringExtra("data");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}