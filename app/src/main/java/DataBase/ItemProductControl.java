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
        db = null;
        values = null;
        sp=null;
    }

    public ArrayList<itemProduct> getitemProductByCategory(int idCategory,
                                                           DataBaseHandler dh){
        ArrayList<itemProduct> products = new ArrayList<>();
        itemProduct product = new itemProduct();
        Category category = new Category();
        Store store = new Store();
        City city = new City();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "SELECT P." +
                DataBaseHandler.KEY_PRODUCT_ID + ", P." +
                DataBaseHandler.KEY_PRODUCT_TITLE + ", P."+
                DataBaseHandler.KEY_PRODUCT_IMAGE + ", S."+
                DataBaseHandler.KEY_CITY_NAME + ", CA."+
                DataBaseHandler.KEY_CATEGORY_ID + ", CA."+
                DataBaseHandler.KEY_STORE_ID + ", S." +
                DataBaseHandler.KEY_STORE_NAME + ", S." +
                DataBaseHandler.KEY_STORE_PHONE + ", S."+
                DataBaseHandler.KEY_STORE_THUMBNAIL + ", S."+
                DataBaseHandler.KEY_STORE_LAT + ", S."+
                DataBaseHandler.KEY_STORE_LNG + ", C."+
                DataBaseHandler.KEY_CITY_ID + ", C."+
                DataBaseHandler.KEY_CATEGORY_NAME + " FROM"+
                DataBaseHandler.TABLE_PRODUCT + "P INNER JOIN "+
                DataBaseHandler.TABLE_CATEGORY + "CA ON P."+
                DataBaseHandler.KEY_PRODUCT_CATEGORY + " = C."+
                DataBaseHandler.KEY_CATEGORY_ID + " INNER JOIN "+
                DataBaseHandler.TABLE_STOREPRODUCT  + "SP ON SP."+
                DataBaseHandler.KEY_SP_PRODUCT + " = P."+
                DataBaseHandler.KEY_PRODUCT_ID + "INNER JOIN "+
                DataBaseHandler.TABLE_STORE + "S ON S."+
                DataBaseHandler.KEY_STORE_ID + " = SP."+
                DataBaseHandler.KEY_SP_STORE + " INNER JOIN "+
                DataBaseHandler.TABLE_CITY + "C ON C."+
                DataBaseHandler.KEY_CITY_ID +" = S."+
                DataBaseHandler.KEY_STORE_CITY + " WHERE P."+
                DataBaseHandler.KEY_PRODUCT_CATEGORY + " = "+ idCategory;

        Cursor cursor= db.rawQuery(select, null );

        while (cursor.moveToNext()) {
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setImage(cursor.getInt(2));
            category.setId(cursor.getInt(3));
            category.setName(cursor.getString(4));
            store.setId(cursor.getInt(5));
            store.setName(cursor.getString(6));
            store.setPhone(cursor.getString(7));
            store.setThumbnail(cursor.getInt(8));
            store.setLatitude(cursor.getDouble(9));
            store.setLongitude(cursor.getDouble(10));
            city.setId(cursor.getInt(11));
            city.setName(cursor.getString(12));

            store.setCity(city);
            product.setStore(store);
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
