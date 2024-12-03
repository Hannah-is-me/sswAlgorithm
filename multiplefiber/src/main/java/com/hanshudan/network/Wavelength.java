package com.hanshudan.network;

import com.hanshudan.general.CommonObject;
import com.hanshudan.general.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;



public class Wavelength extends CommonObject {
    private Double capacity;
    private String name;
    private Integer index;


    public Wavelength(String name, Integer index, Double capacity) {
        super(name, index);
        this.capacity = capacity;
        this.name = name;
        this.index= index;
    }

    public void setCapacity( Double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public void setIndex(Integer index) {
        this.index = index;
    }

    public void useCapacity(Double needCapacity) {
        capacity -= needCapacity;
//        System.out.println(getName()+"使用资源"+needCapacity+ " 剩余"+capacity);
    }

    public void release() {
        capacity = Constant.wavCapacity;
//        System.out.println(getName()+"释放资源"+releaseCapacity+"剩余："+capacity);
     //   if (capacity == Constant.wavCapacity){
   //         System.out.print("waveCapacity error! capacity:"+capacity);
       //     capacity += Constant.wavCapacity;
      //  }
    }

    public Double getCapacity() {
        return capacity;
    }
}
