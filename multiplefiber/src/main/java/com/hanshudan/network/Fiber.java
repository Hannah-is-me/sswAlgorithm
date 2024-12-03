package com.hanshudan.network;

import com.hanshudan.general.CommonObject;
import com.hanshudan.general.Constant;
import com.hanshudan.general.Time;
import com.hanshudan.general.Timeroute;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fiber extends CommonObject {
    private Network associateNetwork;
    private Node srcNode;
    private Node destNode;
    private Double length;
    //private List<Wavelength> wavelengthList;
   // private Map<String, Wavelength> wavelengthMap;

    private ArrayList<Time> timeList;
    private HashMap<String, Time> timeHashMap;

    private List<Fiber> TypeList;
    private Map<Fiber, Integer> TypeMap;

    private HashMap<Integer, Double> TimewindowPWHashMap;
    private int Fuse;
    private int Fuseoutput;
    private NodePair nodePair;

    public Fiber(String name, Integer index, Network associateNetwork, Node srcNode, Node destNode, Double length) {
        super(name, index);
        this.associateNetwork = associateNetwork;
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.length = length;
    //    wavelengthList=new ArrayList<>();
    //    wavelengthMap = new HashMap<>();
        timeList = new ArrayList<>();
        timeHashMap = new HashMap<>();
        this.TimewindowPWHashMap = new HashMap<>();
    //    generateWav();
          generateTime();
          this.Fuse =0;
          this.Fuseoutput=0;
          this.nodePair = null;
    }


 //   public void generateWav() {
 //       for (int i = 0; i < Constant.wavNum; i++) {
 //           Wavelength wav = new Wavelength("λ" + i, i, Constant.wavCapacity);
  //          addWav(wav);
  //      }
 //   }

    // 用于计算fiber当前TW内时隙数.


    public NodePair getNodePair() {
        return nodePair;
    }

    public void setNodePair(NodePair nodePair) {
        this.nodePair = nodePair;
    }

    public int calculateFuse(Timeroute TW){
        int flag = 0 ;
        for(int i = TW.getFirstSlotNum(); i <= TW.getLastSlotNum(); i++)
        {
                  for (Wavelength wav: this.getTimeList().get(i).getWavelengthList() ){
                                if (wav.getCapacity()==0) {
                                    flag = flag + 1;
                                }
                  }
        }
        return flag;
    }

    public int getFuse() {
        return Fuse;
    }

    public int getFuseputput() {
        return Fuseoutput;
    }
    public void setFuseoutput(int fuseoutput) {
        Fuseoutput = fuseoutput;
    }

    public void setFuse(Timeroute TW) {
        Fuse = this.calculateFuse(TW);
    }


    public void AddFiberTimePW(Integer index, Double pw ) {

       this.TimewindowPWHashMap.put(index,pw);

   }

    public HashMap<Integer, Double> getTimewindowPWHashMap() {
        return TimewindowPWHashMap;
    }

    public void setTimewindowPWHashMap(HashMap<Integer, Double> timewindowPWHashMap) {
        TimewindowPWHashMap = timewindowPWHashMap;
    }

    public void generateTime() {
        for (int i = 0; i < Constant.timetotalnum; i++) {
            Time tim = new Time("T" + i, i, Constant.timeslotcapacity);
            addTim(tim);
        }
    }


 //   private void addWav(Wavelength wav) {
 //       wavelengthMap.put(wav.getName(), wav);
  //      wavelengthList.add(wav);
 //   }

    private void addTim(Time tim) {
        timeHashMap .put(tim.getName(), tim);
        timeList .add(tim);
    }

 //   public boolean canUseCapacity(Wavelength wavelength, double capacity) {
  //      return wavelengthMap.get(wavelength.getName()).getCapacity() >= capacity ? true : false;
  //  }

 //   public void useCapacity(Wavelength wavelength, double capacity) {
   //     if (canUseCapacity(wavelength, capacity)) {
  //          wavelengthMap.get(wavelength.getName()).useCapacity(capacity);
  //      }else {
   //         System.out.println("波长容量不足够");
   //     }
 //   }

 //   public void release(Wavelength wav) {
   //     Wavelength wavelength = wavelengthMap.get(wav.getName());
  //      if (wavelength.getCapacity() == 0){
 //       wavelength.release();}
 //   }


    public void groupfibers (double granularity, double fiberNumber, Fiber fiber){

      //     TypeMap.put(SwitchGranularity.getIndex(), fiber);
           TypeList.add(fiber);

    }

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public Node getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(Node srcNode) {
        this.srcNode = srcNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public void setDestNode(Node destNode) {
        this.destNode = destNode;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

   // public List<Wavelength> getWavelengthList() {
   //     return wavelengthList;
   // }

   // public void setWavelengthList(List<Wavelength> wavelengthList) {
   //     this.wavelengthList = wavelengthList;
  //  }

   // public Map<String, Wavelength> getWavelengthMap() {
   //     return wavelengthMap;
  //  }
//
    //public void setWavelengthMap(Map<String, Wavelength> wavelengthMap) {
   //     this.wavelengthMap = wavelengthMap;
   // }

    public List<Fiber> getTypeList() {
        return TypeList;
    }

    public void setTypeList(List<Fiber> typeList) {
        TypeList = typeList;
    }

    public Map<Fiber, Integer> getTypeMap() {
        return TypeMap;
    }

    public void setTypeMap(Map<Fiber, Integer> typeMap) {
        TypeMap = typeMap;
    }

    public ArrayList<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(ArrayList<Time> timeList) {
        this.timeList = timeList;
    }

    public HashMap<String, Time> getTimeHashMap() {
        return timeHashMap;
    }

    public void setTimeHashMap(HashMap<String, Time> timeHashMap) {
        this.timeHashMap = timeHashMap;
    }
}
