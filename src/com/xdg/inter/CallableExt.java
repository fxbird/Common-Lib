package com.xdg.inter;

import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public interface CallableExt{
    void run(List material) throws Exception;
}
