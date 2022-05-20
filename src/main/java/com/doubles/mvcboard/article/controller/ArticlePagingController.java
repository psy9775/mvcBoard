package com.doubles.mvcboard.article.controller;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.service.ArticleService;
import com.doubles.mvcboard.commons.paging.Criteria;
import com.doubles.mvcboard.commons.paging.PageMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);

    private final ArticleService articleService;

    @Inject
    public ArticlePagingController(ArticleService articleService){
        this.articleService = articleService;
    }

    //등록 페이지이동
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() throws Exception {
        logger.info("등록 페이지이동");
        return "/article/paging/write";
    }

    //등록처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleVO articleVO,
                            RedirectAttributes redirectAttributes) throws Exception {
        logger.info("등록처리");
        //ArticleVO vo = articleVO;
        articleService.create(articleVO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");
        return "redirect:/article/paging/list";
    }

    //목록 페이지이동
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPaging(Model model,
                             Criteria criteria) throws Exception {
        //페이지 번호 출력처리를 위한 파라미터들 : 시작 페이지 번호, 끝 페이지 번호, 전체 게시글의 갯수, 이전 링크, 다음링크
        //끝 페이지 번호 계산식 : Math.ceil(현재 페이지 번호 / 페이지 번호의 갯수) * 페이지 번호의 갯수
        //시작 페이지 번호 계산식 : (끝 페이지 번호 - 페이지 번호의 갯수 ) + 1
        //끝 페이지 번호의 보정 계산식 : Math.ceil(전체 게시글의 갯수 / 페이지 당 출력할 게시글의 갯수)
        //이전 링크 활성/비활성 계산식 : 시작 페이지 번호 == 1 ? false : true
        //다음 링크 활성/비활성 계산식 : 끝 페이지 번호 * 페이지 당 출려갛 ㄹ게시글 갯수 >= 전체 게시글의 갯수 ? false : true
        logger.info("listPaging GET");
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        //전체 게시글 갯수 조회
        pageMaker.setTotalCount(articleService.countArticles(criteria));
        model.addAttribute("articles", articleService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);
        return "/article/paging/list";
    }

    //상세 페이지 이동
    //조회, 수정, 삭제 처리 후에 다시 특정 목록 페이지로 이동하기 위해 각 페이지를 이동 할때마다 필요한 값
    //page - 현재 목록페이지의 번호
    //perPageNum - 페이지당 출력할 게시글의 갯수
    //articleNo - 조회게시글의 번호
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String readPaging(@RequestParam("articleNo") int articleNo,
                             @ModelAttribute("criteria") Criteria criteria,
                             Model model) throws Exception{
        logger.info("상세페이지 이동");
        model.addAttribute("article", articleService.read(articleNo));
        return "/article/paging/read";
    }

    //수정 페이지이동
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modifyGetPaging(@RequestParam("articleNo") int articleNo,
                                  @ModelAttribute("criteria") Criteria criteria,
                                  Model model) throws Exception{
        logger.info("수정페이지 이동");
        model.addAttribute("article", articleService.read(articleNo));
        return "/article/paging/modify";
    }

    //수정처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPostPaging(ArticleVO articleVO,
                                   Criteria criteria,
                                   RedirectAttributes redirectAttributes) throws Exception {
        logger.info("수정처리");
        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "modSuccess");
        return "redirect:/article/paging/list";
    }

    //삭제처리
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removePaing(@RequestParam("articleNo") int articleNo,
                              Criteria criteria,
                              RedirectAttributes redirectAttributes) throws Exception {
        logger.info("삭제처리");
        articleService.delete(articleNo);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "delSuccess");
        return "redirect:/article/paging/remove";
    }

}
