package com.hanshudan.algorithm;

import com.hanshudan.general.Constant;
import com.hanshudan.network.Fiber;
import com.hanshudan.network.Network;
import com.hanshudan.network.Node;
import com.hanshudan.network.Wavelength;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class FixRoute {

    private Node srcNode;
    private Node destNode;
    private Network associateNetwork;
    private List<Node> visitedNodeList;
    private List<Node> pathList;
    private List<Node> nodeList;
    private Node currentNode;

    public  FixRoute (Node srcNode, Node destNode, Network associateNetwork) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.associateNetwork = associateNetwork;
        visitedNodeList = new ArrayList<>();
        pathList = new ArrayList<>();
        nodeList = associateNetwork.getNodeList();

        srcNode.setLengthFromSrc(0.0);
        currentNode = srcNode;

        if (currentNode != null) {
            while (currentNode != destNode) {
                currentNode.setStatus(Constant.VisitedTwice);
                if (visitedNodeList.contains(currentNode)) {
                    visitedNodeList.remove(currentNode);
                }
                for (Node node : currentNode.getAroundNodeList()) {
                    if (nodeList.contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
//                            List<Fiber> fiberList = associateNetwork.findFiberList(currentNode, node);
                            node.setLengthFromSrc(currentNode.getLengthFromSrc() + 1);
                            node.setStatus(Constant.VisitedOnce);
                            node.setParentNode(currentNode);
                            visitedNodeList.add(node);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
//                            List<Fiber> fiberList = associateNetwork.findFiberList(currentNode, node);
                            if (node.getLengthFromSrc() > 1 + currentNode.getLengthFromSrc()) {
                                node.setLengthFromSrc(1 + currentNode.getLengthFromSrc());
                                node.setParentNode(currentNode);
                            }


                        }
                    }

                }
                getShortestLengthNode(visitedNodeList);
                if (currentNode == null) {
                    break;
                }
            }

            getRoute();
        }

    }

    public void getRoute() {
        Node currentNode = destNode;
        pathList.add(currentNode);
        while (currentNode != srcNode) {
            pathList.add(currentNode.getParentNode());
            currentNode = currentNode.getParentNode();
        }
        Collections.reverse(pathList);

    }

    public void getShortestLengthNode(List<Node> visitedNodeList) {
        Node obj = null;
        double shortestLength = Constant.INF;
        for (Node node : visitedNodeList) {
            obj = node.getLengthFromSrc() < shortestLength ? node : obj;
            shortestLength = Math.min(node.getLengthFromSrc(), shortestLength);
        }
        currentNode = obj;
    }





}


