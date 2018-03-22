package DataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iteso.sesion13_scrollabletab.Beans.Category;

import java.util.ArrayList;

/**
 * Created by Mariana Salas on 20/03/2018.
 */

public class CategoryControl {

    public ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        String select = "SELECT C." + DataBaseHandler.KEY_CATEGORY_ID
                +", C." + DataBaseHandler.KEY_CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY + " C";

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor= db.rawQuery(select, null );
        while(cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }

        try{
            cursor.close(); //SIEMPRE CERRAR PRIMERO EL CURSOR
            db.close();
        }catch (Exception e){

        }
        db =null;
        cursor=null;
        return  categories;
    }
}
