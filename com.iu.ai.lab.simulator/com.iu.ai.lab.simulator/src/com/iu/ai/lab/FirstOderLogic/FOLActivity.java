package com.iu.ai.lab.FirstOderLogic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.iu.ai.lab.R;

public class FOLActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fol);

		final Button add = (Button) findViewById(R.id.button1);
		final EditText textconstant = (EditText) findViewById(R.id.editText1);

		final ListView listviewConstant = (ListView) findViewById(R.id.listView1);

		final BaseAdapter constantAdapter = new FOLAdapter(this, FOL.FOLView.Constant);

		listviewConstant.setAdapter(constantAdapter);

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FOL.constant.add(textconstant.getText().toString());
				constantAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_fol, menu);
		return true;
	}
}
