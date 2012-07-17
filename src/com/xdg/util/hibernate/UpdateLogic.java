package com.xdg.util.hibernate;

import org.hibernate.Session;

public interface UpdateLogic {
    void execute(Session session) throws Exception;
}
