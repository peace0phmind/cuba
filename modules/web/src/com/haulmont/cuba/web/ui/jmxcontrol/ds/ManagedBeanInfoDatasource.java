/*
 * Copyright (c) 2008-2010 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 *
 * Author: Alexander Budarov
 * Created: 17.08.2010 11:28:29
 * $Id$
 */

package com.haulmont.cuba.web.ui.jmxcontrol.ds;

import com.haulmont.bali.datastruct.Node;
import com.haulmont.bali.datastruct.Tree;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.gui.ServiceLocator;
import com.haulmont.cuba.gui.data.DataService;
import com.haulmont.cuba.gui.data.DsContext;
import com.haulmont.cuba.gui.data.impl.AbstractTreeTableDatasource;
import com.haulmont.cuba.jmxcontrol.app.JmxControlService;
import com.haulmont.cuba.jmxcontrol.entity.ManagedBeanDomain;
import com.haulmont.cuba.jmxcontrol.entity.ManagedBeanInfo;

import java.util.*;

public class ManagedBeanInfoDatasource extends AbstractTreeTableDatasource<ManagedBeanInfo, UUID> {
    private static final long serialVersionUID = 4086956405782762547L;

    public ManagedBeanInfoDatasource(DsContext context, DataService dataservice, String id, MetaClass metaClass, String viewName) {
        super(context, dataservice, id, metaClass, viewName);
    }

    @Override
    protected Tree<ManagedBeanInfo> loadTree(Map<String, Object> params) {
        Map<String, Node<ManagedBeanInfo>> domainMap = new HashMap<String, Node<ManagedBeanInfo>>();

        JmxControlService srv = ServiceLocator.lookup(JmxControlService.NAME);

        List<ManagedBeanDomain> domains = srv.getDomains();

        List<Node<ManagedBeanInfo>> nodes = new ArrayList<Node<ManagedBeanInfo>>();

        for (ManagedBeanDomain mbd : domains) {
            ManagedBeanInfo dummy = new ManagedBeanInfo();
            dummy.setDomain(mbd.getName());

            Node<ManagedBeanInfo> node = new Node<ManagedBeanInfo>(dummy);
            domainMap.put(mbd.getName(), node);
            nodes.add(node);
        }

        List<ManagedBeanInfo> list = loadManagedBeans(srv, params);
        for (ManagedBeanInfo mbi : list) {
            if (mbi != null) {
                if (domainMap.containsKey(mbi.getDomain())) {
                    domainMap.get(mbi.getDomain()).addChild(new Node<ManagedBeanInfo>(mbi));
                }
            }
        }

        // remove root nodes that might have left without children after filtering
        for (Node<ManagedBeanInfo> rootNode : new ArrayList<Node<ManagedBeanInfo>>(nodes)) {
            if (rootNode.getChildren().isEmpty()) {
                nodes.remove(rootNode);
            }
        }

        return new Tree<ManagedBeanInfo>(nodes);
    }

    private List<ManagedBeanInfo> loadManagedBeans(JmxControlService srv, Map<String, Object> params) {
        List<ManagedBeanInfo> managedBeans = srv.getManagedBeans();
        List<ManagedBeanInfo> res = new ArrayList<ManagedBeanInfo>();

        // filter by object name
        String objectName = (String) params.get("objectName");
        if (objectName != null) {
            objectName = objectName.toLowerCase();
            for (ManagedBeanInfo mbi : managedBeans) {
                if (mbi.getObjectName() != null && mbi.getObjectName().toLowerCase().contains(objectName)) {
                    res.add(mbi);
                }
            }
            return res;
        }

        return managedBeans;
    }

    public boolean isCaption(UUID itemId) {
        ManagedBeanInfo mbi = (ManagedBeanInfo) data.get(itemId);
        return mbi.getObjectName() == null;
    }

    public String getCaption(UUID itemId) {
        ManagedBeanInfo mbi = (ManagedBeanInfo) data.get(itemId);
        return mbi.getDomain();
    }

}
