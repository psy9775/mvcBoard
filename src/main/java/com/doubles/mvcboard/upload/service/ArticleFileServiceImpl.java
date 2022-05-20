package com.doubles.mvcboard.upload.service;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.upload.persistence.ArticleFileDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ArticleFileServiceImpl implements ArticleFileService{

	private final ArticleFileDAO articleFileDAO;

	@Inject
	public ArticleFileServiceImpl(ArticleFileDAO articleFileDAO){
		this.articleFileDAO = articleFileDAO;
	}

	//첨부파일 목록
	@Override
	public List<String> getArticleFiles(Integer articleNo) throws Exception {
		return articleFileDAO.getArticleFiles(articleNo);
	}

	//첨부파일 삭제
	@Transactional
	@Override
	public void deleteFile(String fileName, Integer articleNo) throws Exception {
		articleFileDAO.deleteFile(fileName);
		articleFileDAO.updateFileCnt(articleNo);
	}

}
