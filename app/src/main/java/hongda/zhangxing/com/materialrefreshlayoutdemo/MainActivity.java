package hongda.zhangxing.com.materialrefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MaterialRefreshLayout materialRefreshLayout;
    private ListView listView;

    private List<String> datas;//数据源

    private ArrayAdapter adapter;

    private boolean isLoadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        initRefresh();
    }

    /**
     * 初始化View
     */
    private void initView() {
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        listView = (ListView) findViewById(R.id.listview);

        datas = new ArrayList<>();
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        datas.add("武汉");
        datas.add("北京");
        datas.add("天津");
        datas.add("重庆");
        datas.add("南京");
        datas.add("上海");
        datas.add("深圳");
        datas.add("广州");
        datas.add("合肥");
        datas.add("石家庄");
        datas.add("济南");
        datas.add("青岛");
        datas.add("拉萨");
        datas.add("内蒙古");
        datas.add("昆明");
        datas.add("桂林");
        datas.add("西宁");
        datas.add("青海");
    }

    /**
     * 设置刷新
     */
    private void initRefresh() {
        //设置适配器
        adapter =new ArrayAdapter(this , android.R.layout.simple_list_item_1 , datas);
        listView.setAdapter(adapter);

        //允许上拉加载更多操作
        materialRefreshLayout.setLoadMore(isLoadMore);
        //使用MaterialRefreshLayout实现下拉刷新、上拉加载
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            /**
             * 下拉刷新
             */
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //一般加载数据都是在子线程中，这里用到了handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.clear();//清空原始数据
                        initDatas();//重新添加数据

                        for (int i = 0; i <5; i++) {
                            datas.add(i, "下拉刷新_new City_ " + i);//数据添加到最前面
                        }

                        adapter.notifyDataSetChanged();//通知更新

                        //刷新完成后调用此方法，要不然刷新效果不消失
                        materialRefreshLayout.finishRefresh();

                        Toast.makeText(MainActivity.this, "已经没有更多的数据了", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);
            }

            int count=0;
            /**
             * 上拉加载
             * 真正用的时候，就会去定义方法，获取数据，一般都是分页，在数据端获取的时候把页数去增加1，然后再去服务端去获取数据。
             */
            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;

                        for (int i = 0; i <5; i++) {
                            datas.add(datas.size(), "上拉加载_new City_ " + count);//在列表末尾添加数据
                            count++;
                        }

                        adapter.notifyDataSetChanged();//通知刷新

                        //完成加载数据后，调用此方法，要不然刷新的效果不会消失
                        materialRefreshLayout.finishRefreshLoadMore();

                        Toast.makeText(MainActivity.this, "亲，这是最新数据", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);
            }

        });

    }

}
