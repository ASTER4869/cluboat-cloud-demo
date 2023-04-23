package com.cluboat.springcloud.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.DTO.GetBudgetItemDTO;
import com.cluboat.springcloud.entity.DTO.GetBudgetsDTO;
import com.cluboat.springcloud.entity.DTO.GetBudgetsDetailDTO;
import com.cluboat.springcloud.entity.param.CreateBudgetsItemParam;
import com.cluboat.springcloud.entity.param.CreateBudgetsParam;
import com.cluboat.springcloud.entity.param.UpdateBudgetsParam;
import com.cluboat.springcloud.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/budgets")
public class BudgetsController {
    @Resource
    BudgetsService budgetsService;

    @Resource
    ClubService clubService;
    @Resource
    BelongService belongService;

    @Resource
    UserInfoService userInfoService;

    @Resource
    BudgetItemService budgetItemService;

    //获取某社团提交的预算列表
    @GetMapping("/{clubId}")
    public CommonResult getBudgets(@PathVariable Integer clubId) {
        try {
            LambdaQueryWrapper<BudgetsEntity> wrapper = new LambdaQueryWrapper<BudgetsEntity>()
                    .eq(BudgetsEntity::getClubId, clubId);
            List<BudgetsEntity> budgetsEntityList = budgetsService.list(wrapper);
            if (budgetsEntityList == null || budgetsEntityList.isEmpty()){
                return new CommonResult(444, "无记录");
            }
            List<GetBudgetsDTO> budgetsDTOList = new ArrayList<GetBudgetsDTO>();
            for (BudgetsEntity budgetsEntity: budgetsEntityList){
                LambdaQueryWrapper<UserInfoEntity> userWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                        .eq(UserInfoEntity::getUserId, budgetsEntity.getApplicantId());
                UserInfoEntity user = userInfoService.getOne(userWrapper, false);
                if(user == null){
                    return new CommonResult(400, "获取失败，该用户id不存在");
                }
                GetBudgetsDTO budgetsDTO = new GetBudgetsDTO();
                budgetsDTO.setBudgetId(budgetsEntity.getBudgetId());
                budgetsDTO.setTitle(budgetsEntity.getTitle());
                budgetsDTO.setAmount(budgetsEntity.getAmount());
                budgetsDTO.setApplicantName(user.getUserName());
                budgetsDTO.setCreateTime(budgetsEntity.getCreateTime());
                budgetsDTO.setStatus(budgetsEntity.getStatus());
                budgetsDTO.setFeedback(budgetsEntity.getFeedback());
                budgetsDTOList.add(budgetsDTO);
            }
            return new CommonResult(200, "获取成功", budgetsDTOList);

        }catch (Exception e){
            return new CommonResult(400, "获取失败", e);
        }

    }

    //获取所有预算
    @GetMapping()
    public CommonResult getAllBudgets() {
        try {
            List<BudgetsEntity> budgetsEntityList = budgetsService.list();
            if (budgetsEntityList == null || budgetsEntityList.isEmpty()){
                return new CommonResult(444, "无记录");
            }
            List<GetBudgetsDTO> budgetsDTOList = new ArrayList<GetBudgetsDTO>();
            for (BudgetsEntity budgetsEntity: budgetsEntityList){
                LambdaQueryWrapper<UserInfoEntity> userWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                        .eq(UserInfoEntity::getUserId, budgetsEntity.getApplicantId());
                UserInfoEntity user = userInfoService.getOne(userWrapper, false);
                if(user == null){
                    return new CommonResult(400, "获取失败，该用户id不存在");
                }
                GetBudgetsDTO budgetsDTO = new GetBudgetsDTO();
                budgetsDTO.setBudgetId(budgetsEntity.getBudgetId());
                budgetsDTO.setTitle(budgetsEntity.getTitle());
                budgetsDTO.setAmount(budgetsEntity.getAmount());
                budgetsDTO.setApplicantName(user.getUserName());
                budgetsDTO.setCreateTime(budgetsEntity.getCreateTime());
                budgetsDTO.setStatus(budgetsEntity.getStatus());
                budgetsDTO.setFeedback(budgetsEntity.getFeedback());
                budgetsDTOList.add(budgetsDTO);
            }
            return new CommonResult(200, "获取成功", budgetsDTOList);

        }catch (Exception e){
            return new CommonResult(400, "获取失败", e);
        }

    }

