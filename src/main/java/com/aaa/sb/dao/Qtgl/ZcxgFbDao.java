package com.aaa.sb.dao.Qtgl;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ZcxgFbDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select newsID,newsHead,newsContent,newsPerson,newsPastDate,tname,zname from tb_news \n " +
            "<where><if test=\"NEWSHEAD!=null and NEWSHEAD!=''\"> and NEWSHEAD like '%'||#{NEWSHEAD}||'%'</if> " +
            "<if test=\"ZNAME!=null and ZNAME!=''\"> and ZNAME like '%'||#{ZNAME}||'%'</if></where></script>")
    List<Map> getPageByParam(Map map);


    /**
     * 添加新闻
     * @param map
     * @return
     */
    @Insert("insert into tb_news values(seq_news_id.nextval,#{NEWSHEAD},#{NEWSCONTENT},#{NEWSPERSON},TO_CHAR(SYSDATE,'YYYY-MM-DD'),#{TNAME},'未发布')")
    int add(Map map);

    /**
     * 更新新闻内容
     * @param map
     * @return
     */
    @Update("update tb_news set NEWSHEAD=#{NEWSHEAD},NEWSCONTENT=#{NEWSCONTENT},NEWSPERSON=#{NEWSPERSON}," +
            "NEWSPASTDATE=#{NEWSPASTDATE},TNAME=#{TNAME},ZNAME=#{ZNAME} where NEWSID=#{NEWSID}")
    int upadte(Map map);

    /**
     * 删除新闻
     * @param empNo
     * @return
     */
    @Delete("delete from tb_news where NEWSID=#{NEWSID}")
    int delete(int empNo);

    /**
     *批量删除新闻
     * @param _s
     * @return
     */
    @Delete("delete from tb_news where NEWSID in(${_parameter})")
    int batchDel(String _s);
}
