package com.generallycloud.nio.container.jms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class TextMessage extends BasicMessage{
	
	private String text = null;

	public TextMessage(String messageID,String queueName,String text) {
		super(messageID,queueName);
		this.text = text;
	}

	public String getReadText() {
		return text;
	}

	@Override
	public int getMsgType() {
		return Message.TYPE_TEXT;
	}
	
	
	@Override
	public String toString() {
		return new StringBuilder(24)
			.append("{\"msgType\":2,\"msgID\":\"")
			.append(getMsgID())
			.append("\",\"queueName\":\"")
			.append(getQueueName())
			.append("\",\"timestamp\":")
			.append(getTimestamp())
			.append(",\"text\":\"")
			.append(getText0())
			.append("\"}")
			.toString();
	}
	
	protected String getText0(){
		if (text == null) {
			return "";
		}
		return text;
	}

	public static void main(String[] args) {
		
		
		
		TextMessage message = new TextMessage("mid","qname",null);
		
		System.out.println(JSON.toJSON(message).toString());
		System.out.println(message.toString());
	}
}
