
/*
 * Mailer.java
 *
 * Created on July 16, 2007, 2:40 PM
 *
 * Version: 1.0.0.0
 *
 * Changes:
 */
package com.common;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author pavankumar.g
 */
public class Mailer {

    Properties m_prop = null;
    String m_smtp_host = null;
    String m_default_from = null;
    String m_strUserName = null;
    String m_strPassword = null;
    MimeBodyPart msgBodyPart = null;
    Multipart multipart = null;
    StringTokenizer m_strToken = null;
    String m_SmtpTimeout = "";
    String m_SmtpConTimeout = "";
    boolean m_bDebgMail;
    Logger logger = Logger.getLogger(Mailer.class);

    /**
     * Creates a new instance of SendingMails
     */
    public Mailer(Properties prop) {
        m_prop = prop;
        m_smtp_host = m_prop.getProperty("smtp.host", "smtp.mandrillapp.com");
        m_default_from = m_prop.getProperty("smtp.default.from", "info@aqarabia.net");
        m_strUserName = m_prop.getProperty("smtp.username", "info@aqarabia.net");
        m_strPassword = m_prop.getProperty("smtp.password", "Pl0iuN1kFNJIwvUIItOJLA");
        m_SmtpTimeout = m_prop.getProperty("smtp.timeout", "20000");
        m_SmtpConTimeout = m_prop.getProperty("smtp.connection.timeout", "30000");
        m_bDebgMail = Boolean.parseBoolean(m_prop.getProperty("mail.debug", "false"));
//        String strLogIp = m_prop.getProperty("log.server.ip", "127.0.0.1");
//        String strLogPort = m_prop.getProperty("log.server.port", "6009");
//        int iLogPort = MVaayuUtil.intVal(strLogPort, 6009);
//        if (!(m_Logger.init(strLogIp, iLogPort, "Mail Monitor"))) {
//            System.out.println("Logger Initialization failed");
//        }
    }
    //Sending emails

    public int sendMail(String strFrom, String strTo, String strCC, String strBCC, String Subject, String strContent, ArrayList attachments) {
        System.out.println("Inside Mailer***************");
        Properties props = new Properties();
        props.put("mail.smtp.host", m_smtp_host);
        props.put("mail.smtp.connectiontimeout", m_SmtpConTimeout);
        props.put("mail.smtp.timeout", m_SmtpTimeout);
        props.put("mail.smtp.port", "587");
        Authenticator authenticator = null;
        Session session = null;
        if (m_strUserName.trim().equals("") || m_strPassword.trim().equals("")) {
            session = Session.getInstance(props);
        } else {
            props.put("mail.smtp.auth", "true");
            authenticator = new AuthenticateUser();
            session = Session.getInstance(props, authenticator);
        }
        session.setDebug(m_bDebgMail);
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect();
            if (transport.isConnected()) {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(strFrom));
                m_strToken = new StringTokenizer(strTo, ",");
                while (m_strToken.hasMoreTokens()) {
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_strToken.nextToken()));
                }
                if (strCC != null && strCC.length() > 0) {
                    m_strToken = new StringTokenizer(strCC, ",");
                    while (m_strToken.hasMoreTokens()) {
                        msg.addRecipients(Message.RecipientType.CC,
                                InternetAddress.parse(m_strToken.nextToken(), true));
                    }
                }
                if (strBCC != null && strBCC.length() > 0) {
                    m_strToken = new StringTokenizer(strBCC, ",");
                    while (m_strToken.hasMoreTokens()) {
                        msg.addRecipients(Message.RecipientType.BCC,
                                InternetAddress.parse(m_strToken.nextToken(), true));
                    }
                }
                msg.setSubject(Subject);
                msg.setSentDate(new Date());

                multipart = new MimeMultipart();
                msgBodyPart = new MimeBodyPart();
                msgBodyPart.setText(strContent);
                multipart.addBodyPart(msgBodyPart);
                for (int i = 0; i < attachments.size(); i++) {
                    addAttachment(msg, (String) attachments.get(i));
                }
                msg.setContent(multipart);
                msg.saveChanges();
                try {
                    transport.send(msg);
                } catch (SendFailedException sfe) {
                    logger.error("---------------------" + sfe.getMessage());
                }
                transport.close();
                return ResponseCodes.ServiceErrorCodes.SUCCESS.CODE();
            } else {
                return ResponseCodes.ServiceErrorCodes.INVALID_SMTP_CONNECTION.CODE();
            }
        } catch (MessagingException mex) {
//            m_Logger.logMessage(m_Logger.LOG_WARNING, "Mailer: sendMail() failed for User:" + strTo + " Exception is " + mex.toString());
            System.out.println("Mailer Exception");
            System.out.println("Exception sendMsg " + mex.getMessage());
            mex.printStackTrace();

            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                }
                if (mex.getMessage().indexOf("Invalid Addresses") > 0) {
                    return ResponseCodes.ServiceErrorCodes.INVALID_EMAILID.CODE();
                } else {
                    mex = (MessagingException) ex;
                }
            }
            if (mex instanceof SendFailedException) {
                System.out.println("Sending Mail failed");
            }
            System.out.println("Err msg is " + mex.getMessage());
            if (mex.getMessage().indexOf("Could not connect to SMTP") != -1) {
                return ResponseCodes.ServiceErrorCodes.INVALID_SMTP_CONNECTION.CODE();
            }
            return ResponseCodes.ServiceErrorCodes.GENERIC_ERROR.CODE();
        }
    }

    public int sendHtmlMail(String strFrom, String strTo, String strCC, String strBCC, String Subject, String strContent, ArrayList attachments) {
        System.out.println("Inside Mailer***************");
        Properties props = new Properties();
       props.put("mail.smtp.host", m_smtp_host);
		//props.put("mail.smtp.socketFactory.port", "465");
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
//        Session session = null;
//        if (m_strUserName.trim().equals("") || m_strPassword.trim().equals("")) {
//            session = Session.getInstance(props);
//        } else {
//            props.put("mail.smtp.auth", "true");
//            authenticator = new AuthenticateUser();
//            session = Session.getInstance(props, authenticator);
//        }
//        session.setDebug(m_bDebgMail);
        
        Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(m_strUserName,m_strPassword);
				}
			});
        try {
           // Transport transport = session.getTransport("smtp");
           // transport.connect();
           // if (transport.isConnected()) {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(strFrom));
                m_strToken = new StringTokenizer(strTo, ",");
                while (m_strToken.hasMoreTokens()) {
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_strToken.nextToken()));
                }
                if (strCC != null && strCC.length() > 0) {
                    m_strToken = new StringTokenizer(strCC, ",");
                    while (m_strToken.hasMoreTokens()) {
                        msg.addRecipients(Message.RecipientType.CC,
                                InternetAddress.parse(m_strToken.nextToken(), true));
                    }
                }
                if (strBCC != null && strBCC.length() > 0) {
                    m_strToken = new StringTokenizer(strBCC, ",");
                    while (m_strToken.hasMoreTokens()) {
                        msg.addRecipients(Message.RecipientType.BCC,
                                InternetAddress.parse(m_strToken.nextToken(), true));
                    }
                }
                msg.setSubject(Subject);
                msg.setSentDate(new Date());

                multipart = new MimeMultipart();
                msgBodyPart = new MimeBodyPart();

                msgBodyPart.setContent(strContent, "text/html");
