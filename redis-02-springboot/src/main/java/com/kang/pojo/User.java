package com.kang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//在企业级开发中，基本所有的pojo都会被序列化
public class User implements Serializable {
    private String name;
    private int age;
}
