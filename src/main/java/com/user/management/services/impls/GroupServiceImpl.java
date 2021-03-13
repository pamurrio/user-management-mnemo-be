package com.user.management.services.impls;

import com.user.management.models.Group;
import com.user.management.repository.GroupDao;
import com.user.management.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl  implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }
}
