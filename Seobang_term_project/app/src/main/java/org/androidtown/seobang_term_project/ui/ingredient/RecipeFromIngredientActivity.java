package org.androidtown.seobang_term_project.ui.ingredient;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.androidtown.seobang_term_project.R;
import org.androidtown.seobang_term_project.utils.QuickSort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecipeFromIngredientActivity extends AppCompatActivity {
    public static final String ROOT_DIR = "/data/data/org.androidtown.seobang_term_project/databases/";
    public static final String DB_Name = "test_ingredient.db";
    public SQLiteDatabase db;
    public Cursor cursor;
    ProductDBHelper mHelper;

    String[] recipeList = new String[2000];
    String[] tempList = new String[2000];

    int recipeLength = 0;
    int cnt = 1;
    int tempLength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_from_ingredient);

        setDB(this);
        mHelper = new ProductDBHelper(getApplicationContext());
        db = mHelper.getWritableDatabase();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView textView = findViewById(R.id.ingredientSelected);

        String received = bundle.getString("result");
        String[] ingredient = received.split(",");

        String temp = "";

        for (int i = 0; i < ingredient.length; i++)
            Log.e("RecipeFromIngredient", "\"" + ingredient[i] + "\"");

        int tempNum = 0;

        for (int i = 0; i < ingredient.length; i++) {
            cursor = db.rawQuery("SELECT count(recipe_code) FROM recipe_ingredient_info WHERE ingredient_type_name=\"주재료\" and ingredient_name=\"" + ingredient[tempNum] + "\"", null);
            tempNum++;
            cursor.moveToNext();
            temp += cursor.getString(0) + "\n\n";
            cursor = db.rawQuery("SELECT recipe_code FROM recipe_ingredient_info WHERE ingredient_type_name=\"주재료\" and ingredient_name=\"" + ingredient[i] + "\"", null); //쿼리문

            while (cursor.moveToNext()) {
                tempList[tempLength] = cursor.getString(0);
                tempLength++;
            }
        }
        QuickSort.sort(tempList, 0, tempLength - 1);

//        Log.e("select",String.valueOf(temp));
//        Log.e("testsetsetsetset",String.valueOf(recipeLength));
//
//        for (int i = 0; i < tempList.length; i++)
//            Log.e("test", tempList[i] + "\n");

        for (int i = 0; i < tempLength; i++) {
            if (i + 1 == tempLength)
                break;
            else {
                if (tempList[i].equals(tempList[i + 1]))
                    cnt++;
                else {
                    recipeList[recipeLength] = tempList[i] + "." + String.valueOf(cnt);
                    cnt = 1;
                    recipeLength++;
                }
            }
        }

        for (int i = 0; i < recipeLength; i++) {
            for (int j = 0; j < recipeLength - 1 - i; j++) {
                if (Integer.parseInt(recipeList[j].substring(recipeList[j].indexOf(".") + 1)) < Integer.parseInt(recipeList[j + 1].substring(recipeList[j + 1].indexOf(".") + 1))) {
                    String a = recipeList[j];
                    recipeList[j] = recipeList[j + 1];
                    recipeList[j + 1] = a;
                }
            }
        }

//        for (int i = 0; i < recipeLength; i++)
//            Log.e("testtest", recipeList[i] + "\n");

        for (int i = 0; i < recipeLength; i++)
            textView.append(recipeList[i].substring(0,recipeList[i].indexOf(".")) + " - " + recipeList[i].substring(recipeList[i].indexOf(".") + 1) + "번\n");
    }

    public static void setDB(Context ctx) {
        File folder = new File(ROOT_DIR);
        if (folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets();
        File outfile = new File(ROOT_DIR + DB_Name);
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open(DB_Name, AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } else {
            }
        } catch (IOException e) {
        }
    }

    class ProductDBHelper extends SQLiteOpenHelper {  //새로 생성한 adapter 속성은 SQLiteOpenHelper이다.
        public ProductDBHelper(Context context) {
            super(context, DB_Name, null, 1);    // db명과 버전만 정의 한다.
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }

}
