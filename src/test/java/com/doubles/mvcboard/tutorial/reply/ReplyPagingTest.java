package com.doubles.mvcboard.tutorial.reply;

import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.reply.domain.ReplyVO;
import com.doubles.mvcboard.reply.persistence.ReplyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ReplyPagingTest {

	private static final Logger logger = LoggerFactory.getLogger(ReplyPagingTest.class);

	@Inject
	private ReplyDAO replyDAO;

	@Test
	public void testReplyPaging() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setPerPageNum(20);
		criteria.setPage(1);
		List<ReplyVO> replies = replyDAO.listPaging(1000, criteria);

		for (ReplyVO reply : replies) {
			logger.info(reply.getReplyNo() + " : " + reply.getReplyText());
		}
	}

}
