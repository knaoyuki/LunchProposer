package naoyuki.kawamoto.lunch_proposer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    LunchMaster lm = new LunchMaster(this);
    Cursor csr = null;
    SQLiteDatabase db = null;
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ");
    sql.append(" * ");
    sql.append("FROM ");
    sql.append("  m_lunch ");
    sql.append(";");
    db = lm.getReadableDatabase();
    csr = db.rawQuery(sql.toString(), null);
    while (csr.moveToNext()) {
      int id = csr.getInt(csr.getColumnIndex("id"));
      String lunch = csr.getString(csr.getColumnIndex("name"));
      int light = csr.getInt(csr.getColumnIndex("type_light"));
      int normal = csr.getInt(csr.getColumnIndex("type_normal"));
      int heavy = csr.getInt(csr.getColumnIndex("type_heavy"));
      Toast.makeText(this, id + "," + lunch + "," + light + "," + normal + "," + heavy, Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_lunch) {
      Intent intent = new Intent(this, LunchRegister.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
