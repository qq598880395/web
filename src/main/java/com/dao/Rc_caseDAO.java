package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Rc_case;
import org.apache.ibatis.annotations.Param;

public interface Rc_caseDAO extends  BaseMapper<Rc_case> {
        public int updateRcCase(@Param("rc_a") int rc_a, @Param("rc_b") int rc_b, @Param("rc_c") int rc_c, @Param("rc_a_regiv") int rc_a_regiv, @Param("rc_b_regiv") int rc_b_regiv, @Param("rc_c_regiv") int rc_c_regiv, @Param("rc_caseid") int rc_caseid);
        public Rc_case findbyRc_id(@Param("rc_caseid") int rc_caseid);

}