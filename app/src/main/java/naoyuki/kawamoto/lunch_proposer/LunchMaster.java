package naoyuki.kawamoto.lunch_proposer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by n-kawamoto on 2016/01/16.
 */
public class LunchMaster extends SQLiteOpenHelper{

  // DBバージョン
  private static final int DB_VERSION = 1;

  // コンストラクタ
  public LunchMaster(Context context) {
    super(context, "LunchProposer.sqlite3", null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    StringBuffer sql = new StringBuffer();
    sql.append("CREATE TABLE m_lunch ( ");
    sql.append("    id          INT   PRIMARY KEY");
    sql.append("  , name        TEXT  NOT NULL ");
    sql.append("  , type_light  INT   NOT NULL ");
    sql.append("  , type_normal INT   NOT NULL ");
    sql.append("  , type_heavy  INT   NOT NULL ");
    sql.append(");");
    db.execSQL(sql.toString());
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }


}
