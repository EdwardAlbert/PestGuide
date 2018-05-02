package cn.edu.bupt.lab805.pestguide.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.bupt.lab805.pestguide.entity.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Void> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Username = new Property(0, String.class, "username", false, "USERNAME");
        public final static Property Realname = new Property(1, String.class, "realname", false, "REALNAME");
        public final static Property Mobile = new Property(2, String.class, "mobile", false, "MOBILE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Manager = new Property(4, Boolean.class, "manager", false, "MANAGER");
        public final static Property Hasaudit = new Property(5, Boolean.class, "hasaudit", false, "HASAUDIT");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"USERNAME\" TEXT," + // 0: username
                "\"REALNAME\" TEXT," + // 1: realname
                "\"MOBILE\" TEXT," + // 2: mobile
                "\"TITLE\" TEXT," + // 3: title
                "\"MANAGER\" INTEGER," + // 4: manager
                "\"HASAUDIT\" INTEGER);"); // 5: hasaudit
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String realname = entity.getRealname();
        if (realname != null) {
            stmt.bindString(2, realname);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(3, mobile);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        Boolean manager = entity.getManager();
        if (manager != null) {
            stmt.bindLong(5, manager ? 1L: 0L);
        }
 
        Boolean hasaudit = entity.getHasaudit();
        if (hasaudit != null) {
            stmt.bindLong(6, hasaudit ? 1L: 0L);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String realname = entity.getRealname();
        if (realname != null) {
            stmt.bindString(2, realname);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(3, mobile);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        Boolean manager = entity.getManager();
        if (manager != null) {
            stmt.bindLong(5, manager ? 1L: 0L);
        }
 
        Boolean hasaudit = entity.getHasaudit();
        if (hasaudit != null) {
            stmt.bindLong(6, hasaudit ? 1L: 0L);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // username
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // realname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mobile
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // manager
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0 // hasaudit
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setUsername(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setRealname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMobile(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setManager(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setHasaudit(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Void updateKeyAfterInsert(User entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(User entity) {
        return null;
    }

    @Override
    public boolean hasKey(User entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}