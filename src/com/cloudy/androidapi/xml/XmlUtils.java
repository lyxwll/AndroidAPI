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
	 * SAX ����
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlSAX(InputStream inputStream){
		ArrayList<PersonBean> arrayList;
		//����һ��SAX��������
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		try {
			//����������
			SAXParser saxParser = parserFactory.newSAXParser();
			//�����¼�ģ��
			SaxContentHandler contentHandler = new SaxContentHandler();
			//��ʼ����
			saxParser.parse(inputStream, contentHandler);
			//�ر�������
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
	 * DOM����
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlDOM(InputStream inputStream){
		ArrayList<PersonBean> list = new ArrayList<PersonBean>();
		//����һ���������Ĺ��������
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			//����һ��DocumentBuilder
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			//�����ĵ�����ģ��
			Document document = documentBuilder.parse(inputStream);
			//��ȡ�����е��ĵ�Ԫ��
			Element element = document.getDocumentElement();
			//�õ����е�person�ڵ�
			NodeList nodeList = element.getElementsByTagName("person");
			for(int i=0;i< nodeList.getLength();i++){
				//��ʼ��һ��PersonBean
				PersonBean bean = new PersonBean();
				Element personNode = (Element) nodeList.item(i);
				//��ȡ��person�ڵ��Id ����ֵ
				bean.id = Integer.parseInt(personNode.getAttribute("id"));
				//��ȡperson�ڵ��µ������ӽڵ�(������ǩ֮��Ŀհ׽ڵ��name/ageԪ��)
				NodeList childNodes = personNode.getChildNodes();
				for(int j = 0;j < childNodes.getLength();j++){
					//�õ�person�����ÿ���ӽڵ�
					Node node = childNodes.item(j);
					if(node.getNodeType() == Node.ELEMENT_NODE){//�Ƿ���Ԫ��,�����ǿսڵ�
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
	 * PULL ����
	 * @param inputStream
	 * @return
	 */
	public static ArrayList<PersonBean> readFromXmlPULL(InputStream inputStream){
		ArrayList<PersonBean> list = new ArrayList<PersonBean>();
		//������һ��XmlPillParser������
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {
			//��������������
			xmlPullParser.setInput(inputStream, "UTF-8");
			int eventType = xmlPullParser.getEventType();
			PersonBean bean = null;
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT://�ĵ���ʼ
					System.out.println("START_DOCUMENT");
					break;
				case XmlPullParser.START_TAG://��ʼԪ�ر�ǩ
					String name = xmlPullParser.getName();
					System.out.println("START_TAG---------->>tagName="+ name);
					if(name.equalsIgnoreCase("person")){
						//ֻ���������ʼ��PersonBean
						bean = new PersonBean();
						System.out.println("id=" + xmlPullParser.getAttributeValue(null,"id"));
						//����bean��Id
						bean.id = Integer.parseInt(xmlPullParser.getAttributeValue(null,"id"));
					}else if(bean != null){
						if(name.equalsIgnoreCase("name")){
								bean.name = xmlPullParser.nextText();
						}else if(name.equalsIgnoreCase("age")){
								bean.age = Integer.parseInt(xmlPullParser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG://������ǩ
					System.out.println("END_TAG------->>tagName=" + xmlPullParser.getName());
					if(xmlPullParser.getName().equalsIgnoreCase("person")&& bean != null){
						list.add(bean);
						bean = null;
					}
					break;
				}
				//������һ��Ԫ��,���õ��¼�����
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
















