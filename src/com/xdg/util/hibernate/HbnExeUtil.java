package com.xdg.util.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HbnExeUtil {
    private final static Log log = LogFactory.getLog(HbnExeUtil.class);

    public static void executeUpdateLogic(Session session, UpdateLogic updateLogic) {
        Transaction tx = session.beginTransaction();
        try {
            updateLogic.execute(session);
        } catch (Exception e) {
            log.error(e);
        }
        tx.commit();

    }

    public static void executeUpdateHQL(Session session, final String hql) {
        Transaction tx = session.beginTransaction();
        try {
            new UpdateLogic() {
                @Override
                public void execute(Session session) throws Exception {
                    session.createQuery(hql).executeUpdate();
                }
            }.execute(session);
        } catch (Exception e) {
            log.error(e);
        }
        tx.commit();

    }

    public static void executeUpdateSQL(Session session, final String sql) {
        Transaction tx = session.beginTransaction();
        try {
            new UpdateLogic() {
                @Override
                public void execute(Session session) throws Exception {
                    session.createSQLQuery(sql).executeUpdate();
                }
            }.execute(session);
        } catch (Exception e) {
            log.error(e);
        }
        tx.commit();

    }


    public static List executeQueryLogic(Session session, QueryLogic queryLogic) {
        Transaction tx = session.beginTransaction();
        List rst = queryLogic.execute(session);
        tx.commit();
        return rst;
    }

    public static List executeQueryHQL(Session session, final String hql) {
        Transaction tx = session.beginTransaction();
        List list = new QueryLogic() {
            @Override
            public List execute(Session session) {
                return session.createQuery(hql).list();
            }
        }.execute(session);
        tx.commit();
        return list;
    }


}
