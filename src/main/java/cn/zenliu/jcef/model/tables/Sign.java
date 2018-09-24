/*
 * This file is generated by jOOQ.
 */
package cn.zenliu.jcef.model.tables;


import cn.zenliu.jcef.model.Indexes;
import cn.zenliu.jcef.model.Keys;
import cn.zenliu.jcef.model.Public;
import cn.zenliu.jcef.model.tables.records.SignRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sign extends TableImpl<SignRecord> {

    private static final long serialVersionUID = 1083258101;

    /**
     * The reference instance of <code>public.sign</code>
     */
    public static final Sign SIGN = new Sign();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SignRecord> getRecordType() {
        return SignRecord.class;
    }

    /**
     * The column <code>public.sign.idx</code>.
     */
    public final TableField<SignRecord, Integer> IDX = createField("idx", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.sign.name</code>.
     */
    public final TableField<SignRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.sign.conf_id</code>.
     */
    public final TableField<SignRecord, Integer> CONF_ID = createField("conf_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.sign.depart</code>.
     */
    public final TableField<SignRecord, String> DEPART = createField("depart", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.sign.device_id</code>.
     */
    public final TableField<SignRecord, String> DEVICE_ID = createField("device_id", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.sign.card_id</code>.
     */
    public final TableField<SignRecord, String> CARD_ID = createField("card_id", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.sign.card_type</code>.
     */
    public final TableField<SignRecord, String> CARD_TYPE = createField("card_type", org.jooq.impl.SQLDataType.VARCHAR(2), this, "");

    /**
     * The column <code>public.sign.check_in</code>.
     */
    public final TableField<SignRecord, LocalDateTime> CHECK_IN = createField("check_in", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>public.sign.check_out</code>.
     */
    public final TableField<SignRecord, LocalDateTime> CHECK_OUT = createField("check_out", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>public.sign</code> table reference
     */
    public Sign() {
        this(DSL.name("sign"), null);
    }

    /**
     * Create an aliased <code>public.sign</code> table reference
     */
    public Sign(String alias) {
        this(DSL.name(alias), SIGN);
    }

    /**
     * Create an aliased <code>public.sign</code> table reference
     */
    public Sign(Name alias) {
        this(alias, SIGN);
    }

    private Sign(Name alias, Table<SignRecord> aliased) {
        this(alias, aliased, null);
    }

    private Sign(Name alias, Table<SignRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Sign(Table<O> child, ForeignKey<O, SignRecord> key) {
        super(child, key, SIGN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<SignRecord, Integer> getIdentity() {
        return Keys.IDENTITY_SIGN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SignRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SignRecord>> getKeys() {
        return Arrays.<UniqueKey<SignRecord>>asList(Keys.CONSTRAINT_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sign as(String alias) {
        return new Sign(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sign as(Name alias) {
        return new Sign(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Sign rename(String name) {
        return new Sign(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sign rename(Name name) {
        return new Sign(name, null);
    }
}