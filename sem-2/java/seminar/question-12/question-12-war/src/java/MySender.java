
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySender extends HttpServlet {

    @Resource(mappedName = "myTopic")
    private Topic myTopic;

    @Resource(mappedName = "myTopicConnectionFactory")
    private ConnectionFactory Topic;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String message = request.getParameter("message");
            sendJMSMessageToMyTopic(message);
            out.print("Your name was sent successfully as message.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void sendJMSMessageToMyTopic(String messageData) {
        try {
            Connection con = Topic.createConnection();
            Session s = con.createSession();
            MessageProducer mp = s.createProducer(myTopic);
            TextMessage tm = s.createTextMessage();
            tm.setText(messageData);
            mp.send(tm);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
