package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.entity.param.NewsParam;
import com.cluboat.springcloud.entity.param.NewsUpdateParam;
import com.cluboat.springcloud.service.ClubNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/news")

public class NewsController {
    @Resource
    private ClubNewsService newsService;

    /* 根据社团id返回某社团所有新闻列表 */
    @GetMapping("/{clubId}")
    public CommonResult getAllClubNewsById(@PathVariable("clubId") int id) {
        List<NewsEntity> newsEntityList = newsService.lambdaQuery()
                .eq(NewsEntity::getClubId, id).list();
        if (newsEntityList.size() > 0) {
            return new CommonResult(200, "查询成功", newsEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    /* 删除某一新闻 */
    @DeleteMapping("/{newsId}")
    public CommonResult removeNewsById(@PathVariable("newsId") int news_id) {
        boolean isSuccess = newsService.removeById(news_id);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }

    }

    /* 增某一新闻 */
    @PostMapping
    public CommonResult createNews(@RequestBody NewsParam newsParam) {
        NewsEntity news = new NewsEntity();
        newsParam.newsTime = new Timestamp(System.currentTimeMillis());
//        System.out.println(newsParam);
        news.setNews(newsParam);
        try {
            newsService.save(news);
            return new CommonResult(200, "修改成功");
        } catch (Exception e) {
            return new CommonResult(400, "修改失败", e);
        }
    }

    /* 改某一新闻 */
    @PutMapping
    public CommonResult updateNews(@RequestBody NewsUpdateParam newsUpdateParam) {
        NewsEntity news = newsService.getById(newsUpdateParam.newsId);
        news.setNewsContent(newsUpdateParam.newsContent);
        news.setNewsTitle(newsUpdateParam.newsTitle);

        if(newsService.updateById(news)){
            return new CommonResult(200, "更新成功");
        }
        else {
            return new CommonResult(400, "更新失败");
        }
    }
}
