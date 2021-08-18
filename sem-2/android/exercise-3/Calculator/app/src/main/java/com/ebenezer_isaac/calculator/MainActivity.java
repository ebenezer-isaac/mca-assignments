package com.ebenezer_isaac.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    float result = 0;
    String variable ="";
    String opr="";
    float temp = 0;
    TextView Variable_Display;
    TextView Result_Display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Variable_Display = (TextView)findViewById(R.id.disp_variable);
        Result_Display = (TextView)findViewById(R.id.disp_result);
    }
    public void variable_typed(View v){
        String character = v.getTag().toString();
        if (character.equals("10")){
            character=".";
        }
        variable = variable +character;
        Variable_Display.setText(variable);
    }
    public void clear(View v){
        variable = "";
        result=0;
        opr="";
        Variable_Display.setText("0");
        Result_Display.setText("0");
    }
    public void delete(View v){
        if (variable != null && variable.length() > 0) {
            variable = variable.substring(0, variable.length() - 1);
        }
        Variable_Display.setText(variable+"");
    }
    public void eq(View v){
        switch(opr){
            case "add":
                result=temp+Float.parseFloat(variable);
                break;
            case "sub":
                result=temp-Float.parseFloat(variable);
                break;
            case "mul":
                result=temp*Float.parseFloat(variable);
                break;
            case "div":
                result=temp/Float.parseFloat(variable);
                break;
            default:
                break;
        }
        variable=result+"";
        opr="";
        Variable_Display.setText(result+"");
        Result_Display.setText(result+"");
    }
    public void addition(View v){
        temp = Float.parseFloat(variable);
        opr="add";
        variable="";
    }
    public void subtraction(View v){
        temp = Float.parseFloat(variable);
        opr="sub";
        variable="";
    }
    public void multiplication(View v){
        temp = Float.parseFloat(variable);
        opr="mul";
        variable="";
    }
    public void division(View v){
        temp = Float.parseFloat(variable);
        opr="div";
        variable="";
    }
}