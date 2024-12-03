package com.hanshudan.network;

import com.hanshudan.general.Constant;
import com.hanshudan.general.SubGraph;
import com.hanshudan.general.FileOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LinearRoute extends SubGraph {

    public LinearRoute(String name, Integer index) {
        super(name, index);
    }

    public LinearRoute(LinearRoute route) {
        super(route.getName(), route.getIndex());
        this.setFiberlist(new ArrayList<>(route.getFiberlist()));
        this.setNodelist(new ArrayList<>(route.getNodelist()));
        this.setFmaxrecord(route.getFmaxrecord());
        this.setFiberMap(new HashMap<String, Fiber>((route.getFiberMap())));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinearRoute{").append(getName()).append("}:/n");
        for (Fiber fiber : getFiberlist()) {
            sb.append(fiber.getName()).append("->");
        }
        return sb.toString();
    }

    public List<Fiber> findFiberList(Node currentNode, Node node, LinearRoute route, int fibernumber, int g) {
        List<Fiber> fibers = new ArrayList<>();
        for (int i = 0; i <  fibernumber/g; i++) {    // the fiber group number
            String name = currentNode.getName() + "-" + node.getName() + "-" + i;
            if (route.getFiberMap().containsKey(name )){
                fibers.add(route.getFiberMap().get(name));}
        }
        return fibers;
    }

    public void OutputWriteRoute_node(String writename, LinearRoute newroute){
        FileOutput write = new FileOutput();
        if (newroute.getNodelist().size() > 0) {
            int i = 0;
            int k = newroute.getNodelist().size();
            for (i = 0; i < k - 1; i++) {
//				System.out.print(newroute.getNodelist().get(i).getName() + "->");
                write.filewriteContinuous(writename, newroute.getNodelist().get(i).getName() + "->");
            }
//			System.out.println(newroute.getNodelist().get(i).getName());
            write.filewriteContinuous(writename, newroute.getNodelist().get(i).getName());
        }
        write.filewriteContinuous(writename, "\r\n");
    }



}
