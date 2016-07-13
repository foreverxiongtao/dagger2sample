package com.desperado.dagger2sample.entity;

import java.util.List;

/*
 *
 *
 * 版 权 :@Copyright 北京xxxx科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:02
 *
 * 描 述 :学生信息类
 *
 * 修订日期 :
 */
public class StudentInfo {
    public StatusEntity status;

    public static class StatusEntity {
        public int code;
        public String message;
    }

    public List<Student> data;
}
