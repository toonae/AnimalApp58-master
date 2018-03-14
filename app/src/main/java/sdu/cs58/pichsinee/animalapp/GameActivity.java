package sdu.cs58.pichsinee.animalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    //Explicit
    Button btn1,btn2,btn3,btn4;
    ImageView questionImageView;
    ImageButton volumnImageButton;
    MediaPlayer mediaPlayer;//สำหรับเล่นเสียง
    int questionCount = 1; //ตัวแปรเก็บจำนวนข้อคำถาม
    ArrayList<Integer> qID = new ArrayList<Integer>();//ตัวแปร qId เป็นตัวแปรชนิดอะเรย์แบบสุ่มคำถาม
    String answer;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initial view ผูกเอริเม้นบนตัวแปร
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        questionImageView = findViewById(R.id.imvQuestion);
        volumnImageButton = findViewById(R.id.imbVolumn);

        //แสดงค่าถ่ม
        for (int i=1; i<=questionCount; i++) {
            qID.add(i);
        }
        Collections.shuffle(qID);//กำหนดให้คำถามเป็นแบบสุ่ม
        setQuestion(qID.remove(0));
    }//end onCreate Method

    private void setQuestion(int qID ) { //ใน method ทำหน้าที่กำหนดข้อคำถามและเฉลยในแต่ละข้อ

        if (qID ==1){
            answer = "นก";
            questionImageView.setImageResource(R.drawable.bird);
            mediaPlayer = MediaPlayer.create(this, R.raw.bird);

            ArrayList<String> choice = new ArrayList<String>(); //กำหนดการเรนด้อมช้อย
            choice.add("นก");
            choice.add("ยุง");
            choice.add("แมว");
            choice.add("หมู");
            Collections.shuffle(choice);//กำหนดเรนด้อมช้อย

            btn1.setText(choice.remove(0));
            btn2.setText(choice.remove(0));
            btn3.setText(choice.remove(0));
            btn4.setText(choice.remove(0));

        }

    }// end setquestion Method

    public void choiceAns (View view) { //ตรวจคำตอบว่า user ตอบถูกไหม

        Button button = (Button) view;
        String buttonString = button.getText().toString();
        if (buttonString.equals(answer)) {
            score++;
        }

        if (qID.isEmpty()) { //ถ้าทำครบทุกข้อ นั่นคือ qID เป็นค่าว่าง
            dialogboxScore();// เรียก method dialogboxScore() สำหรับแสดงคะแนน
        }else {//ถ้ายังทำไม่ครบทุกข้อ
            setQuestion(qID.remove(0));// เรียก medthod setQuestion() สำหรับแสดงคำถามถัดไป
        }

    }// end choiceAns Method

    private void dialogboxScore() {//method สำหรับแสดงคะแนน

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("สรุปคะแนน");
        builder.setMessage("ได้คะแนน " + score+ "คะแนน")
            .setCancelable(false)
            .setPositiveButton("ออกจากเกม", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                 finish();//ปิดแอคติวิตี้เพื่ออกจากแอพ
                }
            })
            .setNegativeButton("เล่นอีกครั้ง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }//end dialogboxScore Method


    public  void playSound(View view) {  //ชนิดview ตัวแปรview
        mediaPlayer.start();
    }//end playSound Method




}// end Class
