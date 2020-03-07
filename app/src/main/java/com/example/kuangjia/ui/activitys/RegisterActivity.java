package com.example.kuangjia.ui.activitys;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseActivity;
import com.example.kuangjia.interfaces.shop.RegisterConstract;
import com.example.kuangjia.models.bean.UserBean;
import com.example.kuangjia.models.bean.VerifyBean;
import com.example.kuangjia.persenter.shop.RegisterPersenter;


import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterConstract.Persenter> implements RegisterConstract.View {
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_pw1)
    EditText editPw1;
    @BindView(R.id.edit_pw2)
    EditText editPw2;
    @BindView(R.id.edit_verify)
    EditText editVerify;
    @BindView(R.id.img_verify)
    ImageView imgVerify;
    @BindView(R.id.layout_list)
    LinearLayout layoutList;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getVerify();
    }

    @Override
    protected RegisterConstract.Persenter createPersenter() {
        return new RegisterPersenter();
    }

    @Override
    public void getVerifyReturn(VerifyBean result) {
        updateVerify(result);
    }

    @Override
    public void getUserReturn(UserBean userBean) {
        String errmsg = userBean.getErrmsg();
        Toast.makeText(this,errmsg,Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.img_verify, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_verify:
                persenter.getVerify();
                break;
            case R.id.btn_register:
                String username = editUsername.getText().toString();
                String pw1 = editPw1.getText().toString();
                String ps2 = editPw2.getText().toString();
                String verify = editVerify.getText().toString();
                if (pw1.equals(ps2)){
                    persenter.getUser(username,pw1,verify);
                }else {
                    Toast.makeText(this,"两次输入的密码不同，请重新输入",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void updateVerify(VerifyBean bean){
        Glide.with(this).load(bean.getData().getImg()).into(imgVerify);
    }
}
