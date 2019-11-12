package com.example.demo.spring;

import java.beans.PropertyEditorSupport;

import com.example.demo.spring.beans.Car;

public class MyPropertyEditor extends PropertyEditorSupport{

	@Override
	//1. 将字面值转换为属性类型对象    
    public void setAsText(String text){     
        if(text == null || text.indexOf(",") == -1){    
            throw new IllegalArgumentException("设置的字符串格式不正确");    
        }    
        String[] infos = text.split(",");    
        Car car = new Car();    
        car.setBrand(infos[0]);    
        car.setMaxSpeed(Integer.parseInt(infos[1]));    
        car.setPrice(Double.parseDouble(infos[2]));    
    
         //2. 调用父类的setValue()方法设置转换后的属性对象    
        super.setValue(car);     
    } 

}
