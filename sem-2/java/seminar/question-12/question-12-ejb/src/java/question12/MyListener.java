package question12;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@JMSDestinationDefinition(name = "myTopic", interfaceName = "javax.jms.Topic", resourceAdapter = "jmsra", 
        destinationName = "myTopic")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myTopic")
    ,@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MyListener implements MessageListener {
    public MyListener() {}
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("Message Received :" + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
