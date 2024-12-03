package com.hanshudan.algorithm;

import com.hanshudan.general.Constant;
import com.hanshudan.general.SearchConstraint;
import com.hanshudan.general.Timeroute;
import com.hanshudan.network.*;

import java.util.*;

public class RouteSearching {

    public RouteSearching() {
    }

    public void  Dijkstra(Node srcNode, Node destNode, Network layer, LinearRoute newRoute,
                          SearchConstraint constraint){
//        boolean flag = false;
        ArrayList<Node> visitedNode = new ArrayList<>();
        List<Node> List = layer.getNodeList();
        for (int i =0 ; i < List.size() ; i ++) {
            Node node = List.get(i);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
            node.setCost_from_src(Double.MAX_VALUE);
            node.setHop_from_src(Integer.MAX_VALUE);
            node.setLengthFromSrc(Double.MAX_VALUE);
        }

        Node curNode = srcNode;
        curNode.setCost_from_src(0);
        curNode.setHop_from_src(0);
        curNode.setLengthFromSrc(0.0);
        curNode.setStatus(Constant.VisitedTwice);

        if (constraint == null) {
            for (Node node : curNode.getAroundNodeList()) {
                if (node.getStatus() == Constant.Unvisited) {
                    traverseNeiNode(layer, visitedNode, curNode, node);
                }
            }
        } else {
            for (Node node : curNode.getAroundNodeList()) { //可以把不需要的值从相邻节点中去除
                if (!constraint.getExcludedNodelist().contains(node)) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                    }
                }
            }
        }

       curNode = getLowestLengthNode(visitedNode);
        while (curNode != null && !curNode.equals(destNode)) {
            curNode.setStatus(Constant.VisitedTwice);
            visitedNode.remove(curNode);

            if (constraint == null) {
                for (Node node : curNode.getAroundNodeList()) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node);
                    } else if (node.getStatus() == Constant.VisitedOnce) {
                        List <Fiber>   fiberList = layer.findFiberList(curNode, node);
                        if (fiberList  == null) {
                            continue;
                        }
                        if (node.getLengthFromSrc()> curNode.getLengthFromSrc()+  fiberList.get(0).getLength()) {
                            node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList.get(0).getLength());
                            node.setHop_from_src(curNode.getHop_from_src() + 1);
                            node.setParentNode(curNode);
                        }
                    }
                }
            } else {
                for (Node node : curNode.getAroundNodeList()) {
                    if (!constraint.getExcludedNodelist().contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
                            traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
                         List <Fiber>   fiberList  = layer.findFiberList(curNode, node);
                            if (fiberList  == null) {
                                continue;
                            }
                            if (! flagofexcludcontainallfibers(fiberList, constraint ) ) {
                                if (node.getLengthFromSrc() > curNode.getLengthFromSrc()+ fiberList.get(0).getLength()) {
                                    node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList .get(0).getLength());
                                    node.setHop_from_src(curNode.getHop_from_src() + 1);
                                    node.setParentNode(curNode);
                                }
                            }
                        }
                    }
                }
            }
           curNode =  getLowestLengthNode(visitedNode);
        }

        newRoute.getNodelist().clear();
        newRoute.getFiberlist().clear();
        curNode = destNode;
        if (destNode.getParentNode() != null) {
            newRoute.getNodelist().add( 0,curNode);
            while (curNode != srcNode) {

                List<Fiber> fiberList = layer.findFiberList(curNode.getParentNode(), curNode);

                //  Random random =new Random();
                //    int n =random.nextInt(fiberList.size());
                if (fiberList.size() != 0) {
                    newRoute.getFiberlist().add(0, fiberList.get(0));  //增加是第一组fiber
                    curNode = curNode.getParentNode();
                    newRoute.getNodelist().add(0, curNode); //增加是第一组fiber，证明是也是SSWfirst
                }
            }

//
//            if (flag){newRoute.getNodelist().clear();
//                        newRoute.getFiberlist().clear();}
        }
    }

    public void  DijkstraLestloadfibeList(Node srcNode, Node destNode, Network layer, LinearRoute newRoute,
                                     SearchConstraint constraint){
//        boolean flag = false;
        ArrayList<Node> visitedNode = new ArrayList<>();
        List<Node> List = layer.getNodeList();
        for (int i =0 ; i < List.size() ; i ++) {
            Node node = List.get(i);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
            node.setCost_from_src(Double.MAX_VALUE);
            node.setHop_from_src(Integer.MAX_VALUE);
            node.setLengthFromSrc(Double.MAX_VALUE);
        }

        Node curNode = srcNode;
        curNode.setCost_from_src(0);
        curNode.setHop_from_src(0);
        curNode.setLengthFromSrc(0.0);
        curNode.setStatus(Constant.VisitedTwice);

        if (constraint == null) {
            for (Node node : curNode.getAroundNodeList()) {
                if (node.getStatus() == Constant.Unvisited) {
                    traverseNeiNode(layer, visitedNode, curNode, node);
                }
            }
        } else {
            for (Node node : curNode.getAroundNodeList()) { //可以把不需要的值从相邻节点中去除
                if (!constraint.getExcludedNodelist().contains(node)) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                    }
                }
            }
        }

        curNode = getLowestLengthNode(visitedNode);
        while (curNode != null && !curNode.equals(destNode)) {
            curNode.setStatus(Constant.VisitedTwice);
            visitedNode.remove(curNode);

            if (constraint == null) {
                for (Node node : curNode.getAroundNodeList()) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node);
                    } else if (node.getStatus() == Constant.VisitedOnce) {
                        List <Fiber>   fiberList = layer.findFiberList(curNode, node);
                        if (fiberList  == null) {
                            continue;
                        }
                        if (node.getLengthFromSrc()> curNode.getLengthFromSrc()+  fiberList.get(0).getLength()) {
                            node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList.get(0).getLength());
                            node.setHop_from_src(curNode.getHop_from_src() + 1);
                            node.setParentNode(curNode);
                        }
                    }
                }
            } else {
                for (Node node : curNode.getAroundNodeList()) {
                    if (!constraint.getExcludedNodelist().contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
                            traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
                            List <Fiber>   fiberList  = layer.findFiberList(curNode, node);
                            if (fiberList  == null) {
                                continue;
                            }
                            if (! flagofexcludcontainallfibers(fiberList, constraint ) ) {
                                if (node.getLengthFromSrc() > curNode.getLengthFromSrc()+ fiberList.get(0).getLength()) {
                                    node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList .get(0).getLength());
                                    node.setHop_from_src(curNode.getHop_from_src() + 1);
                                    node.setParentNode(curNode);
                                }
                            }
                        }
                    }
                }
            }
            curNode =  getLowestLengthNode(visitedNode);
        }

        newRoute.getNodelist().clear();
        newRoute.getFiberlist().clear();
        curNode = destNode;
        if (destNode.getParentNode() != null) {
            newRoute.getNodelist().add( 0,curNode);
            while (curNode != srcNode) {

                List<Fiber> fiberList = layer.findFiberList(curNode.getParentNode(), curNode);




                //  Random random =new Random();
                //    int n =random.nextInt(fiberList.size());

                if (fiberList.size() != 0) {
                    newRoute.getFiberlist().add(0, fiberList.get(0));
                    for(Fiber fiber : fiberList){
                        newRoute.getFiberMap().put(fiber.getName(),fiber);}
                    curNode = curNode.getParentNode();
                    newRoute.getNodelist().add(0, curNode);

                }



            }

//
//            if (flag){newRoute.getNodelist().clear();
//                        newRoute.getFiberlist().clear();}
        }
    }


    public Fiber minFSofFiber(List<Fiber> fiberlist, Timeroute TW){
    Fiber fiber1 =fiberlist.get(0);

    for (Fiber fiber: fiberlist){
        fiber.setFuse(TW);
    }

      for (int i = 0 ; i < fiberlist.size()-1; i++){
          if (fiberlist.get(i).getFuse()>fiberlist.get(i+1).getFuse()){
                 fiber1 = fiberlist.get(i+1);
          }

      }
      return  fiber1;
    }

    public void  DijkstraallfibeList(Node srcNode, Node destNode, Network layer, LinearRoute newRoute,
                          SearchConstraint constraint){
//        boolean flag = false;
        ArrayList<Node> visitedNode = new ArrayList<>();
        List<Node> List = layer.getNodeList();
        for (int i =0 ; i < List.size() ; i ++) {
            Node node = List.get(i);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
            node.setCost_from_src(Double.MAX_VALUE);
            node.setHop_from_src(Integer.MAX_VALUE);
            node.setLengthFromSrc(Double.MAX_VALUE);
        }

        Node curNode = srcNode;
        curNode.setCost_from_src(0);
        curNode.setHop_from_src(0);
        curNode.setLengthFromSrc(0.0);
        curNode.setStatus(Constant.VisitedTwice);

        if (constraint == null) {
            for (Node node : curNode.getAroundNodeList()) {
                if (node.getStatus() == Constant.Unvisited) {
                    traverseNeiNode(layer, visitedNode, curNode, node);
                }
            }
        } else {
            for (Node node : curNode.getAroundNodeList()) { //可以把不需要的值从相邻节点中去除
                if (!constraint.getExcludedNodelist().contains(node)) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                    }
                }
            }
        }

        curNode = getLowestLengthNode(visitedNode);
        while (curNode != null && !curNode.equals(destNode)) {
            curNode.setStatus(Constant.VisitedTwice);
            visitedNode.remove(curNode);

            if (constraint == null) {
                for (Node node : curNode.getAroundNodeList()) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node);
                    } else if (node.getStatus() == Constant.VisitedOnce) {
                        List <Fiber>   fiberList = layer.findFiberList(curNode, node);
                        if (fiberList  == null) {
                            continue;
                        }
                        if (node.getLengthFromSrc()> curNode.getLengthFromSrc()+  fiberList.get(0).getLength()) {
                            node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList.get(0).getLength());
                            node.setHop_from_src(curNode.getHop_from_src() + 1);
                            node.setParentNode(curNode);
                        }
                    }
                }
            } else {
                for (Node node : curNode.getAroundNodeList()) {
                    if (!constraint.getExcludedNodelist().contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
                            traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
                            List <Fiber>   fiberList  = layer.findFiberList(curNode, node);
                            if (fiberList  == null) {
                                continue;
                            }
                            if (! flagofexcludcontainallfibers(fiberList, constraint ) ) {
                                if (node.getLengthFromSrc() > curNode.getLengthFromSrc()+ fiberList.get(0).getLength()) {
                                    node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList .get(0).getLength());
                                    node.setHop_from_src(curNode.getHop_from_src() + 1);
                                    node.setParentNode(curNode);
                                }
                            }
                        }
                    }
                }
            }
            curNode =  getLowestLengthNode(visitedNode);
        }

        newRoute.getNodelist().clear();
        newRoute.getFiberlist().clear();
        curNode = destNode;
        if (destNode.getParentNode() != null) {
            newRoute.getNodelist().add( 0,curNode);
            while (curNode != srcNode) {

                List<Fiber> fiberList = layer.findFiberList(curNode.getParentNode(), curNode);




                //  Random random =new Random();
                //    int n =random.nextInt(fiberList.size());
                if (fiberList.size() != 0) {
                    newRoute.getFiberlist().addAll(fiberList);
                    for(Fiber fiber : fiberList){
                    newRoute.getFiberMap().put(fiber.getName(),fiber);}
                    curNode = curNode.getParentNode();
                    newRoute.getNodelist().add(0, curNode);
                }
            }

//
//            if (flag){newRoute.getNodelist().clear();
//                        newRoute.getFiberlist().clear();}
        }
    }


    public void  DijkstraallfibeList1(Node srcNode, Node destNode, Network layer, LinearRoute newRoute,
                                     SearchConstraint constraint){
        ArrayList<NodePair> nodePairList;
        HashMap<NodePair,Fiber> nodePairFiberMap;
//        boolean flag = false;
        ArrayList<Node> visitedNode = new ArrayList<>();
        List<Node> List = layer.getNodeList();
        for (int i =0 ; i < List.size() ; i ++) {
            Node node = List.get(i);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
            node.setCost_from_src(Double.MAX_VALUE);
            node.setHop_from_src(Integer.MAX_VALUE);
            node.setLengthFromSrc(Double.MAX_VALUE);
        }

        Node curNode = srcNode;
        curNode.setCost_from_src(0);
        curNode.setHop_from_src(0);
        curNode.setLengthFromSrc(0.0);
        curNode.setStatus(Constant.VisitedTwice);

        if (constraint == null) {
            for (Node node : curNode.getAroundNodeList()) {
                if (node.getStatus() == Constant.Unvisited) {
                    traverseNeiNode(layer, visitedNode, curNode, node);
                }
            }
        } else {
            for (Node node : curNode.getAroundNodeList()) { //可以把不需要的值从相邻节点中去除
                if (!constraint.getExcludedNodelist().contains(node)) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                    }
                }
            }
        }

        curNode = getLowestLengthNode(visitedNode);
        while (curNode != null && !curNode.equals(destNode)) {
            curNode.setStatus(Constant.VisitedTwice);
            visitedNode.remove(curNode);

            if (constraint == null) {
                for (Node node : curNode.getAroundNodeList()) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node);
                    } else if (node.getStatus() == Constant.VisitedOnce) {
                        List <Fiber>   fiberList = layer.findFiberList(curNode, node);
                        if (fiberList  == null) {
                            continue;
                        }
                        if (node.getLengthFromSrc()> curNode.getLengthFromSrc()+  fiberList.get(0).getLength()) {
                            node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList.get(0).getLength());
                            node.setHop_from_src(curNode.getHop_from_src() + 1);
                            node.setParentNode(curNode);


                        }
                    }
                }
            } else {
                for (Node node : curNode.getAroundNodeList()) {
                    if (!constraint.getExcludedNodelist().contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
                            traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
                            List <Fiber>   fiberList  = layer.findFiberList(curNode, node);
                            if (fiberList  == null) {
                                continue;
                            }
                            if (! flagofexcludcontainallfibers(fiberList, constraint ) ) {
                                if (node.getLengthFromSrc() > curNode.getLengthFromSrc()+ fiberList.get(0).getLength()) {
                                    node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList .get(0).getLength());
                                    node.setHop_from_src(curNode.getHop_from_src() + 1);
                                    node.setParentNode(curNode);
                                }
                            }
                        }
                    }
                }
            }
            curNode =  getLowestLengthNode(visitedNode);
        }

        newRoute.getNodelist().clear();
        newRoute.getFiberlist().clear();
        curNode = destNode;
        if (destNode.getParentNode() != null) {
            newRoute.getNodelist().add( 0,curNode);
            while (curNode != srcNode) {

                List<Fiber> fiberList = layer.findFiberList(curNode.getParentNode(), curNode);




                //  Random random =new Random();
                //    int n =random.nextInt(fiberList.size());
                if (fiberList.size() != 0) {
                    newRoute.getFiberlist().addAll(fiberList);
                    for(Fiber fiber : fiberList){
                        newRoute.getFiberMap().put(fiber.getName(),fiber);}
                    curNode = curNode.getParentNode();
                    newRoute.getNodelist().add(0, curNode);
                }
            }

//
//            if (flag){newRoute.getNodelist().clear();
//                        newRoute.getFiberlist().clear();}
        }
    }


    public void  Dijkstrarandomfiber(Node srcNode, Node destNode, Network layer, LinearRoute newRoute,
                          SearchConstraint constraint){
//        boolean flag = false;
        ArrayList<Node> visitedNode = new ArrayList<>();
        List<Node> List = layer.getNodeList();
        for (int i =0 ; i < List.size() ; i ++) {
            Node node = List.get(i);
            node.setStatus(Constant.Unvisited);
            node.setParentNode(null);
            node.setCost_from_src(Double.MAX_VALUE);
            node.setHop_from_src(Integer.MAX_VALUE);
            node.setLengthFromSrc(Double.MAX_VALUE);
        }

        Node curNode = srcNode;
        curNode.setCost_from_src(0);
        curNode.setHop_from_src(0);
        curNode.setLengthFromSrc(0.0);
        curNode.setStatus(Constant.VisitedTwice);

        if (constraint == null) {
            for (Node node : curNode.getAroundNodeList()) {
                if (node.getStatus() == Constant.Unvisited) {
                    traverseNeiNode(layer, visitedNode, curNode, node);
                }
            }
        } else {
            for (Node node : curNode.getAroundNodeList()) { //可以把不需要的值从相邻节点中去除
                if (!constraint.getExcludedNodelist().contains(node)) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                    }
                }
            }
        }

        curNode = getLowestLengthNode(visitedNode);
        while (curNode != null && !curNode.equals(destNode)) {
            curNode.setStatus(Constant.VisitedTwice);
            visitedNode.remove(curNode);

            if (constraint == null) {
                for (Node node : curNode.getAroundNodeList()) {
                    if (node.getStatus() == Constant.Unvisited) {
                        traverseNeiNode(layer, visitedNode, curNode, node);
                    } else if (node.getStatus() == Constant.VisitedOnce) {
                        List <Fiber>   fiberList = layer.findFiberList(curNode, node);
                        if (fiberList  == null) {
                            continue;
                        }
                        if (node.getLengthFromSrc()> curNode.getLengthFromSrc()+  fiberList.get(0).getLength()) {
                            node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList.get(0).getLength());
                            node.setHop_from_src(curNode.getHop_from_src() + 1);
                            node.setParentNode(curNode);
                        }
                    }
                }
            } else {
                for (Node node : curNode.getAroundNodeList()) {
                    if (!constraint.getExcludedNodelist().contains(node)) {
                        if (node.getStatus() == Constant.Unvisited) {
                            traverseNeiNode(layer, visitedNode, curNode, node, constraint);
                        } else if (node.getStatus() == Constant.VisitedOnce) {
                            List <Fiber>   fiberList  = layer.findFiberList(curNode, node);
                            if (fiberList  == null) {
                                continue;
                            }
                            if (! flagofexcludcontainallfibers(fiberList, constraint ) ) {
                                if (node.getLengthFromSrc() > curNode.getLengthFromSrc()+ fiberList.get(0).getLength()) {
                                    node.setLengthFromSrc(curNode.getLengthFromSrc() + fiberList .get(0).getLength());
                                    node.setHop_from_src(curNode.getHop_from_src() + 1);
                                    node.setParentNode(curNode);
                                }
                            }
                        }
                    }
                }
            }
            curNode =  getLowestLengthNode(visitedNode);
        }

        newRoute.getNodelist().clear();
        newRoute.getFiberlist().clear();
        curNode = destNode;
        if (destNode.getParentNode() != null) {
            newRoute.getNodelist().add( 0,curNode);
            while (curNode != srcNode) {

                List<Fiber> fiberList = layer.findFiberList(curNode.getParentNode(), curNode);

                //  Random random =new Random();
                //    int n =random.nextInt(fiberList.size());
                if (fiberList.size() != 0) {

                        Random inter = new Random();
                    newRoute.getFiberlist().add(0, fiberList.get(inter.nextInt(fiberList.size())));
                    curNode = curNode.getParentNode();
                    newRoute.getNodelist().add(0, curNode);
                }
            }

//
//            if (flag){newRoute.getNodelist().clear();
//                        newRoute.getFiberlist().clear();}
        }
    }