//            msgBodyPart=getFileBodyPart("c://count.txt");
                multipart.addBodyPart(msgBodyPart);
                for (int i = 0; i < attachments.size(); i++) {
                    addAttachment(msg, (String) attachments.get(i));
                }
                msg.setContent(multipart);
                msg.saveChanges();
                Transport.send(msg);
              //  Transport.close();
                return ResponseCodes.ServiceErrorCodes.SUCCESS.CODE();
            //} else {
              //  return ResponseCodes.ServiceErrorCodes.INVALID_SMTP_CONNECTION.CODE();
            //}
        } catch (MessagingException mex) {
//            m_Logger.logMessage(m_Logger.LOG_WARNING, "Mailer: sendMail() failed for User:" + strTo + " Exception is " + mex.toString());
            System.out.println("Mailer Exception");
            System.out.println("Exception sendMsg " + mex.getMessage());
            mex.printStackTrace();

            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                }
                if (mex.getMessage().indexOf("Invalid Addresses") > 0) {
                    return ResponseCodes.ServiceErrorCodes.INVALID_EMAILID.CODE();
                } else {
                    mex = (MessagingException) ex;
                }
            }
            if (mex instanceof SendFailedException) {
                System.out.println("Sending Mail failed");
            }
            System.out.println("Err msg is " + mex.getMessage());
            if (mex.getMessage().indexOf("Could not connect to SMTP") != -1) {
                return ResponseCodes.ServiceErrorCodes.INVALID_SMTP_CONNECTION.CODE();
            }
            return ResponseCodes.ServiceErrorCodes.GENERIC_ERROR.CODE();
        }
    }
    // getting message content only for testing

    static public String getMsgContent(String strUid, String strUName, String strPW) throws MessagingException {
        String strText = "Dear " + strUName + ",\n\n"
                + "Thanks for registering with mVaayu.\n\n"
                + "Your USER ID is: " + strUid + " \n"
                + "Your PASSWORD is: " + strPW + " \n\n\n"
                + "Regards\n"
                + "mVaayu Team\n ";
        //ttp://www.roseindia.net

        return strText;
    }
    //file attaching

    public void addAttachment(Message msg, String filename)
            throws MessagingException {
        msgBodyPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filename);
        msgBodyPart.setDataHandler(new DataHandler(fds));
        msgBodyPart.setFileName(fds.getName());
        multipart.addBodyPart(msgBodyPart);
    }
    //Getting File Content as  Body Part

    public MimeBodyPart getFileBodyPart(String filename)
            throws javax.mail.MessagingException {
        msgBodyPart = new MimeBodyPart();
        msgBodyPart.setDataHandler(new DataHandler(new FileDataSource(filename)));
        return msgBodyPart;
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        FileInputStream fin = new FileInputStream("c://mvaayu.properties");
        prop.load(fin);
        Mailer objHandler = new Mailer(prop);
        String strCnt = objHandler.getMsgContent("vas_srinuin", "srinu", "s1232312jkh");
        ArrayList attchList = new ArrayList();
        attchList.add("c:\\mvaayu.properties");
        objHandler.sendMail(objHandler.m_default_from, "srinivasu.k@imimobile.com", "", "", "Mvaayu Password Details", strCnt, attchList);
    }

    class AuthenticateUser extends Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(m_strUserName, m_strPassword);
        }
    }
}
