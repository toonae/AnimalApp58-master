package sdu.cs58.pichsinee.animalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    EditText nameEditText;
    Button startButton;
    String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        nameEditText = findViewById(R.id.edtName);
        startButton = findViewById(R.id.btnStart);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = nameEditText.getText().toString().trim();//เอาข้อความที่Userใช้มาเก็บไว้ใน namestring
                //check ค่าว่างในตัวแปร nameString
                if (nameString.length() == 0) {
                    Toast.makeText(getApplicationContext(), "กรุณาใส่ชื่อด้วยค่ะ", Toast.LENGTH_SHORT).show();
                } else {
                    //เปิดหน้า game
                    Intent starIntent = new Intent(MainActivity.this, GameActivity.class);
                    starIntent.putExtra("Name",nameString);
                    startActivity(starIntent);
                }
            }
        });
    }//end onCreate Method

}//end Class
