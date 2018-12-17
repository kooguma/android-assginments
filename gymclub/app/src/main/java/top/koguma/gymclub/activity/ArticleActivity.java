package top.koguma.gymclub.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import top.koguma.gymclub.Navigator;
import top.koguma.gymclub.R;

import static top.koguma.gymclub.R.id.txt_content;

public class ArticleActivity extends GymClubBaseActivity {

    TextView mTxtArticle;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        String content = getIntent().getStringExtra(Navigator.EXTRA_CONTENT_URL);
        mTxtArticle = (TextView) findViewById(R.id.txt_content);
        mTxtArticle.setText(content);
    }


    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
