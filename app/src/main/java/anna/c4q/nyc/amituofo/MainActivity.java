package anna.c4q.nyc.amituofo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import anna.c4q.nyc.amituofo.SlidingTabLayout.TabColorizer;

public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = { "经 文", "音 乐", "每日佛语" };
    int Numboftabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.drawable.app_tab_icon);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles,
                Numboftabs);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {

                return R.color.accentColor;
            }
        });

        tabs.setViewPager(pager);
        pager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.goToQuestions){
            startActivity(new Intent(this,QuestionActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}


