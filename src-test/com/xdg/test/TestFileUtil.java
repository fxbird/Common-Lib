package com.xdg.test;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.xdg.util.FileUtil;
import junit.framework.TestCase;

import java.io.*;

public class TestFileUtil extends TestCase {
    public void testWritePart() throws IOException {
        FileInputStream is=new FileInputStream("f:/temp/data.txt");

//        FileUtil.writePart(new FileOutputStream("f:/temp/out.txt"),is,10,1,20);
    }
}
