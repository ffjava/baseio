package com.generallycloud.nio.acceptor;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

import com.generallycloud.nio.common.LifeCycleUtil;
import com.generallycloud.nio.component.BaseContext;
import com.generallycloud.nio.component.SelectorLoop;
import com.generallycloud.nio.component.concurrent.EventLoopThread;
import com.generallycloud.nio.configuration.ServerConfiguration;

public final class SocketChannelAcceptor extends AbstractChannelAcceptor {

	private ServerSocket		serverSocket;
	private SelectorLoop[]		selectorLoops;
	private EventLoopThread[]	selectorLoopThreads;
	
	public SocketChannelAcceptor(BaseContext context) {
		super(context);
	}

	protected void bind(BaseContext context, InetSocketAddress socketAddress) throws IOException {

		// 打开服务器套接字通道
		this.selectableChannel = ServerSocketChannel.open();
		// 服务器配置为非阻塞
		this.selectableChannel.configureBlocking(false);
		// 检索与此通道关联的服务器套接字
		this.serverSocket = ((ServerSocketChannel) selectableChannel).socket();
		try {
			// 进行服务的绑定
			this.serverSocket.bind(socketAddress, 50);
		} catch (BindException e) {
			throw new BindException(e.getMessage() + " at " + socketAddress.getPort());
		}

		ServerConfiguration configuration = context.getServerConfiguration();

		int core_size = configuration.getSERVER_CORE_SIZE();

		this.selectorLoops = new SelectorLoop[core_size];
		
		this.selectorLoopThreads = new EventLoopThread[core_size];

		for (int i = 0; i < core_size; i++) {
			
			selectorLoops[i] = new ServerSocketChannelSelectorLoop(context,selectorLoops, selectableChannel);
		}
		
		for (int i = 0; i < core_size; i++) {
			selectorLoops[i].startup();
		}

		for (int i = 0; i < core_size; i++) {

			selectorLoopThreads[i] = new EventLoopThread(selectorLoops[i], getServiceDescription(i));
			
			selectorLoops[i].setMonitor(selectorLoopThreads[i].getMonitor());

			selectorLoopThreads[i].startup();
		}
		
	}

	public String getServiceDescription(int i) {
		return "tcp-io-process-" + i;
	}

	public InetSocketAddress getServerSocketAddress() {
		return (InetSocketAddress) serverSocket.getLocalSocketAddress();
	}

	protected void unbind(BaseContext context) {

		ServerConfiguration configuration = context.getServerConfiguration();

		int core_size = configuration.getSERVER_CORE_SIZE();

		for (int i = 0; i < core_size; i++) {

			LifeCycleUtil.stop(selectorLoopThreads[i]);
		}
	}

}
