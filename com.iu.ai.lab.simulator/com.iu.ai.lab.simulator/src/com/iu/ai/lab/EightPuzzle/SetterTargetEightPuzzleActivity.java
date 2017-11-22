package com.iu.ai.lab.EightPuzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iu.ai.lab.R;

public class SetterTargetEightPuzzleActivity extends Activity {

	Button	clearButton, setButton;
	EditText	target, init;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_setter_target_eight_puzzle);
		this.clearButton = (Button) this.findViewById(R.id.buttonClear);
		this.setButton = (Button) this.findViewById(R.id.buttonSet);
		this.target = (EditText) this.findViewById(R.id.editTextTarget);
		this.init = (EditText) this.findViewById(R.id.editTextInit);
		final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

		this.clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setAnimation(animAlpha);
				if (SetterTargetEightPuzzleActivity.this.target.isFocused())
					SetterTargetEightPuzzleActivity.this.target.setText("");
				if (SetterTargetEightPuzzleActivity.this.init.isFocused())
					SetterTargetEightPuzzleActivity.this.init.setText("");
			}
		});
		this.setButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setAnimation(animAlpha);
				String t = SetterTargetEightPuzzleActivity.this.target.getText().toString();
				String i = SetterTargetEightPuzzleActivity.this.init.getText().toString();

				if (!this.checkValidState(i)) {
					Toast.makeText(SetterTargetEightPuzzleActivity.this.getApplicationContext(), "Error on set init state", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!this.checkValidState(t)) {
					Toast.makeText(SetterTargetEightPuzzleActivity.this.getApplicationContext(), "Error on set target state", Toast.LENGTH_SHORT).show();
					return;
				}

				Intent intent = new Intent(SetterTargetEightPuzzleActivity.this.getApplicationContext(), EightPuzzleCalculationActivity.class);
				intent.putExtra("target", t);
				intent.putExtra("init", i);

				SetterTargetEightPuzzleActivity.this.startActivity(intent);
				SetterTargetEightPuzzleActivity.this.finish();
			}

			private boolean checkValidState(final String i) {
				if (i.length() != 9)
					return false;
				if (!i.contains("1"))
					return false;
				if (!i.contains("2"))
					return false;
				if (!i.contains("3"))
					return false;
				if (!i.contains("4"))
					return false;
				if (!i.contains("5"))
					return false;
				if (!i.contains("6"))
					return false;
				if (!i.contains("7"))
					return false;
				if (!i.contains("8"))
					return false;
				if (!i.contains("0"))
					return false;

				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		this.getMenuInflater().inflate(R.menu.activity_setter_target_eight_puzzle, menu);
		return true;
	}
}
