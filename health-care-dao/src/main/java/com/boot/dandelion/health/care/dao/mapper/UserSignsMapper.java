package com.boot.dandelion.health.care.dao.mapper;

import com.boot.dandelion.health.care.dao.entity.UserSigns;

public interface UserSignsMapper {

    /**
     * 通过用户id号查找信息
     * @param userIde
     * @return
     */
    UserSigns selectByUid(String userIde);

    /**
     * 通过身份证号查数据
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
