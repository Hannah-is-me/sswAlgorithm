package com.hanshudan.traffic;

import com.hanshudan.general.Constant;
import com.hanshudan.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActionListSort {

    List<Action> actionList;
    List<Action> sortList;

    public ActionListSort (List<Action> actionList, Random random){
        this.actionList = new ArrayList<>();
        this.actionList.addAll(actionList);
        this.sortList = new ArrayList<>();

    }

    public List<Action> sortAction(Random randominit){
     //   int random = randominit.nextInt(actionList.size());
        sortList.add(actionList.get(0));
        int times = actionList.size();
        actionList.remove(0);

        for (int i = 0; i < times-1; i++) {
            int index = 0;
            double minWeight = Double.MAX_VALUE;
            Action base = sortList.get(sortList.size()-1);
            base.getCon().getSrc().getIndex();
            double baseFS = base.getCon().getCapacity()/ Constant.wavCapacity;
            double[] nodePair = new double[]{(double)base.getCon().getSrc().getIndex(),(double)base.getCon().getDest().getIndex()};
            int linkNum = base.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size();
            double[] Links = new double[linkNum];
            for (int j = 0; j < linkNum; j++) {
                Links[j] = base.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(j).getLength();
            }

            int timeslotNum = (int) (base.getCon().getEndTime()-base.getCon().getStartTime())+1;
            double[] timeslots = new double[timeslotNum];

            for (int j = 0; j < timeslotNum;j++){
                timeslots[j]= base.getCon().getStartTime()+j;
            }

            int index1 = 0;
            for (Action cur : actionList){
                double curWeight = cur.getWeight();
                double curFS = cur.getCon().getCapacity()/Constant.wavCapacity;
                double[] curNodePair = new double[]{(double)cur.getCon().getSrc().getIndex(),(double)cur.getCon().getDest().getIndex()};
                int curLinkNum = cur.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size();
                double [] curLinks = new double[curLinkNum];
                for (int j = 0; j < curLinkNum; j++) {
                    curLinks[j] = cur.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(j).getLength();
                }

                int curtimeslotNum =(int) (cur.getCon().getEndTime()-cur.getCon().getStartTime()+1);
                double[] curtimeslots = new double[curtimeslotNum];
                for (int j = 0; j < curtimeslotNum;j++){
                    curtimeslots[j]=cur.getCon().getStartTime()+j;
                }


                double sameNodeNum = (double) countSameElements(nodePair, curNodePair);
                double sameLinkNum = (double) countSameElements(Links,curLinks);
                double samecurtimeslotsNum = (double) countSameElements(timeslots, curtimeslots);

                curWeight += (sameNodeNum+sameLinkNum+curFS/baseFS)* samecurtimeslotsNum;
                baseFS = curFS;
                cur.setWeight(curWeight);
                if(curWeight < minWeight){
                    index = index1;
                    minWeight = curWeight;
                }
                index1++;
            }
            sortList.add(actionList.get(index));
            actionList.remove(index);
        }
        return sortList;
    }



    public static double countSameElements(double[] arr1,double[] arr2){
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if(arr1[i] == arr2[j]){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

}
