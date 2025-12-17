package com.end.finalproject.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.end.finalproject.MainActivity;
import com.end.finalproject.R;
import com.end.finalproject.management.DanhSachKhachHangActivity;
import com.google.firebase.auth.FirebaseAuth;

public class EmployeeHomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private CardView cardAccountDetail;
    private CardView cardProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employee);

        tvWelcome = findViewById(R.id.tv_welcome);
        cardAccountDetail = findViewById(R.id.cardKhachHang);

        ImageView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(EmployeeHomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Lấy dữ liệu từ Intent
        Intent incomingIntent = getIntent();
        String name = incomingIntent.getStringExtra("name");
        String email = incomingIntent.getStringExtra("email");
        String phone = incomingIntent.getStringExtra("phoneNumber");
        String userId = incomingIntent.getStringExtra("key");
        String password = incomingIntent.getStringExtra("password");
        String role = incomingIntent.getStringExtra("role");


        // Hiển thị thông tin
        tvWelcome.setText("Chào mừng " + name + "!");

        // Xử lý sự kiện click vào card để mở danh sách khách hàng
        cardAccountDetail.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeHomeActivity.this, DanhSachKhachHangActivity.class);
            startActivity(intent);
        });

        cardProfile = findViewById(R.id.cardTrangCaNhan); // ID cần thêm vào XML layout
        cardProfile.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeHomeActivity.this, ProfileActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("phoneNumber", phone);
            intent.putExtra("userId", userId);
            intent.putExtra("password", password);
            intent.putExtra("role", role);
            startActivity(intent);
        });
    }
}
