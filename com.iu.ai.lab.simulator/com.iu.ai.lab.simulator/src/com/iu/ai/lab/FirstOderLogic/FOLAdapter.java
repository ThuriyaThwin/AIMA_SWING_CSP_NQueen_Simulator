package com.iu.ai.lab.FirstOderLogic;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FOLAdapter extends BaseAdapter {

	private final Activity			activity;
	private static LayoutInflater	inflater	= null;
	FOL.FOLView						view;

	public FOLAdapter(Activity a, FOL.FOLView view) {
		activity = a;
		this.view = view;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		switch (view) {
			case Constant:
				return FOL.constant.size();

			case Predicate:
				return FOL.predicate.size();

			case Knowledge:
				return FOL.knowledge.size();
			default:
				return 0;
		}
	}

	@Override
	public String getItem(int position) {
		switch (view) {
			case Constant:
				return FOL.constant.get(position);

			case Predicate:
				return FOL.predicate.get(position);

			case Knowledge:
				return FOL.knowledge.get(position);
			default:
				return "~Error";
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.simple_list_item_1, parent);
		final TextView name = (TextView) vi.findViewById(R.id.text1);
		final String dt = getItem(position);
		name.setText(dt);
		return vi;
	}

}