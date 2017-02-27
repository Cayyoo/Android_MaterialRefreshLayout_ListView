# MaterialRefreshLayout、ListView下拉刷新、上拉加载

```java
<com.cjj.MaterialRefreshLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/refresh"
        app:overlay="false"
        app:wave_show="true"
        app:wave_color="@color/material_green"
        app:wave_height_type="higher">

         <ListView
             android:id="@+id/listview"
             android:layout_width="match_parent"
             android:layout_height="match_parent"/>

</com.cjj.MaterialRefreshLayout>
```

```java
//允许上拉加载更多操作
materialRefreshLayout.setLoadMore(true);

materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
      @Override
      public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

          //刷新完成后调用此方法，要不然刷新效果不消失
          materialRefreshLayout.finishRefresh();
      }

      @Override
      public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
      
          //完成加载数据后，调用此方法，要不然刷新的效果不会消失
          materialRefreshLayout.finishRefreshLoadMore();
      }
            
});
```
![screenshot](https://github.com/ykmeory/MaterialRefreshLayout_ListView/blob/master/screenshot.jpg "截图")
