package com.tairun.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PickupdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PickupdExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("ordertype is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("ordertype is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(String value) {
            addCriterion("ordertype =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(String value) {
            addCriterion("ordertype <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(String value) {
            addCriterion("ordertype >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(String value) {
            addCriterion("ordertype >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(String value) {
            addCriterion("ordertype <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(String value) {
            addCriterion("ordertype <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLike(String value) {
            addCriterion("ordertype like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotLike(String value) {
            addCriterion("ordertype not like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<String> values) {
            addCriterion("ordertype in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<String> values) {
            addCriterion("ordertype not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(String value1, String value2) {
            addCriterion("ordertype between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(String value1, String value2) {
            addCriterion("ordertype not between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andAcountIsNull() {
            addCriterion("acount is null");
            return (Criteria) this;
        }

        public Criteria andAcountIsNotNull() {
            addCriterion("acount is not null");
            return (Criteria) this;
        }

        public Criteria andAcountEqualTo(String value) {
            addCriterion("acount =", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountNotEqualTo(String value) {
            addCriterion("acount <>", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountGreaterThan(String value) {
            addCriterion("acount >", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountGreaterThanOrEqualTo(String value) {
            addCriterion("acount >=", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountLessThan(String value) {
            addCriterion("acount <", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountLessThanOrEqualTo(String value) {
            addCriterion("acount <=", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountLike(String value) {
            addCriterion("acount like", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountNotLike(String value) {
            addCriterion("acount not like", value, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountIn(List<String> values) {
            addCriterion("acount in", values, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountNotIn(List<String> values) {
            addCriterion("acount not in", values, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountBetween(String value1, String value2) {
            addCriterion("acount between", value1, value2, "acount");
            return (Criteria) this;
        }

        public Criteria andAcountNotBetween(String value1, String value2) {
            addCriterion("acount not between", value1, value2, "acount");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andChargeIsNull() {
            addCriterion("charge is null");
            return (Criteria) this;
        }

        public Criteria andChargeIsNotNull() {
            addCriterion("charge is not null");
            return (Criteria) this;
        }

        public Criteria andChargeEqualTo(Double value) {
            addCriterion("charge =", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotEqualTo(Double value) {
            addCriterion("charge <>", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThan(Double value) {
            addCriterion("charge >", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeGreaterThanOrEqualTo(Double value) {
            addCriterion("charge >=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThan(Double value) {
            addCriterion("charge <", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeLessThanOrEqualTo(Double value) {
            addCriterion("charge <=", value, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeIn(List<Double> values) {
            addCriterion("charge in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotIn(List<Double> values) {
            addCriterion("charge not in", values, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeBetween(Double value1, Double value2) {
            addCriterion("charge between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andChargeNotBetween(Double value1, Double value2) {
            addCriterion("charge not between", value1, value2, "charge");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterionForJDBCDate("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterionForJDBCDate("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterionForJDBCDate("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCustomernumberIsNull() {
            addCriterion("customernumber is null");
            return (Criteria) this;
        }

        public Criteria andCustomernumberIsNotNull() {
            addCriterion("customernumber is not null");
            return (Criteria) this;
        }

        public Criteria andCustomernumberEqualTo(String value) {
            addCriterion("customernumber =", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberNotEqualTo(String value) {
            addCriterion("customernumber <>", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberGreaterThan(String value) {
            addCriterion("customernumber >", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberGreaterThanOrEqualTo(String value) {
            addCriterion("customernumber >=", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberLessThan(String value) {
            addCriterion("customernumber <", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberLessThanOrEqualTo(String value) {
            addCriterion("customernumber <=", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberLike(String value) {
            addCriterion("customernumber like", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberNotLike(String value) {
            addCriterion("customernumber not like", value, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberIn(List<String> values) {
            addCriterion("customernumber in", values, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberNotIn(List<String> values) {
            addCriterion("customernumber not in", values, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberBetween(String value1, String value2) {
            addCriterion("customernumber between", value1, value2, "customernumber");
            return (Criteria) this;
        }

        public Criteria andCustomernumberNotBetween(String value1, String value2) {
            addCriterion("customernumber not between", value1, value2, "customernumber");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerIsNull() {
            addCriterion("waybillnumbrer is null");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerIsNotNull() {
            addCriterion("waybillnumbrer is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerEqualTo(String value) {
            addCriterion("waybillnumbrer =", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerNotEqualTo(String value) {
            addCriterion("waybillnumbrer <>", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerGreaterThan(String value) {
            addCriterion("waybillnumbrer >", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerGreaterThanOrEqualTo(String value) {
            addCriterion("waybillnumbrer >=", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerLessThan(String value) {
            addCriterion("waybillnumbrer <", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerLessThanOrEqualTo(String value) {
            addCriterion("waybillnumbrer <=", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerLike(String value) {
            addCriterion("waybillnumbrer like", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerNotLike(String value) {
            addCriterion("waybillnumbrer not like", value, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerIn(List<String> values) {
            addCriterion("waybillnumbrer in", values, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerNotIn(List<String> values) {
            addCriterion("waybillnumbrer not in", values, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerBetween(String value1, String value2) {
            addCriterion("waybillnumbrer between", value1, value2, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andWaybillnumbrerNotBetween(String value1, String value2) {
            addCriterion("waybillnumbrer not between", value1, value2, "waybillnumbrer");
            return (Criteria) this;
        }

        public Criteria andBoxnumberIsNull() {
            addCriterion("boxnumber is null");
            return (Criteria) this;
        }

        public Criteria andBoxnumberIsNotNull() {
            addCriterion("boxnumber is not null");
            return (Criteria) this;
        }

        public Criteria andBoxnumberEqualTo(String value) {
            addCriterion("boxnumber =", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberNotEqualTo(String value) {
            addCriterion("boxnumber <>", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberGreaterThan(String value) {
            addCriterion("boxnumber >", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberGreaterThanOrEqualTo(String value) {
            addCriterion("boxnumber >=", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberLessThan(String value) {
            addCriterion("boxnumber <", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberLessThanOrEqualTo(String value) {
            addCriterion("boxnumber <=", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberLike(String value) {
            addCriterion("boxnumber like", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberNotLike(String value) {
            addCriterion("boxnumber not like", value, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberIn(List<String> values) {
            addCriterion("boxnumber in", values, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberNotIn(List<String> values) {
            addCriterion("boxnumber not in", values, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberBetween(String value1, String value2) {
            addCriterion("boxnumber between", value1, value2, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andBoxnumberNotBetween(String value1, String value2) {
            addCriterion("boxnumber not between", value1, value2, "boxnumber");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}