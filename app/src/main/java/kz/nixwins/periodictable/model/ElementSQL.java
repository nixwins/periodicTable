package kz.nixwins.periodictable.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nixwins on 11/23/16.
 */

public class ElementSQL {

    private Database        database;
    private SQLiteDatabase  sqLiteDatabase;
    private Cursor          cursor;

    public ElementSQL(Context context){

        database = new Database(context);

        sqLiteDatabase = database.getWritableDatabase();
    }

    public List<Element> getAll(){

        List<Element> elements = new ArrayList<>();

        cursor = sqLiteDatabase.query(ElementConst.TABLE_NAME, null, null, null, null, null, null);

        int nameIdx         = cursor.getColumnIndex(ElementConst.NAME);
        int symbolIdx       = cursor.getColumnIndex(ElementConst.SYMBOL);
        int latinNameIdx    = cursor.getColumnIndex(ElementConst.LATIN_NAME);
        int atomicNumIdx    = cursor.getColumnIndex(ElementConst.ATOMIC_NUMBER);
        int atomicWeightIdx = cursor.getColumnIndex(ElementConst.ATOMIC_WEIGHT);
        int electronConfIdx = cursor.getColumnIndex(ElementConst.ELECTRON_CONFIGURATION);
        int engergyLvlIdx   = cursor.getColumnIndex(ElementConst.ELECTRON_ENERGY_LEVEL);
        int energyType      = cursor.getColumnIndex(ElementConst.ELECTRON_ENERGY_TYPE);
        int period          = cursor.getColumnIndex(ElementConst.PERIOD);
        int row             = cursor.getColumnIndex(ElementConst.ROW);
        int group           = cursor.getColumnIndex(ElementConst.GROUP);
        int subgroup        = cursor.getColumnIndex(ElementConst.SUB_GROUP);
        int oxide           = cursor.getColumnIndex(ElementConst.OXIDE);
        int vh              = cursor.getColumnIndex(ElementConst.VOLATILE_HYDROGENID);
        int elementType     = cursor.getColumnIndex(ElementConst.ELEMENT_TYPE);
        int electronFormula = cursor.getColumnIndex(ElementConst.ELECTRON_FORMULA);
        int valenceIdx      = cursor.getColumnIndex(ElementConst.VALENCE);



        while (cursor.moveToNext()){

            elements.add(new ElementBuilder()
                    .name(cursor.getString(nameIdx))
                    .latinName(cursor.getString(latinNameIdx))
                    .symbol(cursor.getString(symbolIdx))
                    .atomicNumber(cursor.getInt(atomicNumIdx))
                    .atomicWeight(cursor.getDouble(atomicWeightIdx))
                    .electronConfiguration(cursor.getString(electronConfIdx))
                    .electronEnergyLevel(cursor.getString(engergyLvlIdx))
                    .electronEnergyType(cursor.getString(energyType))
                    .period(cursor.getInt(period))
                    .row(cursor.getInt(row))
                    .group(cursor.getString(group))
                    .subGroup(cursor.getString(subgroup))
                    .oxide(cursor.getString(oxide))
                    .volatileHydrogenid(cursor.getString(vh))
                    .elementType(cursor.getString(elementType))
                    .electronFormula(cursor.getString(electronFormula))
                    .valence(cursor.getString(valenceIdx))
                    .build()
            );
        }
       /// sqLiteDatabase.close();

        return elements;
    }

    public Element getRandomRow(){

        cursor  = sqLiteDatabase.query(ElementConst.TABLE_NAME, new String[] {
                        ElementConst.SYMBOL,
                        ElementConst.ATOMIC_NUMBER,
                        ElementConst.ATOMIC_WEIGHT,
                        ElementConst.LATIN_NAME,
                        ElementConst.NAME,
                        ElementConst.ELECTRON_ENERGY_TYPE},
                null, null, null, null, "RANDOM()", "1");

        Element element = null;

        while (cursor.moveToNext()){

            element = new ElementBuilder()
                    .name(cursor.getString(cursor.getColumnIndex(ElementConst.NAME)))
                    .latinName(cursor.getString(cursor.getColumnIndex(ElementConst.LATIN_NAME)))
                    .symbol(cursor.getString(cursor.getColumnIndex(ElementConst.SYMBOL)))
                    .atomicNumber(cursor.getInt(cursor.getColumnIndex(ElementConst.ATOMIC_NUMBER)))
                    .atomicWeight(cursor.getDouble(cursor.getColumnIndex(ElementConst.ATOMIC_WEIGHT)))
                    .electronEnergyType(cursor.getString(cursor.getColumnIndex(ElementConst.ELECTRON_ENERGY_TYPE)))
                    .build();
        }

        //sqLiteDatabase.close();

        return element;
    }


}
