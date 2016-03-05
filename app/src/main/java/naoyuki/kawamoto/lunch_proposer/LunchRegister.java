package naoyuki.kawamoto.lunch_proposer;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class LunchRegister extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lunch_register);

    Button btnRegister = (Button)findViewById(R.id.BtnRegister);
    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        register();
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_lunch_register, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }


    return super.onOptionsItemSelected(item);
  }

  public void register() {
    EditText txtLunchName = (EditText)findViewById(R.id.TxtLunchName);
    CheckBox chkLight = (CheckBox)findViewById(R.id.ChkLight);
    CheckBox chkNormal = (CheckBox)findViewById(R.id.ChkNormal);
    CheckBox chkHeavy = (CheckBox)findViewById(R.id.ChkHeavy);
    String lunchname = txtLunchName.getText().toString();
    int light = chkLight.isChecked() ? 1 : 0;
    int normal = chkNormal.isChecked() ? 1 : 0;
    int heavy = chkHeavy.isChecked() ? 1 : 0;

    LunchMaster lm = new LunchMaster(this);

    SQLiteDatabase db = null;
    SQLiteStatement stmt = null;
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO m_lunch ( ");
    sql.append("    name ");
    sql.append("  , type_light ");
    sql.append("  , type_normal ");
    sql.append("  , type_heavy ");
    sql.append(") VALUES ( ");
    sql.append("    ? ");
    sql.append("  , ? ");
    sql.append("  , ? ");
    sql.append("  , ? ");
    sql.append(");");
    db = lm.getWritableDatabase();
    stmt = db.compileStatement(sql.toString());
    stmt.bindString(1, lunchname);
    stmt.bindLong(2, light);
    stmt.bindLong(3, normal);
    stmt.bindLong(4, heavy);
    stmt.executeInsert();
    stmt.close();
    db.close();
    finish();
  }
}
