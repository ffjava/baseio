package com.generallycloud.nio.front;

import com.generallycloud.nio.component.SocketChannel;
import com.generallycloud.nio.component.SocketSessionFactoryImpl;
import com.generallycloud.nio.component.UnsafeSocketSession;

public class FrontFacadeSocketSessionFactory extends SocketSessionFactoryImpl{

	@Override
	public UnsafeSocketSession newUnsafeSession(SocketChannel channel) {
		return new FrontFacadeSocketSessionImpl(channel, channel.getChannelID());
	}
	
}
