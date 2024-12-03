package com.hanshudan.general;

import com.hanshudan.network.Network;
import com.hanshudan.traffic.Event;
import com.hanshudan.traffic.Traffic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventListSort {

    List<Event> eventList;

    List<Event> sortList;

    public EventListSort(Network associatenetwork, Integer time, Random random,List<Event> eventList){
        this.eventList = new ArrayList<>();
        this.eventList.addAll(eventList);
        this.sortList = new ArrayList<>();
    }

    public List<Event> sortEvent(){
        int random = new Random().nextInt(eventList.size());
        sortList.add(eventList.get(random));
        int times = eventList.size();
        eventList.remove(random);
        for (int i = 0; i < times-1; i++) {
            int index = 0;
            double minWeight = Double.MAX_VALUE;
            Event base = sortList.get(sortList.size()-1);
            base.getSrc().getIndex();
            double baseFS = base.getCapacity()/Constant.wavCapacity;
            double[] nodePair = new double[]{(double)base.getSrc().getIndex(),(double)base.getDest().getIndex()};
            int linkNum = base.getNodePair().getLinearroutelist().get(0).getFiberlist().size();
            double[] Links = new double[linkNum];
            for (int j = 0; j < linkNum; j++) {
                Links[j] = base.getNodePair().getLinearroutelist().get(0).getFiberlist().get(j).getLength();
            }

            int timeslotNum = (int) (base.getEndTime()-base.getStartTime())+1;
            double[] timeslots = new double[timeslotNum];

            for (int j = 0; j < timeslotNum;j++){
                timeslots[j]= base.getStartTime()+j;
            }

            int index1 = 0;
            for (Event cur : eventList){
                double curWeight = cur.getWeight();
                double curFS = cur.getCapacity()/Constant.wavCapacity;
                double[] curNodePair = new double[]{(double)cur.getSrc().getIndex(),(double)cur.getDest().getIndex()};
                int curLinkNum = cur.getNodePair().getLinearroutelist().get(0).getFiberlist().size();
                double [] curLinks = new double[curLinkNum];
                for (int j = 0; j < curLinkNum; j++) {
                    curLinks[j] = cur.getNodePair().getLinearroutelist().get(0).getFiberlist().get(j).getLength();
                }

                int curtimeslotNum =(int) (cur.getEndTime()-cur.getStartTime()+1);
                double[] curtimeslots = new double[curtimeslotNum];
                for (int j = 0; j < curtimeslotNum;j++){
                    curtimeslots[j]=cur.getStartTime()+j;
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
            sortList.add(eventList.get(index));
            eventList.remove(index);
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
