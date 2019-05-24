package ua.sertox.evo.adapters;

import java.util.ArrayList;

import ua.sertox.evo.R;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EvoAdapter extends ArrayAdapter<ua.sertox.evo.objects.EvoDocument> {
	
	public EvoAdapter(Context context, ArrayList<ua.sertox.evo.objects.EvoDocument> objects) {
		super(context, R.id.evo_name, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.custom_listview_row,
					parent, false);

			ViewHolder viewHolder = new ViewHolder();

			viewHolder.evoName = (TextView) convertView
					.findViewById(R.id.evo_name);
			viewHolder.evoDate = (TextView) convertView
					.findViewById(R.id.evo_date);

			convertView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();

		ua.sertox.evo.objects.EvoDocument evoDocument = getItem(position);
		
		holder.evoName.setText(evoDocument.getName());
		
		holder.evoDate.setText(DateFormat.format("dd MMMM, yyyy,  hh:mm",
				evoDocument.getCreateDate()));

		return convertView;
	}
	
	static class ViewHolder {
		public TextView evoName;
		public TextView evoDate;
	}
	


}
