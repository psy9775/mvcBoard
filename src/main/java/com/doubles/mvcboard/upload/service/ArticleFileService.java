package com.doubles.mvcboard.upload.service;

import com.doubles.mvcboard.article.domain.ArticleVO;

import java.util.List;

public interface ArticleFileService {

	//첨부파일 목록
	List<String> getArticleFiles(Integer articleNo) throws Exception;

	//첨부파일 삭제
	void deleteFile(String fileName, Integer articleNo) throws Exception;

}
