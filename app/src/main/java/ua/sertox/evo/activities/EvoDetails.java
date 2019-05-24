package ua.sertox.evo.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import ua.sertox.evo.R;
import ua.sertox.evo.objects.AppContext;

public class EvoDetails extends Activity {

    public static final int RESULT_SAVE = 100;
    public static final int RESULT_DELETE = 101;

    private static final int NAME_LENGTH = 20;

    private EditText txtEvoDetails;
    private ua.sertox.evo.objects.EvoDocument evoDocument;

    private ArrayList<ua.sertox.evo.objects.EvoDocument> listDocuments;

    private int actionType;
    private int docIndex;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evo_details);

        txtEvoDetails = findViewById(R.id.txtEvoDetails);

        listDocuments = ((AppContext) getApplicationContext())
                .getListDocuments();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        actionType = getIntent().getExtras().getInt(AppContext.ACTION_TYPE);

        prepareDocument(actionType);
    }

    private void prepareDocument(int actionType) {
        switch (actionType) {
            case AppContext.ACTION_NEW_TASK:
                evoDocument = new ua.sertox.evo.objects.EvoDocument();
                break;

            case AppContext.ACTION_UPDATE:
                docIndex = getIntent().getExtras().getInt(AppContext.DOC_INDEX);
                evoDocument = listDocuments.get(docIndex);
                txtEvoDetails.setText(evoDocument.getContent());
                break;

            default:
                break;
        }
    }

    private void saveDocument() {
        evoDocument.setName(getDocumentName());

        if (actionType == AppContext.ACTION_UPDATE) {

            if (txtEvoDetails.getText().toString().trim().equals(evoDocument.getContent())) {
                finish();
                return;
            }
        } else if (actionType == AppContext.ACTION_NEW_TASK) {
            listDocuments.add(evoDocument);
        }

        evoDocument.setCreateDate(new Date());
        evoDocument.setContent(txtEvoDetails.getText().toString().trim());

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.evo_details, menu);
        return true;
    }

    private String getDocumentName() {
        StringBuilder sb = new StringBuilder(txtEvoDetails.getText());

        if (sb.length() > NAME_LENGTH) {
            sb.delete(NAME_LENGTH, sb.length()).append("...");
        }

        String tmpName = sb.toString().trim().split("\n")[0];

        String name = (tmpName.length() > 0) ? tmpName : getResources()
                .getString(R.string.new_document);

        return name;
    }

	@SuppressLint("NewApi")
	private void deleteDocument(ua.sertox.evo.objects.EvoDocument evoDocument) {
		if (actionType == AppContext.ACTION_UPDATE) {
			listDocuments.remove(docIndex);
		}

		finish();
	}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {

                if (txtEvoDetails.getText().toString().trim().length() == 0) {
                    finish();
                } else {
                    saveDocument();
                }

                return true;
            }

            case R.id.save: {

                saveDocument();

                return true;
            }

            case R.id.delete: {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.confirm_delete);

                builder.setPositiveButton(R.string.delete,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteDocument(evoDocument);

                            }
                        });
                builder.setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
