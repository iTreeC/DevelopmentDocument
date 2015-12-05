/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.impl;

import org.apache.log4j.Logger;
import com.itree.engine.api.PermissionEngineAPI;
import com.itree.entity.Perm;

public class PermissionEngine extends EngineImpl implements PermissionEngineAPI {

	private static Logger logger = Logger.getLogger(UserPermissionEngine.class);

	public Boolean add(int id) {
		// �жϲ����Ƿ�Ϸ�
		if (id == 0) {
			logger.info("ID����Ϊ�գ�����");
			return false;
		}
		// �鿴���ݿ����Ƿ���ڴ�Ȩ��
		if (super.pdao.getOneByClientID(id) != null) {
			logger.info("��Ȩ���Ѵ���.");
			return false;
		}
		// �����ݿ������Ȩ��
		Perm perm = new Perm();
		perm.setClientPermissionID(id);
		return super.pdao.add(perm);
	}

	public Boolean delete(int id) {
		// �жϲ����Ƿ�Ϸ�
		if (id == 0) {
			logger.info("Ȩ��ID����Ϊ�գ�����");
			return false;
		}
		// �����ݿ���ɾ��Ȩ��
		return super.pdao.deleteByID(id);

	}

}
