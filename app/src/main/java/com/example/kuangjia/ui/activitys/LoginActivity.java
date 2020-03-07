package com.example.kuangjia.ui.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.shop.LoginConstract;
import com.example.kuangjia.models.bean.UserBean;
import com.example.kuangjia.persenter.shop.LoginPersenter;
import com.example.kuangjia.utils.SpUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginConstract.Persenter> implements LoginConstract.View {
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.regist)
    TextView regist;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected LoginConstract.Persenter createPersenter() {
        return new LoginPersenter();
    }

    @Override
    public void loginReturn(UserBean result) {
        //登录成功以后存入sp
        SpUtils.getInstance().setValue("token",result.getData().getToken());
        String errmsg = result.getErrmsg();
        Toast.makeText(this,errmsg,Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String nickname = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        persenter.login(nickname,password);
    }
}
