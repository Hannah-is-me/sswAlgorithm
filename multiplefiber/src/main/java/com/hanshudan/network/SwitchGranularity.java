package com.hanshudan.network;

import com.hanshudan.general.CommonObject;

import java.util.List;

public class  SwitchGranularity  extends CommonObject {
    private int Type;
    private List<Fiber> fiberList = null;

        public SwitchGranularity (String name, Integer index, Network associateNetwork, Node srcNode, Node destNode, Double length) {
            super(name, index);
        }

        }

