package com.example.android.customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2,editText3;
    Button button;
    ListView listView;
    ArrayList<Emp> arrayList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<Emp>();
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = listView.getChildAt(position);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    Emp emp = arrayList.get(position);
                    Toast.makeText(MainActivity.this, emp.getEno() + "" + emp.getEname() + "" + emp.getEsal(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this,"PLEASE SELECT BOX", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eno = editText1.getText().toString();
                String ename = editText2.getText().toString();
                String esal = editText3.getText().toString();
                Emp emp = new Emp();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setEsal(esal);
                arrayList.add(emp);
                myAdapter.notifyDataSetChanged();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText1.requestFocus();


            }
        });
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Emp emp = arrayList.get(position);
            View v = getLayoutInflater().inflate(R.layout.row,null);
            TextView textView1 = (TextView) v.findViewById(R.id.textView1);
            TextView textView2 = (TextView) v.findViewById(R.id.textView2);
            TextView textView3 = (TextView) v.findViewById(R.id.textView3);
            textView1.setText(emp.getEno());
            textView2.setText(emp.getEname());
            textView3.setText(emp.getEsal());
            return  v;
            //return null;
        }
    }
}
