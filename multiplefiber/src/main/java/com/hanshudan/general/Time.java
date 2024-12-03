package com.hanshudan.general;

import com.hanshudan.network.Network;
import com.hanshudan.network.Wavelength;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Time extends CommonObject{
    private Double slot;
    private String name;
    private Integer index;
    private boolean flag;
    private List<Wavelength> wavelengthList;
    private Map<String, Wavelength> wavelengthMap;
    private ArrayList<Double>powerList;
    private ArrayList<Double>costList;
    private HashMap<String,Double>powerHashMap;
    private HashMap<String,Double>costHashMap;

    public Time(String name, Integer index, double slot){
        super(name, index);
        this.slot = slot;
        wavelengthList=new ArrayList<>();
        wavelengthMap = new HashMap<>();
        generateWav();
    }

    public void generateWav() {
        for (int i = 0; i < Constant.wavNum; i++) {
            Wavelength wav = new Wavelength("λ" + i, i, Constant.wavCapacity);
            addWav(wav);
        }
    }
    private void addWav(Wavelength wav) {
        wavelengthMap.put(wav.getName(), wav);
        wavelengthList.add(wav);
    }

    public Double getSlot() {
        return slot;
    }

    public void setSlot(Double slot) {
        this.slot = slot;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Wavelength> getWavelengthList() {
        return wavelengthList;
    }

    public void setWavelengthList(List<Wavelength> wavelengthList) {
        this.wavelengthList = wavelengthList;
    }

    public Map<String, Wavelength> getWavelengthMap() {
        return wavelengthMap;
    }

    public void setWavelengthMap(Map<String, Wavelength> wavelengthMap) {
        this.wavelengthMap = wavelengthMap;
    }
}
