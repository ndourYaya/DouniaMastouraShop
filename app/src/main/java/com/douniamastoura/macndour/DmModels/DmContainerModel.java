package com.douniamastoura.macndour.DmModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macndour on 01/03/15.
 */
public class DmContainerModel{

    public List userList;

    public List getUserList() {
        return userList;
    }

    public void setUserList(List user_list) {
        this.userList = user_list;
    }

    public DmContainerModel()
    {
        userList = new ArrayList();
    }

    public DmContainerModel(List userList)
    {
        this.userList = userList;
    }


}
