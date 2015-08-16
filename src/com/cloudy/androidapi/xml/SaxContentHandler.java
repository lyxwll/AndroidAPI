package com.cloudy.androidapi.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxContentHandler extends DefaultHandler{
	ArrayList<PersonBean> list = new ArrayList<PersonBean>();
	
	private PersonBean currentPersonBean;//当前解析的person存放在这个对象里
	private String tagName = null;//tag标签
	
	/**
	 * 解析完成后,调用此方法可以得到解析后的数据
	 * @return
	 */
	public ArrayList<PersonBean> getPerson(){
		return list;
	}
	
	/**
	 * 文档开始事件
	 * 接收文档开始的通知,当遇到文档的开头时,调用此方法,可以在其中做一些与处理的工作
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	
	/**
	 * 接收元素开始的通知,当读到一个开始标签的时候,会触发此方法,其中nameSpaceURI表示元素的命名空间
	 * LocalName表示元素的本地名称:qName表示元素的限定名;atts 表示元素的属性集合
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement");
		System.out.println("localName=" + localName);
		System.out.println("qName=" + qName);
		if(localName.equals("person")){
			currentPersonBean = new PersonBean();
			//获取到id
			currentPersonBean.id = Integer.parseInt(attributes.getValue("id"));
		}
		//把本地名称保存
		 this.tagName = localName;
	}
	/**
	 * 接收字符数据的通知,该方法用来处理在XML文件中读到的内容,第一个参数用于存放文件的内容
	 * 后面两个参数读到的字符串在这个数组中的起始位置和长度,使用new String(ch, start, length)就可以获取内容
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
	 * 接收文档结尾的通知,在遇到结束标签的时候,调用此方法.其中Uri表示元素的命名空间
	 * LocalName表示元素的本地名称(不带前缀);qName表示元素的限定名(带前缀)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("endElement");
		System.out.println("localName=" + localName);
		System.out.println("qName=" + qName);
		if(localName.equals("person")){
			list.add(currentPersonBean);//将当前person加入到列表中
			currentPersonBean = null;//等待下次存储
		}
		//把本地名称设置为空
		tagName = null;
	}
	
	/**
	 * 文档结束事件
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	

}
