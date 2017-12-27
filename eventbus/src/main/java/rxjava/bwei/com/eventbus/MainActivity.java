package rxjava.bwei.com.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button but;
    private TextView tv;

    /**
     * 在OnCreate()函数中注册EventBus，在OnDestroy（）函数中反注册。所以整体的注册与反注册的代码如下：
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第二步接收消息页面注册

        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);


        but = (Button) findViewById(R.id.btn_try);
        tv = (TextView) findViewById(R.id.tv);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMainThread(FirstEvent event){
        String msg="接受到了消息"+event.getmMsg();
        Log.d("harvic",msg);
        tv.setText(event.getmMsg());

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}