
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySender extends HttpServlet {

    @Resource(mappedName = "myQueue")
    private Queue myQueue;

    @Resource(mappedName = "myQueueConnectionFactory")
    private ConnectionFactory queue;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String message = request.getParameter("name");
            sendJMSMessageToMyQueue(message);
            out.print("Your name was sent successfully as message.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void sendJMSMessageToMyQueue(String messageData) {
        try {
            Connection con = queue.createConnection();
            Session s = con.createSession();
            MessageProducer mp = s.createProducer(myQueue);
            TextMessage tm = s.createTextMessage();
            tm.setText(messageData);
            mp.send(tm);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
