package naoyuki.kawamoto.lunch_proposer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LunchList extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lunch_list);
    ArrayAdapter<String> lunchList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    LunchMaster lm = new LunchMaster(this);
    Cursor csr = null;
    SQLiteDatabase db = null;
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ");
    sql.append(" name ");
    sql.append("FROM ");
    sql.append("  m_lunch ");
    sql.append(";");
    db = lm.getReadableDatabase();
    csr = db.rawQuery(sql.toString(), null);
    while (csr.moveToNext()) {
      lunchList.add(csr.getString(csr.getColumnIndex("name")));
    }
    ListView ListLunch = (ListView)findViewById(R.id.ListLunch);
    ListLunch.setAdapter(lunchList);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }
}
