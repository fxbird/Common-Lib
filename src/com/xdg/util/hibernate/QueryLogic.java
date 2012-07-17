package com.xdg.util.hibernate;

import org.hibernate.Session;

import java.util.List;

public interface QueryLogic {
    List execute(Session session);
}
