package service.impl;

import com.xsl.user.UserInfoResouce;
import mapper.XslAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslAccount;
import resource.TaskInfoResource;
import service.GenerateAccount;
import util.UUIDUtil;

import javax.annotation.Resource;

@Service
public class GenerateAccountImpl implements GenerateAccount {

@Resource
private TaskInfoResource taskInfoResource;
@Resource
private UserInfoResouce userInfoResouce;
@Autowired
private XslAccountMapper xslAccountMapper;
    @Override
    public void generateAccount() {

       try {

           XslAccount xslAccount=new XslAccount();
           XslAccount old=xslAccountMapper.selectLastOne();
           int totalTask=taskInfoResource.totalTask();
           int totalMoney=taskInfoResource.totalMoney();
           int totalDoneTask=taskInfoResource.totalDoneTask();
           int totalUser=userInfoResouce.getUserNums();
           int oldtotalTask=old.getTotalTask();
           float oldtotalMoney=old.getTotalMoney();
           int oldtotalDoneTask=old.getTotalDoneTask();
           int oldtotalUser=old.getTotalUser();
           xslAccount.setTotalUser(totalUser);
           xslAccount.setNewUser(totalUser-oldtotalUser);

           xslAccount.setTotalTask(totalTask);
           xslAccount.setNewTask(totalTask-oldtotalTask);

           xslAccount.setTotalDoneTask(totalDoneTask);
           xslAccount.setNewDoneTask(totalDoneTask-oldtotalDoneTask);

           xslAccount.setTotalMoney(totalMoney);
           xslAccount.setNewMoney(totalMoney-oldtotalMoney);

           xslAccount.setAccountId(UUIDUtil.getUUID());
           xslAccountMapper.insert(xslAccount);

       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }
}