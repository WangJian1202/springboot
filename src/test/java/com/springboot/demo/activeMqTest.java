package com.springboot.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.IOException;

public class activeMqTest {
	@Test
	public void test(){
		try {
			for (int i = 0; i <12 ; i++) {
				testActivemq();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testActivemq() throws Exception{
		//创建连接 使用tcp://127.680.25.128
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

		//使用connectionfactory 创建connection对象												  
		Connection connection = connectionFactory.createConnection();

		//开启连接
		connection.start();
		//使用connection创建一个session对象
		//第一个参数：是否开启事务。true：开启事务，第二个参数忽略。
		//第二个参数：当第一个参数为false时，才有意义。消息的应答模式。1、自动应答2、手动应答。一般是自动应答。
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用session创建一个destination对象
		Queue queue = session.createQueue("Queue.rcif_rcif_online");
		//创建一个生产者对象
		MessageProducer producer = session.createProducer(queue);
		//创建一个Textmessage对象
		TextMessage textMessage = session.createTextMessage("activemq,test潘天福");
		//使用productor发送消息
		producer.send(textMessage);
		//关闭
		producer.close();
		session.close();
		connection.close();
	}
/**
 * 消费者
 * @throws JMSException 
 * @throws IOException 
 */
	@Test
	public void testConsumer() throws JMSException, IOException{
		//创建一个connectionFactory对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		//使用connectionfactory 创建一个connection对象
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建一个session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建一个destination对象
		Queue queue = session.createQueue("Queue.rcif_rcif_online");
		//创建一个消费者对象
		MessageConsumer consumer = session.createConsumer(queue);
		// 消费者：接收消息。
		//创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//将信息转换成Textmessage
				TextMessage textMessage = (TextMessage) message;
				//取消息
				try {
					String text = textMessage.getText();
					//打印消息内容
					int a =10/0;
					message.acknowledge();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//系统等待
		System.out.println("等待接收消息");
		System.in.read();
		System.out.println("接收到消息");
		System.out.println("系统关闭");
		// 第九步：关闭资源
		connection.close();
		session.close();
		consumer.close();
	}


	/**
	 * destination : topic
	 * @throws Exception 
	 */
	@Test
	public void testActivemqTopic() throws Exception{
		//创建connectionfactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//获取连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用session创建destination对象
		Topic topic = session.createTopic("test-topic");
		//根据topic创建producer对象
		MessageProducer createProducer = session.createProducer(topic);
		//创建TextMessage对象
		TextMessage message = session.createTextMessage("发送一个话题消息");
		//使用producer发送消息
		createProducer.send(message);
		//关闭
		session.close();
		connection.close();
		createProducer.close();
	}


	/**
	 * 订阅/发布  消费者;
	 * @throws Exception 
	 */
	@Test
	public void testTopicConcumer() throws Exception{
		//创建connectionfactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//创建connection
		Connection connection = connectionFactory.createConnection();
		//持久化订阅需要设置id
		connection.setClientID("topic-test-ClienId1");
		//开启连接
		connection.start();
		//创建session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建一个destination对象
		Topic topic = session.createTopic("test-topic");
		//创建一个持久化consumer对象
		  TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "clin-concumer-name21");
		//创建监听器
		topicSubscriber.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
					try {
						System.out.println(textMessage.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		System.out.println("系统等待1");
		System.in.read();
		System.out.println("系统关闭");
		//关闭
		connection.close();
		session.close();
		topicSubscriber.close();
	}
	
	/**
	 * spring-activemq测试
	 */
	@Test
	public void testSpringActivemq(){
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//从容器中获得模板对象
		JmsTemplate template = applicationContext.getBean(JmsTemplate.class);

		//从容器中获得destination对象
		Destination destination= (Destination) applicationContext.getBean("queueDestination");
		//使用template对象发送消息
		template.send(destination,new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				//根据session创建TextMesage对象
				Message createMessage = session.createTextMessage("spring-activemq 测试");
				return createMessage;
			}
		});
	}


	
	
}
