package com.cluboat.springcloud.service.impl;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.param.ActivityApplyClubParam;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityApplyServiceImplTest {

    @Resource
    ActivityApplyServiceImpl activityApplyServiceImpl;

    @BeforeClass
    public static void startTest(){
        System.out.println("开始测试...");
    }

    @Test
    public void testGetActivity1() {
        ActivityApplyClubParam activityApplyClubParam = new ActivityApplyClubParam();
        activityApplyClubParam.setClubId(-1);
        activityApplyClubParam.setStatus("正常");
        List<ActivityApplyEntity> test_list = activityApplyServiceImpl.getActivityApplyList(activityApplyClubParam);
        String test = test_list.get(test_list.size()-1).getActivityTitle();
        Assert.assertEquals("查询社团不存在",test);
    }

    @Test
    public void testGetActivity2() {
        ActivityApplyClubParam activityApplyClubParam = new ActivityApplyClubParam();
        activityApplyClubParam.setClubId(666);
        activityApplyClubParam.setStatus("待审批");
        List<ActivityApplyEntity> test_list = activityApplyServiceImpl.getActivityApplyList(activityApplyClubParam);
        String test = test_list.get(test_list.size()-1).getActivityTitle();
        Assert.assertEquals("查询社团不存在",test);
    }

    @Test
    public void testGetActivity3() {
        ActivityApplyClubParam activityApplyClubParam = new ActivityApplyClubParam();
        activityApplyClubParam.setClubId(13);
        activityApplyClubParam.setStatus("待审批");
        List<ActivityApplyEntity> test_list = activityApplyServiceImpl.getActivityApplyList(activityApplyClubParam);
        String test = test_list.get(test_list.size()-1).getActivityTitle();
        Assert.assertEquals("满足查询条件的社团活动为空",test);
    }

    @Test
    public void testApplyForActivity1() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(time);
        activityApplyEntity.setClubId(1);
        activityApplyEntity.setActivityTitle("123");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("活动地点为空",test);
    }

    @Test
    public void testApplyForActivity2() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("317");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(null);
        activityApplyEntity.setClubId(1);
        activityApplyEntity.setActivityTitle("123");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("活动时间不符合规范",test);
    }

    @Test
    public void testApplyForActivity3() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("317");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(time);
        activityApplyEntity.setClubId(233);
        activityApplyEntity.setActivityTitle("123");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("创建社团不存在",test);
    }

    @Test
    public void testApplyForActivity4() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("317");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(time);
        activityApplyEntity.setClubId(1);
        activityApplyEntity.setActivityTitle("@23");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("活动标题含有非法字符",test);
    }

    @Test
    public void testApplyForActivity5() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("317");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(time);
        activityApplyEntity.setClubId(1);
        activityApplyEntity.setActivityTitle("");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("活动标题为空",test);
    }

    @Test
    public void testApplyForActivity6() {
        ActivityApplyEntity activityApplyEntity = new ActivityApplyEntity();
        activityApplyEntity.setActivityLocation("317");
        Date time = new Date(123);
        activityApplyEntity.setActivityDate(time);
        activityApplyEntity.setClubId(1);
        activityApplyEntity.setActivityTitle("活动预告");
        String test = activityApplyServiceImpl.applyForActivity(activityApplyEntity);
        Assert.assertEquals("活动申请提交成功",test);
    }



    @AfterClass
    public static void endTest(){
        System.out.println("结束测试...");
    }
}