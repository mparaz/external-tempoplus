package hk.com.novare.tempoplus.bmnmanager.sendMail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/sendMail")
@SessionAttributes({"userEmployeeId", "userEmail"})
public class SendMailController {

	@Autowired
	SendEmailService sendEmailService;

	@RequestMapping(value = "/send")
	public @ResponseBody
	void sendMail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String hrEmail = (String)modelMap.get("userEmail");
		final String department = request.getParameter("selectedDepartment");
		final String msgBody = request.getParameter("msgBody");
		
		System.out.println("MsgBody: "+msgBody);
		final JSONObject jsonObject = new JSONObject();
		try {

			sendEmailService.sendMail(department, msgBody);

			jsonObject.put("status", "OK");

			System.out.println("Done");
		} catch (JSONException e) {
			// Ignore
		}
	
		
		final String jsonOutput = jsonObject.toString();

		response.setContentType("application/json");
		response.getWriter().println(jsonOutput);

	}


}
