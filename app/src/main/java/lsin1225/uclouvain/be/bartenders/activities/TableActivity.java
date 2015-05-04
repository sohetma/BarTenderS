package lsin1225.uclouvain.be.bartenders.activities;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Adapter;
import android.widget.TableLayout;
import android.widget.TextView;

import lsin1225.uclouvain.be.bartenders.R;

/**
 * Created by xavier on 4/05/15.
 */
abstract public class TableActivity extends Activity {

    protected void setTableAdapter(final Adapter adapter) {
        final TableLayout tableLayout = getTableView();
        for (int i = 0; i < adapter.getCount(); i++) {
            final View row = adapter.getView(i, null, tableLayout);
            tableLayout.addView(row);
            tableLayout.addView(getSeparatorView());

            final int position = i;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTableItemClick(tableLayout, adapter, row, position, row.getId());
                }
            });
        }
    }

    protected abstract void onTableItemClick(TableLayout t, Adapter adapter, View v, int position, long id);

    protected TableLayout getTableView() {
        return (TableLayout) findViewById(R.id.table);
    }

    protected View getSeparatorView() {
        TextView separator = new TextView(this);
        separator.setBackgroundColor(Color.parseColor("#80808080"));
        separator.setHeight(1);

        return separator;
    }
}
