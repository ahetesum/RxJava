package map.shiveh.ir.myrxfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Part2Activity extends AppCompatActivity
{
    private Observable<Post> postObservable=null;
    private Observer<Post> postObserver=null;
    private TextView resutTextView=null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);

        resutTextView=findViewById(R.id.part2_rest_textview);

        postObserver=new Observer<Post>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(Post post)
            {
                resutTextView.setText(post.title);
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

        postObservable =getPostObservable();
        //Subscribing The Observable with observer Mentioning the which part is in main thread and which part in IO thread
        postObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postObserver);


    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(postObserver!=null)
        {
            postObservable.unsubscribeOn(Schedulers.io());
        }

    }

    public Observable<Post> getPostObservable()
    {
        return Observable.defer(new Callable<ObservableSource<? extends Post>>()
        {
            @Override
            public ObservableSource<? extends Post> call() throws Exception
            {
                try
                {
                    return Observable.just(getPost());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    private Post getPost() throws IOException
    {
        OkHttpClient okHttpClient= new OkHttpClient();

        Request request=new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/1")
                .build();
        Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful())
        {
            Post post= new Gson().fromJson(response.body().charStream(), Post.class);
            return post;
        }

        return null;
    }
}
