package com.hanshudan.algorithm;


import com.hanshudan.general.Constant;
import com.hanshudan.network.Fiber;
import com.hanshudan.network.Network;
import com.hanshudan.network.Node;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
    private Node srcNode;
    private Node destNode;
    private Network associateNetwork;
    private List<Node> visitedNodeList;
    private List<Node> pathList;
    private List<Node> nodeList;
    private Node currentNode;

    public Dijkstra(Node srcNode, Node destNode, Network associateNetwork) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.associateNetwork = associateNetwork;
        visitedNodeList = new ArrayList<>();
        pathList = new ArrayList<>();
        nodeList = associateNetwork.getNodeList();
        for (Node node : getNodeList()) {
            node.setLengthFromSrc(Constant.INF);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
        }
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

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public List<Node> getVisitedNodeList() {
        return visitedNodeList;
    }

    public void setVisitedNodeList(List<Node> visitedNodeList) {
        this.visitedNodeList = visitedNodeList;
    }

    public List<Node> getPathList() {
        return pathList;
    }

    public void setPathList(List<Node> pathList) {
        this.pathList = pathList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }
}