    //获取某一预算详情
    @GetMapping("/detail/{budgetId}")
    public CommonResult getBudgetsDetail(@PathVariable Integer budgetId) {
        try {
            //先判断是否有该预算
            LambdaQueryWrapper<BudgetsEntity> wrapper = new LambdaQueryWrapper<BudgetsEntity>()
                    .eq(BudgetsEntity::getBudgetId, budgetId);
            BudgetsEntity budgetsEntity = budgetsService.getOne(wrapper, false);
            if (budgetsEntity == null){
                return new CommonResult(444, "无记录");
            }
            //已经有该预算了
            GetBudgetsDetailDTO budgetsDetailDTO = new GetBudgetsDetailDTO();
            budgetsDetailDTO.setTitle(budgetsEntity.getTitle());
            budgetsDetailDTO.setAmount(budgetsEntity.getAmount());
            budgetsDetailDTO.setCreateTime(budgetsEntity.getCreateTime());
            //获取社团名称和申请人姓名
            LambdaQueryWrapper<ClubEntity> clubWrapper = new LambdaQueryWrapper<ClubEntity>()
                    .eq(ClubEntity::getClubId, budgetsEntity.getClubId());
            ClubEntity clubEntity = clubService.getOne(clubWrapper, false);
            budgetsDetailDTO.setClubName(clubEntity.getClubName());
            LambdaQueryWrapper<UserInfoEntity> userInfoWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                    .eq(UserInfoEntity::getUserId, budgetsEntity.getApplicantId());
            UserInfoEntity userInfoEntity = userInfoService.getOne(userInfoWrapper, false);
            budgetsDetailDTO.setApplicantName(userInfoEntity.getUserName());
            //获取预算子项
            LambdaQueryWrapper<BudgetItemEntity> itemWrapper = new LambdaQueryWrapper<BudgetItemEntity>()
                    .eq(BudgetItemEntity::getBudgetId, budgetsEntity.getBudgetId());
            List<BudgetItemEntity> budgetItemEntityList = budgetItemService.list(itemWrapper);
            //如果有子项
            if(budgetItemEntityList != null && budgetItemEntityList.isEmpty() == false){
                List<GetBudgetItemDTO> budgetItemDTOList = new ArrayList<GetBudgetItemDTO>();
                for (BudgetItemEntity budgetItemEntity: budgetItemEntityList){
                    GetBudgetItemDTO budgetItemDTO = new GetBudgetItemDTO();
                    budgetItemDTO.setName(budgetItemEntity.getName());
                    budgetItemDTO.setMoney(budgetItemEntity.getMoney());
                    budgetItemDTO.setDescription(budgetItemEntity.getDescription());
                    budgetItemDTOList.add(budgetItemDTO);
                }
                budgetsDetailDTO.setItem(budgetItemDTOList);
            }
            return new CommonResult(200, "获取成功", budgetsDetailDTO);

        }catch (Exception e){
            return new CommonResult(400, "获取失败", e);
        }

    }

