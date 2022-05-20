package com.doubles.mvcboard.tutorial.reply;

import com.doubles.mvcboard.reply.domain.ReplyVO;
import com.doubles.mvcboard.reply.persistence.ReplyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ReplyDAOTest {

	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);

	@Inject
	private ReplyDAO replyDAO;

	@Test
	public void testReplyCreate() throws Exception {
		/*더미 데이터 생성*/
		for(int i = 2; i <= 100; i++){
			ReplyVO replyVO = new ReplyVO();
			replyVO.setArticleNo(1000);
			replyVO.setReplyText(i+"번째 댓글입니다..");
			replyVO.setReplyWriter("user@"+(i%10));
			replyDAO.create(replyVO);
		}
	}

	@Test
	public void testReplyList() throws Exception {
		logger.info(replyDAO.list(1000).toString());
	}

	@Test
	public void testReplyUpdate() throws Exception {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyNo(55);
		replyVO.setReplyText(55+"번째 3차 수정댓글입니다..");
		replyDAO.update(replyVO);
	}

	@Test
	public void testReplyDelete() throws Exception {
		replyDAO.delete(3);
	}
}
