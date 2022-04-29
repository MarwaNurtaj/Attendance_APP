package MK_Nurtaj.attendance_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView grid;

    private String URL = "https://muthosoft.com/univ/attendance/report.php";
    private ArrayList<Course> course_list;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);
        String[] keys = {"my_courses", "sid"};
        String[] values = {"true", "2019160255"};
        httpRequest(keys, values);

        grid.setOnItemClickListener((adapterView, view, i, l) -> {
            Course course = course_list.get(i);
            Intent intent = new Intent(MainActivity.this, Attendance.class);
            intent.putExtra("crs_code", course.crs_code);
            startActivity(intent);
        });
    }

    private void httpRequest(final String keys[], final String values[]) {
        course_list = new ArrayList<>();
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    for (int i = 0; i < keys.length; i++) {
                        System.out.println("att In For Loop" + keys[i] + " " + values[i]);
                        params.add(new BasicNameValuePair(keys[i], values[i]));
                    }
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST", params);
                    System.out.println("att OutSide For Loop: " + data);
                    System.out.println("att OutSide For Loop: " + data.split(",")[0]);
                    System.out.println("att OutSide For Loop: " + data.split(",")[1]);
                    Course course = new Course(data.split(",")[0], data.split(",")[1]);

                    course_list.add(course);

                    return data;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                if (data != null) {
                    try {
                        System.out.println("Final data Html Body: " + data);
                        adapter = new CustomAdapter(MainActivity.this, course_list);
                        grid.setAdapter(adapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }.execute();
    }
}