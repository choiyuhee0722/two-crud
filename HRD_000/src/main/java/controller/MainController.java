package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
		return "index";
	}
}
