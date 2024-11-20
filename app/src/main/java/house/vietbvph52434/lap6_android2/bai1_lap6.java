package house.vietbvph52434.lap6_android2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import androidx.appcompat.app.AppCompatActivity;



public class bai1_lap6 extends AppCompatActivity {

    // navigation drawer thì chia làm 3 phần
    // 1 - content - là nơi hiển thị các fragment nằm trong menu
    // 2 - Menu : là cửa sổ trượt ngang từ trái hoặc phải qua
    // 2 phần : Header  : chứa logo và thông tin
    //          Menu : danh sách tùy chọn
    // 1 có công việc sửa menu cần biết
    // sửa thông tin giao diện header ở đâu ?  trong file nav_header_main.xml
    // thêm, xóa menu tùy chọn ở đâu, làm như thế nào ?
    // - thêm, xóa menu trong file menu/activity_main_drawer.xml

    // tùy chỉnh Fragment xuất hiện trong Menu


    // slide 6 :
    // Notification trong Android
    //
    // Service trong Android
    // service là 1 dich vụ  chạy ngầm  -- 1 activity nhưng không có giao diện
    // 2 loại service bound và unbound  ràng buộc và không dàng buộc
    // startservice và bindservice là 2 câu lệnh chạy 1 service tương với không dàng buộc và ràng buộc
    // service cũng giống như activity , phải được  khai báo trong mainfest thì mới được phép sử dụng
    //service dùng cho các task vụ chạy ngầm như : hẹn giờ , tải file , xử lý dữ liệu... phát nhạc mp3
    // 2 loai  chạy ngầm  foreground và background
    // Work Manager

    private static final String CHANNEL_ID = "channelId";
    private static final int NOTIFICATION_ID = 1234;
    private static final int PERMISSION_REQUEST_CODE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);




        findViewById(R.id.btnNofify).setOnClickListener(v -> {
            // < android 6 thì chỉ cần thêm xin quyền trong manifest ...
            // Xin quyền gửi thông báo cho android 6 - 8
            // Kiểm tra quyền POST_NOTIFICATIONS
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Yêu cầu quyền nếu chưa được cấp
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_REQUEST_CODE);
            } else {
                // Hiển thị thông báo nếu đã có quyền
                showNotification();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, hiển thị thông báo
                showNotification();
            } else {
                // Quyền bị từ chối, xử lý trường hợp này (ví dụ: hiển thị thông báo lỗi)
            }
        }
    }
    @SuppressLint("MissingPermission")
    private void showNotification() {
        // Tạo kênh thông báo cho Android 8.0 trở lên
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // từ android 8 trở lên thì phải bổ sung thêm phần tạo Chanel ID thì mới hiển thị được
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Tên kênh", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Bitmap logo  = BitmapFactory.decodeResource(getResources(),R.mipmap.caphereal);
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
//                .bigText("Đây là một đoạn văn bản dài hơn có thể được hiển thị trong một thông báo mở rộng. " +
//                        "Bạn có thể thêm nhiều văn bản hơn ở đây để xem cách nó được hiển thị khi thông báo được mở rộng.");
        // Tạo thông báo
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(logo);
                // Loại bỏ largeIcon khi mở rộng thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Chào mừng đến với fptpolytechnic")
                .setContentText("androi2")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(logo)
                        .bigLargeIcon((Bitmap) null)
                )
                .setLargeIcon(logo)
//                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Hiển thị thông báo
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }




}