package com.hanshudan.network;

import com.hanshudan.general.CommonObject;
import com.hanshudan.general.Timeroute;

public class LightPath extends CommonObject {
    private LinearRoute physicPath  = null; //路由集合？
    private LinearRoute physicPath_temp  = null; //物理路径
    private FSRoute associatedslotwindow = null; // 频谱窗算法
    private NodePair AssociatedNodepair    = null;//光路所在的节点对
    private Timeroute associatedtimeroute = null;

    public LightPath(String name, Integer index,  NodePair associatedNodepair) {
        super(name, index);
        this.AssociatedNodepair = associatedNodepair;
    }

/*    public void release() {
//        System.out.println("---------release业务"+this.getSrc()+"-"+this.getDest()+getState()+"-----------------");
        for (Fiber fiber : this.physicPath.getFiberlist()) {
            for (int i = this.getAssociatedslotwindow().getFirstSlotNum(); i <=this.getAssociatedslotwindow().getLastSlotNum(); i++) {//这边是当前节点对所有路由和波长全部释放
//                System.out.println("释放"+fiber.getName());
                fiber.release(fiber.getWavelengthList().get(i));
            }

        }
       this.physicPath.getFiberlist().clear();
       this.physicPath.getNodelist().clear();
    }*/

    public Timeroute getAssociatedtimeroute() {
        return associatedtimeroute;
    }

    public void setAssociatedtimeroute(Timeroute associatedtimeroute) {
        this.associatedtimeroute = associatedtimeroute;
    }

    public LinearRoute getPhysicPath() {
        return physicPath;
    }

    public void setPhysicPath(LinearRoute physicPath) {
        this.physicPath = physicPath;
    }

    public LinearRoute getPhysicPath_temp() {
        return physicPath_temp;
    }

    public void setPhysicPath_temp(LinearRoute physicPath_temp) {
        this.physicPath_temp = physicPath_temp;
    }

    public FSRoute getAssociatedslotwindow() {
        return associatedslotwindow;
    }

    public void setAssociatedslotwindow(FSRoute associatedslotwindow) {
        this.associatedslotwindow = associatedslotwindow;
    }

    public NodePair getAssociatedNodepair() {
        return AssociatedNodepair;
    }

    public void setAssociatedNodepair(NodePair associatedNodepair) {
        AssociatedNodepair = associatedNodepair;
    }
    private int requiredSlotNum = 0;

    public int getRequiredSlotNum() {
        return requiredSlotNum;
    }

    public void setRequiredSlotNum(int requiredSlotNum) {
        this.requiredSlotNum = requiredSlotNum;
    }
}
