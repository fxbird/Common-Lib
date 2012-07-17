package com.xdg.util.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HbnDeleteUtil {
    public static void clearEntity(Session session, String clsName) {
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from " + clsName).executeUpdate();
        tx.commit();
    }
}
