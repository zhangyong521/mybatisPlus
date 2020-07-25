package com.zhangyong;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyong.entity.User;
import com.zhangyong.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MptestApplicationTests {
    @Autowired
    private UserMapper userMapper;


    /**
     * 查询所有的用户
     */
    @Test
    public void testSelectList() {
        System.out.println("-------查询所有的用户----------");
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 添加用户
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("mak");
        user.setAge(18);
        user.setEmail("lay@163.com");

        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }

    /**
     * 根据Id进行修改
     */
    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1286841059950825474L);
        user.setAge(28);
        user.setEmail("mak@163.com");

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     * 测试 乐观锁插件（先要把数据库的默认值设置为1）
     */
    @Test
    public void testOptimisticLocker() {

        //查询
        User user = userMapper.selectById(1L);
        //修改数据
        user.setName("Helen");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }

    /**
     * 根据Id查询
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     * 多个Id批量查询
     */
    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 通过map封装查询条件
     */
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 28);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    /**
     * 通过map封装查询条件
     */
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);

        System.out.println(page.getCurrent()); //当前第几页
        System.out.println(page.getPages()); //总页数
        System.out.println(page.getSize());//当前有几页
        System.out.println(page.getTotal());//总页数
        System.out.println(page.hasNext());//是否有上一页
        System.out.println(page.hasPrevious());//是否有下一页

    }

    /**
     * 物理删除
     */
    /**
     * 根据Id删除
     */
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1286841059950825474L);
        System.out.println(result);
    }

    /**
     * 根据Id批量删除
     */
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1286837707892326402L, 5L));
        System.out.println(result);
    }

    /**
     * 简单的条件查询删除
     */
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "lay");
        map.put("age", 10);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    /**
     * 逻辑删除
     */
    /**
     * 根据Id逻辑删除
     */
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(1L);
        System.out.println(result);
    }

    /**
     * 逻辑条件删除
     */
    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                .eq("age", 28)
                .isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    /**
     * 根据条件查询一条，有多条报错
     */

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 28);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 查询年龄段的条数
     */
    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 25, 28);
        Integer integer = userMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }

    /**
     * 多条件查询所有满足条件的
     */
    @Test
    public void SelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "Jack");
        map.put("age", 20);

        queryWrapper.allEq(map);
        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    /**
     * 多条件模糊查询
     */
    @Test
    public void testSelectMaps() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .notLike("name", "e")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    /**
     * 多条件子查询
     */
    @Test
    public void testSelectObjs() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("id", 1, 2, 3);
        queryWrapper.inSql("id", "select id from user where id < 3");

        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);
    }

    /**
     * 修改姓名含有h或者年龄在20-30之间的
     */
    @Test
    public void testUpdate1() {

        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or()
                .between("age", 20, 30);

        int result = userMapper.update(user, userUpdateWrapper);

        System.out.println(result);
    }

    /**
     *
     */
    @Test
    public void testUpdate2() {

        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or(i -> i.eq("name", "李白").ne("age", 20));

        int result = userMapper.update(user, userUpdateWrapper);

        System.out.println(result);
    }

    /**
     * 按照Id降序排序
     */
    @Test
    public void testSelectListOrderBy() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectListLast() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectListColumn() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdateSet() {

        //修改值
        User user = new User();
        user.setAge(99);

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询

        int result = userMapper.update(user, userUpdateWrapper);
    }

}
