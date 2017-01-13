package com.sziit.easypay.activity;
/* * 文件名：
* * 描述：
* * 作者：
* * 时间：
* * 版权：
* */

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sziit.easypay.R;
import com.sziit.easypay.common.BaseActivity;
import com.sziit.easypay.http.RestAPI;
import com.sziit.easypay.pub.Pub;
import com.sziit.easypay.utils.Charset;
import com.sziit.easypay.utils.ImportDBUtil;
import com.sziit.easypay.utils.NetworkDetector;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //view created by layoutcreator by itself


    private static final int REQUEST_WRITE_PERMISSION = 1;
    EventHandler eh;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private EditText mEdtTxtMainRegisteredPhoneNumber;
    private Button mBtnMainGetVerificationCode;
    private EditText mEdtTxtMainInputVerificationCode;
    private EditText mEdtTxtMainRefisteredUserPass;
    private EditText mEdtTxtMainAccountName;
    private EditText mEdtTxtMainCardNumbers;
    private Spinner mSpnMainBankName;
    private Spinner mSpnMainBankProvince;
    private Spinner mBankCity;
    private Spinner mBranchBank;
    private EditText mEdtTxtMainBranchBankEdtext;
    private Spinner mSpnMainProvinceSpinner;
    private Spinner mSpnMainCitySpinner;
    private EditText mEdtTxtAddress;
    private Button mBtnMainRegisteredConfirmSubmit;
    //by myself
    private LinearLayout mLlMainAgain;
    private TextView mTvMainSeconds;
    private Spinner mSpnMainRegionr;
    private String sFileDir = Environment.getExternalStorageDirectory() + "/" + Pub.EASY_SETTLEMENT_DIRECTORY;
    private String sFilePath = Environment.getExternalStorageDirectory() + "/" + Pub.EASY_SETTLEMENT_PATH;
    private SQLiteDatabase mSQLiteDatabase;
    private ImportDBUtil mImportDBUtil;
    private String strClassName = "ImportDBUtil";
    private ProgressDialog progressDialog;
    private String userName, accountName, bankName, bankProvince, bankCity, bankBranchName, phoneNumberReg, province, city, district, store, password, bankCardId;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    @Override
    public void initView() {
        mEdtTxtMainRegisteredPhoneNumber = (EditText) findViewById(R.id.edtTxt_main_registered_phone_number);
        mEdtTxtMainRegisteredPhoneNumber.setOnClickListener(this);
        mBtnMainGetVerificationCode = (Button) findViewById(R.id.btn_main_get_verification_code);
        mBtnMainGetVerificationCode.setOnClickListener(this);
        mEdtTxtMainInputVerificationCode = (EditText) findViewById(R.id.edtTxt_main_input_verification_code);
        mEdtTxtMainInputVerificationCode.setOnClickListener(this);
        mEdtTxtMainRefisteredUserPass = (EditText) findViewById(R.id.edtTxt_main_refistered_user_pass);
        mEdtTxtMainRefisteredUserPass.setOnClickListener(this);
        mEdtTxtMainAccountName = (EditText) findViewById(R.id.edtTxt_main_account_name);
        mEdtTxtMainAccountName.setOnClickListener(this);
        mEdtTxtMainCardNumbers = (EditText) findViewById(R.id.edtTxt_main_card_numbers);
        mEdtTxtMainCardNumbers.setOnClickListener(this);
        mSpnMainBankName = (Spinner) findViewById(R.id.spn_main_bank_name);

        mSpnMainBankProvince = (Spinner) findViewById(R.id.spn_main_bank_province);

        mBankCity = (Spinner) findViewById(R.id.bank_city);
        // mBankCity.setOnClickListener(this);
        mBranchBank = (Spinner) findViewById(R.id.branch_bank);
        // mBranchBank.setOnClickListener(this);
        mEdtTxtMainBranchBankEdtext = (EditText) findViewById(R.id.edtTxt_main_branch_bank_edtext);
        mEdtTxtMainBranchBankEdtext.setOnClickListener(this);
        mSpnMainProvinceSpinner = (Spinner) findViewById(R.id.spn_main_province_spinner);

        mSpnMainProvinceSpinner.setOnItemSelectedListener(this);//

        mSpnMainCitySpinner = (Spinner) findViewById(R.id.spn_main_city_spinner);


        mEdtTxtAddress = (EditText) findViewById(R.id.edtTxt_address);
        mEdtTxtAddress.setOnClickListener(this);
        mBtnMainRegisteredConfirmSubmit = (Button) findViewById(R.id.btn_main_registered_confirm_submit);
        mBtnMainRegisteredConfirmSubmit.setOnClickListener(this);
        //
        //
        //by myself
        mLlMainAgain = (LinearLayout) findViewById(R.id.ll_main_again_layout);
        mTvMainSeconds = (TextView) findViewById(R.id.tv_main_seconds);
        mSpnMainCitySpinner = (Spinner) findViewById(R.id.spn_main_city_spinner);
        mSpnMainRegionr = (Spinner) findViewById(R.id.spn_main_regionr);


    }

    @Override
    public void init() {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        initSMSSdk();
        mImportDBUtil = new ImportDBUtil();
        requestPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_get_verification_code:
                getVerificationDel();
                break;
            case R.id.btn_main_registered_confirm_submit:

                break;
        }
    }

    private void submit() {
        // validate
        String number = mEdtTxtMainRegisteredPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "number不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = mEdtTxtMainInputVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "code不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = mEdtTxtMainRefisteredUserPass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "pass不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = mEdtTxtMainAccountName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String numbers = mEdtTxtMainCardNumbers.getText().toString().trim();
        if (TextUtils.isEmpty(numbers)) {
            Toast.makeText(this, "numbers不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String edtext = mEdtTxtMainBranchBankEdtext.getText().toString().trim();
        if (TextUtils.isEmpty(edtext)) {
            Toast.makeText(this, "edtext不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = mEdtTxtAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "address不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    /* * 函数名：
    * * 功能：获取权限
    * * 参数含义：
    * * 返回值：
    * */
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            mSQLiteDatabase = mImportDBUtil.openDatabase(this, sFilePath, sFileDir);
            initData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mSQLiteDatabase = mImportDBUtil.openDatabase(this, sFilePath, sFileDir);
                    initData();
                } else {
                }
                break;
            default:
                break;
        }
    }

    private void initData() {
        //读取省份
        List<String> mProvinceList = new ArrayList<>();
        Cursor mProvinceCursor = mSQLiteDatabase.rawQuery("select * from e_province", null);
        while (mProvinceCursor.moveToNext()) {
            String mProvinceName = mProvinceCursor.getString(mProvinceCursor.getColumnIndex("name"));
            mProvinceList.add(mProvinceName);
        }
        mProvinceCursor.close();
        ArrayAdapter mProvinceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mProvinceList);
        mSpnMainProvinceSpinner.setAdapter(mProvinceAdapter);
        mProvinceAdapter.setDropDownViewResource(R.layout.main_spn_view);


    }


    //Province的子项被点击
    private void spnProvinceItemClick(int position) {
        int index = position + 1;
        Cursor mCityCursor = mSQLiteDatabase.rawQuery("select * from e_city where province_id = ?", new String[]{index + ""});
        List<String> mCityList = new ArrayList<String>();
        while (mCityCursor.moveToNext()) {
            String mCityName = mCityCursor.getString(mCityCursor.getColumnIndex("name"));
            mCityList.add(mCityName);
        }
        ArrayAdapter mCityAdapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, mCityList);
        mSpnMainCitySpinner.setAdapter(mCityAdapter);
        mCityAdapter.setDropDownViewResource(R.layout.main_spn_view);
        mSpnMainCitySpinner.setOnItemSelectedListener(this);


    }

    //City子项被点击
    private void spnCityItemClick(int position) {
        int index = position + 1;
        String mCityName = mSpnMainCitySpinner.getSelectedItem().toString();
        Cursor mCityCursor = mSQLiteDatabase.rawQuery("select * from e_city where name=?", new String[]{mCityName});
        String strCityId = null;
        if (mCityCursor.moveToFirst()) {
            do {
                index = mCityCursor.getColumnIndex("city_id");
                strCityId = mCityCursor.getString(index);
            } while (mCityCursor.moveToNext());
        }
        if (!mCityCursor.isClosed()) {
            mCityCursor.close();
        }

        Cursor mAreaCursor = mSQLiteDatabase.rawQuery("select * from e_district where city_id = ?", new String[]{strCityId});
        List<String> mAreaList = new ArrayList<String>();
        while (mAreaCursor.moveToNext()) {
            String strAreaName = mAreaCursor.getString(mAreaCursor.getColumnIndex("name"));
            mAreaList.add(strAreaName);
        }
        ArrayAdapter mAreaAdapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, mAreaList);
        mSpnMainRegionr.setAdapter(mAreaAdapter);
        mAreaAdapter.setDropDownViewResource(R.layout.main_spn_view);
        mSpnMainRegionr.setOnItemSelectedListener(this);
    }

    //Region子项被点击
    private void spnRegionItemClick(View view, int position) {
        TextView tv = (TextView) view;
        tv.setTextColor(getResources().getColor(R.color.back));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spn_main_province_spinner:
                spnProvinceItemClick(position);
                break;
            case R.id.spn_main_city_spinner:
                spnCityItemClick(position);
                break;
            case R.id.spn_main_regionr:
                //spnRegionItemClick(view,position);
                break;
            default:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /* * 函数名：getVerificationDel
* * 功能：用户点击获取验证码后的处理
* * 参数含义：
* * 返回值：
* */
    private void getVerificationDel() {
        boolean bnetworkState = NetworkDetector.detect(this);
        if (bnetworkState) {
            String strPhoneNumber = mEdtTxtMainRegisteredPhoneNumber.getText().toString().trim();
            if (strPhoneNumber != null && strPhoneNumber.length() > 0) {
                SMSSDK.getVerificationCode("86", strPhoneNumber);
                mBtnMainGetVerificationCode.setClickable(false);
                mBtnMainGetVerificationCode.setBackgroundColor(this.getResources().getColor(R.color.smssdk_gray));
                mLlMainAgain.setVisibility(View.VISIBLE);
                @SuppressWarnings("unused")
                CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
                    public void onFinish() {
                        mBtnMainGetVerificationCode.setClickable(true);
                        mBtnMainGetVerificationCode.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.login_btn_bg));
                        mLlMainAgain.setVisibility(View.GONE);
                    }

                    public void onTick(long millisUntilFinished) {
                        mTvMainSeconds.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                }.start();
            } else {
                Toast.makeText(getApplicationContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "网络不可用", Toast.LENGTH_SHORT).show();
        }

    }

    private void initSMSSdk() {
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        regidstere();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功

                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void regidstere() {
        progressDialog.setMessage("注册中...");
        progressDialog.show();
        try {
            userName = Charset.changeCharset("空值", "utf-8", "ISO-8859-1");
            //accountName = Charset.changeCharset(accountName.getText().toString().trim(), "utf-8", "ISO-8859-1");
//            bankName = Charset.changeCharset(bank_name.getSelectedItem().toString().trim(), "utf-8", "ISO-8859-1");
//            bankProvince = Charset.changeCharset(bank_province.getSelectedItem().toString().trim(), "utf-8", "ISO-8859-1");
//            bankCity = Charset.changeCharset(bank_city.getSelectedItem().toString().trim(), "utf-8", "ISO-8859-1");
            bankName = Charset.changeCharset("中国银行", "utf-8", "ISO-8859-1");
            bankProvince = Charset.changeCharset("北京市", "utf-8", "ISO-8859-1");
            bankCity = Charset.changeCharset("北京市", "utf-8", "ISO-8859-1");
            bankBranchName = Charset.changeCharset(mEdtTxtMainBranchBankEdtext.getText().toString().trim(), "utf-8", "ISO-8859-1");
            province = Charset.changeCharset(mSpnMainProvinceSpinner.getSelectedItem().toString().trim(), "utf-8", "ISO-8859-1");
            city = Charset.changeCharset(mSpnMainCitySpinner.getSelectedItem().toString(), "utf-8", "ISO-8859-1");
            district = Charset.changeCharset(mSpnMainRegionr.getSelectedItem().toString().trim(), "utf-8", "ISO-8859-1");
            //store = Charset.changeCharset(address.getText().toString().trim(), "utf-8", "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //password = RestAPI.encrypByMD5(refistered_user_pass.getText().toString().trim());
        // phoneNumberReg = registered_phone_number.getText().toString().trim();
        // bankCardId = card_numbers.getText().toString().trim();

        if (userName.length() > 0 && password.length() > 0 && phoneNumberReg.length() > 0 && accountName.length() > 0 && bankCardId.length() > 0 && bankName.length() > 0
                && bankProvince.length() > 0 && bankCity.length() > 0 && bankBranchName.length() > 0 && province.length() > 0 && city.length() > 0 && district.length() > 0
                && store.length() > 0) {
            if (bankCardId.length() > 15 && bankCardId.length() < 20) {
                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0x123) {
                            if (status != null && status.equals("0")) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "此手机号已注册", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                };

                new Thread() {
                    @Override
                    public void run() {
                        status = RestAPI.register(phoneNumberReg, password, accountName, bankCardId, bankName, bankProvince, bankCity, bankBranchName, province, city, district,
                                store);
                        Message msg = new Message();
                        msg.what = 0x123;
                        Bundle bundle = new Bundle();
                        msg.setData(bundle);
                        msg.setTarget(handler);
                        handler.sendMessage(msg);
                    }
                }.start();
            } else {
                progressDialog.dismiss();
                System.err.println("bankCardId=================" + bankCardId);
                Toast.makeText(getApplicationContext(), "银行卡号格式不正确", Toast.LENGTH_SHORT).show();
            }
        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "数据填写不完整", Toast.LENGTH_SHORT).show();
        }

    }
}




