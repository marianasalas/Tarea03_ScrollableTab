package DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion13_scrollabletab.Beans.Category;
import com.iteso.sesion13_scrollabletab.Beans.City;
import com.iteso.sesion13_scrollabletab.Beans.Store;
import com.iteso.sesion13_scrollabletab.Beans.itemProduct;

import java.util.ArrayList;

/**
 * Created by Mariana Salas on 20/03/2018.
 */

public class ItemProductControl {


    public void addItemProduct(itemProduct itemProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_ID, itemProduct.getCode());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, itemProduct.getCategory().getId());

         // Inserting Row
        db.insert(DataBaseHandler.TABLE_PRODUCT, null, values);

        ContentValues sp = new ContentValues();
        sp.put(DataBaseHandler.KEY_SP_PRODUCT, itemProduct.getCode());
        sp.put(DataBaseHandler.KEY_SP_STORE, itemProduct.getStore().getId());
        db.insert(DataBaseHandler.TABLE_STOREPRODUCT, null, sp);

        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null; values = null;
    }

    public ArrayList<itemProduct> getitemProductByCategory(int idCategory,
                                                           DataBaseHandler dh){
        ArrayList<itemProduct> products = new ArrayList<>();
        itemProduct product = new itemProduct();
        String select = "SELECT P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_CATEGORY + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " P, "
                + DataBaseHandler.TABLE_CATEGORY + " C WHERE P."
                + DataBaseHandler.KEY_PRODUCT_CATEGORY
                + " = C." + DataBaseHandler.KEY_CATEGORY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor= db.rawQuery(select, null );
        while (cursor.moveToNext()) {
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setImage(cursor.getInt(3));
            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            product.setCategory(category);
            products.add(product);
        }
        try{
            cursor.close(); //SIEMPRE CERRAR PRIMERO EL CURSOR
            db.close();
        }catch (Exception e){

        }
        db =null;
        cursor=null;
        return  products;

    }

}