//    private Fiber findfiber (Network layer, Node nodeA, Node nodeB){
//
//       HashMap<Integer, Fiber> fiberMap = new HashMap<>();
//     //  List<Fiber> fiberList = new ArrayList<>() ;
//       fiberMap = layer.findFiberMap(nodeA, nodeB);
////       fiberList = layer.findFiberList(nodeA, nodeB);
//        fiberMap.keySet();
//
//
//    }


    private  void traverseNeiNode(Network layer, ArrayList<Node> visitedNode, Node curNode, Node node) {
       List <Fiber> fiberList = layer.findFiberList(curNode, node);
        if ( fiberList == null) {
            return;
        }
        node.setCost_from_src(curNode.getCost_from_src() + fiberList.get(0).getLength());
        node.setHop_from_src(curNode.getHop_from_src() + 1);
        node.setLengthFromSrc(curNode.getCost_from_src() + fiberList.get(0).getLength());
        node.setStatus(Constant.VisitedOnce);
        node.setParentNode(curNode);
        visitedNode.add(node);
    }

    private  void traverseNeiNode1(LinearRoute layer, ArrayList<Node> visitedNode, Node curNode, Node node, int fiberNumber, int g) {
        List <Fiber> fiberList = layer.findFiberList(curNode, node,layer, fiberNumber, g);
        if ( fiberList == null) {
            return;
        }
        node.setCost_from_src(curNode.getCost_from_src() + fiberList.get(0).getFuse());
        node.setHop_from_src(curNode.getHop_from_src() + 1);
        node.setLengthFromSrc(curNode.getCost_from_src() + fiberList.get(0).getFuse());
        node.setStatus(Constant.VisitedOnce);
        node.setParentNode(curNode);
        visitedNode.add(node);
    }

    private  void traverseNeiNode(Network layer, ArrayList<Node> visitedNode, Node curNode, Node node, SearchConstraint constraint) {
        List<Fiber> fiberList = layer.findFiberList(curNode, node);

        if ( fiberList.size()==0) {
            return;
        }

        int j =0 ;
        for ( int i = 0 ;  i < fiberList.size(); i++){
           if ( constraint.getExcludedFiberlist().contains(fiberList.get(i))){
                    j++;
           }
        }
        if (j ==  fiberList.size()){
            return;
        }

       else{
            node.setLengthFromSrc(curNode.getLengthFromSrc()+  fiberList.get(0).getLength());
            node.setHop_from_src(curNode.getHop_from_src() + 1);
            node.setStatus(Constant.VisitedOnce);
            node.setParentNode(curNode);
            visitedNode.add(node);
        }
    }


    private  void traverseNeiNode1(LinearRoute layer, ArrayList<Node> visitedNode, Node curNode, Node node, SearchConstraint constraint, int fiberNumber, int g) {
        List<Fiber> fiberList = layer.findFiberList(curNode, node,layer, fiberNumber,g);

        if ( fiberList.size()==0) {
            return;
        }

        int j =0 ;
        for ( int i = 0 ;  i < fiberList.size(); i++){
            if ( constraint.getExcludedFiberlist().contains(fiberList.get(i))){
                j++;
            }
        }
        if (j ==  fiberList.size()){
            return;
        }

        else{
            node.setLengthFromSrc(curNode.getLengthFromSrc()+  fiberList.get(0).getFuse());
            node.setHop_from_src(curNode.getHop_from_src() + 1);
            node.setStatus(Constant.VisitedOnce);
            node.setParentNode(curNode);
            visitedNode.add(node);
        }
    }

    private  Node getLowestLengthNode(List<Node> visited) {
        Node curNode = null;
        double curCost = Double.MAX_VALUE;
        for (Node node : visited) {
            if (node.getLengthFromSrc()< curCost) {
                curNode = node;
                curCost = node.getLengthFromSrc();
            }
        }
        return curNode;
    }

    private  boolean flagofexcludcontainallfibers (List<Fiber> fiberList, SearchConstraint constraint){
        int j =0 ;
        for ( int i = 0 ;  i < fiberList.size(); i++){
            if ( constraint.getExcludedFiberlist().contains(fiberList.get(i))){
                j++;
            }
        }
        if (j ==  fiberList.size()){
            return true;
        }
        else {
            return  false;
        }
    }

    public void kShortestPath(Network layer, int routeNum, boolean randomornot) {
        HashMap<String, NodePair> map = layer.getNodepairMap();
        for (String s : map.keySet()) {
            NodePair nodePair = map.get(s);
           this.kShortest(nodePair.getSrcNode(), nodePair.getDesNode(),layer, routeNum, nodePair.getLinearroutelist(),  randomornot);
            nodePair.setUsedRoute(nodePair.getLinearroutelist().get(0));
        }
    }

    /**
     * 找寻K条最短路径，返回找到的路径数
     */
    public void kShortest(Node srcNode, Node destNode, Network layer, int k, ArrayList<LinearRoute> routeList,boolean randomornot) {
        routeList.clear();
        int numFound = 0; // number of found routes
        SearchConstraint constraint = new SearchConstraint();

        while (true) {
            LinearRoute route = new LinearRoute("", 0);
         if (randomornot = true)
         { this.Dijkstra(srcNode, destNode, layer, route, constraint);}
         else{
             { this. Dijkstrarandomfiber(srcNode, destNode, layer, route, constraint);}
         }

            if (route.getFiberlist().size() > 0) {
                routeList.add(route);
                constraint.getExcludedFiberlist().addAll(route.getFiberlist());
                numFound++;

                if (numFound == k) {
                    break;
                }
            } else {
                break;
            }
        }
    }


}
