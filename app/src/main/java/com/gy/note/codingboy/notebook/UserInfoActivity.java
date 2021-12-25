package com.gy.note.codingboy.notebook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.note.R;
import com.gy.note.codingboy.notebook.bean.UserInfo;

public class UserInfoActivity extends Activity {

    ImageView iv_ack;
    TextView tv_user_name;
    TextView tv_pwd;
    TextView tv_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_user_info);

        tv_user_name = findViewById(R.id.user_name);
        tv_pwd = findViewById(R.id.user_pwd);
        tv_phone = findViewById(R.id.user_phone);

        iv_ack = findViewById(R.id.iv_back);
        iv_ack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        UserInfo userInfo = Global.userInfo;

        tv_user_name.setText(userInfo.userName);
        tv_pwd.setText(userInfo.pwd);
        tv_phone.setText(userInfo.userPhone.isEmpty() ? "18000000000":userInfo.userPhone);
    }
}
