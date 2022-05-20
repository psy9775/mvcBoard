package com.doubles.mvcboard.reply.domain;

import java.util.Date;

public class ReplyVO {

    private Integer replyNo;			/*리플번호*/
    private Integer articleNo;			/*게시글번호*/
    private String replyText;			/*리플내용*/
    private String replyWriter;			/*리플작성자*/
    private Date regDate;				/*리플등록일자*/
    private Date updateDate;			/*리플수정일자*/

    public Integer getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(Integer replyNo) {
        this.replyNo = replyNo;
    }

    public Integer getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(Integer articleNo) {
        this.articleNo = articleNo;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getReplyWriter() {
        return replyWriter;
    }

    public void setReplyWriter(String replyWriter) {
        this.replyWriter = replyWriter;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "replyNo=" + replyNo +
                ", articleNo=" + articleNo +
                ", replyText='" + replyText + '\'' +
                ", replyWriter='" + replyWriter + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
