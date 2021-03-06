package edu.wpi.cs.wpisuitetng.janeway.gui.login;

import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class LoginRequestObserver implements RequestObserver {

	protected LoginController controller;

	public LoginRequestObserver(LoginController controller) {
		this.controller = controller;
	}

	@Override
	public void responseSuccess(IRequest iReq) {
		// cast observable to a Request
		Request request = (Request) iReq;

		// get the response from the request
		ResponseModel response = request.getResponse();

		// check the response code
		if (response.getStatusCode() == 200) {
			controller.loginSuccessful(response);
		}
		else { // login failed
			controller.loginFailed(response);
		}
	}

	@Override
	public void responseError(IRequest iReq) {
		// TODO Auto-generated method stub
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
	}
}
