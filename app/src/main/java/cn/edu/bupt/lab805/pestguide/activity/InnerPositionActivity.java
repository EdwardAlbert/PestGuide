package cn.edu.bupt.lab805.pestguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.bupt.lab805.pestguide.R;

public class InnerPositionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_pos1)
    Button btnPos1;
    @BindView(R.id.btn_pos2)
    Button btnPos2;
    @BindView(R.id.btn_pos3)
    Button btnPos3;
    @BindView(R.id.btn_pos4)
    Button btnPos4;
    @BindView(R.id.btn_pos5)
    Button btnPos5;
    @BindView(R.id.btn_pos6)
    Button btnPos6;
    @BindView(R.id.btn_pos7)
    Button btnPos7;
    @BindView(R.id.btn_pos8)
    Button btnPos8;
    @BindView(R.id.btn_pos9)
    Button btnPos9;
    @BindView(R.id.btn_pos10)
    Button btnPos10;
    @BindView(R.id.btn_pos11)
    Button btnPos11;
    @BindView(R.id.btn_pos12)
    Button btnPos12;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_position);
        ButterKnife.bind(this);

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPos1.setOnClickListener(this);
        btnPos2.setOnClickListener(this);
        btnPos3.setOnClickListener(this);
        btnPos4.setOnClickListener(this);
        btnPos5.setOnClickListener(this);
        btnPos6.setOnClickListener(this);
        btnPos7.setOnClickListener(this);
        btnPos8.setOnClickListener(this);
        btnPos9.setOnClickListener(this);
        btnPos10.setOnClickListener(this);
        btnPos11.setOnClickListener(this);
        btnPos12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int pos = 0;
        switch (v.getId()) {
            case R.id.btn_pos1:
                pos = 1;
                break;
            case R.id.btn_pos2:
                pos = 2;
                break;
            case R.id.btn_pos3:
                pos = 3;
                break;
            case R.id.btn_pos4:
                pos = 4;
                break;
            case R.id.btn_pos5:
                pos = 5;
                break;
            case R.id.btn_pos6:
                pos = 6;
                break;
            case R.id.btn_pos7:
                pos = 7;
                break;
            case R.id.btn_pos8:
                pos = 8;
                break;
            case R.id.btn_pos9:
                pos = 9;
                break;
            case R.id.btn_pos10:
                pos = 10;
                break;
            case R.id.btn_pos11:
                pos = 11;
                break;
            case R.id.btn_pos12:
                pos = 12;
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("position", pos);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
