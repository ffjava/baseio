package com.generallycloud.nio.container.http11.example;

import com.generallycloud.nio.codec.http11.HttpSession;
import com.generallycloud.nio.codec.http11.future.HttpReadFuture;
import com.generallycloud.nio.container.http11.service.HttpFutureAcceptorService;

public class TestUploadServlet extends HttpFutureAcceptorService {

	@Override
	protected void doAccept(HttpSession session, HttpReadFuture future) throws Exception {
		
		String res;

		if (future.hasBodyContent()) {

			res = "yes server already accept your message :) "+future.getRequestParams()+" </BR><PRE style='font-size: 18px;color: #FF9800;'>" + new String(future.getBodyContent())+"</PRE>";
		} else {
			res = "yes server already accept your message :) " + future.getRequestParams();
		}

		future.setResponseHeader("Content-Type", "text/html");
		
		future.write(res);
		session.flush(future);
	}

}
