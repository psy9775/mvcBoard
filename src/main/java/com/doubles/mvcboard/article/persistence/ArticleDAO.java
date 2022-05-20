package com.doubles.mvcboard.article.persistence;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.commons.paging.SearchCriteria;

import java.util.List;

public interface ArticleDAO {

    /*게시글 등록*/
    void create(ArticleVO aRticleVO) throws Exception;

    /*게시글 상세 조회*/
    ArticleVO read(Integer articleNo) throws Exception;

    /*게시글 수정*/
    void update(ArticleVO articleVO) throws Exception;

    /*게시글 삭제*/
    void delete(Integer articleNo) throws Exception;

    /*게시글 목록 조회*/
    List<ArticleVO> listAll() throws Exception;

    /*게시글 페이징 처리*/
    List<ArticleVO> listPaging(int page) throws Exception;

    /*게시글 페이징*/
    List<ArticleVO> listCriteria(Criteria criteria) throws Exception;

    /*게시글 전체 수 카운트*/
    int countArticles(Criteria criteria) throws Exception;

    /*게시글 검색 목록 조회*/
    List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception;

    /*게시글 검색 카운트*/
    int countSearchedArticles(SearchCriteria searchCriteria) throws Exception;

    /*게시글 댓글 갯수 갱신*/
	void updateReplyCnt(Integer articleNo, int amount) throws Exception;

	/*게시글 조회수 증가처리*/
	void updateViewCnt(Integer articleNo) throws Exception;

}
