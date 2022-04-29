package MK_Nurtaj.attendance_app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Course> {

    private final Context context;
    private final ArrayList<Course> values;


    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<Course> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.course_list_item, parent, false);

        TextView crs_code = rowView.findViewById(R.id.crs_code);
        TextView crs_title = rowView.findViewById(R.id.crs_title);

        crs_code.setText(values.get(position).getCrs_code());
        crs_title.setText(values.get(position).getCrs_title());

        return rowView;
    }
}