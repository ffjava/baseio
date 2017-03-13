/*
 * Copyright 2015-2017 GenerallyCloud.com
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package com.generallycloud.test.nio.jms;

import com.generallycloud.baseio.common.Encoding;
import com.generallycloud.baseio.common.SharedBundle;
import com.generallycloud.baseio.common.ThreadUtil;
import com.generallycloud.baseio.connector.SocketChannelConnector;
import com.generallycloud.baseio.container.FixedSession;
import com.generallycloud.baseio.container.SimpleIoEventHandle;
import com.generallycloud.baseio.container.jms.Message;
import com.generallycloud.baseio.container.jms.TextByteMessage;
import com.generallycloud.baseio.container.jms.client.MessageConsumer;
import com.generallycloud.baseio.container.jms.client.OnMessage;
import com.generallycloud.baseio.container.jms.client.impl.DefaultMessageConsumer;
import com.generallycloud.test.nio.common.IoConnectorUtil;

public class TestListenerByteMessage {

	public static void main(String[] args) throws Exception {
		
		SharedBundle.instance().loadAllProperties("nio");
		
		SimpleIoEventHandle eventHandle = new SimpleIoEventHandle();

		SocketChannelConnector connector = IoConnectorUtil.getTCPConnector(eventHandle);

		FixedSession session = new FixedSession(connector.connect());
		
		session.login("admin", "admin100");
		
		MessageConsumer consumer = new DefaultMessageConsumer(session);

		final long old = System.currentTimeMillis();

		consumer.receive(new OnMessage() {
			
			@Override
			public void onReceive(Message message) {
				System.out.println(message);
				if (message.getMsgType() == Message.TYPE_TEXT_BYTE) {
					TextByteMessage _Message = (TextByteMessage) message;
					System.out.println(new String(_Message.getByteArray(),Encoding.UTF8));
				}
				
				System.out.println("Time:" + (System.currentTimeMillis() - old));
			}
		});
		
		ThreadUtil.sleep(3000);

		connector.close();

	}

}
