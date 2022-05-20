package com.doubles.mvcboard.article.domain;

import java.util.Arrays;
import java.util.Date;

public class ArticleVO {

    private Integer articleNo;      /*게시글_번호*/
    private String title;           /*게시글_제목*/
    private String content;         /*게시글_내용*/
    private String writer;          /*게시글_작성자*/
    private Date regDate;           /*게시글_등록시간*/
    private int viewCnt;            /*게시글_조회수*/
	private int replyCnt;			/*게시글 댓글수*/

	/*첨부파일 관련 변수 선언*/
	private String[] files;
	private int fileCnt;

	public int getReplyCnt() { return replyCnt; }

	public void setReplyCnt(int replyCnt) { this.replyCnt = replyCnt; }

	public Integer getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(Integer articleNo) {
        this.articleNo = articleNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public int getFileCnt() {return fileCnt;}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	@Override
	public String toString() {
		return "ArticleVO{" +
				"articleNo=" + articleNo +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", writer='" + writer + '\'' +
				", regDate=" + regDate +
				", viewCnt=" + viewCnt +
				", replyCnt=" + replyCnt +
				", files=" + Arrays.toString(files) +
				", fileCnt=" + fileCnt +
				'}';
	}
}
