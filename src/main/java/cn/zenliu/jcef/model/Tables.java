/*
 * This file is generated by jOOQ.
 */
package cn.zenliu.jcef.model;


import cn.zenliu.jcef.model.tables.Conference;
import cn.zenliu.jcef.model.tables.Sign;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.conference</code>.
     */
    public static final Conference CONFERENCE = cn.zenliu.jcef.model.tables.Conference.CONFERENCE;

    /**
     * The table <code>public.sign</code>.
     */
    public static final Sign SIGN = cn.zenliu.jcef.model.tables.Sign.SIGN;
}
