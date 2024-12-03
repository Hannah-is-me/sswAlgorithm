package com.hanshudan.network;

import com.hanshudan.general.CommonObject;

public class Link  extends CommonObject {
    private Network associateNetwork;
    private Node srcNode;
    private Node destNode;
    private Double length;

    public Link (String name, Integer index,Network associateNetwork, Node srcNode, Node destNode, Double length)
    {
        super(name, index);
        this.associateNetwork = associateNetwork;
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.length = length;

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
}
