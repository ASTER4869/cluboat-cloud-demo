package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.mapper.BelongMapper;
import com.cluboat.springcloud.mapper.ClubMaMapper;

import com.cluboat.springcloud.service.BelongService;
import com.cluboat.springcloud.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clubadmin")
public class ClubAdminController {




    @Resource
    private BelongService belongService;
    @Resource
    private BelongMapper belongMapper;
    @DeleteMapping
    public CommonResult deleteById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int user_id = jsonObject.getInt("user_id");
        int club_id = jsonObject.getInt("club_id");
        QueryWrapper<Belong> wrapper=new QueryWrapper<>();

        wrapper.eq("user_id",user_id);
        wrapper.eq("club_id",club_id);

        try {
            belongService.remove(wrapper);
            return new CommonResult(200, "删除成功");
        } catch (Exception e){
            return new CommonResult(400, "删除失败",e);
        }

    }

    @PostMapping
    public CommonResult updateById(@RequestBody String json) {
        JSONObject jsonObject = new JSONObject(json);
        int user_id = jsonObject.getInt("user_id");
        int club_id = jsonObject.getInt("club_id");
        int permission = jsonObject.getInt("permission");

        QueryWrapper<Belong> wrapper=new QueryWrapper<>();
        //wrapper.eq相当于Map.put,放的是查询条件
        wrapper.eq("user_id",user_id);
        wrapper.eq("club_id",club_id);

        Belong belong = belongMapper.selectOne(wrapper);
        if(belong==null)
        {
            Belong newBelong=new Belong();
            newBelong.setClubId(club_id);
            newBelong.setUserId(user_id);
            newBelong.setPermission(permission);
            newBelong.setState(0);
            belongService.save(newBelong);
            return new CommonResult(200, "修改成功");

        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_id", user_id);
        updateWrapper.eq("club_id", club_id);
        updateWrapper.set("permission", permission);
        belongMapper.update(null,updateWrapper);
        return new CommonResult(200, "修改成功");

    }
    @Resource
    private ClubMaMapper clubMaMapper;
    @Resource
    private ClubService clubService;
    @GetMapping
    public CommonResult getById() {
        QueryWrapper<Belong> wrapper=new QueryWrapper<>();
        wrapper.eq("permission",2);
        List<Belong> clubAdminList = belongService.list(wrapper);
        List<ClubMaster> clubMaList = new ArrayList<>();

            for (int i = 0; i < clubAdminList.size(); i++) {
                System.out.println(clubAdminList.get(i));
                if (clubAdminList.get(i).getPermission() == 2) {
                    ClubMaster clubMaster = new ClubMaster();
                    clubMaster.userId = clubAdminList.get(i).getUserId();
                    clubMaster.clubId = clubAdminList.get(i).getClubId();
                    clubMaList.add(clubMaster);
                }
            }
            for (int i = 0; i < clubMaList.size(); i++) {
                UserInfoEntity s = clubMaMapper.selectById(clubMaList.get(i).userId);
                clubMaList.get(i).userName = s.getUserName();
                clubMaList.get(i).userPhotoUrl = s.getUserPhotoUrl();
            }
            for (int i = 0; i < clubMaList.size(); i++) {
                ClubEntity s = clubService.getById(clubMaList.get(i).clubId);
                clubMaList.get(i).clubName = s.getClubName();
            }
            return new CommonResult(200, "查询成功", clubMaList);

    }







    }
