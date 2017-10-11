package map.shiveh.ir.myrxfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Part1Activity extends AppCompatActivity
{
    private Button subscribeButton=null;
    private TextView outPutTextView=null;

    private Observable<String> stringObservable=null;
    private Observer<String> stringObserver=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        outPutTextView=findViewById(R.id.part1_output_textview);
        subscribeButton=findViewById(R.id.part1_subscribe_btn);


        stringObservable= Observable.just(getMessage());

        stringObserver=new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(String s)
            {
                Log.v("Here is Data-->","------->"+s);
                setSubscribedResponse(s);
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        };

        subscribeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSubscribe();
            }
        });

    }

    private String getMessage()
    {
        return "Hello From RxJava";
    }

    private void setSubscribedResponse(String s)
    {
        outPutTextView.setText(s);
    }

    private void onSubscribe()
    {
        stringObservable.subscribe(stringObserver);
    }
}
