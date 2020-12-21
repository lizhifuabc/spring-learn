package com.boot.hot.dao;

import com.boot.hot.dao.param.AccountHisParam;
import com.boot.hot.dao.vo.AccountHisVO;
import com.boot.hot.enums.EntryType;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户历史
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@Repository
public class AccountHisDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 入账户历史，没有扣款
     * @param accountHisParam
     */
    public Long insert(AccountHisParam accountHisParam){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into my_db.account_his" +
                "(account_no, amount, trade_flow_no, trade_type, direction, create_time, entry,trade_sign_no) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, accountHisParam.getAccountNo());
                ps.setBigDecimal(2, accountHisParam.getAmount());
                ps.setString(3, accountHisParam.getTradeFlowNo());
                ps.setInt(4, accountHisParam.getTradeType().getName());
                ps.setInt(5, accountHisParam.getDirection().getName());
                ps.setDate(6,new Date(System.currentTimeMillis()));
                ps.setInt(7, EntryType.NO.getName());
                ps.setLong(8, accountHisParam.getTradeSignNo());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 批量更新
     * @param idList
     */
    public void batchUpdate(List<AccountHisVO> idList){
        String sql  = "update my_db.account_his set entry = 0,version = version +1  where id = ? and version = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
            @Override
            public int getBatchSize(){
                return idList.size();
            }
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, idList.get(i).getId());
                ps.setLong(2,idList.get(i).getVersion());
            }
        });
    }
    /**
     * 查询增款
     * @param accountNo
     * @return
     */
    public List<AccountHisVO> selectAddIdList(Long accountNo){
        String sql  = "select id,version from my_db.account_his where entry = 1 and account_no = ? and direction = 0";
        List<AccountHisVO> idList = jdbcTemplate.queryForList(sql,accountNo).stream().map(item->{
            AccountHisVO accountHisVO = new AccountHisVO();
            accountHisVO.setId(Long.valueOf(item.get("id").toString()));
            accountHisVO.setVersion(Long.valueOf(item.get("version").toString()));
            return accountHisVO;
        }).collect(Collectors.toList());
        return idList;
    }
    /**
     * 查询扣款
     * @param accountNo
     * @return
     */
    public List<AccountHisVO> selectReduceIdList(Long accountNo){
        String sql  = "select id,version from my_db.account_his where entry = 1 and account_no = ? and direction = 1";
        List<AccountHisVO> idList = jdbcTemplate.queryForList(sql,accountNo).stream().map(item->{
            AccountHisVO accountHisVO = new AccountHisVO();
            accountHisVO.setId(Long.valueOf(item.get("id").toString()));
            accountHisVO.setVersion(Long.valueOf(item.get("version").toString()));
            return accountHisVO;
        }).collect(Collectors.toList());
        return idList;
    }
    /**
     * 查询增款
     * @param accountNo
     * @return
     */
    public BigDecimal selectAddSum(Long accountNo){
        String sql  = "select sum(amount) from my_db.account_his where entry = 1 and account_no = ? and direction = 0";
        BigDecimal sum = jdbcTemplate.queryForObject(sql,BigDecimal.class,accountNo);
        return sum;
    }
    /**
     * 查询扣款
     * @param accountNo
     * @return
     */
    public BigDecimal selectReduceSum(Long accountNo){
        String sql  = "select sum(amount) from my_db.account_his where entry = 1 and account_no = ? and direction = 1";
        BigDecimal sum = jdbcTemplate.queryForObject(sql,BigDecimal.class,accountNo);
        return sum;
    }
}
