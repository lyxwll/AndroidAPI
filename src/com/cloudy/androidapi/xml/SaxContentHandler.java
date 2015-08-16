package com.cloudy.androidapi.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxContentHandler extends DefaultHandler{
	ArrayList<PersonBean> list = new ArrayList<PersonBean>();
	
	private PersonBean currentPersonBean;//��ǰ������person��������������
	private String tagName = null;//tag��ǩ
	
	/**
	 * ������ɺ�,���ô˷������Եõ������������
	 * @return
	 */
	public ArrayList<PersonBean> getPerson(){
		return list;
	}
	
	/**
	 * �ĵ���ʼ�¼�
	 * �����ĵ���ʼ��֪ͨ,�������ĵ��Ŀ�ͷʱ,���ô˷���,������������һЩ�봦��Ĺ���
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	
	/**
	 * ����Ԫ�ؿ�ʼ��֪ͨ,������һ����ʼ��ǩ��ʱ��,�ᴥ���˷���,����nameSpaceURI��ʾԪ�ص������ռ�
	 * LocalName��ʾԪ�صı�������:qName��ʾԪ�ص��޶���;atts ��ʾԪ�ص����Լ���
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement");
		System.out.println("localName=" + localName);
		System.out.println("qName=" + qName);
		if(localName.equals("person")){
			currentPersonBean = new PersonBean();
			//��ȡ��id
			currentPersonBean.id = Integer.parseInt(attributes.getValue("id"));
		}
		//�ѱ������Ʊ���
		 this.tagName = localName;
	}
	/**
	 * �����ַ����ݵ�֪ͨ,�÷�������������XML�ļ��ж���������,��һ���������ڴ���ļ�������
	 * �������������������ַ�������������е���ʼλ�úͳ���,ʹ��new String(ch, start, length)�Ϳ��Ի�ȡ����
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("characters");
		System.out.println("data=" + new String(ch, start, length));
		if(this.tagName != null){
			String str = new String(ch, start, length);
			if(tagName.equals("name")){
				currentPersonBean.name = str;
			}
			if(tagName.equals("age")){
				currentPersonBean.age = Integer.parseInt(str);
			}
		}
	}
	/**
	 * �����ĵ���β��֪ͨ,������������ǩ��ʱ��,���ô˷���.����Uri��ʾԪ�ص������ռ�
	 * LocalName��ʾԪ�صı�������(����ǰ׺);qName��ʾԪ�ص��޶���(��ǰ׺)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("endElement");
		System.out.println("localName=" + localName);
		System.out.println("qName=" + qName);
		if(localName.equals("person")){
			list.add(currentPersonBean);//����ǰperson���뵽�б���
			currentPersonBean = null;//�ȴ��´δ洢
		}
		//�ѱ�����������Ϊ��
		tagName = null;
	}
	
	/**
	 * �ĵ������¼�
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	

}
