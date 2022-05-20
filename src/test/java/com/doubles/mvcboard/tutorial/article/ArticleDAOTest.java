package com.doubles.mvcboard.tutorial.article;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.persistence.ArticleDAO;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.commons.paging.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ArticleDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOTest.class);

    @Inject
    private ArticleDAO articleDAO;

    @Test
    public void testCreate() throws Exception{

        /*ArticleVO article = new ArticleVO();
        article.setTitle("새로운 글 작성 테스트 제목");
        article.setContent("새로운 글 작성 테스트 내용");
        article.setWriter("새로운 글 작성자");
        article.setRegDate(new Date());
        articleDAO.create(article);*/

        /*더미데이터 생성*/
        for (int i = 1; i <= 1000; i++) {
            ArticleVO articleVO = new ArticleVO();
            articleVO.setTitle(i+"번째 글 제목입니다");
            articleVO.setContent(i+"번째 글 내용입니다");
            articleVO.setWriter("user0"+(i%10));

            articleDAO.create(articleVO);
        }

    }

    @Test
    public void testRead() throws Exception{

        logger.info(articleDAO.read(1).toString());

    }

    @Test
    public void testUpdate() throws Exception{

        ArticleVO article = new ArticleVO();
        article.setArticleNo(1);
        article.setTitle("글 수정 테스트 제목");
        article.setContent("글 수정 테스트 내용");
        articleDAO.update(article);

    }

    @Test
    public void testDelete() throws Exception{

        articleDAO.delete(1);
    }

    @Test
    public void testListPaging() throws Exception {

        int page = 3;

        List<ArticleVO> articles = articleDAO.listPaging(page);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + ":" + article.getTitle());
        }
    }

    @Test
    public void testListCriteria() throws Exception {

        Criteria criteria = new Criteria();
        criteria.setPage(3);
        criteria.setPerPageNum(20);

        List<ArticleVO> articles = articleDAO.listCriteria(criteria);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + ":" + article.getTitle());
        }

    }

    @Test
    public void testURI() throws Exception {

        //UricomponentsBuilder를 이용하는 방식 테스트
        //Uricomponents 클래스를 통해 path나 query에 해당하는 문자열을 추가해서 원하는 URI를 생성 가능
        UriComponents uricomponents = UriComponentsBuilder.newInstance()
                .path("/article/read")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/article/read?articleNo=12&perPageNum=20");
        logger.info(uricomponents.toString());

    }

    @Test
    public void testURI2() throws Exception {

        //미리 필요한 경로를 설정해두고 {module}고 ㅏ같은 경로를 article로 {page}를 read로 변경 가능
        UriComponents uriComponents = UriComponentsBuilder.newInstance()

                .path("/{module}/{page}")
                .queryParam("articleNo", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("article", "read")
                .encode();

        logger.info("/article/read?articleNo-12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testDynamic1() throws Exception {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPage(1);
        searchCriteria.setKeyword("999");
        searchCriteria.setSearchType("t");

        logger.info("=========================================================");

        List<ArticleVO> articles = articleDAO.listSearch(searchCriteria);

        for (ArticleVO article : articles) {
            logger.info(article.getArticleNo() + " : " + article.getTitle());
        }

        logger.info("=========================================================");

        logger.info("searched articles count : " + articleDAO.countSearchedArticles(searchCriteria));

    }

}
