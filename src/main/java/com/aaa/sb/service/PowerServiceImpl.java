package com.aaa.sb.service;

import com.aaa.sb.dao.PowerDao;
import com.aaa.sb.entity.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:PowerServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-08 19:10
 */
@Service
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerDao powerDao;

    @Override
    public List<TreeNode> getList() {

        //查询数据
        List<TreeNode> powerMapList = powerDao.getList();     //定义返回列表
        List<TreeNode> powerList = new ArrayList<TreeNode>();
        //判断是否为空
        if(powerMapList!=null&&powerMapList.size()>0){
            TreeNode treeNode = null;
            //循环遍历，构造TreeNode对象，加入powerList
            for(TreeNode powerMap:powerMapList){
                //treeNode = new TreeNode(id, text, parentId, state, iconCls, url);
                treeNode = new TreeNode(Integer.valueOf(powerMap.getId()+""), powerMap.getLabel()+"",
                        Integer.valueOf(powerMap.getParentId()+""), powerMap.getState()+"",
                        powerMap.getIconCls()+"", powerMap.getUrl()+"");
                powerList.add(treeNode);
            }
        }
        return powerList;
    }

   @Override
    public List<TreeNode> getListt() {
        // TODO Auto-generated method stub
        List<TreeNode> powerAllList = powerDao.getList();
        //临时的powerList
        List<TreeNode> powerTempList = new ArrayList<TreeNode>();
        //判断是否为空
        if(powerAllList!=null&&powerAllList.size()>0){
            for(TreeNode ptreeNode:powerAllList){
                if(ptreeNode.getParentId()==0){//如果等于0，说明是一级节点
                    powerTempList.add(ptreeNode);
                    //递归绑定子节点
                    bindChirldren(ptreeNode,powerAllList);
                }
            }
        }
        return powerTempList;
    }

    /**
     * 递归绑定所有子节点
     * @param parentTreeNode
     * @param powerAllList
     */
    private void bindChirldren(TreeNode parentTreeNode,List<TreeNode> powerAllList){
        for(TreeNode chirldrenTreeNode:powerAllList){
            if(parentTreeNode.getId()==chirldrenTreeNode.getParentId()){
                List<TreeNode> chirldren = parentTreeNode.getChildren();
                if(chirldren==null){//如果孩子集合为空
                    List<TreeNode> chirldrenTempList = new ArrayList<TreeNode>();//实例化孩子集合
                    chirldrenTempList.add(chirldrenTreeNode);//添加子节点到集合里面
                    parentTreeNode.setChildren(chirldrenTempList);//设置孩子集合
                }else{//不空，说明设置过
                    chirldren.add(chirldrenTreeNode);//添加子节点到集合里面
                }
                //自己调用自己，找孩子
                bindChirldren(chirldrenTreeNode,powerAllList);
            }
        }
    }


}
