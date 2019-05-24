package ua.sertox.evo.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ua.sertox.evo.R;
import ua.sertox.evo.objects.AppContext;

public class EvoList extends Activity {

    private ListView listviewTasks;
    private EditText txtSearch;
    private ArrayList<ua.sertox.evo.objects.EvoDocument> listDocuments;
    private Intent intent;
    private ua.sertox.evo.adapters.EvoAdapter evoAdapter;

    private static Comparator<ua.sertox.evo.objects.EvoDocument> comparator = ua.sertox.evo.objects.EvoListComparator
            .getDateComparator();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evo_list);

        listviewTasks = findViewById(R.id.listTasks);
        listviewTasks.setOnItemClickListener(new ListViewClickListener());
        listviewTasks.setEmptyView(findViewById(R.id.emptyView));
        // listviewTasks.setTextFilterEnabled(false);

        listDocuments = ((AppContext) getApplicationContext())
                .getListDocuments();

        txtSearch =  findViewById(R.id.txtSearch);
        txtSearch.addTextChangedListener(new TextChangeListener());

        getActionBar().setDisplayHomeAsUpEnabled(false);

        intent = new Intent(this, EvoDetails.class);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        sort();
        checkSearchActive();
    }

    private void checkSearchActive() {
        if (listDocuments.isEmpty()) {
            txtSearch.setEnabled(false);
        } else {
            txtSearch.setEnabled(true);
        }
    }

    private void sort() {

        Collections.sort(listDocuments, comparator);
        updateIndexes();

        evoAdapter = new ua.sertox.evo.adapters.EvoAdapter(this, listDocuments);
        listviewTasks.setAdapter(evoAdapter);

        evoAdapter.getFilter().filter(txtSearch.getText());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.evo_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.isChecked()) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_new_task: {

                Bundle bundle = new Bundle();
                bundle.putInt(AppContext.ACTION_TYPE, AppContext.ACTION_NEW_TASK);

                intent.putExtras(bundle);
                startActivity(intent);

                return true;
            }

            case R.id.menu_sort_name: {
                comparator = ua.sertox.evo.objects.EvoListComparator.getNameComparator();
                sort();
                return true;
            }

            case R.id.menu_sort_date: {
                comparator = ua.sertox.evo.objects.EvoListComparator.getDateComparator();
                sort();
                return true;
            }

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateIndexes() {
        int i = 0;
        for (ua.sertox.evo.objects.EvoDocument doc : listDocuments) {
            doc.setNumber(i++);
        }
    }

    public void clearSearch(View view) {
        txtSearch.setText("");
    }

    private class ListViewClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Bundle bundle = new Bundle();
            bundle.putInt(AppContext.ACTION_TYPE, AppContext.ACTION_UPDATE);
            bundle.putInt(AppContext.DOC_INDEX, ((ua.sertox.evo.objects.EvoDocument) parent
                    .getAdapter().getItem(position)).getNumber());

            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    private class TextChangeListener implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            evoAdapter.getFilter().filter(s);
        }

    }

}
