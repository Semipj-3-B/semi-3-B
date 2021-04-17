package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.FindBoardDao;
import dao.impl.FindBoardDaoImpl;
import dto.FindBoard;
import dto.FindImg;
import service.face.FindBoardService;
import util.Paging;

public class FindBoardServiceImpl implements FindBoardService{
	
	//FindBoardDao 객체 생성
	private FindBoardDao findBoardDao = new FindBoardDaoImpl();

	@Override
	public List<FindBoard> getList() {
		return findBoardDao.selectAll(JDBCTemplate.getConnection());
	}
	
	@Override
	public List<FindBoard> getList(Paging paging) {
		return findBoardDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = findBoardDao.selectCntAll(JDBCTemplate.getConnection());

		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public FindBoard getParam(HttpServletRequest req) {
		
		//FindBoardno를 저장할 객체 생성
		FindBoard findNo = new FindBoard();
		
		//FindBoardno 전달 파라미터 검증
		String param = req.getParameter("FindNo");
		if(param!=null && !"".equals(param)) {
			
			//Findboardno 전달파라미터 추출
			findNo.setFindNo( Integer.parseInt(param) );
		}
				
		// 결과 반환
		return findNo;
	}
	
	@Override
	public FindBoard views(FindBoard findno) {
		Connection conn = JDBCTemplate.getConnection();

		//
		if( findBoardDao.updateHit(conn, findno) >= 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		FindBoard board = findBoardDao.selectBoardByFindno(conn, findno); 
		
		return board;
	}



//	@Override
//	public FindBoard read(FindBoard findNo) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//
//		//조회수 증가
//		if( findBoardDao.updateHit(conn, findNo) >= 0 ) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
//		
//		//게시글 조회
//		FindBoard board = findBoardDao.selectFind(conn, findNo); 
//		
//		return board;
//	}

	
	@Override
	public String getnick(FindBoard viewFindBoard) {
		return findBoardDao.selectNickByUserNo(JDBCTemplate.getConnection(), viewFindBoard);
	}
	
	@Override
	public String getemail(FindBoard viewFindBoard) {
		return findBoardDao.selectEmailByUserNo(JDBCTemplate.getConnection(), viewFindBoard);
	}

	@Override
	public FindImg viewFile(FindBoard viewFindBoard) {
		return findBoardDao.selectFile(JDBCTemplate.getConnection(), viewFindBoard);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		
		//새 게시글, 첨부파일 데이터를 저장할 객체 선언
		FindBoard findBoard = null;
		FindImg findImg = null;
		List<FindImg> findImages = new ArrayList<FindImg>();
		final String[] location = {"서울특별시", "경기도", "강원도", "충청북도", "충청남도"
									, "경상북도", "경상남도", "전라북도", "전라남도", "대전광역시"
									, "광주광역시", "인천광역시", "부산광역시", "대구광역시"
									, "울산광역시", "세종시", "제주시"};
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMutltipart = false;
		isMutltipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMutltipart) {
			System.err.println("multipart/form-data 형식이 아닙니다.");
			return;	//fileupload() 메소드 실행 중지
		}
		
		//multipart/form-data일 때 인스턴스 생성
		findBoard = new FindBoard();
		
		Connection conn = JDBCTemplate.getConnection();
		int findno = findBoardDao.selectFindno(conn);
		findBoard.setFindNo(findno);
		
		//DiskFileItemFactory: FileItem 오브젝트 생성 및 메모리/HDD에서의 데이터 처리 기능을 가진다.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 지정
		final int MEM_SIZE = 1 * 1024; 	//1KB
		factory.setSizeThreshold(MEM_SIZE);	
		
		//임시 저장소(name: tmp) 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		
		factory.setRepository(repository);
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 용량 제한
		final int MAX_SIZE = 10 * 1024 * 1024;	//10MB
		upload.setFileSizeMax(MAX_SIZE);	
		
		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();
		
		//모든 요청 정보 처리하기
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			//빈 파일 처리
			if(item.getSize() <= 0)	continue;
			
			//일반적인 요청 데이터 처리
			if(item.isFormField()) {
				//name 값으로 키 추출
				String key = item.getFieldName();

				//NOT NULL 파라미터 먼저 처리 (NOT NULL: title, petkinds, loc)
				if(key != null && !"".equals(key)) {
					
					//전달 파라미터 name이 "title"
					if("title".equals(key)) {
						try {
							findBoard.setTitle(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} //if(title.key) END
					
					if("petkinds".equals(key)) {
						try {
							findBoard.setPetKinds(item.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} //if(petkinds.key) END
					
					if("loc".equals(key)) {
						try {
							int value = Integer.parseInt(item.getString("UTF-8")) - 1;
							for(int i = 0; i < location.length; i++) {
								if( i == value ) {
									findBoard.setLoc(location[i]);
								}
							}
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} //if(loc.key) END
				} //if(NOT NULL) END
				
				//NULL 허용 데이터 처리
				if("petname".equals(key) ) {
					try {
						findBoard.setPetName(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				if("petage".equals(key) ) {
					try {
						findBoard.setPetAge(Integer.parseInt(item.getString("UTF-8")));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				if("content".equals(key) ) {
					try {
						findBoard.setContent(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			} //if(isFormField) END
			
			//파일 처리
			if(!item.isFormField()) {
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
					
					//파일이 저장될 이름을 설정(originName_xxxxxxxx)
					String storedName = originName + "_" + uid;
					
					//로컬 저장소에 파일 객체(upload 폴더) 생성
					File uploFolder = new File(req.getServletContext().getRealPath("upload"));
					uploFolder.mkdir();
					
					File upFile = new File(uploFolder, storedName);
					
					findImg = new FindImg();
					findImg.setFindNo(findno);
					findImg.setOriginImg(originName);
					findImg.setStoredImg(storedName);
					
					findImages.add(findImg);
					
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
			int userno = findBoardDao.selectUserno(JDBCTemplate.getConnection(), userid);
			findBoard.setUserNo(userno);
		}
		
		String usernoString = (String)req.getSession().getAttribute("userno");
		if(usernoString != null && !"".equals(usernoString)) {
			findBoard.setUserNo(Integer.parseInt(usernoString));
		}
		
		if(findBoard != null) {
			if(findBoardDao.insert(conn, findBoard) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(findImages != null) {
			if(findBoardDao.insertImg(conn, findImages) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	} //write() END

	@Override
	public void delete(FindBoard findboard) {
		
		Connection conn = JDBCTemplate.getConnection();
		
//		if(findBoardDao.deleteFile(conn, findboard) > 0) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
		
		if(findBoardDao.delete(conn, findboard) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
	}
	
	//게시글 수정
	@Override
	public void update(HttpServletRequest req) {
		
		FindBoard findboard = null;
		
		findboard = new FindBoard();
		
//		findboard.setFindno알겠습니다
		findboard.setTitle( req.getParameter("title") );
		findboard.setContent( req.getParameter("content") );
		
		Connection conn = JDBCTemplate.getConnection();
		
		if(findboard != null) {
			if(findBoardDao.update(conn, findboard) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		
	}




	
			
}
