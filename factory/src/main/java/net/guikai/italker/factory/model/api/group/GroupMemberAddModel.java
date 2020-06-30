package net.guikai.italker.factory.model.api.group;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 添加群成员Model
 * Crete by Anding on 2020/6/29
 */
public class GroupMemberAddModel {
    private Set<String> users = new HashSet<>();

    public GroupMemberAddModel(Set<String> users) {
        this.users = users;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
