package yu.mthgh123.booksmall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yu.mthgh123.booksmall.service.BooksMallGoodsService;
import yu.mthgh123.booksmall.util.Result;
import yu.mthgh123.booksmall.util.ResultGenerator;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class BooksElasticSearch {

    @Autowired
    private BooksMallGoodsService booksService;

    @GetMapping("/es")
    public Result search(@RequestParam("keywords") String keywords, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        if(keywords==null) {
            return ResultGenerator.genFailResult("search fail!");
        }
        List<Map<String,Object>> booksPageList=booksService.esSearchBooksList(keywords,pageNum,pageSize);
        if(booksPageList==null){
            return ResultGenerator.genFailResult("search fail!");
        }
        else {
            return ResultGenerator.genSuccessResult(booksPageList);
        }
    }
}
