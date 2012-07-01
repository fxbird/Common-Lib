package com.xdg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
	private final static Log log= LogFactory.getLog(XmlUtil.class);
	public static List<String> selectAttributes(String xmlPath, String xpath, String attr)
			throws FileNotFoundException, DocumentException {

		SAXReader reader = new SAXReader();

		Document doc = reader.read(new FileInputStream(new File(xmlPath)));

		return selectAttributes(doc, xpath, attr);

	}

	public static List<String> selectAttributes(Node node, String xpath, String attr) {
		ArrayList<String> attrs = new ArrayList<String>();
		List nodes = node.selectNodes(xpath);
		for (Object obj : nodes) {
			Element elem = (Element) obj;
			attrs.add(elem.attribute(attr).getValue());
		}

		return attrs;
	}

	public static List<Element> selectElements(Node node, String xpath) {
		ArrayList<Element> elements = (ArrayList<Element>) node.selectNodes(xpath);

		return elements;
	}

	public static Document parseXmlFromStr(String content) throws DocumentException {
		SAXReader reader = new SAXReader();
		return reader.read(new StringReader(content)).getDocument();
	}

	public static Element selectElement(Node node, String xpath) {
		return (Element) node.selectSingleNode(xpath);
	}

	public static String getAttrValue(Element element, String attr) {
		return element.attribute(attr).getValue();
	}

	public static String getAttrValue(Element element, String attr, String deft) {
		Attribute attribute = element.attribute(attr);
		return attribute == null ? deft : attribute.getValue();
	}

	public static Document parseXmlFromFile(String path) throws FileNotFoundException, DocumentException {
		SAXReader reader = new SAXReader();
		return reader.read(new FileReader(path)).getDocument();
	}

    public static String format(Node node) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        StringWriter str=new StringWriter();
        XMLWriter writer = new XMLWriter(str, format);
        writer.write(node);
        writer.flush();

        return str.toString();

    }

    public static String getElementValue(Node node,String xpath){
        Element element=selectElement(node,xpath);
            if (element==null){
                    return null;
                }else {
                    return element.getText();
                }
        }

    public static List<String> getElementValues(Node node,String xpath){
        List<String> values=new ArrayList<String>();
        for(Element element:selectElements(node,xpath)){
            values.add(element.getText());
        }

        return values;
    }

}
