package com.doubles.mvcboard.article.controller;

import com.doubles.mvcboard.article.domain.ArticleVO;
import com.doubles.mvcboard.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Inject
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    //등록 페이지이동
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET(){
        logger.info("등록 페이지이동");
        return "article/normal/write";
    }

    //등록처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("등록처리");
        ArticleVO vo = articleVO;
        articleService.create(vo);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");
        return "article/normal/list";
    }

    //목록 페이지이동
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        logger.info("목록 페이지이동");
        List<ArticleVO> articleList = articleService.listAll();
        model.addAttribute("articles", articleList);
        //System.out.println(articleList.get(0).getArticleNo());
        return "article/normal/list";
    }

    //상세 페이지 이동
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
        logger.info("상세 페이지이동");
        ArticleVO articleVO = articleService.read(articleNo);
        model.addAttribute("article", articleVO);
        return "article/normal/read";
    }

    //수정 페이지이동
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
        logger.info("수정 페이지이동");
        ArticleVO articleVO = articleService.read(articleNo);
        model.addAttribute("article", articleVO);
        return "article/normal/modify";
    }

    //수정처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("수정처리");
        ArticleVO vo = articleVO;
        articleService.update(vo);
        redirectAttributes.addFlashAttribute("msg", "modSuccess");
        return "article/normal/list";
    }

    //삭제처리
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("articleNo") int articleNo, RedirectAttributes redirectAttributes) throws Exception {
        logger.info("삭제처리");
        articleService.delete(articleNo);
        redirectAttributes.addFlashAttribute("msg", "delSuccess");
        return "article/normal/list";
    }

}
