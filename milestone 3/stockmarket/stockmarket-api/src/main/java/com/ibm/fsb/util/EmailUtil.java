package com.ibm.fsb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class EmailUtil {
	
    private static final Log log = LogFactory.getLog(EmailUtil.class);
	
    @Value("${email.Hosts}")
	private String mailHosts;
	
    @Value("${email.Username}")
	private String mailUsername;
	
    @Value("${email.Password}")
	private String mailPassword;
	
	@Autowired
	protected MessageSource messageSource;
	
    public static final String MAIL_SERVER_HOSTS_NOT_DEFINED = "Mail server host not defined.";
	
	@PostConstruct
	public void postConstruct() {
		
	}

	private enum DeliveredState {
		INITIAL, MESSAGE_DELIVERED, MESSAGE_NOT_DELIVERED, MESSAGE_PARTIALLY_DELIVERED,
	}

	private static class DeliveredStateFuture {
		private DeliveredState state = DeliveredState.INITIAL;
		synchronized void waitForReady() throws InterruptedException {
			if (state == DeliveredState.INITIAL) {
				wait();
			}
		}
		@SuppressWarnings("unused")
		synchronized DeliveredState getState() {
			return state;
		}
		synchronized void setState(DeliveredState newState) {
			state = newState;
			notifyAll();
		}
	}

	public void sendMail(String fromMail, String toMail, String ccMail, String subject, String content, MultipartFile[] attachments)
			throws MessagingException, IOException {
		log.info("fromMail:" + (fromMail != null ? fromMail : "null"));
		log.info("toMail:" + (toMail != null ? toMail : "null"));
		log.info("ccMail:" + (ccMail != null ? ccMail : "null"));
		Multipart multipart = new MimeMultipart();
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(content, "text/html; charset=utf-8");
		multipart.addBodyPart(contentPart);

		if (attachments != null) {
			for (MultipartFile attachment : attachments) {
				BodyPart messageBodyPart = new MimeBodyPart();
				DataSource source = new AttachDataSource(attachment.getBytes(), attachment.getOriginalFilename());
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new String(attachment.getOriginalFilename().getBytes("utf-8"), "utf-8"));
				multipart.addBodyPart(messageBodyPart);
			}
		}

		String[] hostArr = mailHosts.split(",", -1);
		String[] usernameArr = mailUsername.split(",", -1);
		String[] passwordArr = mailPassword.split(",", -1);
		boolean isSuccess = false;

		for (int i = 0; i < hostArr.length; i++) {
			Properties props = System.getProperties();
			String host = hostArr[i];
			if (StringUtils.isEmpty(host)) {
				continue;
			}
			final String hostUsername;
			final String hostPassword;
			if (i < usernameArr.length) {
				hostUsername = usernameArr[i];
			} else {
				hostUsername = "";
			}
			if (i < passwordArr.length) {
				hostPassword = passwordArr[i];
			} else {
				hostPassword = "";
			}
			log.info("sending mail by host " + host);
			props.put("mail.smtp.host", host);
			
			if (!StringUtils.isEmpty(hostUsername) && !StringUtils.isEmpty(hostPassword)) {
				props.put("mail.smtp.auth", "true");
			} else {
				props.put("mail.smtp.auth", "false");
			}
			props.put("mail.smtp.timeout", 2000);
			props.put("mail.smtp.connectiontimeout", 2000);
			props.put("mail.smtp.writetimeout", 2000);
			props.put("mail.debug", "true");

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(hostUsername, hostPassword);
				}
			});
			session.setDebug(false);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.addRecipients(RecipientType.TO, InternetAddress.parse(toMail));
			if (!StringUtils.isEmpty(ccMail)) {
				message.addRecipients(RecipientType.CC, InternetAddress.parse(ccMail));
			}
			message.setSubject(subject);
			message.setContent(multipart);
			message.setSentDate(new Date());
			message.saveChanges();
			try {
				Transport transport = session.getTransport("smtp");
				final DeliveredStateFuture future = new DeliveredStateFuture();
				transport.addTransportListener(new TransportListener() {
					public void messageDelivered(TransportEvent arg0) {
						future.setState(DeliveredState.MESSAGE_DELIVERED);
					}
					public void messageNotDelivered(TransportEvent arg0) {
						future.setState(DeliveredState.MESSAGE_NOT_DELIVERED);
					}
					public void messagePartiallyDelivered(TransportEvent arg0) {
						future.setState(DeliveredState.MESSAGE_PARTIALLY_DELIVERED);
					}
				});
				transport.connect();
				transport.sendMessage(message, message.getAllRecipients());
				future.waitForReady();
				isSuccess = true;
				break;
			} catch (Exception ex) {
				log.error("sending mail by host " + host + " failed! try next if any.");
				log.error(ex);
				throw new MessagingException(ex.getMessage());
			}
		}
		if (isSuccess) {
			log.info("sending mail completed!");
		} else {
			throw new MessagingException(MAIL_SERVER_HOSTS_NOT_DEFINED);
		}
	}
	
	public static class AttachDataSource implements DataSource {

		private byte[] attach;
		private String fileName;
		
		public AttachDataSource(byte[] attach, String fileName){
			this.attach = attach;
			this.fileName = fileName;
		}
		@Override
		public String getContentType() {
			return "application/octet-stream";
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(attach);
		}

		@Override
		public String getName() {
			return fileName;
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return new ByteArrayOutputStream();
		}

	}
	
	public String getLocalizedMessage(String messageId, Object... messageParams ) {
        return this.getLocalizedMessage(messageId, null, messageParams);
	}

	public String getLocalizedMessage(String messageId, Locale locale, Object... messageParams ) {
		if (locale == null) {
			locale = LocaleContextHolder.getLocale();
		}
		return messageSource.getMessage(messageId, messageParams, locale);
	}

}
