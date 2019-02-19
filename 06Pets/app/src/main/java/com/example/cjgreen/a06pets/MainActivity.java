package com.example.cjgreen.a06pets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 변수선언
    // activity_main.xml의 9개 위젯에 대응할 위젯 변수 9개 선언

    TextView text1, text2;
    CheckBox chkagree;
    RadioGroup rGroup1;
    RadioButton rdoDog,rdoCat,rdoRabbit;
    Button btnOK;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진보기");

        text1 = (TextView) findViewById(R.id.Text1);
        text2= (TextView) findViewById(R.id.Text2);

        chkagree = (CheckBox) findViewById(R.id.ChkAgree);

        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog = (RadioButton) findViewById(R.id.RdoDog);
        rdoCat= (RadioButton) findViewById(R.id.RdoCat);
        rdoRabbit = (RadioButton) findViewById(R.id.RdoRabbit);

        btnOK = (Button) findViewById(R.id.BtnOK);
        imgPet = (ImageView) findViewById(R.id.ImgPet);

        // <시작함>체크박스를 체크/언체크할 때 동작하는 리스너를 정의
        chkagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(chkagree.isChecked() == true) {
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);

                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnOK.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                    rdoCat.setChecked(false);
                    rdoDog.setChecked(false);
                    rdoRabbit.setChecked(false);

                }//end of if
            }//end of setOnCheckedChanged
        });//end of chkagree.setOnCheckChangeListener

        //<선택완료> 버튼 클릭 선택시 동작하는 리스너 정의
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rdoCat.isChecked() == true || rdoDog.isChecked() == true || rdoRabbit.isChecked() == true) {
                    imgPet.setVisibility(View.VISIBLE);

                    switch (rGroup1.getCheckedRadioButtonId()) {
                        case R.id.RdoDog:
                            imgPet.setImageResource(R.drawable.dog);
                            break;
                        case R.id.RdoCat:
                            imgPet.setImageResource(R.drawable.cat);
                            break;
                        case R.id.RdoRabbit:
                            imgPet.setImageResource(R.drawable.rabbit);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "동물을 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                    }//end of switch

                }else{
                    Toast.makeText(getApplicationContext(), "체크가 안되어있습니다", Toast.LENGTH_SHORT).show();
                }
            }//end of onClick
        });//end of btnOK

     }//end of OnCreate()
}// end of class Main_activity
