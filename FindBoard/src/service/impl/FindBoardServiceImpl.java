package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
	public FindBoard views(FindBoard find_no) {
		Connection conn = JDBCTemplate.getConnection();

		//조회수 증가
		if( findBoardDao.updateViews(conn, find_no) >= 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		FindBoard board = findBoardDao.selectBoardByFindno(conn, find_no); 
		
		return board;
	}

	@Override
	public FindBoard getParam(HttpServletRequest req) {
		
		//FindBoardno를 저장할 객체 생성
		FindBoard findNo = new FindBoard();
		
		//FindBoardno 전달 파라미터 검증
		String param = req.getParameter("findNo");
		if(param!=null && !"".equals(param)) {
			
			//Findboardno 전달파라미터 추출
			findNo.setFindNo( Integer.parseInt(param) );
		}
				
		// 결과 반환
		return findNo;
	}

	@Override
	public FindBoard read(FindBoard findNo) {
		
		Connection conn = JDBCTemplate.getConnection();

		//조회수 증가
		if( findBoardDao.updateHit(conn, findNo) >= 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		FindBoard board = findBoardDao.selectFind(conn, findNo); 
		
		return board;
	}

	
	@Override
	public String getnick(FindBoard viewFindBoard) {
		return findBoardDao.selectNickByUserNo(JDBCTemplate.getConnection(), viewFindBoard);
	}

	@Override
	public FindImg viewFile(FindBoard viewFindBoard) {
		return findBoardDao.selectFile(JDBCTemplate.getConnection(), viewFindBoard);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		/* <<INSER DATA>>
		 * 게시글: userno(session), title, petname, petkinds, petage, loc, content
		 * NOT NULL: userno, title, petkinds, loc
		 * 
		 * 첨부사진: imgnum, findno, originimg, storedimg 
		 */
		
		FindBoard findBoard = null;
		FindImg findImg = null;
		
		boolean isMutltipart = false;
		isMutltipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMutltipart) {
			System.err.println("multipart/form-data 형식이 아닙니다.");
			return;
		}
		
		findBoard = new FindBoard();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		final int MEM_SIZE = 1 * 1024; 	//1KB
		factory.setSizeThreshold(MEM_SIZE);
		
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();
		
		factory.setRepository(repository);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10 * MEM_SIZE);	//10MB
		
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			if(item.getSize() <= 0)	continue;
			
			if(item.isFormField()) {
				String key = item.getFieldName();
				
				//NOT NULL 데이터 먼저 처리 (NOT NULL: userno, title, petkinds, loc)
				if(key != null && !"".equals(key)) {
					if("userno".equals(key)) {
						try {
							findBoard.setUserNo(Integer.parseInt(item.getString("UTF-8")));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					} //if(userno.key) END
					
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
							findBoard.setLoc(item.getString("UTF-8"));
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
			
			
			if(!item.isFormField()) {
				UUID uuid = UUID.randomUUID();
				String uid = uuid.toString().split("-")[0];
				String storedName = item.getName() + "_" + uid;
				
				File uploFolder = new File(req.getServletContext().getRealPath("upload"));
				uploFolder.mkdir();
				
				File upFile = new File(uploFolder, storedName);
				
				findImg = new FindImg();
				findImg.setOriginImg(item.getName());
				findImg.setStoredImg(storedName);
				
				try {
					item.write(upFile);
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} //if(!ifFormField) END
			
		} //while(iter.hasnext) END
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		int findno = findBoardDao.selectFindno(conn);
		if(findBoard != null) {
			findBoard.setFindNo(findno);
			
			if(findBoardDao.insert(conn, findBoard) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		if(findImg != null) {
			findImg.setFindNo(findno);
			
			if(findBoardDao.insertImg(conn, findImg) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	} //write() END
	
			
}
