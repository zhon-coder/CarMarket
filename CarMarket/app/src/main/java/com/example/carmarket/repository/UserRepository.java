package com.example.carmarket.repository;

import com.example.carmarket.MyApplication;
import com.example.carmarket.common.Result;
import com.example.carmarket.database.User;
import com.example.carmarket.database.UserDao;
import com.example.carmarket.util.DateTimeUtil;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository() {
        userDao = MyApplication.getInstance().getUserDatabase().UserDao();
    }

    public Result<User> login(String username, String password) {
        User user = userDao.queryByName(username);
        if((user != null) && (user.getPassword().equals(password))) {
            return Result.success("Login successfully", user);
        }else {
            return Result.fail("Incorrect username or password");
        }
    }

    public Result<User> loginWithThirdParty(String email,String name) {
        User user = userDao.queryByEmail(email);

        // 第三方关联到本地用户
        // 查找不到就创建新用户 查找到就更新 总是返回成功
        if(user == null) {
            user = new User();
            user.setUsername(name);
            user.setEmail(email);
            user.setCreateTime(DateTimeUtil.currentTimeMillis());
            user.setPassword("");
            userDao.insert(user);
        }else{
            user.setUsername(name);
            userDao.update(user);
        }
        return Result.success("",user);
    }

    public Result<User> register(String username, String password, String email) {
        User user1 = userDao.queryByName(username);
        User user2 = userDao.queryByEmail(email);

        if(user1 != null || user2 != null) {
            return Result.fail("The username or email already exists");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setCreateTime(DateTimeUtil.currentTimeMillis());
        long id = userDao.insert(newUser);
        if(id < 0) {
            return Result.fail("Register failed");
        }else {
            return Result.success("Register successfully", newUser);
        }
    }

    public Result<User> resetPassword(String email,String newPassword) {
        User user = userDao.queryByEmail(email);
        if(user == null) {
            return Result.fail("The email is not exists");
        }
        if(newPassword.isEmpty()) {
            return Result.fail("Please enter new password");
        }

        User newUser = new User();
        newUser.setPassword(newPassword);
        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        int i = userDao.update(newUser);
        if(i>0) {
            return Result.success("Reset password successfully", newUser);
        }else {
            return Result.fail("Reset password failed");
        }
    }
}
