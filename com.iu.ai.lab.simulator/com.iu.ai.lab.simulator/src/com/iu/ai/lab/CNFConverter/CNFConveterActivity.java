package com.iu.ai.lab.CNFConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lab6.Logic.UseCNFConverter;
import lab6.Logic.UsePreposition;
import lab6.Logic.Minesweeper.MineBoard;
import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
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
import android.widget.TextView;

import com.iu.ai.lab.R;

public class CNFConveterActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_cnfconveter);

		final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

		Button buttonConveter = (Button) this.findViewById(R.id.button1);
		Button buttonMine = (Button) this.findViewById(R.id.button2);
		TextView clear = (TextView) this.findViewById(R.id.textView2);
		TextView addBracket = (TextView) this.findViewById(R.id.TextView22);
		TextView addImplement = (TextView) this.findViewById(R.id.textView3);

		TextView tvA = (TextView) this.findViewById(R.id.TextViewA);
		TextView tvNA = (TextView) this.findViewById(R.id.TextViewNA);

		final EditText text = (EditText) this.findViewById(R.id.editText1);

		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				text.setText("");
			}
		});
		addBracket.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				String s = text.getText().toString();

				int start = text.getSelectionStart();
				int end = text.getSelectionEnd();

				s = s.substring(0, start) + "(" + s.substring(start, end) + ")" + s.substring(end, s.length());
				text.setText(s);
				text.setSelection(start + 1);

			}
		});
		addImplement.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				String s = text.getText().toString();

				int start = text.getSelectionStart();
				int end = text.getSelectionStart();

				s = s.substring(0, start) + " => " + s.substring(end, s.length());
				text.setText(s);
				text.setSelection(start + 1);

			}
		});

		tvA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				String s = text.getText().toString();

				int start = text.getSelectionStart();
				int end = text.getSelectionStart();

				s = s.substring(0, start) + "A" + s.substring(end, s.length());
				text.setText(s);
				text.setSelection(start + 1);

			}
		});

		tvNA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				String s = text.getText().toString();

				int start = text.getSelectionStart();
				int end = text.getSelectionStart();

				s = s.substring(0, start) + "-A" + s.substring(end, s.length());
				text.setText(s);
				text.setSelection(start + 1);

			}
		});
		//
		text.setText(" 111\n" + //
				" AB1\n" + //
				" C21\n" + //
				" D1\n" + //
				" 11"); //

		text.setText("A => (D ^ F)\n" + //
				"B => (C v E)\n" + //
				"(E ^ F) => H\n" + //
				"A\n" + //
				"B\n" + //
				"-C"); //

		UsePreposition.getKB("");
		KnowledgeBase kb = new KnowledgeBase();
		buttonConveter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setAnimation(animAlpha);

				String content_html = "<H4>Convert Sentences to Conjunctive Normal Form</H4>\n" + //
						"<table border=\"0\">\n" + //
						"  <tr bgcolor=\"#5BC5FA\">\n" + //
						"    <th >Sentences </th>\n" + //
						"    <th  >CNF</th>\n" + //
						"  </tr>"; //

				KnowledgeBase kb = new KnowledgeBase();
				// Create a Resolution-based prover
				PLResolution prover = new PLResolution();
				String lists = text.getText().toString().toUpperCase();
				List<String> setKB = UsePreposition.getKB(lists);

				System.out.println("---------CNF --------------------");
				int count_cnf = 1;
				int count_st = 1;
				for (String k : setKB) {

					System.out.println(k);
					Set<String> cnf = UseCNFConverter.getCNF(k);
					int index = 0;
					for (String c : cnf) {

						if (index++ == 0) {

							content_html += "\n" + "<tr bgcolor=\"#BFE6FE\">";
							content_html += "\n" + " <td bgcolor=\"#FAB4B4\">" + (count_st + ". " + k) + "</td> ";
							content_html += "\n" + "<td>" + (count_cnf + ". " + UseCNFConverter.toNatural(c)) + "</td>";
							content_html += "\n" + " </tr>";

						}
						else {

							content_html += "\n" + " <tr bgcolor=\"#BFE6FE\">";
							content_html += "\n" + " <td bgcolor=\"#FFFFFF\"></td> ";
							content_html += "\n" + " <td>" + (count_cnf + ". " + UseCNFConverter.toNatural(c)) + "</td>";
							content_html += "\n" + " </tr>";
						}

						System.out.println(count_cnf + ". " + UseCNFConverter.toNatural(c));

						++count_cnf;
					}
					kb.tell(k);
					count_st++;

				}
				content_html += "</table><br>";
				// Declare the sentence, called 'conclusion',
				// that you want to infer from the knowledge base
				content_html += "<H4>Prove by contradition</H4>\n" + //
						"<table border=\"0\">\n" + //
						"  <tr bgcolor=\"#5BC5FA\">\n" + //
						"    <th >Conclusion </th>\n" + //
						"    <th  >Found a contradition</th>\n" + //
						"  </tr>"; //

				String patternAlpha = "QWERYUIOPADFGHJKLZXCBNM";

				List<String> s = new ArrayList<String>();

				for (int i = 0; i < patternAlpha.length(); i++) {
					String conclusion = "(" + String.valueOf(patternAlpha.charAt(i)) + ")";
					// Prove the conclusion
					if (lists.contains(String.valueOf(patternAlpha.charAt(i)))) {
						boolean result = prover.plResolution(kb, conclusion);
						// Result : TRUE = found a CONTRADITION
						// Result : FALSE = COULD NOT find a CONTRADITION
						String sub = "";
						sub += "\n" + "<tr bgcolor=\"#BFE6FE\">";
						sub += "\n" + " <td bgcolor=\"#BFE6FE\">" + conclusion + "</td> ";
						sub += "\n" + "<td>" + result + "</td>";
						sub += "\n" + " </tr>";
						s.add(sub);
					}
				}
				Collections.sort(s);
				for (String string : s)
					content_html += string;
				content_html += "</table>";

				Intent intent = new Intent(CNFConveterActivity.this.getApplicationContext(), CNFResultActivity.class);
				intent.putExtra("content_html", content_html);
				CNFConveterActivity.this.startActivity(intent);
			}
		});

		buttonMine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setAnimation(animAlpha);

				String x = text.getText().toString();

				lab6.Logic.Minesweeper.MineBoard mine = new MineBoard(x, false);

				String content_html = "";

				content_html += "<H4>MineSweeper</H4>\n" + //

						mine.getHtml() + //
						"<table border=\"0\">\n" + //
						"  <tr bgcolor=\"#5BC5FA\">\n" + //
						"    <th > </th>\n" + //
						"    <th  >Final Logic</th>\n" + //
						"    <th  ></th>\n" + //
						"  </tr>"; //

				System.out.println("------- Final Logic------------");
				// Create a knowledge base
				KnowledgeBase kb = new KnowledgeBase();
				// Create a Resolution-based prover
				PLResolution prover = new PLResolution();

				int count = 1;
				int count_cnf = 1;
				for (String l : mine.logic) {
					System.out.println(count + ". " + l);

					String k = UseCNFConverter.parseAnd(l);
					k = UseCNFConverter.toEngine(k);
					System.out.println("kkkk = " + k);
					kb.tell(k);

					content_html += "\n" + "<tr bgcolor=\"#BFE6FE\">";
					content_html += "\n" + " <td bgcolor=\"#BFE6FE\">" + count + "</td> ";
					content_html += "\n" + "<td>" + l + "</td>";
					content_html += "\n" + "<td>" + k + "</td>";
					content_html += "\n" + " </tr>";

					Set<String> cnf = UseCNFConverter.getCNF(k);
					int index = 0;
					for (String c : cnf) {
						System.out.println(++count + ". " + UseCNFConverter.toNatural(c));

						count_cnf++;
					}
					++count;
				}
				content_html += "</table>";

				String patternAlpha = "QWERYUIOPADFGHJKLZXCBNM";

				List<String> s = new ArrayList<String>();

				content_html += "</table><br>";
				// Declare the sentence, called 'conclusion',
				// that you want to infer from the knowledge base
				content_html += "<H4>Prove by contradition</H4>\n" + //
						"<table border=\"0\">\n" + //
						"  <tr bgcolor=\"#5BC5FA\">\n" + //
						"    <th >Conclusion </th>\n" + //
						"    <th  >Found a contradition</th>\n" + //
						"  </tr>"; //

				for (int i = 0; i < patternAlpha.length(); i++) {
					String conclusion = "(" + String.valueOf(patternAlpha.charAt(i)) + ")";
					// Prove the conclusion
					if (x.contains(String.valueOf(patternAlpha.charAt(i)))) {
						boolean result = prover.plResolution(kb, conclusion);
						// Result : TRUE = found a CONTRADITION
						// Result : FALSE = COULD NOT find a CONTRADITION
						String sub = "";
						sub += "\n" + "<tr bgcolor=\"#BFE6FE\">";
						sub += "\n" + " <td bgcolor=\"#BFE6FE\">" + conclusion + "</td> ";
						sub += "\n" + "<td>" + result + "</td>";
						sub += "\n" + " </tr>";
						s.add(sub);
					}
				}
				Collections.sort(s);
				for (String string : s)
					content_html += string;
				content_html += "</table>";

				content_html = "<body width=\"500px\">" + content_html + "\n<body>";
				Intent intent = new Intent(CNFConveterActivity.this.getApplicationContext(), CNFResultActivity.class);
				intent.putExtra("content_html", content_html);
				CNFConveterActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		this.getMenuInflater().inflate(R.menu.activity_cnfconveter, menu);
		return true;
	}

}
