package com.doubles.mvcboard.article.controller;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.service.ArticleService;
import com.doubles.mvcboard.commons.paging.PageMaker;
import com.doubles.mvcboard.commons.paging.SearchCriteria;
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

@Controller
@RequestMapping("/article/paging/search")
public class ArticlePagingSearchController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePagingSearchController.class);

    private final ArticleService articleService;

    @Inject
    public ArticlePagingSearchController(ArticleService articleService){
        this.articleService = articleService;
    }

    //게시판 목록(검색필터적용)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                       Model model) throws Exception {
        logger.info("목럭 조회(검색)");
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        //pageMaker.setTotalCount(articleService.countArticles(searchCriteria));
        pageMaker.setTotalCount(articleService.countSearchArticles(searchCriteria));
        //model.addAttribute("articles", articleService.listCriteria(searchCriteria));
        model.addAttribute("articles", articleService.listSearch(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);
        return "article/search/list";
    }

	//등록 페이지이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGET() throws Exception {
		logger.info("등록 페이지이동");
		return "/article/search/write";
	}

	//등록처리
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePOST(ArticleVO articleVO,
							RedirectAttributes redirectAttributes) throws Exception {
		logger.info("등록처리");
		//ArticleVO vo = articleVO;
		articleService.create(articleVO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		return "redirect:/article/search/list";
	}

    //게시판 상세 페이지 이동
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("articleNo") int articleNo,
                       @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                       Model model) throws Exception {
        logger.info("상세페이지 이동");
        model.addAttribute("article", articleService.read(articleNo));
        return "article/search/read";
    }

    //게시판 수정 페이지 이동
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("articleNo") int articleNo,
                            @ModelAttribute("searchCriteria") SearchCriteria searchCriteria,
                            Model model) throws Exception {
        logger.info("수정페이지 이동");
        model.addAttribute("article", articleService.read(articleNo));
        return "article/search/modify";
    }

    //게시판 수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPost(ArticleVO articleVO,
                             SearchCriteria searchCriteria,
                             RedirectAttributes redirectAttributes) throws Exception {
        logger.info("수정처리");
        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", searchCriteria.getPage());
        redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
        redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());
        redirectAttributes.addFlashAttribute("msg", "수정성공");
        return "redirect:/article/paging/search/list";
    }

    //게시판 삭제 처리
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("articleNo") int articleNo,
                         SearchCriteria searchCriteria,
                         RedirectAttributes redirectAttributes) throws Exception {
        logger.info("삭제처리");
        articleService.delete(articleNo);
        redirectAttributes.addAttribute("page", searchCriteria.getPage());
        redirectAttributes.addAttribute("perPageNum", searchCriteria.getPerPageNum());
        redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
        redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword());
        redirectAttributes.addFlashAttribute("msg", "삭제성공");
        return "redirect:/article/paging/search/list";
    }


}
