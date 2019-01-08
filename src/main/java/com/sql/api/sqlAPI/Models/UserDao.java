package com.sql.api.sqlAPI.Models;


import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class UserDao  {
    @Qualifier("firstdb")
    @Autowired
    private DataSource dataSource;

    public List<User> list(){
        System.out.println(dataSource);
        Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        UserSQLs userQLs = handle.attach(UserSQLs.class);
        return userQLs.list();
    }

    public Integer insert(User item){
        Connection conn =  DataSourceUtils.getConnection(dataSource);
        Handle handle = DBI.open(conn);
        UserSQLs userSQLs = handle.attach(UserSQLs.class);
        return userSQLs.insert(item);
    }

    @RegisterMapper(UserMapper.class)
    interface UserSQLs {
        @SqlQuery("select * from users")
        List<User> list();

        @SqlUpdate("insert into users (username)" +
                " values(:username) ")
        @GetGeneratedKeys
        Integer insert(@BindBean User test);
    }

    public static class UserMapper implements ResultSetMapper<User> {
        @Override
        public User map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
            User user = new User();
            user.setId((Integer) r.getObject("id"));
            user.setUsername(r.getString("username"));
            return user;
        }
    }

}

