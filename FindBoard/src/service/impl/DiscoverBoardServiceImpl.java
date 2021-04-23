package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.DiscoverBoardDao;
import dao.impl.DiscoverBoardDaoImpl;
import dto.DiscoverBoard;
import dto.DiscoverComment;
import dto.DiscoverImg;
import service.face.DiscoverBoardService;
import util.Paging;

public class DiscoverBoardServiceImpl implements DiscoverBoardService {

	private DiscoverBoardDao discoverBoardDao = new DiscoverBoardDaoImpl();
	
	@Override
	public List<DiscoverBoard> getList(Paging paging, Map<String, String> map) {
		return discoverBoardDao.selectAll(JDBCTemplate.getConnection(), paging, map);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		int totalCount = discoverBoardDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public DiscoverBoard getParam(HttpServletRequest req) {
		
		DiscoverBoard discoverNo = new DiscoverBoard();
		
		String param = req.getParameter("DiscoverNo");
		if(param != null && !"".equals(param)) {
			
			discoverNo.setDiscoverNo( Integer.parseInt(param) );
			
		}
		
		return discoverNo;
	}

	@Override
	public DiscoverBoard views(DiscoverBoard discoverno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( discoverBoardDao.updateHit(conn, discoverno) >= 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		DiscoverBoard board = discoverBoardDao.selectBoardByDiscoverno(conn, discoverno);
		
		
		return board;
	}

	@Override
	public Object getnick(DiscoverBoard viewDiscoverBoard) {
		return discoverBoardDao.selectNickByUserNo(JDBCTemplate.getConnection(), viewDiscoverBoard );
	}

	@Override
	public Object getemail(DiscoverBoard viewDiscoverBoard) {
		return discoverBoardDao.selectEmailByUserNo(JDBCTemplate.getConnection(), viewDiscoverBoard );
	}

	@Override
	public List<DiscoverImg> viewFile(DiscoverBoard viewDiscoverBoard) {
		return discoverBoardDao.selectFile(JDBCTemplate.getConnection(), viewDiscoverBoard );
	}

	@Override
	public void write(HttpServletRequest req) {
				
		
//		discoverImg
		DiscoverBoard discoverBoard = null;
		DiscoverImg discoverImg = null;

		List<DiscoverImg> discoverImages = new ArrayList<DiscoverImg>();
		
		final String[] location = { "서울특별시", "경기도", "강원도", "충청북도", "충청남도", "경상북도", "경상남도", "전라북도", "전라남도", "대전광역시",
				"광주광역시", "인천광역시", "부산광역시", "대구광역시", "울산광역시", "세종시", "제주시" };

		// 파일업로드 형태의 데이터가 맞는지 검사
		boolean isMutltipart = false;
		isMutltipart = ServletFileUpload.isMultipartContent(req);

		if (!isMutltipart) {
			System.err.println("multipart/form-data 형식이 아닙니다.");
			return; // fileupload() 메소드 실행 중지
		}

		// multipart/form-data일 때 인스턴스 생성
		discoverBoard = new DiscoverBoard();

		Connection conn = JDBCTemplate.getConnection();
		int discoverno = discoverBoardDao.selectDiscoverno(conn);
		discoverBoard.setDiscoverNo(discoverno);

		// DiskFileItemFactory: FileItem 오브젝트 생성 및 메모리/HDD에서의 데이터 처리 기능을 가진다.
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리 처리 사이즈 지정
		final int MEM_SIZE = 1 * 1024; // 1KB
		factory.setSizeThreshold(MEM_SIZE);

		// 임시 저장소(name: tmp) 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();

		factory.setRepository(repository);

		// 파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 용량 제한
		final int MAX_SIZE = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(MAX_SIZE);

		// 전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		// 모든 요청 정보 처리하기
		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			// 일반적인 요청 데이터 처리
			if (item.isFormField()) {
				// name 값으로 키 추출
				String key = item.getFieldName();

				// NOT NULL 파라미터 먼저 처리 (NOT NULL: title, petkinds, loc)
				if (key != null && !"".equals(key)) {

					// 전달 파라미터 name이 "title"
					if ("title".equals(key)) {
						try {
							discoverBoard.setTitle(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} // if(title.key) END

					if ("petkinds".equals(key)) {
						try {
							discoverBoard.setPetKinds(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} // if(petkinds.key) END

					if ("loc".equals(key)) {
						try {
							int value = Integer.parseInt(item.getString("UTF-8")) - 1;
							for (int i = 0; i < location.length; i++) {
								if (i == value) {
									discoverBoard.setLoc(location[i]);
								}
							}
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} // if(loc.key) END
				} // if(NOT NULL) END

				// NULL 허용 데이터 처리
				if ("petname".equals(key)) {
					try {
						discoverBoard.setPetName(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				if ("petage".equals(key)) {
					try {
						discoverBoard.setPetAge(Integer.parseInt(item.getString("UTF-8")));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				if ("content".equals(key)) {
					try {
						discoverBoard.setContent(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			} // if(isFormField) END

			// 파일 처리
			if (!item.isFormField()) {
				// 확장자 추출
				int lastDot = item.getName().lastIndexOf('.');
				String ext = item.getName().substring(lastDot + 1);

				// 확장자 유효 검사
				boolean isImg = false;
				if ("jpg".equals(ext) || "jpeg".equals(ext))
					isImg = true;

				// 파일명 유효 검사
				boolean isValidName = false;
				String originName = item.getName().substring(0, lastDot);

				// 파일명 String -> Byte 길이로 변환
				int nameToBytes = 0;
				try {
					nameToBytes = originName.getBytes("UTF-8").length;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				// 30byte 초과시 false
				final int maxBytes = 30;
				if (nameToBytes <= maxBytes)
					isValidName = true;
				else {
					originName = originName.substring(0, 9);
					isValidName = true;
				}

				// 확장자, 파일명 모두 유효할 때만 파일 저장 및 DB 삽입
				if (isImg && isValidName) {

					// UUID 생성
					UUID uuid = UUID.randomUUID();
					String uid = uuid.toString().split("-")[0];

					// 파일이 저장될 이름을 설정(originName_xxxxxxxx)
					String storedName = originName + "_" + uid;

					// 로컬 저장소에 파일 객체(upload 폴더) 생성
					File uploFolder = new File(req.getServletContext().getRealPath("upload"));
					uploFolder.mkdir();

					File upFile = new File(uploFolder, storedName);

					discoverImg = new DiscoverImg();
					discoverImg.setDiscoverNo(discoverno);
					discoverImg.setOriginImg(originName);
					discoverImg.setStoredImg(storedName);

					discoverImages.add(discoverImg);

					// 처리가 완료된 파일 업로드
					try {
						item.write(upFile); // 실제 업로드
						item.delete(); // 임시 파일 삭제
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // if(isImg) END

			} // if(!ifFormField) END

		} // while(iter.hasnext) END

		String userid = (String) req.getSession().getAttribute("userid");
		if (userid != null && !"".equals(userid)) {
			int userno = discoverBoardDao.selectUserno(JDBCTemplate.getConnection(), userid);
			discoverBoard.setUserNo(userno);
		}

//				String usernoString = String.valueOf(req.getSession().getAttribute("userno"));
//				if(usernoString != null && !"".equals(usernoString)) {
//					discoverBoard.setUserNo((Integer) req.getSession().getAttribute("userno"));
//				}
		System.out.println("userno = " + req.getSession().getAttribute("userno"));

		String usernoString = String.valueOf(req.getSession().getAttribute("userno"));
		if (usernoString != null && !"".equals(usernoString)) {
			discoverBoard.setUserNo((Integer) req.getSession().getAttribute("userno"));
		}

		if (discoverBoard != null) {
			if (discoverBoardDao.insert(conn, discoverBoard) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

		if (discoverImages != null) {
			if (discoverBoardDao.insertImg(conn, discoverImages) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

	}

	@Override
	public void update(HttpServletRequest req) {
		
		
//		discoverBoard discoverBoard = null;
//		discoverImg discoverImg = null;
//		List<discoverImg> findImages = new ArrayList<discoverImg>();
		
		DiscoverBoard discoverBoard = null;
		DiscoverImg discoverImg = null;

		List<DiscoverImg> discoverImages = new ArrayList<DiscoverImg>();
		
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		boolean isNewFile = false;
		

				
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		System.out.println("사진 있는지 없는지 = "+ isMultipart);
		
		//사진파일이 없으면
		if(!isMultipart) {
			System.out.println("사진파일 없습니다");
			
			discoverBoard = new DiscoverBoard();
			
			discoverBoard.setTitle( req.getParameter("title") );
			discoverBoard.setContent( req.getParameter("content") );

			
		} else {
		
		//사진파일 있을때
			
		System.out.println("수정 사진 파일이 있을 때 --------");
		discoverBoard = new DiscoverBoard();
			
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		final int MEM_SIZE = 1 * 1024; 	//1KB
		factory.setSizeThreshold(MEM_SIZE);	
		
		//임시 저장소
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		
		factory.setRepository(repository);
		
		//파일업로드 
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//용량 제한
		final int MAX_SIZE = 10 * 1024 * 1024;	//10MB
		upload.setFileSizeMax(MAX_SIZE);
		
		
		//파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
//		System.out.println("form으로 전달된 데이터 = " + items.iterator() );
		
		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();
		
		
		//모든 요청 정보 처리하기
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			//빈 파일
			if(item.getSize() <= 0)	continue;
			
			//요청 데이터 처리
			if(item.isFormField()) {
				
				try {
					if( "discoverNo".equals( item.getFieldName() ) ) {
						discoverBoard.setDiscoverNo( Integer.parseInt(item.getString() ));
					}
					if( "title".equals( item.getFieldName() ) ) {
						discoverBoard.setTitle(item.getString("UTF-8"));
					}
					if( "content".equals( item.getFieldName() ) ) {
						discoverBoard.setContent(item.getString("UTF-8"));
					}
					
//					board.setUserid((String) req.getSession().getAttribute("userid"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
//								
				
			} //if(isFormField) END
			

			
			//파일 처리
			if(!item.isFormField()) {
				
				isNewFile = true;
				
				//확장자 추출
				int lastDot = item.getName().lastIndexOf('.');
				String ext = item.getName().substring(lastDot + 1);
				
				//확장자 유효 검사
				boolean isImg = false;
				if("jpg".equals(ext) || "jpeg".equals(ext)) isImg = true;
				
				//파일명 유효 검사
				boolean isValidName = false;
				String originName = item.getName().substring(0, lastDot);
				
				//파일명 String -> Byte 길이로 변환
				int nameToBytes = 0;
				try {
					nameToBytes = originName.getBytes("UTF-8").length;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//30byte 초과시 false
				final int maxBytes = 30;
				if(nameToBytes <= maxBytes) isValidName = true;
				
				//확장자, 파일명 모두 유효할 때만 파일 저장 및 DB 삽입
				if(isImg && isValidName) {
					
					//UUID 생성
					UUID uuid = UUID.randomUUID();
					String uid = uuid.toString().split("-")[0];
					
					//파일의 저장될 이름
					String storedName = originName + "_" + uid;
					
					//upload 폴더 생성
					File uploFolder = new File(req.getServletContext().getRealPath("upload"));
					uploFolder.mkdir();
					
					File upFile = new File(uploFolder, storedName);
					
					discoverImg = new DiscoverImg();
					discoverImg.setDiscoverNo(discoverBoard.getDiscoverNo());
					discoverImg.setOriginImg(originName);
					discoverImg.setStoredImg(storedName);
					
					discoverImages.add(discoverImg);
					
					//처리가 완료된 파일 업로드
					try {
						item.write(upFile);	//실제 업로드
						item.delete();		//임시 파일 삭제
					} catch (Exception e) {
						e.printStackTrace();
					}
				} //if(isImg) END
				
			} //if(!ifFormField) END
			
		} //while(iter.hasnext) END
		
		

			String userid = (String) req.getSession().getAttribute("userid");
			if(userid != null && !"".equals(userid)) {
				int userno = discoverBoardDao.selectUserno(JDBCTemplate.getConnection(), userid);
				discoverBoard.setUserNo(userno);
			}

			String usernoString = String.valueOf(req.getSession().getAttribute("userno"));
			if(usernoString != null && !"".equals(usernoString)) {
				discoverBoard.setUserNo((Integer) req.getSession().getAttribute("userno"));
			}

		}
			
		
		
		if(isNewFile) {
			//이전파일삭제		
			discoverBoardDao.deleteFile(conn, discoverBoard) ;		
		}
				
		if(discoverBoard != null) {
			if(discoverBoardDao.update(conn, discoverBoard) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(discoverImages != null) {
			if(discoverBoardDao.insertImg(conn, discoverImages) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		

		
	}

	@Override
	public void delete(DiscoverBoard discoverboard) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( discoverBoardDao.deleteFile(conn, discoverboard) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if ( discoverBoardDao.delete(conn, discoverboard) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public List<DiscoverComment> viewComment(int discoverNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<DiscoverComment> list = discoverBoardDao.selectComment(conn, discoverNo);
		
		return list;
	}

	@Override
	public DiscoverComment getCommentParam(HttpServletRequest req) {
		
		DiscoverComment discoverComment = new DiscoverComment();
		
		
//		얻어온값과 게시글번호 검사
		String param1 = req.getParameter("discoverNo");
		
//		System.out.println("얻어온 게시글 번호 = " + param1);
		
		int discoverNo = 0;
		if( param1 != null && !"".equals(param1) ) {
			discoverNo = Integer.parseInt(param1);
		}
		
//		System.out.println("검사 결과 = "+ discoverNo);
		
//		얻어온값과 유저번호 검사
		String param2 = req.getParameter("userno");
		
//		System.out.println("얻어온 값 의 유저 번호 = " + param2);
		
		int userno = 0;
		if(param2 != null && !"".equals(param2)) {
			userno = Integer.parseInt(param2);
			
		}
		
//		System.out.println("userno 추출 결과 = " + userno);
		
		discoverComment.setDiscoverNo(discoverNo);
		
		if(req.getParameter("comment") != null) {
			discoverComment.setCommentText(req.getParameter("comment"));
		}
		
		discoverComment.setNick(req.getParameter("nick"));
		discoverComment.setUserNo(userno);
		
//		System.out.println("comment = " + req.getParameter("comment"));
		System.out.println("commentno = " + req.getParameter("commentno"));
		
		if(req.getParameter("commentno") != null && !"".equals(req.getParameter("commentno"))) {
			
			
			discoverComment.setCommentNo( Integer.parseInt( req.getParameter("commentno") ) );
		}
		
		
		return discoverComment;
		
	}

	@Override
	public void writeComment(DiscoverComment param) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = discoverBoardDao.insertComment(conn, param);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);				
		}
		
	}

	@Override
	public void updateComment(DiscoverComment param) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = discoverBoardDao.updateComment(conn, param);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

	@Override
	public void removeComment(DiscoverComment param) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = discoverBoardDao.deleteComment(conn, param);
		
		if(res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

}
