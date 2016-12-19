package com.generallycloud.nio.connector;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.generallycloud.nio.component.DatagramChannel;
import com.generallycloud.nio.component.DatagramChannelContext;
import com.generallycloud.nio.component.DatagramChannelSelectorLoop;
import com.generallycloud.nio.component.DatagramSession;
import com.generallycloud.nio.component.NioDatagramChannel;
import com.generallycloud.nio.component.SelectorLoop;
import com.generallycloud.nio.component.UnsafeDatagramSession;
import com.generallycloud.nio.protocol.DatagramPacket;

public final class DatagramChannelConnector extends AbstractChannelConnector {

	private DatagramChannelContext	context	= null;
	private UnsafeDatagramSession		session	= null;

	public DatagramChannelConnector(DatagramChannelContext context) {
		this.context = context;
	}

	@Override
	public DatagramSession connect() throws IOException {

		this.session = null;
		
		this.service();

		return getSession();
	}

	@Override
	protected void connect(InetSocketAddress socketAddress) throws IOException {

		((java.nio.channels.DatagramChannel) this.selectableChannel).connect(socketAddress);

		initSelectorLoops();

		@SuppressWarnings("resource")
		DatagramChannel channel = new NioDatagramChannel((DatagramChannelSelectorLoop) selectorLoops[0],
				(java.nio.channels.DatagramChannel) selectableChannel, socketAddress);
		
		this.session = channel.getSession();
	}
	
	@Override
	protected boolean canSafeClose() {
		return session == null || !session.inSelectorLoop();
	}

	@Override
	protected void fireSessionOpend() {
		session.fireOpend();
	}

	@Override
	public DatagramChannelContext getContext() {
		return context;
	}

	@Override
	public DatagramSession getSession() {
		return session;
	}

	@Override
	protected void initselectableChannel() throws IOException {

		this.selectableChannel = java.nio.channels.DatagramChannel.open();

		this.selectableChannel.configureBlocking(false);
	}

	@Override
	protected SelectorLoop newSelectorLoop(SelectorLoop[] selectorLoops) throws IOException {
		return new DatagramChannelSelectorLoop(this, selectorLoops);
	}

	public void sendDatagramPacket(DatagramPacket packet) throws IOException {
		session.sendPacket(packet);
	}

}
