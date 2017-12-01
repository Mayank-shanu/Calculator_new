package com.example.mayankjain.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.M;
import static com.example.mayankjain.calc.R.id.del;
import static com.example.mayankjain.calc.R.id.display;

public class MainActivity extends AppCompatActivity {
    private String disp="";
    private TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onclicknum(View v) {
        Button b=(Button)v;
        disp +=b.getText().toString();
        display(disp);

    }

    private void display(String d)
    {   if(disp.length()<100)
    {t= (TextView)findViewById(display);
        t.setText(d);}
        else
        Toast.makeText(getApplicationContext(),"OVERFLOW", Toast.LENGTH_LONG).show();
    }

    public void onclickclear()
    {
        disp="";
        display(disp);

    }


    public void onclickback()
    {
       char[] ch=new char[disp.length()];
        ch = disp.toCharArray();
        disp="";
        for(int i=0;i<(ch.length -1 );i++)
            disp+=ch[i];
        display(disp);
    }

    public void onclickop(View v)
    {

        if(disp.length()==0)
        { Toast.makeText(MainActivity.this,"ENTER NUMBER FIRST",Toast.LENGTH_LONG).show();}

        else {Button b=(Button)v;


            disp += b.getText().toString();

            display(disp);

        }

    }

    public void delete(View v)
    {
        Button b=(Button)v;
        b.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                onclickclear();
                return true;
            }
        });

        onclickback();


    }

    public void onclickequal(View v)
    {
        char[] ch = disp.toCharArray();

        ArrayList<Double> arr=new ArrayList<Double>();
        ArrayList<Character> operators=new ArrayList<Character>();


        operators=getOperator(ch);
        arr=getNumber(ch);


        Double result=calculation(operators,arr);
        disp=""+result;
        display(disp);

    }

    private  ArrayList getOperator(char[] ch)
    {
        ArrayList<Character> operators =new ArrayList<Character>();

        for(int i=0;i<ch.length;i++)
        {if(ch[i]=='/'||ch[i]=='*'||ch[i]=='+'||ch[i]=='-')
            operators.add(ch[i]);}


        return operators;

    }

    private  ArrayList getNumber(char[] ch)
    {
        Double arr[]=new Double[ch.length];
        ArrayList<Double> arr2=new ArrayList<Double>();
        for(int i=0;i<arr.length;++i)
            arr[i]=0.0;
        int nPos=0;
        int count=1,del=0;
        for(int i=0;i<ch.length;i++)
        {
            if(ch[i]=='/'||ch[i]=='*'||ch[i]=='+'||ch[i]=='-')
            {
                arr2.add(arr[nPos]);
                ++nPos;
                del=0;
                count=1;

            }
            else if(ch[i]=='.'){del=1;}
            else{
                if(del==0) {
                    switch (ch[i]) {
                        case '0':
                            arr[nPos] = arr[nPos] * 10;
                            break;
                        case '1':
                            arr[nPos] = arr[nPos] * 10 + 1;
                            break;
                        case '2':
                            arr[nPos] = arr[nPos] * 10 + 2;
                            break;
                        case '3':
                            arr[nPos] = arr[nPos] * 10 + 3;
                            break;
                        case '4':
                            arr[nPos] = arr[nPos] * 10 + 4;
                            break;
                        case '5':
                            arr[nPos] = arr[nPos] * 10 + 5;
                            break;
                        case '6':
                            arr[nPos] = arr[nPos] * 10 + 6;
                            break;
                        case '7':
                            arr[nPos] = arr[nPos] * 10 + 7;
                            break;
                        case '8':
                            arr[nPos] = arr[nPos] * 10 + 8;
                            break;
                        case '9':
                            arr[nPos] = arr[nPos] * 10 + 9;
                            break;
                        default:
                            break;

                    }
                }
                else if(del==1){

                    switch (ch[i]) {
                        case '0':
                            arr[nPos] = arr[nPos] ;
                            break;
                        case '1':
                            arr[nPos] = arr[nPos]  + 1/Math.pow(10,count);
                            break;
                        case '2':
                            arr[nPos] = arr[nPos]  + 2/Math.pow(10,count);
                            break;
                        case '3':
                            arr[nPos] = arr[nPos]  + 3/Math.pow(10,count);
                            break;
                        case '4':
                            arr[nPos] = arr[nPos]  + 4/Math.pow(10,count);
                            break;
                        case '5':
                            arr[nPos] = arr[nPos]  + 5/Math.pow(10,count);
                            break;
                        case '6':
                            arr[nPos] = arr[nPos] + 6/Math.pow(10,count);
                            break;
                        case '7':
                            arr[nPos] = arr[nPos]  + 7/Math.pow(10,count);
                            break;
                        case '8':
                            arr[nPos] = arr[nPos]  + 8/Math.pow(10,count);
                            break;
                        case '9':
                            arr[nPos] = arr[nPos]  + 9/Math.pow(10,count);
                            break;
                        default:
                            break;

                    }
                    ++count;

                }

            }

        }
        arr2.add(arr[nPos]);
        for(int i=nPos+1;i<arr.length;++i){
            arr[i]=-5.0;
        }

        return arr2;
    }

    private  Double calculation(ArrayList<Character> operators,ArrayList<Double> numbers){

        for(int i=0;i<operators.size();++i)
        {
            if((char)operators.get(i)=='/'){
                numbers.set(i, (Double)numbers.get(i)/(Double)numbers.get(i+1) );
                numbers.remove(i+1);
                operators.remove(i);
                --i;
            }}

        for(int i=0;i<operators.size();++i)
        {
            if((char)operators.get(i)=='*'){
                numbers.set(i, (Double)numbers.get(i)*(Double)numbers.get(i+1) );
                numbers.remove(i+1);
                operators.remove(i);
                --i;
            }}
        for(int i=0;i<operators.size();++i)
        {
            if((char)operators.get(i)=='+'){
                numbers.set(i, (Double)numbers.get(i)+(Double)numbers.get(i+1) );
                numbers.remove(i+1);
                operators.remove(i);
                --i;
            }}

        for(int i=0;i<operators.size();++i)
        {
            if((char)operators.get(i)=='-'){
                numbers.set(i, (Double)numbers.get(i)-(Double)numbers.get(i+1) );
                numbers.remove(i+1);
                operators.remove(i);
                --i;
            }}

        return (Double)numbers.get(0);

    }

}

