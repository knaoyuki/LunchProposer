package naoyuki.kawamoto.lunch_proposer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView pLunch = (TextView) findViewById(R.id.TxtProposeLunch);
    pLunch.setText(propseLunch());
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

  public String propseLunch() {

    String lunch = "";
    ArrayList<String> lunchList = new ArrayList<String>();

    CheckBox chkLight = (CheckBox)findViewById(R.id.ChkLight);
    CheckBox chkNormal = (CheckBox)findViewById(R.id.ChkNormal);
    CheckBox chkHeavy = (CheckBox)findViewById(R.id.ChkHeavy);

    String light = chkLight.isChecked() ? "1" : "0";
    String normal = chkNormal.isChecked() ? "1" : "0";
    String heavy = chkHeavy.isChecked() ? "1" : "0";

    LunchMaster lm = new LunchMaster(this);
    Cursor csr = null;
    SQLiteDatabase db = null;
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ");
    sql.append(" name ");
    sql.append("FROM ");
    sql.append("  m_lunch ");
    sql.append("WHERE ");
    sql.append("  type_light  = ? ");
    sql.append("  type_normal = ? ");
    sql.append("  type_heavy  = ? ");
    sql.append(";");
    db = lm.getReadableDatabase();
    csr = db.rawQuery(sql.toString(), new String[] { light, normal, heavy });
    while (csr.moveToNext()) {
      lunch = csr.getString(csr.getColumnIndex("name"));
      lunchList.add(lunch);
    }
    Collections.shuffle(lunchList);
    lunch = lunchList.get(0);
    return lunch;
  }
}

