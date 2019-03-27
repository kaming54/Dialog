package com.kaming.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button, button2, button3, button4, button5, button6, button7, button8, button9, button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoView();
    }

    public void infoView() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
    }

    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                basicDiaLog1();
                break;
            case R.id.button2:
                basicDiaLog2();
                break;
            case R.id.button3:
                listDiaLog1();
                break;
            case R.id.button4:
                showSingleDigLog();
                break;
            case R.id.button5:
                showMultDigLog();
                break;
            case R.id.button6:
                waitProgressDigLog();
                break;
            case R.id.button7:
                showProgressDigLog2();
                break;
            case R.id.button8:
                enterTextDigLog();
                break;
            case R.id.button9:
                MyDiglog myDiglog = new MyDiglog(this);
                myDiglog.show();
                break;
            case R.id.button10:
                showArrayDiglog();
                break;
        }
    }

    public void basicDiaLog1() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("提示");
        ad.setMessage("你是否要退出該程序");
        ad.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        ad.setNegativeButton("中間", null);
        ad.setNeutralButton("左邊", null);
        ad.show();
    }

    public void basicDiaLog2() {
        AlertDialog ad2 = new AlertDialog.Builder(this).create();
        ad2.setTitle("評分");
        ad2.setMessage("你打算給幾分?");
        ad2.setButton(DialogInterface.BUTTON_POSITIVE, "5分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你選擇了5分", Toast.LENGTH_LONG).show();
            }
        });
        ad2.setButton(DialogInterface.BUTTON_NEGATIVE, "3分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        ad2.setButton(DialogInterface.BUTTON_NEGATIVE, "1分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        ad2.show();
    }

    public void listDiaLog1() {
        final String[] arr = {"我是1", "我是2", "我是3"};
        AlertDialog.Builder adlist1 = new AlertDialog.Builder(this);
        adlist1.setTitle("列表").setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, arr[i], Toast.LENGTH_LONG).show();
            }
        });
        adlist1.show();
    }

    //單選 列表對話框DigLog
    int idx = 0;

    public void showSingleDigLog() {
        final String[] arr = {"謝安琪", "周國賢", "陳小春"};
        AlertDialog.Builder listSingleDigLog = new AlertDialog.Builder(this);
        listSingleDigLog.setTitle("你喜歡那一位明星?").setSingleChoiceItems(arr, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                idx = which;
            }
        }).setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, arr[idx], Toast.LENGTH_LONG).show();
            }
        });
        listSingleDigLog.show();
    }

    //多選 列表對話框
    public void showMultDigLog() {
        final String[] arr = {"籃球", "足球", "冰球", "游水"};
        final boolean[] booArr = {true, false, true, false};
        AlertDialog.Builder multDigLog = new AlertDialog.Builder(this);
        multDigLog.setTitle("你喜歡什麼運動?");
        multDigLog.setMultiChoiceItems(arr, booArr, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Log.e("Lag", arr[which]);
            }
        }).setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = "你喜歡的運動是..";
                for (int x = 0; x < booArr.length; x++) {
                    if (booArr[x]) {
                        msg += arr[x] + " ";
                    }
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

            }
        });
        multDigLog.show();
    }

    public void waitProgressDigLog() {
        ProgressDialog waitDL = new ProgressDialog(this);
        waitDL.setTitle("我是一個等待對話框");
        waitDL.setMessage("請等待");
        waitDL.setCancelable(false); //默認是true
        waitDL.show();
//        waitDL.dismiss(); //設置對話框消失
    }

    private void showProgressDigLog2() {
        final ProgressDialog pd2 = new ProgressDialog(this);
        pd2.setTitle("我是一個進度條對話框");
        pd2.setMessage("等待中");
        //進度條是否模糊
//        pd2.setIndeterminate(true);
        //Style是用進度條
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd2.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    pd2.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pd2.dismiss();
            }
        }).start();
    }

    public void enterTextDigLog() {
        final EditText et = new EditText(this);
        AlertDialog.Builder etDL = new AlertDialog.Builder(this);
        etDL.setTitle("提示")
                .setView(et)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, et.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        etDL.show();
    }

    public void showArrayDiglog() {
        final String[] arr = {"Java", "Android", "IOS", "C++", "C"};
        //this,layout位置,layout位置中的那一個id,要拿的數組
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.adapter_array, R.id.tvText, arr);
        //建一個列表對話框
        AlertDialog.Builder adlist1 = new AlertDialog.Builder(this);
        adlist1.setTitle("列表")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,arr[which],Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        adlist1.show();
    }
}
