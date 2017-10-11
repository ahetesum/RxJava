package map.shiveh.ir.myrxfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity
{
    private Button part1Button=null;
    private Button part2Button=null;
    private Button part3Button=null;
    private Button part4Button=null;
    private Button part5Button=null;
    private Button part6Button=null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        part1Button=findViewById(R.id.main_part1_btn);
        part2Button=findViewById(R.id.main_part2_btn);
        part3Button=findViewById(R.id.main_part3_btn);
        part4Button=findViewById(R.id.main_part4_btn);
        part5Button=findViewById(R.id.main_part5_btn);
        part6Button=findViewById(R.id.main_part6_btn);


        part1Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent part1Intent=new Intent(MainActivity.this,Part1Activity.class);
                startActivity(part1Intent);
            }
        });

        part2Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent part2Intent=new Intent(MainActivity.this,Part2Activity.class);
                startActivity(part2Intent);
            }
        });

    }

}
