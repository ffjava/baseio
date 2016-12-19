package com.generallycloud.nio.container.jms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TextByteMessage extends TextMessage implements BytedMessage{

	private byte[]	array	;

	public TextByteMessage(String messageID, String queueName,String text, byte[] array) {
		super(messageID, queueName,text);
		this.array = array;
	}

	@Override
	public byte[] getByteArray() {
		return array;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(24)
			.append("{\"msgType\":3,\"msgID\":\"")
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

	@Override
	public int getMsgType() {
		return Message.TYPE_TEXT_BYTE;
	}
	
	public static void main(String[] args) {
		
		TextByteMessage message = new TextByteMessage("mid","qname","text",null);
		
		System.out.println(JSON.toJSON(message).toString());
		System.out.println(message.toString());
	}
}
