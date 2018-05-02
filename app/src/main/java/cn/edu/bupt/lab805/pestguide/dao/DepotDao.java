package cn.edu.bupt.lab805.pestguide.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.bupt.lab805.pestguide.entity.Depot;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DEPOT".
*/
public class DepotDao extends AbstractDao<Depot, Void> {

    public static final String TABLENAME = "DEPOT";

    /**
     * Properties of entity Depot.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Lcbm = new Property(0, String.class, "lcbm", false, "LCBM");
        public final static Property Binname = new Property(1, String.class, "binname", false, "BINNAME");
        public final static Property Orientation = new Property(2, String.class, "orientation", false, "ORIENTATION");
        public final static Property Granarynum = new Property(3, String.class, "granarynum", false, "GRANARYNUM");
        public final static Property Typebin = new Property(4, String.class, "typebin", false, "TYPEBIN");
        public final static Property Capacity = new Property(5, Integer.class, "capacity", false, "CAPACITY");
        public final static Property Structureofbody = new Property(6, String.class, "structureofbody", false, "STRUCTUREOFBODY");
        public final static Property Structureofroof = new Property(7, String.class, "structureofroof", false, "STRUCTUREOFROOF");
        public final static Property Designcapacity = new Property(8, Integer.class, "designcapacity", false, "DESIGNCAPACITY");
        public final static Property Designgrainheapheight = new Property(9, Float.class, "designgrainheapheight", false, "DESIGNGRAINHEAPHEIGHT");
        public final static Property Longth = new Property(10, Integer.class, "longth", false, "LONGTH");
        public final static Property Width = new Property(11, Integer.class, "width", false, "WIDTH");
        public final static Property Height = new Property(12, Integer.class, "height", false, "HEIGHT");
        public final static Property Circulatedevice = new Property(13, String.class, "circulatedevice", false, "CIRCULATEDEVICE");
        public final static Property Circulatefan = new Property(14, String.class, "circulatefan", false, "CIRCULATEFAN");
        public final static Property Fanway = new Property(15, String.class, "fanway", false, "FANWAY");
        public final static Property Elsedevice = new Property(16, String.class, "elsedevice", false, "ELSEDEVICE");
        public final static Property Contract = new Property(17, String.class, "contract", false, "CONTRACT");
        public final static Property Phone = new Property(18, String.class, "phone", false, "PHONE");
        public final static Property Note = new Property(19, String.class, "note", false, "NOTE");
        public final static Property Modifer = new Property(20, String.class, "modifer", false, "MODIFER");
        public final static Property Modifydate = new Property(21, String.class, "modifydate", false, "MODIFYDATE");
    }


    public DepotDao(DaoConfig config) {
        super(config);
    }
    
    public DepotDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEPOT\" (" + //
                "\"LCBM\" TEXT," + // 0: lcbm
                "\"BINNAME\" TEXT," + // 1: binname
                "\"ORIENTATION\" TEXT," + // 2: orientation
                "\"GRANARYNUM\" TEXT," + // 3: granarynum
                "\"TYPEBIN\" TEXT," + // 4: typebin
                "\"CAPACITY\" INTEGER," + // 5: capacity
                "\"STRUCTUREOFBODY\" TEXT," + // 6: structureofbody
                "\"STRUCTUREOFROOF\" TEXT," + // 7: structureofroof
                "\"DESIGNCAPACITY\" INTEGER," + // 8: designcapacity
                "\"DESIGNGRAINHEAPHEIGHT\" REAL," + // 9: designgrainheapheight
                "\"LONGTH\" INTEGER," + // 10: longth
                "\"WIDTH\" INTEGER," + // 11: width
                "\"HEIGHT\" INTEGER," + // 12: height
                "\"CIRCULATEDEVICE\" TEXT," + // 13: circulatedevice
                "\"CIRCULATEFAN\" TEXT," + // 14: circulatefan
                "\"FANWAY\" TEXT," + // 15: fanway
                "\"ELSEDEVICE\" TEXT," + // 16: elsedevice
                "\"CONTRACT\" TEXT," + // 17: contract
                "\"PHONE\" TEXT," + // 18: phone
                "\"NOTE\" TEXT," + // 19: note
                "\"MODIFER\" TEXT," + // 20: modifer
                "\"MODIFYDATE\" TEXT);"); // 21: modifydate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEPOT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Depot entity) {
        stmt.clearBindings();
 
        String lcbm = entity.getLcbm();
        if (lcbm != null) {
            stmt.bindString(1, lcbm);
        }
 
        String binname = entity.getBinname();
        if (binname != null) {
            stmt.bindString(2, binname);
        }
 
        String orientation = entity.getOrientation();
        if (orientation != null) {
            stmt.bindString(3, orientation);
        }
 
        String granarynum = entity.getGranarynum();
        if (granarynum != null) {
            stmt.bindString(4, granarynum);
        }
 
        String typebin = entity.getTypebin();
        if (typebin != null) {
            stmt.bindString(5, typebin);
        }
 
        Integer capacity = entity.getCapacity();
        if (capacity != null) {
            stmt.bindLong(6, capacity);
        }
 
        String structureofbody = entity.getStructureofbody();
        if (structureofbody != null) {
            stmt.bindString(7, structureofbody);
        }
 
        String structureofroof = entity.getStructureofroof();
        if (structureofroof != null) {
            stmt.bindString(8, structureofroof);
        }
 
        Integer designcapacity = entity.getDesigncapacity();
        if (designcapacity != null) {
            stmt.bindLong(9, designcapacity);
        }
 
        Float designgrainheapheight = entity.getDesigngrainheapheight();
        if (designgrainheapheight != null) {
            stmt.bindDouble(10, designgrainheapheight);
        }
 
        Integer longth = entity.getLongth();
        if (longth != null) {
            stmt.bindLong(11, longth);
        }
 
        Integer width = entity.getWidth();
        if (width != null) {
            stmt.bindLong(12, width);
        }
 
        Integer height = entity.getHeight();
        if (height != null) {
            stmt.bindLong(13, height);
        }
 
        String circulatedevice = entity.getCirculatedevice();
        if (circulatedevice != null) {
            stmt.bindString(14, circulatedevice);
        }
 
        String circulatefan = entity.getCirculatefan();
        if (circulatefan != null) {
            stmt.bindString(15, circulatefan);
        }
 
        String fanway = entity.getFanway();
        if (fanway != null) {
            stmt.bindString(16, fanway);
        }
 
        String elsedevice = entity.getElsedevice();
        if (elsedevice != null) {
            stmt.bindString(17, elsedevice);
        }
 
        String contract = entity.getContract();
        if (contract != null) {
            stmt.bindString(18, contract);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(19, phone);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(20, note);
        }
 
        String modifer = entity.getModifer();
        if (modifer != null) {
            stmt.bindString(21, modifer);
        }
 
        String modifydate = entity.getModifydate();
        if (modifydate != null) {
            stmt.bindString(22, modifydate);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Depot entity) {
        stmt.clearBindings();
 
        String lcbm = entity.getLcbm();
        if (lcbm != null) {
            stmt.bindString(1, lcbm);
        }
 
        String binname = entity.getBinname();
        if (binname != null) {
            stmt.bindString(2, binname);
        }
 
        String orientation = entity.getOrientation();
        if (orientation != null) {
            stmt.bindString(3, orientation);
        }
 
        String granarynum = entity.getGranarynum();
        if (granarynum != null) {
            stmt.bindString(4, granarynum);
        }
 
        String typebin = entity.getTypebin();
        if (typebin != null) {
            stmt.bindString(5, typebin);
        }
 
        Integer capacity = entity.getCapacity();
        if (capacity != null) {
            stmt.bindLong(6, capacity);
        }
 
        String structureofbody = entity.getStructureofbody();
        if (structureofbody != null) {
            stmt.bindString(7, structureofbody);
        }
 
        String structureofroof = entity.getStructureofroof();
        if (structureofroof != null) {
            stmt.bindString(8, structureofroof);
        }
 
        Integer designcapacity = entity.getDesigncapacity();
        if (designcapacity != null) {
            stmt.bindLong(9, designcapacity);
        }
 
        Float designgrainheapheight = entity.getDesigngrainheapheight();
        if (designgrainheapheight != null) {
            stmt.bindDouble(10, designgrainheapheight);
        }
 
        Integer longth = entity.getLongth();
        if (longth != null) {
            stmt.bindLong(11, longth);
        }
 
        Integer width = entity.getWidth();
        if (width != null) {
            stmt.bindLong(12, width);
        }
 
        Integer height = entity.getHeight();
        if (height != null) {
            stmt.bindLong(13, height);
        }
 
        String circulatedevice = entity.getCirculatedevice();
        if (circulatedevice != null) {
            stmt.bindString(14, circulatedevice);
        }
 
        String circulatefan = entity.getCirculatefan();
        if (circulatefan != null) {
            stmt.bindString(15, circulatefan);
        }
 
        String fanway = entity.getFanway();
        if (fanway != null) {
            stmt.bindString(16, fanway);
        }
 
        String elsedevice = entity.getElsedevice();
        if (elsedevice != null) {
            stmt.bindString(17, elsedevice);
        }
 
        String contract = entity.getContract();
        if (contract != null) {
            stmt.bindString(18, contract);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(19, phone);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(20, note);
        }
 
        String modifer = entity.getModifer();
        if (modifer != null) {
            stmt.bindString(21, modifer);
        }
 
        String modifydate = entity.getModifydate();
        if (modifydate != null) {
            stmt.bindString(22, modifydate);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Depot readEntity(Cursor cursor, int offset) {
        Depot entity = new Depot( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // lcbm
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // binname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // orientation
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // granarynum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // typebin
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // capacity
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // structureofbody
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // structureofroof
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // designcapacity
            cursor.isNull(offset + 9) ? null : cursor.getFloat(offset + 9), // designgrainheapheight
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // longth
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // width
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // height
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // circulatedevice
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // circulatefan
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // fanway
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // elsedevice
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // contract
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // phone
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // note
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // modifer
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21) // modifydate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Depot entity, int offset) {
        entity.setLcbm(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBinname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setOrientation(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGranarynum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTypebin(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCapacity(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setStructureofbody(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setStructureofroof(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDesigncapacity(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setDesigngrainheapheight(cursor.isNull(offset + 9) ? null : cursor.getFloat(offset + 9));
        entity.setLongth(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setWidth(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setHeight(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCirculatedevice(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCirculatefan(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setFanway(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setElsedevice(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setContract(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setPhone(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setNote(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setModifer(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setModifydate(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Depot entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Depot entity) {
        return null;
    }

    @Override
    public boolean hasKey(Depot entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}