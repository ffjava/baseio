package com.generallycloud.nio.component;

import com.generallycloud.nio.common.Logger;
import com.generallycloud.nio.common.LoggerFactory;

public class LoggerSocketSEListener implements SocketSessionEventListener{

	private Logger logger = LoggerFactory.getLogger(LoggerSocketSEListener.class);
	
	@Override
	public void sessionOpened(SocketSession session) {
		logger.info("session opened:{}",session);
	}

	@Override
	public void sessionClosed(SocketSession session) {
		logger.info("session closed:{}",session);
	}

	@Override
	public void sessionIdled(SocketSession session,long lastIdleTime, long currentTime) {
//		logger.debug("session Idled:{}",session);
	}
}
