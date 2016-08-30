package com.webLean.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webLean.weixin.common.util.SignUtil;

@Controller
@RequestMapping("/coreServlet")
public class CoreServlet extends HttpServlet
{
	
  private static final long serialVersionUID = 4440739483644821986L;
  
  //private @Autowired CoreService coreService;
  @RequestMapping(value="index",method=RequestMethod.GET)
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    String echostr = request.getParameter("echostr");

    PrintWriter out = response.getWriter();
    if (SignUtil.checkSignature(signature, timestamp, nonce)) {
      out.print(echostr);
    }
    out.close();
    out = null;
  }
  @RequestMapping(value="index",method=RequestMethod.POST)
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

   // String respXml = coreService.processRequest(request);

    PrintWriter out = response.getWriter();
   // out.print(respXml);
    out.close();
  }
}