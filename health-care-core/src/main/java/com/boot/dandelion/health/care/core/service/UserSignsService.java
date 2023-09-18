package com.boot.dandelion.health.care.core.service;


import com.boot.dandelion.health.care.dao.entity.UserSigns;

public interface UserSignsService {

    /**
     * 通过身份证号查找体征信息
     * @return
     */
    UserSigns selectAllByUid(String userIde);

    /**
     * 通过身份账号查数据
     * @param userIden
     * @return
     */
    UserSigns getUserByIden(String userIden);

    /**
     * 添加数据
     * @param userSigns
     * @return
     */
    int addUser(UserSigns userSigns);

    /**
     * 通过身份证号删除数据
     * @param userIden
     * @return
     */
    int deleteUser(String userIden);

    /**
     * 修改信息
     * @param userSigns
     * @return
     */
    int updateUser(UserSigns userSigns);
}
