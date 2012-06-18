package com.xdg.test;

import com.xdg.util.ListUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListUtil extends TestCase {

    public void testSplit5() {
        List list = Arrays.asList(1, 2, 3, 4, 5);
        List split1 = ListUtil.split(list, 1);
        List split2 = ListUtil.split(list, 2);
        List split3 = ListUtil.split(list, 3);
        List split4 = ListUtil.split(list, 4);
        List split5 = ListUtil.split(list, 5);

        assertEquals(3, split2.size());
        assertEquals(2, split3.size());
        assertEquals(5, split1.size());
        assertEquals(1, split5.size());
        assertEquals(2, split4.size());
    }

    public void testSplit6() {
        List list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List split1 = ListUtil.split(list, 1);
        List split2 = ListUtil.split(list, 2);
        List split3 = ListUtil.split(list, 3);
        List split4 = ListUtil.split(list, 4);
        List split5 = ListUtil.split(list, 5);
        List split6 = ListUtil.split(list, 6);

        assertEquals(3, split2.size());
        assertEquals(2, split3.size());
        assertEquals(1, split6.size());
        assertEquals(6, split1.size());
        assertEquals(2, split5.size());
        assertEquals(2, split4.size());

        System.out.println();
    }


}
