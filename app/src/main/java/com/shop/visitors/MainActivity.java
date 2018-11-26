package com.shop.visitors;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shop.visitors.Service.ShopService;
import com.shop.visitors.model.Shop;
import com.shop.visitors.model.Visitor;
import com.shop.visitors.ui.AboutMeFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvRecyclerView;
    private PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(myToolbar);

        rvRecyclerView = findViewById(R.id.rvRecyclerView);

        VenueFetcher venueFetcher = (VenueFetcher) new VenueFetcher(this).execute();
        venueFetcher.setListener(new VenueFetcher.OnUpdateListener() {
            @Override
            public void onUpdate(List<Visitor> pList) {
                personAdapter = new PersonAdapter(pList, getBaseContext());
                rvRecyclerView.setAdapter(personAdapter);
                rvRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

                LinearLayoutManager layoutManager = (LinearLayoutManager) rvRecyclerView.getLayoutManager();
                layoutManager.getOrientation();

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvRecyclerView.getContext(),
                        layoutManager.getOrientation());
                rvRecyclerView.addItemDecoration(dividerItemDecoration);
            }
        });

    }

    private static class VenueFetcher extends AsyncTask<Void, Void, Shop> {
        private final WeakReference<MainActivity> activityWeakReference;
        OnUpdateListener listener;

        public void setListener(OnUpdateListener listener) {
            this.listener = listener;
        }

        public interface OnUpdateListener {
            void onUpdate(List<Visitor> pList);
        }
        public VenueFetcher(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected Shop doInBackground(Void... params) {
            return activityWeakReference != null ? ShopService.get().getVenue(activityWeakReference.get()) : null;
        }

        @Override
        protected void onPostExecute(Shop venue) {
            if (activityWeakReference == null || venue == null) {
                return;
            }
            if (listener != null)
                listener.onUpdate(venue.getVisitors());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            FragmentManager fm = getSupportFragmentManager();
            AboutMeFragment aboutMeFragment = AboutMeFragment.newInstance("Some Title");
            aboutMeFragment.show(fm, "About me");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
