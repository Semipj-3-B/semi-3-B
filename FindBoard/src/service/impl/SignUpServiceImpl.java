package service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.SignUpDao;
import dao.impl.SignUpDaoImpl;
import dto.Usertb;
import service.face.SignUpService;
import util.HashNMacUtil;

public class SignUpServiceImpl implements SignUpService {
	
	class MyAuthentication extends Authenticator {
	    
		//id, pw 저장 객체 생성
	    PasswordAuthentication pa;
	 
	    public MyAuthentication(){
	         
	        String id = "choi13698@naver.com";       //  ID
	        String pw = "mirae1398";          //  비밀번호
	 
	        pa = new PasswordAuthentication(id, pw);
	      
	    }
	 
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }
	}

	private Connection conn = null;
	private SignUpDao signUpDao = new SignUpDaoImpl();
	
	
	@Override
	public String sendEmail(String email) {

		// 메일 보내기 정보
	    Properties p = System.getProperties();
	    p.put("mail.smtp.starttls.enable", "true");    
	    p.put("mail.smtp.host", "smtp.naver.com");      // smtp 서버 주소
	    p.put("mail.smtp.auth","true");                 
	    p.put("mail.smtp.port", "587");                 //  포트
	       
	    Authenticator auth = new MyAuthentication();
	     
	    //session 생성 및  MimeMessage생성
	    Session session = Session.getDefaultInstance(p, auth);
	    MimeMessage msg = new MimeMessage(session);
	     
	    //난수 발생 변수
	    String value = "";
	    
	    try{
	        //편지보낸시간
	        msg.setSentDate(new Date());
	        InternetAddress from = new InternetAddress() ;
	         
	        //발신자
	        from = new InternetAddress("choi13698@naver.com");
	         
	        // 이메일 발신자
	        msg.setFrom(from);
	        
	        //보낸사람 이름
	        msg.setFrom(new InternetAddress("choi13698@naver.com", "으으"));
	         
	        // 이메일 수신자
	        InternetAddress to = new InternetAddress(email);
	        msg.setRecipient(Message.RecipientType.TO, to);
	         
	        // 이메일 제목  ( 제목 / 타입 )
	        msg.setSubject("TEST5", "UTF-8");
	        
	        //인증번호 생성
	        Random ran = new Random();
	        
	        for(int i=0;i<11;i++){
	            if(ran.nextBoolean()){
	                value += ((char)((int)(ran.nextInt(26))+97));
	            }else{
	                value += ((ran.nextInt(10)));
	            }
	        }

	        // 이메일 내용  ( 내용 / 타입 )
	        msg.setText("이메일 인증번호" + "<br>" + value, "UTF-8");
	         
	        // 이메일 헤더
	        msg.setHeader("content-Type", "text/html");
	         
	        //메일보내기
	        javax.mail.Transport.send(msg, msg.getAllRecipients());
	        
	        System.out.println("Mail Sending Success");
	         
	    }catch (AddressException addr_e) {
	        addr_e.printStackTrace();
	    }catch (MessagingException msg_e) {
	        msg_e.printStackTrace();
	    }catch (Exception msg_e) {
	        msg_e.printStackTrace();
	    }
		
		return value;
	}
	
	@Override
	public Usertb getParam(HttpServletRequest req) {

		Usertb user = new Usertb();
		
		user.setUserId(req.getParameter("id")); //id
		
		try {
			//비밀번호 Sha256 해쉬코드 암호화처리
			user.setUserPw(HashNMacUtil.EncBySha256(req.getParameter("pw"))); //pw
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		user.setNick(req.getParameter("nick")); //nick
		
		String year = req.getParameter("year"); //year
		if(year != null && !"".equals(year)) {
			user.setYear(Integer.parseInt(year));
		}
		
		String month = req.getParameter("month"); //month
		if(month != null && !"".equals(month)) {
			user.setMonth(Integer.parseInt(month));
		}
		
		String day = req.getParameter("day"); //day
		if(day != null && !"".equals(day)) {
			user.setDay(Integer.parseInt(day));
		}
		
		user.setName(req.getParameter("name")); //name
		user.setGender(req.getParameter("gender")); //gender
		user.setEmail(req.getParameter("email")); //email
		user.setPhone(req.getParameter("tel")); //tel
		
		String postnum = req.getParameter("postnum"); //postnum
		if(postnum !=null && !"".equals(postnum)) {
			user.setPostnum(Integer.parseInt(postnum));
		}
		
		user.setAddr(req.getParameter("addr")); //addr
		user.setAddrDetail(req.getParameter("addrDetail")); //addrDetail
		
		return user;
	}
	
	@Override
	public int signUpUser(Usertb user) {

		conn = JDBCTemplate.getConnection();
		
		//유저정보 삽입
		int res = signUpDao.insertUser(conn, user);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return res;
	}
	
	@Override
	public int signUpIDCheck(String id) {

		conn = JDBCTemplate.getConnection();
		
		//아이디 중복 체크
		int res = signUpDao.selectId(conn, id);
		
		return res;
	}
	
	@Override
	public int signUpNickCheck(String nick) {

		conn = JDBCTemplate.getConnection();
		
		//닉네임 중복 체크
		int res = signUpDao.selectNick(conn, nick);
		
		return res; 
	
	}
}
