package com.xdg;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Test {
	public static void main(String[] args) throws Exception {
		SAXReader reader=new SAXReader();
		String file = "E:/workspace2/eclipse3.6.2/hangameadmin-game2/src/conf/applicationContext-dn.xml";
		Document doc=reader.read(new File(file));
		Element beans= (Element)doc.selectSingleNode("beans");
		
		Element bean=beans.addElement("bean");
		bean.addAttribute("class", "dummy.class");
		bean.addAttribute("id", "dummyID");
		
		XMLWriter writer=new XMLWriter(new FileWriter(new File(file)));
		writer.write(doc);
		
		writer.close();
		

	}
}
