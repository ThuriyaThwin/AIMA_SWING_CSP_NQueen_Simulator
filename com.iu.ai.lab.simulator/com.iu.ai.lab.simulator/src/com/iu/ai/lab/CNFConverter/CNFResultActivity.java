package com.iu.ai.lab.CNFConverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

import com.iu.ai.lab.R;

public class CNFResultActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cnfresult);

		Intent intent = getIntent();
		String html = intent.getStringExtra("content_html");
		String mime = "text/html";
		String encoding = "utf-8";
		WebView web = (WebView) findViewById(R.id.webView1);

		web.getSettings().setJavaScriptEnabled(false);
		web.loadDataWithBaseURL(null, html, mime, encoding, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_cnfresult, menu);
		return true;
	}
}
