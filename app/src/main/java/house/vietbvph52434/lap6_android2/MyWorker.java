package house.vietbvph52434.lap6_android2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Tạo handler
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Sau 1 giây sẽ Toast lên khi thiết bị được sạc
                Toast.makeText(getApplicationContext(), "Thiết bị đang được sạc", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
        return Result.success();
    }
}