    @PostMapping()
    public CommonResult createBudgets(@RequestBody CreateBudgetsParam budgetsParam){
        try {
            //先判断该社团以及该用户是否存在
            LambdaQueryWrapper<ClubEntity> clubWrapper = new LambdaQueryWrapper<ClubEntity>()
                    .eq(ClubEntity::getClubId, budgetsParam.getClubId());
            if (clubService.getOne(clubWrapper) == null){
                return new CommonResult(444, "该社团不存在");
            }
            LambdaQueryWrapper<UserInfoEntity> userInfoWrapper = new LambdaQueryWrapper<UserInfoEntity>()
                    .eq(UserInfoEntity::getUserId, budgetsParam.getApplicantId());
            if (userInfoService.getOne(userInfoWrapper) == null){
                return new CommonResult(444, "该用户不存在");
            }
            //再判断该申请人是否是该社团的一员
            LambdaQueryWrapper<BelongEntity> wrapper = new LambdaQueryWrapper<BelongEntity>()
                    .eq(BelongEntity::getClubId, budgetsParam.getClubId())
                    .eq(BelongEntity::getUserId, budgetsParam.getApplicantId());
            BelongEntity belongEntity = belongService.getOne(wrapper, false);
            if (belongEntity == null){
                return new CommonResult(444, "该用户不是该社团成员");
            }
            //先创建预算表内容
            BudgetsEntity budgetsEntity = new BudgetsEntity();
            budgetsEntity.setClubId(budgetsParam.getClubId());
            budgetsEntity.setApplicantId(budgetsParam.getApplicantId());
            budgetsEntity.setAmount(budgetsParam.getAmount());
            budgetsEntity.setTitle(budgetsParam.getTitle());
            budgetsEntity.setStatus("待审批");
            budgetsEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            budgetsService.save(budgetsEntity);

            //获取最新的budget_id
            LambdaQueryWrapper<BudgetsEntity> tmpWrapper = new LambdaQueryWrapper<BudgetsEntity>()
                    .orderByDesc(BudgetsEntity::getBudgetId);
            BudgetsEntity tmp = budgetsService.getOne(tmpWrapper, false);
            Integer budgetId = tmp.getBudgetId();

            List<CreateBudgetsItemParam> budgetsItemParamList = budgetsParam.getItemList();
            //再创建预算项内容
            for (CreateBudgetsItemParam item: budgetsItemParamList){
                BudgetItemEntity budgetItemEntity = new BudgetItemEntity();
                budgetItemEntity.setName(item.getName());
                budgetItemEntity.setMoney(item.getMoney());
                budgetItemEntity.setDescription(item.getDescription());
                budgetItemEntity.setBudgetId(budgetId);
                budgetItemService.save(budgetItemEntity);
            }

            return new CommonResult(200, "创建成功");

        }catch (Exception e){
            return new CommonResult(400, "创建失败", e);
        }
    }

    @PutMapping
    public CommonResult updateStatus(@RequestBody UpdateBudgetsParam budgetsParam){
        try {
            //先判断该预算是否存在
            LambdaQueryWrapper<BudgetsEntity> wrapper = new LambdaQueryWrapper<BudgetsEntity>()
                    .eq(BudgetsEntity::getBudgetId, budgetsParam.getBudgetId());
            BudgetsEntity budgetsEntity = budgetsService.getOne(wrapper, false);
            if (budgetsEntity == null){
                return new CommonResult(444, "操作失败，该预算不存在");
            }
            //再判断状态是否与当前状态一致
            if (budgetsEntity.getStatus().equals(budgetsParam.getStatus())){
                return new CommonResult(400, "操作失败，该预算状态已经是" + budgetsParam.getStatus() + "，无需修改");
            }
            List<String> status = Arrays.asList("待审批", "已通过", "已拒绝");
            if(status.indexOf(budgetsParam.getStatus()) < 0){
                return new CommonResult(400, "操作失败，目标状态非法");
            }
            budgetsEntity.setStatus(budgetsParam.getStatus());
            budgetsEntity.setFeedback(budgetsParam.getFeedback());
            budgetsService.saveOrUpdate(budgetsEntity);
            if(budgetsParam.getStatus().equals("已通过")){
                return new CommonResult(200, "已通过该申请");
            }
            else if(budgetsParam.getStatus().equals("已拒绝")){
                return new CommonResult(200, "已驳回该申请");
            }
            return new CommonResult(200, "修改成功");

        }catch (Exception e){
            return new CommonResult(400, "操作失败", e);
        }
    }


}
