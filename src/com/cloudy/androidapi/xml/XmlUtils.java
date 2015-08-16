package com.cloudy.androidapi.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlUtils {
	/**
	 * SAX 解析
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlSAX(InputStream inputStream){
		ArrayList<PersonBean> arrayList;
		//生成一个SAX工厂对象
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		try {
			//创建解析器
			SAXParser saxParser = parserFactory.newSAXParser();
			//创建事件模型
			SaxContentHandler contentHandler = new SaxContentHandler();
			//开始解析
			saxParser.parse(inputStream, contentHandler);
			//关闭输入流
			inputStream.close();
			arrayList = contentHandler.getPerson();
			return arrayList;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * DOM解析
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlDOM(InputStream inputStream){
		ArrayList<PersonBean> list = new ArrayList<PersonBean>();
		//创建一个解析器的工厂类对象
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			//创建一个DocumentBuilder
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			//加载文档数据模型
			Document document = documentBuilder.parse(inputStream);
			//获取到所有的文档元素
			Element element = document.getDocumentElement();
			//拿到所有的person节点
			NodeList nodeList = element.getElementsByTagName("person");
			for(int i=0;i< nodeList.getLength();i++){
				//初始化一个PersonBean
				PersonBean bean = new PersonBean();
				Element personNode = (Element) nodeList.item(i);
				//获取到person节点的Id 属性值
				bean.id = Integer.parseInt(personNode.getAttribute("id"));
				//获取person节点下的所有子节点(包括标签之间的空白节点和name/age元素)
				NodeList childNodes = personNode.getChildNodes();
				for(int j = 0;j < childNodes.getLength();j++){
					//拿到person下面的每个子节点
					Node node = childNodes.item(j);
					if(node.getNodeType() == Node.ELEMENT_NODE){//是否是元素,而不是空节点
						Element childNode = (Element) node;
						System.out.println("childNode.getNodeName()="+childNode.getNodeName());
						if(childNode.getNodeName().equals("name")){
							System.out.println("childNode.getFirstChild().getNodeValue()=" + childNode.getFirstChild().getNodeValue());
							bean.name = childNode.getFirstChild().getNodeValue();
						}else if(childNode.getNodeName().equals("age")){
							System.out.println("childNode.getFirstChild().getNodeValue()=" + childNode.getFirstChild().getNodeValue());
							bean.age = Integer.parseInt(childNode.getFirstChild().getNodeValue());
						}
					}
				}
				list.add(bean);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * PULL 解析
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlPULL(InputStream inputStream){
		ArrayList<PersonBean> list = new ArrayList<PersonBean>();
		//新生成一个XmlPillParser解析器
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {
			//设置输入流编码
			xmlPullParser.setInput(inputStream, "UTF-8");
			int eventType = xmlPullParser.getEventType();
			PersonBean bean = null;
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT://文档开始
					System.out.println("START_DOCUMENT");
					break;
				case XmlPullParser.START_TAG://开始元素标签
					String name = xmlPullParser.getName();
					System.out.println("START_TAG---------->>tagName="+ name);
					if(name.equalsIgnoreCase("person")){
						//只能在这里初始化PersonBean
						bean = new PersonBean();
						System.out.println("id=" + xmlPullParser.getAttributeValue(null,"id"));
						//设置bean的Id
						bean.id = Integer.parseInt(xmlPullParser.getAttributeValue(null,"id"));
					}else if(bean != null){
						if(name.equalsIgnoreCase("name")){
								bean.name = xmlPullParser.nextText();
						}else if(name.equalsIgnoreCase("age")){
								bean.age = Integer.parseInt(xmlPullParser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG://结束标签
					System.out.println("END_TAG------->>tagName=" + xmlPullParser.getName());
					if(xmlPullParser.getName().equalsIgnoreCase("person")&& bean != null){
						list.add(bean);
						bean = null;
					}
					break;
				}
				//解析下一个元素,并拿到事件类型
				xmlPullParser.next();
				eventType = xmlPullParser.getEventType();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	

}
















