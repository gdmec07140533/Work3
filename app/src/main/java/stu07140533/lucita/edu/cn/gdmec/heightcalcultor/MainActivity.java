package stu07140533.lucita.edu.cn.gdmec.heightcalcultor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText weightEdit;
    private CheckBox manCheckbox,womenCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.calcultor);
        weightEdit = (EditText)findViewById(R.id.weight);
        manCheckbox = (CheckBox)findViewById(R.id.man);
        womenCheckbox = (CheckBox)findViewById(R.id.woman);
        resultView = (TextView)findViewById(R.id.result);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registEvent();
    }
    private void registEvent(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEdit.getText().toString().equals("")) {
                    if (manCheckbox.isChecked() || womenCheckbox.isChecked()) {
                        Double weight = Double.parseDouble(weightEdit.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果-------\n");
                        if (manCheckbox.isChecked()) {
                            sb.append("男性的标准身高：");
                            double result = valueHeight(weight, "男");
                            sb.append((int) result + "厘米");
                        }
                        if (womenCheckbox.isChecked()) {
                            sb.append("女性的标准身高");
                            double result = valueHeight(weight, "女");
                            sb.append((int) result + "厘米");
                        }
                        resultView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别");
                    }
                } else {
                    showMessage("请输入体重");
                }
            }
        });
    }

    private double valueHeight(double weight,String sex){
        double height;
        if (sex == "男"){
            height = 170 - (62 - weight)/0.6;
        }else {
            height = 158 - (52 - weight)/0.5;
        }
        return height;
    }

    private void  showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, 1, 1,"退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
